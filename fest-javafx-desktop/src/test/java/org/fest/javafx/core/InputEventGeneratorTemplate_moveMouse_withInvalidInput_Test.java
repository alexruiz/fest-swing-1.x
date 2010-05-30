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

import static org.fest.javafx.test.builder.Buttons.button;
import static org.fest.test.ExpectedFailure.expect;
import javafx.scene.Node;

import org.fest.javafx.util.Point;
import org.fest.test.CodeToTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link InputEventGeneratorTemplate#moveMouse(Node, Point)}</code>.
 *
 * @author Alex Ruiz
 */
public class InputEventGeneratorTemplate_moveMouse_withInvalidInput_Test {

  private InputEventGeneratorTemplate inputEventGenerator;

  @Before
  public void setUp() {
    inputEventGenerator = new TestInputEventGeneratorTemplate();
  }

  @Test
  public void should_throw_error_if_Node_is_null() {
    expect(NullPointerException.class).withMessage("The target Node should not be null").on(new CodeToTest() {
      @Override public void run() {
        inputEventGenerator.moveMouse(null, new Point(0, 0));
      }
    });
  }

  @Test
  public void should_throw_error_if_Point_is_null() {
    String msg = "The Point where to move the mouse pointer to should not be null";
    expect(NullPointerException.class).withMessage(msg).on(new CodeToTest() {
      @Override public void run() {
        inputEventGenerator.moveMouse(button().createNew(), null);
      }
    });
  }
}
