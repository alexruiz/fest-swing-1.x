/*
 * Created on Feb 25, 2008
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

import static org.easymock.EasyMock.expectLastCall;
import static org.fest.swing.data.TableCell.row;

import javax.swing.JTable;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.data.TableCell;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableDriver#cancelCellEditing(JTable, TableCell)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTableDriver_cancelCellEditing_Test extends JTableDriver_withMockCellWriter_TestCase {

  @Test
  public void should_cancel_cell_editing() {
    new EasyMockTemplate(cellWriter) {
      protected void expectations() {
        cellWriter.cancelCellEditing(table, 0, 0);
        expectLastCall().once();
      }

      protected void codeToTest() {
        driver.cancelCellEditing(table, row(0).column(0));
      }
    }.run();
  }
}
