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

import static org.fest.assertions.ArrayFactory.byteArray;
import static org.fest.assertions.CommonFailures.*;
import static org.fest.assertions.EmptyArrays.emptyByteArray;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link ByteArrayAssert#containsOnly(byte...)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ByteArrayAssert_containsOnly_Test implements GroupAssert_containsOnly_TestCase {

  @Test
  public void should_pass_if_actual_contains_only_given_values() {
    new ByteArrayAssert(byteArray(8)).containsOnly(byteArray(8));
  }

  @Test
  public void should_pass_if_actual_contains_only_given_values_in_different_order() {
    new ByteArrayAssert(byteArray(8, 6)).containsOnly(byteArray(6, 8));
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(null).containsOnly(byteArray(7));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(null).as("A Test")
                                 .containsOnly(byteArray(7));
      }
    });
  }

  @Test
  public void should_throw_error_if_expected_is_null() {
    expectNullPointerException("The given array should not be null").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(emptyByteArray()).containsOnly(null);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] The given array should not be null").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(emptyByteArray()).as("A Test")
                                             .containsOnly(null);
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("<[]> does not contain element(s):<[7]>").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(emptyByteArray()).containsOnly(byteArray(7));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("[A Test] <[]> does not contain element(s):<[7]>").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(emptyByteArray()).as("A Test")
                                             .containsOnly(byteArray(7));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(emptyByteArray()).overridingErrorMessage("My custom message")
                                             .containsOnly(byteArray(7));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_empty_and_expecting_at_least_one_element() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(emptyByteArray()).as("A Test")
                                             .overridingErrorMessage("My custom message")
                                             .containsOnly(byteArray(7));
      }
    });
  }

  @Test
  public void should_fail_if_actual_contains_unexpected_values() {
    expectAssertionError("unexpected element(s):<[6]> in <[8, 6]>").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(byteArray(8, 6)).containsOnly(byteArray(8));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_contains_unexpected_values() {
    expectAssertionError("[A Test] unexpected element(s):<[6]> in <[8, 6]>").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(byteArray(8, 6)).as("A Test")
                                            .containsOnly(byteArray(8));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_contains_unexpected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(byteArray(8, 6)).overridingErrorMessage("My custom message")
                                            .containsOnly(byteArray(8));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_contains_unexpected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(byteArray(8, 6)).as("A Test")
                                            .overridingErrorMessage("My custom message")
                                            .containsOnly(byteArray(8));
      }
    });
  }

  @Test
  public void should_fail_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("<[8, 6]> does not contain element(s):<[7]>").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(byteArray(8, 6)).containsOnly(byteArray(7));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("[A Test] <[8, 6]> does not contain element(s):<[7]>").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(byteArray(8, 6)).as("A Test")
                                            .containsOnly(byteArray(7));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(byteArray(8, 6)).overridingErrorMessage("My custom message")
                                            .containsOnly(byteArray(7));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_contain_all_the_expected_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ByteArrayAssert(byteArray(8, 6)).as("A Test")
                                            .overridingErrorMessage("My custom message")
                                            .containsOnly(byteArray(7));
      }
    });
  }
}
