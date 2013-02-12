/*
 * Created on Dec 1, 2009
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

import org.junit.Test;

/**
 * Tests for {@link JTreeFixture#rightClickRow(int)}.
 * 
 * @author Alex Ruiz
 */
public class JTreeFixture_rightClickRow_Test extends JTreeFixture_TestCase {
  @Test
  public void should_right_click_row() {
    final int row = 6;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().rightClickRow(target(), row);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().rightClickRow(row));
      }
    }.run();
  }
}
