/*
 * Created on Feb 21, 2009
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

import javax.swing.JRadioButton;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.ComponentNodeMatcher;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.desktop.driver.AbstractSwingButtonDriver;
import org.fest.javafx.desktop.exception.NodeLookupException;

import static org.fest.javafx.desktop.matcher.SwingRadioButtonNodeMatcher.radioButtonWithId;
import static org.fest.javafx.desktop.util.Nodes.componentInNode;

/**
 * Understands how to simulate user input on a <code>SwingRadioButton</code>, and verifies the state of such 
 * <code>SwingRadioButton</code>.
 *
 * @author Alex Ruiz
 */
public class SwingRadioButtonFixture extends NodeFixture {

  private final JRadioButton radioButton;
  private AbstractSwingButtonDriver<JRadioButton> driver;
  
  /**
   * Creates a new </code>{@link SwingRadioButtonFixture}</code>.
   * @param robot simulates user input.
   * @param id the id of the node to find.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  public SwingRadioButtonFixture(JavaFxRobot robot, String id) {
    this(robot, radioButtonWithId(id));
  }
  
  /**
   * Creates a new </code>{@link SwingRadioButtonFixture}</code>.
   * @param robot simulates user input.
   * @param matcher contains the search criteria to look up the node to be used in this fixture.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  public SwingRadioButtonFixture(JavaFxRobot robot, ComponentNodeMatcher<? extends JRadioButton> matcher) {
    super(robot, matcher);
    radioButton = checkBoxInNode();
    createDriver();
  }
  
  /**
   * Creates a new </code>{@link SwingRadioButtonFixture}</code>.
   * @param robot simulates user input.
   * @param node the node that contains an attached <code>JRadioButton</code>.
   */
  public SwingRadioButtonFixture(JavaFxRobot robot, FXNode node) {
    super(robot, node);
    radioButton = checkBoxInNode();
    createDriver();
  }

  private JRadioButton checkBoxInNode() {
    JRadioButton b = componentInNode(node(), JRadioButton.class);
    if (b == null) throw new NodeLookupException("The node in this fixture is not a SwingRadioButton");
    return b;
  }
  
  private void createDriver() {
    updateDriver(new AbstractSwingButtonDriver<JRadioButton>(JRadioButton.class, robot()));
  }
  
  final void updateDriver(AbstractSwingButtonDriver<JRadioButton> newDriver) {
    driver = newDriver;
  }

  /**
   * Simulates a user clicking this fixture's <code>SwingRadioButton</code>.
   * @return this fixture.
   */
  public SwingRadioButtonFixture click() {
    driver.click(node());
    return this;
  }
  
  /**
   * Checks (or selects) this fixture's <code>SwingRadioButton</code> only it is not already checked.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>SwingRadioButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>SwingRadioButton</code> is not showing on the screen.
   */
  public SwingRadioButtonFixture check() {
    driver.select(node());
    return this;
  }

  /**
   * Unchecks this fixture's <code>SwingRadioButton</code> only if it is checked.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>SwingRadioButton</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>SwingRadioButton</code> is not showing on the screen.
   */
  public SwingRadioButtonFixture uncheck() {
    driver.unselect(node());
    return this;
  }

  /**
   * Asserts that this fixture's <code>SwingRadioButton</code> has the given text.
   * @param expected the given text.
   * @return this fixture.
   * @throws AssertionError if the text of the button is not equal to the given one.
   */
  public SwingRadioButtonFixture requireText(String expected) {
    driver.requireText(node(), expected);
    return this;
  }
  
  /**
   * Verifies that this fixture's <code>SwingRadioButton</code> is selected.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>SwingRadioButton</code> is not selected.
   */
  public SwingRadioButtonFixture requireSelected() {
    driver.requireSelected(node());
    return this;
  }

  /**
   * Verifies that this fixture's <code>SwingRadioButton</code> is not selected.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>SwingRadioButton</code> is selected.
   */
  public SwingRadioButtonFixture requireNotSelected() {
    driver.requireNotSelected(node());
    return this;
  }

  /**
   * Returns the <code>{@link JRadioButton}</code> attached to this fixture's <code>SwingRadioButton</code>.
   * @return the <code>JRadioButton</code> attached to this fixture's <code>SwingRadioButton</code>.
   */
  protected JRadioButton component() {
    return radioButton;
  }
}
