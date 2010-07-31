package org.fest.swing.driver;

import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.JTreeAddRootIfInvisibleTask.addRootIfInvisible;
import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.fest.swing.annotation.*;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that finds a path in a <code>{@link JTree}</code>
 * that matches a given <code>String</code>.
 *
 * @author Alex Ruiz
 *
 * @see JTreePathFinder
 */
final class JTreeMatchingPathQuery {

  @RunsInEDT
  static TreePath verifyJTreeIsReadyAndFindMatchingPath(final JTree tree, final String path,
      final JTreePathFinder pathFinder) {
    return execute(new GuiQuery<TreePath>() {
      @Override protected TreePath executeInEDT() {
        validateIsEnabledAndShowing(tree);
        return matchingPathWithRootIfInvisible(tree, path, pathFinder);
      }
    });
  }

  @RunsInEDT
  static TreePath matchingPathFor(final JTree tree, final String path, final JTreePathFinder pathFinder) {
    return execute(new GuiQuery<TreePath>() {
      @Override protected TreePath executeInEDT() {
        return matchingPathWithRootIfInvisible(tree, path, pathFinder);
      }
    });
  }

  @RunsInCurrentThread
  static TreePath matchingPathWithRootIfInvisible(JTree tree, String path, JTreePathFinder pathFinder) {
    TreePath matchingPath = pathFinder.findMatchingPath(tree, path);
    return addRootIfInvisible(tree, matchingPath);
  }

  private JTreeMatchingPathQuery() {}
}