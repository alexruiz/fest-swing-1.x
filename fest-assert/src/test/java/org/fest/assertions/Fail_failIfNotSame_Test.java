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
 * Tests for <code>{@link Fail#failIfNotSame(String, Description, Object, Object)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Fail_failIfNotSame_Test {

  @Test
  public void should_fail_if_values_are_not_same() {
    expectAssertionError("[A message] expected same instance but found:<'Ben'> and:<'Han'>").on(new CodeToTest() {
      public void run() {
        Fail.failIfNotSame(null, new BasicDescription("A message"), "Ben", "Han");
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_values_are_not_same() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        Fail.failIfNotSame("My custom message", null, "Ben", "Han");
      }
    });
  }

  @Test
  public void should_pass_if_values_are_same() {
    Object o = new Object();
    Fail.failIfNotSame(null, null, o, o);
  }
}
