/*
 * Created on Apr 10, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Component;
import java.util.regex.Pattern;

/**
 * Understands state verification and property value queries of GUI components that display text.
 *
 * @author Alex Ruiz
 */
public interface TextDisplayFixture {

  /**
   * Returns the text of this fixture's <code>{@link Component}</code>.
   * @return the text of the managed <code>Component</code>.
   */
  String text();

  /**
   * Asserts that the text of this fixture's <code>{@link Component}</code> is equal to or matches the specified
   * <code>String</code>.
   * @param expected the text to match. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the text of the target component is not equal to or does not match the given one.
   */
  TextDisplayFixture requireText(String expected);

  /**
   * Asserts that the text of this fixture's <code>{@link Component}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the text of the target component does not match the given regular expression pattern.
   * @since 1.2
   */
  TextDisplayFixture requireText(Pattern pattern);
}
