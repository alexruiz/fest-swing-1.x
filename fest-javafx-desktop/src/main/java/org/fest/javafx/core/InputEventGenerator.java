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

import javafx.scene.control.Control;
import javafx.scene.input.MouseButton;

import org.fest.javafx.util.Point;

/**
 * Understands simulation of input events.
 *
 * @author Alex Ruiz
 */
public interface InputEventGenerator {

  /**
   * Simulates a user pressing a mouse button.
   * @param button the mouse button to press.
   */
  void pressMouse(MouseButton button);

  /**
   * Simulates a user pressing the given mouse buttons on the given <code>{@link Control}</code>.
   * @param c the {@code Control} to click on.
   * @param button the mouse button to press.
   * @param where the given coordinates, relative to the given {@code Control}.
   */
  void pressMouse(MouseButton button, Control c, Point where);

  /**
   * Simulates a user moving the mouse pointer to the given point.
   * @param where the point to move the mouse pointer to.
   */
  void moveMouse(Point where);

  /**
   * Simulates a user moving the mouse pointer to the given coordinates relative to the given
   * <code>{@link Control}</code>.
   * @param c the given {@code Control}.
   * @param where the point, relative to the given {@code Control}.
   */
  void moveMouse(Control c, Point where);

  /**
   * Simulates a user releasing the given mouse button.
   * @param button the mouse button to release.
   */
  void releaseMouse(MouseButton button);

  /**
   * Simulates a user rotating the scroll wheel on wheel-equipped mice.
   * @param amount number of "notches" to move the mouse wheel. Negative values indicate movement up/away from the user,
   * while positive values indicate movement down/towards the user.
   */
  void rotateMouseWheel(int amount);
}
