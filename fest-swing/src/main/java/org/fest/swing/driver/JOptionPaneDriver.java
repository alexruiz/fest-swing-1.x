/*
 * Created on Feb 27, 2008
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

import static javax.swing.JOptionPane.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.JOptionPaneMessageQuery.messageOf;
import static org.fest.swing.driver.JOptionPaneMessageTypeQuery.messageTypeOf;
import static org.fest.swing.driver.JOptionPaneMessageTypes.messageTypeAsText;
import static org.fest.swing.driver.JOptionPaneOptionsQuery.optionsOf;
import static org.fest.swing.driver.JOptionPaneTitleQuery.titleOf;
import static org.fest.swing.driver.TextAssert.verifyThat;

import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import org.fest.assertions.Description;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.core.matcher.JButtonMatcher;
import org.fest.swing.exception.ComponentLookupException;

/**
 * Understands functional testing of <code>{@link JOptionPane}</code>s:
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
public class JOptionPaneDriver extends JComponentDriver {

  private static final String MESSAGE_PROPERTY = "message";
  private static final String MESSAGE_TYPE_PROPERTY = "messageType";
  private static final String OPTIONS_PROPERTY = "options";
  private static final String TITLE_PROPERTY = "title";

  /**
   * Creates a new </code>{@link JOptionPaneDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JOptionPaneDriver(Robot robot) {
    super(robot);
  }

  /**
   * Asserts that the title in the given <code>{@link JOptionPane}</code> matches the given value.
   * @param optionPane the target <code>JOptionPane</code>.
   * @param title the title to match. It can be a regular expression.
   * @throws AssertionError if the <code>JOptionPane</code> does not have the given title.
   */
  @RunsInEDT
  public void requireTitle(JOptionPane optionPane, String title) {
    verifyThat(titleOf(optionPane)).as(propertyName(optionPane, TITLE_PROPERTY)).isEqualOrMatches(title);
  }

  /**
   * Asserts that the title in the given <code>{@link JOptionPane}</code> matches the given regular expression pattern.
   * @param optionPane the target <code>JOptionPane</code>.
   * @param pattern the regular expression pattern to match.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the <code>JOptionPane</code> does not have the given title.
   * @since 1.2
   */
  @RunsInEDT
  public void requireTitle(JOptionPane optionPane, Pattern pattern) {
    verifyThat(titleOf(optionPane)).as(propertyName(optionPane, TITLE_PROPERTY)).matches(pattern);
  }

  /**
   * Asserts that the title of the <code>{@link JOptionPane}</code> matches the given value. If the given value is a
   * regular expression and the message in the <code>JOptionPane</code> is not a <code>String</code>, this method will use the
   * <code>toString</code> representation of such message.
   * message in the <code>JOptionPane</code> is not a <code>String</code>, this method will use the
   * <code>toString</code> representation of such message.
   * @param optionPane the target <code>JOptionPane</code>.
   * @param message the message to verify. If it is a <code>String</code>, it can be specified as a regular expression.
   * @throws AssertionError if the message in the <code>JOptionPane</code> is not equal to or does not match the given
   * message.
   */
  @RunsInEDT
  public void requireMessage(JOptionPane optionPane, Object message) {
    Object actual = messageOf(optionPane);
    if (message instanceof String) {
      requireMessage(optionPane, (String)message, toStringOf(actual));
      return;
    }
    assertThat(actual).as(messageProperty(optionPane)).isEqualTo(message);
  }

  @RunsInEDT
  private void requireMessage(JOptionPane optionPane, String expected, String actual) {
    verifyThat(actual).as(messageProperty(optionPane)).isEqualOrMatches(expected);
  }

  /**
   * Asserts that the title of the <code>{@link JOptionPane}</code> matches the given regular expression pattern. If the
   * message in the <code>JOptionPane</code> is not a <code>String</code>, this method will use the
   * <code>toString</code> representation of such message.
   * @param optionPane the target <code>JOptionPane</code>.
   * @param pattern the regular expression to match.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the message in the </code>JOptionPaneFixture</code> does not match the given regular
   * expression pattern.
   * @since 1.2
   */
  @RunsInEDT
  public void requireMessage(JOptionPane optionPane, Pattern pattern) {
    Object actual = messageOf(optionPane);
    verifyThat(toStringOf(actual)).as(messageProperty(optionPane)).matches(pattern);
  }

  private String toStringOf(Object o) {
    return (o == null) ? null : o.toString();
  }

  private Description messageProperty(JOptionPane optionPane) {
    return propertyName(optionPane, MESSAGE_PROPERTY);
  }

  /**
   * Asserts that the <code>{@link JOptionPane}</code> has the given options.
   * @param optionPane the target <code>JOptionPane</code>.
   * @param options the options to verify.
   * @throws AssertionError if the <code>JOptionPane</code> does not have the given options.
   */
  @RunsInEDT
  public void requireOptions(JOptionPane optionPane, Object[] options) {
    assertThat(optionsOf(optionPane)).as(propertyName(optionPane, OPTIONS_PROPERTY)).isEqualTo(options);
  }

  /**
   * Finds the "OK" button in the <code>{@link JOptionPane}</code>. This method is independent of locale and platform.
   * @param optionPane the target <code>JOptionPane</code>.
   * @return the "OK" button.
   * @throws ComponentLookupException if the a "OK" button cannot be found.
   */
  @RunsInEDT
  public JButton okButton(JOptionPane optionPane) {
    return buttonWithTextFromUIManager(optionPane, "OptionPane.okButtonText");
  }

  /**
   * Finds the "Cancel" button in the <code>{@link JOptionPane}</code>. This method is independent of locale and
   * platform.
   * @param optionPane the target <code>JOptionPane</code>.
   * @return the "Cancel" button.
   * @throws ComponentLookupException if the a "Cancel" button cannot be found.
   */
  @RunsInEDT
  public JButton cancelButton(JOptionPane optionPane) {
    return buttonWithTextFromUIManager(optionPane, "OptionPane.cancelButtonText");
  }

  /**
   * Finds the "Yes" button in the <code>{@link JOptionPane}</code>. This method is independent of locale and platform.
   * @param optionPane the target <code>JOptionPane</code>.
   * @return the "Yes" button.
   * @throws ComponentLookupException if the a "Yes" button cannot be found.
   */
  @RunsInEDT
  public JButton yesButton(JOptionPane optionPane) {
    return buttonWithTextFromUIManager(optionPane, "OptionPane.yesButtonText");
  }

  /**
   * Finds the "No" button in the <code>{@link JOptionPane}</code>.  This method is independent of locale and platform.
   * @param optionPane the target <code>JOptionPane</code>.
   * @return the "No" button.
   * @throws ComponentLookupException if the a "No" button cannot be found.
   */
  @RunsInEDT
  public JButton noButton(JOptionPane optionPane) {
    return buttonWithTextFromUIManager(optionPane, "OptionPane.noButtonText");
  }

  @RunsInEDT
  private JButton buttonWithTextFromUIManager(JOptionPane optionPane, String key) {
    return buttonWithText(optionPane, UIManager.getString(key));
  }

  /**
   * Finds a button in the <code>{@link JOptionPane}</code> containing the given text.
   * @param optionPane the target <code>JOptionPane</code>.
   * @param text the text of the button to find and return. It can be a regular expression.
   * @return a button containing the given text.
   * @throws ComponentLookupException if the a button with the given text cannot be found.
   */
  @RunsInEDT
  public JButton buttonWithText(JOptionPane optionPane, String text) {
    return robot.finder().find(optionPane, JButtonMatcher.withText(text).andShowing());
  }

  /**
   * Finds a button in the <code>{@link JOptionPane}</code> whose text matches the given regular expression pattern.
   * @param optionPane the target <code>JOptionPane</code>.
   * @param pattern the regular expression pattern to match.
   * @return a button containing the given text.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws ComponentLookupException if the a button with the given text cannot be found.
   * @since 1.2
   */
  @RunsInEDT
  public JButton buttonWithText(JOptionPane optionPane, Pattern pattern) {
    return robot.finder().find(optionPane, JButtonMatcher.withText(pattern).andShowing());
  }

  /**
   * Finds a <code>{@link JButton}</code> in the <code>{@link JOptionPane}</code> (assuming it has only one button.)
   * @param optionPane the target <code>JOptionPane</code>.
   * @return the only <code>JButton</code> contained in the <code>JOptionPane</code>.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   * @deprecated in 1.2
   */
  @RunsInEDT
  @Deprecated public JButton button(JOptionPane optionPane) {
    return robot.finder().findByType(optionPane, JButton.class);
  }

  /**
   * Returns the <code>{@link JTextComponent}</code> in the given message only if the message is of type input.
   * @param optionPane the target <code>JOptionPane</code>.
   * @return the text component in the given message.
   * @throws ComponentLookupException if the message type is not input and therefore it does not contain a text component.
   * @deprecated in 1.2
   */
  @RunsInEDT
  @Deprecated public JTextComponent textBox(JOptionPane optionPane) {
    return robot.finder().findByType(optionPane, JTextComponent.class);
  }

  /**
   * Asserts that the <code>{@link JOptionPane}</code> is displaying an error message.
   * @param optionPane the target <code>JOptionPane</code>.
   */
  @RunsInEDT
  public void requireErrorMessage(JOptionPane optionPane) {
    assertEqualMessageType(optionPane, ERROR_MESSAGE);
  }

  /**
   * Asserts that the <code>{@link JOptionPane}</code> is displaying an information message.
   * @param optionPane the target <code>JOptionPane</code>.
   */
  @RunsInEDT
  public void requireInformationMessage(JOptionPane optionPane) {
    assertEqualMessageType(optionPane, INFORMATION_MESSAGE);
  }

  /**
   * Asserts that the <code>{@link JOptionPane}</code> is displaying a warning message.
   * @param optionPane the target <code>JOptionPane</code>.
   */
  @RunsInEDT
  public void requireWarningMessage(JOptionPane optionPane) {
    assertEqualMessageType(optionPane, WARNING_MESSAGE);
  }

  /**
   * Asserts that the <code>{@link JOptionPane}</code> is displaying a question.
   * @param optionPane the target <code>JOptionPane</code>.
   */
  @RunsInEDT
  public void requireQuestionMessage(JOptionPane optionPane) {
    assertEqualMessageType(optionPane, QUESTION_MESSAGE);
  }

  /**
   * Asserts that the <code>{@link JOptionPane}</code> is displaying a plain message.
   * @param optionPane the target <code>JOptionPane</code>.
   */
  @RunsInEDT
  public void requirePlainMessage(JOptionPane optionPane) {
    assertEqualMessageType(optionPane, PLAIN_MESSAGE);
  }

  @RunsInEDT
  private void assertEqualMessageType(JOptionPane optionPane, int expected) {
    String actualType = actualMessageTypeAsText(optionPane);
    assertThat(actualType).as(propertyName(optionPane, MESSAGE_TYPE_PROPERTY)).isEqualTo(messageTypeAsText(expected));
  }

  @RunsInEDT
  private String actualMessageTypeAsText(final JOptionPane optionPane) {
    return messageTypeAsText(messageTypeOf(optionPane));
  }
}
