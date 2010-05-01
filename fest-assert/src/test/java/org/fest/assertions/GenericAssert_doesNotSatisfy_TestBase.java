/*
 * Created on 2010-4-19
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
 * Base class for testing {@link org.fest.assertions.GenericAssert#doesNotSatisfy(Condition)}.
 * <p/>
 * This class implements the algorithms which must be performed to test <code>doesNotSatisfy</code> as template methods
 * and uses implementations of the abstract methods in subclasses to derive concrete tests.
 *
 * @author Ansgar Konermann
 */

public abstract class GenericAssert_doesNotSatisfy_TestBase<VALUE_TYPE> implements GenericAssert_doesNotSatisfy_TestCase {

  protected abstract VALUE_TYPE zero();

  protected abstract String zeroAsString();

  protected abstract GenericAssert<VALUE_TYPE> assertionFor(VALUE_TYPE actual);

  protected NotNull<VALUE_TYPE> notNull() {
    return NotNull.instance();
  }

  @Test
  public void should_pass_if_condition_is_not_satisfied() {
    assertionFor(null).doesNotSatisfy(notNull());
  }

  @Test
  public void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        assertionFor(zero()).doesNotSatisfy(null);
      }
    });
  }

  @Test
  public void should_fail_if_condition_is_satisfied() {
    expectAssertionError("actual value:<" + zeroAsString() + "> should not satisfy condition:<NotNull>").on(new CodeToTest() {
      public void run() {
        assertionFor(zero()).doesNotSatisfy(notNull());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("[A Test] actual value:<" + zeroAsString() + "> should not satisfy condition:<NotNull>").on(new CodeToTest() {
      public void run() {
        assertionFor(zero()).as("A Test").doesNotSatisfy(notNull());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("actual value:<" + zeroAsString() + "> should not satisfy condition:<Not Null>").on(new CodeToTest() {
      public void run() {
        assertionFor(zero()).doesNotSatisfy(notNull().as("Not Null"));
      }
    });
  }

  @Test
  public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    expectAssertionError("[A Test] actual value:<" + zeroAsString() + "> should not satisfy condition:<Not Null>").on(new CodeToTest() {
      public void run() {
        assertionFor(zero()).as("A Test").doesNotSatisfy(notNull().as("Not Null"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionFor(zero()).overridingErrorMessage("My custom message").doesNotSatisfy(notNull());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionFor(zero()).as("A Test").overridingErrorMessage("My custom message").doesNotSatisfy(notNull());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        assertionFor(zero()).overridingErrorMessage("My custom message").doesNotSatisfy(notNull().as("Not Null"));
      }
    });
  }

}