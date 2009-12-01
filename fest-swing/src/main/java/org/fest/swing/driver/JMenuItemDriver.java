/*
 * Created on Jan 30, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static java.lang.Boolean.getBoolean;
import static org.fest.swing.core.WindowAncestorFinder.windowAncestorOf;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.JMenuPopupMenuQuery.popupMenuOf;
import static org.fest.swing.driver.WindowMoveToFrontTask.toFront;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.swing.util.Platform.isOSX;
import static org.fest.util.Strings.concat;

import java.awt.Window;

import javax.swing.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ActionFailedException;

/**
 * Understands functional testing of <code>{@link JMenuItem}</code>s including:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JMenuItemDriver extends JComponentDriver {

  /**
   * With decreased robot auto delay, OSX pop-up menus don't activate properly. Indicate the minimum delay for proper
   * operation (determined experimentally.)
   */
  private static final int SUBMENU_DELAY = isOSX() ? 100 : 0;

  /**
   * Creates a new </code>{@link JMenuItemDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JMenuItemDriver(Robot robot) {
    super(robot);
  }

  @RunsInEDT
  private void show(JMenuItem menuItem) {
    JMenuItemLocation location = locationOf(menuItem);
    activateParentIfIsAMenu(location);
    moveParentWindowToFront(location);
    if (menuItem instanceof JMenu && !location.inMenuBar()) waitForSubMenuToShow();
  }

  private static JMenuItemLocation locationOf(final JMenuItem menuItem) {
    return execute(new GuiQuery<JMenuItemLocation>() {
      protected JMenuItemLocation executeInEDT() {
        return new JMenuItemLocation(menuItem);
      }

    });
  }

  @RunsInEDT
  private void activateParentIfIsAMenu(JMenuItemLocation location) {
    if (!location.isParentAMenu()) return;
    click((JMenuItem)location.parentOrInvoker());
  }

  /**
   * Finds and selects the given <code>{@link JMenuItem}</code>.
   * @param menuItem the <code>JMenuItem</code> to select.
   * @throws IllegalStateException if the menu to select is disabled.
   * @throws IllegalStateException if the menu to select is not showing on the screen.
   * @throws ActionFailedException if the menu has a pop-up and it fails to show up.
   */
  @RunsInEDT
  public void click(JMenuItem menuItem) {
    show(menuItem);
    doClick(menuItem);
    ensurePopupIsShowing(menuItem);
  }

  @RunsInEDT
  private void ensurePopupIsShowing(JMenuItem menuItem) {
    if (!(menuItem instanceof JMenu)) return;
    JPopupMenu popup = popupMenuOf((JMenu)menuItem);
    // TODO review EDT access
    if (!waitForShowing(popup, robot.settings().timeoutToFindPopup()))
      throw actionFailure(concat("Clicking on menu item <", format(menuItem), "> never showed a pop-up menu"));
    waitForSubMenuToShow();
  }

  @RunsInEDT
  private void moveParentWindowToFront(JMenuItemLocation location) {
    if (!location.inMenuBar()) return;
    // TODO windowAncestorOf is not being called in EDT
    moveToFront(windowAncestorOf(location.parentOrInvoker()));
  }

  @RunsInEDT
  private void moveToFront(Window w) {
    if (w == null) return;
    // Make sure the window is in front, or its menus may be obscured by another window.
    toFront(w);
    robot.waitForIdle();
    robot.moveMouse(w);
  }

  private void waitForSubMenuToShow() {
    int delayBetweenEvents = robot.settings().delayBetweenEvents();
    if (SUBMENU_DELAY > delayBetweenEvents) pause(SUBMENU_DELAY - delayBetweenEvents);
  }

  @RunsInEDT
  private void doClick(JMenuItem menuItem) {
    if (isMacOSMenuBar()) {
      validateAndDoClick(menuItem);
      return;
    }
    super.click(menuItem);
    robot.waitForIdle();
  }

  private boolean isMacOSMenuBar() {
    return isOSX() && (getBoolean("apple.laf.useScreenMenuBar") || getBoolean("com.apple.macos.useScreenMenuBar"));
  }

  @RunsInEDT
  private static void validateAndDoClick(final JMenuItem menuItem) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        validateIsEnabledAndShowing(menuItem);
        menuItem.doClick();
      }
    });
  }
}
