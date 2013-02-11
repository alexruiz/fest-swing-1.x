/*
 * Created on Jul 1, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.JSpinner;
import javax.swing.text.JTextComponent;

import org.fest.swing.core.*;
import org.fest.swing.driver.JSpinnerDriver;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of {@code JSpinner}s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JSpinnerFixture extends AbstractComponentFixture<JSpinner> implements CommonComponentFixture,
    AbstractJComponentFixture, JPopupMenuInvokerFixture {
  private JSpinnerDriver driver;

  /**
   * Creates a new {@link JSpinnerFixture}.
   * @param robot performs simulation of user events on a {@code JSpinner}.
   * @param spinnerName the name of the {@code JSpinner} to find using the given {@code Robot}.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws ComponentLookupException if a matching {@code JSpinner} could not be found.
   * @throws ComponentLookupException if more than one matching {@code JSpinner} is found.
   */
  public JSpinnerFixture(Robot robot, String spinnerName) {
    super(robot, spinnerName, JSpinner.class);
    createDriver();
  }

  /**
   * Creates a new {@link JSpinnerFixture}.
   * @param robot performs simulation of user events on the given {@code JSpinner}.
   * @param target the {@code JSpinner} to be managed by this fixture.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws NullPointerException if {@code target} is {@code null}.
   */
  public JSpinnerFixture(Robot robot, JSpinner target) {
    super(robot, target);
    createDriver();
  }

  private void createDriver() {
    driver(new JSpinnerDriver(robot));
  }

  /**
   * Sets the {@link JSpinnerDriver} to be used by this fixture.
   * @param newDriver the new {@code JSpinnerDriver}.
   * @throws NullPointerException if the given driver is {@code null}.
   */
  protected final void driver(JSpinnerDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Simulates a user incrementing the value of this fixture's {@code JSpinner} the given number of times.
   * @param times how many times the value of this fixture's {@code JSpinner} should be incremented.
   * @return this fixture.
   * @throws IllegalArgumentException if {@code times} is less than or equal to zero.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   */
  public JSpinnerFixture increment(int times) {
    driver.increment(target, times);
    return this;
  }

  /**
   * Simulates a user incrementing the value of this fixture's {@code JSpinner} one time.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   */
  public JSpinnerFixture increment() {
    driver.increment(target);
    return this;
  }

  /**
   * Simulates a user decrementing the value of this fixture's {@code JSpinner} the given number of times.
   * @param times how many times the value of this fixture's {@code JSpinner} should be decremented.
   * @return this fixture.
   * @throws IllegalArgumentException if {@code times} is less than or equal to zero.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   */
  public JSpinnerFixture decrement(int times) {
    driver.decrement(target, times);
    return this;
  }

  /**
   * Simulates a user decrementing the value of this fixture's {@code JSpinner} one time.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   */
  public JSpinnerFixture decrement() {
    driver.decrement(target);
    return this;
  }

  /**
   * Simulates a user entering the given text in this fixture's {@code JSpinner} (assuming its editor has a
   * {@code JTextComponent} under it.) This method does not commit the value to the {@code JSpinner}.
   * @param text the text to enter.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   * @throws ActionFailedException if the editor of the {@code JSpinner} is not a {@code JTextComponent} or
   * cannot be found.
   * @throws UnexpectedException if the entering the text in the {@code JSpinner}'s editor fails.
   */
  public JSpinnerFixture enterText(String text) {
    driver.enterText(target, text);
    return this;
  }

  /**
   * Simulates a user entering and committing the given text in this fixture's {@code JSpinner} (assuming
   * its editor has a {@code JTextComponent} under it.)
   * @param text the text to enter.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   * @throws ActionFailedException if the editor of the {@code JSpinner} is not a {@code JTextComponent} or
   * cannot be found.
   * @throws UnexpectedException if the entering the text in the {@code JSpinner}'s editor fails.
   */
  public JSpinnerFixture enterTextAndCommit(String text) {
    driver.enterTextAndCommit(target, text);
    return this;
  }

  /**
   * Selects the given value in this fixture's {@code JSpinner}.
   * @param value the value to select.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   * @throws IllegalArgumentException if the {@code JSpinner} does not support the specified {@code value}.
   */
  public Object select(Object value) {
    driver.selectValue(target, value);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code JSpinner}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   */
  public JSpinnerFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code JSpinner}.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given {@code MouseButton} is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   */
  public JSpinnerFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code JSpinner}.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given {@code MouseClickInfo} is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   */
  public JSpinnerFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's {@code JSpinner}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   */
  public JSpinnerFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's {@code JSpinner}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   */
  public JSpinnerFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's {@code JSpinner}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   */
  public JSpinnerFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's {@code JSpinner}.
   * Modifiers is a mask from the available {@link java.awt.event.InputEvent} masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given {@code KeyPressInfo} is {@code null}.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   * @see KeyPressInfo
   */
  public JSpinnerFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's {@code JSpinner}. This method
   * does not affect the current focus.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JSpinnerFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's {@code JSpinner}.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JSpinnerFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's {@code JSpinner}.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JSpinnerFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JSpinner} has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JSpinner} does not have input focus.
   */
  public JSpinnerFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JSpinner} is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JSpinner} is disabled.
   */
  public JSpinnerFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JSpinner} is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws org.fest.swing.exception.WaitTimedOutError if this fixture's {@code JSpinner} is never enabled.
   */
  public JSpinnerFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JSpinner} is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JSpinner} is enabled.
   */
  public JSpinnerFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JSpinner} is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JSpinner} is not visible.
   */
  public JSpinnerFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JSpinner} is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JSpinner} is visible.
   */
  public JSpinnerFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Verifies that the value of this fixture's {@code JSpinner} is equal to the given one.
   * @param value the expected value of this fixture's {@code JSpinner}.
   * @return this fixture.
   * @throws AssertionError if the value of this fixture's {@code JSpinner} is not equal to the given one.
   */
  public JSpinnerFixture requireValue(Object value) {
    driver.requireValue(target, value);
    return this;
  }

  /**
   * Returns the text displayed by this fixture's {@code JSpinner}. This method first tries to get the text
   * displayed in the {@code JSpinner}'s editor, assuming it is a {@code JTextComponent}. If the
   * text from the editor cannot be retrieved, it will return the {@code String} representation of the value
   * in the {@code JSpinner}'s model.
   * @return the text displayed by this fixture's {@code JSpinner}.
   * @since 1.2
   */
  public String text() {
    return driver.textOf(target);
  }


  /**
   * Asserts that the toolTip in this fixture's {@code JSpinner} matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's {@code JSpinner} does not match the given value.
   * @since 1.2
   */
  public JSpinnerFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's {@code JSpinner} matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws AssertionError if the toolTip in this fixture's {@code JSpinner} does not match the given regular
   * expression.
   * @since 1.2
   */
  public JSpinnerFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's {@code JSpinner}, under the given key.
   * @param key the key to use to retrieve the client property.
   * @return the value of the client property stored under the given key, or {@code null} if the property was
   * not found.
   * @throws NullPointerException if the given key is {@code null}.
   * @since 1.2
   */
  public Object clientProperty(Object key) {
    return driver.clientProperty(target, key);
  }

  /**
   * Shows a pop-up menu using this fixture's {@code JSpinner} as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's {@code JSpinner} as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is disabled.
   * @throws IllegalStateException if this fixture's {@code JSpinner} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }
}
