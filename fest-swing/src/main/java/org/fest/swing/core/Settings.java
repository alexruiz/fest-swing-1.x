/*
 * Created on Dec 19, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.core;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static org.fest.swing.core.ComponentLookupScope.DEFAULT;
import static org.fest.swing.util.Platform.*;

/**
 * Understands configuration settings.
 *
 * @author Alex Ruiz
 */
public class Settings {

  private static final int DEFAULT_DELAY = 30000;

  private ComponentLookupScope componentLookupScope;
  private int timeoutToBeVisible;
  private int timeoutToFindPopup;
  private int timeoutToFindSubMenu;
  private int delayBetweenEvents;
  private int dragDelay;
  private int dropDelay;
  private int eventPostingDelay;
  private int idleTimeout;

  private java.awt.Robot robot;

  public Settings() {
    timeoutToBeVisible(DEFAULT_DELAY);
    timeoutToFindPopup(DEFAULT_DELAY);
    timeoutToFindSubMenu(100);
    delayBetweenEvents(60);
    dragDelay(0);
    dropDelay(0);
    eventPostingDelay(100);
    componentLookupScope(DEFAULT);
    idleTimeout(10000);
  }

  void attachTo(java.awt.Robot newRobot) {
    robot = newRobot;
    if (delayBetweenEvents < 0) delayBetweenEvents = this.robot.getAutoDelay();
    else updateRobotAutoDelay();
  }

  java.awt.Robot robot() { return robot; }

  /**
   * Returns a value representing the millisecond count in between generated events. The default is 60 milliseconds.
   * @return a value representing the millisecond count in between generated events.
   */
  public int delayBetweenEvents() {
    return delayBetweenEvents;
  }

  /**
   * Updates the value representing the millisecond count in between generated events. Usually just set to 100-200 if
   * you want to slow down the playback to simulate actual user input. The default is 60 milliseconds.
   * <p>
   * To change the speed of a GUI test, you need to change the values of both <code>delayBetweenEvents</code> and
   * <code>eventPostingDelay</code>.
   * </p>
   * @param ms the millisecond count in between generated events. It should be between -1 and 60000.
   * @see #eventPostingDelay(int)
   */
  public void delayBetweenEvents(int ms) {
    delayBetweenEvents = valueToUpdate(ms, -1, 60000);
    if (robot != null) updateRobotAutoDelay();
  }

  private void updateRobotAutoDelay() {
    robot.setAutoDelay(delayBetweenEvents);
  }

  /**
   * Returns the number of milliseconds to wait for a component to be visible. The default value is 30000 milliseconds.
   * @return the number of milliseconds to wait for a component to be visible.
   */
  public int timeoutToBeVisible() {
    return timeoutToBeVisible;
  }

  /**
   * Updates the number of milliseconds to wait for a component to be visible. The default value is 30000 milliseconds.
   * @param ms the time in milliseconds. It should be between 0 and 60000.
   */
  public void timeoutToBeVisible(int ms) {
    timeoutToBeVisible = valueToUpdate(ms, 0, 60000);
  }

  /**
   * Returns the number of milliseconds to wait before failing to find a pop-up menu that should appear. The default
   * value is 30000 milliseconds.
   * @return the number of milliseconds to wait before failing to find a pop-up menu that should appear.
   */
  public int timeoutToFindPopup() {
    return timeoutToFindPopup;
  }

  /**
   * Updates the number of milliseconds to wait before failing to find a pop-up menu that should appear. The default
   * value is 30000 milliseconds.
   * @param ms the time in milliseconds. It should be between 0 and 60000.
   */
  public void timeoutToFindPopup(int ms) {
    timeoutToFindPopup = valueToUpdate(ms, 0, 60000);
  }

  /**
   * Returns the number of milliseconds to wait for a sub-menu to appear. The default value is 100 milliseconds.
   * @return  the number of milliseconds to wait for a sub-menu to appear.
   */
  public int timeoutToFindSubMenu() {
    return timeoutToFindSubMenu;
  }

