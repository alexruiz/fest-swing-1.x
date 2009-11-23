/*
 * Created on Oct 1, 2008
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
package org.fest.swing.test.swing;

import static javax.swing.SwingUtilities.invokeLater;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.timing.Pause.pause;

import javax.swing.JDialog;
import javax.swing.JOptionPane;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.timing.Condition;

/**
 * Understands launching a <code>{@link JOptionPane}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class JOptionPaneLauncher {

  /**
   * Launches the given <code>{@link JOptionPane}</code>, using the text "A Message" as the title of the dialog hosting
   * the <code>JOptionPane</code>.
   * @param optionPane the <code>JOptionPane</code> to launch.
   * @return the dialog hosting the <code>JOptionPane</code>.
   */
  @RunsInEDT
  public static JDialog launch(JOptionPane optionPane) {
    return launch(optionPane, "A Message");
  }

  /**
   * Launches the given <code>{@link JOptionPane}</code>.
   * @param optionPane the <code>JOptionPane</code> to launch.
   * @param title the title of the dialog to host the <code>JOptionPane</code>.
   * @return the dialog hosting the <code>JOptionPane</code>.
   */
  @RunsInEDT
  public static JDialog launch(final JOptionPane optionPane, final String title) {
    final JDialog dialog = pack(optionPane, title);
    invokeLater(new Runnable() {
      public void run() {
        dialog.pack();
        dialog.setVisible(true);
        dialog.dispose();
      }
    });
    pause(new Condition("JOptionPane is showing") {
      public boolean test() {
        return dialog.isShowing();
      }
    });
    return dialog;
  }

  /**
   * Packs the given <code>{@link JOptionPane}</code> in a <code>{@link JDialog}</code>.
   * @param optionPane the <code>JOptionPane</code> to launch.
   * @param title the title of the dialog to host the <code>JOptionPane</code>.
   * @return the dialog hosting the <code>JOptionPane</code>.
   */
  @RunsInEDT
  public static JDialog pack(final JOptionPane optionPane, final String title) {
    final JDialog dialog = execute(new GuiQuery<JDialog>() {
      protected JDialog executeInEDT() {
        return optionPane.createDialog(title);
      }
    });
    return dialog;
  }

  private JOptionPaneLauncher() {}

}
