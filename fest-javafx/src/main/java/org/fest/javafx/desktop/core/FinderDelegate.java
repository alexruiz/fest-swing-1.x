/*
 * Created on Jan 11, 2009
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; either version 2 of 
 * the License. You may obtain a copy of the License at
 * 
 * http://www.gnu.org/licenses/gpl-2.0.txt
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See 
 * the GNU General Public License for more details.
 * 
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.core;

import java.awt.Component;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;

import com.sun.scenario.scenegraph.JSGPanel;
import com.sun.scenario.scenegraph.SGNode;
import com.sun.scenario.scenegraph.SGParent;
import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.hierarchy.ComponentHierarchy;

import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Finds all the JavaFX nodes in a node hierarchy that match the search criteria specified in a 
 * <code>{@link NodeMatcher}</code>.
 *
 * @author Alex Ruiz
 */
class FinderDelegate {

  @RunsInEDT
  Collection<FXNode> find(ComponentHierarchy h, NodeMatcher m) {
    Set<FXNode> found = new LinkedHashSet<FXNode>();
    for (Object o : rootsOf(h)) 
      find(h, m, (Component)o, found);
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
  private void find(ComponentHierarchy h, NodeMatcher m, Component root, Set<FXNode> found) {
    for (Component c : childrenOfComponent(root, h)) 
      find(h, m, c, found);
    if (root instanceof JSGPanel) find((JSGPanel)root, m, found);
  }

  @RunsInEDT
  private void find(JSGPanel root, NodeMatcher m, Set<FXNode> found) {
    SGNode s = root.getScene();
    if (s == null) return;
    SGParent p = s.getParent();
    if (p == null) return;
    find(p, m, found);
  }
  
  @RunsInEDT
  private void find(SGParent parent, NodeMatcher m, Set<FXNode> found) {
    for (SGNode child : parent.getChildren()) {
      if (addIfMatching(child, m, found)) continue;
      if (child instanceof SGParent) find((SGParent)child, m, found);
    }
  }

  @RunsInEDT
  private static boolean addIfMatching(SGNode node, NodeMatcher m, Set<FXNode> found) {
    if (!isMatching(node, m)) return false;
    found.add((FXNode)node);
    return true;
  }
  
  @RunsInEDT
  private static boolean isMatching(final SGNode node, final NodeMatcher m) {
    if (!(node instanceof FXNode)) return false;
    return execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        return m.matches((FXNode)node);
      }
    });
  }
  
  @RunsInEDT
  private static Collection<Component> childrenOfComponent(final Component c, final ComponentHierarchy h) {
    return execute(new GuiQuery<Collection<Component>>() {
      protected Collection<Component> executeInEDT() {
        return h.childrenOf(c);
      }
    });
  }
}
