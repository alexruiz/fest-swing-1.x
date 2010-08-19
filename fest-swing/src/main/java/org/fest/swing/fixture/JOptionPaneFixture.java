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
 * Copyright @2006-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.fixture.ComponentFixtureValidator.notNullRobot;

import java.util.regex.Pattern;

import javax.swing.JOptionPane;

import org.fest.swing.core.*;
import org.fest.swing.driver.JOptionPaneDriver;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JOptionPane}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Alex Ruiz
 */
public class JOptionPaneFixture extends ContainerFixture<JOptionPane> implements CommonComponentFixture {

  private JOptionPaneDriver driver;

  /**
   * Creates a new <code>{@link JOptionPaneFixture}</code>.
   * @param robot finds a showing {@code JOptionPane}, which will be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is {@code null}.
   * @throws ComponentLookupException if a showing {@code JOptionPane} could not be found.
   * @throws ComponentLookupException if more than one showing {@code JOptionPane} is found.
   */
  public JOptionPaneFixture(Robot robot) {
    this(robot, findShowingOptionPane(robot));
  }

  private static JOptionPane findShowingOptionPane(Robot robot) {
    notNullRobot(robot);
    return robot.finder().findByType(JOptionPane.class, true);
  }

  /**
   * Creates a new <code>{@link JOptionPaneFixture}</code>.
   * @param robot performs simulation of user events on the given {@code JOptionPane}.
   * @param target the {@code JOptionPane} to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is {@code null}.
   * @throws IllegalArgumentException if <code>target</code> is {@code null}.
   */
  public JOptionPaneFixture(Robot robot, JOptionPane target) {
    super(robot, target);
    driver(new JOptionPaneDriver(robot));
  }

