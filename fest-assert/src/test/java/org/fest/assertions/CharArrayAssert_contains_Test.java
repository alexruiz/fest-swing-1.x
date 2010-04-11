/*
 * Created on Feb 14, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link CharArrayAssert#contains(char...)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CharArrayAssert_contains_Test implements GroupAssert_contains_TestCase {

  private static char[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = ArrayFactory.charArray('a', 'b');
  }

  @Test
  public void should_pass_if_actual_contains_given_value() {
    new CharArrayAssert(array).contains('a');
  }

  @Test
  public void should_pass_if_actual_contains_given_values() {
    new CharArrayAssert(array).contains('a', 'b');
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualArrayIsNull(new CodeToTest() {
      public void run() {
        new CharArrayAssert(null).contains('a', 'b');
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualArrayIsNull(new CodeToTest() {
      public void run() {
        new CharArrayAssert(null).as("A Test")
                                 .contains('a', 'b');
      }
    });
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("the given array of chars should not be null").on(new CodeToTest() {
      public void run() {
        new CharArrayAssert(array).contains(null);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] the given array of chars should not be null").on(new CodeToTest() {
      public void run() {
        new CharArrayAssert(array).as("A Test")
                                  .contains(null);
      }
    });
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_values() {
    expectAssertionError("array:<[a, b]> does not contain element(s):<[c, d]>").on(new CodeToTest() {
      public void run() {
        new CharArrayAssert(array).contains('c', 'd');
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_given_values() {
    expectAssertionError("[A Test] array:<[a, b]> does not contain element(s):<[c, d]>").on(new CodeToTest() {
      public void run() {
        new CharArrayAssert(array).as("A Test")
                                  .contains('c', 'd');
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new CharArrayAssert(array).overridingErrorMessage("My custom message")
                                  .contains('c', 'd');
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new CharArrayAssert(array).as("A Test")
                                  .overridingErrorMessage("My custom message")
                                  .contains('c', 'd');
      }
    });
  }


}
