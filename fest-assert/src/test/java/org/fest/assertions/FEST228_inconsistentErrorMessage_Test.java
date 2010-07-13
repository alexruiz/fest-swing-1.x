/*
 * Created on Oct 1, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for bug <a href="http://jira.codehaus.org/browse/FEST-228" target="_blank">FEST-228</a>.
 *
 * @author Alex Ruiz
 */
public class FEST228_inconsistentErrorMessage_Test {

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_and_expected_are_not_equal() {
    expectAssertionError("[A Test] expected:<'L[eia]'> but was:<'L[uke]'>").on(new CodeToTest() {
      public void run() {
        Fail.failIfNotEqual(null, new BasicDescription("A Test"), "Luke", "Leia");
      }
    });
  }
}
