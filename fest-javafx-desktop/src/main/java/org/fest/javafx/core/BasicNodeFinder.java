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

import static org.fest.javafx.format.Formatting.format;
import static org.fest.javafx.threading.GuiActionRunner.execute;
import static org.fest.util.Strings.concat;
import static org.fest.util.Systems.LINE_SEPARATOR;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.Scene;

import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.exception.NodeLookupException;
import org.fest.javafx.threading.GuiTask;

/**
 * Understands <code>{@link Node}</code> lookups.
 *
 * @author Alex Ruiz
 */
public class BasicNodeFinder implements NodeFinder {

  private final NodePrinter printer = new BasicNodePrinter();

  private final FinderDelegate finderDelegate = new FinderDelegate();

  private boolean includeHierarchyInNodeLookupException;

  /** {@inheritDoc} */
  @Override public Node findById(Scene root, String id, Visibility visibility) {
    return find(root, new NodeMatcherById(id, visibility));
  }

  /** {@inheritDoc} */
  @Override public <T extends Node> T findByType(Scene root, Class<T> type, Visibility visibility) {
    Node found = find(root, new NodeMatcherByType(type, visibility));
    return type.cast(found);
  }

  /** {@inheritDoc} */
  @Override public <T extends Node> T findById(Scene root, String id, Class<T> type, Visibility visibility) {
    Node found = find(root, new NodeMatcherByIdAndType(id, type, visibility));
    return type.cast(found);
  }

  @RunsInUIThread
  private Node find(Scene root, NodeMatcher matcher) {
    Collection<Node> found = finderDelegate.find(new SingleSceneNodeHierarchy(root), matcher);
    if (found.isEmpty()) throw nodeNotFound(root, matcher);
    if (found.size() > 1) throw multipleNodesFound(found, matcher);
    return found.iterator().next();
  }

  private NodeLookupException nodeNotFound(Scene root, NodeMatcher matcher) {
    String message = concat("Unable to find node using matcher ", matcher, ".");
    if (includeHierarchyIfNodeNotFound())
      message = concat(message,
          LINE_SEPARATOR, LINE_SEPARATOR, "Component hierarchy:", LINE_SEPARATOR, formattedHierarchy(root));
    throw new NodeLookupException(message);
  }

  @RunsInUIThread
  private String formattedHierarchy(Scene root) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out, true);
    printer.printNodes(printStream, root);
    printStream.flush();
    return new String(out.toByteArray());
  }

  private NodeLookupException multipleNodesFound(Collection<Node> found, NodeMatcher matcher) {
    StringBuilder message = new StringBuilder();
    message.append("Found more than one node using matcher ").append(matcher).append(".").append(LINE_SEPARATOR)
           .append(LINE_SEPARATOR)
           .append("Found:");
    appendNodes(message, found);
    if (!found.isEmpty()) message.append(LINE_SEPARATOR);
    throw new NodeLookupException(message.toString(), found);
  }

  @RunsInUIThread
  private static void appendNodes(final StringBuilder message, final Collection<Node> found) {
    execute(new GuiTask() {
      @Override protected void executeInUIThread() {
        for (Node n : found) message.append(LINE_SEPARATOR).append(format(n));
      }
    });
  }

  /** {@inheritDoc} */
  @Override public boolean includeHierarchyIfNodeNotFound() {
    return includeHierarchyInNodeLookupException;
  }

  /** {@inheritDoc} */
  @Override public void includeHierarchyIfNodeNotFound(boolean newValue) {
    includeHierarchyInNodeLookupException = newValue;
  }
}
