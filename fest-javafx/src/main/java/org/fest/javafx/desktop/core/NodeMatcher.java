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

import com.sun.scenario.scenegraph.fx.FXNode;

/**
 * Understands whether a JavaFX node matches some desired criteria.
 *
 * @author Alex Ruiz
 */
public interface NodeMatcher {

  /**
   * Verifies that the given node matches some lookup criteria.
   * @param node the node to verify. 
   * @return <code>true</code> if the given node matches some lookup criteria, otherwise <code>false</code>. 
   */
  boolean matches(FXNode node);
}
