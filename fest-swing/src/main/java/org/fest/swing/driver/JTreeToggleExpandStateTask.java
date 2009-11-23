package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.reflect.core.Reflection.method;

import java.awt.Point;

import javax.swing.JTree;
import javax.swing.plaf.TreeUI;
import javax.swing.plaf.basic.BasicTreeUI;
import javax.swing.text.JTextComponent;
import javax.swing.tree.TreePath;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a task that uses reflection to toggle the "expand state" of a node in a given
 * <code>{@link JTextComponent}</code>. This task is executed in the event dispatch thread.
 *
 * @author Yvonne Wang
 */
final class JTreeToggleExpandStateTask {

  @RunsInCurrentThread
  static void toggleExpandState(final JTree tree, final Point pathLocation) {
    TreePath path = tree.getPathForLocation(pathLocation.x, pathLocation.y);
    TreeUI treeUI = tree.getUI();
    assertThat(treeUI).isInstanceOf(BasicTreeUI.class);
    method("toggleExpandState").withParameterTypes(TreePath.class).in(treeUI).invoke(path);
  }

  private JTreeToggleExpandStateTask() {}
}