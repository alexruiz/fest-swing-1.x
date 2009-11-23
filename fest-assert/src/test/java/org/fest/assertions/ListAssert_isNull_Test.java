/*
 * Created on Mar 29, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyList;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import java.util.List;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ListAssert#isNull()}</code>.
 *
 * @author Alex Ruiz
 */
public class ListAssert_isNull_Test implements GenericAssert_isNull_TestCase {

  private static List<Object> list;

  @BeforeClass
  public static void setUpOnce() {
    list = emptyList();
  }

  @Test
  public void should_pass_if_actual_is_null() {
    new ListAssert(null).isNull();
  }

  @Test
  public void should_fail_if_actual_is_not_null() {
    expectAssertionError("<[]> should be null").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).isNull();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_not_null() {
    expectAssertionError("[A Test] <[]> should be null").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .isNull();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_not_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).overridingErrorMessage("My custom message")
                            .isNull();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_not_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ListAssert(list).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .isNull();
      }
    });
  }
}
