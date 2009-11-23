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
import java.awt.Container;
import java.util.Collection;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.exception.NodeLookupException;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.hierarchy.ComponentHierarchy;
import org.fest.swing.hierarchy.ExistingHierarchy;
import org.fest.swing.hierarchy.SingleComponentHierarchy;

import static org.fest.swing.hierarchy.NewHierarchy.ignoreExistingComponents;
import static org.fest.util.Strings.concat;

/**
 * Understands SOMETHING DUMMY.
 *
 * @author Alex Ruiz
 */
public class BasicNodeFinder implements NodeFinder {

  private final FinderDelegate finderDelegate = new FinderDelegate();
  private final ComponentHierarchy hierarchy;
  
  /**
   * Creates a new <code>{@link BasicNodeFinder}</code> with a new AWT hierarchy. <code>{@link Component}</code>s
   * created before the created <code>{@link BasicNodeFinder}</code> cannot be accessed by the created
   * <code>{@link BasicNodeFinder}</code>.
   * @return the created finder.
   */
  public static NodeFinder finderWithNewAwtHierarchy() {
    return new BasicNodeFinder(ignoreExistingComponents());
  }

  /**
   * Creates a new <code>{@link BasicNodeFinder}</code> that has access to all the GUI components in the AWT hierarchy.
   * @return the created finder.
   */
  public static NodeFinder finderWithCurrentAwtHierarchy() {
    return new BasicNodeFinder(new ExistingHierarchy());
  }

  /**
   * Creates a new <code>{@link BasicNodeFinder}</code>.
   * @param hierarchy the component hierarchy to use.
   */
  protected BasicNodeFinder(ComponentHierarchy hierarchy) {
    this.hierarchy = hierarchy;
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public FXNode find(NodeMatcher m) {
    return find(hierarchy, m);
  }

  /** {@inheritDoc} */
  public FXNode find(Container root, NodeMatcher m) {
    return find(hierarchy(root), m);
  }

  @RunsInEDT
  private FXNode find(ComponentHierarchy h, NodeMatcher m)  {
    Collection<FXNode> found = finderDelegate.find(h, m);
    if (found.isEmpty()) throw nodeNotFound(h, m);
    if (found.size() > 1) throw multipleNodesFound(found, m);
    return found.iterator().next();
  }

  private NodeLookupException nodeNotFound(ComponentHierarchy h, NodeMatcher m) {
    String message = concat("Unable to find component using matcher ", m, ".");
    throw new NodeLookupException(message);
  }

  private static NodeLookupException multipleNodesFound(Collection<FXNode> found, NodeMatcher m) {
    StringBuilder message = new StringBuilder();
    message.append("Found more than one component using matcher ").append(m).append(".");
    throw new NodeLookupException(message.toString(), found);
  }

  private ComponentHierarchy hierarchy(Container root) {
    if (root == null) return hierarchy;
    return new SingleComponentHierarchy(root, hierarchy);
  }
}
