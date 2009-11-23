/*
 * Created on Jan 24, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyMap;
import static org.fest.assertions.CommonFailures.expectErrorIfMapIsNull;
import static org.fest.assertions.CommonFailures.expectErrorWithDescriptionIfMapIsNull;
import static org.fest.assertions.MapAssert.entry;
import static org.fest.assertions.MapFactory.map;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import java.util.*;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link MapAssert#isNotEmpty()}</code>.
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class MapAssert_isNotEmpty_Test implements GroupAssert_isNotEmpty_TestCase {

  @Test
  public void should_pass_if_actual_is_not_empty() {
    Map<Object, Object> map = map(entry("key1", 1));
    new MapAssert(map).isNotEmpty();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfMapIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).isNotEmpty();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfMapIsNull(new CodeToTest() {
      public void run() {
        new MapAssert(null).as("A Test")
                           .isNotEmpty();
      }
    });
  }

  @Test
  public void should_fail_if_actual_is_empty() {
    expectAssertionError("expecting non-empty map, but it was empty").on(new CodeToTest() {
      public void run() {
        new MapAssert(emptyMap()).isNotEmpty();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_empty() {
    expectAssertionError("[A Test] expecting non-empty map, but it was empty").on(new CodeToTest() {
      public void run() {
        new MapAssert(emptyMap()).as("A Test")
                                 .isNotEmpty();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_empty() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new MapAssert(emptyMap()).overridingErrorMessage("My custom message")
                                 .isNotEmpty();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_empty() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new MapAssert(emptyMap()).as("A Test")
                                 .overridingErrorMessage("My custom message")
                                 .isNotEmpty();
      }
    });
  }
}
