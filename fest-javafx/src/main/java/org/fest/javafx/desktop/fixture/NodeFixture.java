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

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.javafx.desktop.core.NodeDragAndDrop;
import org.fest.javafx.desktop.core.NodeMatcher;
import org.fest.javafx.desktop.exception.NodeLookupException;

/**
 * Understands the base class for all the node-based fixtures.
 *
 * @author Alex Ruiz
 */
public abstract class NodeFixture {

  private final JavaFxRobot robot;
  private final FXNode node;
  private final NodeDragAndDrop dragAndDrop;
  
  /**
   * Creates a new </code>{@link NodeFixture}</code>.
   * @param robot simulates user input.
   * @param matcher contains the search criteria to look up the node to be used in this fixture.
   * @throws NodeLookupException if a matching node could not be found.
   * @throws NodeLookupException if more than one matching node is found.
   */
  protected NodeFixture(JavaFxRobot robot, NodeMatcher matcher) {
    this(robot, robot.finder().find(matcher));
  }

  /**
   * Creates a new </code>{@link NodeFixture}</code>.
   * @param robot simulates user input.
   * @param node the node to be used in this fixture.
   */
  protected NodeFixture(JavaFxRobot robot, FXNode node) {
    this.robot = robot;
    this.node = node;
    this.dragAndDrop = new NodeDragAndDrop(robot);
  }
  
  /**
   * Simulates a user clicking the node in this fixture.
   */
  protected final void clickNode() {
    robot.click(node);
  }

  /**
   * Returns the <code>{@link JavaFxRobot}</code> that simulates user input.
   * @return the robot that simulates user input.
   */
  public final JavaFxRobot robot() { return robot; }
  
  /**
   * Returns the target node of this fixture.
   * @return the target node of this fixture.
   */
  public final FXNode node() { return node; }
  
  /**
   * Returns the node-based 'drag and drop' implementation in this fixture.
   * @return the node-based 'drag and drop' implementation in this fixture.
   */
  protected final NodeDragAndDrop dragAndDrop() { return dragAndDrop; }
}
