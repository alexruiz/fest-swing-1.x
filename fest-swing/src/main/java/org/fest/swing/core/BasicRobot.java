/*
 * Created on Sep 29, 2006
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2006-2010 the original author or authors.
 */
package org.fest.swing.core;

import static java.awt.event.InputEvent.BUTTON1_MASK;
import static java.awt.event.InputEvent.BUTTON2_MASK;
import static java.awt.event.InputEvent.BUTTON3_MASK;
import static java.awt.event.KeyEvent.CHAR_UNDEFINED;
import static java.awt.event.KeyEvent.KEY_TYPED;
import static java.awt.event.KeyEvent.VK_UNDEFINED;
import static java.awt.event.WindowEvent.WINDOW_CLOSING;
import static java.lang.System.currentTimeMillis;
import static javax.swing.SwingUtilities.getWindowAncestor;
import static javax.swing.SwingUtilities.isEventDispatchThread;
import static org.fest.swing.awt.AWT.centerOf;
import static org.fest.swing.awt.AWT.visibleCenterOf;
import static org.fest.swing.core.ActivateWindowTask.activateWindow;
import static org.fest.swing.core.ComponentIsFocusableQuery.isFocusable;
import static org.fest.swing.core.ComponentRequestFocusTask.giveFocusTo;
import static org.fest.swing.core.FocusOwnerFinder.focusOwner;
import static org.fest.swing.core.FocusOwnerFinder.inEdtFocusOwner;
import static org.fest.swing.core.InputModifiers.unify;
import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.core.MouseButton.RIGHT_BUTTON;
import static org.fest.swing.core.Scrolling.scrollToVisible;
import static org.fest.swing.core.WindowAncestorFinder.windowAncestorOf;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.format.Formatting.inEdtFormat;
import static org.fest.swing.hierarchy.NewHierarchy.ignoreExistingComponents;
import static org.fest.swing.keystroke.KeyStrokeMap.keyStrokeFor;
import static org.fest.swing.query.ComponentShowingQuery.isShowing;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.swing.util.Modifiers.keysFor;
import static org.fest.swing.util.Modifiers.updateModifierWithKeyCode;
import static org.fest.swing.util.TimeoutWatch.startWatchWithTimeoutOf;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.isEmpty;

import java.applet.Applet;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.InvocationEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;
import javax.swing.KeyStroke;

import net.jcip.annotations.GuardedBy;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.hierarchy.ComponentHierarchy;
import org.fest.swing.hierarchy.ExistingHierarchy;
import org.fest.swing.input.InputState;
import org.fest.swing.lock.ScreenLock;
import org.fest.swing.monitor.WindowMonitor;
import org.fest.swing.util.Pair;
import org.fest.swing.util.TimeoutWatch;
import org.fest.util.VisibleForTesting;

