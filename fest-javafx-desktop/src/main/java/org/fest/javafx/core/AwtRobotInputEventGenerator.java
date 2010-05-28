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

import java.awt.Robot;

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
  
  AwtRobotInputEventGenerator(RobotFactory robotFactory) {
    robot = robotFactory.createAwtRobot();
  }
  
  /** {@inheritDoc} */
  @Override public void moveMouse(Point where) {
  // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void moveMouse(Control c, Point where) {
  // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void pressMouse(MouseButton button) {
  // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void pressMouse(MouseButton button, Control c, Point where) {
  // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void releaseMouse(MouseButton button) {
  // TODO Auto-generated method stub

  }

  /** {@inheritDoc} */
  @Override
  public void rotateMouseWheel(int amount) {
  // TODO Auto-generated method stub

  }

}
