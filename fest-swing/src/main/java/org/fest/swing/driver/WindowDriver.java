/*
 * Created on Jan 27, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.WindowLikeContainerLocations.closeLocationOf;
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ActionFailedException;

/**
 * Understands functional testing of <code>{@link Window}</code>s including:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Alex Ruiz
 */
public class WindowDriver extends ContainerDriver {

  /**
   * Creates a new </code>{@link WindowDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public WindowDriver(Robot robot) {
    super(robot);
  }

  /**
   * Resizes the <code>{@link Window}</code> horizontally.
   * @param w the target <code>Window</code>.
   * @param width the width that the <code>Window</code> should have after being resized.
   * @throws ActionFailedException if the <code>Window</code> is not enabled.
   * @throws ActionFailedException if the <code>Window</code> is not resizable by the user.
   * @throws ActionFailedException if the <code>Window</code> is not showing on the screen.
   */
  @RunsInEDT
  public void resizeWidthTo(Window w, int width) {
    resizeWidth(w, width);
  }

  /**
   * Resizes the <code>{@link Window}</code> vertically.
   * @param w the target <code>Window</code>.
   * @param height the height that the <code>Window</code> should have after being resized.
   * @throws ActionFailedException if the <code>Window</code> is not enabled.
   * @throws ActionFailedException if the <code>Window</code> is not resizable by the user.
   * @throws ActionFailedException if the <code>Window</code> is not showing on the screen.
   */
  @RunsInEDT
  public void resizeHeightTo(Window w, int height) {
    resizeHeight(w, height);
  }

  /**
   * Resizes the <code>{@link Window}</code> to the given size.
   * @param w the target <code>Window</code>.
   * @param size the size to resize the <code>Window</code> to.
   * @throws ActionFailedException if the <code>Window</code> is not enabled.
   * @throws ActionFailedException if the <code>Window</code> is not resizable by the user.
   * @throws ActionFailedException if the <code>Window</code> is not showing on the screen.
   */
  @RunsInEDT
  public void resizeTo(Window w, Dimension size) {
    resize(w, size.width, size.height);
  }

  /**
   * Moves the <code>{@link Window}</code> to the given location.
   * @param w the target <code>Window</code>.
   * @param where the location to move the <code>Window</code> to.
   * @throws ActionFailedException if the <code>Window</code> is not enabled.
   * @throws ActionFailedException if the <code>Window</code> is not movable by the user.
   * @throws ActionFailedException if the <code>Window</code> is not showing on the screen.
   */
  public void moveTo(Window w, Point where) {
    move(w, where.x, where.y);
  }

  /**
   * Closing the <code>{@link Window}</code>.
   * @param w the target <code>Window</code>.
   * @throws ActionFailedException if the <code>Window</code> is not enabled.
   * @throws ActionFailedException if the <code>Window</code> is not showing on the screen.
   */
  @RunsInEDT
  public void close(Window w) {
    moveMouseIgnoringAnyError(w, closeInfo(w));
    robot.close(w);
  }

  @RunsInEDT
  private static Point closeInfo(final Window w) {
    return execute(new GuiQuery<Point>() {
      protected Point executeInEDT() {
        validateIsEnabledAndShowing(w);
        return closeLocationOf(w);
      }
    });
  }

  /**
   * Shows the <code>{@link Window}</code>.
   * @param w the target <code>Window</code>.
   */
  @RunsInEDT
  public void show(Window w) {
    robot.showWindow(w);
  }

  /**
   * Shows the <code>{@link Window}</code>, resized to the given size.
   * @param w the target <code>Window</code>.
   * @param size the size to resize the <code>Window</code> to.
   */
  @RunsInEDT
  public void show(Window w, Dimension size) {
    robot.showWindow(w, size);
  }

  /**
   * If the given <code>{@link Window}</code> is visible, brings it to the front and may make it the focused one.
   * @param w the target <code>Window</code>.
   */
  @RunsInEDT
  public void moveToFront(Window w) {
    doMoveToFront(w);
    robot.waitForIdle();
  }

  @RunsInEDT
  private static void doMoveToFront(final Window w) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        w.toFront();
      }
    });
  }

  /**
   * If the given <code>{@link Window}</code> is visible, sends it to the back and may cause it to lose focus or
   * activation if it is the focused or active.
   * @param w the target <code>Window</code>.
   */
  @RunsInEDT
  public void moveToBack(Window w) {
    doMoveToBack(w);
    robot.waitForIdle();
  }

  @RunsInEDT
  private static void doMoveToBack(final Window w) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        w.toBack();
      }
    });
  }
}