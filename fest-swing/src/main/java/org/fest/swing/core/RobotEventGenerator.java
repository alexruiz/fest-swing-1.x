/*
 * Created on Apr 2, 2008
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
package org.fest.swing.core;

import static java.lang.String.valueOf;
import static org.fest.swing.awt.AWT.translate;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.exception.UnexpectedException.unexpected;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.swing.util.Platform.isOSX;
import static org.fest.swing.util.Platform.isWindows;
import static org.fest.util.Strings.concat;

import java.awt.AWTException;
import java.awt.Component;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;

import org.fest.swing.util.RobotFactory;

/**
 * Understands input event generation using a AWT <code>{@link Robot}</code>.
 *
 * @author Alex Ruiz
 */
class RobotEventGenerator implements InputEventGenerator {

  private static final int KEY_INPUT_DELAY = 200;

  private final Robot robot;
  private final Settings settings;

  RobotEventGenerator() {
    this(new Settings());
  }

  RobotEventGenerator(Settings settings) {
    this(new RobotFactory(), settings);
  }

  RobotEventGenerator(RobotFactory robotFactory, Settings settings) {
    try {
      robot = robotFactory.newRobotInPrimaryScreen();
      if (isWindows() || isOSX()) pause(500);
    } catch (AWTException e) {
      throw unexpected(e);
    }
    this.settings = settings;
    settings.attachTo(robot);
  }

  Robot robot() { return robot; }

  /** {@inheritDoc} */
  public void pressMouse(Component c, Point where, int buttons) {
    Point p = where;
    if (c != null) {
      p = translate(c, where.x, where.y);
      if (!isPointInScreenBoundaries(p))
        throw actionFailure("The component to click is out of the boundaries of the screen");
    }
    pressMouse(p, buttons);
  }

  private boolean isPointInScreenBoundaries(Point p) {
    Rectangle screen = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
    return screen.contains(p);
  }

  /** {@inheritDoc} */
  public void pressMouse(Point where, int buttons) {
    moveMouse(where.x, where.y);
    pressMouse(buttons);
  }

  /** {@inheritDoc} */
  public void pressMouse(int buttons) {
    robot.mousePress(buttons);
  }

  /** {@inheritDoc} */
  public void releaseMouse(int buttons) {
    robot.mouseRelease(buttons);
  }

  /** {@inheritDoc} */
  public void rotateMouseWheel(int amount) {
    robot.mouseWheel(amount);
  }

  /** {@inheritDoc} */
  public void moveMouse(Component c, int x, int y) {
    Point p = translate(c, x, y);
    moveMouse(p.x, p.y);
  }

  /** {@inheritDoc} */
  public void moveMouse(int x, int y) {
    robot.mouseMove(x, y);
  }

  /** {@inheritDoc} */
  public void pressKey(int keyCode, char keyChar) {
    try {
      robot.keyPress(keyCode);
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(concat("Invalid key code '", valueOf(keyCode), "'"));
    }
  }

  /** {@inheritDoc} */
  public void releaseKey(int keyCode) {
    robot.keyRelease(keyCode);
    if (!isOSX()) return;
    int delayBetweenEvents = settings.delayBetweenEvents();
    if (KEY_INPUT_DELAY > delayBetweenEvents) pause(KEY_INPUT_DELAY - delayBetweenEvents);
  }
}
