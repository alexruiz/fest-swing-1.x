/*
 * Created on Jan 26, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.core.MouseButton.RIGHT_BUTTON;
import static org.fest.swing.driver.ComponentEnabledCondition.untilIsEnabled;
import static org.fest.swing.driver.ComponentPerformDefaultAccessibleActionTask.performDefaultAccessibleAction;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.query.ComponentEnabledQuery.isEnabled;
import static org.fest.swing.query.ComponentHasFocusQuery.hasFocus;
import static org.fest.swing.query.ComponentSizeQuery.sizeOf;
import static org.fest.swing.query.ComponentVisibleQuery.isVisible;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.swing.util.TimeoutWatch.startWatchWithTimeoutOf;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.awt.*;

import javax.accessibility.AccessibleAction;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import org.fest.assertions.Description;
import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.*;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiLazyLoadingDescription;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.*;
import org.fest.swing.format.ComponentFormatter;
import org.fest.swing.format.Formatting;
import org.fest.swing.timing.Timeout;
import org.fest.swing.util.TimeoutWatch;

/**
 * Understands functional testing of <code>{@link Component}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Alex Ruiz
 */
public class ComponentDriver {

  private static final String ENABLED_PROPERTY = "enabled";
  private static final String SIZE_PROPERTY = "size";
  private static final String VISIBLE_PROPERTY = "visible";

  protected final Robot robot;

  private final ComponentDragAndDrop dragAndDrop;

  /**
   * Creates a new </code>{@link ComponentDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public ComponentDriver(Robot robot) {
    this.robot = robot;
    this.dragAndDrop = new ComponentDragAndDrop(robot);
  }

  /**
   * Simulates a user clicking once the given <code>{@link Component}</code> using the left mouse button.
   * @param c the <code>Component</code> to click on.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInEDT
  public void click(Component c) {
    assertIsEnabledAndShowing(c);
    robot.click(c);
  }

  /**
   * Simulates a user clicking once the given <code>{@link Component}</code> using the given mouse button.
   * @param c the <code>Component</code> to click on.
   * @param button the mouse button to use.
   * @throws NullPointerException if the given <code>MouseButton</code> is <code>null</code>.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInEDT
  public void click(Component c, MouseButton button) {
    click(c, button, 1);
  }

  /**
   * Simulates a user clicking the given mouse button, the given times on the given <code>{@link Component}</code>.
   * @param c the <code>Component</code> to click on.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInEDT
  public void click(Component c, MouseClickInfo mouseClickInfo) {
    if (mouseClickInfo == null) throw new NullPointerException("The given MouseClickInfo should not be null");
    click(c, mouseClickInfo.button(), mouseClickInfo.times());
  }

  /**
   * Simulates a user double-clicking the given <code>{@link Component}</code>.
   * @param c the <code>Component</code> to click on.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInEDT
  public void doubleClick(Component c) {
    click(c, LEFT_BUTTON, 2);
  }

  /**
   * Simulates a user right-clicking the given <code>{@link Component}</code>.
   * @param c the <code>Component</code> to click on.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInEDT
  public void rightClick(Component c) {
    click(c, RIGHT_BUTTON);
  }

  /**
   * Simulates a user clicking the given mouse button, the given times on the given <code>{@link Component}</code>.
   * @param c the <code>Component</code> to click on.
   * @param button the mouse button to click.
   * @param times the number of times to click the given mouse button.
   * @throws NullPointerException if the given <code>MouseButton</code> is <code>null</code>.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInEDT
  public void click(Component c, MouseButton button, int times) {
    if (button == null) throw new NullPointerException("The given MouseButton should not be null");
    assertIsEnabledAndShowing(c);
    robot.click(c, button, times);
  }

  /**
   * Simulates a user clicking at the given position on the given <code>{@link Component}</code>.
   * @param c the <code>Component</code> to click on.
   * @param where the position where to click.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInEDT
  public void click(Component c, Point where) {
    assertIsEnabledAndShowing(c);
    robot.click(c, where);
  }

  protected Settings settings() {
    return robot.settings();
  }

  /**
   * Asserts that the size of the <code>{@link Component}</code> is equal to given one.
   * @param c the target component.
   * @param size the given size to match.
   * @throws AssertionError if the size of the <code>Window</code> is not equal to the given size.
   */
  @RunsInEDT
  public void requireSize(Component c, Dimension size) {
    assertThat(sizeOf(c)).as(propertyName(c, SIZE_PROPERTY)).isEqualTo(size);
  }

