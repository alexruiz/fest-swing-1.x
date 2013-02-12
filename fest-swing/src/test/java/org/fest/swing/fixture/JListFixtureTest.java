/*
 * Created on Jun 12, 2007
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
import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.test.builder.JPopupMenus.popupMenu;
import static org.fest.swing.test.core.Regex.regex;
import static org.fest.swing.util.Range.from;
import static org.fest.swing.util.Range.to;
import static org.fest.util.Arrays.array;

import java.util.regex.Pattern;

import javax.swing.JPopupMenu;

import org.fest.swing.cell.JListCellReader;
import org.fest.swing.util.Range.From;
import org.fest.swing.util.Range.To;
import org.junit.Test;

/**
 * Tests for {@link JListFixture}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JListFixtureTest extends JListFixture_TestCase {
  // TODO Reorganize into smaller units

  @Test
  public void shouldReturnContents() {
    final String[] contents = array("Luke", "Leia");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().contentsOf(target())).andReturn(contents);
      }

      @Override
      protected void codeToTest() {
        Object[] result = fixture().contents();
        assertThat(result).isSameAs(contents);
      }
    }.run();
  }

  @Test
  public void shouldReturnSelection() {
    final String[] selection = array("Luke", "Leia");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().selectionOf(target())).andReturn(selection);
      }

      @Override
      protected void codeToTest() {
        Object[] result = fixture().selection();
        assertThat(result).isSameAs(selection);
      }
    }.run();
  }

  @Test
  public void shouldClearSelection() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().clearSelection(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().clearSelection());
      }
    }.run();
  }

  @Test
  public void shouldSelectItemsInRange() {
    final From from = from(6);
    final To to = to(8);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectItems(target(), from, to);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectItems(from, to));
      }
    }.run();
  }

  @Test
  public void shouldSelectItemsUnderIndices() {
    final int[] indices = new int[] { 6, 8 };
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectItems(target(), indices);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectItems(indices));
      }
    }.run();
  }

  @Test
  public void shouldSelectItemUnderIndex() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectItem(target(), 6);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectItem(6));
      }
    }.run();
  }

  @Test
  public void shouldSelectItemsWithValues() {
    final String[] values = array("Frodo", "Sam");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectItems(target(), values);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectItems(values));
      }
    }.run();
  }

  @Test
  public void shouldSelectItemsMatchingPatterns() {
    final Pattern[] patterns = array(regex("Frodo"), regex("Sam"));
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectItems(target(), patterns);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectItems(patterns));
      }
    }.run();
  }

  @Test
  public void shouldSelectItemWithValue() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectItem(target(), "Frodo");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectItem("Frodo"));
      }
    }.run();
  }

  @Test
  public void shouldSelectItemMatchingPattern() {
    final Pattern pattern = regex("Hello");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectItem(target(), pattern);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectItem(pattern));
      }
    }.run();
  }

  @Deprecated
  public void shouldDoubleClickItemUnderIndex() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().clickItem(target(), 6, LEFT_BUTTON, 2);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().doubleClickItem(6));
      }
    }.run();
  }

  @Deprecated
  public void shouldDoubleClickItemWithValue() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().clickItem(target(), "Frodo", LEFT_BUTTON, 2);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().doubleClickItem("Frodo"));
      }
    }.run();
  }

  @Test
  public void shouldRequireSelectionValue() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelection(target(), "Frodo");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelection("Frodo"));
      }
    }.run();
  }

  @Test
  public void shouldRequireSelectionMatchingPattern() {
    final Pattern p = regex("hello");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelection(target(), p);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelection(p));
      }
    }.run();
  }

  @Test
  public void shouldRequireSelectionIndex() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelection(target(), 6);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelection(6));
      }
    }.run();
  }

  @Test
  public void shouldRequireSelectedItems() {
    final String[] items = array("Frodo", "Sam");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelectedItems(target(), items);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelectedItems(items));
      }
    }.run();
  }

  @Test
  public void shouldRequireSelectedItemsToMatchPatterns() {
    final Pattern[] patterns = array(regex("Frodo"), regex("Sam"));
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelectedItems(target(), patterns);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelectedItems(patterns));
      }
    }.run();
  }

  @Test
  public void shouldRequireSelectedIndices() {
    final int[] items = { 0, 1 };
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireSelectedItems(target(), items);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireSelectedItems(items));
      }
    }.run();
  }

  @Test
  public void shouldRequireNoSelection() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireNoSelection(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireNoSelection());
      }
    }.run();
  }

  @Test
  public void shouldReturnValueAtIndex() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().value(target(), 6)).andReturn("Frodo");
      }

      @Override
      protected void codeToTest() {
        assertThat(fixture().valueAt(6)).isEqualTo("Frodo");
      }
    }.run();
  }

  @Test
  public void shouldReturnJListItemFixture() {
    JListItemFixture item = fixture().item(6);
    assertThat(item.index).isEqualTo(6);
    assertThat(item.list).isSameAs(fixture());
  }

  @Test
  public void shouldReturnJListItemFixtureUsingValue() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().indexOf(target(), "Frodo")).andReturn(8);
      }

      @Override
      protected void codeToTest() {
        JListItemFixture item = fixture().item("Frodo");
        assertThat(item.index).isEqualTo(8);
        assertThat(item.list).isSameAs(fixture());
      }
    }.run();
  }

  @Test
  public void shouldReturnJListItemFixtureWithItemMatchingPatternAsString() {
    final Pattern pattern = regex("Frodo");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().indexOf(target(), pattern)).andReturn(8);
      }

      @Override
      protected void codeToTest() {
        JListItemFixture item = fixture().item(pattern);
        assertThat(item.index).isEqualTo(8);
        assertThat(item.list).isSameAs(fixture());
      }
    }.run();
  }

  @Test
  public void shouldDragElementMatchingValue() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drag(target(), "Frodo");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drag("Frodo"));
      }
    }.run();
  }

  @Test
  public void shouldDragElementMatchingPattern() {
    final Pattern p = regex("Frodo");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drag(target(), p);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drag(p));
      }
    }.run();
  }

  @Test
  public void shouldDropElementMatchingValue() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drop(target(), "Frodo");
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drop("Frodo"));
      }
    }.run();
  }

  @Test
  public void shouldDropElementMatchingPattern() {
    final Pattern p = regex("Frodo");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drop(target(), p);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drop(p));
      }
    }.run();
  }

  @Test
  public void shouldDragElementUnderIndex() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drag(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drag(8));
      }
    }.run();
  }

  @Test
  public void shouldDrop() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drop(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drop());
      }
    }.run();
  }

  @Test
  public void shouldDropElementUnderIndex() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drop(target(), 8);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drop(8));
      }
    }.run();
  }

  @Test
  public void shouldShowJPopupMenuAtItemUnderIndex() {
    final JPopupMenu popup = popupMenu().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().showPopupMenu(target(), 6)).andReturn(popup);
      }

      @Override
      protected void codeToTest() {
        JPopupMenuFixture result = fixture().showPopupMenuAt(6);
        assertThat(result.target()).isSameAs(popup);
      }
    }.run();
  }

  @Test
  public void shouldShowJPopupMenuAtItemWithValue() {
    final JPopupMenu popup = popupMenu().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().showPopupMenu(target(), "Frodo")).andReturn(popup);
      }

      @Override
      protected void codeToTest() {
        JPopupMenuFixture result = fixture().showPopupMenuAt("Frodo");
        assertThat(result.target()).isSameAs(popup);
      }
    }.run();
  }

  @Test
  public void shouldShowJPopupMenuAtItemMatchingPattern() {
    final Pattern pattern = regex("Frodo");
    final JPopupMenu popup = popupMenu().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().showPopupMenu(target(), pattern)).andReturn(popup);
      }

      @Override
      protected void codeToTest() {
        JPopupMenuFixture result = fixture().showPopupMenuAt(pattern);
        assertThat(result.target()).isSameAs(popup);
      }
    }.run();
  }

  @Test
  public void shouldSetCellReaderInDriver() {
    final JListCellReader reader = createMock(JListCellReader.class);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().cellReader(reader);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().replaceCellReader(reader));
      }
    }.run();
  }
}
