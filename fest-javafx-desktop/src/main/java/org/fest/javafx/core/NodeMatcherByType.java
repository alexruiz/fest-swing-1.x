/*
 * Created on May 12, 2010
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

import static org.fest.javafx.core.Visibility.MAY_BE_VISIBLE;

import org.fest.javafx.annotations.RunsInCurrentThread;

import javafx.scene.Node;

/**
 * Understands how to match <code>{@link Node}</code>s by type.
 *
 * @author Alex Ruiz
 */
public class NodeMatcherByType extends AbstractNodeMatcher {

  private final Class<? extends Node> type;

  /**
   * Creates a new </code>{@link NodeMatcherByType}</code>. The {@code Node} to match does not have to be showing on
   * the screen.
   * @param type the type of {@code Node} to look for.
   */
  public NodeMatcherByType(Class<? extends Node> type) {
    this(type, MAY_BE_VISIBLE);
  }

  /**
   * Creates a new </code>{@link NodeMatcherByType}</code>.
   * @param type the type of {@code Node} to look for.
   * @param visibility indicates whether the node to match should be showing on the screen or not.
   */
  public NodeMatcherByType(Class<? extends Node> type, Visibility visibility) {
    super(visibility);
    this.type = type;
  }

  /** {@inheritDoc} */
  @RunsInCurrentThread
  @Override public boolean matches(Node node) {
    return false;
  }

}
