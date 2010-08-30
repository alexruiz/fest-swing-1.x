/*
 * Created on Aug 24, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.assertions;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.assertions.AbstractButtonSelectedQuery.isSelected;
import static org.fest.swing.assertions.TextAssert.verifyThat;
import static org.fest.swing.query.AbstractButtonTextQuery.textOf;

import java.util.regex.Pattern;

import javax.swing.AbstractButton;

import org.fest.assertions.Description;
import org.fest.swing.annotation.RunsInEDT;

/**
 * Verifies the state of an <code>{@link AbstractButton}</code>.
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 * </p>
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
public class AbstractButtonAssertions extends JComponentAssertions {

  private static final String SELECTED_PROPERTY = "selected";
  private static final String TEXT_PROPERTY = "text";

  /**
   * Asserts that the text in the given button is equal to or matches the specified {@code String}.
   * @param button the given button.
   * @param expected the text to match. It can be a regular expression.
   * @throws AssertionError if the text of the button is not equal to or does not match the given one.
   */
  @RunsInEDT
  public void requireText(AbstractButton button, String expected) {
    verifyThat(textOf(button)).as(propertyName(button, TEXT_PROPERTY)).isEqualOrMatches(expected);
  }

  /**
   * Asserts that the text in the given button matches the given regular expression pattern.
   * @param button the given button.
   * @param pattern the regular expression pattern to match.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws AssertionError if the text of the button does not match the given regular expression pattern.
   * @since 1.2
   */
  public void requireText(AbstractButton button, Pattern pattern) {
    verifyThat(textOf(button)).as(propertyName(button, TEXT_PROPERTY)).matches(pattern);
  }

  /**
   * Verifies that the button is selected.
   * @param button the given button.
   * @throws AssertionError if the button is not selected.
   */
  @RunsInEDT
  public void requireSelected(AbstractButton button) {
    assertThatButtonIsSelected(button, true);
  }

  /**
   * Verifies that the button is not selected.
   * @param button the given button.
   * @throws AssertionError if the button is selected.
   */
  @RunsInEDT
  public void requireNotSelected(AbstractButton button) {
    assertThatButtonIsSelected(button, false);
  }

  @RunsInEDT
  private void assertThatButtonIsSelected(AbstractButton button, boolean selected) {
    assertThat(isSelected(button)).as(selectedProperty(button)).isEqualTo(selected);
  }

  @RunsInEDT
  private static Description selectedProperty(AbstractButton button) {
    return propertyName(button, SELECTED_PROPERTY);
  }
}
