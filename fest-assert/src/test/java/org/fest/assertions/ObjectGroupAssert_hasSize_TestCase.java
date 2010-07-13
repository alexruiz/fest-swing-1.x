/*
 * Created on Jan 10, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.objectArray;
import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.*;

/**
 * Base class for testing implementations of <code>{@link ObjectGroupAssert#hasSize(int)}</code>.
 * @param <T> The type supported by the implementation of the {@code ObjectGroupAssert} to test.
 *
 * @author Yvonne Wang
 */
public abstract class ObjectGroupAssert_hasSize_TestCase<T> extends ObjectGroupAssert_TestCase<T> implements
    Assert_hasSize_TestCase {

  private static Object[] actualValues;

  @BeforeClass
  public static void setUpOnce() {
    actualValues = objectArray("Gandalf", "Frodo", "Sam");
  }

  private T actual;
  private GroupAssert<T> assertions;

  @Before
  public final void setUp() {
    actual = actualFrom(actualValues);
    assertions = assertionsFor(actual);
  }

  @Test
  public final void should_pass_if_actual_has_expected_size() {
    assertions.hasSize(3);
  }

  @Test
  public final void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public final void run() {
        assertionsFor(null).hasSize(0);
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public final void run() {
        assertionsFor(null).as("A Test")
                           .hasSize(0);
      }
    });
  }

  @Test
  public final void should_fail_if_actual_does_not_have_expected_size() {
    String message = "expected size:<2> but was:<3> for <['Gandalf', 'Frodo', 'Sam']>";
    expectAssertionError(message).on(new CodeToTest() {
      public final void run() {
        assertions.hasSize(2);
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_actual_does_not_have_expected_size() {
    String message = "[A Test] expected size:<2> but was:<3> for <['Gandalf', 'Frodo', 'Sam']>";
    expectAssertionError(message).on(new CodeToTest() {
      public final void run() {
        assertions.as("A Test")
                  .hasSize(2);
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_if_actual_does_not_have_expected_size() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public final void run() {
        assertions.overridingErrorMessage("My custom message")
                  .hasSize(2);
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_does_not_have_expected_size() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public final void run() {
        assertions.as("A Test")
                  .overridingErrorMessage("My custom message")
                  .hasSize(2);
      }
    });
  }
}