  /**
   * Asserts that the <code>{@link Component}</code> is visible.
   * @param c the target component.
   * @throws AssertionError if the <code>Component</code> is not visible.
   */
  @RunsInEDT
  public void requireVisible(Component c) {
    assertThat(isVisible(c)).as(visibleProperty(c)).isTrue();
  }

  /**
   * Asserts that the <code>{@link Component}</code> is not visible.
   * @param c the target component.
   * @throws AssertionError if the <code>Component</code> is visible.
   */
  @RunsInEDT
  public void requireNotVisible(Component c) {
    assertThat(isVisible(c)).as(visibleProperty(c)).isFalse();
  }

  @RunsInEDT
  private static Description visibleProperty(Component c) {
    return propertyName(c, VISIBLE_PROPERTY);
  }

  /**
   * Asserts that the <code>{@link Component}</code> has input focus.
   * @param c the target component.
   * @throws AssertionError if the <code>Component</code> does not have input focus.
   */
  @RunsInEDT
  public void requireFocused(Component c) {
    assertThat(hasFocus(c)).as(requiredFocusedErrorMessage(c)).isTrue();
  }

  private static Description requiredFocusedErrorMessage(final Component c) {
    return new GuiLazyLoadingDescription() {
      protected String loadDescription() {
        return concat("Expected component ", format(c), " to have input focus");
      }
    };
  }

  /**
   * Asserts that the <code>{@link Component}</code> is enabled.
   * @param c the target component.
   * @throws AssertionError if the <code>Component</code> is disabled.
   */
  @RunsInEDT
  public void requireEnabled(Component c) {
    assertThat(isEnabled(c)).as(enabledProperty(c)).isTrue();
  }

  /**
   * Asserts that the <code>{@link Component}</code> is enabled.
   * @param c the target component.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @throws WaitTimedOutError if the <code>Component</code> is never enabled.
   */
  @RunsInEDT
  public void requireEnabled(Component c, Timeout timeout) {
    pause(untilIsEnabled(c), timeout);
  }

  /**
   * Asserts that the <code>{@link Component}</code> is disabled.
   * @param c the target component.
   * @throws AssertionError if the <code>Component</code> is enabled.
   */
  @RunsInEDT
  public void requireDisabled(Component c) {
    assertThat(isEnabled(c)).as(enabledProperty(c)).isFalse();
  }

  @RunsInEDT
  private static Description enabledProperty(Component c) {
    return propertyName(c, ENABLED_PROPERTY);
  }

  /**
   * Simulates a user pressing and releasing the given keys on the <code>{@link Component}</code>.
   * @param c the target component.
   * @param keyCodes one or more codes of the keys to press.
   * @throws NullPointerException if the given array of codes is <code>null</code>.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  @RunsInEDT
  public void pressAndReleaseKeys(Component c, int... keyCodes) {
    if (keyCodes == null) throw new NullPointerException("The array of key codes should not be null");
    assertIsEnabledAndShowing(c);
    focusAndWaitForFocusGain(c);
    robot.pressAndReleaseKeys(keyCodes);
  }

  /**
   * Simulates a user pressing and releasing the given key on the <code>{@link Component}</code>. Modifiers is a
   * mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param c the target component.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   * @see java.awt.event.InputEvent
   */
  @RunsInEDT
  public void pressAndReleaseKey(Component c, KeyPressInfo keyPressInfo) {
    if (keyPressInfo == null) throw new NullPointerException("The given KeyPressInfo should not be null");
    pressAndReleaseKey(c, keyPressInfo.keyCode(), keyPressInfo.modifiers());
  }

