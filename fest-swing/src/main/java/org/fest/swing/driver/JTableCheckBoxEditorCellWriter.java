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

import static java.lang.Boolean.parseBoolean;
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Point;

import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.text.JTextComponent;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JTableCellWriter;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.util.Pair;

/**
 * Understands an implementation of <code>{@link JTableCellWriter}</code> that knows how to use
 * <code>{@link JTextComponent}</code>s as cell editors.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTableCheckBoxEditorCellWriter extends AbstractJTableCellWriter  {

  public JTableCheckBoxEditorCellWriter(Robot robot) {
    super(robot);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void enterValue(JTable table, int row, int column, String value) {
    boolean realValue = parseBoolean(value);
    Pair<Boolean, Point> editingInfo = doStartCellEditing(table, row, column, location);
    if (editingInfo.i == realValue) return; // JCheckBox already has value to set.
    robot.click(table, editingInfo.ii);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void startCellEditing(JTable table, int row, int column) {
    doStartCellEditing(table, row, column, location);
  }

  @RunsInEDT
  private static Pair<Boolean, Point> doStartCellEditing(final JTable table, final int row, final int column, 
      final JTableLocation location) {
    return execute(new GuiQuery<Pair<Boolean, Point>>() {
      protected Pair<Boolean, Point> executeInEDT() {
        JCheckBox editor = editor(table, row, column, JCheckBox.class);
        scrollToCell(table, row, column, location);
        return new Pair<Boolean, Point>(editor.isSelected(), location.pointAt(table, row, column));
      }
    });
  }
}
