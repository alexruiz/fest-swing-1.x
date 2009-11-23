package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JComboBox;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that sets a <code>{@link JComboBox}</code> editable. This task is executed in the event dispatch
 * thread.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class JComboBoxSetEditableTask {

  @RunsInEDT
  static void setEditable(final JComboBox comboBox, final boolean editable) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        comboBox.setEditable(editable);
      }
    });
  }

  private JComboBoxSetEditableTask() {}
}