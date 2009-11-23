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
import static org.fest.assertions.EmptyArrays.emptyIntArray;
import static org.fest.assertions.ArrayFactory.intArray;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link IntArrayAssert#containsOnly(int...)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class IntArrayAssert_containsOnly_Test implements GroupAssert_containsOnly_TestCase {

  private static int[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = intArray(6, 8);
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values() {
    new IntArrayAssert(array).containsOnly(6, 8);
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values_in_different_order() {
    new IntArrayAssert(array).containsOnly(8, 6);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfArrayIsNull(new CodeToTest() {
      public void run() {
        new IntArrayAssert(null).containsOnly(intArray(10, 2));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfArrayIsNull(new CodeToTest() {
      public void run() {
        new IntArrayAssert(null).as("A Test")
                                .containsOnly(intArray(10, 2));
      }
    });
  }


  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("the given array of ints should not be null").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(emptyIntArray()).containsOnly(null);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] the given array of ints should not be null").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(emptyIntArray()).as("A Test")
                                           .containsOnly(null);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("array:<[]> does not contain element(s):<[10, 2]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(emptyIntArray()).containsOnly(intArray(10, 2));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("[A Test] array:<[]> does not contain element(s):<[10, 2]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(emptyIntArray()).as("A Test")
                                           .containsOnly(intArray(10, 2));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(emptyIntArray()).overridingErrorMessage("My custom message")
                                           .containsOnly(intArray(10, 2));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(emptyIntArray()).as("A Test")
                                           .overridingErrorMessage("My custom message")
                                           .containsOnly(intArray(10, 2));
      }
    });
  }

  @Test
  public void should_fail_if_actual_contains_unexpected_values() {
    expectAssertionError("unexpected element(s):<[8]> in array:<[6, 8]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(array).containsOnly(intArray(6));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_contains_unexpected_values() {
    expectAssertionError("[A Test] unexpected element(s):<[8]> in array:<[6, 8]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(array).as("A Test")
                                 .containsOnly(intArray(6));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_contains_unexpected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(array).as("A Test")
                                 .overridingErrorMessage("My custom message")
                                 .containsOnly(intArray(6));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_contains_unexpected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(array).as("A Test")
                                 .overridingErrorMessage("My custom message")
                                 .containsOnly(intArray(6));
      }
    });
  }

  @Test
  public void should_fail_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("array:<[6, 8]> does not contain element(s):<[10]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(array).containsOnly(intArray(10));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("[A Test] array:<[6, 8]> does not contain element(s):<[10]>").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(array).as("A Test")
                                 .containsOnly(intArray(10));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(array).overridingErrorMessage("My custom message")
                                 .containsOnly(intArray(10));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new IntArrayAssert(array).as("A Test")
                                 .overridingErrorMessage("My custom message")
                                 .containsOnly(intArray(10));
      }
    });
  }


}
