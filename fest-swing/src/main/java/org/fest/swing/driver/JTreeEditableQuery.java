package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JTree;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that indicates whether a <code>{@link JTree}</code>
 * is editable or not.
 * @see JTree#isEditable()
 *
 * @author Alex Ruiz
 */
final class JTreeEditableQuery {

  @RunsInEDT
  static boolean isEditable(final JTree tree) {
    return execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        return tree.isEditable();
      }
    });
  }

  private JTreeEditableQuery() {}
}