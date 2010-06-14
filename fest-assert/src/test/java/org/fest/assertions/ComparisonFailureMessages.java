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

  private static final Description EMPTY_DESCRIPTION = description("");

  static String comparisonFailureMessage(Expected expected, Actual actual) {
    return comparisonFailureMessage(EMPTY_DESCRIPTION, expected, actual);
  }

  static String comparisonFailureMessage(Description description, Expected expected, Actual actual) {
    return new ComparisonFailure(description.value(), expected.value, actual.value).getMessage();
  }

  static Description description(String value) {
    String formatted = value;
    if (!isEmpty(value)) formatted = concat("[", value, "]");
    return new BasicDescription(formatted);
  }

  static Expected expected(Object value) {
    return expected(format(value));
  }

  static Expected expected(String value) {
    return new Expected(value);
  }

  static Actual actual(Object value) {
    return actual(format(value));
  }

  static Actual actual(String value) {
    return new Actual(value);
  }

  private ComparisonFailureMessages() {}

  static class Expected {
    private final String value;

    private Expected(String value) {
      this.value = value;
    }
  }

  static class Actual {
    private final String value;

    private Actual(String value) {
      this.value = value;
    }
  }
}
