/*
 * Created on Dec 19, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import java.util.regex.Pattern;

import javax.swing.JProgressBar;

import org.fest.swing.core.*;
import org.fest.swing.driver.JProgressBarDriver;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JProgressBar}</code>s:
 * <ul>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public class JProgressBarFixture extends ComponentFixture<JProgressBar> implements StateVerificationFixture,
    JComponentFixture, TextDisplayFixture {

  private JProgressBarDriver driver;

  /**
   * Creates a new <code>{@link JProgressBarFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JProgressBar</code>.
   * @param target the <code>JProgressBar</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public JProgressBarFixture(Robot robot, JProgressBar target) {
    super(robot, target);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JProgressBarFixture}</code>.
   * @param robot performs simulation of user events on a <code>JProgressBar</code>.
   * @param labelName the name of the <code>JProgressBar</code> to find using the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching <code>JProgressBar</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JProgressBar</code> is found.
   */
  public JProgressBarFixture(Robot robot, String labelName) {
    super(robot, labelName, JProgressBar.class);
    createDriver();
  }

  private void createDriver() {
    driver(new JProgressBarDriver(robot));
  }

  /**
   * Sets the <code>{@link JProgressBarDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JProgressBarDriver</code>.
   * @throws NullPointerException if the given driver is <code>null</code>.
   */
  protected final void driver(JProgressBarDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Asserts that the value of this fixture's <code>{@link JProgressBar}</code> is equal to the given one.
   * @param value the expected value.
   * @return this fixture.
   * @throws AssertionError if the value of this fixture's <code>JProgressBar</code> is not equal to the given one.
   */
  public JProgressBarFixture requireValue(int value) {
    driver.requireValue(target, value);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JProgressBar}</code> is in determinate mode.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JProgressBar</code> is not in determinate mode.
   */
  public JProgressBarFixture requireDeterminate() {
    driver.requireDeterminate(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JProgressBar}</code> is in indeterminate mode.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JProgressBar</code> is not in indeterminate mode.
   */
  public JProgressBarFixture requireIndeterminate() {
    driver.requireIndeterminate(target);
    return this;
  }

  /**
   * Returns the text of this fixture's <code>{@link JProgressBar}</code>.
   * @return the text of this fixture's <code>JProgressBar</code>.
   */
  public String text() {
    return driver.textOf(target);
  }

  /**
   * Asserts that the text of this fixture's <code>{@link JProgressBar}</code> is equal to the specified
   * <code>String</code>.
   * @param expected the text to match.
   * @return this fixture.
   * @throws AssertionError if the text of this fixture's <code>JProgressBar</code> is not equal to the given one.
   */
  public JProgressBarFixture requireText(String expected) {
    driver.requireText(target, expected);
    return this;
  }

  /**
   * Asserts that the text of this fixture's <code>{@link JProgressBar}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws AssertionError if the text of this fixture's <code>JProgressBar</code> does not match the given regular
   * expression pattern.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   */
  public JProgressBarFixture requireText(Pattern pattern) {
    driver.requireText(target, pattern);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JProgressBar}</code> matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's <code>JProgressBar</code> does not match the given value.
   */
  public JProgressBarFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JProgressBar}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the toolTip in this fixture's <code>JProgressBar</code> does not match the given regular
   * expression pattern.
   */
  public JProgressBarFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JProgressBar}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JProgressBar</code> is disabled.
   */
  public JProgressBarFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JProgressBar}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws org.fest.swing.exception.WaitTimedOutError if this fixture's <code>JProgressBar</code> is never enabled.
   */
  public JProgressBarFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JProgressBar}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JProgressBar</code> is enabled.
   */
  public JProgressBarFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JProgressBar}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JProgressBar</code> is not visible.
   */
  public JProgressBarFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JProgressBar}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JProgressBar</code> is visible.
   */
  public JProgressBarFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's <code>{@link JProgressBar}</code>, under the given key.
   * @param key the key to use to retrieve the client property.
   * @return the value of the client property stored under the given key, or <code>null</code> if the property was
   * not found.
   * @throws NullPointerException if the given key is <code>null</code>.
   */
  public Object clientProperty(Object key) {
    return driver.clientProperty(target, key);
  }

  /**
   * Waits until the value of this fixture's <code>{@link JProgressBar}</code> is equal to the given value.
   * @param value the expected value.
   * @return this fixture.
   * @throws IllegalArgumentException if the given value is less than the <code>JProgressBar</code>'s minimum value.
   * @throws IllegalArgumentException if the given value is greater than the <code>JProgressBar</code>'s maximum value.
   * @throws WaitTimedOutError if the value of the <code>JProgressBar</code> does not reach the expected value within
   * 30 seconds.
   */
  public JProgressBarFixture waitUntilValueIs(int value) {
    driver.waitUntilValueIs(target, value);
    return this;
  }

  /**
   * Waits until the value of this fixture's <code>{@link JProgressBar}</code> is equal to the given value.
   * @param value the expected value.
   * @param timeout the amount of time to wait.
   * @return this fixture.
   * @throws IllegalArgumentException if the given value is less than the <code>JProgressBar</code>'s minimum value.
   * @throws IllegalArgumentException if the given value is greater than the <code>JProgressBar</code>'s maximum value.
   * @throws NullPointerException if the given timeout is <code>null</code>.
   * @throws WaitTimedOutError if the value of the <code>JProgressBar</code> does not reach the expected value within
   * the specified timeout.
   */
  public JProgressBarFixture waitUntilValueIs(int value, Timeout timeout) {
    driver.waitUntilValueIs(target, value, timeout);
    return this;
  }

  /**
   * Waits until the value of this fixture's <code>{@link JProgressBar}</code> is in determinate mode.
   * @return this fixture.
   * @throws WaitTimedOutError if the <code>JProgressBar</code> does not reach determinate mode within 30 seconds.
   */
  public JProgressBarFixture waitUntilIsDeterminate() {
    driver.waitUntilIsDeterminate(target);
    return this;
  }

  /**
   * Waits until the value of this fixture's <code>{@link JProgressBar}</code> is in determinate mode.
   * @param timeout the amount of time to wait.
   * @return this fixture.
   * @throws NullPointerException if the given timeout is <code>null</code>.
   * @throws WaitTimedOutError if the <code>JProgressBar</code> does not reach determinate mode within the specified
   * timeout.
   */
  public JProgressBarFixture waitUntilIsDeterminate(Timeout timeout) {
    driver.waitUntilIsDeterminate(target, timeout);
    return this;
  }
}
