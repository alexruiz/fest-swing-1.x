/*
 * Created on Jul 1, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.ArrayFactory.objectArray;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.*;

/**
 * Base class for testing implementations of <code>{@link ObjectGroupAssert#isNullOrEmpty()}</code>.
 * @param <T> The type supported by the implementation of the {@code ObjectGroupAssert} to test.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public abstract class ObjectGroupAssert_isNullOrEmpty_TestCase<T> extends ObjectGroupAssert_TestCase<T> implements
    GroupAssert_isNullOrEmpty_TestCase {

  private static Object[] actualValues;
  private static Object[] emptyValues;

  @BeforeClass
  public static void setUpOnce() {
    actualValues = objectArray(8);
    emptyValues = new Object[0];
  }

  private T actual;
  private GroupAssert<T> assertions;

  @Before
  public final void setUp() {
    actual = actualFrom(actualValues);
    assertions = assertionsFor(actual);
  }

  @Test
  public final void should_pass_if_actual_is_null() {
    assertionsFor(null).isNullOrEmpty();
  }

  @Test
  public final void should_pass_if_actual_is_empty() {
    assertionsFor(actualFrom(emptyValues)).isNullOrEmpty();
  }

  @Test
  public final void should_fail_if_actual_has_content() {
    expectAssertionError("expecting null or empty, but was:<[8]>").on(new CodeToTest() {
      public void run() {
        assertions.isNullOrEmpty();
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_actual_has_content() {
    expectAssertionError("[A Test] expecting null or empty, but was:<[8]>").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .isNullOrEmpty();
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_if_actual_has_content() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message")
                  .isNullOrEmpty();
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_has_content() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .overridingErrorMessage("My custom message")
                  .isNullOrEmpty();
      }
    });
  }
}
