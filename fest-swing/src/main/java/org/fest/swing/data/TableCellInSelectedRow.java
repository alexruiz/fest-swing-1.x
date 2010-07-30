/*
 * Created on Dec 25, 2009
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

import static java.lang.String.valueOf;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.util.Strings.concat;

import javax.swing.JTable;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JTableCellReader;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.ActionFailedException;

/**
 * Understands lookup of a cell in the first selected row of a <code>{@link JTable}</code>.
 * <p>
 * Example:
 * <pre>
 * // import static org.fest.swing.data.TableCellInSelectedRow.row;
 * <code>{@link TableCell}</code> cell = dialog.table("records").cell({@link TableCellInSelectedRow#selectedRow() selectedRow}().column(2));
 * </pre>
 * </p>
 *
 * @author Alex Ruiz
 *
 */
public class TableCellInSelectedRow implements TableCellFinder {

  /**
   * Starting point for the creation of a <code>{@link TableCellInSelectedRow}</code>.
   * <p>
   * Example:
   * <pre>
   * // import static org.fest.swing.data.TableCellInSelectedRow.row;
   * TableCellInSelectedRow cell = selectedRow().column(2);
   * </pre>
   * </p>
   * @return the created builder.
   */
  public static TableCellBuilder selectedRow() { return new TableCellBuilder(); }

  /**
   * Understands creation of <code>{@link TableCellInSelectedRow}</code>s.
   *
   * @author Alex Ruiz
   */
  public static class TableCellBuilder {

    /**
     * Creates a new table cell finder.
     * @param column the column index of the cell to find.
     * @return the created finder.
     */
    public TableCellInSelectedRow column(int column) {
      return new TableCellInSelectedRow(column);
    }
  }

  private final int column;

  protected TableCellInSelectedRow(int column) {
    this.column = column;
  }

  /**
   * Finds a cell in the given <code>{@link JTable}</code> that belongs to the first selected row and has a matching
   * column index.
   * @param table the target <code>JTable</code>.
   * @param cellReader knows how to read the contents of a cell in a <code>JTable</code>.
   * @return the cell found, if any.
   * @throws ActionFailedException if a matching cell could not be found.
   */
  public TableCell findCell(JTable table, JTableCellReader cellReader) {
    int selectedRow = selectedRowOf(table);
    if (selectedRow == -1) throw actionFailure("The given JTable does not have any selection");
    return new TableCell(selectedRow, column);
  }

  @RunsInEDT
  private static int selectedRowOf(final JTable table) {
    return execute(new GuiQuery<Integer>() {
      @Override
      protected Integer executeInEDT() {
        return table.getSelectedRow();
      }
    });
  }

  @Override public String toString() {
    return concat(getClass().getName(), "[column=", valueOf(column), "]");
  }
}
