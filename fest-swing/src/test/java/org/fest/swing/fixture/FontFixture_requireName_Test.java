/*
 * Created on Apr 16, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link FontFixture#requireName(String)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FontFixture_requireName_Test extends FontFixture_TestCase {

  @Test
  public void should_pass_if_name_is_equal_to_expected() {
    fixture().requireName("SansSerif");
  }

  @Test
  public void should_fail_if_name_is_not_equal_to_expected() {
    String message = "[name] expected:<'[Monospace]'> but was:<'[SansSerif]'>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        fixture().requireName("Monospace");
      }
    });
  }

  @Test
  public void should_fail_showing_description_if_name_is_not_equal_to_expected() {
    String message = "[test - name] expected:<'[Monospace]'> but was:<'[SansSerif]'>";
    expectAssertionError(message).on(new CodeToTest() {
      public void run() {
        FontFixture fixture = new FontFixture(font(), "test");
        fixture.requireName("Monospace");
      }
    });
  }
}
