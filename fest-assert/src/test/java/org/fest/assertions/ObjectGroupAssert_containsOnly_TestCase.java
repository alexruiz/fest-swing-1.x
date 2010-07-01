/*
 * Created on Jun 29, 2010
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

import static org.fest.assertions.ArrayFactory.objectArray;
import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.*;

/**
 * Base class for testing implementations of <code>{@link ObjectGroupAssert#containsOnly(Object...)}</code>.
 * @param <T> The type supported by the implementation of the {@code ObjectGroupAssert} to test.
 *
 * @author Yvonne Wang
 */
public abstract class ObjectGroupAssert_containsOnly_TestCase<T> extends ObjectGroupAssert_TestCase<T> implements
    GroupAssert_containsOnly_TestCase {

  private static Object[] actualValues;
  private static Object[] emptyValues;

  @BeforeClass
  public static void setUpOnce() {
    actualValues = objectArray("Gandalf", "Frodo", "Sam");
    emptyValues = new Object[0];
  }

  private T actual;
  private ObjectGroupAssert<T> assertions;
  private T empty;

  @Before
  public final void setUp() {
    actual = actualFrom(actualValues);
    assertions = assertionsFor(actual);
    empty = actualFrom(emptyValues);
  }

  @Test
  public final void should_pass_if_actual_contains_only_given_values() {
    assertions.containsOnly("Gandalf", "Frodo", "Sam");
  }

  @Test
  public final void should_pass_if_actual_contains_only_given_values_in_different_order() {
    assertions.containsOnly("Sam", "Frodo", "Gandalf");
  }

  @Test
  public final void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        assertionsFor(null).containsOnly("Gandalf", "Frodo", "Sam");
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        assertionsFor(null).as("A Test")
                           .containsOnly("Gandalf", "Frodo", "Sam");
      }
    });
  }

  @Test
  public final void should_throw_error_if_expected_is_null() {
    expectNullPointerException("the given array of objects should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        assertions.containsOnly(objects);
      }
    });
  }

  @Test
  public final void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] the given array of objects should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        assertions.as("A Test")
                  .containsOnly(objects);
      }
    });
  }

  @Test
  public final void should_fail_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("<[]> does not contain element(s):<['Sam']>").on(new CodeToTest() {
      public void run() {
        assertionsFor(empty).containsOnly("Sam");
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("[A Test] <[]> does not contain element(s):<['Sam']>").on(new CodeToTest() {
      public void run() {
        assertionsFor(empty).as("A Test")
                                  .containsOnly("Sam");
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message")
                  .containsOnly("Sam");
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .overridingErrorMessage("My custom message")
                  .containsOnly("Sam");
      }
    });
  }

  @Test
  public final void should_fail_if_actual_contains_unexpected_values() {
    String message = "unexpected element(s):<['Sam']> in:<['Gandalf', 'Frodo', 'Sam']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        assertions.containsOnly("Gandalf", "Frodo");
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_actual_contains_unexpected_values() {
    String message = "[A Test] unexpected element(s):<['Sam']> in:<['Gandalf', 'Frodo', 'Sam']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .containsOnly("Gandalf", "Frodo");
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_if_actual_contains_unexpected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message")
                  .containsOnly("Gandalf", "Frodo");
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_contains_unexpected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .overridingErrorMessage("My custom message")
                  .containsOnly("Gandalf", "Frodo");
      }
    });
  }

  @Test
  public final void should_fail_if_actual_does_not_contain_all_the_expected_values() {
    String message = "<['Gandalf', 'Frodo', 'Sam']> does not contain element(s):<['Meriadoc']>";
    expectAssertionError(message).on(new CodeToTest() {
        public void run() {
          assertions.containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
        }
      });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_all_the_expected_values() {
    String message = "[A Test] <['Gandalf', 'Frodo', 'Sam']> does not contain element(s):<['Meriadoc']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message")
                  .containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .overridingErrorMessage("My custom message")
                  .containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
      }
    });
  }
}
