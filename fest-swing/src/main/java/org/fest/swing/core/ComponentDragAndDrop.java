/*
 * Created on Feb 1, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.swing.util.Platform.isMacintosh;
import static org.fest.swing.util.Platform.isWindows;
import static org.fest.swing.util.TimeoutWatch.startWatchWithTimeoutOf;

import java.awt.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.util.TimeoutWatch;

/**
 * Understands <code>{@link Component}</code>-based drag and drop.
 * @since 1.1
 *
 * @author Alex Ruiz
 */
public class ComponentDragAndDrop {

  private final Robot robot;

  /**
   * Creates a new </code>{@link ComponentDragAndDrop}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public ComponentDragAndDrop(Robot robot) {
    this.robot = robot;
  }

  /** Number of pixels traversed before a drag starts. */
  public static final int DRAG_THRESHOLD = isWindows() || isMacintosh() ? 10 : 16;

  /**
   * Performs a drag action at the given point.
   * @param target the target component.
   * @param where the point where to start the drag action.
   */
  @RunsInEDT
  public void drag(Component target, Point where) {
    robot.pressMouse(target, where, LEFT_BUTTON);
    int dragDelay = settings().dragDelay();
    if (dragDelay > delayBetweenEvents()) pause(dragDelay);
    mouseMove(target, where.x, where.y);
    robot.waitForIdle();
  }

  private void mouseMove(Component target, int x, int y) {
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
  private void mouseMoveOnWindowsAndMacintosh(Component target, int x, int y) {
    Dimension size = target.getSize();
    int dx = distance(x, size.width);
    int dy = distance(y, size.height);
    if (dx == 0 && dy == 0) dx = DRAG_THRESHOLD;
    mouseMove(target,
        point(x + dx / 4, y + dy / 4),
        point(x + dx / 2, y + dy / 2),
        point(x + dx, y + dy),
        point(x + dx + 1, y + dy)
    );
  }

  private int distance(int coordinate, int dimension) {
    return coordinate + DRAG_THRESHOLD < dimension ? DRAG_THRESHOLD : 0;
  }

  private Point point(int x, int y) { return new Point(x, y); }

  /**
   * Ends a drag operation, releasing the mouse button over the given target location.
   * <p>
   * This method is tuned for native drag/drop operations, so if you get odd behavior, you might try using a simple
   * <code>{@link Robot#moveMouse(Component, int, int)}</code> and <code>{@link Robot#releaseMouseButtons()}</code>.
   * @param target the target component.
   * @param where the point where the drag operation ends.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  @RunsInEDT
  public void drop(Component target, Point where) {
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

  private int delayBetweenEvents() {
    return settings().delayBetweenEvents();
  }

  private Settings settings() {
    return robot.settings();
  }

  /**
   * Move the mouse appropriately to get from the source to the destination. Enter/exit events will be generated where
   * appropriate.
   * @param target the target component.
   * @param where the point to drag over.
   */
  public void dragOver(Component target, Point where) {
    dragOver(target, where.x, where.y);
  }

  private void dragOver(Component target, int x, int y) {
    robot.moveMouse(target, x - 4, y);
    robot.moveMouse(target, x, y);
  }

  private void mouseMove(Component target, Point...points) {
    for (Point p : points) robot.moveMouse(target, p.x, p.y);
  }
}
