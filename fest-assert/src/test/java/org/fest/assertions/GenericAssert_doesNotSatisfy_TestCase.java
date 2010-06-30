/*
 * Created on Apr 19, 2010
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
import static org.fest.assertions.Formatter.format;
import static org.fest.assertions.NotNull.notNull;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Strings.concat;

import org.fest.test.CodeToTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Base class for testing <code>{@link GenericAssert#doesNotSatisfy(Condition)}</code>.
 * @param <T> The type supported by the implementation of the {@code GenericAssert} to test.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public abstract class GenericAssert_doesNotSatisfy_TestCase<T> extends GenericAssert_TestCase<T> implements
    GenericAssert_doesNotSatisfy_orAlias_TestCase {

  private GenericAssert<T> assertions;
  private T actual;
  private Condition<T> notNull;

  @Before
  public final void setUp() {
    actual = notNullValue();
    assertions = assertionsFor(actual);
    notNull = notNull();
  }

  @Test
  public final void should_pass_if_condition_is_not_satisfied() {
    assertionsFor(null).doesNotSatisfy(notNull);
  }

  @Test
  public final void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        assertions.doesNotSatisfy(null);
      }
    });
  }

  @Test
  public final void should_fail_if_condition_is_satisfied() {
    String msg = concat("actual value:<", format(actual), "> should not satisfy condition:<NotNull>");
    expectAssertionError(msg).on(new CodeToTest() {
      public void run() {
        assertions.doesNotSatisfy(notNull);
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_condition_is_satisfied() {
    String msg = concat("[A Test] actual value:<", format(actual), "> should not satisfy condition:<NotNull>");
    expectAssertionError(msg).on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .doesNotSatisfy(notNull);
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    String msg = concat("actual value:<", format(actual), "> should not satisfy condition:<Not Null>");
    expectAssertionError(msg).on(new CodeToTest() {
      public void run() {
        assertions.doesNotSatisfy(notNull.as("Not Null"));
      }
    });
  }

  @Test
  public final void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    String msg = concat("[A Test] actual value:<", format(actual), "> should not satisfy condition:<Not Null>");
    expectAssertionError(msg).on(new CodeToTest() {
      public void run() {
        assertions.as("A Test")
                  .doesNotSatisfy(notNull.as("Not Null"));
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message")
                  .doesNotSatisfy(notNull);
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.as("A Test").overridingErrorMessage("My custom message")
                  .doesNotSatisfy(notNull);
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertions.overridingErrorMessage("My custom message")
                  .doesNotSatisfy(notNull.as("Not Null"));
      }
    });
  }
}