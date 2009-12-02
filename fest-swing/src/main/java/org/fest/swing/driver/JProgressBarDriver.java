/*
 * Created on Dec 1, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.JProgressBarValueQuery.valueOf;

import javax.swing.JProgressBar;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;

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

  private static final String VALUE_PROPERTY = "value";

  /**
   * Creates a new </code>{@link JProgressBarDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JProgressBarDriver(Robot robot) {
    super(robot);
  }

  /**
   * Verifies that the value of the given <code>{@link JProgressBar}</code> is equal to the given one.
   * @param progressBar the target <code>JProgressBar</code>.
   * @param value the expected value.
   * @throws AssertionError if the value of the <code>JProgressBar</code> is not equal to the given one.
   */
  @RunsInEDT
  public void requireValue(JProgressBar progressBar, int value) {
    assertThat(valueOf(progressBar)).as(propertyName(progressBar, VALUE_PROPERTY)).isEqualTo(value);
  }
}
