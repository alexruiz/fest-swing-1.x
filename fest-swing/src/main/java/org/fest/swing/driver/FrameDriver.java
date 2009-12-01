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

import static java.awt.Frame.*;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.WindowLikeContainerLocations.iconifyLocationOf;
import static org.fest.swing.driver.WindowLikeContainerLocations.maximizeLocationOf;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;

import java.awt.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.annotation.ThreadSafeAction;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.ActionFailedException;

/**
 * Understands:
 * <ul>
 * <li>simulation of user input on a <code>{@link Frame}</code> (if applicable)</li>
 * <li>state verification of a <code>{@link Frame}</code></li>
 * </ul>
 * This class is intended for internal use only.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class FrameDriver extends WindowDriver {

  /**
   * Creates a new </code>{@link FrameDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public FrameDriver(Robot robot) {
    super(robot);
  }

  /**
   * Iconifies the given <code>{@link Frame}</code>.
   * @param frame the given <code>Frame</code>.
   * @throws IllegalStateException if the <code>Frame</code> is not enabled.
   * @throws IllegalStateException if the <code>Frame</code> is not showing on the screen.
   */
  @RunsInEDT
  public void iconify(Frame frame) {
    moveMouseIgnoringAnyError(frame, iconifyInfo(frame));
    robot.waitForIdle();
    updateFrameExtendedState(frame, ICONIFIED);
  }

  @RunsInEDT
  private static Point iconifyInfo(final Frame frame) {
    return execute(new GuiQuery<Point>() {
      protected Point executeInEDT() {
        validateIsEnabledAndShowing(frame);
        return iconifyLocationOf(frame);
      }
    });
  }

  /**
   * Deiconifies the given <code>{@link Frame}</code>.
   * @param frame the given <code>Frame</code>.
   * @throws IllegalStateException if the <code>Frame</code> is not enabled.
   * @throws IllegalStateException if the <code>Frame</code> is not showing on the screen.
   */
  @RunsInEDT
  public void deiconify(Frame frame) {
    assertIsEnabledAndShowing(frame);
    updateFrameExtendedState(frame, NORMAL);
  }

  /**
   * Normalizes the given <code>{@link Frame}</code>.
   * @param frame the given <code>Frame</code>.
   * @throws IllegalStateException if the <code>Frame</code> is not enabled.
   * @throws IllegalStateException if the <code>Frame</code> is not showing on the screen.
   */
  @RunsInEDT
  public void normalize(Frame frame) {
    assertIsEnabledAndShowing(frame);
    updateFrameExtendedState(frame, NORMAL);
  }

  /**
   * Makes the <code>{@link Frame}</code> full size.
   * @param frame the target <code>Frame</code>.
   * @throws IllegalStateException if the <code>Frame</code> is not enabled.
   * @throws IllegalStateException if the <code>Frame</code> is not showing on the screen.
   * @throws ActionFailedException if the operating system does not support maximizing frames.
   */
  @RunsInEDT
  public void maximize(Frame frame) {
    moveMouseIgnoringAnyError(frame, maximizeInfo(frame));
    if (!supportsMaximize(Toolkit.getDefaultToolkit()))
      throw actionFailure("Platform does not support maximizing frames");
    updateFrameExtendedState(frame, MAXIMIZED_BOTH);
  }

  @RunsInEDT
  private static Point maximizeInfo(final Frame frame) {
    return execute(new GuiQuery<Point>() {
      protected Point executeInEDT() {
        validateIsEnabledAndShowing(frame);
        return maximizeLocationOf(frame);
      }
    });
  }

  @ThreadSafeAction
  private void updateFrameExtendedState(Frame frame, int state) {
    frame.setExtendedState(state);
  }

  static boolean supportsMaximize(Toolkit toolkit) {
    return toolkit.isFrameStateSupported(MAXIMIZED_BOTH);
  }
}
