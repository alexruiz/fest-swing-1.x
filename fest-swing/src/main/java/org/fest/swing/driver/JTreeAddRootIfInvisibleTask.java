/*
 * Created on Aug 6, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.util.Collections.list;

import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a task that adds the root node of a <code>{@link JTree}</code> to a given path if such root is invisible.
 *
 * @author Alex Ruiz
 */
final class JTreeAddRootIfInvisibleTask {

  /*
   * Adds the root node to the path, only if the JTree has an invisible root. If this is not done, a path missing the
   * root node cannot be expanded (issue 293.)
   */
  @RunsInCurrentThread
  static TreePath addRootIfInvisible(JTree tree, TreePath path) {
    if (path == null) return path;
    Object root = tree.getModel().getRoot();
    if (tree.isRootVisible() || root == null) return path;
    // root is invisible but path already contains root
    if (path.getPathCount() > 0 && root == path.getPathComponent(0)) return path;
    List<Object> newPath = list(path.getPath());
    newPath.add(0, root);
    return new TreePath(newPath.toArray());
  }

  private JTreeAddRootIfInvisibleTask() {}
}
