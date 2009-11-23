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

import java.awt.Point;
import java.awt.geom.Rectangle2D;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.fest.swing.core.Settings;

import static org.fest.javafx.desktop.core.MouseButton.LEFT_BUTTON;
import static org.fest.javafx.desktop.util.Nodes.centerOf;
import static org.fest.swing.exception.ActionFailedException.actionFailure;

/**
 * Understands simulation of user events on a JavaFX node.
 *
 * @author Alex Ruiz
 */
public class BasicJavaFxRobot implements JavaFxRobot {

  private final Robot robot;
  private final NodeFinder finder;
  
  /**
   * Creates a new <code>{@link JavaFxRobot}</code> with a new AWT hierarchy. The created <code>Robot</code> will not be able
   * to access any components that were created before it. 
   * @return the created <code>JavaFxRobot</code>.
   */
  public static JavaFxRobot robotWithNewAwtHierarchy() {
    return new BasicJavaFxRobot(BasicRobot.robotWithNewAwtHierarchy());
  }

  /**
   * Creates a new <code>{@link JavaFxRobot}</code> that has access to all the GUI components in the AWT hierarchy.
   * @return the created <code>JavaFxRobot</code>.
   */
  public static JavaFxRobot robotWithCurrentAwtHierarchy() {
    return new BasicJavaFxRobot(BasicRobot.robotWithCurrentAwtHierarchy());
  }

  /**
   * Creates a new </code>{@link BasicJavaFxRobot}</code>.
   * @param robot the <code>Robot</code> that understand simulation of user input.
   */
  protected BasicJavaFxRobot(Robot robot) {
    this.robot = robot;
    finder = new BasicNodeFinder(robot.hierarchy());
  }

  /** {@inheritDoc} */
  public void click(FXNode node) {
    click(node, LEFT_BUTTON, 1);
  }

  /** {@inheritDoc} */
  public void click(FXNode node, MouseButton button, int times) {
    Point center = locationOnScreen(node, centerOf(node));
    if (center == null) throw actionFailure("Unable to find visible center of node");
    robot.click(center, button.internalButton, times);
  }

  /** {@inheritDoc} */
  public void pressMouse(FXNode node, Point where, MouseButton button) {
    Point p = locationOnScreen(node, where);
    robot.pressMouse(p, button.internalButton);
  }

  /** {@inheritDoc} */
  public void releaseMouseButtons() {
    robot.releaseMouseButtons();
  }

  /** {@inheritDoc} */
  public void moveMouse(FXNode node, int x, int y) {
    Point p = locationOnScreen(node, x, y);
    moveMouse(p.x, p.y);
  }

  /** {@inheritDoc} */
  public void moveMouse(int x, int y) {
    robot.moveMouse(x, y);
  }

  public static Point locationOnScreen(FXNode node, Point p) {
    return locationOnScreen(node, p.x, p.y);
  }

  public static Point locationOnScreen(FXNode node, int x, int y) {
    Rectangle2D boundsInScene = node.getBoundsInScene();
    int originX = (int) boundsInScene.getX();
    int originY = (int) boundsInScene.getY();
    Point p = node.getPanel().getLocationOnScreen();
    p.translate(originX + x, originY + y);
    return p;
  }

  /** {@inheritDoc} */
  public boolean isDragging() { 
    return robot.isDragging();
  }
  
  /** {@inheritDoc} */
  public void waitForIdle() {
    robot.waitForIdle();
  }

  /** {@inheritDoc} */
  public void cleanUp() {
    robot.cleanUp();
  }

  /** {@inheritDoc} */
  public Settings settings() {
    return robot.settings();
  }

  /** {@inheritDoc} */
  public NodeFinder finder() {
    return finder;
  }

  /** {@inheritDoc} */
  public Robot swingRobot() { 
    return robot;
  }
}
