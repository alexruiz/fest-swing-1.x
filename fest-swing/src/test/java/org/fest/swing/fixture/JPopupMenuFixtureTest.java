/*
 * Created on Sep 5, 2007
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
import static org.fest.swing.test.builder.JMenuItems.menuItem;
import static org.fest.util.Arrays.array;

import javax.swing.JMenuItem;

import org.fest.swing.core.GenericTypeMatcher;
import org.junit.Test;

/**
 * Tests for {@link JPopupMenuFixture}.
 * 
 * @author Yvonne Wang
 */
public class JPopupMenuFixtureTest extends JPopupMenuFixture_TestCase {
  // TODO Reorganize into smaller units

  @Test
  public void shouldReturnMenuLabels() {
    final String[] labels = array("Ben", "Leia");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().menuLabelsOf(target())).andReturn(labels);
      }

      @Override
      protected void codeToTest() {
        String[] result = fixture().menuLabels();
        assertThat(result).isSameAs(labels);
      }
    }.run();
  }

  @Test
  public void shouldReturnMenuItemByName() {
    final JMenuItem menuItem = menuItem().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().menuItem(target(), "menuItem")).andReturn(menuItem);
      }

      @Override
      protected void codeToTest() {
        JMenuItemFixture result = fixture().menuItem("menuItem");
        assertThat(result.target()).isSameAs(menuItem);
      }
    }.run();
  }

  @SuppressWarnings("unchecked")
  @Test
  public void shouldReturnMenuItemUsingSearchCriteria() {
    final GenericTypeMatcher<JMenuItem> matcher = createMock(GenericTypeMatcher.class);
    final JMenuItem menuItem = menuItem().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().menuItem(target(), matcher)).andReturn(menuItem);
      }

      @Override
      protected void codeToTest() {
        JMenuItemFixture result = fixture().menuItem(matcher);
        assertThat(result.target()).isSameAs(menuItem);
      }
    }.run();
  }
}
