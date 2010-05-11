/*
 * Created on Jan 27, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.awt;

import static java.awt.event.InputEvent.BUTTON3_MASK;
import static org.fest.reflect.core.Reflection.staticMethod;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.util.Platform.isWindows;
import static org.fest.util.Strings.concat;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Insets;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.InputEvent;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands utility methods related to AWT.
 *
 * @author Alex Ruiz
 */
public class AWT {

  private static final String APPLET_APPLET_VIEWER_CLASS = "sun.applet.AppletViewer";
  private static final String ROOT_FRAME_CLASSNAME = concat(SwingUtilities.class.getName(), "$");

  /**
   * Indicates whether the given point, relative to the given <code>JComponent</code>, is inside the screen boundaries.
   * @param c the given <code>JComponent</code>.
   * @param p the point to verify.
   * @return <code>true</code> if the point is inside the screen boundaries; <code>false</code> otherwise.
   * @since 1.2
   */
  public static boolean isPointInScreenBoundaries(JComponent c, Point p) {
    Point where = translate(c, p.x, p.y);
    Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    return screen.contains(where);
  }

  /**
   * Indicates whether the given point is inside the screen boundaries.
   * @param p the point to verify.
   * @return <code>true</code> if the point is inside the screen boundaries; <code>false</code> otherwise.
   * @since 1.2
   */
  public static boolean isPointInScreenBoundaries(Point p) {
    Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    return screen.contains(p);
  }

  /**
   * Returns an array of all <code>{@link Window}</code>s that have no owner. They include <code>{@link Frame}</code>s
   * and ownerless <code>{@link Dialog}</code>s and <code>{@link Window}</code>s.
   * <p>
   * This method only works when using JDK 1.6 or later. For JDK 1.5, this method returns an empty array.
   * </p>
   * @return an array of all <code>{@link Window}</code>s that have no owner.
   * @since 1.2
   */
  public static Window[] ownerLessWindows() {
    try {
      // Java 1.6 code
      return staticMethod("getOwnerlessWindows").withReturnType(Window[].class).in(Window.class).invoke();
    } catch (RuntimeException e) {
      return new Window[0];
    }
  }

  /**
   * Translates the given coordinates to the location on screen of the given <code>{@link Component}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param c the given <code>Component</code>.
   * @param x X coordinate.
   * @param y Y coordinate.
   * @return the translated coordinates.
   * @since 1.1
   */
  @RunsInCurrentThread
  public static Point translate(Component c, int x, int y) {
    Point p = locationOnScreenOf(c);
    if (p == null) return null;
    p.translate(x, y);
    return p;
  }


  /**
   * Returns a point at the center of the visible area of the given <code>{@link Component}</code>.
   * @param c the given <code>Component</code>.
   * @return a point at the center of the visible area of the given <code>Component</code>.
   */
  @RunsInEDT
  public static Point visibleCenterOf(final Component c) {
    return execute(new GuiQuery<Point>() {
      protected Point executeInEDT() {
        if (c instanceof JComponent) return centerOfVisibleRect((JComponent)c);
        return centerOf(c);
      }
    });
  }

  /**
   * Returns a point at the center of the given <code>{@link Component}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param c the given <code>Component</code>.
   * @return a point at the center of the given <code>Component</code>.
   */
  @RunsInCurrentThread
  public static Point centerOf(Component c) {
    Dimension size = c.getSize();
    return new Point(size.width / 2, size.height / 2);
  }

  /**
   * Returns a point at the center of the visible rectangle of the given <code>{@link JComponent}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param c the given <code>JComponent</code>.
   * @return a point at the center of the visible rectangle of the given <code>JComponent</code>.
   */
  @RunsInCurrentThread
  public static Point centerOfVisibleRect(JComponent c) {
    Rectangle r = c.getVisibleRect();
    return centerOf(r);
  }

  /**
   * Returns a point at the center of the given <code>{@link Rectangle}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param r the given <code>Rectangle</code>.
   * @return a point at the center of the given <code>Rectangle</code>.
   */
  @RunsInCurrentThread
  public static Point centerOf(Rectangle r) {
    return new Point((r.x + (r.width / 2)), (r.y + (r.height / 2)));
  }

  /**
   * Returns the insets of the given <code>{@link Container}</code>, or an empty one if no insets can be found.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param c the given <code>Container</code>.
   * @return the insets of the given <code>Container</code>, or an empty one if no insets can be found.
   */
  @RunsInCurrentThread
  public static Insets insetsFrom(Container c) {
    try {
      Insets insets = c.getInsets();
      if (insets != null) return insets;
    } catch (Exception e) {}
    return new Insets(0, 0, 0, 0);
  }

  /**
   * Returns <code>true</code> if the given component is an Applet viewer.
   * @param c the component to check.
   * @return <code>true</code> if the given component is an Applet viewer, <code>false</code> otherwise.
   */
  public static boolean isAppletViewer(Component c) {
    return c != null && APPLET_APPLET_VIEWER_CLASS.equals(c.getClass().getName());
  }

