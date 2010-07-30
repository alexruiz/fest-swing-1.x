/*
 * Created on Jun 10, 2008
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

import static java.lang.String.valueOf;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.JTableCancelCellEditingTask.cancelEditing;
import static org.fest.swing.driver.JTableCellEditorQuery.cellEditorIn;
import static org.fest.swing.driver.JTableCellValidator.*;
import static org.fest.swing.driver.JTableStopCellEditingTask.*;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.util.Strings.concat;

import java.awt.*;

import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import org.fest.swing.annotation.*;
import org.fest.swing.cell.JTableCellWriter;
import org.fest.swing.core.*;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.*;

/**
 * Understands the base class for implementations of <code>{@link JTableCellWriter}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class AbstractJTableCellWriter implements JTableCellWriter {

  protected final Robot robot;
  protected final JTableLocation location = new JTableLocation();
  private TableCellEditor cellEditor;

  private static final long EDITOR_LOOKUP_TIMEOUT = 5000;

  public AbstractJTableCellWriter(Robot robot) {
    this.robot = robot;
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void cancelCellEditing(JTable table, int row, int column) {
    if (cellEditor == null) {
      doCancelCellEditing(table, row, column);
      return;
    }
    doCancelCellEditing();
  }

  @RunsInEDT
  private void doCancelCellEditing(JTable table, int row, int column) {
    cancelEditing(table, row, column);
    robot.waitForIdle();
  }

  @RunsInEDT
  private void doCancelCellEditing() {
    cancelEditing(cellEditor);
    robot.waitForIdle();
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void stopCellEditing(JTable table, int row, int column) {
    if (cellEditor == null) {
      doStopCellEditing(table, row, column);
      return;
    }
    doStopCellEditing();
  }

  @RunsInEDT
  private void doStopCellEditing(JTable table, int row, int column) {
    validateAndStopEditing(table, row, column);
    robot.waitForIdle();
  }

  @RunsInEDT
  private void doStopCellEditing() {
    stopEditing(cellEditor);
    robot.waitForIdle();
  }

  /**
   * Returns the editor for the given table cell. This method is executed in the EDT.
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @return the editor for the given table cell.
   */
  @RunsInEDT
  protected static TableCellEditor cellEditor(final JTable table, final int row, final int column) {
    return execute(new GuiQuery<TableCellEditor>() {
      @Override
      protected TableCellEditor executeInEDT() throws Throwable {
        return table.getCellEditor(row, column);
      }
    });
  }

  /**
   * Scrolls the given <code>{@link JTable}</code> to the given cell.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @param location understands how to get the bounds of the given cell.
   */
  @RunsInCurrentThread
  protected static void scrollToCell(JTable table, int row, int column, JTableLocation location) {
    table.scrollRectToVisible(location.cellBounds(table, row, column));
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public Component editorForCell(JTable table, int row, int column) {
    return cellEditorComponent(table, row, column);
  }

  @RunsInEDT
  private static Component cellEditorComponent(final JTable table, final int row, final int column) {
    return execute(new GuiQuery<Component>() {
      @Override
      protected Component executeInEDT() {
        validateIndices(table, row, column);
        return cellEditorIn(table, row, column);
      }
    });
  }

  /**
   * Finds the component used as editor for the given <code>{@link JTable}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param <T> the generic type of the supported editor type.
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @param supportedType the type of component we expect as editor.
   * @return the component used as editor for the given table cell.
   * @throws IndexOutOfBoundsException if any of the indices is out of bounds or if the <code>JTable</code> does not
   * have any rows.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if the table cell in the given coordinates is not editable.
   * @throws IndexOutOfBoundsException if any of the indices is out of bounds or if the <code>JTable</code> does not
   * have any rows.
   * @throws ActionFailedException if an editor for the given cell cannot be found or cannot be activated.
   */
  @RunsInCurrentThread
  protected static <T extends Component> T editor(JTable table, int row, int column, Class<T> supportedType) {
    validate(table, row, column);
    Component editor = cellEditorIn(table, row, column);
    if (supportedType.isInstance(editor)) return supportedType.cast(editor);
    throw cannotFindOrActivateEditor(row, column);
  }

  /**
   * Returns the location of the given table cell.
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @param location knows how to get the location of a table cell.
   * @return the location of the given table cell.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices is out of bounds or if the <code>JTable</code> does not
   * have any rows.
   * @throws IllegalStateException if the table cell in the given coordinates is not editable.
   */
  @RunsInEDT
  protected static Point cellLocation(final JTable table, final int row, final int column, final JTableLocation location) {
    return execute(new GuiQuery<Point>() {
      @Override
      protected Point executeInEDT() {
        validate(table, row, column);
        scrollToCell(table, row, column, location);
        return location.pointAt(table, row, column);
      }
    });
  }

  /**
   * Validates that:
   * <ol>
   * <li>the given <code>JTable</code> is enabled and showing on the screen</li>
   * <li>the row and column indices are correct (not out of bounds)</li>
   * <li>the table cell at the given indices is editable</li>
   * </ol>
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @throws IllegalStateException if the <code>JTable</code> is disabled.
   * @throws IllegalStateException if the <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices is out of bounds or if the <code>JTable</code> does not
   * have any rows.
   * @throws IllegalStateException if the table cell in the given coordinates is not editable.
   */
  @RunsInCurrentThread
  protected static void validate(final JTable table, final int row, final int column) {
    validateIndices(table, row, column);
    validateIsEnabledAndShowing(table);
    validateCellIsEditable(table, row, column);
  }

  /**
   * Waits until the editor of the given table cell is showing on the screen. Component lookup is performed by type.
   * @param <T> the generic type of the cell editor.
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @param supportedType the type of component we expect as editor.
   * @return the editor of the given table cell once it is showing on the screen.
   * @throws ActionFailedException if an editor for the given cell cannot be found or cannot be activated.
   */
  @RunsInEDT
  protected final <T extends Component> T waitForEditorActivation(JTable table, int row,
      int column, Class<T> supportedType) {
    return waitForEditorActivation(new TypeMatcher(supportedType, true), table, row, column, supportedType);
  }

  /**
   * Waits until the editor of the given table cell is showing on the screen.
   * @param <T> the generic type of the cell editor.
   * @param matcher the condition that the cell editor to look for needs to satisfy.
   * @param table the target <code>JTable</code>.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @param supportedType the type of component we expect as editor.
   * @return the editor of the given table cell once it is showing on the screen.
   * @throws ActionFailedException if an editor for the given cell cannot be found or cannot be activated.
   */
  @RunsInEDT
  protected final <T extends Component> T waitForEditorActivation(ComponentMatcher matcher, JTable table, int row,
      int column, Class<T> supportedType) {
    ComponentFoundCondition condition = new ComponentFoundCondition("", robot.finder(), matcher, table);
    try {
      pause(condition, EDITOR_LOOKUP_TIMEOUT);
    } catch (WaitTimedOutError e) {
      throw cannotFindOrActivateEditor(row, column);
    }
    return supportedType.cast(condition.found());
  }

  /**
   * Throws a <code>{@link ActionFailedException}</code> if this <code>{@link JTableCellWriter}</code> could not find or
   * activate the cell editor of the supported type.
   * @param row the row index of the cell.
   * @param column the column index of the cell.
   * @return the thrown exception.
   */
  protected static ActionFailedException cannotFindOrActivateEditor(int row, int column) {
    String msg = concat("Unable to find or activate editor for cell [", valueOf(row), ",", valueOf(column), "]");
    throw actionFailure(msg);
  }


  /**
   * Returns the cell editor being currently used. This method will return {@code null} if no table cell is being
   * currently edited.
   * @return the cell editor being currently used, or {@code null} if no table cell is being currently edited.
   */
  protected final TableCellEditor cellEditor() { return cellEditor; }

  /**
   * Sets the cell editor being currently used.
   * @param newCellEditor the cell editor being currently used.
   */
  protected final void cellEditor(TableCellEditor newCellEditor) {
    cellEditor = newCellEditor;
  }
}
