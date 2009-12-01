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
import static org.fest.swing.driver.JSliderSetValueTask.setValue;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Strings.concat;

import java.awt.Point;

import javax.swing.JSlider;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.util.GenericRange;
import org.fest.swing.util.Pair;

/**
 * Understands functional testing of <code>{@link JSlider}</code>s including:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JSliderDriver extends JComponentDriver {

  private final JSliderLocation location;

  /**
   * Creates a new </code>{@link JSliderDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JSliderDriver(Robot robot) {
    super(robot);
    location = new JSliderLocation();
  }

  /**
   * Slides the knob to its maximum.
   * @param slider the target <code>JSlider</code>.
   * @throws IllegalStateException if the <code>JSlider</code> is disabled.
   * @throws IllegalStateException if the <code>JSlider</code> is not showing on the screen.
   */
  @RunsInEDT
  public void slideToMaximum(JSlider slider) {
    slide(slider, validateAndFindSlideToMaximumInfo(slider, location));
  }

  @RunsInEDT
  private static Pair<Integer, GenericRange<Point>> validateAndFindSlideToMaximumInfo(final JSlider slider,
      final JSliderLocation location) {
    return execute(new GuiQuery<Pair<Integer, GenericRange<Point>>>() {
      protected Pair<Integer, GenericRange<Point>> executeInEDT() {
        validateIsEnabledAndShowing(slider);
        int value = slider.getMaximum();
        GenericRange<Point> fromAndTo = slideInfo(slider, location, value);
        return new Pair<Integer, GenericRange<Point>>(value, fromAndTo);
      }
    });
  }

  /**
   * Slides the knob to its minimum.
   * @param slider the target <code>JSlider</code>.
   * @throws IllegalStateException if the <code>JSlider</code> is disabled.
   * @throws IllegalStateException if the <code>JSlider</code> is not showing on the screen.
   */
  @RunsInEDT
  public void slideToMinimum(JSlider slider) {
    slide(slider, validateAndFindSlideToMinimumInfo(slider, location));
  }

  @RunsInEDT
  private static Pair<Integer, GenericRange<Point>> validateAndFindSlideToMinimumInfo(final JSlider slider,
      final JSliderLocation location) {
    return execute(new GuiQuery<Pair<Integer, GenericRange<Point>>>() {
      protected Pair<Integer, GenericRange<Point>> executeInEDT() {
        validateIsEnabledAndShowing(slider);
        int value = slider.getMinimum();
        GenericRange<Point> fromAndTo = slideInfo(slider, location, value);
        return new Pair<Integer, GenericRange<Point>>(value, fromAndTo);
      }
    });
  }

  @RunsInEDT
  private void slide(JSlider slider, Pair<Integer, GenericRange<Point>> slideInfo) {
    slide(slider, slideInfo.i, slideInfo.ii);
  }

  /**
   * Slides the knob to the requested value.
   * @param slider the target <code>JSlider</code>.
   * @param value the requested value.
   * @throws IllegalStateException if the <code>JSlider</code> is disabled.
   * @throws IllegalStateException if the <code>JSlider</code> is not showing on the screen.
   * @throws IllegalArgumentException if the given position is not within the <code>JSlider</code> bounds.
   */
  @RunsInEDT
  public void slide(JSlider slider, int value) {
    GenericRange<Point> slideInfo = validateAndFindSlideInfo(slider, location, value);
    slide(slider, value, slideInfo);
  }

  @RunsInEDT
  private void slide(JSlider slider, int value, GenericRange<Point> fromAndTo) {
    moveMouseIgnoringAnyError(slider, fromAndTo.from);
    moveMouseIgnoringAnyError(slider, fromAndTo.to);
    setValue(slider, value);
    robot.waitForIdle();
  }

  @RunsInEDT
  private static GenericRange<Point> validateAndFindSlideInfo(final JSlider slider, final JSliderLocation location,
      final int value) {
    return execute(new GuiQuery<GenericRange<Point>>() {
      protected GenericRange<Point> executeInEDT() {
        validateValue(slider, value);
        validateIsEnabledAndShowing(slider);
        return slideInfo(slider, location, value);
      }
    });
  }

  @RunsInCurrentThread
  private static void validateValue(JSlider slider, int value) {
    int min = slider.getMinimum();
    int max = slider.getMaximum();
    if (value >= min && value <= max) return;
    throw new IllegalArgumentException(
        concat("Value <", value, "> is not within the JSlider bounds of <", min, "> and <", max, ">"));
  }

  @RunsInCurrentThread
  private static GenericRange<Point> slideInfo(JSlider slider, JSliderLocation location, int value) {
    Point from = location.pointAt(slider, slider.getValue());
    Point to = location.pointAt(slider, value);
    return new GenericRange<Point>(from, to);
  }
}
