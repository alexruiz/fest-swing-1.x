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

import org.fest.javafx.exception.NodeLookupException;

/**
 * Understands node lookups.
 *
 * @author Alex Ruiz
 */
public interface NodeFinder {

  /**
   * Fins a <code>{@link Node}</code> by type.
   * <p>
   * Example:
   * <pre>
   * Button button = finder.findByType(Button.class);
   * </pre>
   * </p>
   * @param <T> the generic type of the node to find.
   * @param type the type of the node to find.
   * @return the found node.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  <T extends Node> T findByType(Class<T> type);
}
