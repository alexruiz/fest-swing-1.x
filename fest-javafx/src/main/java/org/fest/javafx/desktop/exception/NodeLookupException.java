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
package org.fest.javafx.desktop.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.sun.scenario.scenegraph.fx.FXNode;

/**
 * Understands an error thrown when looking up a component using a <code>{@link org.fest.javafx.desktop.core.NodeFinder}</code>.
 *
 * @author Alex Ruiz
 */
public class NodeLookupException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final Collection<FXNode> found = new ArrayList<FXNode>();
  
  /**
   * Creates a new </code>{@link NodeLookupException}</code>.
   * @param message the detail message.
   * @param found the <code>Component</code>s found by the lookup (if any.)
   */
  public NodeLookupException(String message, Collection<? extends FXNode> found) {
    this(message);
    this.found.addAll(found);
  }

  /**
   * Creates a new <code>{@link NodeLookupException}</code>.
   * @param message the detail message.
   */
  public NodeLookupException(String message) {
    super(message);
  }

  /**
   * Returns the nodes found by the lookup (if any.)
   * @return the nodes found by the lookup (if any.)
   */
  public final Collection<? extends FXNode> found() {
    return Collections.<FXNode>unmodifiableCollection(found);
  }
}
