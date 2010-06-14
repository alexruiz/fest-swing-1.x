/*
 * Created on Jun 12, 2010
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
package org.fest.assertions;

import static org.fest.assertions.Formatter.format;
import static org.fest.util.Strings.*;

import org.junit.ComparisonFailure;

/**
 * Understands messages related to comparison failures.
 *
 * @author Alex Ruiz
 */
final class ComparisonFailureMessages {

  static String comparisonFailureMessage(Object actual, Object expected) {
    return comparisonFailureMessage(null, actual, expected);
  }

  static String comparisonFailureMessage(String description, Object actual, Object expected) {
    String d = inBrackets(description);
    String a = format(actual);
    String e = format(expected);
    if (isArray(actual) || isArray(expected)) return concat(d, "expected:<", e, "> but was:<", a, ">");
    return new ComparisonFailure(d, e, a).getMessage();
  }

  private static String inBrackets(String s) {
    if (isEmpty(s)) return "";
    return concat("[", s, "] ");
  }

  private static boolean isArray(Object o) {
    return o != null && o.getClass().isArray();
  }
}
