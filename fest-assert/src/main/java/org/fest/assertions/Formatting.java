/*
 * Created on Sep 14, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ToString.toStringOf;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.isEmpty;

/**
 * Provides utility methods related to formatting.
 *
 * @author Yvonne Wang
 */
public final class Formatting {

  private static final String EMPTY_MESSAGE = "";

  static String createMessageFrom(Description description, Object[] message) {
    return format(description, concat(message));
  }

  /**
   * Returns the given message formatted as follows:
   * <pre>
   * [description] message.
   * </pre>
   * @param description the description of the actual value in the failed assertion. It can be <code>null</code>.
   * @param message the message to format.
   * @return the formatted message.
   * @since 1.2
   */
  public static String format(Description description, String message) {
    String s = valueOf(description);
    return concat(format(s), message);
  }

  /**
   * Returns the value of the given <code>{@link Description}</code>.
   * @param description the given <code>Description</code>.
   * @return the value of the given <code>Description</code>, or <code>null</code> if the given <code>Description</code>
   * is <code>null</code>.
   */
  public static String valueOf(Description description) {
    return description == null ? null : description.value();
  }

  /**
   * Formats the given message: <li>if it is <code>null</code> or empty, an empty <code>String</code> is returned,
   * otherwise uses the following format:
   * <pre>
   * [message]{whitespace}
   * </pre>
   * @param message the message to format.
   * @return the formatted message.
   */
  public static String format(String message) {
    if (isEmpty(message)) return EMPTY_MESSAGE;
    return concat("[", message, "] ");
  }

  /**
   * Returns the <code>String</code> representation of the given object in between brackets ("<" and ">"). This method
   * has special support for arrays, <code>Class<?></code>, <code>Collection</code>s, <code>Map</code>s,
   * <code>File</code>s and <code>Dimension</code>s. For any other types, this method simply calls its
   * <code>toString</code> implementation.
   * @param o the given object.
   * @return the <code>String</code> representation of the given object in between brackets.
   */
  public static String inBrackets(Object o) {
    return doBracketAround(toStringOf(o));
  }

  private static String doBracketAround(Object o) {
    return concat("<", o, ">");
  }

  private Formatting() {}
}
