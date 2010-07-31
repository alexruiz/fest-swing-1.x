package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JTable;

import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that clears the selection in a <code>{@link JTable}</code>. This task is executed in the event
 * dispatch thread.
 *
 * @author Alex Ruiz
 */
final class JTableClearSelectionTask {

  static void clearSelectionOf(final JTable table) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        table.clearSelection();
      }
    });
  }

  private JTableClearSelectionTask() {}
}