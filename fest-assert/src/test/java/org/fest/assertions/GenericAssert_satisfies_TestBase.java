/*
 * Created on 2010-4-16
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

import static org.fest.assertions.CommonFailures.expectErrorIfConditionIsNull;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.junit.Test;
import org.fest.test.CodeToTest;

/**
 * Base class for testing {@link org.fest.assertions.GenericAssert#satisfies(Condition)}.
 * <p/>
 * This class implements the algorithms which must be performed to test <code>satisfies</code> as template methods and
 * uses implementations of the abstract methods in subclasses to derive concrete tests.
 *
 * @author Ansgar Konermann
 */


public abstract class GenericAssert_satisfies_TestBase<VALUE_TYPE> implements GenericAssert_satisfies_TestCase {

  protected abstract VALUE_TYPE one();

  protected abstract GenericAssert<VALUE_TYPE> assertionFor(VALUE_TYPE actual);

  protected NotNull<VALUE_TYPE> notNull() {
    return NotNull.instance();
  }

  @Test
  public void should_pass_if_condition_is_satisfied() {
    assertionFor(one()).satisfies(notNull());
  }

  @Test
  public void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        assertionFor(one()).satisfies(null);
      }
    });
  }

  @Test
  public void should_fail_if_condition_is_not_satisfied() {
    expectAssertionError("actual value:<null> should satisfy condition:<NotNull>").on(new CodeToTest() {
      public void run() {
        assertionFor(null).satisfies(notNull());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_condition_is_not_satisfied() {
    expectAssertionError("[A Test] actual value:<null> should satisfy condition:<NotNull>").on(new CodeToTest() {
      public void run() {
        assertionFor(null).as("A Test").satisfies(notNull());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_condition_if_condition_is_not_satisfied() {
    expectAssertionError("actual value:<null> should satisfy condition:<non-null>").on(new CodeToTest() {
      public void run() {
        assertionFor(null).satisfies(notNull().as("non-null"));
      }
    });
  }

  @Test
  public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_not_satisfied() {
    expectAssertionError("[A Test] actual value:<null> should satisfy condition:<non-null>").on(new CodeToTest() {
      public void run() {
        assertionFor(null).as("A Test").satisfies(notNull().as("non-null"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionFor(null).overridingErrorMessage("My custom message").satisfies(notNull());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionFor(null).as("A Test").overridingErrorMessage("My custom message").satisfies(notNull());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_not_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionFor(null).overridingErrorMessage("My custom message")
          .satisfies(notNull().as("non-null"));
      }
    });
  }
}
