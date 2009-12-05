/*
 * Created on Dec 1, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.JProgressBarIndeterminateQuery.isIndeterminate;
import static org.fest.swing.driver.JProgressBarMinimumAndMaximumQuery.minimumAndMaximumOf;
import static org.fest.swing.driver.JProgressBarStringQuery.stringOf;
import static org.fest.swing.driver.JProgressBarValueQuery.valueOf;
import static org.fest.swing.driver.JProgressBarWaitUntilValueIsEqualToExpectedTask.waitUntilValueIsEqualToExpected;
import static org.fest.swing.driver.TextAssert.verifyThat;
import static org.fest.swing.timing.Timeout.timeout;
import static org.fest.util.Strings.concat;

import java.util.regex.Pattern;

import javax.swing.JProgressBar;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.timing.Timeout;
import org.fest.swing.util.Pair;

/**
 * Understands functional testing of <code>{@link JProgressBar}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public class JProgressBarDriver extends JComponentDriver {

  private static final String TEXT_PROPERTY = "string";

  /**
   * Creates a new </code>{@link JProgressBarDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JProgressBarDriver(Robot robot) {
    super(robot);
  }

  /**
   * Asserts that the text of the <code>{@link JProgressBar}</code> is equal to the specified <code>String</code>.
   * @param progressBar the target <code>JProgressBar</code>.
   * @param expected the text to match.
   * @throws AssertionError if the text of the <code>JProgressBar</code> is not equal to the given one.
   * @see JProgressBar#getString()
   */
  @RunsInEDT
  public void requireText(JProgressBar progressBar, String expected) {
    verifyThat(stringOf(progressBar)).as(propertyName(progressBar, TEXT_PROPERTY)).isEqualOrMatches(expected);
  }

  /**
   * Asserts that the text of the <code>{@link JProgressBar}</code> matches the given regular expression pattern.
   * @param progressBar the target <code>JProgressBar</code>.
   * @param pattern the regular expression pattern to match.
   * @throws AssertionError if the text of the <code>JProgressBar</code> does not match the given regular expression pattern.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @see JProgressBar#getString()
   */
  @RunsInEDT
  public void requireText(JProgressBar progressBar, Pattern pattern) {
    verifyThat(stringOf(progressBar)).as(propertyName(progressBar, TEXT_PROPERTY)).matches(pattern);
  }

  /**
   * Verifies that the value of the given <code>{@link JProgressBar}</code> is equal to the given one.
   * @param progressBar the target <code>JProgressBar</code>.
   * @param value the expected value.
   * @throws AssertionError if the value of the <code>JProgressBar</code> is not equal to the given one.
   */
  @RunsInEDT
  public void requireValue(JProgressBar progressBar, int value) {
    assertThat(valueOf(progressBar)).as(propertyName(progressBar, "value")).isEqualTo(value);
  }

  /**
   * Verifies that the given <code>{@link JProgressBar}</code> is in indeterminate mode.
   * @param progressBar the target <code>JProgressBar</code>.
   * @throws AssertionError if the given <code>JProgressBar</code> is not in indeterminate mode.
   */
  @RunsInEDT
  public void requireInIndeterminateMode(JProgressBar progressBar) {
    assertThat(isIndeterminate(progressBar)).as(propertyName(progressBar, "indeterminate")).isTrue();
  }

  /**
   * Waits until the value of the given <code>{@link JProgressBar}</code> is equal to the given value.
   * @param progressBar the target <code>JProgressBar</code>.
   * @param value the expected value.
   * @throws IllegalArgumentException if the given value is less than the <code>JProgressBar</code>'s minimum value.
   * @throws WaitTimedOutError if the value of the <code>JProgressBar</code> does not reach the expected value within
   * 30 seconds.
   */
  @RunsInEDT
  public void waitUntilValueIs(JProgressBar progressBar, int value) {
    waitUntilValueIs(progressBar, value, timeout(30, SECONDS));
  }

  /**
   * Waits until the value of the given <code>{@link JProgressBar}</code> is equal to the given value.
   * @param progressBar the target <code>JProgressBar</code>.
   * @param value the expected value.
   * @param timeout the amout of time to wait.
   * @throws IllegalArgumentException if the given value is less than the <code>JProgressBar</code>'s minimum value.
   * @throws NullPointerException if the given timeout is <code>null</code>.
   * @throws WaitTimedOutError if the value of the <code>JProgressBar</code> does not reach the expected value within
   * the specified timeout.
   */
  @RunsInEDT
  public void waitUntilValueIs(JProgressBar progressBar, int value, Timeout timeout) {
    assertIsInBetweenMinAndMax(progressBar, value);
    if (timeout == null) throw new NullPointerException("Timeout should not be null");
    waitUntilValueIsEqualToExpected(progressBar, value, timeout);
  }

  @RunsInEDT
  private void assertIsInBetweenMinAndMax(JProgressBar progressBar, int value) {
    Pair<Integer, Integer> minAndMax = minimumAndMaximumOf(progressBar);
    assertIsInBetweenMinAndMax(value, minAndMax.i, minAndMax.ii);
  }

  private void assertIsInBetweenMinAndMax(int value, int min, int max) {
    if (value >= min && value <= max) return;
    throw new IllegalArgumentException(concat("Value <", value, "> should be between <[", min, ", ", max, "]>"));
  }
}
