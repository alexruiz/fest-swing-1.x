/*
 * Created on Jun 25, 2009
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
package org.fest.swing.util;

import java.util.regex.Pattern;

import org.fest.util.Arrays;

/**
 * Understands utility methods for regular expression patterns.
 *
 * @author Alex Ruiz
 */
public final class Patterns {

  /**
   * Formats the given array of regular expression patterns.
   * <p>
   * For example, the array
   * <pre>
   * Pattern[] patterns = { Pattern.compile("hello"), Pattern.compile("world") };
   * </pre>
   * will be formatted as
   * <pre>
   * ['hello', 'world']
   * </pre>
   * </p>
   * @param patterns the array of patterns to format.
   * @return the {@code String} containing the formatted array.
   * @throws NullPointerException if the given array of patterns is {@code null}.
   * @throws NullPointerException if any of the patterns in the given array is {@code null}.
   */
  public static String format(Pattern[] patterns) {
    if (patterns == null) throw new NullPointerException("The array of patterns should not be null");
    int patternCount = patterns.length;
    String[] patternsAsText = new String[patternCount];
    for (int i = 0; i < patternCount; i++)
      patternsAsText[i] = patternValueOf(patterns[i]);
    return Arrays.format(patternsAsText);
  }

  private static String patternValueOf(Pattern pattern) {
    if (pattern  == null) throw new NullPointerException("The array of patterns should not contain null values");
    return pattern.pattern();
  }

  private Patterns() {}

}
