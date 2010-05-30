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
import static org.fest.javafx.util.ScreenLocations.translateToScreenCoordinates;
import static org.fest.ui.testing.exception.UnexpectedException.unexpected;
import static org.fest.util.Strings.concat;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.control.Control;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import org.fest.javafx.util.*;
import org.fest.util.VisibleForTesting;

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

  @VisibleForTesting
  AwtRobotInputEventGenerator(RobotFactory robotFactory) {
    try {
      robot = robotFactory.createAwtRobot();
    } catch (AWTException e) {
      throw unexpected(e);
    }
  }

  /** {@inheritDoc} */
  @Override public AwtRobotInputEventGenerator moveMouse(Control control, Point where) {
    validate(control, where);
    mouseMove(control, where);
    return this;
  }

  /** {@inheritDoc} */
  @Override
  public AwtRobotInputEventGenerator pressMouse(MouseButton button, Control control, Point where) {
    validate(control, where);
    validateMouseButtonToPress(button);
    mouseMove(control, where);
    mousePress(button);
    return this;
  }

  private void validate(Control c, Point p) {
    if (c == null) throw new NullPointerException("The target Control should not be null");
    if (p == null) throw new NullPointerException("The Point where to move the mouse pointer to should not be null");
  }

  private void mouseMove(Control control, Point where) {
    Point p = translateToScreenCoordinates(control, where);
    robot.mouseMove(p.x, p.y);
    waitForIdle();
  }

  /** {@inheritDoc} */
  @Override public AwtRobotInputEventGenerator pressMouse(MouseButton button) {
    validateMouseButtonToPress(button);
    mousePress(button);
    return this;
  }

  private void validateMouseButtonToPress(MouseButton button) {
    validate(button, "press");
  }

  private void mousePress(MouseButton button) {
    robot.mousePress(maskOf(button));
    waitForIdle();
  }

  /** {@inheritDoc} */
  @Override public AwtRobotInputEventGenerator releaseMouse(MouseButton button) {
    validate(button, "release");
    robot.mouseRelease(maskOf(button));
    waitForIdle();
    return this;
  }

  private void validate(MouseButton button, String purpose) {
    if (button == null) throw new NullPointerException(concat("The MouseButton to ", purpose, " should not be null"));
    if (button.equals(NONE))
      throw new IllegalArgumentException(concat("The MouseButton to ", purpose, " should not be NONE"));
  }

  private static int maskOf(MouseButton button) {
    return MOUSE_BUTTONS.get(button);
  }

  /** {@inheritDoc} */
  @Override public AwtRobotInputEventGenerator rotateMouseWheel(int amount) {
    robot.mouseWheel(amount);
    return this;
  }

  /** {@inheritDoc} */
  @Override public AwtRobotInputEventGenerator pressKey(KeyCode keyCode) {
    try {
      robot.keyPress(codeFrom(keyCode));
      waitForIdle();
    } catch (IllegalArgumentException e) {
      throw illegalKeyCode(keyCode);
    }
    return this;
  }

  /** {@inheritDoc} */
  @Override public InputEventGenerator releaseKey(KeyCode keyCode) {
    try {
      robot.keyRelease(codeFrom(keyCode));
      waitForIdle();
    } catch (IllegalArgumentException e) {
      throw illegalKeyCode(keyCode);
    }
    return this;
  }

  private static int codeFrom(KeyCode keyCode) {
    return keyCode.impl_getCode();
  }

  private static IllegalArgumentException illegalKeyCode(KeyCode keyCode) {
    throw new IllegalArgumentException(concat("Invalid key code '", keyCode.name(), "'"));
  }

  /** {@inheritDoc} */
  @Override public InputEventGenerator waitForIdle() {
    robot.waitForIdle();
    return this;
  }
}
