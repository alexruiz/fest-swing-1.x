/*
 * Created on Dec 28, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.data;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.util.Arrays.format;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Strings.concat;

import javax.swing.JTable;

import org.fest.swing.annotation.*;
import org.fest.swing.cell.JTableCellReader;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.ActionFailedException;

/**
 * Understands lookup of a cell in the first row in <code>{@link JTable}</code> whose values match the given ones.
 * <p>
 * Example:
 * <pre>
 * // import static org.fest.swing.data.TableCellInSelectedRow.row;
 * <code>{@link TableCell}</code> cell = dialog.table("records").cell({@link TableCellInRowByValue#rowWithValue(String...) rowWithValue}("column1", "column2", "column3").column(2));
 * </pre>
 * </p>
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public class TableCellInRowByValue implements TableCellFinder {

  /**
   * Starting point for the creation of a <code>{@link TableCellInRowByValue}</code>.
   * <p>
   * Example:
   * <pre>
   * // import static org.fest.swing.data.TableCellInRowByValue.rowWithValue;
   * TableCellByColumnId cell = rowWithValue("column1", "column2", "column3").column(3);
   * </pre>
   * </p>
   * @param values the values in the cells of the row we are looking for.
   * @return the created builder.
   * @throws NullPointerException if the given array of values is {@code null}.
   */
  public static TableCellBuilder rowWithValue(String...values) {
    if (values == null) throw new NullPointerException("The array of values should not be null");
    return new TableCellBuilder(values);
  }

  /**
   * Understands creation of <code>{@link TableCellInSelectedRow}</code>s.
   *
   * @author Alex Ruiz
   */
  public static class TableCellBuilder {
    private final String[] values;

    /**
     * Creates a new </code>{@link TableCellBuilder}</code>.
     * @param values the values of the cells of the row to find.
     */
    TableCellBuilder(String[] values) {
      this.values = values;
    }

    /**
     * Creates a new table cell finder using the row cell values specified in
     * <code>{@link TableCellInRowByValue#rowWithValue(String...)}</code>
     * and the column index specified as the argument in this method.
     * @param column the index of the column in the table cell to find.
     * @return the created finder.
     */
    public TableCellInRowByValue column(int column) {
      return new TableCellInRowByValue(values, column);
    }
  }

  private final String[] values;
  private final int column;

  /**
   * Creates a new </code>{@link TableCellInRowByValue}</code>.
   * @param values the values in the cells of the row we are looking for.
   * @param column the index of the column in the table cell to find.
   */
  protected TableCellInRowByValue(String[] values, int column) {
    this.values = values;
    this.column = column;
  }

  /**
   * Finds a cell in the given <code>{@link JTable}</code> that:
   * <ol>
   * <li>is located in the first row whose values match the given ones</li>
   * <li>has a matching row index</li>
   * </ol>
   * @param table the target <code>JTable</code>.
   * @param cellReader knows how to read the contents of a cell in a <code>JTable</code>.
   * @return the cell found, if any.
   * @throws IllegalStateException if the size of values to look up is not equal to the number of columns in the given
   * <code>JTable</code>.
   * @throws ActionFailedException if a matching cell could not be found.
   */
  @RunsInEDT
  public TableCell findCell(JTable table, JTableCellReader cellReader) {
    int row = findRowIndex(table, cellReader, values);
    if (row == -1)
      throw actionFailure(concat("Unable to find a row with values:<", format(values), ">"));
    return new TableCell(row, column);
  }

  @RunsInEDT
  private static int findRowIndex(final JTable table, final JTableCellReader cellReader, final String[] values) {
    return execute(new GuiQuery<Integer>() {
      @Override
      protected Integer executeInEDT() {
        validateEqualSize(table, values);
        int rowCount = table.getRowCount();
        for (int row = 0; row < rowCount; row++)
          if (matchingRow(table, cellReader, values, row)) return row;
        return -1;
      }

    });
  }

  @RunsInCurrentThread
  private static void validateEqualSize(final JTable table, final String[] values) {
    int columnCount = table.getColumnCount();
    if (values.length != columnCount)
      throw new IllegalStateException(concat("The array of values should have size:<", columnCount,">"));
  }

  @RunsInCurrentThread
  private static boolean matchingRow(JTable table, JTableCellReader cellReader, String[] values, int row) {
    int columnCount = table.getColumnCount();
    for (int col = 0; col < columnCount; col++) {
      if (!areEqual(cellReader.valueAt(table, row, col), values[col])) return false;
    }
    return true;
  }

  @Override public String toString() {
    return concat(getClass().getName(), "[values=", format(values), ", column=", column, "]");
  }
}
