/*
 * Created on Oct 20, 2006
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
 * Copyright @2006-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.*;

import org.fest.swing.core.*;
import org.fest.swing.core.Robot;
import org.fest.swing.driver.DialogDriver;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of {@code Dialog}s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Alex Ruiz
 */
public class DialogFixture extends WindowFixture<Dialog> {
  private DialogDriver driver;

  /**
   * Creates a new {@link DialogFixture}. This constructor creates a new {@link Robot}
   * containing the current AWT hierarchy.
   * @param target the {@code Dialog} to be managed by this fixture.
   * @throws NullPointerException if {@code target} is {@code null}.
   * @see BasicRobot#robotWithCurrentAwtHierarchy()
   */
  public DialogFixture(Dialog target) {
    super(target);
    createDriver();
  }

  /**
   * Creates a new {@link DialogFixture}.
   * @param robot performs simulation of user events on the given {@code Dialog}.
   * @param target the {@code Dialog} to be managed by this fixture.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws NullPointerException if {@code target} is {@code null}.
   */
  public DialogFixture(Robot robot, Dialog target) {
    super(robot, target);
    createDriver();
  }

  /**
   * Creates a new {@link DialogFixture}.
   * @param robot performs simulation of user events on a {@code Dialog}.
   * @param dialogName the name of the {@code Dialog} to find using the given {@code Robot}.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws ComponentLookupException if a {@code Dialog} having a matching name could not be found.
   * @throws ComponentLookupException if more than one {@code Dialog} having a matching name is found.
   */
  public DialogFixture(Robot robot, String dialogName) {
    super(robot, dialogName, Dialog.class);
    createDriver();
  }

  /**
   * Creates a new {@link DialogFixture}. This constructor creates a new {@link Robot}
   * containing the current AWT hierarchy.
   * @param dialogName the name of the {@code Dialog} to find.
   * @throws ComponentLookupException if a {@code Dialog} having a matching name could not be found.
   * @throws ComponentLookupException if more than one {@code Dialog} having a matching name is found.
   * @see BasicRobot#robotWithCurrentAwtHierarchy()
   */
  public DialogFixture(String dialogName) {
    super(dialogName, Dialog.class);
    createDriver();
  }

  private void createDriver() {
    driver(new DialogDriver(robot));
  }

  /**
   * Sets the {@link DialogDriver} to be used by this fixture.
   * @param newDriver the new {@code DialogDriver}.
   * @throws NullPointerException if the given driver is {@code null}.
   */
  protected final void driver(DialogDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Simulates a user clicking this fixture's {@code Dialog}.
   * @return this fixture.
   */
  public DialogFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code Dialog}.
   * @param button the button to click.
   * @return this fixture.
   */
  public DialogFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code Dialog}.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given {@code MouseClickInfo} is {@code null}.
   */
  public DialogFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's {@code Dialog}.
   * @return this fixture.
   */
  public DialogFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's {@code Dialog}.
   * @return this fixture.
   */
  public DialogFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user moving this fixture's {@code Dialog} to the given point.
   * @param p the point to move this fixture's {@code Dialog} to.
   * @return this fixture.
   * @throws ActionFailedException if the {@code Window} is not movable.
   * @throws ActionFailedException if the given {@code Window} is not showing on the screen.
   */
  public DialogFixture moveTo(Point p) {
    driver.moveTo(target, p);
    return this;
  }

  /**
   * If fixture's {@code Dialog} is visible, brings it to the front and may make it the focused one.
   * @return this fixture.
   */
  public DialogFixture moveToFront() {
    driver.moveToFront(target);
    return this;
  }


  /**
   * If the given {@code Dialog} is visible, sends it to the back and may cause it to lose focus or
   * activation if it is the focused or active.
   * @return this fixture.
   */
  public DialogFixture moveToBack() {
    driver.moveToBack(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's {@code Dialog}.
   * Modifiers is a mask from the available {@link java.awt.event.InputEvent} masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given {@code KeyPressInfo} is {@code null}.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @see KeyPressInfo
   */
  public DialogFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's {@code Dialog}.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public DialogFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's {@code Dialog}.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public DialogFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's {@code Dialog}.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public DialogFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's {@code Dialog} has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code Dialog} does not have input focus.
   */
  public DialogFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code Dialog} is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code Dialog} is enabled.
   */
  public DialogFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code Dialog} is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code Dialog} is disabled.
   */
  public DialogFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code Dialog} is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws org.fest.swing.exception.WaitTimedOutError if this fixture's {@code Dialog} is never enabled.
   */
  public DialogFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's {@code Dialog} is modal.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code Dialog} is not modal.
   */
  public DialogFixture requireModal() {
    driver.requireModal(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code Dialog} is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code Dialog} is visible.
   */
  public DialogFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Asserts that the size of this fixture's {@code Dialog} is equal to given one.
   * @param size the given size to match.
   * @return this fixture.
   * @throws AssertionError if the size of this fixture's {@code Dialog} is not equal to the given size.
   */
  public DialogFixture requireSize(Dimension size) {
    driver.requireSize(target, size);
    return this;
  }

  /**
   * Asserts that this fixture's {@code Dialog} is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code Dialog} is not visible.
   */
  public DialogFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Simulates a user resizing vertically this fixture's {@code Dialog}.
   * @param height the height that this fixture's {@code Dialog} should have after being resized.
   * @return this fixture.
   * @throws ActionFailedException if the {@code Window} is not resizable.
   */
  public DialogFixture resizeHeightTo(int height) {
    driver.resizeHeightTo(target, height);
    return this;
  }

  /**
   * Simulates a user resizing this fixture's {@code Dialog}.
   * @param size the size that the target window should have after being resized.
   * @return this fixture.
   * @throws ActionFailedException if the {@code Window} is not resizable.
   */
  public DialogFixture resizeTo(Dimension size) {
    driver.resizeTo(target, size);
    return this;
  }

  /**
   * Simulates a user resizing horizontally this fixture's {@code Dialog}.
   * @param width the width that this fixture's {@code Dialog} should have after being resized.
   * @return this fixture.
   * @throws ActionFailedException if the {@code Window} is not resizable.
   */
  public DialogFixture resizeWidthTo(int width) {
    driver.resizeWidthTo(target, width);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's {@code Dialog}.
   * @return this fixture.
   */
  public DialogFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Shows this fixture's {@code Dialog}.
   * @return this fixture.
   */
  @Override public DialogFixture show() {
    driver.show(target);
    return this;
  }

  /**
   * Shows this fixture's {@code Dialog}, resized to the given size.
   * @param size the size to resize this fixture's {@code Dialog} to.
   * @return this fixture.
   */
  @Override public DialogFixture show(Dimension size) {
    driver.show(target, size);
    return this;
  }

  /**
   * Shows a pop-up menu using this fixture's {@code Dialog} as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code Dialog} is disabled.
   * @throws IllegalStateException if this fixture's {@code Dialog} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's {@code Dialog} as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code Dialog} is disabled.
   * @throws IllegalStateException if this fixture's {@code Dialog} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }

  /**
   * Simulates a user closing this fixture's {@code Dialog}.
   */
  public void close() {
    driver.close(target);
  }
}
