/*
 * Created on Aug 23, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.gestures;

import static org.fest.swing.driver.ComponentStateValidator.*;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.gestures.ComponentMovableQuery.isUserMovable;
import static org.fest.swing.gestures.ComponentMoveTask.moveComponent;
import static org.fest.swing.gestures.ComponentSetSizeTask.setComponentSize;
import static org.fest.swing.gestures.ContainerStateValidator.validateCanResize;
import static org.fest.util.Strings.concat;

import java.awt.*;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.util.Pair;
import org.fest.swing.util.Triple;

/**
 * Simulates user input on a <code>{@link Container}</code>.
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 * </p>
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
public class ContainerGestures extends ComponentGestures {

  /**
   * Creates a new </code>{@link ContainerGestures}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public ContainerGestures(Robot robot) {
    super(robot);
  }

  /**
   * Resizes the <code>{@link Container}</code> horizontally.
   * @param c the target {@code Container}.
   * @param width the width that the {@code Container} should have after being resized.
   * @throws IllegalStateException if the {@code Container} is not enabled.
   * @throws IllegalStateException if the {@code Container} is not resizable by the user.
   * @throws IllegalStateException if the {@code Container} is not showing on the screen.
   */
  @RunsInEDT
  protected final void resizeWidth(Container c, int width) {
    Pair<Dimension, Insets> resizeInfo = resizeInfo(c);
    Dimension size = resizeInfo.i;
    resizeBy(c, resizeInfo, width - size.width, 0);
  }

  /**
   * Resizes the <code>{@link Container}</code> vertically.
   * @param c the target {@code Container}.
   * @param height the height that the {@code Container} should have after being resized.
   * @throws IllegalStateException if the {@code Container} is not enabled.
   * @throws IllegalStateException if the {@code Container} is not resizable by the user.
   * @throws IllegalStateException if the {@code Container} is not showing on the screen.
   */
  @RunsInEDT
  protected final void resizeHeight(Container c, int height) {
    Pair<Dimension, Insets> resizeInfo = resizeInfo(c);
    Dimension size = resizeInfo.i;
    resizeBy(c, resizeInfo, 0, height - size.height);
  }

  /**
   * Resizes the <code>{@link Container}</code> to the given size.
   * @param c the target {@code Container}.
   * @param width the width to resize the {@code Container} to.
   * @param height the height to resize the {@code Container} to.
   * @throws IllegalStateException if the {@code Container} is not enabled.
   * @throws IllegalStateException if the {@code Container} is not resizable by the user.
   * @throws IllegalStateException if the {@code Container} is not showing on the screen.
   */
  @RunsInEDT
  protected final void resize(Container c, int width, int height) {
    Pair<Dimension, Insets> resizeInfo = resizeInfo(c);
    Dimension size = resizeInfo.i;
    resizeBy(c, resizeInfo, width - size.width, height - size.height);
  }

  @RunsInEDT
  private static Pair<Dimension, Insets> resizeInfo(final Container c) {
    return execute(new GuiQuery<Pair<Dimension, Insets>>() {
      @Override protected Pair<Dimension, Insets> executeInEDT() {
        validateCanResize(c);
        return new Pair<Dimension, Insets>(c.getSize(), c.getInsets());
      }
    });
  }

  @RunsInEDT
  private void resizeBy(Container c, Pair<Dimension, Insets> resizeInfo, int x, int y) {
    simulateResizeStarted(c, resizeInfo, x, y);
    Dimension size = resizeInfo.i;
    setComponentSize(c, size.width + x, size.height + y);
    robot.waitForIdle();
  }

  @RunsInEDT
  private void simulateResizeStarted(Container c, Pair<Dimension, Insets> resizeInfo, int x, int y) {
    Point p = resizeLocation(resizeInfo);
    moveMouseIgnoringAnyError(c, p);
    moveMouseIgnoringAnyError(c, p.x + x, p.y + y);
  }

  private static Point resizeLocation(final Pair<Dimension, Insets> resizeInfo) {
    return resizeLocation(resizeInfo.i, resizeInfo.ii);
  }

  private static Point resizeLocation(Dimension size, Insets insets) {
    return resizeLocation(size.width, size.height, insets.right, insets.bottom);
  }

  private static Point resizeLocation(int width, int height, int right, int bottom) {
    return new Point(width - right / 2, height - bottom / 2);
  }

  /**
   * Move the given <code>{@link Container}</code> to the requested location.
   * @param c the target {@code Container}.
   * @param x the horizontal coordinate.
   * @param y the vertical coordinate.
   * @throws IllegalStateException if the {@code Container} is not enabled.
   * @throws IllegalStateException if the {@code Container} is not movable by the user.
   * @throws IllegalStateException if the {@code Container} is not showing on the screen.
   */
  @RunsInEDT
  public void move(Container c, int x, int y) {
    Triple<Dimension, Insets, Point> moveInfo = moveInfo(c);
    Point locationOnScreen = moveInfo.iii;
    moveBy(c, moveInfo, x - locationOnScreen.x, y - locationOnScreen.y);
  }

  @RunsInEDT
  private static Triple<Dimension, Insets, Point> moveInfo(final Container c) {
    return execute(new GuiQuery<Triple<Dimension, Insets, Point>>() {
      @Override protected Triple<Dimension, Insets, Point> executeInEDT() {
        validateCanMove(c);
        Point locationOnScreen = null;
        try {
          locationOnScreen = c.getLocationOnScreen();
        } catch (IllegalComponentStateException e) {
          // we should not get to this point, validateIsShowing should have already catched that the container is not
          // visible.
        }
        if (locationOnScreen == null) throw componentNotShowingOnScreenFailure(c);
        return new Triple<Dimension, Insets, Point>(c.getSize(), c.getInsets(), locationOnScreen) ;
      }
    });
  }

  @RunsInCurrentThread
  private static void validateCanMove(Container c) {
    validateIsEnabledAndShowing(c);
    if (!isUserMovable(c))
      throw new IllegalStateException(concat("Expecting component ", format(c), " to be movable by the user"));
  }

  @RunsInEDT
  private void moveBy(Container c, Triple<Dimension, Insets, Point> moveInfo, int x, int y) {
    simulateMoveStarted(c, moveInfo, x, y);
    Point locationOnScreen = moveInfo.iii;
    Point location = new Point(locationOnScreen.x + x, locationOnScreen.y + y);
    moveComponent(c, location);
    robot.waitForIdle();
  }

  @RunsInEDT
  private void simulateMoveStarted(Container c, Triple<Dimension, Insets, Point> moveInfo, int x, int y) {
    Point p = moveLocation(moveInfo.i, moveInfo.ii);
    moveMouseIgnoringAnyError(c, p);
    moveMouseIgnoringAnyError(c, p.x + x, p.y + y);
  }

  // Returns where the mouse usually grabs to move a container (or window.) Center of the top of the frame is usually a
  // good choice.
  private Point moveLocation(Dimension size, Insets insets) {
    return new Point(size.width / 2, insets.top / 2);
  }
}
