/*
 * Created on Dec 25, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.builder.JScrollBars.scrollBar;

import javax.swing.JScrollBar;

import org.junit.Test;

/**
 * Tests for {@link JScrollPaneFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JScrollPaneFixtureTest extends JScrollPaneFixture_TestCase {
  // TODO Reorganize into smaller units

  @Test
  public void shouldReturnHorizontalScrollBar() {
    final JScrollBar scrollBar = scrollBar().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().horizontalScrollBarIn(target())).andReturn(scrollBar);
      }

      @Override
      protected void codeToTest() {
        JScrollBarFixture horizontalScrollBar = fixture().horizontalScrollBar();
        assertThat(horizontalScrollBar.target()).isSameAs(scrollBar);
      }
    }.run();
  }

  @Test
  public void shouldReturnVerticalScrollBar() {
    final JScrollBar scrollBar = scrollBar().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().verticalScrollBarIn(target())).andReturn(scrollBar);
      }

      @Override
      protected void codeToTest() {
        JScrollBarFixture verticalScrollBar = fixture().verticalScrollBar();
        assertThat(verticalScrollBar.target()).isSameAs(scrollBar);
      }
    }.run();
  }
}
