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

import static javafx.scene.input.MouseButton.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.core.Visibility.REQUIRE_VISIBLE;
import static org.fest.javafx.threading.GuiActionRunner.execute;

import java.util.*;

import javafx.scene.Node;
import javafx.scene.input.MouseButton;
import javafx.scene.text.Text;

import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.threading.GuiQuery;
import org.fest.javafx.util.Point;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for implementations of <code>{@link InputEventGenerator#pressMouse(MouseButton, Node, Point)}</code>
 * and <code>{@link InputEventGenerator#releaseMouse(MouseButton)}</code>.
 *
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public abstract class InputEventGenerator_pressMouse_onControl_and_releaseMouse_TestCase extends
    InputEventGenerator_mouse_TestCase {

  private final MouseButton mouseButton;

  @Parameters
  public static Collection<Object[]> mouseButtons() {
    List<Object[]> parameters = new ArrayList<Object[]>();
    parameters.add(new Object[] { PRIMARY });
    parameters.add(new Object[] { MIDDLE });
    parameters.add(new Object[] { SECONDARY });
    return parameters;
  }

  public InputEventGenerator_pressMouse_onControl_and_releaseMouse_TestCase(MouseButton mouseButton) {
    this.mouseButton = mouseButton;
  }

  @Test
  public void should_press_mouse() {
    inputEventGenerator().pressMouse(mouseButton, button(), centerOfButton()).releaseMouse(mouseButton);
    verifyClickedButton();
    verifyMousePointerIsOverCenterOfButton();
  }

  private void verifyClickedButton() {
    Text text = nodeFinder().findById(scene(), "mouseButtonText", Text.class, REQUIRE_VISIBLE);
    assertThat(contentsOf(text)).isEqualTo(mouseButton.name());
  }

  @RunsInUIThread
  private static String contentsOf(final Text text) {
    return execute(new GuiQuery<String>() {
      @Override protected String executeInUIThread() {
        return text.get$content();
      }
    });
  }
}
