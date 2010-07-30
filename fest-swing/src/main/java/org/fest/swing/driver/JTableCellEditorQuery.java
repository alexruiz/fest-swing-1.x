/*
 * Created on Aug 6, 2008
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

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands an action that returns the <code>{@link Component}</code> of a <code>{@link JTable}</code> cell editor.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 * @see JTable#getCellEditor()
 * @see TableCellEditor#getTableCellEditorComponent(JTable, Object, boolean, int, int)
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class JTableCellEditorQuery {

  @RunsInCurrentThread
  static Component cellEditorIn(final JTable table, final int row, final int column) {
    TableCellEditor cellEditor = table.getCellEditor(row, column);
    return cellEditor.getTableCellEditorComponent(table, table.getValueAt(row, column), false, row, column);
  }

  private JTableCellEditorQuery() {}
}