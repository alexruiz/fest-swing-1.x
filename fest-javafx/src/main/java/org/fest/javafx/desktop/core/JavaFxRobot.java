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

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.swing.core.Robot;
import org.fest.swing.core.Settings;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.lock.ScreenLock;

/**
 * Understands simulation of user events on a JavaFX node.
 *
 * @author Alex Ruiz
 */
public interface JavaFxRobot {

  /**
   * Simulates a user clicking the given node once using the left mouse button.
   * @param node the node to click on.
   * @throws ActionFailedException if the node to click is not showing on the screen.
   */
  void click(FXNode node);

  /**
   * Simulates a user clicking the given mouse button, the given times on the given node.
   * @param node the node to click on.
   * @param button the mouse button to click.
   * @param times the number of times to click the given mouse button.
   * @throws ActionFailedException if the node to click is not showing on the screen.
   */
  void click(FXNode node, MouseButton button, int times);
  
  /**
   * Simulates a user pressing the given mouse button on the given node.
   * @param node the node to click on.
   * @param where the position where to press the given mouse button.
   * @param button the mouse button to press.
   */
  void pressMouse(FXNode node, Point where, MouseButton button);

  /**
   * Releases any mouse button(s) used by the robot.
   */
  void releaseMouseButtons();

  /**
   * Simulates a user moving the mouse pointer to the given coordinates relative to the given node.
   * @param node the given node.
   * @param x x coordinate relative to the given node.
   * @param y y coordinate relative to the given node.
   */
  void moveMouse(FXNode node, int x, int y);

  /**
   * Simulates a user moving the mouse pointer to the given coordinates.
   * @param x X coordinate.
   * @param y Y coordinate.
   */
  void moveMouse(int x, int y);

  /**
   * Wait for an idle AWT event queue. Note that this is different from the implementation of
   * <code>java.awt.Robot.waitForIdle()</code>, which may have events on the queue when it returns. Do <strong>NOT</strong>
   * use this method if there are animations or other continual refreshes happening, since in that case it may never
   * return.
   * @throws IllegalThreadStateException if this method is called from the event dispatch thread.
   */
  void waitForIdle();


  /**
   * Indicates whether the robot is currently in a dragging operation.
   * @return <code>true</code> if the robot is currently in a dragging operation, <code>false</code> otherwise.
   */
  boolean isDragging();

  /**
   * Cleans up any used resources (keyboard, mouse, open windows and <code>{@link ScreenLock}</code>) used by this
   * robot.
   */
  void cleanUp();

  /**
   * Returns the <code>{@link NodeFinder}</code> being used by this robot.
   * @return the <code>NodeFinder</code> being used by this robot.
   */
  NodeFinder finder();

  /**
   * Returns the configuration settings for this robot.
   * @return the configuration settings for this robot.
   */
  Settings settings();

  /**
   * Returns the underlying <code>{@link Robot}</code>.
   * @return the robot that knows how to interact with Swing components.
   */
  Robot swingRobot();
}
