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
 * Tests for <code>{@link FontFixture#requireSize(int)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FontFixture_requireSize_Test extends FontFixture_TestCase {

  @Test
  public void should_pass_if_size_is_equal_to_expected() {
    fixture().requireSize(8);
  }

  @Test
  public void should_fail_if_size_is_not_equal_to_expected() {
    expectAssertionError("[size] expected:<6> but was:<8>").on(new CodeToTest() {
      public void run() {
        fixture().requireSize(6);
      }
    });
  }

  @Test
  public void should_fail_showing_description_if_size_is_not_equal_to_expected() {
    expectAssertionError("[test - size] expected:<6> but was:<8>").on(new CodeToTest() {
      public void run() {
        FontFixture fixture = new FontFixture(font(), "test");
        fixture.requireSize(6);
      }
    });
  }
}
