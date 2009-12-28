/*
 * Created on Dec 8, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Dimension;
import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.JInternalFrame;

import org.fest.swing.core.*;
import org.fest.swing.driver.JInternalFrameDriver;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JInternalFrame}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Alex Ruiz
 */
public class JInternalFrameFixture extends ContainerFixture<JInternalFrame> implements CommonComponentFixture,
    FrameLikeFixture, JComponentFixture, JPopupMenuInvokerFixture {

  private JInternalFrameDriver driver;

  /**
   * Creates a new <code>{@link JInternalFrameFixture}</code>.
   * @param robot performs simulation of user events on a <code>JInternalFrame</code>.
   * @param internalFrameName the name of the <code>JInternalFrame</code> to find using the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching <code>JInternalFrame</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JInternalFrame</code> is found.
   */
  public JInternalFrameFixture(Robot robot, String internalFrameName) {
    super(robot, internalFrameName, JInternalFrame.class);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JInternalFrameFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JInternalFrame</code>.
   * @param target the <code>JInternalFrame</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public JInternalFrameFixture(Robot robot, JInternalFrame target) {
    super(robot, target);
    createDriver();
  }

  private void createDriver() {
    driver(new JInternalFrameDriver(robot));
  }

  /**
   * Sets the <code>{@link JInternalFrameDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JInternalFrameDriver</code>.
   * @throws NullPointerException if the given driver is <code>null</code>.
   */
  protected final void driver(JInternalFrameDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Brings this fixture's <code>{@link JInternalFrame}</code> to the front.
   * @return this fixture.
   */
  public JInternalFrameFixture moveToFront() {
    driver.moveToFront(target);
    return this;
  }

  /**
   * Brings this fixture's <code>{@link JInternalFrame}</code> to the back.
   * @return this fixture.
   */
  public JInternalFrameFixture moveToBack() {
    driver.moveToBack(target);
    return this;
  }

  /**
   * Simulates a user deiconifying this fixture's <code>{@link JInternalFrame}</code>.
   * @return this fixture.
   * @throws ActionFailedException if the <code>JInternalFrame</code> vetoes the action.
   */
  public JInternalFrameFixture deiconify() {
    driver.deiconify(target);
    return this;
  }

  /**
   * Simulates a user iconifying this fixture's <code>{@link JInternalFrame}</code>.
   * @return this fixture.
   * @throws ActionFailedException if the given <code>JInternalFrame</code> is not iconifiable.
   * @throws ActionFailedException if the <code>JInternalFrame</code> vetoes the action.
   */
  public JInternalFrameFixture iconify() {
    driver.iconify(target);
    return this;
  }

  /**
   * Simulates a user maximizing this fixture's <code>{@link JInternalFrame}</code>, deconifying it first if it is
   * iconified.
   * @return this fixture.
   * @throws ActionFailedException if the given <code>JInternalFrame</code> is not maximizable.
   * @throws ActionFailedException if the <code>JInternalFrame</code> vetoes the action.
   */
  public JInternalFrameFixture maximize() {
    driver.maximize(target);
    return this;
  }

  /**
   * Simulates a user normalizing this fixture's <code>{@link JInternalFrame}</code>, deconifying it first if it is
   * iconified.
   * @return this fixture.
   * @throws ActionFailedException if the <code>JInternalFrame</code> vetoes the action.
   */
  public JInternalFrameFixture normalize() {
    driver.normalize(target);
    return this;
  }

  /**
   * Simulates a user closing this fixture's <code>{@link JInternalFrame}</code>.
   * @throws ActionFailedException if the <code>JInternalFrame</code> is not closable.
   */
  public void close() {
    driver.close(target);
  }

  /**
   * Asserts that the size of this fixture's <code>{@link JInternalFrame}</code> is equal to given one.
   * @param size the given size to match.
   * @return this fixture.
   * @throws AssertionError if the size of this fixture's <code>JInternalFrame</code> is not equal to the given size.
   */
  public JInternalFrameFixture requireSize(Dimension size) {
    driver.requireSize(target, size);
    return this;
  }

  /**
   * Simulates a user resizing horizontally this fixture's <code>{@link JInternalFrame}</code>.
   * @param width the width that this fixture's <code>JInternalFrame</code> should have after being resized.
   * @return this fixture.
   */
  public JInternalFrameFixture resizeWidthTo(int width) {
    driver.resizeWidthTo(target, width);
    return this;
  }

  /**
   * Simulates a user resizing vertically this fixture's <code>{@link JInternalFrame}</code>.
   * @param height the height that this fixture's <code>JInternalFrame</code> should have after being resized.
   * @return this fixture.
   */
  public JInternalFrameFixture resizeHeightTo(int height) {
    driver.resizeHeightTo(target, height);
    return this;
  }

  /**
   * Simulates a user resizing this fixture's <code>{@link JInternalFrame}</code>.
   * @param size the size that the target <code>JInternalFrame</code> should have after being resized.
   * @return this fixture.
   */
  public JInternalFrameFixture resizeTo(Dimension size) {
    driver.resizeTo(target, size);
    return this;
  }

  /**
   * Simulates a user moving this fixture's <code>{@link JInternalFrame}</code> to the given point.
   * @param p the point to move this fixture's <code>JInternalFrame</code> to.
   * @return this fixture.
   */
  public JInternalFrameFixture moveTo(Point p) {
    driver.moveTo(target, p);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JInternalFrame}</code>.
   * @return this fixture.
   */
  public JInternalFrameFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JInternalFrame}</code>.
   * @param button the button to click.
   * @return this fixture.
   */
  public JInternalFrameFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JInternalFrame}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   */
  public JInternalFrameFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JInternalFrame}</code>.
   * @return this fixture.
   */
  public JInternalFrameFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JInternalFrame}</code>.
   * @return this fixture.
   */
  public JInternalFrameFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JInternalFrame}</code>.
   * @return this fixture.
   */
  public JInternalFrameFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JInternalFrame}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @see KeyPressInfo
   */
  public JInternalFrameFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's <code>{@link JInternalFrame}</code> .
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is <code>null</code>.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JInternalFrameFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing given key on this fixture's <code>{@link JInternalFrame}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JInternalFrameFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JInternalFrame}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JInternalFrameFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JInternalFrame}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JInternalFrame</code> does not have input focus.
   */
  public JInternalFrameFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JInternalFrame}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if the managed <code>JInternalFrame</code> is disabled.
   */
  public JInternalFrameFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JInternalFrame}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if the managed <code>JInternalFrame</code> is never enabled.
   */
  public JInternalFrameFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JInternalFrame}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if the managed <code>JInternalFrame</code> is enabled.
   */
  public JInternalFrameFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JInternalFrame}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if the managed <code>JInternalFrame</code> is not visible.
   */
  public JInternalFrameFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JInternalFrame}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if the managed <code>JInternalFrame</code> is visible.
   */
  public JInternalFrameFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }


  /**
   * Asserts that the toolTip in this fixture's <code>{@link JInternalFrame}</code> matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's <code>JInternalFrame</code> does not match the given value.
   * @since 1.2
   */
  public JInternalFrameFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JInternalFrame}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the toolTip in this fixture's <code>JInternalFrame</code> does not match the given
   * regular expression.
   * @since 1.2
   */
  public JInternalFrameFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's <code>{@link JInternalFrame}</code>, under the given key.
   * @param key the key to use to retrieve the client property.
   * @return the value of the client property stored under the given key, or <code>null</code> if the property was
   * not found.
   * @throws NullPointerException if the given key is <code>null</code>.
   * @since 1.2
   */
  public Object clientProperty(Object key) {
    return driver.clientProperty(target, key);
  }

  /**
   * Shows a pop-up menu using this fixture's <code>{@link JInternalFrame}</code> as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JInternalFrame</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JInternalFrame</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's <code>{@link JInternalFrame}</code> as the invoker of
   * the pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JInternalFrame</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JInternalFrame</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }
}
