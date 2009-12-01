/*
 * Created on Dec 16, 2006
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
 * Copyright @2006-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.JButton;

import org.fest.swing.core.*;
import org.fest.swing.driver.AbstractButtonDriver;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JButton}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JButtonFixture extends ComponentFixture<JButton> implements CommonComponentFixture,
  JComponentFixture, JPopupMenuInvokerFixture, TextDisplayFixture {

  private AbstractButtonDriver driver;

  /**
   * Creates a new <code>{@link JButtonFixture}</code>.
   * @param target the <code>JButton</code> to be managed by this fixture.
   * @param robot performs simulation of user events on the given <code>JButton</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public JButtonFixture(Robot robot, JButton target) {
    super(robot, target);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JButtonFixture}</code>.
   * @param robot performs simulation of user events on a <code>JButton</code>.
   * @param buttonName the name of the <code>JButton</code> to find using the given <code>RobotFixture</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching <code>JButton</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JButton</code> is found.
   */
  public JButtonFixture(Robot robot, String buttonName) {
    super(robot, buttonName, JButton.class);
    createDriver();
  }

  private void createDriver() {
    driver(new AbstractButtonDriver(robot));
  }

  /**
   * Sets the <code>{@link AbstractButtonDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>AbstractButtonDriver</code>.
   * @throws NullPointerException if the given driver is <code>null</code>.
   */
  protected final void driver(AbstractButtonDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Returns the text of this fixture's <code>{@link JButton}</code>.
   * @return the text of this fixture's <code>JButton</code>.
   */
  public String text() {
    return driver.textOf(target);
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JButton}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   */
  public JButtonFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JButton}</code>.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseButton</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   */
  public JButtonFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JButton}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   */
  public JButtonFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JButton}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   */
  public JButtonFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JButton}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   */
  public JButtonFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JButton}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   */
  public JButtonFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JButton}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   * @see KeyPressInfo
   */
  public JButtonFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's <code>{@link JButton}</code>.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is <code>null</code>.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JButtonFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JButton}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JButtonFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JButton}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JButtonFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JButton}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JButton</code> does not have input focus.
   */
  public FocusableComponentFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JButton}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JButton</code> is disabled.
   */
  public JButtonFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JButton}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's <code>JButton</code> is never enabled.
   */
  public JButtonFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JButton}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JButton</code> is enabled.
   */
  public JButtonFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JButton}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JButton</code> is not visible.
   */
  public JButtonFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JButton}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JButton</code> is visible.
   */
  public JButtonFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Asserts that the text of this fixture's <code>{@link JButton}</code> matches the specified value.
   * @param expected the text to match. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the text of the target <code>JButton</code> does not match the given one.
   */
  public JButtonFixture requireText(String expected) {
    driver.requireText(target, expected);
    return this;
  }

  /**
   * Asserts that the text of this fixture's <code>{@link JButton}</code> matches the given regular expression pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the text of the target <code>JButton</code> does not match the given regular expression
   * pattern.
   * @since 1.2
   */
  public JButtonFixture requireText(Pattern pattern) {
    driver.requireText(target, pattern);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JButton}</code> matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's <code>JButton</code> does not match the given value.
   * @since 1.2
   */
  public JButtonFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JButton}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the toolTip in this fixture's <code>JButton</code> does not match the given regular
   * expression.
   * @since 1.2
   */
  public JButtonFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's <code>{@link JButton}</code>, under the given key.
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
   * Shows a pop-up menu using this fixture's <code>{@link JButton}</code> as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's <code>{@link JButton}</code> as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JButton</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }
}

