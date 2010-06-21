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
import static org.fest.assertions.NotNull.notNull;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Base class for testing <code>{@link GenericAssert#is(Condition)}</code>.
 * <p>
 * This class implements the algorithms which must be performed to test <code>is</code> as template methods and uses
 * implementations of the abstract methods in subclasses to derive concrete tests.
 * </p>
 * @param <T> The type supported by the implementation of the {@code GenericAssert} to test.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public abstract class GenericAssert_is_TestCase<T> extends GenericAssert_TestCase<T> implements
    GenericAssert_satisfies_orAlias_TestCase {

  private GenericAssert<T> assertions;
  private Condition<T> notNull;

  @Before
  public final void setUp() {
    assertions = assertionsFor(null);
    notNull = notNull();
  }

  @Test
  public final void should_pass_if_condition_is_satisfied() {
    assertionsFor(notNullValue()).is(notNull);
  }

  @Test
  public final void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        assertions.is(null);
      }
    });
  }

  @Test
  public final void should_fail_if_condition_is_not_satisfied() {
    expectAssertionError("actual value:<null> should be:<NotNull>").on(new CodeToTest() {
      public void run() {
        assertions.is(notNull);
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_condition_is_not_satisfied() {
    expectAssertionError("[A Test] actual value:<null> should be:<NotNull>").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .is(notNull);
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_condition_if_condition_is_not_satisfied() {
    expectAssertionError("actual value:<null> should be:<non-null>").on(new CodeToTest() {
      public void run() {
        assertions.is(notNull.as("non-null"));
      }
    });
  }

  @Test
  public final void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_not_satisfied() {
    expectAssertionError("[A Test] actual value:<null> should be:<non-null>").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .is(notNull.as("non-null"));
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message")
                  .is(notNull);
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message")
                  .is(notNull.as("non-null"));
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_assertion_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .overridingErrorMessage("My custom message")
                  .is(notNull);
      }
    });
  }
}
