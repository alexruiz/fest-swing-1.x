/*
 * Created on Apr 18, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import static java.awt.event.KeyEvent.VK_TAB;
import static javax.swing.KeyStroke.getKeyStroke;
import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;

import javax.swing.JTable;
import javax.swing.event.ListSelectionListener;

/**
 * Understands a <code>{@link JTable}</code> that displays <code>{@link CharMapping}</code>s.
 *
 * @author Alex Ruiz
 */
class CharMappingTable extends JTable {

  private static final long serialVersionUID = 1L;
  private static final String TAB_OUT_ACTION_KEY = "tabOut";

  private final BasicCharMappingTableModel model = new BasicCharMappingTableModel();

  /**
   * Creates a new </code>{@link CharMappingTable}</code>.
   */
  CharMappingTable() {
    setModel(model);
    setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
    getInputMap(WHEN_FOCUSED).put(getKeyStroke(VK_TAB, 0), TAB_OUT_ACTION_KEY);
    getActionMap().put(TAB_OUT_ACTION_KEY, new TabOutAction());
  }

  @Override public BasicCharMappingTableModel getModel() {
    return model;
  }

  void deleteSelection() {
    if (getSelectedRowCount() == 0) return;
    int selectedRow = -1;
    while ((selectedRow = getSelectedRow()) != -1)
      model.removeRow(selectedRow);
    scrollAndSelectLastRow();
  }

  void scrollAndSelectLastRow() {
    int lastRowIndex = model.lastRowIndex();
    if (lastRowIndex < 0) return;
    scrollRectToVisible(getCellRect(lastRowIndex, 0, true));
    setRowSelectionInterval(lastRowIndex, lastRowIndex);
  }

  void addSelectionListener(ListSelectionListener l) {
    getSelectionModel().addListSelectionListener(l);
  }
}


