/*
 * Created on Nov 17, 2009
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
package org.fest.swing.fixture;

import static org.easymock.EasyMock.expectLastCall;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link JButtonFixture#requireText(String)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JButtonFixture_requireText_withString_Test extends JButtonFixture_TestCase {

  @Test
  public void should_require_text() {
    new EasyMockTemplate(driver()) {
      protected void expectations() {
        driver().requireText(target(), "A Button");
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireText("A Button"));
      }
    }.run();
  }
}
