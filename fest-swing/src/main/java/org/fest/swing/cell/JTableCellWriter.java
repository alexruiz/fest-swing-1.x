/*
 * Created on Jun 8, 2008
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
package org.fest.swing.cell;

import java.awt.Component;

import javax.swing.JTable;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.exception.ActionFailedException;

/**
 * Understands how to edit the value of a cell in a <code>{@link JTable}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
@RunsInEDT
public interface JTableCellWriter {

  /**
   * Enters the given value at the given cell of the <code>{@link JTable}</code>. To edit a cell using this method,
   * it is not necessary to call <code>{@link #startCellEditing(JTable, int, int)}</code> or
   * <code>{@link #stopCellEditing(JTable, int, int)}</code>.
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @param value the value to enter.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JTable</code> cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if an editor for the given cell cannot be found or cannot be activated.
   */
  void enterValue(JTable table, int row, int column, String value);

  /**
   * Starts editing the given cell of the <code>{@link JTable}</code>. This method should be called before manipulating
   * the <code>{@link Component}</code> returned by <code>{@link #editorForCell(JTable, int, int)}</code>.
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JTable</code> cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if an editor for the given cell cannot be found or cannot be activated.
   * @see #editorForCell(JTable, int, int)
   */
  void startCellEditing(JTable table, int row, int column);

  /**
   * Stops editing the given cell of the <code>{@link JTable}</code>. This method should be called after manipulating
   * the <code>{@link Component}</code> returned by <code>{@link #editorForCell(JTable, int, int)}</code>.
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JTable</code> cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if an editor for the given cell cannot be found or cannot be activated.
   * @see #editorForCell(JTable, int, int)
   */
  void stopCellEditing(JTable table, int row, int column);

  /**
   * Cancels editing the given cell of the <code>{@link JTable}</code>. This method should be called after manipulating
   * the <code>{@link Component}</code> returned by <code>{@link #editorForCell(JTable, int, int)}</code>.
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JTable</code> cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if an editor for the given cell cannot be found or cannot be activated.
   * @see #editorForCell(JTable, int, int)
   */
  void cancelCellEditing(JTable table, int row, int column);

  /**
   * Returns the <code>{@link Component}</code> used as editor of the given cell. To manipulate the returned
   * <code>Component</code>, <code>{@link #startCellEditing(JTable, int, int)}</code> should be called first.
   * <p>
   * Example:
   *
   * <pre>
   * Component editor = writer.editorForCell(table, 6, 8);
   * // assume editor is a JTextField
   * JTextComponentFixture editorFixture = new JTextComponentFixture(robot, (JTextField) editor);
   * writer.{@link #startCellEditing(JTable, int, int) startCellEditing}(table, 6, 8);
   * editorFixture.enterText(&quot;Hello&quot;);
   * writer.{@link #stopCellEditing(JTable, int, int) stopCellEditing}(table, 6, 8);
   * </pre>
   *
   * </p>
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @return the <code>Component</code> used as editor of the given cell.
   * @throws IllegalStateException if the <code>JTable</code> cell is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws IllegalStateException if the <code>JTable</code> cell is not editable.
   */
  Component editorForCell(JTable table, int row, int column);
}
