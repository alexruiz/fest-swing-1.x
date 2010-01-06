/*
 * Created on Oct 24, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.hierarchy;

import static java.util.Collections.emptyList;
import static org.fest.swing.hierarchy.ContainerComponentsQuery.componentsOf;

import java.awt.Component;
import java.awt.Container;
import java.util.*;

import org.fest.swing.annotation.RunsInCurrentThread;


/**
 * Understands how to find children components in a <code>{@link Container}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
class ChildrenFinder {

  private static List<ChildrenFinderStrategy> strategies = new ArrayList<ChildrenFinderStrategy>();

  static {
    strategies.add(new JDesktopPaneChildrenFinder());
    strategies.add(new JMenuChildrenFinder());
    strategies.add(new WindowChildrenFinder());
  }

  @RunsInCurrentThread
  Collection<Component> childrenOf(Component c) {
    if (!(c instanceof Container)) return emptyList();
    Container container = (Container)c;
    Collection<Component> children = new ArrayList<Component>();
    children.addAll(componentsOf(container));
    children.addAll(nonExplicitChildrenOf(container));
    return children;
  }

  private Collection<Component> nonExplicitChildrenOf(Container c) {
    Collection<Component> children = new ArrayList<Component>();
    for (ChildrenFinderStrategy s : strategies)
      children.addAll(s.nonExplicitChildrenOf(c));
    return children;
  }

  // For testing only
  static List<ChildrenFinderStrategy> strategies() { return new ArrayList<ChildrenFinderStrategy>(strategies); }

  static void replaceStrategiesWith(List<ChildrenFinderStrategy> newStrategies) {
    strategies = new ArrayList<ChildrenFinderStrategy>(newStrategies);
  }
}
