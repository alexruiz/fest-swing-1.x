/*
 * Created on Jan 31, 2008
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

import static javax.swing.JSplitPane.VERTICAL_SPLIT;
import static org.fest.swing.core.MouseButton.LEFT_BUTTON;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.JSplitPaneLocationCalculator.locationToMoveDividerTo;
import static org.fest.swing.driver.JSplitPaneSetDividerLocationTask.setDividerLocation;
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.*;

import javax.swing.JSplitPane;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.util.GenericRange;

/**
 * Understands:
 * <ul>
 * <li>simulation of user input on a <code>{@link JSplitPane}</code> (if applicable)</li>
 * <li>state verification of a <code>{@link JSplitPane}</code></li>
 * </ul>
 * This class is intended for internal use only.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JSplitPaneDriver extends JComponentDriver {

  /**
   * Creates a new </code>{@link JSplitPaneDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JSplitPaneDriver(Robot robot) {
    super(robot);
  }

  /**
   * Sets the divider position to an absolute position.
   * <p>
   * Since 1.2, this method respects the minimum and maximum values of the left and right components inside the given
   * <code>JSplitPane</code>.
   * </p>
   * @param splitPane the target <code>JSplitPane</code>.
   * @param location the location to move the divider to.
   * @throws IllegalStateException if the <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if the <code>JSplitPane</code> is not showing on the screen.
   */
  @RunsInEDT
  public void moveDividerTo(JSplitPane splitPane, int location) {
    int newLocation = locationToMoveDividerTo(splitPane, location);
    simulateMovingDivider(splitPane, newLocation);
    setDividerLocation(splitPane, newLocation);
    robot.waitForIdle();
  }

  @RunsInEDT
  private void simulateMovingDivider(JSplitPane split, int location) {
    if (split.getOrientation() == VERTICAL_SPLIT) {
      simulateMovingDividerVertically(split, location);
      return;
    }
    simulateMovingDividerHorizontally(split, location);
  }

  @RunsInEDT
  private void simulateMovingDividerVertically(JSplitPane splitPane, int location) {
    GenericRange<Point> whereToMove = validateAndFindWhereToMoveDividerVertically(splitPane, location);
    simulateMovingDivider(splitPane, whereToMove);
  }

  @RunsInEDT
  private static GenericRange<Point> validateAndFindWhereToMoveDividerVertically(final JSplitPane splitPane,
      final int location) {
    return execute(new GuiQuery<GenericRange<Point>>() {
      protected GenericRange<Point> executeInEDT() {
        validateIsEnabledAndShowing(splitPane);
        return whereToMoveDividerVertically(splitPane, location);
      }
    });
  }

  @RunsInCurrentThread
  private static GenericRange<Point> whereToMoveDividerVertically(JSplitPane splitPane, int location) {
      int x = splitPane.getWidth() / 2;
      int dividerLocation = splitPane.getDividerLocation();
      return new GenericRange<Point>(new Point(x, dividerLocation), new Point(x, location));
  }

  private void simulateMovingDividerHorizontally(JSplitPane splitPane, int location) {
    GenericRange<Point> whereToMove = validateAndFindWhereToMoveDividerHorizontally(splitPane, location);
    simulateMovingDivider(splitPane, whereToMove);
  }

  @RunsInEDT
  private static GenericRange<Point> validateAndFindWhereToMoveDividerHorizontally(final JSplitPane splitPane,
      final int location) {
    return execute(new GuiQuery<GenericRange<Point>>() {
      protected GenericRange<Point> executeInEDT() {
        validateIsEnabledAndShowing(splitPane);
        return whereToMoveDividerHorizontally(splitPane, location);
      }
    });
  }

  @RunsInCurrentThread
  private static GenericRange<Point> whereToMoveDividerHorizontally(JSplitPane splitPane, int location) {
      int y = splitPane.getHeight() / 2;
      int dividerLocation = splitPane.getDividerLocation();
      return new GenericRange<Point>(new Point(dividerLocation, y), new Point(location, y));
  }

  @RunsInEDT
  private void simulateMovingDivider(JSplitPane splitPane, GenericRange<Point> range) {
    try {
      robot.moveMouse(splitPane, range.from);
      robot.pressMouse(LEFT_BUTTON);
      robot.moveMouse(splitPane, range.to);
      robot.releaseMouse(LEFT_BUTTON);
    } catch (RuntimeException ignored) {}
  }
}
