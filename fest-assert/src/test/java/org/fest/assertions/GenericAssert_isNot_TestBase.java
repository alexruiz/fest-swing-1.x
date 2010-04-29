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
 * Base class for testing {@link org.fest.assertions.GenericAssert#isNot(Condition)}.
 * <p/>
 * This class implements the algorithms which must be performed to test <code>isNot</code> as template methods and uses
 * implementations of the abstract methods in subclasses to derive concrete tests.
 *
 * @author Ansgar Konermann
 */
// TODO konermann: refactor to a common naming scheme for all abstract methods
// TODO konermann: review javadoc of all *_TestBase classes to match a common format and content.
public abstract class GenericAssert_isNot_TestBase<T> implements GenericAssert_doesNotSatisfy_TestCase {

  protected abstract NotNull<T> createNotNullCondition();

  protected abstract GenericAssert<T> createInstanceFromNullReference();

  protected abstract GenericAssert<T> createInstanceRepresentingZero();

  protected abstract String createStringRepresentationOfZero();

  private String ZERO() { return createStringRepresentationOfZero(); }

  @Test
  public void should_pass_if_condition_is_not_satisfied() {
    createInstanceFromNullReference().isNot(createNotNullCondition());
  }

  @Test
  public void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        createInstanceRepresentingZero().isNot(null);
      }
    });
  }

  @Test
  public void should_fail_if_condition_is_satisfied() {
    expectAssertionError("actual value:<" + ZERO() + "> should not be:<NotNull>").on(new CodeToTest() {
      public void run() {
        createInstanceRepresentingZero().isNot(createNotNullCondition());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("[A Test] actual value:<" + ZERO() + "> should not be:<NotNull>").on(new CodeToTest() {
      public void run() {
        createInstanceRepresentingZero().as("A Test")
          .isNot(createNotNullCondition());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("actual value:<" + ZERO() + "> should not be:<Not Null>").on(new CodeToTest() {
      public void run() {
        createInstanceRepresentingZero().isNot(createNotNullCondition().as("Not Null"));
      }
    });
  }

  @Test
  public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    expectAssertionError("[A Test] actual value:<" + ZERO() + "> should not be:<Not Null>").on(new CodeToTest() {
      public void run() {
        createInstanceRepresentingZero().as("A Test")
          .isNot(createNotNullCondition().as("Not Null"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        createInstanceRepresentingZero().overridingErrorMessage("My custom message")
          .isNot(createNotNullCondition());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        createInstanceRepresentingZero().as("A Test")
          .overridingErrorMessage("My custom message")
          .isNot(createNotNullCondition());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        createInstanceRepresentingZero().overridingErrorMessage("My custom message")
          .isNot(createNotNullCondition().as("Not Null"));
      }
    });
  }

}