/*
 * Created on Feb 2, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.driver.CommonValidations.validateCellReader;
import static org.fest.swing.driver.CommonValidations.validateCellWriter;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.JTableCellEditableQuery.isCellEditable;
import static org.fest.swing.driver.JTableCellValidator.validateCellIndices;
import static org.fest.swing.driver.JTableCellValidator.validateIndices;
import static org.fest.swing.driver.JTableCellValidator.validateNotNull;
import static org.fest.swing.driver.JTableColumnByIdentifierQuery.columnIndexByIdentifier;
import static org.fest.swing.driver.JTableColumnCountQuery.columnCountOf;
import static org.fest.swing.driver.JTableContentsQuery.tableContents;
import static org.fest.swing.driver.JTableHasSelectionQuery.hasSelection;
import static org.fest.swing.driver.JTableHeaderQuery.tableHeader;
import static org.fest.swing.driver.JTableMatchingCellQuery.cellWithValue;
import static org.fest.swing.driver.JTableSingleRowCellSelectedQuery.isCellSelected;
import static org.fest.swing.driver.TextAssert.verifyThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.util.Arrays.equal;
import static org.fest.swing.util.Arrays.isEmptyIntArray;
import static org.fest.util.Arrays.format;
import static org.fest.util.Arrays.isEmpty;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import org.fest.assertions.Description;
import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JTableCellReader;
import org.fest.swing.cell.JTableCellWriter;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.Robot;
import org.fest.swing.data.TableCell;
import org.fest.swing.data.TableCellFinder;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.util.Arrays;
import org.fest.swing.util.Pair;
import org.fest.swing.util.PatternTextMatcher;
import org.fest.swing.util.StringTextMatcher;

/**
 * Understands functional testing of <code>{@link JTable}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JTableDriver extends JComponentDriver {

  private static final String CONTENTS_PROPERTY = "contents";
  private static final String EDITABLE_PROPERTY = "editable";
  private static final String SELECTION_PROPERTY = "selection";
  private static final String VALUE_PROPERTY = "value";

  private final JTableLocation location = new JTableLocation();
  private JTableCellReader cellReader;
  private JTableCellWriter cellWriter;

  /**
   * Creates a new </code>{@link JTableDriver}</code>.
   * @param robot the robot to use to simulate user events.
   */
  public JTableDriver(Robot robot) {
    super(robot);
    cellReader(new BasicJTableCellReader());
    cellWriter(new BasicJTableCellWriter(robot));
  }

  /**
   * Returns the <code>{@link JTableHeader}</code> of the given <code>{@link JTable}</code>.
   * @param table the given <code>JTable</code>.
   * @return the <code>JTableHeader</code> of the given <code>JTable</code>.
   */
  @RunsInEDT
  public JTableHeader tableHeaderOf(JTable table) {
    return tableHeader(table);
  }

  /**
   * Returns the <code>String</code> representation of the value of the selected cell, using this driver's
   * <code>{@link JTableCellReader}</code>.
   * @param table the target <code>JTable</code>.
   * @return the <code>String</code> representation of the value of the selected cell.
   * @see #cellReader(JTableCellReader)
   */
  @RunsInEDT
  public String selectionValue(JTable table) {
    return selectionValue(table, cellReader);
  }

  @RunsInEDT
  private static String selectionValue(final JTable table, final JTableCellReader cellReader) {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        if (table.getSelectedRowCount() == 0) return null;
        return cellReader.valueAt(table, table.getSelectedRow(), table.getSelectedColumn());
      }
    });
  }

  /**
   * Returns a cell from the given <code>{@link JTable}</code> using the given cell finder.
   * @param table the target <code>JTable</code>.
   * @param cellFinder knows how to find a cell.
   * @return the found cell, if any.
   * @throws NullPointerException if <code>cellFinder</code> is <code>null</code>.
   * @throws IndexOutOfBoundsException if the row or column indices in the found cell are out of bounds.
   * @throws ActionFailedException if a matching cell could not be found.
   */
  @RunsInEDT
  public TableCell cell(JTable table, TableCellFinder cellFinder) {
    if (cellFinder == null) throw new NullPointerException("The cell finder to use should not be null");
    TableCell cell = cellFinder.findCell(table);
    validateCellIndices(table, cell);
    return cell;
  }

  /**
   * Returns a cell from the given <code>{@link JTable}</code> whose value matches the given one.
   * @param table the target <code>JTable</code>.
   * @param value the value of the cell to look for. It can be a regular expression.
   * @return a cell from the given <code>JTable</code> whose value matches the given one.
   * @throws ActionFailedException if a cell with a matching value cannot be found.
   */
  @RunsInEDT
  public TableCell cell(JTable table, String value) {
    return cellWithValue(table, new StringTextMatcher(value), cellReader);
  }

  /**
   * Returns a cell from the given <code>{@link JTable}</code> whose value matches the given regular expression pattern.
   * @param table the target <code>JTable</code>.
   * @param pattern the regular expression pattern to match
   * @return a cell from the given <code>JTable</code> whose value matches the given one.
   * @throws NullPointerException if the given regular expression is <code>null</code>.
   * @throws ActionFailedException if a cell with a matching value cannot be found.
   * @since 1.2
   */
  @RunsInEDT
  public TableCell cell(JTable table, Pattern pattern) {
    return cellWithValue(table, new PatternTextMatcher(pattern), cellReader);
  }

  /**
   * Returns the <code>String</code> representation of the value at the given cell, using this driver's
   * <code>{@link JTableCellReader}</code>.
   * @param table the target <code>JTable</code>.
   * @param cell the table cell.
   * @return the <code>String</code> representation of the value at the given cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @see #cellReader(JTableCellReader)
   */
  @RunsInEDT
  public String value(JTable table, TableCell cell) {
    validateNotNull(cell);
    return cellValue(table, cell, cellReader);
  }

  @RunsInEDT
  private static String cellValue(final JTable table, final TableCell cell, final JTableCellReader cellReader) {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        validateCellIndices(table, cell);
        return cellReader.valueAt(table, cell.row, cell.column);
      }
    });
  }

  /**
   * Returns the <code>String</code> representation of the value at the given row and column, using this driver's
   * <code>{@link JTableCellReader}</code>.
   * @param table the target <code>JTable</code>.
   * @param row the given row.
   * @param column the given column.
   * @return the <code>String</code> representation of the value at the given row and column.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @see #cellReader(JTableCellReader)
   */
  @RunsInEDT
  public String value(JTable table, int row, int column) {
    return cellValue(table, row, column, cellReader);
  }

  @RunsInEDT
  private static String cellValue(final JTable table, final int row, final int column,
      final JTableCellReader cellReader) {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        validateIndices(table, row, column);
        return cellReader.valueAt(table, row, column);
      }
    });
  }

  /**
   * Returns the font of the given table cell.
   * @param table the target <code>JTable</code>.
   * @param cell the table cell.
   * @return the font of the given table cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  @RunsInEDT
  public Font font(JTable table, TableCell cell) {
    validateNotNull(cell);
    return cellFont(table, cell, cellReader);
  }

  @RunsInEDT
  private static Font cellFont(final JTable table, final TableCell cell, final JTableCellReader cellReader) {
    return execute(new GuiQuery<Font>() {
      protected Font executeInEDT() {
        validateCellIndices(table, cell);
        return cellReader.fontAt(table, cell.row, cell.column);
      }
    });
  }

  /**
   * Returns the background color of the given table cell.
   * @param table the target <code>JTable</code>.
   * @param cell the table cell.
   * @return the background color of the given table cell.
   * @throws ActionFailedException if the cell is <code>null</code>.
   * @throws ActionFailedException if any of the indices (row and column) is out of bounds.
   */
  @RunsInEDT
  public Color background(JTable table, TableCell cell) {
    validateNotNull(cell);
    return cellBackground(table, cell, cellReader);
  }

  @RunsInEDT
  private static Color cellBackground(final JTable table, final TableCell cell, final JTableCellReader cellReader) {
    return execute(new GuiQuery<Color>() {
      protected Color executeInEDT() {
        validateCellIndices(table, cell);
        return cellReader.backgroundAt(table, cell.row, cell.column);
      }
    });
  }

  /**
   * Returns the foreground color of the given table cell.
   * @param table the target <code>JTable</code>.
   * @param cell the table cell.
   * @return the foreground color of the given table cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  @RunsInEDT
  public Color foreground(JTable table, TableCell cell) {
    validateNotNull(cell);
    return cellForeground(table, cell, cellReader);
  }

  @RunsInEDT
  private static Color cellForeground(final JTable table, final TableCell cell, final JTableCellReader cellReader) {
    return execute(new GuiQuery<Color>() {
      protected Color executeInEDT() {
        validateCellIndices(table, cell);
        return cellReader.foregroundAt(table, cell.row, cell.column);
      }
    });
  }

  /**
   * Selects the given cells of the <code>{@link JTable}</code>.
   * @param table the target <code>JTable</code>.
   * @param cells the cells to select.
   * @throws NullPointerException if <code>cells</code> is <code>null</code> or empty.
   * @throws IllegalArgumentException if <code>cells</code> is <code>null</code> or empty.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws NullPointerException if any element in <code>cells</code> is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices of any of the <code>cells</code> are out of bounds.
   */
  public void selectCells(final JTable table, final TableCell[] cells) {
    validateCellsToSelect(cells);
    new MultipleSelectionTemplate(robot) {
      int elementCount() {
        return cells.length;
      }

      void selectElement(int index) {
        selectCell(table, cells[index]);
      }
    }.multiSelect();
  }

  private void validateCellsToSelect(final TableCell[] cells) {
    if (cells == null)  throw new NullPointerException("Array of table cells to select should not be null");
    if (isEmpty(cells)) throw new IllegalArgumentException("Array of table cells to select should not be empty");
  }

  /**
   * Verifies that the <code>{@link JTable}</code> does not have any selection.
   * @param table the target <code>JTable</code>.
   * @throws AssertionError is the <code>JTable</code> has a selection.
   */
  @RunsInEDT
  public void requireNoSelection(JTable table) {
    assertNoSelection(table);
  }

  @RunsInEDT
  private static void assertNoSelection(final JTable table) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        if (!hasSelection(table)) return;
        String message = concat("[", propertyName(table, SELECTION_PROPERTY).value(),
            "] expected no selection but was:<rows=", format(selectedRowsOf(table)), ", columns=",
            format(table.getSelectedColumns()), ">");
        fail(message);
      }
    });
  }

  /**
   * Selects the given cell, if it is not selected already.
   * @param table the target <code>JTable</code>.
   * @param cell the cell to select.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  @RunsInEDT
  public void selectCell(JTable table, TableCell cell) {
    validateNotNull(cell);
    selectCell(table, cell.row, cell.column);
  }

  /**
   * Clicks the given cell, using the specified mouse button, the given number of times.
   * @param table the target <code>JTable</code>.
   * @param cell the table cell.
   * @param mouseButton the mouse button to use.
   * @param times the number of times to click the cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  @RunsInEDT
  public void click(JTable table, TableCell cell, MouseButton mouseButton, int times) {
    if (times <= 0)
      throw new IllegalArgumentException("The number of times to click a cell should be greater than zero");
    Point pointAtCell = scrollToPointAtCell(table, cell, location);
    robot.click(table, pointAtCell, mouseButton, times);
  }

  /**
   * Starts a drag operation at the location of the given table cell.
   * @param table the target <code>JTable</code>.
   * @param cell the table cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  @RunsInEDT
  public void drag(JTable table, TableCell cell) {
    Point pointAtCell = scrollToPointAtCell(table, cell, location);
    drag(table, pointAtCell);
  }

  /**
   * Starts a drop operation at the location of the given table cell.
   * @param table the target <code>JTable</code>.
   * @param cell the table cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  @RunsInEDT
  public void drop(JTable table, TableCell cell) {
    Point pointAtCell = scrollToPointAtCell(table, cell, location);
    drop(table, pointAtCell);
  }

  /**
   * Shows a pop-up menu at the given table cell.
   * @param table the target <code>JTable</code>.
   * @param cell the table cell.
   * @return the displayed pop-up menu.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  @RunsInEDT
  public JPopupMenu showPopupMenuAt(JTable table, TableCell cell) {
    Point pointAtCell = scrollToPointAtCell(table, cell, location);
    return robot.showPopupMenu(table, pointAtCell);
  }

  @RunsInEDT
  private static Point scrollToPointAtCell(final JTable table, final TableCell cell, final JTableLocation location) {
    validateNotNull(cell);
    return execute(new GuiQuery<Point>() {
      protected Point executeInEDT() {
        scrollToCell(table, cell, location);
        return location.pointAt(table, cell.row, cell.column);
      }
    });
  }

  @RunsInCurrentThread
  private static void scrollToCell(final JTable table, final TableCell cell, final JTableLocation location) {
    validateIsEnabledAndShowing(table);
    validateCellIndices(table, cell);
    table.scrollRectToVisible(location.cellBounds(table, cell));
  }

  /**
   * Converts the given table cell into a coordinate pair.
   * @param table the target <code>JTable</code>.
   * @param cell the table cell.
   * @return the coordinates of the given row and column.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  @RunsInEDT
  public Point pointAt(JTable table, TableCell cell) {
    return pointAtCell(table, cell, location);
  }

  @RunsInEDT
  private static Point pointAtCell(final JTable table, final TableCell cell, final JTableLocation location) {
    return execute(new GuiQuery<Point>() {
      protected Point executeInEDT() {
        validateCellIndices(table, cell);
        return location.pointAt(table, cell.row, cell.column);
      }
    });
  }

  /**
   * Asserts that the <code>String</code> representation of the cell values in the <code>{@link JTable}</code> is
   * equal to the given <code>String</code> array. This method uses this driver's
   * <code>{@link JTableCellReader}</code> to read the values of the table cells as <code>String</code>s.
   * @param table the target <code>JTable</code>.
   * @param contents the expected <code>String</code> representation of the cell values in the <code>JTable</code>.
   * @see #cellReader(JTableCellReader)
   */
  @RunsInEDT
  public void requireContents(JTable table, String[][] contents) {
    String[][] actual = contents(table);

    if (!equal(actual, contents))
      failNotEqual(actual, contents, propertyName(table, CONTENTS_PROPERTY));
  }

  private static void failNotEqual(String[][] actual, String[][] expected, Description description) {
    String descriptionValue = description != null ? description.value() : null;
    String message = descriptionValue == null ? "" : concat("[", descriptionValue, "]");
    fail(concat(message, " expected:<", Arrays.format(expected), "> but was:<", Arrays.format(actual), ">"));
  }

  /**
   * Returns the <code>String</code> representation of the cells in the <code>{@link JTable}</code>, using this
   * driver's <code>{@link JTableCellReader}</code>.
   * @param table the target <code>JTable</code>.
   * @return the <code>String</code> representation of the cells in the <code>JTable</code>.
   * @see #cellReader(JTableCellReader)
   */
  @RunsInEDT
  public String[][] contents(JTable table) {
    return tableContents(table, cellReader);
  }

  /**
   * Asserts that the value of the given cell matches the given value.
   * @param table the target <code>JTable</code>.
   * @param cell the given table cell.
   * @param value the expected value. It can be a regular expression.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws AssertionError if the value of the given cell does not match the given value.
   */
  @RunsInEDT
  public void requireCellValue(JTable table, TableCell cell, String value) {
    verifyThat(value(table, cell)).as(cellValueProperty(table, cell)).isEqualOrMatches(value);
  }

  /**
   * Asserts that the value of the given cell matches the given regular expression pattern.
   * @param table the target <code>JTable</code>.
   * @param cell the given table cell.
   * @param pattern the regular expression pattern to match.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the value of the given cell does not match the given regular expression pattern.
   * @since 1.2
   */
  @RunsInEDT
  public void requireCellValue(JTable table, TableCell cell, Pattern pattern) {
    verifyThat(value(table, cell)).as(cellValueProperty(table, cell)).matches(pattern);
  }

  @RunsInEDT
  private Description cellValueProperty(JTable table, TableCell cell) {
    return cellProperty(table, concat(VALUE_PROPERTY, " ", cell));
  }

  /**
   * Enters the given value in the given cell of the <code>{@link JTable}</code>, using this driver's
   * <code>{@link JTableCellWriter}</code>.
   * @param table the target <code>JTable</code>.
   * @param cell the given cell.
   * @param value the given value.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JTable</code> cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if this driver's <code>JTableCellValueReader</code> is unable to enter the given
   * value.
   * @see #cellWriter(JTableCellWriter)
   */
  @RunsInEDT
  public void enterValueInCell(JTable table, TableCell cell, String value) {
    validateNotNull(cell);
    cellWriter.enterValue(table, cell.row, cell.column, value);
  }

  /**
   * Asserts that the given table cell is editable.
   * @param table the target <code>JTable</code>.
   * @param cell the given table cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws AssertionError if the given table cell is not editable.
   */
  @RunsInEDT
  public void requireEditable(JTable table, TableCell cell) {
    requireEditableEqualTo(table, cell, true);
  }

  /**
   * Asserts that the given table cell is not editable.
   * @param table the target <code>JTable</code>.
   * @param cell the given table cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws AssertionError if the given table cell is editable.
   */
  @RunsInEDT
  public void requireNotEditable(JTable table, TableCell cell) {
    requireEditableEqualTo(table, cell, false);
  }

  @RunsInEDT
  private static void requireEditableEqualTo(final JTable table, final TableCell cell, boolean editable) {
    validateNotNull(cell);
    boolean cellEditable = execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        return isCellEditable(table, cell);
      }
    });
    assertThat(cellEditable).as(cellProperty(table, concat(EDITABLE_PROPERTY, " ", cell))).isEqualTo(editable);
  }

  @RunsInEDT
  private static Description cellProperty(JTable table, String propertyName) {
    return propertyName(table, propertyName);
  }

  /**
   * Returns the editor in the given cell of the <code>{@link JTable}</code>, using this driver's
   * <code>{@link JTableCellWriter}</code>.
   * @param table the target <code>JTable</code>.
   * @param cell the given cell.
   * @return the editor in the given cell of the <code>JTable</code>.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if the <code>JTable</code> cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @see #cellWriter(JTableCellWriter)
   */
  @RunsInEDT
  public Component cellEditor(JTable table, TableCell cell) {
    validateNotNull(cell);
    return cellWriter.editorForCell(table, cell.row, cell.column);
  }

  /**
   * Starts editing the given cell of the <code>{@link JTable}</code>, using this driver's
   * <code>{@link JTableCellWriter}</code>. This method should be called before manipulating the
   * <code>{@link Component}</code> returned by <code>{@link #cellEditor(JTable, TableCell)}</code>.
   * @param table the target <code>JTable</code>.
   * @param cell the given cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JTable</code> cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if this writer is unable to handle the underlying cell editor.
   * @see #cellWriter(JTableCellWriter)
   */
  @RunsInEDT
  public void startCellEditing(JTable table, TableCell cell) {
    validateNotNull(cell);
    cellWriter.startCellEditing(table, cell.row, cell.column);
  }

  /**
   * Stops editing the given cell of the <code>{@link JTable}</code>, using this driver's
   * <code>{@link JTableCellWriter}</code>. This method should be called after manipulating the
   * <code>{@link Component}</code> returned by <code>{@link #cellEditor(JTable, TableCell)}</code>.
   * @param table the target <code>JTable</code>.
   * @param cell the given cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JTable</code> cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if this writer is unable to handle the underlying cell editor.
   * @see #cellWriter(JTableCellWriter)
   */
  @RunsInEDT
  public void stopCellEditing(JTable table, TableCell cell) {
    validateNotNull(cell);
    cellWriter.stopCellEditing(table, cell.row, cell.column);
  }

  /**
   * Cancels editing the given cell of the <code>{@link JTable}</code>, using this driver's
   * <code>{@link JTableCellWriter}</code>. This method should be called after manipulating the
   * <code>{@link Component}</code> returned by <code>{@link #cellEditor(JTable, TableCell)}</code>.
   * @param table the target <code>JTable</code>.
   * @param cell the given cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JTable</code> cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if this writer is unable to handle the underlying cell editor.
   * @see #cellWriter(JTableCellWriter)
   */
  @RunsInEDT
  public void cancelCellEditing(JTable table, TableCell cell) {
    validateNotNull(cell);
    cellWriter.cancelCellEditing(table, cell.row, cell.column);
  }

  /**
   * Validates that the given table cell is non <code>null</code> and its indices are not out of bounds.
   * @param table the target <code>JTable</code>.
   * @param cell to validate.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  @RunsInEDT
  public void validate(JTable table, TableCell cell) {
    validateCellIndexBounds(table, cell);
  }

  private static void validateCellIndexBounds(final JTable table, final TableCell cell) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        validateCellIndices(table, cell);
      }
    });
  }

  /**
   * Updates the implementation of <code>{@link JTableCellReader}</code> to use when comparing internal values of a
   * <code>{@link JTable}</code> and the values expected in a test.
   * @param newCellReader the new <code>JTableCellValueReader</code> to use.
   * @throws NullPointerException if <code>newCellReader</code> is <code>null</code>.
   */
  public void cellReader(JTableCellReader newCellReader) {
    validateCellReader(newCellReader);
    cellReader = newCellReader;
  }

  /**
   * Updates the implementation of <code>{@link JTableCellWriter}</code> to use to edit cell values in a
   * <code>{@link JTable}</code>.
   * @param newCellWriter the new <code>JTableCellWriter</code> to use.
   * @throws NullPointerException if <code>newCellWriter</code> is <code>null</code>.
   */
  public void cellWriter(JTableCellWriter newCellWriter) {
    validateCellWriter(newCellWriter);
    cellWriter = newCellWriter;
  }

  /**
   * Returns the number of rows that can be shown in the given <code>{@link JTable}</code>, given unlimited space.
   * @param table the target <code>JTable</code>.
   * @return the number of rows shown in the given <code>JTable</code>.
   * @see JTable#getRowCount()
   */
  @RunsInEDT
  public int rowCountOf(JTable table) {
    return JTableRowCountQuery.rowCountOf(table);
  }

  /**
   * Returns the index of the column in the given <code>{@link JTable}</code> whose id matches the given one.
   * @param table the target <code>JTable</code>.
   * @param columnId the id of the column to look for.
   * @return the index of the column whose id matches the given one.
   * @throws ActionFailedException if a column with a matching id could not be found.
   */
  @RunsInEDT
  public int columnIndex(JTable table, Object columnId) {
    return findColumnIndex(table, columnId);
  }

  @RunsInEDT
  private static int findColumnIndex(final JTable table, final Object columnId) {
    return execute(new GuiQuery<Integer>() {
      protected Integer executeInEDT() {
        int index = columnIndexByIdentifier(table, columnId);
        if (index < 0) failColumnIndexNotFound(columnId);
        return index;
      }
    });
  }

  private static ActionFailedException failColumnIndexNotFound(Object columnId) {
    throw actionFailure(concat("Unable to find a column with id ", quote(columnId)));
  }

  /**
   * Asserts that the given <code>{@link JTable}</code> has the given number of rows.
   * @param table the target <code>JTable</code>.
   * @param rowCount the expected number of rows.
   * @throws AssertionError if the given <code>JTable</code> does not have the given number of rows.
   */
  @RunsInEDT
  public void requireRowCount(JTable table, int rowCount) {
    assertThat(rowCountOf(table)).as(propertyName(table, "rowCount")).isEqualTo(rowCount);
  }

  /**
   * Asserts that the given <code>{@link JTable}</code> has the given number of columns.
   * @param table the target <code>JTable</code>.
   * @param columnCount the expected number of columns.
   * @throws AssertionError if the given <code>JTable</code> does not have the given number of columns.
   */
  @RunsInEDT
  public void requireColumnCount(JTable table, int columnCount) {
    assertThat(columnCountOf(table)).as(propertyName(table, "columnCount")).isEqualTo(columnCount);
  }

  /**
   * Returns the index of the first selected row in the given <code>{@link JTable}</code>.
   * @param table the target <code>JTable</code>.
   * @return the index of the first selected row in the <code>JTable</code>.
   * @throws AssertionError if the <code>JTable</code> does not have any selection.
   * @since 1.2
   */
  public int selectedRow(JTable table) {
    int selectedRow = selectedRowOf(table);
    assertThat(selectedRow).as(propertyName(table, "selectedRow")).isGreaterThanOrEqualTo(0);
    return selectedRow;
  }

  @RunsInEDT
  private static int selectedRowOf(final JTable table) {
    return execute(new GuiQuery<Integer>() {
      protected Integer executeInEDT() {
        return table.getSelectedRow();
      }
    });
  }

  @RunsInEDT
  private static int[] selectedRowsOf(final JTable table) {
    return execute(new GuiQuery<int[]>() {
      protected int[] executeInEDT() {
        return table.getSelectedRows();
      }
    });
  }

  /**
   * Simulates a user selecting the given rows in the given <code>{@link JTable}</code>.
   * @param table the target <code>JTable</code>.
   * @param rows the indices of the row to select.
   * @throws NullPointerException if the given array of indices is <code>null</code>.
   * @throws IllegalArgumentException if the given array of indices is empty.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the given indices is negative, or equal to or greater than the number
   * of rows in the <code>JTable</code>.
   * @since 1.2
   */
  @RunsInEDT
  public void selectRows(final JTable table, final int... rows) {
    if (rows == null) throw new NullPointerException("The array of row indices should not be null");
    if (isEmptyIntArray(rows)) throw new IllegalArgumentException("The array of row indices should not be empty");
    new MultipleSelectionTemplate(robot) {
      int elementCount() {
        return rows.length;
      }

      void selectElement(int index) {
        selectCell(table, rows[index], 0);
      }
    }.multiSelect();
  }

  @RunsInEDT
  private void selectCell(JTable table, int row, int column) {
    Pair<Boolean, Point> cellSelectionInfo = cellSelectionInfo(table, row, column, location);
    if (cellSelectionInfo.i) return; // cell already selected
    robot.click(table, cellSelectionInfo.ii, LEFT_BUTTON, 1);
  }

  @RunsInEDT
  private static Pair<Boolean, Point> cellSelectionInfo(final JTable table, final int row, final int column,
      final JTableLocation location) {
    return execute(new GuiQuery<Pair<Boolean, Point>>() {
      protected Pair<Boolean, Point> executeInEDT() {
        if (isCellSelected(table, row, column)) return new Pair<Boolean, Point>(true, null);
        scrollToCell(table, row, column, location);
        Point pointAtCell = location.pointAt(table, row, column);
        return new Pair<Boolean, Point>(false, pointAtCell);
      }
    });
  }

  @RunsInCurrentThread
  private static void scrollToCell(final JTable table, final int row, final int column, final JTableLocation location) {
    validateIsEnabledAndShowing(table);
    validateIndices(table, row, column);
    table.scrollRectToVisible(location.cellBounds(table, row, column));
  }

  /**
   * Asserts that the indices of the selected rows in the given <code>{@link JTable}</code> are equal to the given ones.
   * @param table the target <code>JTable</code>.
   * @param rows the indices of the rows expected to be selected.
   * @throws AssertionError if the selected rows in the given <code>JTable</code> (if any) are not equal to the given
   * ones.
   * @since 1.2
   */
  @RunsInEDT
  public void requireSelectedRows(JTable table, int[] rows) {
    // TODO Auto-generated method stub

  }
}
