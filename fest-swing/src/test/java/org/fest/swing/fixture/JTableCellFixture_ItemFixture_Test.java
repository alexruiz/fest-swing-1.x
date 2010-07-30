/*
 * Created on Mar 2, 2008
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

import static org.easymock.EasyMock.*;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.MouseButton.*;
import static org.fest.swing.core.MouseClickInfo.leftButton;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.core.MouseClickInfo;
import org.junit.Test;

/**
 * Tests for methods in <code>{@link JTableCellFixture}</code> that are inherited from <code>{@link ItemFixture}</code>.
 *
 * @author Alex Ruiz
 */
public class JTableCellFixture_ItemFixture_Test extends JTableCellFixture_withMockTable_TestCase implements ItemFixture_TestCase {

  @Test
  public void should_select_item() {
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.selectCell(cell)).andReturn(table);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.select());
      }
    }.run();
  }

  @Test
  public void should_click_item() {
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.click(cell, LEFT_BUTTON)).andReturn(table);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.click());
      }
    }.run();
  }

  @Test
  public void should_click_item_with_MouseButton() {
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.click(cell, MIDDLE_BUTTON)).andReturn(table);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.click(MIDDLE_BUTTON));
      }
    }.run();
  }

  @Test
  public void should_click_item_using_MouseClickInfo() {
    final MouseClickInfo mouseClickInfo = leftButton().times(2);
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.click(cell, mouseClickInfo)).andReturn(table);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.click(mouseClickInfo));
      }
    }.run();
  }

  @Test
  public void should_double_click_item() {
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        table.click(cell, LEFT_BUTTON, 2);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.doubleClick());
      }
    }.run();
  }

  @Test
  public void should_right_click_item() {
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.click(cell, RIGHT_BUTTON)).andReturn(table);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.rightClick());
      }
    }.run();
  }

  @Test
  public void should_show_popup_menu_at_item() {
    final JPopupMenuFixture popup = createMock(JPopupMenuFixture.class);
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.showPopupMenuAt(cell)).andReturn(popup);
      }

      @Override
      protected void codeToTest() {
        JPopupMenuFixture result = fixture.showPopupMenu();
        assertThat(result).isSameAs(popup);
      }
    }.run();
  }

  @Test
  public void should_return_item_contents() {
    final String content = "Hello";
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.valueAt(cell)).andReturn(content);
      }

      @Override
      protected void codeToTest() {
        Object result = fixture.value();
        assertThat(result).isSameAs(content);
      }
    }.run();
  }

  @Test
  public void should_drag_item() {
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.drag(cell)).andReturn(table);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.drag());
      }
    }.run();
  }

  @Test
  public void should_drop_item() {
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.drop(cell)).andReturn(table);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.drop());
      }
    }.run();
  }

  @Test
  public void shouldRequireEditableCell() {
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.requireEditable(cell)).andReturn(table);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.requireEditable());
      }
    }.run();
  }

  @Test
  public void shouldRequireNotEditableCell() {
    new EasyMockTemplate(table) {
      @Override
      protected void expectations() {
        expect(table.requireNotEditable(cell)).andReturn(table);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.requireNotEditable());
      }
    }.run();
  }

  @Test
  public void shouldReturnRow() {
    assertThat(fixture.row()).isEqualTo(cell.row);
  }

  @Test
  public void shouldReturnColumn() {
    assertThat(fixture.column()).isEqualTo(cell.column);
  }
}
