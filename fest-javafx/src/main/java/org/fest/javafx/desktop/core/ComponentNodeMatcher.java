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

import com.sun.scenario.scenegraph.SGComponent;
import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.util.Nodes;

import static org.fest.javafx.desktop.util.Nodes.componentInNode;

/**
 * Understands whether a JavaFX node:
 * <ol>
 * <li>satisfies certain search criteria, and</li>
 * <li>has an attached AWT <code>{@link Component}</code> that satisfies certain search criteria</li>
 * <li></li>
 * </ol>
 * @param <T> the type of AWT component this matcher supports.
 *
 * @author Alex Ruiz
 */
public abstract class ComponentNodeMatcher<T extends Component> extends NodeMatcherTemplate {

  private final Class<T> supportedType;
  
  /**
   * Creates a new </code>{@link ComponentNodeMatcher}</code>.
   * @param id the node id to match.
   * @param supportedType the type of AWT component this matcher supports.
   * @throws NullPointerException if the given type is <code>null</code>.
   */
  protected ComponentNodeMatcher(Object id, Class<T> supportedType) {
    super(id);
    this.supportedType = supportedType;
  }

  /**
   * Verifies that the given node's leaf has an attached AWT component that matches some lookup criteria.
   * @param node the node to verify. 
   * @return <code>true</code> if the given node matches the lookup criteria in this matcher, otherwise 
   * <code>false</code>. 
   */
  public final boolean matches(FXNode node) {
    SGComponent leaf = Nodes.leafWithComponent(node);
    if (leaf == null || !isIdMatching(leaf.getID()) || !isMatching(leaf)) return false;
    T c = componentInNode(leaf, supportedType);
    return isMatching(c);
  }
  
  /**
   * Indicates whether the given leaf node satisfies certain search criteria.
   * @param leaf the given node.
   * @return <code>true</code> (by default)
   */
  protected boolean isMatching(SGComponent leaf) {
    return true;
  }
  
  /**
   * Indicates whether the given AWT component matches certain search criteria.
   * @param c the given AWT component.
   * @return <code>true</code> if the component matches the search criteria, <code>false</code> otherwise. 
   */
  protected abstract boolean isMatching(T c);
  
  /**
   * Returns the type of AWT component this matcher supports.
   * @return the type of AWT component this matcher supports.
   */
  public final Class<T> supportedType() { return supportedType; }
}
