/*
 * Created on Jul 12, 2007
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

import static java.awt.Color.BLUE;
import static java.awt.Font.PLAIN;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.core.MouseClickInfo.leftButton;
import static org.fest.swing.data.TableCell.row;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.test.builder.JPopupMenus.popupMenu;
import static org.fest.swing.test.builder.JTableHeaders.tableHeader;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.test.core.Regex.regex;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.JPopupMenu;
import javax.swing.table.JTableHeader;

import org.fest.swing.cell.JTableCellReader;
import org.fest.swing.cell.JTableCellWriter;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.data.TableCell;
import org.fest.swing.data.TableCellByColumnId;
import org.fest.swing.exception.ActionFailedException;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@link JTableFixture}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTableFixtureTest extends JTableFixture_TestCase {
  // TODO Reorganize into smaller units

  private static TableCell cell;

  @BeforeClass
  public static void setUpCell() {
    cell = row(6).column(8);
  }

  @Test
  public void shouldReturnColumnIndexGivenName() {
    final String columnName = "column0";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().columnIndex(target(), columnName)).andReturn(6);
      }

      @Override
      protected void codeToTest() {
        int result = fixture().columnIndexFor(columnName);
        assertThat(result).isEqualTo(6);
      }
    }.run();
  }

  @Test
  public void shouldThrowErrorIfColumnWithGivenNameNotFound() {
    final ActionFailedException expected = actionFailure("Thrown on purpose");
    final String columnName = "column0";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().columnIndex(target(), columnName)).andThrow(expected);
      }

      @Override
      protected void codeToTest() {
        try {
          fixture().columnIndexFor(columnName);
          failWhenExpectingException();
        } catch (ActionFailedException e) {
          assertThat(e).isSameAs(expected);
        }
      }
    }.run();
  }

  @Test
  public void shouldReturnCellHavingGivenColumnName() {
    final int row = 6;
    final int column = 8;
    final TableCellByColumnId cellByColumnName = TableCellByColumnId.row(row).columnId("column0");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().cell(target(), cellByColumnName)).andReturn(row(row).column(column));
      }

      @Override
      protected void codeToTest() {
        JTableCellFixture cellFixture = fixture().cell(cellByColumnName);
        assertThat(cellFixture.row()).isEqualTo(row);
        assertThat(cellFixture.column()).isEqualTo(column);
      }
    }.run();
  }

  @Test
  public void shouldReturnRowCount() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().rowCountOf(target())).andReturn(3);
      }

      @Override
      protected void codeToTest() {
        int result = fixture().rowCount();
        assertThat(result).isEqualTo(3);
      }
    }.run();
  }

  @Test
  public void shouldSelectCell() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectCell(target(), cell);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectCell(cell));
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
  public void shouldSelectCells() {
    final TableCell[] cells = { cell };
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectCells(target(), cells);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectCells(cells));
      }
    }.run();
  }

  @Test
  public void shouldReturnSelectionContents() {
    final String content = "A Cell";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().selectionValue(target())).andReturn(content);
      }

      @Override
      protected void codeToTest() {
        Object result = fixture().selectionValue();
        assertThat(result).isSameAs(content);
      }
    }.run();
  }

  @Test
  public void shouldDragAtCell() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drag(target(), cell);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drag(cell));
      }
    }.run();
  }

  @Test
  public void shouldDropAtCell() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().drop(target(), cell);
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().drop(cell));
      }
    }.run();
  }

  @Test
  public void shouldReturnPointAtCell() {
    final Point p = new Point(6, 8);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().pointAt(target(), cell)).andReturn(p);
      }

      @Override
      protected void codeToTest() {
        Point result = fixture().pointAt(cell);
        assertThat(result).isSameAs(p);
      }
    }.run();
  }

  @Test
  public void shouldReturnCellContent() {
    final String content = "A Cell";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().value(target(), cell)).andReturn(content);
      }

      @Override
      protected void codeToTest() {
        Object result = fixture().valueAt(cell);
        assertThat(result).isSameAs(content);
      }
    }.run();
  }

  @Test
  public void shouldClickCellWithGivenMouseButton() {
    final MouseButton button = LEFT_BUTTON;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().click(target(), cell, button, 1);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().click(cell, button));
      }
    }.run();
  }

  @Test
  public void shouldClickCellWithGivenMouseClickInfo() {
    final MouseClickInfo mouseClickInfo = leftButton().times(2);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().click(target(), cell, mouseClickInfo.button(), mouseClickInfo.times());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().click(cell, mouseClickInfo));
      }
    }.run();
  }

  @Test
  public void shouldThrowExceptionIfMouseClickInfoIsNull() {
    MouseClickInfo mouseClickInfo = null;
    try {
      fixture().click(cell, mouseClickInfo);
      failWhenExpectingException();
    } catch (NullPointerException e) {
      assertThat(e.getMessage()).isEqualTo("The given MouseClickInfo should not be null");
    }
  }

  @Test
  public void shouldShowJPopupMenuAtCell() {
    final JPopupMenu popup = popupMenu().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().showPopupMenuAt(target(), cell)).andReturn(popup);
      }

      @Override
      protected void codeToTest() {
        JPopupMenuFixture result = fixture().showPopupMenuAt(cell);
        assertThat(result.target()).isSameAs(popup);
      }
    }.run();
  }

  @Test
  public void shouldReturnJTableHeaderFixture() {
    final JTableHeader header = tableHeader().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().tableHeaderOf(target())).andReturn(header);
      }

      @Override
      protected void codeToTest() {
        JTableHeaderFixture tableHeader = fixture().tableHeader();
        assertThat(tableHeader.target()).isSameAs(header);
      }
    }.run();
  }

  @Test(expected = AssertionError.class)
  public void shouldThrowErrorIfJTableHeaderIsNull() {
    fixture().tableHeader();
  }

  @Test
  public void shouldSetCellReaderInDriver() {
    final JTableCellReader reader = createMock(JTableCellReader.class);
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

  @Test
  public void shouldSetCellWriterInDriver() {
    final JTableCellWriter writer = createMock(JTableCellWriter.class);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().cellWriter(writer);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().replaceCellWriter(writer));
      }
    }.run();
  }

  @Test
  public void shouldReturnCellFont() {
    final Font font = new Font("SansSerif", PLAIN, 8);
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().font(target(), cell)).andReturn(font);
      }

      @Override
      protected void codeToTest() {
        FontFixture fontFixture = fixture().fontAt(cell);
        assertThat(fontFixture.target()).isSameAs(font);
        assertThat(fontFixture.description()).contains(target().getClass().getName()).contains(
            "property:'font [row=6, column=8]'");
      }
    }.run();
  }

  @Test
  public void shouldReturnCellBackgroundColor() {
    final Color background = BLUE;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().background(target(), cell)).andReturn(background);
      }

      @Override
      protected void codeToTest() {
        ColorFixture colorFixture = fixture().backgroundAt(cell);
        assertThat(colorFixture.target()).isSameAs(background);
        assertThat(colorFixture.description()).contains(target().getClass().getName()).contains(
            "property:'background [row=6, column=8]'");
      }
    }.run();
  }

  @Test
  public void shouldReturnCellForegroundColor() {
    final Color foreground = BLUE;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().foreground(target(), cell)).andReturn(foreground);
      }

      @Override
      protected void codeToTest() {
        ColorFixture colorFixture = fixture().foregroundAt(cell);
        assertThat(colorFixture.target()).isSameAs(foreground);
        assertThat(colorFixture.description()).contains(target().getClass().getName()).contains(
            "property:'foreground [row=6, column=8]'");
      }
    }.run();
  }

  @Test
  public void shouldRequireCellValue() {
    final String value = "Hello";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireCellValue(target(), cell, value);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireCellValue(cell, value));
      }
    }.run();
  }

  @Test
  public void shouldRequireCellValueMatchingPattern() {
    final Pattern pattern = regex("Hello");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireCellValue(target(), cell, pattern);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireCellValue(cell, pattern));
      }
    }.run();
  }

  @Test
  public void shouldRequireEditableCell() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireEditable(target(), cell);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireEditable(cell));
      }
    }.run();
  }

  @Test
  public void shouldRequireNotEditableCell() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireNotEditable(target(), cell);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireNotEditable(cell));
      }
    }.run();
  }

  @Test
  public void shouldEnterValueInCell() {
    final String value = "Hello";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().enterValueInCell(target(), cell, value);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().enterValue(cell, value));
      }
    }.run();
  }

  @Test
  public void shouldReturnCell() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().checkCellIndicesInBounds(target(), cell);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        JTableCellFixture cellFixture = fixture().cell(cell);
        assertThat(cellFixture.table()).isSameAs(fixture());
        assertThat(cellFixture.cell()).isSameAs(cell);
      }
    }.run();
  }

  @Test
  public void shouldFindCellByValue() {
    final String value = "Hello";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().cell(target(), value)).andReturn(cell);
      }

      @Override
      protected void codeToTest() {
        TableCell result = fixture().cell(value);
        assertThat(result).isEqualTo(cell);
      }
    }.run();
  }

  @Test
  public void shouldFindCellByValueMatchingPattern() {
    final Pattern pattern = regex("Hello");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().cell(target(), pattern)).andReturn(cell);
      }

      @Override
      protected void codeToTest() {
        TableCell result = fixture().cell(pattern);
        assertThat(result).isEqualTo(cell);
      }
    }.run();
  }

  @Test
  public void shouldThrowErrorIfCellCannotBeFoundWithGivenValue() {
    final ActionFailedException expected = actionFailure("Thrown on purpose");
    final String value = "Hello";
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().cell(target(), value)).andThrow(expected);
      }

      @Override
      protected void codeToTest() {
        try {
          fixture().cell(value);
          failWhenExpectingException();
        } catch (ActionFailedException e) {
          assertThat(e).isSameAs(expected);
        }
      }
    }.run();
  }

  @Test
  public void shouldRequireRowCount() {
    final int rowCount = 6;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireRowCount(target(), rowCount);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireRowCount(rowCount));
      }
    }.run();
  }

  @Test
  public void shouldRequireColumnCount() {
    final int columnCount = 6;
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().requireColumnCount(target(), columnCount);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().requireColumnCount(columnCount));
      }
    }.run();
  }
}
