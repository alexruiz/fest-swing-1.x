/*
 * Created on May 27, 2010
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
package org.fest.javafx.core;

import static java.awt.event.InputEvent.*;
import static javafx.scene.input.MouseButton.*;
import static org.fest.ui.testing.exception.UnexpectedException.unexpected;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Control;
import javafx.scene.input.MouseButton;

import org.fest.javafx.util.*;

/**
 * Understands an implementation of <code>{@link InputEventGenerator}</code> that uses a AWT <code>{@link Robot}</code>
 * to simulate user input.
 *
 * @author Alex Ruiz
 */
class AwtRobotInputEventGenerator implements InputEventGenerator {

  private final Robot robot;

  private static final Map<MouseButton, Integer> MOUSE_BUTTONS = new HashMap<MouseButton, Integer>();

  static {
    MOUSE_BUTTONS.put(PRIMARY, BUTTON1_MASK);
    MOUSE_BUTTONS.put(MIDDLE, BUTTON2_MASK);
    MOUSE_BUTTONS.put(SECONDARY, BUTTON3_MASK);
  }

  AwtRobotInputEventGenerator() {
    this(new RobotFactory());
  }

  AwtRobotInputEventGenerator(RobotFactory robotFactory) {
    try {
      robot = robotFactory.createAwtRobot();
    } catch (AWTException e) {
      throw unexpected(e);
    }
  }

  /** {@inheritDoc} */
  @Override public void moveMouse(Control control, Point where) {
    validateNotNull(control, where);
    Point p = ScreenLocations.translate(control, where);
    robot.mouseMove(p.x, p.y);
  }

  private void validateNotNull(Control c, Point p) {
    if (c == null) throw new NullPointerException("The target Control should not be null");
    if (p == null) throw new NullPointerException("The Point where to move the mouse pointer to should not be null");
  }

  /** {@inheritDoc} */
  @Override public void pressMouse(MouseButton button) {
    if (button == null) throw new NullPointerException("The MouseButton to press should not be null");
    robot.mousePress(maskOf(button));
  }

  /** {@inheritDoc} */
  @Override
  public void pressMouse(MouseButton button, Control control, Point where) {
    moveMouse(control, where);
    if (button == null) throw new NullPointerException("The MouseButton to press should not be null");
  }

  /** {@inheritDoc} */
  @Override
  public void releaseMouse(MouseButton button) {
    if (button == null) throw new NullPointerException("The MouseButton to release should not be null");
    robot.mouseRelease(maskOf(button));
  }

  private static int maskOf(MouseButton button) {
    return MOUSE_BUTTONS.get(button);
  }

  /** {@inheritDoc} */
  @Override
  public void rotateMouseWheel(int amount) {
    robot.mouseWheel(amount);
  }
}
