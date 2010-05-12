/*
 * Created on May 10, 2010
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
 * Understands a base class for implementations of <code>{@link NodeMatcher}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class AbstractNodeMatcher implements NodeMatcher {

  private final Visibility visibility;

  /**
   * Creates a new </code>{@link AbstractNodeMatcher}</code>. The {@code Node} to match does not have to be showing on
   * the screen.
   */
  public AbstractNodeMatcher() {
    this(MAY_BE_VISIBLE);
  }

  /**
   * Creates a new </code>{@link AbstractNodeMatcher}</code>.
   * @param visibility indicates whether the node to match should be showing on the screen or not.
   */
  public AbstractNodeMatcher(Visibility visibility) {
    this.visibility = visibility;
  }

  /**
   * Indicates if the given {@link Node}'s visibility matches the value specified in this matcher.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the UI thread. Clients are responsible for
   * calling this method from the UI thread.
   * </p>
   * @param node the node to verify.
   * @return <code>true</code> if the {@code Node}s visibility matches the value specified in this matcher;
   * <code>false</code> otherwise.
   * @see AbstractNodeMatcher#AbstractNodeMatcher()
   * @see AbstractNodeMatcher#AbstractNodeMatcher(Visibility)
   */
  @RunsInCurrentThread
  protected final boolean visibilityMatches(Node node) {
    if (MAY_BE_VISIBLE.equals(visibility)) return true;
    return node.get$visible();
  }

  /** {@inheritDoc} */
  @Override public void reset(boolean matchFound) {}
}
