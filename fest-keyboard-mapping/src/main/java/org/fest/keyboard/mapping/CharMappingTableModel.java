/*
 * Created on Apr 9, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import javax.swing.table.*;

import static org.fest.util.Arrays.array;

/**
 * Understands a <code>{@link TableModel}</code> for the table that displays <code>{@link CharMapping}</code>s.
 * 
 * @author Alex Ruiz
 */
class CharMappingTableModel extends DefaultTableModel {

  private static final long serialVersionUID = 1L;
  
  private static final int CHARACTER_COLUMN_INDEX = 0;
  
  CharMappingTableModel() {
    super(emptyTable(), array("Character", "Key", "Modifiers"));
  }

  private static Object[][] emptyTable() {
    return new Object[][] {};
  }
  
  void addOrReplace(CharMapping mapping) {
    remove(mapping);
    addRow(new Object[] { mapping.character, mapping.keyCode, mapping.modifier });
  }
  
  private void remove(CharMapping mapping) {
    int rowCount = getRowCount();
    for (int row = 0; row < rowCount; row++) {
      Object val = getValueAt(row, CHARACTER_COLUMN_INDEX);
      if (mapping.character.equals(val)) {
        removeRow(row);
        break;
      }
    }
  }
  
  int lastRowIndex() {
    return getRowCount() - 1;
  }
}
