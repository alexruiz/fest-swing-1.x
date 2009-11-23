/*
 * Created on Jan 13, 2008
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
package org.fest.swing.util;

import static org.fest.util.Objects.areEqual;
import static org.fest.util.Strings.isEmpty;

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Understands utility methods related to <code>String</code>s.
 *
 * @author Alex Ruiz
 * @author Uli Schrempp
 */
public final class Strings {

  /**
   * Returns whether the given <code>String</code> is the default <code>toString()</code> implementation of an
   * <code>Object</code>.
   * @param s the given <code>String</code>.
   * @return <code>true</code> if the given <code>String</code> is the default <code>toString()</code> implementation,
   * <code>false</code> otherwise.
   */
  public static boolean isDefaultToString(String s) {
    if (isEmpty(s)) return false;
    int at = s.indexOf("@");
    if (at == -1) return false;
    String hash = s.substring(at + 1, s.length());
    try {
      Integer.parseInt(hash, 16);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }

  /**
   * Indicates if the given <code>String</code>s match. To match, one of the following conditions needs to be true:
   * <ul>
   * <li>both <code>String</code>s have to be equal</li>
   * <li><code>s</code> matches the regular expression in <code>pattern</code></li>
   * </ul>
   * @param pattern a <code>String</code> to match (it can be a regular expression.)
   * @param s the <code>String</code> to verify.
   * @return <code>true</code> if the given <code>String</code>s match, <code>false</code> otherwise.
   */
  public static boolean areEqualOrMatch(String pattern, String s) {
    if (areEqual(pattern, s)) return true;
    if (pattern != null && s != null) {
      try {
        return s.matches(pattern);
      } catch (PatternSyntaxException invalidRegex) {
        return s.contains(pattern);
      }
    }
    return false;
  }

  /**
   * Indicates if the given <code>String</code> matches the given regular expression pattern.
   * @param p the given regular expression pattern.
   * @param s the <code>String</code> to evaluate.
   * @return <code>true</code> if the given <code>String</code> matches the given regular expression pattern,
   * <code>false</code> otherwise. It also returns <code>false</code> if the given <code>String</code> is
   * <code>null</code>.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   */
  public static boolean match(Pattern p, String s) {
    return match(p, (CharSequence)s);
  }

  /**
   * Indicates if the given <code>CharSequence</code> matches the given regular expression pattern.
   * @param p the given regular expression pattern.
   * @param s the <code>CharSequence</code> to evaluate.
   * @return <code>true</code> if the given <code>CharSequence</code> matches the given regular expression pattern,
   * <code>false</code> otherwise. It also returns <code>false</code> if the given <code>CharSequence</code> is
   * <code>null</code>.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   */
  public static boolean match(Pattern p, CharSequence s) {
    if (p == null) throw new NullPointerException("The pattern to match should not be null");
    if (s == null) return false;
    return p.matcher(s).matches();
  }

  private Strings() {}
}
