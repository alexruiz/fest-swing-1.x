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

import java.awt.Container;

import javax.swing.JButton;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.ComponentNodeMatcher;
import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.desktop.exception.NodeLookupException;
import org.fest.javafx.desktop.matcher.ImageNodeMatcher;
import org.fest.javafx.desktop.matcher.SwingButtonNodeMatcher;
import org.fest.javafx.desktop.matcher.TextBoxNodeMatcher;

/**
 * Understands lookup of nodes contained in a <code>{@link Container}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class ContainerFixture {

  private final JavaFxRobot robot;
  private final Container container;

  /**
   * Creates a new </code>{@link ContainerFixture}</code>.
   * @param robot simulates user input.
   * @param container the container to handle in this fixture.
   */
  public ContainerFixture(JavaFxRobot robot, Container container) {
    this.robot = robot;
    this.container = container;
  }

  /**
   * Finds a node, in this fixture's <code>{@link Container}</code>, whose id matches the given one and has an attached 
   * image node.
   * @param id the id to match.
   * @return a fixture that manages the node found.
   * @throws NodeLookupException if a node with the given id and an attached image node could not be found.
   * @throws NodeLookupException if more than one node with the given id and an attached image node is found.
   */
  public ImageFixture image(String id) {
    FXNode node = robot.finder().find(container, ImageNodeMatcher.withId(id));
    return new ImageFixture(robot, node);
  }
  
  /**
   * Returns a node, in this fixture's <code>{@link Container}</code>, that has an attached JavaFX text box.
   * @return a fixture that manages the node found.
   * @throws NodeLookupException if a node with an attached <code>JButton</code> could not be found.
   * @throws NodeLookupException if more than one node with an attached <code>JButton</code> is found.
   */
  public TextBoxFixture textBox() {
    FXNode node = robot.finder().find(container, TextBoxNodeMatcher.any());
    return new TextBoxFixture(robot, node);
  }

  /**
   * Finds a node, in this fixture's <code>{@link Container}</code>, whose id matches the given one and has an attached 
   * JavaFX text box.
   * @param id the id to match.
   * @return a fixture that manages the node found.
   * @throws NodeLookupException if a node with the given id and an attached JavaFX text box could not be found.
   * @throws NodeLookupException if more than one node with the given id and an attached JavaFX text box is found.
   */
  public TextBoxFixture textBox(String id) {
    FXNode node = robot.finder().find(container, TextBoxNodeMatcher.withId(id));
    return new TextBoxFixture(robot, node);
  }
  
  /**
   * Returns a node, in this fixture's <code>{@link Container}</code>, that has an attached 
   * <code>{@link JButton}</code>.
   * @return a fixture that manages the node found.
   * @throws NodeLookupException if a node with an attached <code>JButton</code> could not be found.
   * @throws NodeLookupException if more than one node with an attached <code>JButton</code> is found.
   */
  public SwingButtonFixture swingButton() {
    FXNode node = robot.finder().find(container, SwingButtonNodeMatcher.any());
    return new SwingButtonFixture(robot, node);
  }

  /**
   * Finds a node, in this fixture's <code>{@link Container}</code>, whose id matches the given one and has an attached 
   * <code>{@link JButton}</code>.
   * @param id the id to match.
   * @return a fixture that manages the node found.
   * @throws NodeLookupException if a node with the given id and an attached <code>JButton</code> could not be found.
   * @throws NodeLookupException if more than one node with the given id and an attached <code>JButton</code> is found.
   */
  public SwingButtonFixture swingButton(String id) {
    FXNode node = robot.finder().find(container, SwingButtonNodeMatcher.buttonWithId(id));
    return new SwingButtonFixture(robot, node);
  }
  
  /**
   * Finds a node, in this fixture's <code>{@link Container}</code>, that satisfies the provided search criteria and has 
   * an attached <code>{@link JButton}</code>.
   * @param matcher contains the search criteria for finding a node with an attached <code>JButton</code>.
   * @return a fixture that manages the node found.
   * @throws NodeLookupException if a node that matches the given search criteria could not be found.
   * @throws NodeLookupException if more than one node that match the given search criteria is found.
   */
  public SwingButtonFixture swingButton(ComponentNodeMatcher<? extends JButton> matcher) {
    FXNode node = robot.finder().find(container, matcher);
    return new SwingButtonFixture(robot, node);
  }

  /**
   * Returns the <code>{@link JavaFxRobot}</code> that simulates user input.
   * @return the robot that simulates user input.
   */
  public final JavaFxRobot robot() { return robot; }
}
