package org.fest.swing.hierarchy;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.timing.Pause.pause;

import java.beans.PropertyVetoException;

import javax.swing.JInternalFrame;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.timing.Condition;

/**
 * Understands a task that iconifies a given <code>{@link JInternalFrame}</code>. This task is executed in the event
 * dispatch thread.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunsInEDT
final class JInternalFrameIconifyTask {

  static void iconify(final JInternalFrame internalFrame) {
    execute(new GuiTask() {
      protected void executeInEDT() throws PropertyVetoException {
        internalFrame.setIcon(true);
        pause(new Condition("JInternalFrame is iconified") {
          public boolean test() {
            return internalFrame.isIcon();
          }
        });
      }
    });
  }

  private JInternalFrameIconifyTask() {}
}