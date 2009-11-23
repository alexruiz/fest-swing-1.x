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

import static java.util.Collections.emptyList;
import static org.fest.assertions.CommonFailures.expectErrorIfCollectionIsNull;
import static org.fest.assertions.CommonFailures.expectErrorWithDescriptionIfCollectionIsNull;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Collections.list;

import java.util.Collection;
import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link CollectionAssert#isEmpty()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class CollectionAssert_isEmpty_Test implements GroupAssert_isEmpty_TestCase {

  private static Collection<String> collection;

  @BeforeClass
  public static void setUpOnce() {
    collection = list("Yoda");
  }

  @Test
  public void should_pass_if_actual_is_empty() {
    new CollectionAssert(emptyList()).isEmpty();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfCollectionIsNull(new CodeToTest() {
      public void run() {
        new CollectionAssert(null).isEmpty();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfCollectionIsNull(new CodeToTest() {
      public void run() {
        new CollectionAssert(null).as("A Test")
                                  .isEmpty();
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_not_empty() {
    expectAssertionError("expecting empty collection, but was:<['Yoda']>").on(new CodeToTest() {
      public void run() {
        new CollectionAssert(collection).isEmpty();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_not_empty() {
    expectAssertionError("[A Test] expecting empty collection, but was:<['Yoda']>").on(new CodeToTest() {
      public void run() {
        new CollectionAssert(collection).as("A Test")
                                        .isEmpty();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_not_empty() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new CollectionAssert(collection).overridingErrorMessage("My custom message")
                                        .isEmpty();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_not_empty() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new CollectionAssert(collection).as("A Test")
                                        .overridingErrorMessage("My custom message")
                                        .isEmpty();
      }
    });
  }
}
