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

import javax.swing.JButton;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.ComponentNodeMatcher;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.desktop.driver.AbstractSwingButtonDriver;
import org.fest.javafx.desktop.exception.NodeLookupException;

import static org.fest.javafx.desktop.matcher.SwingButtonNodeMatcher.buttonWithId;
import static org.fest.javafx.desktop.util.Nodes.componentInNode;

/**
 * Understands how to simulate user input on a <code>SwingButton</code>, and verifies the state of such 
 * <code>SwingButton</code>.
 *
 * @author Alex Ruiz
 */
public class SwingButtonFixture extends NodeFixture {

  private final JButton button;
  private AbstractSwingButtonDriver<JButton> driver;
  
  /**
   * Creates a new </code>{@link SwingButtonFixture}</code>.
   * @param robot simulates user input.
   * @param id the id of the node to find.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  public SwingButtonFixture(JavaFxRobot robot, String id) {
    this(robot, buttonWithId(id));
  }
  
  /**
   * Creates a new </code>{@link SwingButtonFixture}</code>.
   * @param robot simulates user input.
   * @param matcher contains the search criteria to look up the node to be used in this fixture.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  public SwingButtonFixture(JavaFxRobot robot, ComponentNodeMatcher<? extends JButton> matcher) {
    super(robot, matcher);
    button = buttonInNode();
    createDriver();
  }
  
  /**
   * Creates a new </code>{@link SwingButtonFixture}</code>.
   * @param robot simulates user input.
   * @param node the node that contains an attached <code>JButton</code>.
   */
  public SwingButtonFixture(JavaFxRobot robot, FXNode node) {
    super(robot, node);
    button = buttonInNode();
    createDriver();
  }

  private JButton buttonInNode() {
    JButton b = componentInNode(node(), JButton.class);
    if (b == null) throw new NodeLookupException("The node in this fixture is not a SwingButton");
    return b;
  }
  
  private void createDriver() {
    updateDriver(new AbstractSwingButtonDriver<JButton>(JButton.class, robot()));
  }
  
  final void updateDriver(AbstractSwingButtonDriver<JButton> newDriver) {
    driver = newDriver;
  }

  /**
   * Simulates a user clicking this fixture's <code>SwingButton</code>.
   * @return this fixture.
   */
  public SwingButtonFixture click() {
    driver.click(node());
    return this;
  }
  
  /**
   * Asserts that this fixture's <code>SwingButton</code> has the given text.
   * @param expected the given text.
   * @return this fixture.
   * @throws AssertionError if the text of the button is not equal to the given one.
   */
  public SwingButtonFixture requireText(String expected) {
    driver.requireText(node(), expected);
    return this;
  }
  
  /**
   * Returns the <code>{@link JButton}</code> attached to this fixture's <code>SwingButton</code>.
   * @return the <code>JButton</code> attached to this fixture's <code>SwingButton</code>.
   */
  protected JButton component() {
    return button;
  }
}
