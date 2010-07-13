/*
 * Created on Jun 30, 2010
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
 * Base class for testing implementations of <code>{@link ObjectGroupAssert#excludes(Object...)}</code>.
 * @param <T> The type supported by the implementation of the {@code ObjectGroupAssert} to test.
 *
 * @author Yvonne Wang
 */
public abstract class ObjectGroupAssert_excludes_TestCase<T> extends ObjectGroupAssert_TestCase<T> implements
    GroupAssert_excludes_TestCase {

  private static Object[] actualValues;

  @BeforeClass
  public static void setUpOnce() {
    actualValues = objectArray("Luke", "Leia");
  }

  private T actual;
  private ObjectGroupAssert<T> assertions;

  @Before
  public final void setUp() {
    actual = actualFrom(actualValues);
    assertions = assertionsFor(actual);
  }

  @Test
  public final void should_pass_if_actual_excludes_given_value() {
    assertions.excludes("Anakin");
  }

  @Test
  public final void should_pass_if_actual_excludes_given_values() {
    assertions.excludes("Han", "Yoda");
  }

  @Test
  public final void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        assertionsFor(null).excludes("Luke");
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        assertionsFor(null).as("A Test")
                           .excludes("Luke");
      }
    });
  }

  @Test
  public final void should_throw_error_if_expected_is_null() {
    expectNullPointerException("The given array should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        assertions.excludes(objects);
      }
    });
  }

  @Test
  public final void should_throw_error_and_display_description_of_assertion_if_expected_is_null() {
    expectNullPointerException("[A Test] The given array should not be null").on(new CodeToTest() {
      public void run() {
        Object[] objects = null;
        assertions.as("A Test")
                  .excludes(objects);
      }
    });
  }

  @Test
  public final void should_fail_if_actual_contains_given_values() {
    expectAssertionError("<['Luke', 'Leia']> does not exclude element(s):<['Luke']>").on(new CodeToTest() {
      public void run() {
        assertions.excludes("Luke");
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_actual_contains_given_values() {
    String message = "[A Test] <['Luke', 'Leia']> does not exclude element(s):<['Luke']>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .excludes("Luke");
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_if_actual_contains_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message")
                  .excludes("Luke");
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_contains_given_values() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .overridingErrorMessage("My custom message")
                  .excludes("Luke");
      }
    });
  }
}
