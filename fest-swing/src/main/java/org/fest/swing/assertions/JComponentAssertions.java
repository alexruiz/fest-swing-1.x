/*
 * Created on Aug 23, 2010
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

import static org.fest.swing.assertions.JComponentToolTipQuery.toolTipOf;
import static org.fest.swing.assertions.TextAssert.verifyThat;

import java.util.regex.Pattern;

import javax.swing.JComponent;

import org.fest.swing.annotation.RunsInEDT;

/**
 * Verifies the state of a <code>{@link JComponent}</code>.
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 * </p>
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
public class JComponentAssertions extends ComponentAssertions {

  private static final String TOOL_TIP_TEXT_PROPERTY = "toolTipText";

  /**
   * Asserts that the toolTip in the given <code>{@link JComponent}</code> matches the given value.
   * @param c the given {@code JComponent}.
   * @param expected the expected toolTip. It can be a regular expression.
   * @throws AssertionError if the toolTip of the given {@code JComponent} does not match the given value.
   */
  @RunsInEDT
  public void requireToolTip(JComponent c, String expected) {
    verifyThat(toolTipOf(c)).as(propertyName(c, TOOL_TIP_TEXT_PROPERTY)).isEqualOrMatches(expected);
  }

  /**
   * Asserts that the toolTip in the given <code>{@link JComponent}</code> matches the given regular expression pattern.
   * @param c the given {@code JComponent}.
   * @param pattern the regular expression pattern to match.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws AssertionError if the toolTip of the given {@code JComponent} does not match the given value.
   */
  @RunsInEDT
  public void requireToolTip(JComponent c, Pattern pattern) {
    verifyThat(toolTipOf(c)).as(propertyName(c, TOOL_TIP_TEXT_PROPERTY)).matches(pattern);
  }
}
