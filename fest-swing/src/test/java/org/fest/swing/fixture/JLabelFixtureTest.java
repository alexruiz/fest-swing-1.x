/*
 * Created on Feb 9, 2007
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
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.Regex.regex;

import java.util.regex.Pattern;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link JLabelFixture}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JLabelFixtureTest extends JLabelFixture_TestCase {

  // TODO Reorganize into smaller units

  @Test
  public void shouldReturnText() {
    final String text = "A Label";
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        expect(driver().textOf(target())).andReturn(text);
      }

      protected void codeToTest() {
        assertThat(fixture().text()).isEqualTo(text);
      }
    }.run();
  }

  @Test
  public void shouldRequireText() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireText(target(), "A Label");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireText("A Label"));
      }
    }.run();
  }

  @Test
  public void shouldRequireTextToMatchPattern() {
    final Pattern pattern = regex(".");
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireText(target(), pattern);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireText(pattern));
      }
    }.run();
  }
}
