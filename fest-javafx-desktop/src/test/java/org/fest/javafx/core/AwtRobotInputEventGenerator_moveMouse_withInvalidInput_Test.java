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
import javafx.scene.control.Control;

import org.fest.javafx.util.Point;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link AwtRobotInputEventGenerator#moveMouse(Control, Point)}</code>.
 *
 * @author Alex Ruiz
 */
public class AwtRobotInputEventGenerator_moveMouse_withInvalidInput_Test {

  private AwtRobotInputEventGenerator inputEventGenerator;

  @Before
  public void setUp() {
    inputEventGenerator = new AwtRobotInputEventGenerator();
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_Control_is_null() {
    inputEventGenerator.moveMouse(null, new Point(0, 0));
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_Point_is_null() {
    inputEventGenerator.moveMouse(button().createNew(), null);
  }
}
