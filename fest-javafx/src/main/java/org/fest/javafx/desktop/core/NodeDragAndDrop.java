/*
 * Created on Jan 15, 2009
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

import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.ComponentDragAndDrop;
import org.fest.swing.core.Robot;
import org.fest.swing.core.Settings;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.util.TimeoutWatch;

import static org.fest.javafx.desktop.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.core.ComponentDragAndDrop.DRAG_THRESHOLD;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.swing.util.Platform.*;
import static org.fest.swing.util.TimeoutWatch.startWatchWithTimeoutOf;

/**
 * Understands drag and drop.
 *
 * @author Alex Ruiz
 */
public class NodeDragAndDrop {

  private final JavaFxRobot robot;
  private final ComponentDragAndDrop dnd;
  
  /**
   * Creates a new </code>{@link NodeDragAndDrop}</code>.
   * @param robot simulates user input.
   */
  public NodeDragAndDrop(JavaFxRobot robot) {
    this.robot = robot;
    dnd = new ComponentDragAndDrop(robot.swingRobot());
  }
  
  /**
   * Performs a drag action at the given point.
   * @param target the target node.
   * @param where the point where to start the drag action.
   */
  @RunsInEDT
  public void drag(FXNode target, Point where) {
    robot.pressMouse(target, where, LEFT_BUTTON);
    int dragDelay = settings().dragDelay();
    if (dragDelay > delayBetweenEvents()) pause(dragDelay);
    mouseMove(target, where.x, where.y);
    robot.waitForIdle();
  }

  private void mouseMove(FXNode target, int x, int y) {
    if (isWindows() || isMacintosh()) {
      mouseMoveOnWindowsAndMacintosh(target, x, y);
      return;
    }
    mouseMove(target,
        point(x + DRAG_THRESHOLD / 2, y + DRAG_THRESHOLD / 2),
        point(x + DRAG_THRESHOLD, y + DRAG_THRESHOLD),
        point(x + DRAG_THRESHOLD / 2, y + DRAG_THRESHOLD / 2),
        point(x, y)
    );
  }

  @RunsInEDT
  private void mouseMoveOnWindowsAndMacintosh(FXNode target, int x, int y) {
    Rectangle bounds = target.getBounds().getBounds();
    int dx = distance(x, bounds.width);
    int dy = distance(y, bounds.height);
    if (dx == 0 && dy == 0) dx = DRAG_THRESHOLD;
    mouseMove(target,
        point(x + dx / 4, y + dy / 4),
        point(x + dx / 2, y + dy / 2),
        point(x + dx, y + dy),
        point(x + dx + 1, y + dy)
    );
  }

  /**
   * Ends a drag operation, releasing the mouse button over the given target location.
   * <p>
   * This method is tuned for native drag/drop operations, so if you get odd behavior, you might try using a simple
   * <code>{@link JavaFxRobot#moveMouse(FXNode, int, int)}</code> and 
   * <code>{@link JavaFxRobot#releaseMouseButtons()}</code>.
   * @param target the target node.
   * @param where the point where the drag operation ends.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  
  @RunsInEDT
  public void drop(FXNode target, Point where) {
    dragOver(target, where);
    TimeoutWatch watch = startWatchWithTimeoutOf(settings().eventPostingDelay() * 4);
    while (!robot.isDragging()) {
      if (watch.isTimeOut()) throw actionFailure("There is no drag in effect");
      pause();
    }
    int dropDelay = settings().dropDelay();
    int delayBetweenEvents = delayBetweenEvents();
    if (dropDelay > delayBetweenEvents) pause(dropDelay - delayBetweenEvents);
    robot.releaseMouseButtons();
    robot.waitForIdle();
  }

  private int distance(int coordinate, int dimension) {
    return coordinate + DRAG_THRESHOLD < dimension ? DRAG_THRESHOLD : 0;
  }

  /**
   * Move the mouse appropriately to get from the source to the destination.
   * @param target the target node.
   * @param where the point to drag over.
   */
  public void dragOver(FXNode target, Point where) {
    dragOver(target, where.x, where.y);
  }

  private void dragOver(FXNode target, int x, int y) {
    robot.moveMouse(target, x - 4, y);
    robot.moveMouse(target, x, y);
  }

  private Point point(int x, int y) { return new Point(x, y); }

  private int delayBetweenEvents() {
    return settings().delayBetweenEvents();
  }

  private Settings settings() {
    return robot.settings();
  }

  @RunsInEDT
  private void mouseMove(FXNode target, Point...points) {
    for (Point p : points) robot.moveMouse(target, p.x, p.y);
  }

  /**
   * Performs a drag action at the given point.
   * @param target the target component.
   * @param where the point where to start the drag action.
   */
  public void drag(Component target, Point where) {
    dnd.drag(target, where);
  }

  /**
   * Ends a drag operation, releasing the mouse button over the given target location.
   * <p>
   * This method is tuned for native drag/drop operations, so if you get odd behavior, you might try using a simple
   * <code>{@link Robot#moveMouse(Component, int, int)}</code> and <code>{@link Robot#releaseMouseButtons()}</code>.
   * @param target the target component.
   * @param where the point where the drag operation ends.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  public void drop(Component target, Point where) {
    dnd.drop(target, where);
  }

  /**
   * Move the mouse appropriately to get from the source to the destination. Enter/exit events will be generated where
   * appropriate.
   * @param target the target component.
   * @param where the point to drag over.
   */
  public void dragOver(Component target, Point where) {
    dnd.dragOver(target, where);
  }
}
