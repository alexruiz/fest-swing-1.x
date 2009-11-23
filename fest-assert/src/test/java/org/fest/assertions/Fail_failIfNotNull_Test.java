/*
 * Created on Sep 16, 2007
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
 * Tests for <code>{@link Fail#failIfNotNull(String, Description, Object)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Fail_failIfNotNull_Test {

  @Test
  public void should_fail_if_value_is_not_null() {
    expectAssertionError("[A message] <'Leia'> should be null").on(new CodeToTest() {
      public void run() {
        Fail.failIfNotNull(null, new BasicDescription("A message"), "Leia");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_value_is_not_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        Fail.failIfNotNull("My custom message", null, "Leia");
      }
    });
  }

  @Test
  public void should_pass_if_value_is_not_null() {
    Fail.failIfNotNull(null, null, null);
  }
}
