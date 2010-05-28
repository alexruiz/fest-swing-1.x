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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.core.Visibility.REQUIRE_VISIBLE;
import static org.fest.javafx.util.MousePointer.mousePointerOnScreen;
import static org.fest.javafx.util.Nodes.centerOf;
import static org.fest.javafx.util.ScreenLocations.translateToScreenCoordinates;

import javafx.scene.control.Button;
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
public class AwtRobotInputEventGenerator_mouseMove_Test extends AwtRobotInputEventGenerator_TestCase {

  @Test
  public void should_move_mouse() {
    Button button = nodeFinder().findByType(scene(), Button.class, REQUIRE_VISIBLE);
    Point centerOfButton = centerOf(button);
    inputEventGenerator().moveMouse(button, centerOfButton);
    Point expectedMousePointerLocation = translateToScreenCoordinates(button, centerOfButton);
    assertThat(mousePointerOnScreen()).isEqualTo(expectedMousePointerLocation);
  }
}
