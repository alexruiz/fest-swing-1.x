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

import static java.lang.String.valueOf;
import static org.fest.assertions.CommonFailures.expectErrorIfConditionIsNull;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Arrays.format;
import static org.fest.util.Strings.*;

import org.fest.test.CodeToTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Base class for testing {@link org.fest.assertions.GenericAssert#doesNotSatisfy(Condition)}.
 * <p>
 * This class implements the algorithms which must be performed to test <code>doesNotSatisfy</code> as template methods
 * and uses implementations of the abstract methods in subclasses to derive concrete tests.
 * </p>
 * @param <T> The type supported by the implementation of the {@code GenericAssert} to test.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public abstract class GenericAssert_doesNotSatisfy_TestCase<T> {

  private GenericAssert<T> assertObject;
  private T actual;

  @Before
  public final void setUp() {
    assertObject = assertObject();
    actual = assertObject.actual;
  }

  protected abstract GenericAssert<T> assertObject();

  @Test
  public final void should_pass_if_condition_is_not_satisfied() {
    assertObjectWithNullTarget().doesNotSatisfy(notNull());
  }

  protected abstract GenericAssert<T> assertObjectWithNullTarget();

  @Test
  public final void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        assertObject.doesNotSatisfy(null);
      }
    });
  }

  @Test
  public final void should_fail_if_condition_is_satisfied() {
    String msg = concat("actual value:<", actualAsString(), "> should not satisfy condition:<NotNull>");
    expectAssertionError(msg).on(new CodeToTest() {
      public void run() {
        assertObject.doesNotSatisfy(notNull());
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_assertion_if_condition_is_satisfied() {
    String msg = concat("[A Test] actual value:<", actualAsString(), "> should not satisfy condition:<NotNull>");
    expectAssertionError(msg).on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test")
                    .doesNotSatisfy(notNull());
      }
    });
  }

  @Test
  public final void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    String msg = concat("actual value:<", actualAsString(), "> should not satisfy condition:<Not Null>");
    expectAssertionError(msg).on(new CodeToTest() {
      public void run() {
        assertObject.doesNotSatisfy(notNull().as("Not Null"));
      }
    });
  }

  @Test
  public final void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    String msg = concat("[A Test] actual value:<", actualAsString(), "> should not satisfy condition:<Not Null>");
    expectAssertionError(msg).on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test")
                    .doesNotSatisfy(notNull().as("Not Null"));
      }
    });
  }

  protected String actualAsString() {
    if (actual.getClass().isArray()) return format(actual);
    if (actual instanceof String) return quote((String)actual);
    return valueOf(actual);
  }

  @Test
  public final void should_fail_with_custom_message_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.overridingErrorMessage("My custom message")
                    .doesNotSatisfy(notNull());
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.as("A Test").overridingErrorMessage("My custom message")
                    .doesNotSatisfy(notNull());
      }
    });
  }

  @Test
  public final void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertObject.overridingErrorMessage("My custom message")
                    .doesNotSatisfy(notNull().as("Not Null"));
      }
    });
  }

  protected final NotNull<T> notNull() {
    return NotNull.notNull();
  }

  protected final T actual() { return actual; }
}