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

import static javafx.scene.input.MouseButton.NONE;
import static org.fest.util.Strings.concat;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import org.fest.javafx.util.Point;

/**
 * Understands a template for implementations of <code>{@link InputEventGenerator}</code>.
 *
 * @author Alex Ruiz
 */
abstract class InputEventGeneratorTemplate implements InputEventGenerator {

  @Override final public InputEventGenerator moveMouse(Node node, Point where) {
    validate(node, where);
    mouseMove(node, where);
    return this;
  }

  @Override final public InputEventGenerator pressMouse(MouseButton button, Node node, Point where) {
    validate(button, node, where);
    mouseMove(node, where);
    mousePress(button);
    return this;
  }

  private void validate(MouseButton button, Node node, Point where) {
    validate(node, where);
    validateMouseButtonToPress(button);
  }

  private void validate(Node n, Point p) {
    if (n == null) throw new NullPointerException("The target Node should not be null");
    if (p == null) throw new NullPointerException("The Point where to move the mouse pointer to should not be null");
  }

  abstract void mouseMove(Node node, Point where);

  @Override public final InputEventGenerator pressMouse(MouseButton button) {
    validateMouseButtonToPress(button);
    mousePress(button);
    return this;
  }

  private void validateMouseButtonToPress(MouseButton button) {
    validate(button, "press");
  }

  abstract void mousePress(MouseButton button);

  @Override public final InputEventGenerator releaseMouse(MouseButton button) {
    validate(button, "release");
    mouseRelease(button);
    waitForIdle();
    return this;
  }

  private void validate(MouseButton button, String purpose) {
    if (button == null) throw new NullPointerException(concat("The MouseButton to ", purpose, " should not be null"));
    if (button.equals(NONE))
      throw new IllegalArgumentException(concat("The MouseButton to ", purpose, " should not be NONE"));
  }

  abstract void mouseRelease(MouseButton button);

  @Override public final InputEventGenerator rotateMouseWheel(int amount) {
    mouseWheel(amount);
    return this;
  }

  abstract void mouseWheel(int amount);

  @Override public final InputEventGenerator pressKey(KeyCode keyCode) {
    try {
      keyPress(keyCode);
      waitForIdle();
    } catch (IllegalArgumentException e) {
      throw illegalKeyCode(keyCode);
    }
    return this;
  }

  abstract void keyPress(KeyCode keyCode);

  @Override public final InputEventGenerator releaseKey(KeyCode keyCode) {
    try {
      keyRelease(keyCode);
      waitForIdle();
    } catch (IllegalArgumentException e) {
      throw illegalKeyCode(keyCode);
    }
    return this;
  }

  abstract void keyRelease(KeyCode keyCode);

  private static IllegalArgumentException illegalKeyCode(KeyCode keyCode) {
    throw new IllegalArgumentException(concat("Invalid key code '", keyCode.name(), "'"));
  }
}