  /**
   * Simulates a user pressing and releasing the given key on the <code>{@link Component}</code>. Modifiers is a
   * mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param c the target component.
   * @param keyCode the code of the key to press.
   * @param modifiers the given modifiers.
   * @throws IllegalArgumentException if the given code is not a valid key code. *
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   * @see java.awt.event.InputEvent
   */
  @RunsInEDT
  public void pressAndReleaseKey(Component c, int keyCode, int[] modifiers) {
    focusAndWaitForFocusGain(c);
    robot.pressAndReleaseKey(keyCode, modifiers);
  }

  /**
   * Simulates a user pressing given key on the <code>{@link Component}</code>.
   * @param c the target component.
   * @param keyCode the code of the key to press.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  @RunsInEDT
  public void pressKey(Component c, int keyCode) {
    focusAndWaitForFocusGain(c);
    robot.pressKey(keyCode);
  }

  /**
   * Simulates a user releasing the given key on the <code>{@link Component}</code>.
   * @param c the target component.
   * @param keyCode the code of the key to release.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  @RunsInEDT
  public void releaseKey(Component c, int keyCode) {
    focusAndWaitForFocusGain(c);
    robot.releaseKey(keyCode);
  }

  /**
   * Gives input focus to the given <code>{@link Component}</code> and waits until the <code>{@link Component}</code>
   * has focus.
   * @param c the component to give focus to.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInEDT
  public void focusAndWaitForFocusGain(Component c) {
    assertIsEnabledAndShowing(c);
    robot.focusAndWaitForFocusGain(c);
  }

  /**
   * Gives input focus to the given <code>{@link Component}</code>. Note that the component may not yet have focus when
   * this method returns.
   * @param c the component to give focus to.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInEDT
  public void focus(Component c) {
    assertIsEnabledAndShowing(c);
    robot.focus(c);
  }

  /**
   * Performs a drag action at the given point.
   * @param c the target component.
   * @param where the point where to start the drag action.
   */
  @RunsInEDT
  protected final void drag(Component c, Point where) {
    dragAndDrop.drag(c, where);
  }

  /**
   * Ends a drag operation, releasing the mouse button over the given target location.
   * <p>
   * This method is tuned for native drag/drop operations, so if you get odd behavior, you might try using a simple
   * <code>{@link Robot#moveMouse(Component, int, int)}</code> and <code>{@link Robot#releaseMouseButtons()}</code>.
   * @param c the target component.
   * @param where the point where the drag operation ends.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  @RunsInEDT
  protected final void drop(Component c, Point where) {
    dragAndDrop.drop(c, where);
  }

  /**
   * Move the mouse appropriately to get from the source to the destination. Enter/exit events will be generated where
   * appropriate.
   * @param c the target component.
   * @param where the point to drag over.
   */
  protected final void dragOver(Component c, Point where) {
    dragAndDrop.dragOver(c, where);
  }

  /**
   * Performs the <code>{@link AccessibleAction}</code> in the given <code>{@link Component}</code>'s event queue.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for
   * invoking this method in the EDT.
   * </p>
   * @param c the given <code>Component</code>.
   * @throws ActionFailedException if <code>action</code> is <code>null</code> or empty.
   */
  @RunsInCurrentThread
  protected final void performAccessibleActionOf(Component c) {
    performDefaultAccessibleAction(c);
    robot.waitForIdle();
  }

  /**
   * Wait the given number of milliseconds for the <code>{@link Component}</code> to be showing and ready. Returns
   * <code>false</code> if the operation times out.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the event dispatch thread (EDT.) Clients are responsible for
   * invoking this method in the EDT.
   * </p>
   * @param c the given <code>Component</code>.
   * @param timeout the time in milliseconds to wait for the <code>Component</code> to be showing and ready.
   * @return <code>true</code> if the <code>Component</code> is showing and ready, <code>false</code> otherwise.
   */
  @RunsInCurrentThread
  protected final boolean waitForShowing(Component c, long timeout) {
    // TODO test
    if (robot.isReadyForInput(c)) return true;
    TimeoutWatch watch = startWatchWithTimeoutOf(timeout);
    while (!robot.isReadyForInput(c)) {
      if (c instanceof JPopupMenu) {
        // move the mouse over the parent menu item to ensure the sub-menu shows
        Component invoker = ((JPopupMenu)c).getInvoker();
        if (invoker instanceof JMenu) robot.jitter(invoker);
      }
      if (watch.isTimeOut()) return false;
      pause();
    }
    return true;
  }