  /**
   * Updates the number of milliseconds to wait for a sub-menu to appear. The default value is 100 milliseconds.
   * @param ms the time in milliseconds. It should be between 0 and 10000.
   */
  public void timeoutToFindSubMenu(int ms) {
    timeoutToFindSubMenu = valueToUpdate(ms, 0, 10000);
  }

  /**
   * Returns the number of milliseconds to wait between a pressing a mouse button and moving the mouse. The default
   * value for Mac OS X or the X11 Windowing system is 100 milliseconds. For other platforms, the default value is 0.
   * @return the number of milliseconds to wait between a pressing a mouse button and moving the mouse.
   */
  public int dragDelay() {
    return dragDelay;
  }

  /**
   * Updates the number of milliseconds to wait between a pressing a mouse button and moving the mouse. The default
   * value for Mac OS X or the X11 Windowing system is 100 milliseconds. For other platforms, the default value is 0.
   * @param ms the time in milliseconds. For Mac OS X or the X11 Windowing system, the minimum value is 100. For other
   * platforms the minimum value is 0. The maximum value for all platforms is 60000.
   */
  public void dragDelay(int ms) {
    int min = isX11() || isOSX() ? 100 : 0;
    dragDelay = valueToUpdate(ms, min, 60000);
  }

  /**
   * Returns the number of milliseconds before checking for idle. The default value is 100 milliseconds.
   * @return the number of milliseconds before checking for idle.
   */
  public int eventPostingDelay() {
    return eventPostingDelay;
  }

  /**
   * Updates the number of milliseconds before checking for idle. This allows the system a little time to put a native
   * event onto the AWT event queue. The default value is 100 milliseconds.
   * <p>
   * To change the speed of a GUI test, you need to change the values of both <code>delayBetweenEvents</code> and
   * <code>eventPostingDelay</code>.
   * </p>
   * @param ms the time in milliseconds. It should be between 0 and 1000.
   * @see #delayBetweenEvents(int)
   */
  public void eventPostingDelay(int ms) {
    eventPostingDelay = valueToUpdate(ms, 0, 1000);
  }

  /**
   * Returns the number of milliseconds between the final mouse movement and mouse release to ensure drop ends. The
   * default value for Windows is 200. For other platforms, the default value is 0.
   * @return the number of milliseconds between the final mouse movement and mouse release to ensure drop ends.
   */
  public int dropDelay() {
    return dropDelay;
  }

  /**
   * Updates the number of milliseconds between the final mouse movement and mouse release to ensure drop ends. The
   * default value for Windows is 200. For other platforms, the default value is 0.
   * @param ms the time in milliseconds. For Windows, the minimum value is 200. For other platforms, the minimum value
   * is 0. The maximum value for all platforms is 60000.
   */
  public void dropDelay(int ms) {
    int min = isWindows() ? 200 : 0;
    dropDelay = valueToUpdate(ms, min, 60000);
  }

  /**
   * Returns the scope of component lookups. This setting only affects the component fixtures in the package
   * <code>org.fest.swing.fixture</code>. The default value is <code>{@link ComponentLookupScope#DEFAULT}</code>.
   * @return the scope of component lookups.
   */
  public ComponentLookupScope componentLookupScope() {
    return componentLookupScope;
  }

  /**
   * Updates the scope of component lookups.  This setting only affects the component fixtures in the package
   * <code>org.fest.swing.fixture</code>. The default value is <code>{@link ComponentLookupScope#DEFAULT}</code>.
   * @param scope the new value for the scope.
   */
  public void componentLookupScope(ComponentLookupScope scope) {
    componentLookupScope = scope;
  }

  /**
   * Returns the time (in milliseconds) to wait for an idle AWT event queue. The default value is 10000 milliseconds.
   * @return the time (in milliseconds) to wait for an idle AWT event queue.
   */
  public int idleTimeout() {
    return idleTimeout;
  }

  /**
   * Updates the time (in milliseconds) to wait for an idle AWT event queue.
   * @param ms the new time. The value should be equal to or greater than zero.
   */
  public void idleTimeout(int ms) {
    this.idleTimeout = valueToUpdate(ms, 0, Integer.MAX_VALUE);
  }

  private int valueToUpdate(int value, int min, int max) {
    return max(min, min(max, value));
  }
}
