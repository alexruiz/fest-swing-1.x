/*
 * Created on Mar 16, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.core.MouseClickInfo.leftButton;

import org.fest.swing.core.MouseButton;
import org.junit.Test;

/**
 * Tests for {@link JTableHeaderFixture#clickColumn(int, org.fest.swing.core.MouseClickInfo)}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JTableHeaderFixture_clickColumn_byIndex_withMouseClickInfo_Test extends JTableHeaderFixture_TestCase {
  @Test
  public void should_click_column() {
    final int index = 0;
    final MouseButton mouseButton = LEFT_BUTTON;
    final int times = 2;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().clickColumn(target(), index, mouseButton, times);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().clickColumn(index, leftButton().times(2)));
      }
    }.run();
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_MouseClickInfo_is_null() {
    fixture().clickColumn(0, null);
  }
}