  /**
   * Shows a pop-up menu using the given <code>{@link Component}</code> as the invoker of the pop-up menu.
   * @param c the invoker of the <code>JPopupMenu</code>.
   * @return the displayed pop-up menu.
   * @throws IllegalStateException if the given <code>Component</code> is disabled.
   * @throws IllegalStateException if the given <code>Component</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  @RunsInEDT
  public JPopupMenu invokePopupMenu(Component c) {
    assertIsEnabledAndShowing(c);
    return robot.showPopupMenu(c);
  }

  /**
   * Shows a pop-up menu at the given point using the given <code>{@link Component}</code> as the invoker of the pop-up
   * menu.
   * @param c the invoker of the <code>JPopupMenu</code>.
   * @param p the given point where to show the pop-up menu.
   * @return the displayed pop-up menu.
   * @throws NullPointerException if the given point is <code>null</code>.
   * @throws IllegalStateException if the given <code>Component</code> is disabled.
   * @throws IllegalStateException if the given <code>Component</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  @RunsInEDT
  public JPopupMenu invokePopupMenu(Component c, Point p) {
    if (p == null) throw new NullPointerException("The given point should not be null");
    assertIsEnabledAndShowing(c);
    return robot.showPopupMenu(c, p);
  }

  /**
   * Validates that the given <code>{@link Component}</code> is enabled and showing on the screen. This method is
   * executed in the event dispatch thread.
   * @param c the <code>Component</code> to check.
   * @throws IllegalStateException if the <code>Component</code> is disabled.
   * @throws IllegalStateException if the <code>Component</code> is not showing on the screen.
   */
  @RunsInEDT
  protected static void assertIsEnabledAndShowing(final Component c) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        validateIsEnabledAndShowing(c);
      }
    });
  }

  /**
   * Formats the name of a property of the given <code>{@link Component}</code> by concatenating the value obtained
   * from <code>{@link Formatting#format(Component)}</code> with the given property name.
   * @param c the given <code>Component</code>.
   * @param propertyName the name of the property.
   * @return the description of a property belonging to a <code>Component</code>.
   * @see ComponentFormatter
   * @see Formatting#format(Component)
   */
  @RunsInEDT
  public static Description propertyName(final Component c, final String propertyName) {
    return new GuiLazyLoadingDescription() {
      protected String loadDescription() {
        return concat(format(c), " - property:", quote(propertyName));
      }
    };
  }

  /**
   * Simulates a user moving the mouse pointer to the given coordinates relative to the given
   * <code>{@link Component}</code>. This method will <b>not</b> throw any exceptions if the it was not possible to
   * move the mouse pointer.
   * @param c the given <code>Component</code>.
   * @param p coordinates relative to the given <code>Component</code>.
   */
  @RunsInEDT
  protected final void moveMouseIgnoringAnyError(Component c, Point p) {
    moveMouseIgnoringAnyError(c, p.x, p.y);
  }

  /**
   * Simulates a user moving the mouse pointer to the given coordinates relative to the given
   * <code>{@link Component}</code>. This method will <b>not</b> throw any exceptions if the it was not possible to
   * move the mouse pointer.
   * @param c the given <code>Component</code>.
   * @param x horizontal coordinate relative to the given <code>Component</code>.
   * @param y vertical coordinate relative to the given <code>Component</code>.
   */
  @RunsInEDT
  protected final void moveMouseIgnoringAnyError(Component c, int x, int y) {
    try {
      robot.moveMouse(c, x, y);
    } catch (RuntimeException ignored) {}
  }
}
