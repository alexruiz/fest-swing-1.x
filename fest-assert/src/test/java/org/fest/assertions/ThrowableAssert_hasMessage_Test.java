/*
 * Created on Dec 23, 2007
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

import static org.fest.assertions.CommonFailures.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.*;

/**
 * Tests for <code>{@link ThrowableAssert#hasMessage(String)}</code>.
 *
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class ThrowableAssert_hasMessage_Test {

  private static Exception actual;

  @BeforeClass
  public static void setUpOnce() {
    actual = new Exception("An exception");
  }
  
  @Test
  public void should_pass_if_message_in_actual_is_equal_to_expected() {
    new ThrowableAssert(actual).hasMessage("An exception");
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        new ThrowableAssert(null).hasMessage("");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new ThrowableAssert(null).as("A Test")
                                 .hasMessage("");
      }
    });
  }

  @Test
  public void should_fail_if_message_in_actual_is_not_equal_to_expected() {
    expectAssertionError("expected:<'[Hi]'> but was:<'[An exception]'>").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(actual).hasMessage("Hi");
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_message_in_actual_is_not_equal_to_expected() {
    expectAssertionError("[A Test] expected:<'[Hi]'> but was:<'[An exception]'>").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(actual).as("A Test")
                                   .hasMessage("Hi");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_message_in_actual_is_not_equal_to_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(actual).overridingErrorMessage("My custom message")
                                   .hasMessage("Hi");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_message_in_actual_is_not_equal_to_expected() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ThrowableAssert(actual).as("A Test")
                                   .overridingErrorMessage("My custom message")
                                   .hasMessage("Hi");
      }
    });
  }
}
