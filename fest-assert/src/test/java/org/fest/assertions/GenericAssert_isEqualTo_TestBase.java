/*
 * Created on Apr 23, 2010
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

import static org.fest.assertions.Formatter.format;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.*;


/**
 * Base class for testing <code>{@link GenericAssert#isEqualTo(Object)}.</code>
 * <p>
 * This class implements the algorithms which must be performed to test <code>isEqualTo</code> as template methods and
 * uses implementations of the abstract methods in subclasses to derive concrete tests.
 * </p>
 * @param <T> The type supported by the implementation of the {@code GenericAssert} to test.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public abstract class GenericAssert_isEqualTo_TestBase<T> implements GenericAssert_isEqualTo_TestCase {

  private GenericAssert<T> assertObject;
  private T actual;
  private T expected;

  @Before
  public final void setUp() {
    assertObject = assertObject();
    actual = assertObject.actual;
    expected = expected();
  }

  protected abstract GenericAssert<T> assertObject();

  protected abstract T expected();

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    assertObject.isEqualTo(actual);
  }

  @Test
  public void should_pass_if_both_actual_and_expected_are_null() {
    assertObjectWithNullTarget().isEqualTo(null);
  }

  protected abstract GenericAssert<T> assertObjectWithNullTarget();

  @Test
  public void should_fail_if_actual_and_expected_are_not_equal() {
    expectAssertionError(errorMessage()).on(new CodeToTest() {
      public void run() {
        assertObject.isEqualTo(expected);
      }
    });
  }

  private String errorMessage() {
    return errorMessage("");
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError(errorMessage("[A Test]")).on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test").isEqualTo(expected);
      }
    });
  }

  private String errorMessage(String description) {
    return new ComparisonFailure(description, format(expected), format(actual)).getMessage();
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.overridingErrorMessage("My custom message")
                    .isEqualTo(expected);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test")
                    .overridingErrorMessage("My custom message")
                    .isEqualTo(expected);
      }
    });
  }
}
