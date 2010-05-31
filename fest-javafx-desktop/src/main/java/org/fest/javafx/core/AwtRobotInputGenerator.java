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

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import org.fest.javafx.util.Point;
import org.fest.javafx.util.RobotFactory;
import org.fest.util.VisibleForTesting;

/**
 * Understands an implementation of <code>{@link InputGenerator}</code> that uses a AWT <code>{@link Robot}</code>
 * to simulate user input.
 *
 * @author Alex Ruiz
 */
class AwtRobotInputGenerator extends InputGeneratorTemplate {

  private final Robot robot;

  private static final Map<MouseButton, Integer> MOUSE_BUTTONS = new HashMap<MouseButton, Integer>();

  static {
    MOUSE_BUTTONS.put(PRIMARY, BUTTON1_MASK);
    MOUSE_BUTTONS.put(MIDDLE, BUTTON2_MASK);
    MOUSE_BUTTONS.put(SECONDARY, BUTTON3_MASK);
  }

  AwtRobotInputGenerator() {
    this(new RobotFactory());
  }

  @VisibleForTesting
  AwtRobotInputGenerator(RobotFactory robotFactory) {
    try {
      robot = robotFactory.createAwtRobot();
    } catch (AWTException e) {
      throw unexpected(e);
    }
  }

  @Override void mouseMove(Node node, Point where) {
    Point p = translateToScreenCoordinates(node, where);
    robot.mouseMove(p.x, p.y);
    waitForIdle();
  }

  @Override void mousePress(MouseButton button) {
    robot.mousePress(maskOf(button));
    waitForIdle();
  }

  @Override void mouseRelease(MouseButton button) {
    robot.mouseRelease(maskOf(button));
  }

  private static int maskOf(MouseButton button) {
    return MOUSE_BUTTONS.get(button);
  }

  @Override void mouseWheel(int amount) {
    robot.mouseWheel(amount);
  }

  @Override void keyPress(KeyCode keyCode) {
    try {
      robot.keyPress(codeFrom(keyCode));
    } catch (IllegalArgumentException e) {
      throw illegalKeyCode(keyCode);
    }
  }

  @Override void keyRelease(KeyCode keyCode) {
    try {
      robot.keyRelease(codeFrom(keyCode));
    } catch (IllegalArgumentException e) {
      throw illegalKeyCode(keyCode);
    }
  }

  private static int codeFrom(KeyCode keyCode) {
    return keyCode.impl_getCode();
  }

  private static IllegalArgumentException illegalKeyCode(KeyCode keyCode) {
    throw new IllegalArgumentException(concat("Invalid key code '", keyCode.name(), "'"));
  }

  @Override public InputGenerator waitForIdle() {
    robot.waitForIdle();
    return this;
  }
}
