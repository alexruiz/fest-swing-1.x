/*
 * Created on May 30, 2010
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

import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;

import org.fest.javafx.util.Point;

/**
 * Needed for testing <code>{@link InputEventGeneratorTemplate}</code>.
 *
 * @author Alex Ruiz
 */
public class TestInputEventGeneratorTemplate extends InputEventGeneratorTemplate {

  @Override void keyPress(KeyCode keyCode) {}

  @Override void keyRelease(KeyCode keyCode) {}

  @Override void mouseMove(Node node, Point where) {}

  @Override void mousePress(MouseButton button) {}

  @Override void mouseRelease(MouseButton button) {}

  @Override void mouseWheel(int amount) {}

  @Override public InputEventGenerator waitForIdle() {
    return null;
  }
}
