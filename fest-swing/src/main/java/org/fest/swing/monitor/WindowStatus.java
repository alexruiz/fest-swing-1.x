/*
 * Created on Oct 17, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.monitor;

import static java.lang.Math.max;
import static java.util.logging.Level.WARNING;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.query.ComponentSizeQuery.sizeOf;

import java.awt.*;
import java.util.logging.Logger;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.util.Pair;
import org.fest.swing.util.RobotFactory;

/**
 * Understands verification of the state of a window.
 *
 * @author Alex Ruiz
 */
class WindowStatus {

  private static final Logger LOGGER = Logger.getAnonymousLogger();

  private static final int ARBITRARY_EXTRA_VALUE = 20;

  private static int sign = 1;

  private final Windows windows;

  final Robot robot;

  WindowStatus(Windows windows) {
    this(windows, new RobotFactory());
  }

  WindowStatus(Windows windows, RobotFactory robotFactory) {
    this.windows = windows;
    Robot r = null;
    try {
      r = robotFactory.newRobotInPrimaryScreen();
    } catch (AWTException ignored) {
      LOGGER.log(WARNING, "Error ocurred when creating a new Robot", ignored);
    }
    robot = r;
  }

  Windows windows() { return windows; }

  /**
   * Checks whether the given window is ready for input.
   * @param w the given window.
   */
  @RunsInEDT
  void checkIfReady(Window w) {
    if (robot == null) return;
    try {
      checkSafelyIfReady(w);
    } catch (Exception ignored) {
      // We are getting InterruptedException in Xwnc
      // http://groups.google.com/group/easytesting/browse_frm/thread/116cc070ab7b22e6
      LOGGER.log(WARNING, "Error ocurred when checking if window is ready", ignored);
    }
  }

  private void checkSafelyIfReady(final Window w) {
    // Must avoid frame borders, which are insensitive to mouse motion (at least on w32).
    Pair<WindowMetrics, Point> metricsAndCenter = metricsAndCenter(w);
    final WindowMetrics metrics = metricsAndCenter.i;
    mouseMove(w, metricsAndCenter.ii);
    if (!windows.isShowingButNotReady(w)) return;
    execute(new GuiTask() {
      protected void executeInEDT() {
        makeLargeEnoughToReceiveEvents(w, metrics);
      }
    });
  }

  @RunsInEDT
  private static Pair<WindowMetrics, Point> metricsAndCenter(final Window w) {
    return execute(new GuiQuery<Pair<WindowMetrics, Point>>() {
      protected Pair<WindowMetrics, Point> executeInEDT() {
        WindowMetrics metrics = new WindowMetrics(w);
        return new Pair<WindowMetrics, Point>(metrics, metrics.center());
      }
    });
  }

  @RunsInEDT
  private void mouseMove(Window w, Point point) {
    int x = point.x;
    int y = point.y;
    if (x == 0 || y == 0) return;
    robot.mouseMove(x, y);
    Dimension windowSize = sizeOf(w);
    if (windowSize.width > windowSize.height) robot.mouseMove(x + sign, y);
    else robot.mouseMove(x, y + sign);
    sign = -sign;
  }

  @RunsInCurrentThread
  private void makeLargeEnoughToReceiveEvents(Window window, WindowMetrics metrics) {
    if (!isEmptyFrame(window)) return;
    int w = max(window.getWidth(), proposedWidth(metrics));
    int h = max(window.getHeight(), proposedHeight(metrics));
    window.setSize(new Dimension(w, h));
  }

  @RunsInCurrentThread
  private boolean isEmptyFrame(Window w) {
    Insets insets = w.getInsets();
    return insets.top + insets.bottom == w.getHeight() || insets.left + insets.right == w.getWidth();
  }

  @RunsInCurrentThread
  private int proposedWidth(WindowMetrics metrics) {
    return metrics.leftAndRightInsets() + ARBITRARY_EXTRA_VALUE;
  }

  @RunsInCurrentThread
  private int proposedHeight(WindowMetrics metrics) {
    return metrics.topAndBottomInsets() + ARBITRARY_EXTRA_VALUE;
  }

  static int sign() { return sign; }
}
