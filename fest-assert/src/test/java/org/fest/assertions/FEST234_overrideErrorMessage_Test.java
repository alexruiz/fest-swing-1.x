/*
 * Created on Oct 7, 2009
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

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.fail;

import org.junit.*;
import org.junit.Assert;

/**
 * Tests for bug <a href="http://jira.codehaus.org/browse/FEST-234" target="_blank">FEST-234</a>.
 *
 * @author Alex Ruiz
 */
public class FEST234_overrideErrorMessage_Test {

  @Test
  public void should_override_error_message() {
    try {
      assertThat("Hello").overridingErrorMessage("'Hello' should be equal to 'Bye'").isEqualTo("Bye");
      fail("Expecting AssertionError");
    } catch (AssertionError e) {
      Assert.assertEquals("'Hello' should be equal to 'Bye'", e.getMessage());
    }
  }

  @Test
  public void should_ignore_description_of_assertion_when_overriding_error_message() {
    try {
      assertThat("Hello").as("A Test").overridingErrorMessage("'Hello' should be equal to 'Bye'").isEqualTo("Bye");
      fail("Expecting AssertionError");
    } catch (AssertionError e) {
      Assert.assertEquals("'Hello' should be equal to 'Bye'", e.getMessage());
    }
  }
}
