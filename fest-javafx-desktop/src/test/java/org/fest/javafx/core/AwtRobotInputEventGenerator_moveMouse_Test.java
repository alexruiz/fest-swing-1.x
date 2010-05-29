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

import org.fest.javafx.util.Point;
import org.fest.ui.testing.annotation.GuiTest;
import org.junit.Test;

/**
 * Tests for <code>{@link AwtRobotInputEventGenerator#moveMouse(Control, Point)}</code>.
 *
 * @author Alex Ruiz
 */
@GuiTest
public class AwtRobotInputEventGenerator_moveMouse_Test extends AwtRobotInputEventGenerator_mouse_TestCase {

  @Test
  public void should_move_mouse() {
    inputEventGenerator().moveMouse(button(), centerOfButton());
    verifyMousePointerScreenLocation();
  }
}
