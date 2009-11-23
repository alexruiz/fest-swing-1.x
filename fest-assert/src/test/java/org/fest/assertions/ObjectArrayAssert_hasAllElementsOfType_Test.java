/*
 * Created on Oct 27, 2009
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
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.objectArray;
import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ObjectArrayAssert#hasAllElementsOfType(Class)}</code>.
 *
 * @author Alex Ruiz
 */
public class ObjectArrayAssert_hasAllElementsOfType_Test {

  private static Object[] array;

  @BeforeClass
  public static void setUpOnce() {
    array = objectArray(6, 8);
  }

  @Test
  public void should_pass_if_actual_has_all_elements_of_the_expected_type() {
    new ObjectArrayAssert(array).hasAllElementsOfType(Integer.class);
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfArrayIsNull(new CodeToTest() {
      public void run() {
        Object[] actual = null;
        new ObjectArrayAssert(actual).hasAllElementsOfType(Integer.class);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfArrayIsNull(new CodeToTest() {
      public void run() {
        Object[] actual = null;
        new ObjectArrayAssert(actual).as("A Test")
                                     .hasAllElementsOfType(Integer.class);
      }
    });
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectErrorIfTypeIsNull(new CodeToTest() {
      public void run() {
        new ObjectArrayAssert(1, 2).hasAllElementsOfType(null);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectErrorWithDescriptionIfTypeIsNull(new CodeToTest() {
      public void run() {
        new ObjectArrayAssert(1, 2).as("A Test")
                                   .hasAllElementsOfType(null);
      }
    });
  }

  @Test
  public void should_fail_if_elements_in_actual_do_not_belong_to_expected_type() {
    expectAssertionError("not all elements in array:<[6, 8]> belong to the type:<java.lang.String>").on(new CodeToTest() {
      public void run() {
        new ObjectArrayAssert(array).hasAllElementsOfType(String.class);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_elements_in_actual_do_not_belong_to_expected_type() {
    String message = "[A Test] not all elements in array:<[6, 8]> belong to the type:<java.lang.String>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ObjectArrayAssert(array).as("A Test")
                                    .hasAllElementsOfType(String.class);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_elements_in_actual_do_not_belong_to_expected_type() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ObjectArrayAssert(array).overridingErrorMessage("My custom message")
                                    .hasAllElementsOfType(String.class);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_elements_in_actual_do_not_belong_to_expected_type() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ObjectArrayAssert(array).as("A Test")
                                    .overridingErrorMessage("My custom message")
                                    .hasAllElementsOfType(String.class);
      }
    });
  }
}
