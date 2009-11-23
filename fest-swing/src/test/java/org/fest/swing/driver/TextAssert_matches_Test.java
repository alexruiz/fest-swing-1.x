/*
 * Created on Jun 21, 2009
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
package org.fest.swing.driver;

import static org.fest.swing.test.core.Regex.regex;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link TextAssert#matches(java.util.regex.Pattern)}</code>.
 *
 * @author Alex Ruiz
 */
public class TextAssert_matches_Test {

  @Test
  public void should_fail_if_actual_does_not_match_regex_pattern() {
    expectAssertionError("actual value:<'hello'> does not match pattern:<'bye'>")
      .on(new CodeToTest() {
        public void run() throws Throwable {
          new TextAssert("hello").matches(regex("bye"));
        }
      });
  }

  @Test
  public void should_fail_showing_description_if_actual_does_not_match_regex_pattern() {
    expectAssertionError("[A Test] actual value:<'hello'> does not match pattern:<'bye'>")
      .on(new CodeToTest() {
        public void run() throws Throwable {
          new TextAssert("hello").as("A Test").matches(regex("bye"));
        }
      });
  }

  @Test
  public void should_pass_if_actual_matches_regex_pattern() {
    new TextAssert("Hello").matches(regex("Hel.*"));
  }
}
