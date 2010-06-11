/*
 * Created on Apr 16, 2010
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

import static org.fest.assertions.CommonFailures.expectErrorIfConditionIsNull;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Base class for testing {@link org.fest.assertions.GenericAssert#is(Condition)}.
 * <p>
 * This class implements the algorithms which must be performed to test <code>is</code> as template methods and uses
 * implementations of the abstract methods in subclasses to derive concrete tests.
 * </p>
 * @param <T> The type supported by the implementation of the {@code GenericAssert} to test.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public abstract class GenericAssert_is_TestCase<T> implements GenericAssert_satisfies_orAlias_TestCase {

  private GenericAssert<T> assertObject;
  private T actual;

  @Before
  public final void setUp() {
    assertObject = assertObjectWithNullTarget();
    actual = assertObject.actual;
  }

  protected abstract GenericAssert<T> assertObjectWithNullTarget();

  @Test
  public void should_pass_if_condition_is_satisfied() {
    assertObject().is(notNull());
  }

  protected abstract GenericAssert<T> assertObject();

  @Test
  public void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        assertObject.is(null);
      }
    });
  }

  @Test
  public void should_fail_if_condition_is_not_satisfied() {
    expectAssertionError("actual value:<null> should be:<NotNull>").on(new CodeToTest() {
      public void run() {
        assertObject.is(notNull());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_condition_is_not_satisfied() {
    expectAssertionError("[A Test] actual value:<null> should be:<NotNull>").on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test")
                    .is(notNull());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_condition_if_condition_is_not_satisfied() {
    expectAssertionError("actual value:<null> should be:<non-null>").on(new CodeToTest() {
      public void run() {
        assertObject.is(notNull().as("non-null"));
      }
    });
  }

  @Test
  public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_not_satisfied() {
    expectAssertionError("[A Test] actual value:<null> should be:<non-null>").on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test")
          .is(notNull().as("non-null"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.overridingErrorMessage("My custom message").is(notNull());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.overridingErrorMessage("My custom message")
                    .is(notNull().as("non-null"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test")
                    .overridingErrorMessage("My custom message")
                    .is(notNull());
      }
    });
  }

  protected final NotNull<T> notNull() {
    return NotNull.notNull();
  }

  protected final T actual() { return actual; }
}
