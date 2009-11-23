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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.data.TableCellByColumnId.row;
import static org.fest.util.Collections.list;

import java.util.Collection;

import org.fest.swing.data.TableCell;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link JTableDriver#selectCell(javax.swing.JTable, org.fest.swing.data.TableCell)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunWith(Parameterized.class)
public class JTableDriver_cellByColumnId_Test extends JTableDriver_TestCase {

  private final String columnId;

  @Parameters
  public static Collection<Object[]> ids() {
    return list(columnIds());
  }

  public JTableDriver_cellByColumnId_Test(String columnId) {
    this.columnId = columnId;
  }

  @Test
  public void should_find_cell_by_column_id() {
    TableCell cell = driver.cell(table, row(0).columnId(columnId));
    assertThat(cell.row).isEqualTo(0);
    assertThat(cell.column).isEqualTo(columnIndexFrom(columnId));
  }
}
