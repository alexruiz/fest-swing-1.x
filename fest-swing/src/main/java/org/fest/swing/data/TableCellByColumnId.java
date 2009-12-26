/*
 * Created on Oct 13, 2007
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
package org.fest.swing.data;

import static java.lang.String.valueOf;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.query.JTableColumnByIdentifierQuery.columnIndexByIdentifier;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import javax.swing.JTable;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.ActionFailedException;

/**
 * Understands lookup of a cell in a <code>{@link JTable}</code> by column identifier.
 * <p>
 * Example:
 * <pre>
 * // import static org.fest.swing.data.TableCellByColumnId.row;
 * JTableCellFixture cell = dialog.table("records").cell({@link TableCellByColumnId#row(int) row}(3).columnId("firstColumn"));
 * </pre>
 * </p>
 *
 * @author Alex Ruiz
 */
public class TableCellByColumnId implements TableCellFinder {

  public final int row;
  public final Object columnId;

  /**
   * Starting point for the creation of a <code>{@link TableCellByColumnId}</code>.
   * <p>
   * Example:
   * <pre>
   * // import static org.fest.swing.data.TableCellByColumnId.row;
   * TableCellByColumnId cell = row(5).columnId("hobbyColumn");
   * </pre>
   * </p>
   * @param row the row index of the table cell to find.
   * @return the created builder.
   */
  public static TableCellBuilder row(int row) { return new TableCellBuilder(row); }

  /**
   * Understands creation of <code>{@link TableCellByColumnId}</code>s.
   *
   * @author Alex Ruiz
   */
  public static class TableCellBuilder {
    private final int row;

    TableCellBuilder(int row) { this.row = row; }

    /**
     * Creates a new table cell finder using the row index specified in <code>{@link TableCellBuilder#row(int)}</code>
     * and the column index specified as the argument in this method.
     * @param columnId the name of the column in the table cell to find.
     * @return the created finder.
     */
    public TableCellByColumnId columnId(Object columnId) {
      return new TableCellByColumnId(row, columnId);
    }
  }

  protected TableCellByColumnId(int row, Object columnId) {
    this.row = row;
    this.columnId = columnId;
  }

  /**
   * Finds a cell in the given <code>{@link JTable}</code> that has a matching row index and column id.
   * @param table the target <code>JTable</code>.
   * @return the cell found, if any.
   * @throws ActionFailedException if a matching cell could not be found.
   */
  @RunsInEDT
  public TableCell findCell(JTable table) {
    return findCell(table, row, columnId);
  }

  @RunsInEDT
  private static TableCell findCell(final JTable table, final int row, final Object columnId) {
    return execute(new GuiQuery<TableCell>() {
      protected TableCell executeInEDT() {
        int column = columnIndexByIdentifier(table, columnId);
        if (column == -1) failColumnIndexNotFound(columnId);
        table.convertColumnIndexToView(table.getColumn(columnId).getModelIndex());
        return new TableCell(row, column);
      }
    });
  }

  private static ActionFailedException failColumnIndexNotFound(Object columnId) {
    throw actionFailure(concat("Unable to find a column with id ", quote(columnId)));
  }

  @Override public String toString() {
    return concat(getClass().getName(), "[row=", valueOf(row), ", columnId=", quote(columnId), "]");
  }
}