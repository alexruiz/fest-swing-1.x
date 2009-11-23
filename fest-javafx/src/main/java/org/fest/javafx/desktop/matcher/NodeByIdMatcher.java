/*
 * Created on Jan 17, 2009
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
package org.fest.javafx.desktop.matcher;

import com.sun.scenario.scenegraph.SGNode;
import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.NodeMatcherTemplate;

import static org.fest.util.Strings.concat;

/**
 * Understands matching a JavaFX node by id.
 *
 * @author Alex Ruiz
 */
public class NodeByIdMatcher extends NodeMatcherTemplate {

  /**
   * Creates a new <code>{@link NodeByIdMatcher}</code>.
   * @param id the id to use to match nodes.
   * @return the created matcher.
   */
  public static NodeByIdMatcher nodeWithId(String id) {
    return new NodeByIdMatcher(id);
  }
  
  private NodeByIdMatcher(Object id) {
    super(id);
  }

  /**
   * Verifies that the id of the given node's leaf matches the id in this matcher.
   * @param node the node to verify. 
   * @return <code>true</code> if the given node matches the lookup criteria in this matcher, otherwise 
   * <code>false</code>. 
   */
  public boolean matches(FXNode node) {
    SGNode leaf = node.getLeaf();
    if (leaf == null) return false;
    return isIdMatching(leaf.getID());
  }

  @Override public String toString() {
    return concat(getClass().getName(), "[", "id=", quotedId(), "]");
  }
}
