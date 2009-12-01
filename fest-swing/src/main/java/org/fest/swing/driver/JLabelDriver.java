/*
 * Created on Feb 28, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.driver.TextAssert.verifyThat;

import java.util.regex.Pattern;

import javax.swing.JLabel;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;

/**
 * Understands:
 * <ul>
 * <li>simulation of user input on a <code>{@link JLabel}</code> (if applicable)</li>
 * <li>state verification of a <code>{@link JLabel}</code></li>
 * </ul>
 * This class is intended for internal use only.
 *
 * @author Alex Ruiz
 */
public class JLabelDriver extends JComponentDriver {

  private static final String TEXT_PROPERTY = "text";

  /**
   * Creates a new </code>{@link JLabelDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JLabelDriver(Robot robot) {
    super(robot);
  }

  /**
   * Asserts that the text of the <code>{@link JLabel}</code> is equal to the specified <code>String</code>.
   * @param label the target <code>JLabel</code>.
   * @param expected the text to match.
   * @throws AssertionError if the text of the <code>JLabel</code> is not equal to the given one.
   */
  @RunsInEDT
  public void requireText(JLabel label, String expected) {
    verifyThat(textOf(label)).as(propertyName(label, TEXT_PROPERTY)).isEqualOrMatches(expected);
  }

  /**
   * Asserts that the text of the <code>{@link JLabel}</code> matches the given regular expression pattern.
   * @param label the target <code>JLabel</code>.
   * @param pattern the regular expression pattern to match.
   * @throws AssertionError if the text of the <code>JLabel</code> does not match the given regular expression pattern.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @since 1.2
   */
  @RunsInEDT
  public void requireText(JLabel label, Pattern pattern) {
    verifyThat(textOf(label)).as(propertyName(label, TEXT_PROPERTY)).matches(pattern);
  }

  /**
   * Returns the text of the given <code>{@link JLabel}</code>.
   * @param label the given <code>JLabel</code>.
   * @return the text of the given <code>JLabel</code>.
   */
  @RunsInEDT
  public String textOf(JLabel label) {
    return JLabelTextQuery.textOf(label);
  }
}
