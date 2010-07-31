package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that indicates whether the node (in a
 * <code>{@link JTree}</code>) identified by the given path is expanded or not.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
final class JTreeExpandedPathQuery {

  @RunsInEDT
  static boolean isExpanded(final JTree tree, final TreePath path) {
    return execute(new GuiQuery<Boolean>() {
      @Override protected Boolean executeInEDT() {
        return tree.isExpanded(path);
      }
    });
  }

  private JTreeExpandedPathQuery() {}
}