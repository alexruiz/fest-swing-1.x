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

import static java.util.Collections.unmodifiableCollection;
import static org.fest.util.Collections.list;

import java.util.Collection;
import javafx.scene.*;

/**
 * Understands a <code>{@link NodeHierarchy}</code> for a single <code>{@link Scene}</code>.
 *
 * @author Alex Ruiz
 */
class SingleSceneNodeHierarchy extends NodeHierarchyTemplate {

  private final Scene root;

  /**
   * Creates a new </code>{@link SingleSceneNodeHierarchy}</code>.
   * @param root the root {@code Scene} for this hierarchy.
   */
  SingleSceneNodeHierarchy(Scene root) {
    this.root = root;
  }

  /** {@inheritDoc} */
  @Override public Collection<Scene> roots() {
    return unmodifiableCollection(list(root));
  }
}
