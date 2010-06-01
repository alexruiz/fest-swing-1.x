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

import static org.fest.javafx.core.SingleSceneNodeHierarchy.hierarchyFor;
import static org.fest.javafx.format.Formatting.format;
import static org.fest.javafx.threading.GuiActionRunner.execute;
import static org.fest.util.Strings.concat;
import static org.fest.util.Systems.LINE_SEPARATOR;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.Scene;

import org.fest.javafx.annotations.RunsInCurrentThread;
import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.exception.NodeLookupException;
import org.fest.javafx.threading.GuiTask;

/**
 * Understands <code>{@link Node}</code> lookups.
 *
 * @author Alex Ruiz
 */
public class BasicNodeFinder implements NodeFinder {

  private final NodeHierarchy nodeHierarchy;
  private final NodePrinter printer;
  private final FinderDelegate finderDelegate;

  private boolean includeHierarchyInNodeLookupException;

  /**
   * Creates a new </code>{@link BasicNodeFinder}</code> with an empty <code>{@link NodeHierarchy}</code>.
   */
  public BasicNodeFinder() {
    this(new EmptyNodeHierarchy());
  }

  /**
   * Creates a new </code>{@link BasicNodeFinder}</code>.
   * @param nodeHierarchy the {@code NodeHierarchy} for this finder.
   */
  public BasicNodeFinder(NodeHierarchy nodeHierarchy) {
    this.nodeHierarchy = nodeHierarchy;
    printer = new BasicNodePrinter();
    finderDelegate = new FinderDelegate();
  }

  /** {@inheritDoc} */
  @Override
  public Node findById(String id, Visibility visibility) {
    return findById(nodeHierarchy, id, visibility);
  }

  /** {@inheritDoc} */
  @Override public Node findById(Scene root, String id, Visibility visibility) {
    return findById(hierarchyFor(root), id, visibility);
  }

  @RunsInUIThread
  private Node findById(NodeHierarchy h, String id, Visibility v) {
    return find(h, new NodeMatcherById(id, v));
  }

  /** {@inheritDoc} */
  @Override public <T extends Node> T findByType(Class<T> type, Visibility visibility) {
    return findByType(nodeHierarchy, type, visibility);
  }

  /** {@inheritDoc} */
  @Override public <T extends Node> T findByType(Scene root, Class<T> type, Visibility visibility) {
    return findByType(hierarchyFor(root), type, visibility);
  }

  @RunsInUIThread
  private <T extends Node> T findByType(NodeHierarchy h, Class<T> c, Visibility v) {
    Node found = find(h, new NodeMatcherByType(c, v));
    return c.cast(found);
  }

  /** {@inheritDoc} */
  @Override public <T extends Node> T findById(String id, Class<T> type, Visibility visibility) {
    return findById(nodeHierarchy, id, type, visibility);
  }

  /** {@inheritDoc} */
  @Override public <T extends Node> T findById(Scene root, String id, Class<T> type, Visibility visibility) {
    return findById(hierarchyFor(root), id, type, visibility);
  }

  @RunsInUIThread
  private <T extends Node> T findById(NodeHierarchy h, String id, Class<T> c, Visibility v) {
    Node found = find(h, new NodeMatcherByIdAndType(id, c, v));
    return c.cast(found);
  }

  @RunsInCurrentThread
  private Node find(NodeHierarchy h, NodeMatcher m) {
    Collection<Node> found = finderDelegate.find(h, m);
    if (found.isEmpty()) throw nodeNotFound(h, m);
    if (found.size() > 1) throw multipleNodesFound(found, m);
    return found.iterator().next();
  }

  private NodeLookupException nodeNotFound(NodeHierarchy h, NodeMatcher m) {
    String message = concat("Unable to find node using matcher ", m, ".");
    if (includeHierarchyIfNodeNotFound())
      message = concat(message,
          LINE_SEPARATOR, LINE_SEPARATOR, "Component hierarchy:", LINE_SEPARATOR, formattedHierarchy(h));
    throw new NodeLookupException(message);
  }

  @RunsInUIThread
  private String formattedHierarchy(NodeHierarchy hierarchy) {
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out, true);
    printer.printNodes(printStream, hierarchy);
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
