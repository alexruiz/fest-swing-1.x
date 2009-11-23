/*
 * Created on Feb 20, 2009
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

import javax.swing.JCheckBox;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.ComponentNodeMatcher;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.desktop.driver.AbstractSwingButtonDriver;
import org.fest.javafx.desktop.exception.NodeLookupException;

import static org.fest.javafx.desktop.matcher.SwingCheckBoxNodeMatcher.checkBoxWithId;
import static org.fest.javafx.desktop.util.Nodes.componentInNode;

/**
 * Understands how to simulate user input on a <code>SwingCheckBox</code>, and verifies the state of such 
 * <code>SwingCheckBox</code>.
 *
 * @author Alex Ruiz
 */
public class SwingCheckBoxFixture extends NodeFixture {

  private final JCheckBox checkBox;
  private AbstractSwingButtonDriver<JCheckBox> driver;
  
  /**
   * Creates a new </code>{@link SwingCheckBoxFixture}</code>.
   * @param robot simulates user input.
   * @param id the id of the node to find.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  public SwingCheckBoxFixture(JavaFxRobot robot, String id) {
    this(robot, checkBoxWithId(id));
  }
  
  /**
   * Creates a new </code>{@link SwingCheckBoxFixture}</code>.
   * @param robot simulates user input.
   * @param matcher contains the search criteria to look up the node to be used in this fixture.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  public SwingCheckBoxFixture(JavaFxRobot robot, ComponentNodeMatcher<? extends JCheckBox> matcher) {
    super(robot, matcher);
    checkBox = checkBoxInNode();
    createDriver();
  }
  
  /**
   * Creates a new </code>{@link SwingCheckBoxFixture}</code>.
   * @param robot simulates user input.
   * @param node the node that contains an attached <code>JCheckBox</code>.
   */
  public SwingCheckBoxFixture(JavaFxRobot robot, FXNode node) {
    super(robot, node);
    checkBox = checkBoxInNode();
    createDriver();
  }

  private JCheckBox checkBoxInNode() {
    JCheckBox b = componentInNode(node(), JCheckBox.class);
    if (b == null) throw new NodeLookupException("The node in this fixture is not a SwingCheckBox");
    return b;
  }
  
  private void createDriver() {
    updateDriver(new AbstractSwingButtonDriver<JCheckBox>(JCheckBox.class, robot()));
  }
  
  final void updateDriver(AbstractSwingButtonDriver<JCheckBox> newDriver) {
    driver = newDriver;
  }

  /**
   * Simulates a user clicking this fixture's <code>SwingCheckBox</code>.
   * @return this fixture.
   */
  public SwingCheckBoxFixture click() {
    driver.click(node());
    return this;
  }
  
  /**
   * Checks (or selects) this fixture's <code>SwingCheckBox</code> only it is not already checked.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>SwingCheckBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>SwingCheckBox</code> is not showing on the screen.
   */
  public SwingCheckBoxFixture check() {
    driver.select(node());
    return this;
  }

  /**
   * Unchecks this fixture's <code>SwingCheckBox</code> only if it is checked.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>SwingCheckBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>SwingCheckBox</code> is not showing on the screen.
   */
  public SwingCheckBoxFixture uncheck() {
    driver.unselect(node());
    return this;
  }

  /**
   * Asserts that this fixture's <code>SwingCheckBox</code> has the given text.
   * @param expected the given text.
   * @return this fixture.
   * @throws AssertionError if the text of the button is not equal to the given one.
   */
  public SwingCheckBoxFixture requireText(String expected) {
    driver.requireText(node(), expected);
    return this;
  }
  
  /**
   * Verifies that this fixture's <code>SwingCheckBox</code> is selected.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>SwingCheckBox</code> is not selected.
   */
  public SwingCheckBoxFixture requireSelected() {
    driver.requireSelected(node());
    return this;
  }

  /**
   * Verifies that this fixture's <code>SwingCheckBox</code> is not selected.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>SwingCheckBox</code> is selected.
   */
  public SwingCheckBoxFixture requireNotSelected() {
    driver.requireNotSelected(node());
    return this;
  }

  /**
   * Returns the <code>{@link JCheckBox}</code> attached to this fixture's <code>SwingCheckBox</code>.
   * @return the <code>JCheckBox</code> attached to this fixture's <code>SwingCheckBox</code>.
   */
  protected JCheckBox component() {
    return checkBox;
  }
}
