/*
 * Created on Sep 4, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Formatter.format;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Strings.concat;

import org.fest.test.CodeToTest;
import org.junit.*;

/**
 * Base class for testing <code>{@link GenericAssert#isNull()}</code>.
 * @param <T> The type supported by the implementation of the {@code GenericAssert} to test.
 *
 * @author Alex Ruiz
 */
public abstract class GenericAssert_isNull_TestCase<T> extends GenericAssert_TestCase<T> {

  private T actual;
  private GenericAssert<T> assertions;

  @Before
  public final void setUp() {
    actual = notNullValue();
    assertions = assertionsFor(actual);
  }

  @Test
  public final void should_pass_if_actual_is_null() {
    assertionsFor(null).isNull();
  }

  @Test
  public final void should_fail_if_actual_is_not_null() {
    expectAssertionError(concat("<", format(actual), "> should be null")).on(new CodeToTest() {
      public void run() {
        assertions.isNull();
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_actual_is_not_null() {
    expectAssertionError(concat("[A Test] <", format(actual), "> should be null")).on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .isNull();
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_if_actual_is_not_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message")
                  .isNull();
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_not_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .overridingErrorMessage("My custom message")
                  .isNull();
      }
    });
  }
}