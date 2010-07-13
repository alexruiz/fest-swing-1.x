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
import org.junit.*;

/**
 * Tests for <code>{@link ListAssert#containsSequence(Object...)}</code>.
 *
 * @author Alex Ruiz
 */
public class ListAssert_containsSequence_Test {

  private static List<String> list;

  @BeforeClass
  public static void setUpOnce() {
    list = list("Anakin", "Leia", "Han");
  }

  @Test
  public void should_pass_if_actual_contains_sequence() {
    new ListAssert(list).containsSequence("Anakin", "Leia")
                        .containsSequence("Leia", "Han");
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    new ListAssert(list).containsSequence("Anakin", "Leia", "Han");
  }

  @Test
  public void should_pass_if_both_actual_and_sequence_are_empty() {
    Object[] sequence = new Object[0];
    new ListAssert(emptyList()).containsSequence(sequence);
  }

  @Test
  public void should_pass_if_actual_is_not_empty_and_expected_is_empty() {
    Object[] sequence = new Object[0];
    new ListAssert(list).containsSequence(sequence);
  }

  @Test
  public void should_fail_if_actual_does_not_contain_sequence() {
    String message = "list:<['Anakin', 'Leia', 'Han']> does not contain the sequence:<['Ben']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).containsSequence("Ben");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_sequence() {
    String message = "[A Test] list:<['Anakin', 'Leia', 'Han']> does not contain the sequence:<['Ben']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .containsSequence("Ben");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_sequence() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).overridingErrorMessage("My custom message")
                            .containsSequence("Ben");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_sequence() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .containsSequence("Ben");
      }
    });
  }

  @Test
  public void should_fail_if_actual_does_not_contain_some_elements_in_the_sequence() {
    String message = "list:<['Anakin', 'Leia', 'Han']> does not contain the sequence:<['Anakin', 'Ben']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).containsSequence("Anakin", "Ben");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_some_elements_in_the_sequence() {
    String message = "[A Test] list:<['Anakin', 'Leia', 'Han']> does not contain the sequence:<['Anakin', 'Ben']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .containsSequence("Anakin", "Ben");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_some_elements_in_the_sequence() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).overridingErrorMessage("My custom message")
                            .containsSequence("Anakin", "Ben");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_some_elements_in_the_sequence() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .containsSequence("Anakin", "Ben");
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).containsSequence("Gandalf", "Frodo", "Sam");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).as("A Test")
                            .containsSequence("Gandalf", "Frodo", "Sam");
      }
    });
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("The given array should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        new ListAssert(emptyList()).containsSequence(objects);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] The given array should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        new ListAssert(emptyList()).as("A Test")
                                   .containsSequence(objects);
      }
    });
  }
}
