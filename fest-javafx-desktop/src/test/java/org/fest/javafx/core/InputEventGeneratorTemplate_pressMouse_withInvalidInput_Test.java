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
import static javafx.scene.input.MouseButton.PRIMARY;
import static org.fest.javafx.test.builder.Buttons.button;
import static org.fest.test.ExpectedFailure.expect;
import javafx.scene.Node;
import javafx.scene.control.Button;
import org.fest.javafx.util.Point;
import org.fest.test.CodeToTest;
import org.junit.*;

/**
 * Tests for <code>{@link InputEventGeneratorTemplate#pressMouse(javafx.scene.input.MouseButton, Node, Point)}</code>.
 *
 * @author Alex Ruiz
 */
public class InputEventGeneratorTemplate_pressMouse_withInvalidInput_Test {

  private static Button button;
  private static Point where;

  private InputEventGeneratorTemplate inputEventGenerator;

  @BeforeClass
  public static void setUpOnce() {
    button = button().createNew();
    where = new Point(0, 0);
  }

  @Before
  public void setUp() {
    inputEventGenerator = new TestInputEventGeneratorTemplate();
  }

  @Test
  public void should_throw_error_if_MouseButton_is_null() {
    expect(NullPointerException.class).withMessage("The MouseButton to press should not be null").on(new CodeToTest() {
      @Override public void run() {
        inputEventGenerator.pressMouse(null, button, where);
      }
    });
  }

  @Test
  public void should_throw_error_if_MouseButton_is_NONE() {
    String msg = "The MouseButton to press should not be NONE";
    expect(IllegalArgumentException.class).withMessage(msg).on(new CodeToTest() {
      @Override public void run() {
        inputEventGenerator.pressMouse(NONE, button, where);
      }
    });
  }

  @Test
  public void should_throw_error_if_Node_is_null() {
    expect(NullPointerException.class).withMessage("The target Node should not be null").on(new CodeToTest() {
      @Override public void run() {
        inputEventGenerator.pressMouse(PRIMARY, null, new Point(0, 0));
      }
    });
  }

  @Test
  public void should_throw_error_if_Point_is_null() {
    String msg = "The Point where to move the mouse pointer to should not be null";
    expect(NullPointerException.class).withMessage(msg).on(new CodeToTest() {
      @Override public void run() {
        inputEventGenerator.pressMouse(PRIMARY, button, null);
      }
    });
  }
}
