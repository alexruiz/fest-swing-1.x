/*
 * Created on Sep 27, 2009
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
package org.fest.assertions;

import static org.fest.assertions.Formatting.*;
import static org.fest.util.Arrays.array;
import static org.fest.util.Strings.concat;

/**
 * Understands common error messages.
 *
 * @author Alex Ruiz
 */
final class ErrorMessages {

  static String unexpectedNotEqual(Object actual, Object expected) {
    return concat("expected:", inBrackets(expected), " but was:", inBrackets(actual));
  }

  static String unexpectedEqual(Object actual, Object o) {
    return assertionFailed(actual, " should not be equal to:", o);
  }

  static String unexpectedLessThanOrEqualTo(Object actual, Object value) {
    return assertionFailed(actual, " should be greater than:", value);
  }

  static String unexpectedLessThan(Object actual, Object value) {
    return assertionFailed(actual, " should be greater than or equal to:", value);
  }

  static String unexpectedGreaterThanOrEqualTo(Object actual, Object value) {
    return assertionFailed(actual, " should be less than:", value);
  }

  static String unexpectedGreaterThan(Object actual, Object value) {
    return assertionFailed(actual, " should be less than or equal to:", value);
  }

  static String unexpectedNullType(Description description) {
    return createMessageFrom(description, array("expected type should not be null"));
  }

  private static String assertionFailed(Object actual, String reason, Object expected) {
    return assertionFailed(null, actual, reason, expected);
  }

  private static String assertionFailed(Description description, Object actual, String reason, Object expected) {
    return createMessageFrom(description, array("actual value:", inBrackets(actual), reason, inBrackets(expected)));
  }

  private ErrorMessages() {}
}
