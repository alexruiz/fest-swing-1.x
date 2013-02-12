/*
 * Created on Dec 27, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.core.MouseButton.MIDDLE_BUTTON;

import org.fest.swing.core.MouseButton;
import org.junit.Test;

/**
 * Tests for {@link JTreeFixture#clickRow(int, org.fest.swing.core.MouseButton)}.
 * 
 * @author Alex Ruiz
 */
public class JTreeFixture_clickRow_withMouseButton_Test extends JTreeFixture_TestCase {
  @Test
  public void should_click_row() {
    final int row = 6;
    final MouseButton button = MIDDLE_BUTTON;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().clickRow(target(), row, button);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().clickRow(row, button));
      }
    }.run();
  }
}
