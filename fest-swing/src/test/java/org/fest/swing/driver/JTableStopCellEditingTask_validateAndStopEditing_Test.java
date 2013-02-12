/*
 * Created on May 23, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import org.junit.Test;

/**
 * Tests for {@link JTableStopCellEditingTask#validateAndStopEditing(javax.swing.JTable, int, int)}.
 * 
 * @author Alex Ruiz
 */
public class JTableStopCellEditingTask_validateAndStopEditing_Test extends JTableCellEditingTask_TestCase {
  @Test
  public void should_throw_error_if_row_index_is_out_of_bounds() {
    try {
      JTableStopCellEditingTask.validateAndStopEditing(window.table, 8, 2);
      failWhenExpectingException();
    } catch (IndexOutOfBoundsException e) {
      assertThat(e.getMessage()).isEqualTo("row '8' should be between <0> and <4>");
    }
  }

  @Test
  public void should_throw_error_if_column_index_is_out_of_bounds() {
    try {
      JTableStopCellEditingTask.validateAndStopEditing(window.table, 0, 8);
      failWhenExpectingException();
    } catch (IndexOutOfBoundsException e) {
      assertThat(e.getMessage()).isEqualTo("column '8' should be between <0> and <1>");
    }
  }

  @Test
  public void should_throw_error_if_cell_is_not_editable() {
    try {
      JTableStopCellEditingTask.validateAndStopEditing(window.table, 0, 0);
      failWhenExpectingException();
    } catch (IllegalStateException e) {
      assertThat(e.getMessage()).isEqualTo("Expecting the cell [0,0] to be editable");
    }
  }

  @Test
  public void should_stop_cell_editing() {
    int row = 0;
    int col = 1;
    editTableCellAt(row, col);
    JTableStopCellEditingTask.validateAndStopEditing(window.table, row, col);
    robot.waitForIdle();
    assertCellEditingStopped();
  }

  public void shouldStopCellEditing() {
    int row = 0;
    int col = 1;
    editTableCellAt(row, col);
    JTableStopCellEditingTask.stopEditing(window.table, row, col);
    robot.waitForIdle();
    assertCellEditingStopped();
  }

  public void shouldNotThrowErrorIfCellEditorIsNull() {
    JTableStopCellEditingTask.stopEditing(null);
  }
}
