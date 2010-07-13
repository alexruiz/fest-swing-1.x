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

import static org.fest.assertions.ArrayFactory.shortArray;
import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.*;

/**
 * Tests for <code>{@link ShortArrayAssert#contains(short...)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ShortArrayAssert_contains_Test implements GroupAssert_contains_TestCase {

  private static short[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = shortArray(8, 6);
  }

  @Test
  public void should_pass_if_actual_contains_given_value() {
    new ShortArrayAssert(array).contains(shortArray(8));
  }

  @Test
  public void should_pass_if_actual_contains_given_values() {
    new ShortArrayAssert(array).contains(shortArray(8, 6));
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(null).contains(shortArray(8, 6));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(null).as("A Test")
                                  .contains(shortArray(8, 6));
      }
    });
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("The given array should not be null").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).contains(null);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] The given array should not be null").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).as("A Test")
                                   .contains(null);
      }
    });
  }

  @Test
  public void should_fail_if_actual_does_not_contain_given_values() {
    expectAssertionError("<[8, 6]> does not contain element(s):<[10]>").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).contains(shortArray(10));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_given_values() {
    expectAssertionError("[A Test] <[8, 6]> does not contain element(s):<[10]>").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).as("A Test")
                                   .contains(shortArray(10));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).overridingErrorMessage("My custom message")
                                   .contains(shortArray(10));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ShortArrayAssert(array).as("A Test")
                                   .overridingErrorMessage("My custom message")
                                   .contains(shortArray(10));
      }
    });
  }
}
