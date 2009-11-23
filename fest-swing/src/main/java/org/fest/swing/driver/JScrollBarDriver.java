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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.JScrollBarSetValueTask.setValue;
import static org.fest.swing.driver.JScrollBarValueQuery.valueOf;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Strings.concat;

import java.awt.Point;

import javax.swing.JScrollBar;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.util.GenericRange;
import org.fest.swing.util.Pair;

/**
 * Understands simulation of user input on a <code>{@link JScrollBar}</code>. Unlike <code>JScrollBarFixture</code>,
 * this driver only focuses on behavior present only in <code>{@link JScrollBar}</code>s. This class is intended for
 * internal use only.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JScrollBarDriver extends JComponentDriver {

  private static final String VALUE_PROPERTY = "value";

  private final JScrollBarLocation location = new JScrollBarLocation();

  /**
   * Creates a new </code>{@link JScrollBarDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JScrollBarDriver(Robot robot) {
    super(robot);
  }

  /**
   * Scrolls up (or right) one unit (usually a line).
   * @param scrollBar the target <code>JScrollBar</code>.
   */
  public void scrollUnitUp(JScrollBar scrollBar) {
    scrollUnitUp(scrollBar, 1);
  }

  /**
   * Scrolls up (or right) one unit (usually a line,) the given number of times.
   * @param scrollBar the target <code>JScrollBar</code>.
   * @param times the number of times to scroll up one unit.
   * @throws IllegalArgumentException if <code>times</code> is less than or equal to zero.
   * @throws IllegalStateException if the <code>JScrollBar</code> is disabled.
   * @throws IllegalStateException if the <code>JScrollBar</code> is not showing on the screen.
   */
  public void scrollUnitUp(JScrollBar scrollBar, int times) {
    validateTimes(times, "scroll up one unit");
    Pair<Point, Integer> scrollInfo = validateAndFindScrollUnitInfo(scrollBar, location, times);
    scroll(scrollBar, scrollInfo);
  }

  /**
   * Scroll down (or left) one unit (usually a line).
   * @param scrollBar the target <code>JScrollBar</code>.
   */
  public void scrollUnitDown(JScrollBar scrollBar) {
    scrollUnitDown(scrollBar, 1);
  }

  /**
   * Scrolls down one unit (usually a line,) the given number of times.
   * @param scrollBar the target <code>JScrollBar</code>.
   * @param times the number of times to scroll down one unit.
   * @throws IllegalArgumentException if <code>times</code> is less than or equal to zero.
   * @throws IllegalStateException if the <code>JScrollBar</code> is disabled.
   * @throws IllegalStateException if the <code>JScrollBar</code> is not showing on the screen.
   */
  public void scrollUnitDown(JScrollBar scrollBar, int times) {
    validateTimes(times, "scroll down one unit");
    Pair<Point, Integer> scrollInfo = validateAndFindScrollUnitInfo(scrollBar, location, times * -1);
    scroll(scrollBar, scrollInfo);
  }

  @RunsInEDT
  private static Pair<Point, Integer> validateAndFindScrollUnitInfo(final JScrollBar scrollBar,
      final JScrollBarLocation location, final int times) {
    return execute(new GuiQuery<Pair<Point, Integer>>() {
      protected Pair<Point, Integer> executeInEDT() {
        validateIsEnabledAndShowing(scrollBar);
        return scrollUnitInfo(scrollBar, location, times);
      }
    });
  }

  @RunsInCurrentThread
  private static Pair<Point, Integer> scrollUnitInfo(JScrollBar scrollBar, JScrollBarLocation location, int times) {
    Point where = location.blockLocationToScrollDown(scrollBar);
    int count = times * scrollBar.getUnitIncrement();
    return new Pair<Point, Integer>(where, scrollBar.getValue() + count);
  }

  /**
   * Scrolls up (or right) one block (usually a page).
   * @param scrollBar the target <code>JScrollBar</code>.
   */
  @RunsInEDT
  public void scrollBlockUp(JScrollBar scrollBar) {
    scrollBlockUp(scrollBar, 1);
  }

  /**
   * Scrolls up (or right) one block (usually a page,) the given number of times.
   * @param scrollBar the target <code>JScrollBar</code>.
   * @param times the number of times to scroll up one block.
   * @throws IllegalArgumentException if <code>times</code> is less than or equal to zero.
   * @throws IllegalStateException if the <code>JScrollBar</code> is disabled.
   * @throws IllegalStateException if the <code>JScrollBar</code> is not showing on the screen.
   */
  @RunsInEDT
  public void scrollBlockUp(JScrollBar scrollBar, int times) {
    validateTimes(times, "scroll up one block");
    Pair<Point, Integer> scrollInfo = validateAndFindScrollBlockInfo(scrollBar, location, times);
    scroll(scrollBar, scrollInfo);
  }

  /**
   * Scrolls down (or left) one block (usually a page).
   * @param scrollBar the target <code>JScrollBar</code>.
   */
  @RunsInEDT
  public void scrollBlockDown(JScrollBar scrollBar) {
    scrollBlockDown(scrollBar, 1);
  }

  /**
   * Scrolls down (or left) one block (usually a page,) the given number of times.
   * @param scrollBar the target <code>JScrollBar</code>.
   * @param times the number of times to scroll down one block.
   * @throws IllegalArgumentException if <code>times</code> is less than or equal to zero.
   * @throws IllegalStateException if the <code>JScrollBar</code> is disabled.
   * @throws IllegalStateException if the <code>JScrollBar</code> is not showing on the screen.
   */
  @RunsInEDT
  public void scrollBlockDown(JScrollBar scrollBar, int times) {
    validateTimes(times, "scroll down one block");
    Pair<Point, Integer> scrollInfo = validateAndFindScrollBlockInfo(scrollBar, location, times * -1);
    scroll(scrollBar, scrollInfo);
  }

  private void validateTimes(int times, String action) {
    if (times > 0) return;
    String message = concat(
        "The number of times to ", action, " should be greater than zero, but was <", times, ">");
    throw new IllegalArgumentException(message);
  }

  @RunsInEDT
  private static Pair<Point, Integer> validateAndFindScrollBlockInfo(final JScrollBar scrollBar,
      final JScrollBarLocation location, final int times) {
    return execute(new GuiQuery<Pair<Point, Integer>>() {
      protected Pair<Point, Integer> executeInEDT() {
        validateIsEnabledAndShowing(scrollBar);
        return scrollBlockInfo(scrollBar, location, times);
      }
    });
  }

  @RunsInCurrentThread
  private static Pair<Point, Integer> scrollBlockInfo(JScrollBar scrollBar, JScrollBarLocation location, int times) {
    Point where = location.blockLocationToScrollDown(scrollBar);
    int count = times * scrollBar.getBlockIncrement();
    return new Pair<Point, Integer>(where, scrollBar.getValue() + count);
  }

  @RunsInEDT
  private void scroll(JScrollBar scrollBar, Pair<Point, Integer> scrollInfo) {
    // For now, do it programmatically, faking the mouse movement and clicking
    robot.moveMouse(scrollBar, scrollInfo.i);
    setValueProperty(scrollBar, scrollInfo.ii);
  }

  /**
   * Scrolls to the maximum position of the given <code>{@link JScrollBar}</code>.
   * @param scrollBar the target <code>JScrollBar</code>.
   * @throws IllegalStateException if the <code>JScrollBar</code> is disabled.
   * @throws IllegalStateException if the <code>JScrollBar</code> is not showing on the screen.
   */
  @RunsInEDT
  public void scrollToMaximum(JScrollBar scrollBar) {
    Pair<Integer, GenericRange<Point>> scrollInfo = validateAndFindScrollToMaximumInfo(scrollBar, location);
    scroll(scrollBar, scrollInfo.i, scrollInfo.ii);
  }

  @RunsInEDT
  private static Pair<Integer, GenericRange<Point>> validateAndFindScrollToMaximumInfo(final JScrollBar scrollBar,
      final JScrollBarLocation location) {
    return execute(new GuiQuery<Pair<Integer, GenericRange<Point>>>() {
      protected Pair<Integer, GenericRange<Point>> executeInEDT() {
        validateIsEnabledAndShowing(scrollBar);
        int position = scrollBar.getMaximum();
        GenericRange<Point> scrollInfo = scrollInfo(scrollBar, location, position);
        return new Pair<Integer, GenericRange<Point>>(position, scrollInfo);
      }
    });
  }

  /**
   * Scrolls to the minimum position of the given <code>{@link JScrollBar}</code>.
   * @param scrollBar the target <code>JScrollBar</code>.
   * @throws IllegalStateException if the <code>JScrollBar</code> is disabled.
   * @throws IllegalStateException if the <code>JScrollBar</code> is not showing on the screen.
   */
  @RunsInEDT
  public void scrollToMinimum(JScrollBar scrollBar) {
    Pair<Integer, GenericRange<Point>> scrollInfo = validateAndFindScrollToMinimumInfo(scrollBar, location);
    scroll(scrollBar, scrollInfo.i, scrollInfo.ii);
  }

  @RunsInEDT
  private static Pair<Integer, GenericRange<Point>> validateAndFindScrollToMinimumInfo(final JScrollBar scrollBar,
      final JScrollBarLocation location) {
    return execute(new GuiQuery<Pair<Integer, GenericRange<Point>>>() {
      protected Pair<Integer, GenericRange<Point>> executeInEDT() {
        validateIsEnabledAndShowing(scrollBar);
        int position = scrollBar.getMinimum();
        GenericRange<Point> scrollInfo = scrollInfo(scrollBar, location, position);
        return new Pair<Integer, GenericRange<Point>>(position, scrollInfo);
      }
    });
  }

  /**
   * Scrolls to the given position.
   * @param scrollBar the target <code>JScrollBar</code>.
   * @param position the position to scroll to.
   * @throws IllegalStateException if the <code>JScrollBar</code> is disabled.
   * @throws IllegalStateException if the <code>JScrollBar</code> is not showing on the screen.
   * @throws IllegalArgumentException if the given position is not within the <code>JScrollBar</code> bounds.
   */
  @RunsInEDT
  public void scrollTo(JScrollBar scrollBar, int position) {
    GenericRange<Point> scrollInfo = validateAndFindScrollInfo(scrollBar, location, position);
    scroll(scrollBar, position, scrollInfo);
  }

  @RunsInEDT
  private static GenericRange<Point> validateAndFindScrollInfo(final JScrollBar scrollBar,
      final JScrollBarLocation location, final int position) {
    return execute(new GuiQuery<GenericRange<Point>>() {
      protected GenericRange<Point> executeInEDT() {
        validatePosition(scrollBar, position);
        validateIsEnabledAndShowing(scrollBar);
        return scrollInfo(scrollBar, location, position);
      }
    });
  }

  @RunsInCurrentThread
  private static void validatePosition(JScrollBar scrollBar, int position) {
    int min = scrollBar.getMinimum();
    int max = scrollBar.getMaximum();
    if (position >= min && position <= max) return;
    throw new IllegalArgumentException(concat(
        "Position <", position, "> is not within the JScrollBar bounds of <", min, "> and <", max, ">"));
  }

  @RunsInCurrentThread
  private static GenericRange<Point> scrollInfo(JScrollBar scrollBar, JScrollBarLocation location, int position) {
    Point from = location.thumbLocation(scrollBar, scrollBar.getValue());
    Point to = location.thumbLocation(scrollBar, position);
    return new GenericRange<Point>(from, to);
  }

  private void scroll(JScrollBar scrollBar, int position, GenericRange<Point> points) {
    simulateScrolling(scrollBar, points);
    setValueProperty(scrollBar, position);
  }

  @RunsInEDT
  private void simulateScrolling(JScrollBar scrollBar, GenericRange<Point> points) {
    robot.moveMouse(scrollBar, points.from);
    robot.moveMouse(scrollBar, points.to);
  }

  @RunsInEDT
  private void setValueProperty(JScrollBar scrollBar, int value) {
    setValue(scrollBar, value);
    robot.waitForIdle();
  }

  /**
   * Asserts that the value of the <code>{@link JScrollBar}</code> is equal to the given one.
   * @param scrollBar the target <code>JScrollBar</code>.
   * @param value the expected value.
   * @throws AssertionError if the value of the <code>JScrollBar</code> is not equal to the given one.
   */
  @RunsInEDT
  public void requireValue(JScrollBar scrollBar, int value) {
    assertThat(valueOf(scrollBar)).as(propertyName(scrollBar, VALUE_PROPERTY)).isEqualTo(value);
  }
}
