/*
 * Created on Jan 10, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.CommonFailures.expectErrorIfConditionIsNull;
import static org.fest.assertions.UpperCase.upperCase;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link StringAssert#doesNotSatisfy(Condition)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class StringAssert_doesNotSatisfy_Test implements GenericAssert_doesNotSatisfy_TestCase {

  @Test
  public void should_pass_if_condition_is_not_satisfied() {
    new StringAssert("a").doesNotSatisfy(upperCase());
  }

  @Test
  public void should_throw_error_if_condition_is_null() {
    expectErrorIfConditionIsNull().on(new CodeToTest() {
      public void run() {
        new StringAssert("a").doesNotSatisfy(null);
      }
    });
  }

  @Test
  public void should_fail_if_condition_is_satisfied() {
    expectAssertionError("actual value:<'A'> should not satisfy condition:<UpperCase>").on(new CodeToTest() {
      public void run() {
        new StringAssert("A").doesNotSatisfy(upperCase());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("[Test] actual value:<'A'> should not satisfy condition:<UpperCase>").on(new CodeToTest() {
      public void run() {
        new StringAssert("A").as("Test")
                             .doesNotSatisfy(upperCase());
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("actual value:<'A'> should not satisfy condition:<uppercase>").on(new CodeToTest() {
      public void run() {
        new StringAssert("A").doesNotSatisfy(upperCase().as("uppercase"));
      }
    });
  }

  @Test
  public void should_fail_and_display_descriptions_of_assertion_and_condition_if_condition_is_satisfied() {
    expectAssertionError("[Test] actual value:<'A'> should not satisfy condition:<uppercase>").on(new CodeToTest() {
      public void run() {
        new StringAssert("A").as("Test")
                             .doesNotSatisfy(upperCase().as("uppercase"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new StringAssert("A").overridingErrorMessage("My custom message")
                             .doesNotSatisfy(upperCase());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new StringAssert("A").as("Test")
                             .overridingErrorMessage("My custom message")
                             .doesNotSatisfy(upperCase());
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_condition_if_condition_is_satisfied() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new StringAssert("A").overridingErrorMessage("My custom message")
                             .doesNotSatisfy(upperCase().as("uppercase"));
      }
    });
  }
}
