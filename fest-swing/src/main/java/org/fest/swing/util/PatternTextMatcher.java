/*
 * Created on Jun 26, 2009
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

import static org.fest.swing.util.Patterns.format;
import static org.fest.swing.util.Strings.match;
import static org.fest.util.Arrays.isEmpty;
import static org.fest.util.Strings.quote;

import java.util.regex.Pattern;

/**
 * Understands matching text to a group of {@code String} values. Matching is perform by equality or by regular
 * expression matching.
 *
 * @author Alex Ruiz
 */
public class PatternTextMatcher implements TextMatcher {

  private final Pattern[] patterns;

  /**
   * Creates a new </code>{@link PatternTextMatcher}</code>.
   * @param patterns the regular expression patterns to match.
   * @throws NullPointerException if the array of patterns is {@code null}.
   * @throws IllegalArgumentException if the array of patterns is empty.
   */
  public PatternTextMatcher(Pattern...patterns) {
    if (patterns == null) throw new NullPointerException("The array of patterns should not be null");
    if (isEmpty(patterns)) throw new IllegalArgumentException("The array of patterns should not be empty");
    this.patterns = patterns;
  }

  /**
   * Indicates whether the given text matches the regular expression patterns in this matcher.
   * @param text the text to verify.
   * @return {@code true} if the given text matches the <code>Pattern</code> values in this matcher,
   * {@code false} otherwise.
   * @throws NullPointerException if any of the regular expressions is {@code null}.
   */
  public boolean isMatching(String text) {
    for (Pattern pattern : patterns)
      if (match(pattern, text)) return true;
    return false;
  }

  /**
   * Returns "pattern" if this matcher contains only one pattern, or "patterns" if this matcher contains more than one
   * pattern.
   * @return "pattern" if this matcher contains only one pattern, or "patterns" if this matcher contains more than one
   * pattern.
   */
  public String description() {
    if (onlyOnePattern()) return "pattern";
    return "patterns";
  }

  /**
   * Returns the regular expression patterns in this matcher, formatted as a single {@code String}.
   * @return the regular expression patterns in this matcher, formatted as a single {@code String}.
   */
  public String formattedValues() {
    if (onlyOnePattern()) return quote(patterns[0].pattern());
    return format(patterns);
  }

  private boolean onlyOnePattern() {
    return patterns.length == 1;
  }
}
