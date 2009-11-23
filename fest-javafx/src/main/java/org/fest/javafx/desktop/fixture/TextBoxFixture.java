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
package org.fest.javafx.desktop.fixture;

import com.sun.scenario.scenegraph.SGText;
import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.desktop.core.NodeMatcher;
import org.fest.javafx.desktop.exception.NodeLookupException;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.desktop.matcher.TextBoxNodeMatcher.withId;
import static org.fest.javafx.desktop.util.Nodes.textBoxIn;

/**
 * Understands how to simulate user input on a JavaFX text box and verifies the state of such text box.
 *
 * @author Alex Ruiz
 */
public class TextBoxFixture extends NodeFixture {

  private final SGText textBox;

  /**
   * Creates a new </code>{@link TextBoxFixture}</code>.
   * @param robot simulates user input.
   * @param id the id of the node to find.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  public TextBoxFixture(JavaFxRobot robot, String id) {
    this(robot, withId(id));
  }

  /**
   * Creates a new </code>{@link TextBoxFixture}</code>.
   * @param robot simulates user input.
   * @param matcher contains the search criteria to look up the node to be used in this fixture.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  public TextBoxFixture(JavaFxRobot robot, NodeMatcher matcher) {
    super(robot, matcher);
    textBox = textBoxInNode();
  }

  /**
   * Creates a new </code>{@link TextBoxFixture}</code>.
   * @param robot simulates user input.
   * @param node the node that contains an attached JavaFX text box.
   */
  public TextBoxFixture(JavaFxRobot robot, FXNode node) {
    super(robot, node);
    textBox = textBoxInNode();
  }

  private SGText textBoxInNode() {
    SGText t = textBoxIn(node());
    if (t == null) throw new NodeLookupException("The node in this fixture does not have an attached text box");
    return t;
  }

  /**
   * Asserts that the text of this fixture's text box is equal to the specified <code>String</code>.
   * @param expected the text to match.
   * @return this fixture.
   * @throws AssertionError if the text of this fixture's text component is not equal to the given one.
   */
  public TextBoxFixture requireText(String expected) {
    assertThat(textBox.getText()).as("text").isEqualTo(expected);
    return this;
  }
}
