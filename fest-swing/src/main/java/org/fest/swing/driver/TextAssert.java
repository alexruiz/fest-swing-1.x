/*
 * Created on Jun 20, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.util.Strings.areEqualOrMatch;
import static org.fest.swing.util.Strings.match;
import static org.fest.util.Strings.*;

import java.util.regex.Pattern;

import org.fest.assertions.*;

/**
 * Understands assertion methods for text.
 *
 * @author Alex Ruiz
 */
class TextAssert extends Assert implements AssertExtension {

  private final String actual;

  static TextAssert assertThat(String s) {
    return new TextAssert(s);
  }

  static TextAssert verifyThat(String s) {
    return new TextAssert(s);
  }

  TextAssert(String actual) {
    this.actual = actual;
  }

  TextAssert as(String description) {
    description(description);
    return this;
  }

  TextAssert as(Description description) {
    description(description);
    return this;
  }

  TextAssert isEqualOrMatches(String s) {
    if (areEqualOrMatch(s, actual)) return this;
    throw failure(concat(format(description()),
        "actual value:<", quote(actual), "> is not equal to or does not match pattern:<", quote(s), ">"));
  }

  TextAssert matches(Pattern pattern) {
    if (match(pattern, actual)) return this;
    throw failure(concat(format(description()),
        "actual value:<", quote(actual), "> does not match pattern:<", quote(pattern.pattern()), ">"));
  }

  private static String format(String message) {
    if (isEmpty(message)) return "";
    return concat("[", message, "] ");
  }
}
