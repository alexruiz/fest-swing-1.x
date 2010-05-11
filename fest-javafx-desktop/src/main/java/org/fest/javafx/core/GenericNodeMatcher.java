/*
 * Created on May 11, 2010
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
 * Understands some search criteria to be used to look up specific types of <code>{@link Node}</code>s.
 * @param <T> the type of {@code Node} supported by this matcher.
 *
 * @author Alex Ruiz
 */
public abstract class GenericNodeMatcher<T extends Node> extends AbstractNodeMatcher {

  private final Class<T> supportedType;

  /**
   * Creates a new </code>{@link GenericNodeMatcher}</code>. The node to match does not have to be showing on the
   * screen.
   * @param supportedType the type of node supported by this matcher.
   */
  public GenericNodeMatcher(Class<T> supportedType) {
    this(supportedType, MAY_BE_VISIBLE);
  }

  /**
   * Creates a new </code>{@link GenericNodeMatcher}</code>.
   * @param supportedType the type of node supported by this matcher.
   * @param visibility indicates whether the node to match should be showing on the screen or not.
   */
  public GenericNodeMatcher(Class<T> supportedType, Visibility visibility) {
    super(visibility);
    this.supportedType = supportedType;
  }

  /**
   * Verifies that the given <code>{@link Node}</code> matches the search criteria specified in this matcher.
   * <p>
   * <b>Note:</b> Implementations of this method <b>may not</b> be guaranteed to be executed in the UI thread. Clients
   * are responsible for invoking this method in the UI thread.
   * </p>
   * @param node the {@code Node} to verify.
   * @return <code>true</code> if the {@code Node} matches the search criteria specified in this matcher;
   * <code>false</code> otherwise.
   */
  @Override public final boolean matches(Node node) {
    if (node == null) return false;
    if (!supportedType.isInstance(node)) return false;
    try {
      return (visibilityMatches(node) && isMatching(supportedType.cast(node)));
    } catch (ClassCastException ignored) {
      return false;
    }
  }

  /**
   * Verifies that the given <code>{@link Node}</code> satisfies the search criteria specified in this matcher.
   * <p>
   * <b>Note:</b> Implementations of this method <b>may not</b> be guaranteed to be executed in the UI thread. Clients
   * are responsible for invoking this method in the UI thread.
   * </p>
   * @param node the {@code Node} to verify.
   * @return <code>true</code> if the given {@code Node} satisfies the search criteria specified in this matcher;
   * <code>false</code> otherwise.
   */
  @RunsInCurrentThread
  protected abstract boolean isMatching(T node);
}
