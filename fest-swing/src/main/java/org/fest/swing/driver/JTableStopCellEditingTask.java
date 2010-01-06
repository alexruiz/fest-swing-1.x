/*
 * Created on Nov 22, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.driver.JTableCellValidator.validateCellIsEditable;
import static org.fest.swing.driver.JTableCellValidator.validateIndices;
import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that stops editing of a cell in a <code>{@link JTable}</code>. This task is executed in the
 * event dispatch thread.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class JTableStopCellEditingTask {

  @RunsInEDT
  static void stopEditing(final TableCellEditor cellEditor) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        doStopCellEditing(cellEditor);
      }
    });
  }

  @RunsInEDT
  static void stopEditing(final JTable table, final int row, final int column) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        doStopCellEditing(table, row, column);
      }
    });
  }

  @RunsInEDT
  static void validateAndStopEditing(final JTable table, final int row, final int column) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        validateIndices(table, row, column);
        validateCellIsEditable(table, row, column);
        doStopCellEditing(table, row, column);
      }
    });
  }

  @RunsInCurrentThread
  private static void doStopCellEditing(JTable table, int row, int column) {
    doStopCellEditing(table.getCellEditor(row, column));
  }

  @RunsInCurrentThread
  private static void doStopCellEditing(TableCellEditor cellEditor) {
    if (cellEditor == null) return;
    cellEditor.stopCellEditing();
  }

  private JTableStopCellEditingTask() {}
}