  /**
   * Returns whether the given component is the default Swing hidden frame.
   * @param c the component to check.
   * @return <code>true</code> if the given component is the default hidden frame, <code>false</code> otherwise.
   */
  public static boolean isSharedInvisibleFrame(Component c) {
    if (c == null) return false;
    // Must perform an additional check, since applets may have their own version in their AppContext
    return c instanceof Frame
        && (c == JOptionPane.getRootFrame() || c.getClass().getName().startsWith(ROOT_FRAME_CLASSNAME));
  }

  /**
   * Returns whether the given <code>Component</code> is a heavy-weight pop-up, that is, a container for a
   * <code>JPopupMenu</code> that is implemented with a heavy-weight component (usually a <code>Window</code>).
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param c the given <code>Component</code>.
   * @return <code>true</code> if the given <code>Component</code> is a heavy-weight pop-up; <code>false</code>
   * otherwise.
   * @since 1.2
   */
  @RunsInCurrentThread
  public static boolean isHeavyWeightPopup(Component c) {
    if (!(c instanceof Window) || c instanceof Dialog || c instanceof Frame) return false;
    String name = obtainNameSafely(c);
    if ("###overrideRedirect###".equals(name) || "###focusableSwingPopup###".equals(name)) return true;
    String typeName = c.getClass().getName();
    return typeName.indexOf("PopupFactory$WindowPopup") != -1 || typeName.indexOf("HeavyWeightWindow") != -1;
  }

  @RunsInCurrentThread
  private static String obtainNameSafely(Component c) {
    // Work around some components throwing exceptions if getName is called prematurely
    try {
      return c.getName();
    } catch (Throwable e) {
      return null;
    }
  }

  /**
   * Returns the invoker, if any, of the given <code>{@link Component}</code>; or <code>null</code>, if the
   * <code>Component</code> is not on a pop-up of any sort.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param c the given <code>Component</code>.
   * @return the invoker, if any, of the given <code>Component</code>; or <code>null</code>, if the
   *         <code>Component</code> is not on a pop-up of any sort.
   */
  @RunsInCurrentThread
  public static Component invokerOf(final Component c) {
    if (c instanceof JPopupMenu) return ((JPopupMenu)c).getInvoker();
    Container parent = c.getParent();
    return parent != null ? invokerOf(parent) : null;
  }

  /**
   * Safe version of <code>{@link Component#getLocationOnScreen}</code>, which avoids lockup if an AWT pop-up menu is
   * showing. The AWT pop-up holds the AWT tree lock when showing, which lock is required by
   * <code>getLocationOnScreen</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param c the given <code>Component</code>.
   * @return the a point specifying the <code>Component</code>'s top-left corner in the screen's coordinate space, or
   * <code>null</code>, if the <code>Component</code> is not showing on the screen.
   */
  @RunsInCurrentThread
  public static Point locationOnScreenOf(Component c) {
    if (!isAWTTreeLockHeld()) return new Point(c.getLocationOnScreen());
    if (!c.isShowing()) return null;
    Point location = new Point(c.getLocation());
    if (c instanceof Window) return location;
    Container parent = c.getParent();
    if (parent == null) return null;
    Point parentLocation = locationOnScreenOf(parent);
    location.translate(parentLocation.x, parentLocation.y);
    return location;
  }

  /**
   * Returns whether the platform registers a pop-up on mouse press.
   * @return <code>true</code> if the platform registers a pop-up on mouse press, <code>false</code> otherwise.
   */
  public static boolean popupOnPress() {
    // Only w32 is pop-up on release
    return !isWindows();
  }

  /**
   * Returns the <code>{@link InputEvent}</code> mask for the pop-up trigger button.
   * @return the <code>InputEvent</code> mask for the pop-up trigger button.
   */
  public static int popupMask() {
    return BUTTON3_MASK;
  }

  /**
   * Indicates whether the AWT Tree Lock is currently held.
   * @return <code>true</code> if the AWT Tree Lock is currently held, <code>false</code> otherwise.
   */
  public static boolean isAWTTreeLockHeld() {
    Frame[] frames = Frame.getFrames();
    if (frames.length == 0) return false;
    // From Abbot: Hack based on 1.4.2 java.awt.PopupMenu implementation, which blocks the event dispatch thread while
    // the pop-up is visible, while holding the AWT tree lock.
    // Start another thread which attempts to get the tree lock.
    // If it can't get the tree lock, then there is a pop-up active in the current tree.
    // Any component can provide the tree lock.
    ThreadStateChecker checker = new ThreadStateChecker(frames[0].getTreeLock());
    try {
      checker.start();
      // wait a little bit for the checker to finish
      if (checker.isAlive()) checker.join(100);
      return checker.isAlive();
    } catch (InterruptedException e) {
      return false;
    }
  }

  // Try to lock the AWT tree lock; returns immediately if it can
  private static class ThreadStateChecker extends Thread {
    private final Object lock;

    public ThreadStateChecker(Object lock) {
      super("Thread state checker");
      setDaemon(true);
      this.lock = lock;
    }

    @Override public synchronized void start() {
      super.start();
      try {
        wait(30000);
      } catch (InterruptedException e) {}
    }

    @Override public void run() {
      synchronized (this) {
        notifyAll();
      }
      synchronized (lock) {
        setName(super.getName()); // dummy operation
      }
    }
  }

  private AWT() {}
}
