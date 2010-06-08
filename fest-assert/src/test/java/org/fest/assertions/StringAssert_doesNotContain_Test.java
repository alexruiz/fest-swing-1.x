/*
 * Created on Jun 7, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link StringAssert#doesNotContain(String)}</code>.
 *
 * @author Yvonne Wang
 */
public class StringAssert_doesNotContain_Test {

  @Test
  public void should_fail_if_actual_String_contains_given_text() {
    expectAssertionError("<'hello'> should not contain <'he'>").on(new CodeToTest() {
      public void run() {
        new StringAssert("hello").doesNotContain("he");
      }
    });
  }
}
