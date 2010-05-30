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
   * @param <T> the generic type of the {@code Node} to find.
   * @param root the {@code Scene} that may contain the to find.
   * @param type the type of the {@code Node} to find.
   * @param visibility indicates whether the {@code Node} should be showing on the screen or not.
   * @return the found {@code Node}.
   * @throws NodeLookupException if a matching {@code Node} could not be found.
   * @throws NodeLookupException if more than one matching {@code Node} is found.
   */
  <T extends Node> T findByType(Scene root, Class<T> type, Visibility visibility);

  /**
   * Finds a {@code Node} by id in the given {@code Scene}.
   * <p>
   * Example:
   * <pre>
   * Node node = finder.findById(scene, "myNode", {@link Visibility#REQUIRE_VISIBLE REQUIRED_VISIBLE});
   * </pre>
   * </p>
   * @param root the {@code Scene} that may contain the to find.
   * @param id the id of the node to find.
   * @param visibility indicates whether the {@code Node} should be showing on the screen or not.
   * @return the found {@code Node}.
   * @throws NodeLookupException if a matching {@code Node} could not be found.
   * @throws NodeLookupException if more than one matching {@code Node} is found.
   */
  Node findById(Scene root, String id, Visibility visibility);

  /**
   * @param <T> the generic type of the node to find.
   * @param root the {@code Scene} that may contain the to find.
   * @param id the id of the node to find.
   * @param type the type of the {@code Node} to find.
   * @param visibility indicates whether the {@code Node} should be showing on the screen or not.
   * @return the found {@code Node}.
   */
  <T extends Node> T findById(Scene root, String id, Class<T> type, Visibility visibility);

  /**
   * Returns whether the message in a <code>{@link NodeLookupException}</code> should include the current node
   * hierarchy. The default value is <code>true</code>.
   * @return <code>true</code> if the node hierarchy is included as part of the {@code NodeLookupException} message,
   * <code>false</code> otherwise.
   */
  boolean includeHierarchyIfNodeNotFound();

  /**
   * Updates whether the message in a <code>{@link NodeLookupException}</code> should include the current node
   * hierarchy. The default value is <code>true</code>.
   * @param newValue the new value to set.
   */
  void includeHierarchyIfNodeNotFound(boolean newValue);
}
