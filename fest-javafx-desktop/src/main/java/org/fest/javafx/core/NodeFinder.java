/*
 * Created on May 4, 2010
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

import javafx.scene.Node;
import javafx.scene.Scene;

import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.exception.NodeLookupException;

/**
 * Understands <code>{@link Node}</code> lookups.
 *
 * @author Alex Ruiz
 */
@RunsInUIThread
public interface NodeFinder {

  /**
   * Finds a {@code Node} by type in the given {@code Scene}.
   * <p>
   * Example:
   * <pre>
   * Button button = finder.findByType(scene, Button.class, {@link Visibility#REQUIRE_VISIBLE REQUIRED_VISIBLE});
   * </pre>
   * </p>
   * @param <T> the generic type of the node to find.
   * @param root the scene that may contain the to find.
   * @param type the type of the node to find.
   * @param visibility indicates whether the node should be showing on the screen or not.
   * @return the found node.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  <T extends Node> T findByType(Scene root, Class<T> type, Visibility visibility);

  /**
   * Finds a {@code Node} by id in the given {@code Scene}.
   * <p>
   * Example:
   * <pre>
   * Button button = finder.findById(scene, "myButton", {@link Visibility#REQUIRE_VISIBLE REQUIRED_VISIBLE});
   * </pre>
   * </p>
   * @param root the scene that may contain the to find.
   * @param id the id of the node to find.
   * @param visibility indicates whether the node should be showing on the screen or not.
   * @return the found node.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  Node findById(Scene root, String id, Visibility visibility);
}
