/*
 * Created on Jan 10, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Collections.list;

import java.util.List;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link CollectionAssert#isEqualTo(java.util.Collection)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CollectionAssert_isEqualTo_Test implements GenericAssert_isEqualTo_TestCase {

  private static List<String> collection;

  @BeforeClass
  public static void setUpOnce() {
    collection = list("Luke", "Leia");
  }

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    new CollectionAssert(collection).isEqualTo(list("Luke", "Leia"));
  }

  @Test
  public void should_pass_if_both_actual_and_expected_are_null() {
    new CollectionAssert(null).isEqualTo(null);
  }

  @Test
  public void should_fail_if_actual_and_expected_are_not_equal() {
    expectAssertionError("expected:<['[Anakin]']> but was:<['[Luke', 'Leia]']>").on(new CodeToTest() {
      public void run() {
        new CollectionAssert(collection).isEqualTo(list("Anakin"));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("[A Test] expected:<['[Anakin]']> but was:<['[Luke', 'Leia]']>").on(new CodeToTest() {
      public void run() {
        new CollectionAssert(collection).as("A Test")
                                        .isEqualTo(list("Anakin"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new CollectionAssert(collection).overridingErrorMessage("My custom message")
                                        .isEqualTo(list("Anakin"));
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new CollectionAssert(collection).as("A Test")
                                        .overridingErrorMessage("My custom message")
                                        .isEqualTo(list("Anakin"));
      }
    });
  }
}
