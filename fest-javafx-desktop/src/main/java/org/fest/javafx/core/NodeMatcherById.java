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
 * Understands how to match <code>{@link Node}</code>s by id.
 *
 * @author Alex Ruiz
 */
public class NodeMatcherById extends AbstractNodeMatcher {

  private final String id;

  /**
   * Creates a new </code>{@link NodeMatcherById}</code>. The {@code Node} to match does not have to be showing on the
   * screen.
   * @param id the id of the {@code Node} to look for.
   * @throws NullPointerException if {@code id} is <code>null</code>.
   */
  public NodeMatcherById(String id) {
    this(id, MAY_BE_VISIBLE);
  }

  /**
   * Creates a new </code>{@link NodeMatcherById}</code>.
   * @param id the id of the {@code Node} to look for.
   * @param visibility indicates whether the node to match should be showing on the screen or not.
   * @throws NullPointerException if {@code id} is <code>null</code>.
   * @throws NullPointerException if {@code visibility} is <code>null</code>.
   */
  public NodeMatcherById(String id, Visibility visibility) {
    super(visibility);
    if (id == null) throw new NullPointerException("The id to match should not be null");
    this.id = id;
  }

  /**
   * Indicates whether the id and visibility of the given <code>{@link Node}</code> match the values in this matcher. 
   * This method returns <code>false</code> if the given {@code Node} is <code>null</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> executed in the UI thread. Clients are responsible for invoking this method 
   * in the UI thread.
   * </p>
   * @param node the {@code Node} to verify.
   * @return <code>true</code> if the id and visibility of the given {@code Node} match the values in this matcher; 
   * <code>false</code> otherwise.
   */
  @RunsInCurrentThread
  @Override
  public boolean matches(Node node) {
    if (node == null) return false;
    return id.equals(node.get$id()) && visibilityMatches(node);
  }

}
