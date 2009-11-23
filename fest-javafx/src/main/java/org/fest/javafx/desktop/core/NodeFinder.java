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

import java.awt.Container;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.exception.NodeLookupException;
import org.fest.swing.annotation.RunsInEDT;

/**
 * Understands JavaFX node lookup.
 *
 * @author Alex Ruiz
 */
@RunsInEDT
public interface NodeFinder {

  /**
   * Finds a node using the given <code>{@link NodeMatcher}</code>. The given matcher
   * will be evaluated in the event dispatch thread. Implementations of <code>ComponentMatcher</code> do not need to
   * be concerned about the event dispatch thread.
   * @param m the matcher to use to find the node of interest.
   * @return the found node.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  FXNode find(NodeMatcher m);

  /**
   * Finds a node using the given <code>{@link NodeMatcher}</code>, in the hierarchy under the given root. The given
   * matcher will be evaluated in the event dispatch thread. Implementations of <code>ComponentMatcher</code> do not
   * need to be concerned about the event dispatch thread.
   * @param root the root used as the starting point of the search.
   * @param m the matcher to use to find the node of interest.
   * @return the found node.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  FXNode find(Container root, NodeMatcher m);
}
