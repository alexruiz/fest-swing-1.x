/*
 * Created on May 31, 2010
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

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.Scene;

/**
 * Understands the default implementation of <code>{@link NodeHierarchy}</code>.
 *
 * @author Alex Ruiz
 */
class BasicNodeHierarchy extends NodeHierarchyTemplate {

  private final CopyOnWriteArrayList<Scene> roots = new CopyOnWriteArrayList<Scene>();

  void addScene(Scene scene) {
    roots.addIfAbsent(scene);
  }

  /** {@inheritDoc} */
  @Override public Collection<Scene> roots() {
    return unmodifiableCollection(roots);
  }
}
