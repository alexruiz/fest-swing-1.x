/*
 * Created on Oct 13, 2008
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
package org.fest.swing.query;

import javax.swing.JTable;
import javax.swing.table.TableColumn;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands an action that returns the index of a column in a <code>{@link JTable}</code> whose identifier matches
 * the given one.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 * @see JTable#getColumn(Object)
 * @see TableColumn#getModelIndex()
 *
 * @author Alex Ruiz
 */
public final class JTableColumnByIdentifierQuery {

  /**
   * Returns the index of a column in a <code>{@link JTable}</code> whose identifier matches the given one.
   * @param table the given <code>JTable</code>.
   * @param identifier the column identifier.
   * @return the index of a column with a matching identifier. Otherwise it returns -1.
   */
  @RunsInCurrentThread
  public static int columnIndexByIdentifier(final JTable table, final Object identifier) {
    try {
      TableColumn column = table.getColumn(identifier);
      return table.convertColumnIndexToView(column.getModelIndex());
    } catch (IllegalArgumentException e) {
      return -1;
    }
  }

  private JTableColumnByIdentifierQuery() {}
}
