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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import static java.awt.Color.BLUE;
import static java.awt.Font.PLAIN;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.core.MouseButton.RIGHT_BUTTON;
import static org.fest.swing.core.MouseClickInfo.leftButton;
import static org.fest.swing.data.TableCell.row;
import static org.fest.swing.test.builder.JTables.table;
import static org.fest.swing.test.builder.JTextFields.textField;
import static org.fest.swing.test.core.Regex.regex;

import java.awt.Component;
import java.awt.Font;
import java.util.regex.Pattern;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.core.Robot;
import org.fest.swing.data.TableCell;
import org.fest.swing.driver.JTableDriver;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableCellFixture}</code>.
 * @author Alex Ruiz
 */
public class JTableCellFixtureTest {

  private JTableFixture table;
  private TableCell cell;
  private JTableCellFixture fixture;

  @Before
  public void setUp() {
    table = createMock(JTableFixture.class);
    cell = row(8).column(6);
    fixture = new JTableCellFixture(table, cell);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorIfJTableFixtureIsNull() {
    new JTableCellFixture(null, cell);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorIfCellIsNull() {
    new JTableCellFixture(table, null);
  }

  @Test
  public void shouldSelect() {
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.selectCell(cell)).andReturn(table);
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.select());
      }
    }.run();
  }

  @Test
  public void shouldClick() {
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.click(cell, LEFT_BUTTON)).andReturn(table);
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.click());
      }
    }.run();
  }

  @Test
  public void shouldClickUsingMouseClickInfo() {
    final MouseClickInfo mouseClickInfo = leftButton().times(2);
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.click(cell, mouseClickInfo)).andReturn(table);
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.click(mouseClickInfo));
      }
    }.run();
  }

  @Test
  public void shouldDoubleClick() {
    new EasyMockTemplate(table) {
      protected void expectations() {
        table.click(cell, LEFT_BUTTON, 2);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.doubleClick());
      }
    }.run();
  }

  @Test
  public void shouldRightClick() {
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.click(cell, RIGHT_BUTTON)).andReturn(table);
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.rightClick());
      }
    }.run();
  }

  @Test
  public void shouldShowPopupMenu() {
    final JPopupMenuFixture popup = createMock(JPopupMenuFixture.class);
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.showPopupMenuAt(cell)).andReturn(popup);
      }

      protected void codeToTest() {
        JPopupMenuFixture result = fixture.showPopupMenu();
        assertThat(result).isSameAs(popup);
      }
    }.run();
  }

  @Test
  public void shouldReturnContents() {
    final String content = "Hello";
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.valueAt(cell)).andReturn(content);
      }

      protected void codeToTest() {
        Object result = fixture.value();
        assertThat(result).isSameAs(content);
      }
    }.run();
  }

  @Test
  public void shouldPassIfContentIsEqualToExpected() {
    final String content = "Hello";
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.requireCellValue(cell, content)).andReturn(table);
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.requireValue(content));
      }
    }.run();
  }

  @Test
  public void shouldPassIfContentMatchesPattern() {
    final Pattern pattern = regex("Hello");
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.requireCellValue(cell, pattern)).andReturn(table);
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.requireValue(pattern));
      }
    }.run();
  }

  @Test
  public void shouldReturnFontForCell() {
    final FontFixture fontFixture = new FontFixture(new Font("SansSerif", PLAIN, 8));
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.fontAt(cell)).andReturn(fontFixture);
      }

      protected void codeToTest() {
        FontFixture result = fixture.font();
        assertThat(result).isSameAs(fontFixture);
      }
    }.run();
  }

  @Test
  public void shouldReturnBackgroundColorForCell() {
    final ColorFixture colorFixture = new ColorFixture(BLUE);
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.backgroundAt(cell)).andReturn(colorFixture);
      }

      protected void codeToTest() {
        ColorFixture result = fixture.background();
        assertThat(result).isSameAs(colorFixture);
      }
    }.run();
  }

  @Test
  public void shouldReturnForegroundColorForCell() {
    final ColorFixture colorFixture = new ColorFixture(BLUE);
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.foregroundAt(cell)).andReturn(colorFixture);
      }

      protected void codeToTest() {
        ColorFixture result = fixture.foreground();
        assertThat(result).isSameAs(colorFixture);
      }
    }.run();
  }

  @Test
  public void shouldDrag() {
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.drag(cell)).andReturn(table);
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.drag());
      }
    }.run();
  }

  @Test
  public void shouldDrop() {
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.drop(cell)).andReturn(table);
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.drop());
      }
    }.run();
  }

  @Test
  public void shouldRequireEditableCell() {
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.requireEditable(cell)).andReturn(table);
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.requireEditable());
      }
    }.run();
  }

  @Test
  public void shouldRequireNotEditableCell() {
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.requireNotEditable(cell)).andReturn(table);
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.requireNotEditable());
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

  void assertThatReturnsThis(JTableCellFixture result) {
    assertThat(result).isSameAs(fixture);
  }

  @Test
  public void shouldRequireValue() {
    final String value = "Hello";
    new EasyMockTemplate(table) {
      protected void expectations() {
        expect(table.requireCellValue(cell, value)).andReturn(table);
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.requireValue(value));
      }
    }.run();
  }

  @Test
  public void shouldStartCellEditing() {
    table = tableFixtureStub();
    fixture = new JTableCellFixture(table, cell);
    final JTableDriver driver = table.driver();
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.startCellEditing(table.target, cell);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.startEditing());
      }
    }.run();
  }

  @Test
  public void shouldStopCellEditing() {
    table = tableFixtureStub();
    fixture = new JTableCellFixture(table, cell);
    final JTableDriver driver = table.driver();
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.stopCellEditing(table.target, cell);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.stopEditing());
      }
    }.run();
  }

  @Test
  public void shouldCancelCellEditing() {
    table = tableFixtureStub();
    fixture = new JTableCellFixture(table, cell);
    final JTableDriver driver = table.driver();
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.cancelCellEditing(table.target, cell);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.cancelEditing());
      }
    }.run();
  }

  @Test
  public void shouldReturnCellEditor() {
    table = tableFixtureStub();
    fixture = new JTableCellFixture(table, cell);
    final JTableDriver driver = table.driver();
    final Component editor = textField().createNew();
    new EasyMockTemplate(driver) {
      protected void expectations() {
        expect(driver.cellEditor(table.target, cell)).andReturn(editor);
      }

      protected void codeToTest() {
        Component result = fixture.editor();
        assertThat(result).isSameAs(editor);
      }
    }.run();
  }

  @Test
  public void shouldEnterValue() {
    table = tableFixtureStub();
    fixture = new JTableCellFixture(table, cell);
    final JTableDriver driver = table.driver();
    final String value = "Hello";
    new EasyMockTemplate(driver) {
      protected void expectations() {
        driver.enterValueInCell(table.target, cell, value);
        expectLastCall().once();
      }

      protected void codeToTest() {
        assertThatReturnsThis(fixture.enterValue(value));
      }
    }.run();
  }

  private JTableFixture tableFixtureStub() {
    JTableFixture tableFixture = new JTableFixture(createMock(Robot.class), table().createNew());
    tableFixture.driver(createMock(JTableDriver.class));
    return tableFixture;
  }
}
