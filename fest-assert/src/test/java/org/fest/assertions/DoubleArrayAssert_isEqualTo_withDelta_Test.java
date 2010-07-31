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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.doubleArray;
import static org.fest.assertions.Delta.delta;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.*;

/**
 * Tests for <code>{@link DoubleArrayAssert#isEqualTo(double[], Delta)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class DoubleArrayAssert_isEqualTo_withDelta_Test {

  private static double[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = doubleArray(55.03, 4345.91);
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal_using_delta() {
    new DoubleArrayAssert(array).isEqualTo(doubleArray(55.00, 4345.0), delta(1.0));
  }

  @Test
  public void should_pass_if_actual_and_expected_are_exactly_equal() {
    new DoubleArrayAssert(array).isEqualTo(doubleArray(55.03, 4345.91), delta(1.0));
  }

  @Test
  public void should_pass_if_actual_is_same_as_expected() {
    new DoubleArrayAssert(array).isEqualTo(array, delta(0.0));
  }

  @Test
  public void should_pass_if_both_actual_and_expected_are_null() {
    new DoubleArrayAssert(null).isEqualTo(null);
  }

  @Test
  public void should_fail_if_expected_is_null() {
    expectAssertionError("expected:<null> but was:<[55.03, 4345.91]> using delta:<0.1>").on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).isEqualTo(null, delta(0.1));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_expected_is_null() {
    expectAssertionError("[A Test] expected:<null> but was:<[55.03, 4345.91]> using delta:<0.1>").on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).as("A Test")
                                    .isEqualTo(null, delta(0.1));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_expected_is_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).overridingErrorMessage("My custom message")
                                    .isEqualTo(null, delta(0.1));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_expected_is_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).as("A Test")
                                    .overridingErrorMessage("My custom message")
                                    .isEqualTo(null, delta(0.1));
      }
    });
  }

  @Test
  public void should_fail_if_arrays_do_not_have_equal_size() {
    expectAssertionError("expected:<[5323.2]> but was:<[55.03, 4345.91]> using delta:<0.1>").on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).isEqualTo(doubleArray(5323.2), delta(0.1));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_arrays_do_not_have_equal_size() {
    String message = "[A Test] expected:<[5323.2]> but was:<[55.03, 4345.91]> using delta:<0.1>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).as("A Test")
                                    .isEqualTo(doubleArray(5323.2), delta(0.1));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_arrays_do_not_have_equal_size() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).overridingErrorMessage("My custom message")
                                    .isEqualTo(doubleArray(5323.2), delta(0.1));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_arrays_do_not_have_equal_size() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).as("A Test")
                                    .overridingErrorMessage("My custom message")
                                    .isEqualTo(doubleArray(5323.2), delta(0.1));
      }
    });
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_equal() {
    expectAssertionError("expected:<[55.0, 4345.0]> but was:<[55.03, 4345.91]> using delta:<0.1>").on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).isEqualTo(doubleArray(55.0, 4345.0), delta(0.1));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_equal() {
    String message = "[A Test] expected:<[55.0, 4345.0]> but was:<[55.03, 4345.91]> using delta:<0.1>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).as("A Test")
                                    .isEqualTo(doubleArray(55.0, 4345.0), delta(0.1));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).overridingErrorMessage("My custom message")
                                    .isEqualTo(doubleArray(55.0, 4345.0), delta(0.1));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new DoubleArrayAssert(array).as("A Test")
                                    .overridingErrorMessage("My custom message")
                                    .isEqualTo(doubleArray(55.0, 4345.0), delta(0.1));
      }
    });
  }
}
