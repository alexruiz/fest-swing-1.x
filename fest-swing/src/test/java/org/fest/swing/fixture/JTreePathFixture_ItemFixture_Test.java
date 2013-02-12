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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.MouseButton.MIDDLE_BUTTON;
import static org.fest.swing.core.MouseClickInfo.leftButton;

import org.fest.swing.core.MouseClickInfo;
import org.junit.Test;

/**
 * Tests for methods in {@link JTreePathFixture} that are inherited from {@link ItemFixture}.
 * 
 * @author Alex Ruiz
 */
public class JTreePathFixture_ItemFixture_Test extends JTreePathFixture_withMockTree_TestCase implements
ItemFixture_TestCase {
  @Override
  @Test
  public void should_select_item() {
    new EasyMockTemplate(tree) {
      @Override
      protected void expectations() {
        expect(tree.selectPath(path)).andReturn(tree);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.select());
      }
    }.run();
  }

  @Override
  @Test
  public void should_click_item() {
    new EasyMockTemplate(tree) {
      @Override
      protected void expectations() {
        expect(tree.clickPath(path)).andReturn(tree);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.click());
      }
    }.run();
  }

  @Override
  @Test
  public void should_click_item_with_MouseButton() {
    new EasyMockTemplate(tree) {
      @Override
      protected void expectations() {
        expect(tree.clickPath(path, MIDDLE_BUTTON)).andReturn(tree);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.click(MIDDLE_BUTTON));
      }
    }.run();
  }

  @Override
  @Test
  public void should_click_item_using_MouseClickInfo() {
    final MouseClickInfo mouseClickInfo = leftButton();
    new EasyMockTemplate(tree) {
      @Override
      protected void expectations() {
        expect(tree.clickPath(path, mouseClickInfo)).andReturn(tree);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.click(mouseClickInfo));
      }
    }.run();
  }

  @Override
  @Test
  public void should_double_click_item() {
    new EasyMockTemplate(tree) {
      @Override
      protected void expectations() {
        expect(tree.doubleClickPath(path)).andReturn(tree);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.doubleClick());
      }
    }.run();
  }

  @Override
  @Test
  public void should_right_click_item() {
    new EasyMockTemplate(tree) {
      @Override
      protected void expectations() {
        expect(tree.rightClickPath(path)).andReturn(tree);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.rightClick());
      }
    }.run();
  }

  @Override
  @Test
  public void should_drag_item() {
    new EasyMockTemplate(tree) {
      @Override
      protected void expectations() {
        expect(tree.drag(path)).andReturn(tree);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.drag());
      }
    }.run();
  }

  @Override
  @Test
  public void should_drop_item() {
    new EasyMockTemplate(tree) {
      @Override
      protected void expectations() {
        expect(tree.drop(path)).andReturn(tree);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.drop());
      }
    }.run();
  }

  @Override
  @Test
  public void should_return_item_contents() {
    final String text = "hello";
    new EasyMockTemplate(tree) {
      @Override
      protected void expectations() {
        expect(tree.valueAt(path)).andReturn(text);
      }

      @Override
      protected void codeToTest() {
        String value = fixture.value();
        assertThat(value).isEqualTo(text);
      }
    }.run();
  }

  @Override
  @Test
  public void should_show_popup_menu_at_item() {
    final JPopupMenuFixture popupMenu = createMock(JPopupMenuFixture.class);
    new EasyMockTemplate(tree) {
      @Override
      protected void expectations() {
        expect(tree.showPopupMenuAt(path)).andReturn(popupMenu);
      }

      @Override
      protected void codeToTest() {
        assertThat(fixture.showPopupMenu()).isSameAs(popupMenu);
      }
    }.run();
  }
}
