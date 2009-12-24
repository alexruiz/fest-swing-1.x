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

import static javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.JTable;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;
import org.junit.Test;

/**
 * Tests for <code>{@link JTableDriver#selectedRow(javax.swing.JTable)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTableDriver_selectedRow_Test extends JTableDriver_TestCase {

  @Test
  public void should_return_index_of_selected_row() {
    selectRow(1);
    assertThat(driver.selectedRow(table)).isEqualTo(1);
  }

  @RunsInEDT
  private void selectRow(int row) {
    selectRows(table, row, row);
    robot.waitForIdle();
  }

  @Test
  public void should_throw_error_if_JTable_does_not_have_selection() {
    clearSelection();
    try {
      driver.selectedRow(table);
      failWhenExpectingException();
    } catch (AssertionError e) {
      assertThat(e.getMessage()).contains("property:'selectedRow'")
                                .contains("actual value:<-1> should be greater than or equal to:<0>");
    }
  }

  @RunsInEDT
  private static void selectRows(final JTable table, final int from, final int to) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        if (from != to) table.setSelectionMode(MULTIPLE_INTERVAL_SELECTION);
        table.setRowSelectionInterval(from, to);
      }
    });
  }
}
