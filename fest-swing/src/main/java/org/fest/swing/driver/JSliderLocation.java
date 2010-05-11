/*
 * Created on Jan 27, 2008
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
package org.fest.swing.driver;

import static javax.swing.SwingConstants.HORIZONTAL;
import static javax.swing.SwingConstants.VERTICAL;

import java.awt.Insets;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JSlider;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a location in a <code>{@link JSlider}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class JSliderLocation {

  private static final Map<Integer, JSliderLocationStrategy> LOCATIONS = new HashMap<Integer, JSliderLocationStrategy>();

  static {
    LOCATIONS.put(HORIZONTAL, new JSliderHorizontalLocation());
    LOCATIONS.put(VERTICAL, new JSliderVerticalLocation());
  }

  /**
   * Returns the coordinates of the given value in the given <code>{@link JSlider}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for ensuring that this method is executed in the EDT.
   * </p>
   * @param slider the given <code>JSlider</code>.
   * @param value the given value.
   * @return the coordinates of the given value in the given <code>JSlider</code>.
   */
  @RunsInCurrentThread
  public Point pointAt(JSlider slider, int value) {
    return LOCATIONS.get(slider.getOrientation()).locationForValue(slider, value);
  }

  private static class JSliderHorizontalLocation extends JSliderLocationStrategy {
    @RunsInCurrentThread
    int max(JSlider slider, Insets insets) {
      return slider.getWidth() - insets.left - insets.right - 1;
    }

    @RunsInCurrentThread
    Point update(Point center, int coordinate) {
      return new Point(coordinate, center.y);
    }
  }

  private static class JSliderVerticalLocation extends JSliderLocationStrategy {
    @RunsInCurrentThread
    int max(JSlider slider, Insets insets) {
      return slider.getHeight() - insets.top - insets.bottom - 1;
    }

    @RunsInCurrentThread
    Point update(Point center, int coordinate) {
      return new Point(center.x, coordinate);
    }
  }

  private static abstract class JSliderLocationStrategy {
    @RunsInCurrentThread
    final Point locationForValue(JSlider slider, int value) {
      Point center = new Point(slider.getWidth() / 2, slider.getHeight() / 2);
      int max = max(slider, slider.getInsets());
      int coordinate = (int)(percent(slider, value) * max);
      if (!slider.getInverted()) coordinate = max - coordinate;
      return update(center, coordinate);
    }

    @RunsInCurrentThread
    abstract int max(JSlider slider, Insets insets);

    @RunsInCurrentThread
    abstract Point update(Point center, int coordinate);

    @RunsInCurrentThread
    private float percent(JSlider slider, int value) {
      int minimum = slider.getMinimum();
      int range = slider.getMaximum() - minimum;
      return (float)(value - minimum) / range;
    }
  }
}