/**
 * Understands simulation of user events on a GUI <code>{@link Component}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class BasicRobot implements Robot {

  private static final int POPUP_DELAY = 10000;
  private static final int POPUP_TIMEOUT = 5000;
  private static final int WINDOW_DELAY = 20000;

  private static final ComponentMatcher POPUP_MATCHER = new TypeMatcher(JPopupMenu.class, true);

  @GuardedBy("this") private volatile boolean active;

  private static final Runnable EMPTY_RUNNABLE = new Runnable() {
    public void run() {}
  };

  private static final int BUTTON_MASK = BUTTON1_MASK | BUTTON2_MASK | BUTTON3_MASK;

  private static Toolkit toolkit = Toolkit.getDefaultToolkit();
  private static WindowMonitor windowMonitor = WindowMonitor.instance();
  private static InputState inputState = new InputState(toolkit);

  /** Provides access to all the components in the hierarchy. */
  private final ComponentHierarchy hierarchy;

  private final Object screenLockOwner;

  /** Looks up <code>{@link java.awt.Component}</code>s. */
  private final ComponentFinder finder;

  private final Settings settings;

  private final AWTEventPoster eventPoster;
  private final InputEventGenerator eventGenerator;
  private final UnexpectedJOptionPaneFinder unexpectedJOptionPaneFinder;

  /**
   * Creates a new <code>{@link Robot}</code> with a new AWT hierarchy. The created <code>Robot</code> will not be able
   * to access any components that were created before it.
   * @return the created <code>Robot</code>.
   */
  public static Robot robotWithNewAwtHierarchy() {
    Object screenLockOwner = acquireScreenLock();
    return new BasicRobot(screenLockOwner, ignoreExistingComponents());
  }

  /**
   * Creates a new <code>{@link Robot}</code> that has access to all the GUI components in the AWT hierarchy.
   * @return the created <code>Robot</code>.
   */
  public static Robot robotWithCurrentAwtHierarchy() {
    Object screenLockOwner = acquireScreenLock();
    return new BasicRobot(screenLockOwner, new ExistingHierarchy());
  }

  private static Object acquireScreenLock() {
    Object screenLockOwner = new Object();
    ScreenLock.instance().acquire(screenLockOwner);
    return screenLockOwner;
  }

  @VisibleForTesting
  BasicRobot(Object screenLockOwner, ComponentHierarchy hierarchy) {
    this.screenLockOwner = screenLockOwner;
    this.hierarchy = hierarchy;
    settings = new Settings();
    eventGenerator = new RobotEventGenerator(settings);
    eventPoster = new AWTEventPoster(toolkit, inputState, windowMonitor, settings);
    finder = new BasicComponentFinder(this.hierarchy, settings);
    unexpectedJOptionPaneFinder = new UnexpectedJOptionPaneFinder(finder);
    active = true;
  }

  /** {@inheritDoc} */
  public ComponentPrinter printer() {
    return finder().printer();
  }

  /** {@inheritDoc} */
  public ComponentFinder finder() {
    return finder;
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void showWindow(Window w) {
    showWindow(w, null, true);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void showWindow(Window w, Dimension size) {
    showWindow(w, size, true);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void showWindow(final Window w, final Dimension size, final boolean pack) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        if (pack) packAndEnsureSafePosition(w);
        if (size != null) w.setSize(size);
        w.setVisible(true);
      }
    });
    waitForWindow(w);
  }

  @RunsInCurrentThread
  private void packAndEnsureSafePosition(Window w) {
    w.pack();
    w.setLocation(100, 100);
  }

  @RunsInEDT
  private void waitForWindow(Window w) {
    long start = currentTimeMillis();
    while (!windowMonitor.isWindowReady(w) || !isShowing(w)) {
      long elapsed = currentTimeMillis() - start;
      if (elapsed > WINDOW_DELAY)
        throw new WaitTimedOutError(concat("Timed out waiting for Window to open (", String.valueOf(elapsed), "ms)"));
      pause();
    }
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void close(Window w) {
    WindowEvent event = new WindowEvent(w, WINDOW_CLOSING);
    // If the window contains an applet, send the event on the applet's queue instead to ensure a shutdown from the
    // applet's context (assists AppletViewer cleanup).
    Component applet = findAppletDescendent(w);
    EventQueue eventQueue = windowMonitor.eventQueueFor(applet != null ? applet : w);
    eventQueue.postEvent(event);
    waitForIdle();
  }

  /**
   * Returns the <code>{@link Applet}</code> descendant of the given <code>{@link Container}</code>, if any.
   * @param c the given {@code Container}.
   * @return the <code>Applet</code> descendant of the given {@code Container}, or {@code null} if none
   * is found.
   */
  @RunsInEDT
  private Applet findAppletDescendent(Container c) {
    List<Component> found = new ArrayList<Component>(finder.findAll(c, new TypeMatcher(Applet.class)));
    if (found.size() == 1) return (Applet)found.get(0);
    return null;
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void focusAndWaitForFocusGain(Component c) {
    focus(c, true);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void focus(Component c) {
    focus(c, false);
  }

  @RunsInEDT
  private void focus(Component target, boolean wait) {
    Component currentOwner = inEdtFocusOwner();
    if (currentOwner == target) return;
    FocusMonitor focusMonitor = FocusMonitor.attachTo(target);
    // for pointer focus
    moveMouse(target);
    // Make sure the correct window is in front
    activateWindowOfFocusTarget(target, currentOwner);
    giveFocusTo(target);
    try {
      if (wait) {
        TimeoutWatch watch = startWatchWithTimeoutOf(settings().timeoutToBeVisible());
        while (!focusMonitor.hasFocus()) {
          if (watch.isTimeOut()) throw actionFailure(concat("Focus change to ", format(target), " failed"));
          pause();
        }
      }
    } finally {
      target.removeFocusListener(focusMonitor);
    }
  }

  @RunsInEDT
  private void activateWindowOfFocusTarget(Component target, Component currentOwner) {
    Pair<Window, Window> windowAncestors = windowAncestorsOf(currentOwner, target);
    Window currentOwnerAncestor = windowAncestors.i;
    Window targetAncestor = windowAncestors.ii;
    if (currentOwnerAncestor == targetAncestor) return;
    activate(targetAncestor);
    waitForIdle();
  }

  @RunsInEDT
  private static Pair<Window, Window> windowAncestorsOf(final Component one, final Component two) {
    return execute(new GuiQuery<Pair<Window, Window>>() {
      protected Pair<Window, Window> executeInEDT() throws Throwable {
        return new Pair<Window, Window>(windowAncestor(one), windowAncestor(two));
      }

      private Window windowAncestor(Component c) {
        return (c != null) ? windowAncestorOf(c) : null;
      }
    });
  }

  /**
   * Activates the given <code>{@link Window}</code>. "Activate" means that the given window gets the keyboard focus.
   * @param w the window to activate.
   */
  @RunsInEDT
  private void activate(Window w) {
    activateWindow(w);
    moveMouse(w); // For pointer-focus systems
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public synchronized void cleanUp() {
    cleanUp(true);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public synchronized void cleanUpWithoutDisposingWindows() {
    cleanUp(false);
  }

  @RunsInEDT
  private void cleanUp(boolean disposeWindows) {
    try {
      if (disposeWindows) disposeWindows(hierarchy);
      releaseMouseButtons();
    } finally {
      active = false;
      releaseScreenLock();
    }
  }

  private void releaseScreenLock() {
    ScreenLock screenLock = ScreenLock.instance();
    if (screenLock.acquiredBy(screenLockOwner)) screenLock.release(screenLockOwner);
  }

  @RunsInEDT
  private static void disposeWindows(final ComponentHierarchy hierarchy) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        for (Container c : hierarchy.roots()) if (c instanceof Window) dispose(hierarchy, (Window)c);
      }
    });
  }

  @RunsInCurrentThread
  private static void dispose(final ComponentHierarchy hierarchy, Window w) {
    hierarchy.dispose(w);
    w.setVisible(false);
    w.dispose();
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void click(Component c) {
    click(c, LEFT_BUTTON);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void rightClick(Component c) {
    click(c, RIGHT_BUTTON);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void click(Component c, MouseButton button) {
    click(c, button, 1);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void doubleClick(Component c) {
    click(c, LEFT_BUTTON, 2);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void click(Component c, MouseButton button, int times) {
    Point where = visibleCenterOf(c);
    if (c instanceof JComponent)
      where = scrollIfNecessary((JComponent) c, where);
    click(c, where, button, times);
  }

  private Point scrollIfNecessary(JComponent c, Point p) {
    scrollToVisible(this, c);
    return visibleCenterOf(c);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void click(Component c, Point where) {
    click(c, where, LEFT_BUTTON, 1);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void click(Point where, MouseButton button, int times) {
    click(null, where, button, times);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void click(Component c, Point where, MouseButton button, int times) {
    int mask = button.mask;
    int modifierMask = mask & ~BUTTON_MASK;
    mask &= BUTTON_MASK;
    pressModifiers(modifierMask);
    // From Abbot: Adjust the auto-delay to ensure we actually get a multiple click
    // In general clicks have to be less than 200ms apart, although the actual setting is not readable by Java.
    int delayBetweenEvents = settings.delayBetweenEvents();
    if (shouldSetDelayBetweenEventsToZeroWhenClicking(times)) settings.delayBetweenEvents(0);
    eventGenerator.pressMouse(c, where, mask);
    for (int i = times; i > 1; i--) {
      eventGenerator.releaseMouse(mask);
      eventGenerator.pressMouse(c, where, mask);
    }
    settings.delayBetweenEvents(delayBetweenEvents);
    eventGenerator.releaseMouse(mask);
    releaseModifiers(modifierMask);
    waitForIdle();
  }

  private boolean shouldSetDelayBetweenEventsToZeroWhenClicking(int times) {
    return times > 1 /*FEST-137: && settings.delayBetweenEvents() * 2 > 200*/;
  }

  /** {@inheritDoc} */
  public void pressModifiers(int modifierMask) {
    for (int modifierKey : keysFor(modifierMask))
      pressKey(modifierKey);
  }

  /** {@inheritDoc} */
  public void releaseModifiers(int modifierMask) {
    // For consistency, release in the reverse order of press.
    int[] modifierKeys = keysFor(modifierMask);
    for (int i = modifierKeys.length - 1; i >= 0; i--)
      releaseKey(modifierKeys[i]);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void moveMouse(Component c) {
    moveMouse(c, visibleCenterOf(c));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void moveMouse(Component c, Point p) {
    moveMouse(c, p.x, p.y);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void moveMouse(Component c, int x, int y) {
    if (!waitForComponentToBeReady(c, settings.timeoutToBeVisible()))
      throw actionFailure(concat("Could not obtain position of component ", format(c)));
    eventGenerator.moveMouse(c, x, y);
    waitForIdle();
  }

  /** {@inheritDoc} */
  public void moveMouse(Point p) {
    moveMouse(p.x, p.y);
  }

  /** {@inheritDoc} */
  public void moveMouse(int x, int y) {
    eventGenerator.moveMouse(x, y);
  }

  /** {@inheritDoc} */
  public void pressMouse(MouseButton button) {
    eventGenerator.pressMouse(button.mask);
  }

  /** {@inheritDoc} */
  public void pressMouse(Component c, Point where) {
    pressMouse(c, where, LEFT_BUTTON);
  }

  /** {@inheritDoc} */
  public void pressMouse(Component c, Point where, MouseButton button) {
    jitter(c, where);
    moveMouse(c, where.x, where.y);
    eventGenerator.pressMouse(c, where, button.mask);
  }

  /** {@inheritDoc} */
  public void pressMouse(Point where, MouseButton button) {
    eventGenerator.pressMouse(where, button.mask);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void releaseMouse(MouseButton button) {
    mouseRelease(button.mask);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void releaseMouseButtons() {
    int buttons = inputState.buttons();
    if (buttons == 0) return;
    mouseRelease(buttons);
  }

  /** {@inheritDoc} */
  public void rotateMouseWheel(Component c, int amount) {
    moveMouse(c);
    rotateMouseWheel(amount);
  }

  /** {@inheritDoc} */
  public void rotateMouseWheel(int amount) {
    eventGenerator.rotateMouseWheel(amount);
    waitForIdle();
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void jitter(Component c) {
    jitter(c, visibleCenterOf(c));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void jitter(Component c, Point where) {
    int x = where.x;
    int y = where.y;
    moveMouse(c, (x > 0 ? x - 1 : x + 1), y);
  }

  // Wait the given number of milliseconds for the component to be showing and ready.
  @RunsInEDT
  private boolean waitForComponentToBeReady(Component c, long timeout) {
    if (isReadyForInput(c)) return true;
    TimeoutWatch watch = startWatchWithTimeoutOf(timeout);
    while (!isReadyForInput(c)) {
      if (c instanceof JPopupMenu) {
        // wiggle the mouse over the parent menu item to ensure the sub-menu shows
        Pair<Component, Point> invokerAndCenterOfInvoker = invokerAndCenterOfInvoker((JPopupMenu)c);
        Component invoker = invokerAndCenterOfInvoker.i;
        if (invoker instanceof JMenu) jitter(invoker, invokerAndCenterOfInvoker.ii);
      }
      if (watch.isTimeOut()) return false;
      pause();
    }
    return true;
  }

  @RunsInEDT
  private static Pair<Component, Point> invokerAndCenterOfInvoker(final JPopupMenu popupMenu) {
    return execute(new GuiQuery<Pair<Component, Point>>() {
      protected Pair<Component, Point> executeInEDT() {
        Component invoker = popupMenu.getInvoker();
        return new Pair<Component, Point>(invoker, centerOf(invoker));
      }
    });
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void enterText(String text) {
    if (isEmpty(text)) return;
    for (char character : text.toCharArray()) type(character);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void type(char character) {
    KeyStroke keyStroke = keyStrokeFor(character);
    if (keyStroke == null) {
      Component focus = focusOwner();
      if (focus == null) return;
      KeyEvent keyEvent = keyEventFor(focus, character);
      // Allow any pending robot events to complete; otherwise we might stuff the typed event before previous
      // robot-generated events are posted.
      waitForIdle();
      eventPoster.postEvent(focus, keyEvent);
      return;
    }
    keyPressAndRelease(keyStroke.getKeyCode(), keyStroke.getModifiers());
  }

  private KeyEvent keyEventFor(Component c, char character) {
    return new KeyEvent(c, KEY_TYPED, System.currentTimeMillis(), 0, VK_UNDEFINED, character);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void pressAndReleaseKey(int keyCode, int... modifiers) {
    keyPressAndRelease(keyCode, unify(modifiers));
    waitForIdle();
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void pressAndReleaseKeys(int... keyCodes) {
    for (int keyCode : keyCodes) {
      keyPressAndRelease(keyCode, 0);
      waitForIdle();
      pause(50); // it seems that even when waiting for idle the events are not completely propagated
    }
  }

  @RunsInEDT
  private void keyPressAndRelease(int keyCode, int modifiers) {
    int updatedModifiers = updateModifierWithKeyCode(keyCode, modifiers);
    pressModifiers(updatedModifiers);
    if (updatedModifiers == modifiers) {
      doPressKey(keyCode);
      eventGenerator.releaseKey(keyCode);
    }
    releaseModifiers(updatedModifiers);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void pressKey(int keyCode) {
    doPressKey(keyCode);
    waitForIdle();
  }

  @RunsInEDT
  private void doPressKey(int keyCode) {
    eventGenerator.pressKey(keyCode, CHAR_UNDEFINED);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void releaseKey(int keyCode) {
    eventGenerator.releaseKey(keyCode);
    waitForIdle();
  }

  @RunsInEDT
  private void mouseRelease(int buttons) {
    eventGenerator.releaseMouse(buttons);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void waitForIdle() {
    waitIfNecessary();
    Collection<EventQueue> queues = windowMonitor.allEventQueues();
    if (queues.size() == 1) {
      waitForIdle(toolkit.getSystemEventQueue());
      return;
    }
    // FIXME this resurrects dead event queues
    for (EventQueue queue : queues) waitForIdle(queue);
  }

  private void waitIfNecessary() {
    int delayBetweenEvents = settings.delayBetweenEvents();
    int eventPostingDelay  = settings.eventPostingDelay();
    if (eventPostingDelay > delayBetweenEvents) pause(eventPostingDelay - delayBetweenEvents);
  }

  private void waitForIdle(EventQueue eventQueue) {
    if (EventQueue.isDispatchThread())
      throw new IllegalThreadStateException("Cannot call method from the event dispatcher thread");
    // Abbot: as of Java 1.3.1, robot.waitForIdle only waits for the last event on the queue at the time of this
    // invocation to be processed. We need better than that. Make sure the given event queue is empty when this method
    // returns.
    // We always post at least one idle event to allow any current event dispatch processing to finish.
    long start = currentTimeMillis();
    int count = 0;
    do {
      // Timed out waiting for idle
      int idleTimeout = settings.idleTimeout();
      if (postInvocationEvent(eventQueue, idleTimeout)) break;
      // Timed out waiting for idle event queue
      if (currentTimeMillis() - start > idleTimeout) break;
      ++count;
      // Force a yield
      pause();
      // Abbot: this does not detect invocation events (i.e. what gets posted with EventQueue.invokeLater), so if
      // someone is repeatedly posting one, we might get stuck. Not too worried, since if a Runnable keeps calling
      // invokeLater on itself, *nothing* else gets much chance to run, so it seems to be a bad programming practice.
    } while (eventQueue.peekEvent() != null);
  }

  // Indicates whether we timed out waiting for the invocation to run
  @RunsInEDT
  private boolean postInvocationEvent(EventQueue eventQueue, long timeout) {
    Object lock = new RobotIdleLock();
    synchronized (lock) {
      eventQueue.postEvent(new InvocationEvent(toolkit, EMPTY_RUNNABLE, lock, true));
      long start = currentTimeMillis();
      try {
        // NOTE: on fast linux systems when showing a dialog, if we don't provide a timeout, we're never notified, and
        // the test will wait forever (up through 1.5.0_05).
        lock.wait(timeout);
        return (currentTimeMillis() - start) >= settings.idleTimeout();
      } catch (InterruptedException e) {}
      return false;
    }
  }

  private static class RobotIdleLock {
    RobotIdleLock() {}
  }

  /** {@inheritDoc} */
  public boolean isDragging() {
    return inputState.dragInProgress();
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public JPopupMenu showPopupMenu(Component invoker) {
    return showPopupMenu(invoker, visibleCenterOf(invoker));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public JPopupMenu showPopupMenu(Component invoker, Point location) {
    if (isFocusable(invoker)) focusAndWaitForFocusGain(invoker);
    click(invoker, location, RIGHT_BUTTON, 1);
    JPopupMenu popup = findActivePopupMenu();
    if (popup == null)
      throw new ComponentLookupException(concat("Unable to show popup at ", location, " on ", inEdtFormat(invoker)));
    long start = currentTimeMillis();
    while (!isWindowAncestorReadyForInput(popup) && currentTimeMillis() - start > POPUP_DELAY)
      pause();
    return popup;
  }

  @RunsInEDT
  private boolean isWindowAncestorReadyForInput(final JPopupMenu popup) {
    return execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        return isReadyForInput(getWindowAncestor(popup));
      }
    });
  }

  /**
   * Indicates whether the given <code>{@link Component}</code> is ready for input.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param c the given {@code Component}.
   * @return {@code true} if the given {@code Component} is ready for input, {@code false} otherwise.
   * @throws ActionFailedException if the given {@code Component} does not have a {@code Window} ancestor.
   */
  @RunsInCurrentThread
  public boolean isReadyForInput(Component c) {
    Window w = windowAncestorOf(c);
    if (w == null) throw actionFailure(concat("Component ", format(c), " does not have a Window ancestor"));
    return c.isShowing() && windowMonitor.isWindowReady(w);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public JPopupMenu findActivePopupMenu() {
    JPopupMenu popup = activePopupMenu();
    if (popup != null || isEventDispatchThread()) return popup;
    TimeoutWatch watch = startWatchWithTimeoutOf(POPUP_TIMEOUT);
    while ((popup = activePopupMenu()) == null) {
      if (watch.isTimeOut()) break;
      pause(100);
    }
    return popup;
  }

  @RunsInEDT
  private JPopupMenu activePopupMenu() {
    List<Component> found = new ArrayList<Component>(finder().findAll(POPUP_MATCHER));
    if (found.size() == 1) return (JPopupMenu)found.get(0);
    return null;
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void requireNoJOptionPaneIsShowing() {
    unexpectedJOptionPaneFinder.requireNoJOptionPaneIsShowing();
  }

  /** {@inheritDoc} */
  public Settings settings() {
    return settings;
  }

  /** {@inheritDoc} */
  public ComponentHierarchy hierarchy() {
    return hierarchy;
  }

  /** {@inheritDoc} */
  public synchronized boolean isActive() { return active; }

  @VisibleForTesting
  final Object screenLockOwner() { return screenLockOwner; }
}
