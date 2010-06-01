/*
 * Created on May 24, 2010
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

import java.io.PrintStream;

import org.fest.javafx.annotations.RunsInUIThread;
import javafx.scene.Node;
import javafx.scene.Scene;

/**
 * Understands printing the {@code String} representation of <code>{@link Node}</code>s to make debugging easier.
 *
 * @author Alex Ruiz
 */
public interface NodePrinter {

  /**
   * Prints all the nodes in the given <code>{@link NodeHierarchy}</code>.
   * @param out the output stream where to print the nodes to.
   * @param hierarchy the given {@code NodeHierarchy}.
   * @param root the starting point.
   * @throws NullPointerException if {@code out} is <code>null</code>.
   */
  @RunsInUIThread
  void printNodes(PrintStream out, NodeHierarchy hierarchy);

  /**
   * Prints all the nodes in the given root.
   * @param out the output stream where to print the nodes to.
   * @param root the starting point.
   * @throws NullPointerException if {@code out} is <code>null</code>.
   */
  @RunsInUIThread
  void printNodes(PrintStream out, Scene root);

  /**
   * Prints all the nodes that match the given criteria in the given <code>{@link NodeHierarchy}</code>.
   * @param out the output stream where to print the nodes to.
   * @param hierarchy the given {@code NodeHierarchy}.
   * @param matcher specifies which nodes should be printed.
   * @param root the starting point.
   * @throws NullPointerException if {@code out} is <code>null</code>.
   * @throws NullPointerException if {@code matcher} is <code>null</code>.
   */
  @RunsInUIThread
  void printNodes(PrintStream out, NodeMatcher matcher, NodeHierarchy hierarchy);

  /**
   * Prints all the nodes that match the given criteria in the given root.
   * @param out the output stream where to print the nodes to.
   * @param matcher specifies which nodes should be printed.
   * @param root the starting point.
   * @throws NullPointerException if {@code out} is <code>null</code>.
   * @throws NullPointerException if {@code matcher} is <code>null</code>.
   */
  @RunsInUIThread
  void printNodes(PrintStream out, NodeMatcher matcher, Scene root);
}
