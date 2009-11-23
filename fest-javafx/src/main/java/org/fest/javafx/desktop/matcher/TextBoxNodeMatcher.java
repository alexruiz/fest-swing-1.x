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
package org.fest.javafx.desktop.matcher;

import com.sun.scenario.scenegraph.SGText;
import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.NodeMatcherTemplate;

import static org.fest.javafx.desktop.util.Nodes.textBoxIn;
import static org.fest.util.Strings.concat;

/**
 * Understands matching JavaFX nodes that have an attached text box by:
 * <ul>
 * <li>by type</li>
 * <li>by id</li>
 * <li>all of the above</li>
 * </ul>
 * <p>
 * Matching a node by id only (the node must have a JavaFX text box attached to it though):
 * <pre>
 * NodeMatcher m = {@link #withId(String) withId}("login");
 * </pre> 
 * </p>
 * <p>
 * Matching any node that has a JavaFX text box attached to it:
 * <pre>
 * NodeMatcher m = {@link #any() any}();
 * </pre> 
 * </p>
 *
 * @author Alex Ruiz
 */
public final class TextBoxNodeMatcher extends NodeMatcherTemplate {

  /**
   * Creates a new <code>{@link TextBoxNodeMatcher}</code> that matches a node that:
   * <ol>
   * <li>has a JavaFX text box attached to it</li>
   * <li>has a matching id</li>
   * @param id the id to match.
   * @return the created matcher.
   */
  public static TextBoxNodeMatcher withId(String id) {
    return new TextBoxNodeMatcher(id);
  }
  
  /**
   * Creates a new <code>{@link TextBoxNodeMatcher}</code> that matches any node that has a JavaFX text box attached to it. 
   * @return the created matcher.
   */
  public static TextBoxNodeMatcher any() {
    return new TextBoxNodeMatcher(ANY);
  }

  private TextBoxNodeMatcher(Object id) {
    super(id);
  }
  
  /**
   * Verifies that:
   * <ol>
   * <li>the given node's leaf has a JavaFX text box attached to it</li>
   * <li>the id of the given node's leaf matches the id in this matcher (if one was specified)</li>
   * </ol>
   * @param node the node to verify. 
   * @return <code>true</code> if the given node matches the lookup criteria in this matcher, otherwise 
   * <code>false</code>. 
   */
  public boolean matches(FXNode node) {
    SGText textBox = textBoxIn(node);
    if (textBox == null) return false;
    return isIdMatching(textBox.getID());
  }

  @Override public String toString() {
    return concat(getClass().getName(), "[", "id=", quotedId(), "]");
  }
}
