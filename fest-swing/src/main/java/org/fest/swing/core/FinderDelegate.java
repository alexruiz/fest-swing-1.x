/*
 * Created on Jun 5, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Component;
import java.util.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.hierarchy.ComponentHierarchy;

/**
 * Finds all the components in a <code>{@link ComponentHierarchy}</code> that match the search criteria specified in a
 * <code>{@link ComponentMatcher}</code>.
 *
 * @author Alex Ruiz
 */
final class FinderDelegate {

  @RunsInEDT
  Collection<Component> find(ComponentHierarchy h, ComponentMatcher m)  {
    Set<Component> found = new LinkedHashSet<Component>();
    for (Object o : rootsOf(h)) find(h, m, (Component)o, found);
    return found;
  }

  @RunsInEDT
  private void find(ComponentHierarchy h, ComponentMatcher m, Component root, Set<Component> found) {
    for (Component c : childrenOfComponent(root, h))
      find(h, m, c, found);
    if (isMatching(root, m)) found.add(root);
  }

  @RunsInEDT
  private static Collection<Component> childrenOfComponent(final Component c, final ComponentHierarchy h) {
    return execute(new GuiQuery<Collection<Component>>() {
      protected Collection<Component> executeInEDT() {
        return h.childrenOf(c);
      }
    });
  }

  @RunsInEDT
  private static boolean isMatching(final Component c, final ComponentMatcher m) {
    return execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        return m.matches(c);
      }
    });
  }

  @RunsInEDT
  <T extends Component> Collection<T> find(ComponentHierarchy h, GenericTypeMatcher<T> m) {
    Set<T> found = new LinkedHashSet<T>();
    for (Object o : rootsOf(h)) find(h, m, (Component)o, found);
    return found;
  }

  @RunsInEDT
  private static Collection<? extends Component> rootsOf(final ComponentHierarchy h ) {
    return execute(new GuiQuery<Collection<? extends Component>>() {
      protected Collection<? extends Component> executeInEDT() {
        return h.roots();
      }
    });
  }

  @RunsInEDT
  private <T extends Component> void find(ComponentHierarchy h, GenericTypeMatcher<T> m, Component root, Set<T> found) {
    for (Component c : childrenOfComponent(root, h))
      find(h, m, c, found);
    if (isMatching(root, m)) found.add(m.supportedType().cast(root));
  }

  @RunsInEDT
  private static <T extends Component> boolean isMatching(final Component c, final GenericTypeMatcher<T> m) {
    return execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        return m.matches(c);
      }
    });
  }
}
