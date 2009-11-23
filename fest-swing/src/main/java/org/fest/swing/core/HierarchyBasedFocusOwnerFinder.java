/*
 * Created on May 31, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.core;

import java.awt.Component;
import java.awt.Container;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands how to find the focus owner using the roots of a component hierarchy.
 *
 * @author Alex Ruiz
 */
class HierarchyBasedFocusOwnerFinder implements FocusOwnerFinderStrategy {

  private final ContainerFocusOwnerFinder delegate;
  private final HierarchyRootsSource rootsSource;

  HierarchyBasedFocusOwnerFinder() {
    this(new ContainerFocusOwnerFinder(), new HierarchyRootsSource());
  }

  HierarchyBasedFocusOwnerFinder(ContainerFocusOwnerFinder newDelegate, HierarchyRootsSource newRootsSource) {
    delegate = newDelegate;
    rootsSource = newRootsSource;
  }

  @RunsInCurrentThread
  public Component focusOwner() {
    for (Container c : rootsSource.existingHierarchyRoots()) {
      Component focus = delegate.focusOwnerOf(c);
      if (focus != null) return focus;
    }
    return null;
  }
}
