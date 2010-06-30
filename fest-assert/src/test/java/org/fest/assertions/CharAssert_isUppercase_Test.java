/*
 * Created on Jun 18, 2007
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

import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link CharAssert#isUpperCase()}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class CharAssert_isUppercase_Test {

  @Test
  public void should_pass_if_actual_is_uppercase() {
    new CharAssert('A').isUpperCase();
  }

  @Test
  public void should_fail_if_actual_is_not_uppercase() {
    expectAssertionError("<a> should be an upper-case character").on(new CodeToTest() {
      public void run() {
        new CharAssert('a').isUpperCase();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_not_uppercase() {
    expectAssertionError("[A Test] <a> should be an upper-case character").on(new CodeToTest() {
      public void run() {
        new CharAssert('a').as("A Test")
                           .isUpperCase();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_not_uppercase() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new CharAssert('a').overridingErrorMessage("My custom message")
                           .isUpperCase();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_not_uppercase() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new CharAssert('a').as("A Test")
                           .overridingErrorMessage("My custom message")
                           .isUpperCase();
      }
    });
  }
}
