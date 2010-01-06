/*
 * Created on Feb 2, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static javax.swing.SwingUtilities.getWindowAncestor;
import static org.fest.reflect.core.Reflection.field;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.JToolBarIsFloatingQuery.isJToolBarFloating;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.format.Formatting.format;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.awt.*;

import javax.swing.JToolBar;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.util.GenericRange;
import org.fest.swing.util.Pair;

/**
 * Understands functional testing of <code>{@link JToolBar}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JToolBarDriver extends JComponentDriver {

  private final JToolBarLocation location = new JToolBarLocation();

  /**
   * Creates a new </code>{@link JToolBarDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JToolBarDriver(Robot robot) {
    super(robot);
  }

  /**
   * Indicates whether the given <code>{@link JToolBar}</code> is floating or not.
   * @param toolBar the target <code>JToolBar</code>.
   * @return <code>true</code> if the <code>JToolBar</code> is floating, <code>false</code> otherwise.
   */
  @RunsInEDT
  public boolean isFloating(JToolBar toolBar) {
    return floating(toolBar);
  }

  private static boolean floating(final JToolBar toolBar) {
    return execute(new GuiQuery<Boolean>() {
      protected Boolean executeInEDT() {
        return isJToolBarFloating(toolBar);
      }
    });
  }

  /**
   * Makes the given <code>{@link JToolBar}</code> float.
   * @param toolBar the target <code>JToolBar</code>.
   * @throws IllegalStateException if the <code>JToolBar</code> is disabled.
   * @throws IllegalStateException if the <code>JToolBar</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JToolBar</code> is not floatable.
   * @throws ActionFailedException if the <code>JToolBar</code> cannot be dragged.
   */
  public void makeFloat(JToolBar toolBar) {
    Pair<Point, Pair<Window, Point>> floatInfo = floatInfo(toolBar, location);
    Point p = floatInfo.ii.ii; // ancestor location
    doFloat(toolBar, p.x, p.y, floatInfo);
  }

  /**
   * Drags the <code>{@link JToolBar}</code> to the given location, causing it to float.
   * @param toolBar the target <code>JToolBar</code>.
   * @param x the horizontal coordinate of the location to drag the <code>JToolBar</code> to.
   * @param y the vertical coordinate of the location to drag the <code>JToolBar</code> to.
   * @throws IllegalStateException if the <code>JToolBar</code> is disabled.
   * @throws IllegalStateException if the <code>JToolBar</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JToolBar</code> is not floatable.
   * @throws ActionFailedException if the <code>JToolBar</code> cannot be dragged.
   */
  @RunsInEDT
  public void floatTo(JToolBar toolBar, int x, int y) {
    doFloat(toolBar, x, y, floatInfo(toolBar, location));
  }

  @RunsInEDT
  private static Pair<Point, Pair<Window, Point>> floatInfo(final JToolBar toolBar, final JToolBarLocation location) {
    return execute(new GuiQuery<Pair<Point, Pair<Window, Point>>>() {
      protected Pair<Point, Pair<Window, Point>> executeInEDT() {
        validateIsEnabledAndShowing(toolBar);
        validateIsFloatable(toolBar);
        Pair<Window, Point> windowAndLocation = ancestorAndLocation(toolBar);
        return new Pair<Point, Pair<Window,Point>>(location.pointToGrab(toolBar), windowAndLocation);
      }
    });
  }

  @RunsInCurrentThread
  private static void validateIsFloatable(JToolBar toolBar) {
    if (!toolBar.isFloatable())
      throw new IllegalStateException(concat("JToolbar <", format(toolBar), "> is not floatable"));
  }

  @RunsInCurrentThread
  private static Pair<Window, Point> ancestorAndLocation(final JToolBar toolBar) {
    Window window = getWindowAncestor(toolBar);
    return new Pair<Window, Point>(window, window.getLocation());
  }

  @RunsInEDT
  private void doFloat(JToolBar toolBar, int x, int y, Pair<Point, Pair<Window, Point>> floatInfo) {
    drag(toolBar, floatInfo.i);
    Pair<Window, Point> locationAndAncestor = floatInfo.ii;
    Point ancestorLocation = locationAndAncestor.ii;
    drop(locationAndAncestor.i, new Point(x - ancestorLocation.x, y - ancestorLocation.y));
    validateFloated(toolBar);
  }

  @RunsInEDT
  private static void validateFloated(final JToolBar toolBar) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        if (!isJToolBarFloating(toolBar))
          throw actionFailure(concat("Unable to float JToolbar <", format(toolBar), ">"));
      }
    });
  }

  /**
   * Drop the {@link JToolBar} to the requested constraint position. The constraint position must be one of the
   * constants <code>{@link BorderLayout#NORTH NORTH}</code>, <code>{@link BorderLayout#EAST EAST}</code>,
   * <code>{@link BorderLayout#SOUTH SOUTH}</code>, or <code>{@link BorderLayout#WEST WEST}</code>.
   * @param toolBar the target <code>JToolBar</code>.
   * @param constraint the constraint position.
   * @throws IllegalStateException if the <code>JToolBar</code> is disabled.
   * @throws IllegalStateException if the <code>JToolBar</code> is not showing on the screen.
   * @throws IllegalArgumentException if the constraint has an invalid value.
   * @throws ActionFailedException if the dock container cannot be found.
   */
  @RunsInEDT
  public void unfloat(JToolBar toolBar, String constraint) {
    Pair<GenericRange<Point>, Container> unfloatInfo = unfloatInfo(toolBar, constraint, location);
    GenericRange<Point> fromAndTo = unfloatInfo.i;
    drag(toolBar, fromAndTo.from);
    drop(unfloatInfo.ii, fromAndTo.to);
    validateIsNotFloating(toolBar, constraint);
  }

  @RunsInEDT
  private static Pair<GenericRange<Point>, Container> unfloatInfo(final JToolBar toolBar, final String constraint,
      final JToolBarLocation location) {
    return execute(new GuiQuery<Pair<GenericRange<Point>, Container>>() {
      protected Pair<GenericRange<Point>, Container> executeInEDT() {
        validateIsEnabledAndShowing(toolBar);
        Container dock = dockFor(toolBar);
        Point from = location.pointToGrab(toolBar);
        Point to = location.dockLocation(toolBar, dock, constraint);
        return new Pair<GenericRange<Point>, Container>(new GenericRange<Point>(from, to), dock);
      }
    });
  }

  @RunsInEDT
  private static void validateIsNotFloating(final JToolBar toolBar, final String constraint) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        if (isJToolBarFloating(toolBar))
          throw actionFailure(concat("Failed to dock <", format(toolBar), "> using constraint ", quote(constraint)));
      }
    });
  }

  @RunsInCurrentThread
  private static Container dockFor(final JToolBar toolBar) {
    try {
      return field("dockingSource").ofType(Container.class).in(toolBar.getUI()).get();
    } catch (RuntimeException e) {
      throw actionFailure("Unabled to determine dock for JToolBar");
    }
  }

  /**
   * Closes a floating <code>{@link JToolBar}</code>, making it go back to its original container in its last known
   * location.
   * @param toolBar the target <code>JToolBar</code>.
   * @throws IllegalStateException if the <code>JToolBar</code> is disabled.
   * @throws IllegalStateException if the <code>JToolBar</code> is not showing on the screen.
   */
  @RunsInEDT
  public void unfloat(JToolBar toolBar) {
    Window w = windowAncestorOf(toolBar);
    robot.close(w);
  }

  @RunsInEDT
  private static Window windowAncestorOf(final JToolBar toolBar) {
    return execute(new GuiQuery<Window>() {
      protected Window executeInEDT() {
        validateIsEnabledAndShowing(toolBar);
        return getWindowAncestor(toolBar);
      }
    });
  }
}
