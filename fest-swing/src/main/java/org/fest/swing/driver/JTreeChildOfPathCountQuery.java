package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns how many children a node in a
 * <code>{@link JTree}</code> has.
 *
 * @author Alex Ruiz
 */
final class JTreeChildOfPathCountQuery {

  @RunsInEDT
  static int childCount(final JTree tree, final TreePath path) {
    return execute(new GuiQuery<Integer>() {
      @Override protected Integer executeInEDT() {
        Object lastPathComponent = path.getLastPathComponent();
        return tree.getModel().getChildCount(lastPathComponent);
      }
    });
  }

  private JTreeChildOfPathCountQuery() {}
}