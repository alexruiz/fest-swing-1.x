/*
 * Created on Jan 17, 2008
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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.swing.TreeNodeFactory.node;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.util.Arrays.array;
import static org.fest.util.Collections.list;

import java.awt.*;
import java.util.Collection;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.fest.util.Collections;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link JTreeLocation#pointAt(JTree, TreePath)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunWith(Parameterized.class)
public class JTreeLocation_pointAtTreePath_Test extends RobotBasedTestCase {

  private JTree tree;
  private DefaultMutableTreeNode treeRoot;
  private JTreeLocation location;
  private List<TreePath> paths;
  
  private final int pathIndex;

  @Parameters
  public static Collection<Object[]> pathIndices() {
    return Collections.list(new Object[][] {
        { 0 }, { 1 }, { 2 }, { 3 }, { 4 }, { 5 }, { 6 }
    });
  }

  public JTreeLocation_pointAtTreePath_Test(int pathIndex) {
    this.pathIndex = pathIndex;
  }
  
  @Override protected final void onSetUp() {
    MyWindow window = MyWindow.createNew();
    tree = window.tree;
    treeRoot = window.treeRoot;
    robot.showWindow(window, new Dimension(200, 200));
    location = new JTreeLocation();
    populatePaths();
  }

  @RunsInEDT
  private void populatePaths() {
    paths = list(rootPath(), node1Path(), node1_1Path(), childOf(node1_1Path(), 0),
                 childOf(node1_1Path(), 1), childOf(node1Path(), 1), childOf(rootPath(), 1));
  }

  @Test
  public void should_find_location_of_TreePath() {
    TreePath path = paths.get(pathIndex);
    pause(160);
    Point actual = pointAt(tree, path, location);
    Rectangle pathBounds = pathBoundsOf(tree, path);
    Point expected = new Point(pathBounds.x + pathBounds.width / 2, pathBounds.y + pathBounds.height / 2);
    assertThat(actual).isEqualTo(expected);
  }

  @RunsInEDT
  private static Point pointAt(final JTree tree, final TreePath path, final JTreeLocation location) {
    return execute(new GuiQuery<Point>() {
      protected Point executeInEDT() {
        return location.pointAt(tree, path);
      }
    });
  }

  @RunsInEDT
  private static Rectangle pathBoundsOf(final JTree tree, final TreePath path) {
    return execute(new GuiQuery<Rectangle>() {
      protected Rectangle executeInEDT() {
        return tree.getPathBounds(path);
      }
    });
  }

  @RunsInEDT
  private TreePath node1_1Path() {
    return childOf(node1Path(), 0);
  }

  @RunsInEDT
  private TreePath node1Path() {
    return childOf(rootPath(), 0);
  }

  private TreePath rootPath() {
    return new TreePath(array(treeRoot));
  }

  @RunsInEDT
  private TreePath childOf(TreePath parent, int index) {
    TreeNode child = childOf(parent.getLastPathComponent(), index);
    final TreePath childPath = parent.pathByAddingChild(child);
    execute(new GuiTask() {
      protected void executeInEDT() {
        tree.expandPath(childPath);
      }
    });
    robot.waitForIdle();
    return childPath;
  }

  @RunsInEDT
  private static TreeNode childOf(final Object parent, final int index) {
    return execute(new GuiQuery<TreeNode>() {
      protected TreeNode executeInEDT() {
        return ((DefaultMutableTreeNode)parent).getChildAt(index);
      }
    }) ;
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    final JTree tree = new JTree();
    final DefaultMutableTreeNode treeRoot;

    private MyWindow() {
      super(JTreeLocation_pointAtTreePath_Test.class);
      treeRoot = root();
      tree.setModel(new DefaultTreeModel(treeRoot));
      tree.setPreferredSize(new Dimension(200, 200));
      add(tree);
    }

    private DefaultMutableTreeNode root() {
      return node("root",
          node("node1",
              node("node11",
                  node("node111"),
                  node("node112")
                  ),
              node("node12")
              ),
          node("node2")
          );
    }
  }

}
