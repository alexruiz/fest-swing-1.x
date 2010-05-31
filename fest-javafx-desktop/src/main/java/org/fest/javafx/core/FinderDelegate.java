/*
 * Created on May 20, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.javafx.core;

import java.util.*;

import javafx.scene.Node;

import com.sun.javafx.runtime.sequence.Sequence;

import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.threading.GuiQuery;

import static java.util.Collections.unmodifiableCollection;

import static org.fest.javafx.threading.GuiActionRunner.execute;

/**
 * Finds all the nodes in a <code>{@link NodeHierarchy}</code> that match the search criteria specified in a
 * <code>{@link NodeMatcher}</code>.
 * 
 * @author Alex Ruiz
 */
class FinderDelegate {

  @RunsInUIThread
  Collection<Node> find(NodeHierarchy h, NodeMatcher m) {
    Set<Node> found = new LinkedHashSet<Node>();
    for (Node node : h.contents())
      find(h, m, node, found);
    return unmodifiableCollection(found);
  }

  @RunsInUIThread
  private void find(NodeHierarchy h, NodeMatcher m, Node root, Set<Node> found) {
    for (Node child : children(root, h))
      find(h, m, child, found);
    if (isMatching(root, m)) found.add(root);
  }

  @RunsInUIThread
  private static Sequence<? extends Node> children(final Node parent, final NodeHierarchy h) {
    return execute(new GuiQuery<Sequence<? extends Node>>() {
      @Override protected Sequence<? extends Node> executeInUIThread() {
        return h.childrenOf(parent);
      }
    });
  }
  
  @RunsInUIThread
  private static boolean isMatching(final Node n, final NodeMatcher m) {
    return execute(new GuiQuery<Boolean>() {
      @Override protected Boolean executeInUIThread() {
        return m.matches(n);
      }
    });
  }
}
