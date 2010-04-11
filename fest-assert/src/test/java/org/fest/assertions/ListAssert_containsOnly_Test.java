/*
 * Created on Mar 29, 2009
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

import static java.util.Collections.emptyList;
import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Collections.list;

import java.util.List;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ListAssert#containsOnly(Object...)}</code>.
 *
 * @author Alex Ruiz
 */
public class ListAssert_containsOnly_Test implements GroupAssert_containsOnly_TestCase {

  private static List<String> list;

  @BeforeClass
  public static void setUpOnce() {
    list = list("Gandalf", "Frodo", "Sam");
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values() {
    new ListAssert(list).containsOnly("Gandalf", "Frodo", "Sam");
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values_in_different_order() {
    new ListAssert(list).containsOnly("Sam", "Frodo", "Gandalf");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualListIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).containsOnly("Gandalf", "Frodo", "Sam");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualListIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).as("A Test")
                            .containsOnly("Gandalf", "Frodo", "Sam");
      }
    });
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("the given array of objects should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        new ListAssert(emptyList()).containsOnly(objects);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] the given array of objects should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        new ListAssert(emptyList()).as("A Test")
                                   .containsOnly(objects);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("list:<[]> does not contain element(s):<['Sam']>").on(new CodeToTest() {
      public void run() {
        new ListAssert(emptyList()).containsOnly("Sam");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("[A Test] list:<[]> does not contain element(s):<['Sam']>").on(new CodeToTest() {
      public void run() {
        new ListAssert(emptyList()).as("A Test")
                                   .containsOnly("Sam");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(emptyList()).overridingErrorMessage("My custom message")
                                   .containsOnly("Sam");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(emptyList()).as("A Test")
                                   .overridingErrorMessage("My custom message")
                                   .containsOnly("Sam");
      }
    });
  }

  @Test
  public void should_fail_if_actual_contains_unexpected_values() {
    String message = "unexpected element(s):<['Sam']> in list:<['Gandalf', 'Frodo', 'Sam']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).containsOnly("Gandalf", "Frodo");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_contains_unexpected_values() {
    String message = "[A Test] unexpected element(s):<['Sam']> in list:<['Gandalf', 'Frodo', 'Sam']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .containsOnly("Gandalf", "Frodo");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_contains_unexpected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).overridingErrorMessage("My custom message")
                            .containsOnly("Gandalf", "Frodo");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_contains_unexpected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .containsOnly("Gandalf", "Frodo");
      }
    });
  }

  @Test
  public void should_fail_if_actual_does_not_contain_all_the_expected_values() {
    String message = "list:<['Gandalf', 'Frodo', 'Sam']> does not contain element(s):<['Meriadoc']>";
    expectAssertionError(message).on(new CodeToTest() {
        public void run() {
          new ListAssert(list).containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
        }
      });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_all_the_expected_values() {
    String message = "[A Test] list:<['Gandalf', 'Frodo', 'Sam']> does not contain element(s):<['Meriadoc']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).overridingErrorMessage("My custom message")
                            .containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .containsOnly("Gandalf", "Frodo", "Sam", "Meriadoc");
      }
    });
  }


}