  /**
   * Sets the <code>{@link JOptionPaneDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JOptionPaneDriver</code>.
   * @throws NullPointerException if the given driver is {@code null}.
   */
  protected final void driver(JOptionPaneDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Returns the title of this fixture's <code>{@link JOptionPane}</code>.
   * @return the title of this fixture's {@code JOptionPane}.
   * @since 1.2
   */
  public String title() {
    return driver.title(target);
  }

  /**
   * Returns a fixture wrapping the "OK" button in this fixture's <code>{@link JOptionPane}</code>. This method is
   * locale-independent and platform-independent.
   * @return a fixture wrapping the "OK" button.
   * @throws ComponentLookupException if the a "OK" button cannot be found.
   */
  public JButtonFixture okButton() {
    return new JButtonFixture(robot, driver.okButton(target));
  }

  /**
   * Returns a fixture wrapping the "Cancel" button in this fixture's <code>{@link JOptionPane}</code>. This method is
   * locale-independent and platform-independent.
   * @return a fixture wrapping the "Cancel" button.
   * @throws ComponentLookupException if the a "Cancel" button cannot be found.
   */
  public JButtonFixture cancelButton() {
    return new JButtonFixture(robot, driver.cancelButton(target));
  }

  /**
   * Returns a fixture wrapping the "Yes" button in this fixture's <code>{@link JOptionPane}</code>. This method is
   * locale-independent and platform-independent.
   * @return a fixture wrapping the "Yes" button.
   * @throws ComponentLookupException if the a "Yes" button cannot be found.
   */
  public JButtonFixture yesButton() {
    return new JButtonFixture(robot, driver.yesButton(target));
  }

  /**
   * Returns a fixture wrapping the "No" button in this fixture's <code>{@link JOptionPane}</code>. This method is
   * locale-independent and platform-independent.
   * @return a fixture wrapping the "No" button.
   * @throws ComponentLookupException if the a "No" button cannot be found.
   */
  public JButtonFixture noButton() {
    return new JButtonFixture(robot, driver.noButton(target));
  }

  /**
   * Finds and returns a fixture wrapping a button (this fixture's <code>{@link JOptionPane}</code>) matching the
   * given text.
   * @param text the text of the button to find. It can be a regular expression.
   * @return a fixture wrapping a button matching the given text.
   * @throws ComponentLookupException if the a button with the given text cannot be found.
   */
  public JButtonFixture buttonWithText(String text) {
    return new JButtonFixture(robot, driver.buttonWithText(target, text));
  }

  /**
   * Finds and returns a fixture wrapping a button (this fixture's <code>{@link JOptionPane}</code>) matching the
   * given text.
   * @param pattern the regular expression pattern to match.
   * @return a fixture wrapping a button matching the given regular expression pattern.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws ComponentLookupException if the a button with the given text cannot be found.
   * @since 1.2
   */
  public JButtonFixture buttonWithText(Pattern pattern) {
    return new JButtonFixture(robot, driver.buttonWithText(target, pattern));
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JOptionPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is not showing on the screen.
   */
  public JOptionPaneFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JOptionPane}</code>.
   * @param button the button to click.
   * @throws NullPointerException if the given <code>MouseButton</code> is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is not showing on the screen.
   * @return this fixture.
   */
  public JOptionPaneFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JOptionPane}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is not showing on the screen.
   */
  public JOptionPaneFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JOptionPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is not showing on the screen.
   */
  public JOptionPaneFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JOptionPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is not showing on the screen.
   */
  public JOptionPaneFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JOptionPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is disabled.
   * @throws IllegalStateException if this fixture's {@code JOptionPane} is not showing on the screen.
   */
  public JOptionPaneFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is displaying an error message.
   * @return this fixture.
   */
  public JOptionPaneFixture requireErrorMessage() {
    driver.requireErrorMessage(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is displaying an information
   * message.
   * @return this fixture.
   */
  public JOptionPaneFixture requireInformationMessage() {
    driver.requireInformationMessage(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is displaying a warning message.
   * @return this fixture.
   */
  public JOptionPaneFixture requireWarningMessage() {
    driver.requireWarningMessage(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is displaying a question.
   * @return this fixture.
   */
  public JOptionPaneFixture requireQuestionMessage() {
    driver.requireQuestionMessage(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is displaying a plain message.
   * @return this fixture.
   */
  public JOptionPaneFixture requirePlainMessage() {
    driver.requirePlainMessage(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JOptionPane}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is {@code null}.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @see KeyPressInfo
   */
  public JOptionPaneFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys this fixture's <code>{@link JOptionPane}</code>. This method
   * does not affect the current focus.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JOptionPaneFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JOptionPane}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JOptionPaneFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JOptionPane}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JOptionPaneFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that the title of this fixture's <code>{@link JOptionPane}</code> matches the given value.
   * @param title the title to match. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if this fixture's </code>JOptionPaneFixture</code> does not have the given title.
   */
  public JOptionPaneFixture requireTitle(String title) {
    driver.requireTitle(target, title);
    return this;
  }

  /**
   * Asserts that the title of this fixture's <code>{@link JOptionPane}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression is {@code null}.
   * @throws AssertionError if this fixture's </code>JOptionPaneFixture</code> does not have the given title.
   * @since 1.2
   */
  public JOptionPaneFixture requireTitle(Pattern pattern) {
    driver.requireTitle(target, pattern);
    return this;
  }

  /**
   * Asserts that the message of this fixture's <code>{@link JOptionPane}</code> matches the given value.
   * @param message the message to verify. If it is a {@code String}, it can be specified as a regular expression.
   * @return this fixture.
   * @throws AssertionError if the message in this fixture's </code>JOptionPaneFixture</code> is not equal to or does
   * not match the given message.
   */
  public JOptionPaneFixture requireMessage(Object message) {
    driver.requireMessage(target, message);
    return this;
  }

  /**
   * Asserts that the message of this fixture's <code>{@link JOptionPane}</code> matches the given regular expression
   * pattern. If the message in the {@code JOptionPane} is not a {@code String}, this method will use the
   * <code>toString</code> representation of such message.
   * @param pattern the regular expression to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws AssertionError if the message in this fixture's </code>JOptionPaneFixture</code> does not match the given
   * regular expression pattern.
   * @since 1.2
   */
  public JOptionPaneFixture requireMessage(Pattern pattern) {
    driver.requireMessage(target, pattern);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> has the given options.
   * @param options the options to verify.
   * @return this fixture.
   * @throws AssertionError if this fixture's </code>JOptionPaneFixture</code> does not have the given options.
   */
  public JOptionPaneFixture requireOptions(Object[] options) {
    driver.requireOptions(target, options);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JOptionPane} does not have input focus.
   */
  public JOptionPaneFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JOptionPane} is disabled.
   */
  public JOptionPaneFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws org.fest.swing.exception.WaitTimedOutError if this fixture's {@code JOptionPane} is never enabled.
   */
  public JOptionPaneFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JOptionPane} is enabled.
   */
  public JOptionPaneFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JOptionPane} is not visible.
   */
  public JOptionPaneFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JOptionPane}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JOptionPane} is visible.
   */
  public JOptionPaneFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }
}
