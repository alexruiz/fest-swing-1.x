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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import java.awt.Component;

import javax.swing.*;
import javax.swing.text.JTextComponent;

import org.fest.swing.cell.JTableCellWriter;
import org.fest.swing.core.Robot;
import org.fest.swing.exception.ActionFailedException;

/**
 * Understands the default implementation of <code>{@link JTableCellWriter}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class BasicJTableCellWriter extends AbstractJTableCellWriter {

  private final JTableCheckBoxEditorCellWriter checkBoxWriter;
  private final JTableComboBoxEditorCellWriter comboBoxWriter;
  private final JTableTextComponentEditorCellWriter textComponentWriter;

  public BasicJTableCellWriter(Robot robot) {
    super(robot);
    checkBoxWriter = new JTableCheckBoxEditorCellWriter(robot);
    comboBoxWriter = new JTableComboBoxEditorCellWriter(robot);
    textComponentWriter = new JTableTextComponentEditorCellWriter(robot);
  }

  /**
   * Enters the given value at the given cell of the <code>JTable</code>. This method only supports the following GUI
   * components as cell editors:
   * <ul>
   * <li><code>{@link JCheckBox}</code>: valid values for the property "selected" (a boolean) are "true" and "yes",
   * other values are considered {@code false}.</li>
   * <li><code>{@link JComboBox}</code>: this writer will select the element which <code>String</code> representation
   * matches the given value.</li>
   * <li><code>{@link JTextComponent}</code>: any value will be entered in the cell.</li>
   * </ul>
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @param value the value to enter.
   * @throws ActionFailedException if this writer is unable to handle the underlying cell editor.
   */
  public void enterValue(JTable table, int row, int column, String value) {
    cellWriterFor(table, row, column).enterValue(table, row, column, value);
  }

  /**
   * Starts editing the given cell of the <code>{@link JTable}</code>. This method only supports the following GUI
   * components as cell editors:
   * <ul>
   * <li><code>{@link JCheckBox}</code></li>
   * <li><code>{@link JComboBox}</code></li>
   * <li><code>{@link JTextComponent}</code></li>
   * </ul>
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @throws ActionFailedException if this writer is unable to handle the underlying cell editor.
   * @see JTableCellWriter#startCellEditing(JTable, int, int)
   */
  public void startCellEditing(JTable table, int row, int column) {
    cellWriterFor(table, row, column).startCellEditing(table, row, column);
  }
  
  /**
   * Stops editing the given cell of the <code>{@link JTable}</code>. This method only supports the following GUI
   * components as cell editors:
   * <ul>
   * <li><code>{@link JCheckBox}</code></li>
   * <li><code>{@link JComboBox}</code></li>
   * <li><code>{@link JTextComponent}</code></li>
   * </ul>
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @throws ActionFailedException if this writer is unable to handle the underlying cell editor.
   * @see JTableCellWriter#stopCellEditing(JTable, int, int)
   */
  public void stopCellEditing(JTable table, int row, int column) {
    cellWriterFor(table, row, column).stopCellEditing(table, row, column);
  }
  
  /**
   * Cancels editing the given cell of the <code>{@link JTable}</code>. This method only supports the following GUI
   * components as cell editors:
   * <ul>
   * <li><code>{@link JCheckBox}</code></li>
   * <li><code>{@link JComboBox}</code></li>
   * <li><code>{@link JTextComponent}</code></li>
   * </ul>
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @throws ActionFailedException if this writer is unable to handle the underlying cell editor.
   * @see JTableCellWriter#cancelCellEditing(JTable, int, int)
   */
  public void cancelCellEditing(JTable table, int row, int column) {
    cellWriterFor(table, row, column).cancelCellEditing(table, row, column);
  }

  private JTableCellWriter cellWriterFor(JTable table, int row, int column) {
    Component editor = editorForCell(table, row, column);
    if (editor instanceof JCheckBox) return checkBoxWriter;
    if (editor instanceof JComboBox) return comboBoxWriter;
    if (editor instanceof JTextComponent) return textComponentWriter;
    throw cannotFindOrActivateEditor(row, column);
  }
}
