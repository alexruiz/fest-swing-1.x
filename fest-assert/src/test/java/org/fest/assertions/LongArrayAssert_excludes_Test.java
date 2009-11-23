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

import static org.fest.assertions.ArrayFactory.longArray;
import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link LongArrayAssert#excludes(long...)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class LongArrayAssert_excludes_Test implements GroupAssert_excludes_TestCase {

  private static long[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = longArray(6, 8);
  }

  @Test
  public void should_pass_if_actual_excludes_given_value() {
    new LongArrayAssert(array).excludes(10);
  }

  @Test
  public void should_pass_if_actual_excludes_given_values() {
    new LongArrayAssert(array).excludes(10, 2);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfArrayIsNull(new CodeToTest() {
      public void run() {
        new LongArrayAssert(null).excludes(6, 8);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfArrayIsNull(new CodeToTest() {
      public void run() {
        new LongArrayAssert(null).as("A Test")
                                 .excludes(6, 8);
      }
    });
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("the given array of longs should not be null").on(new CodeToTest() {
      public void run() {
        new LongArrayAssert(array).excludes(null);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] the given array of longs should not be null").on(new CodeToTest() {
      public void run() {
        new LongArrayAssert(array).as("A Test")
                                  .excludes(null);
      }
    });
  }

  @Test
  public void should_fail_if_actual_contains_given_values() {
    expectAssertionError("array:<[6, 8]> does not exclude element(s):<[6]>").on(new CodeToTest() {
      public void run() {
        new LongArrayAssert(array).excludes(6);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_contains_given_values() {
    expectAssertionError("[A Test] array:<[6, 8]> does not exclude element(s):<[6]>").on(new CodeToTest() {
      public void run() {
        new LongArrayAssert(array).as("A Test")
                                  .excludes(6);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_contains_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new LongArrayAssert(array).overridingErrorMessage("My custom message")
                                  .excludes(6);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_contains_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new LongArrayAssert(array).as("A Test")
                                  .overridingErrorMessage("My custom message")
                                  .excludes(6);
      }
    });
  }
}
