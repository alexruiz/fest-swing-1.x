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
 * Tests for <code>{@link ListAssert#startsWith(Object...)}</code>.
 *
 * @author Alex Ruiz
 */
public class ListAssert_startsWith_Test {

  private static final Object[] EMPTY_SEQUENCE = new Object[0];
  private static List<String> list;

  @BeforeClass
  public static void setUpOnce() {
    list = list("Anakin", "Leia");
  }

  @Test
  public void should_pass_if_actual_starts_with_sequence() {
    new ListAssert(list).startsWith("Anakin");
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    new ListAssert(list).startsWith("Anakin", "Leia");
  }

  @Test
  public void should_pass_if_both_actual_and_sequence_are_empty() {
    new ListAssert(list()).startsWith(EMPTY_SEQUENCE);
  }

  @Test
  public void should_fail_if_actual_is_smaller_than_sequence() {
    String message = "list:<['Anakin', 'Leia']> does not start with the sequence:<['Anakin', 'Leia', 'Han']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).startsWith("Anakin", "Leia", "Han");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_smaller_than_sequence() {
    String message = "[A Test] list:<['Anakin', 'Leia']> does not start with the sequence:<['Anakin', 'Leia', 'Han']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .startsWith("Anakin", "Leia", "Han");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_smaller_than_sequence() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).overridingErrorMessage("My custom message")
                            .startsWith("Anakin", "Leia", "Han");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_smaller_than_sequence() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .startsWith("Anakin", "Leia", "Han");
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_not_empty_and_sequence_is_empty() {
    expectAssertionError("list:<['Anakin', 'Leia']> does not start with the sequence:<[]>").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).startsWith(EMPTY_SEQUENCE);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_not_empty_and_sequence_is_empty() {
    String message = "[A Test] list:<['Anakin', 'Leia']> does not start with the sequence:<[]>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .startsWith(EMPTY_SEQUENCE);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_not_empty_and_sequence_is_empty() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).overridingErrorMessage("My custom message")
                            .startsWith(EMPTY_SEQUENCE);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_not_empty_and_sequence_is_empty() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .startsWith(EMPTY_SEQUENCE);
      }
    });
  }

  @Test
  public void should_fail_if_actual_does_not_start_with_sequence() {
    expectAssertionError("list:<['Anakin', 'Leia']> does not start with the sequence:<['Leia', 'Anakin']>").on(
      new CodeToTest() {
        public void run() {
          new ListAssert(list).startsWith("Leia", "Anakin");
        }
      });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_does_not_start_with_sequence() {
    String message = "[A Test] list:<['Anakin', 'Leia']> does not start with the sequence:<['Leia', 'Anakin']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .startsWith("Leia", "Anakin");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_start_with_sequence() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).overridingErrorMessage("My custom message")
                            .startsWith("Leia", "Anakin");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_start_with_sequence() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .startsWith("Leia", "Anakin");
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).startsWith("Gandalf", "Frodo", "Sam");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).as("A Test")
                            .startsWith("Gandalf", "Frodo", "Sam");
      }
    });
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("The given array should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        new ListAssert(emptyList()).startsWith(objects);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] The given array should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        new ListAssert(emptyList()).as("A Test")
                                   .startsWith(objects);
      }
    });
  }
}
