/*
 * Created on Oct 8, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.monitor;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.*;
import java.util.Collection;

import org.fest.swing.annotation.*;
import org.fest.swing.edt.GuiQuery;
import org.fest.util.VisibleForTesting;

/**
 * Understands a monitor that keeps track of all known root windows (showing, hidden, closed.)
 *
 * @author Alex Ruiz
 */
public class WindowMonitor {

  private final Context context;
  private final ContextMonitor contextMonitor;
  private final Windows windows;
  private final WindowStatus windowStatus;
  private final WindowAvailabilityMonitor windowAvailabilityMonitor;

  /**
   * Create an instance of WindowTracker which will track all windows coming and going on the current and subsequent
   * <code>AppContext</code>s.
   * <p>
   * <strong>WARNING:</strong> if an applet loads this class, it will only ever see stuff in its own
   * <code>AppContext</code>.
   * </p>
   * @param toolkit the <code>Toolkit</code> to use.
   */
  @RunsInCurrentThread
  WindowMonitor(Toolkit toolkit) {
    this(toolkit, new Context(toolkit), new WindowStatus(new Windows()));
  }

  @VisibleForTesting
  @RunsInCurrentThread
  WindowMonitor(Toolkit toolkit, Context context, WindowStatus windowStatus) {
    this.context = context;
    this.windowStatus = windowStatus;
    windows = windowStatus.windows();
    contextMonitor = new ContextMonitor(context, windows);
    contextMonitor.attachTo(toolkit);
    windowAvailabilityMonitor = new WindowAvailabilityMonitor(windows);
    windowAvailabilityMonitor.attachTo(toolkit);
    populateExistingWindows();
  }

  private void populateExistingWindows() {
    for (Frame f : Frame.getFrames()) examine(f);
  }

  @RunsInCurrentThread
  private void examine(Window w) {
    windows.attachNewWindowVisibilityMonitor(w);
    for (Window owned : w.getOwnedWindows()) examine(owned);
    windows.markExisting(w);
    context.addContextFor(w);
  }

  /**
   * Returns whether the window is ready to receive OS-level event input. A window's "isShowing" flag may be set
   * {@code true} before the <code>WINDOW_OPENED</code> event is generated, and even after the
   * <code>WINDOW_OPENED</code> is sent the window peer is not guaranteed to be ready.
   * @param w the given window.
   * @return whether the window is ready to receive OS-level event input.
   */
  public boolean isWindowReady(Window w) {
    if (windows.isReady(w)) return true;
    windowStatus.checkIfReady(w);
    return false;
  }

  /**
   * Returns the event queue corresponding to the given component. In most cases, this is the same as
   * <code>Component.getToolkit().getSystemEventQueue()</code>, but in the case of applets will bypass the
   * <code>AppContext</code> and provide the real event queue.
   * @param c the given component.
   * @return the event queue corresponding to the given component.
   */
  public EventQueue eventQueueFor(Component c) {
    return context.eventQueueFor(c);
  }

  /**
   * Returns all known event queues.
   * @return all known event queues.
   */
  public Collection<EventQueue> allEventQueues() {
    return context.allEventQueues();
  }

  /**
   * Return all available root windows. A root window is one that has a null parent. Nominally this means a list similar
   * to that returned by <code>{@link Frame#getFrames() Frame.getFrames()}</code>, but in the case of an
   * <code>{@link java.applet.Applet}</code> may return a few dialogs as well.
   * @return all available root windows.
   */
  public Collection<Window> rootWindows() {
    return context.rootWindows();
  }

  /**
   * Returns the singleton instance of this class.
   * @return the singleton instance of this class.
   */
  @RunsInEDT
  public static WindowMonitor instance() {
    return SingletonLazyLoader.INSTANCE;
  }

  @RunsInEDT
  private static class SingletonLazyLoader {
    static final WindowMonitor INSTANCE = execute(new GuiQuery<WindowMonitor>() {
      @Override protected WindowMonitor executeInEDT() throws Throwable {
        return new WindowMonitor(Toolkit.getDefaultToolkit());
      }
    });
  }
}
