/*
 * Created on Aug 6, 2009
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.core;

import static java.awt.event.InputEvent.BUTTON1_MASK;
import static org.fest.util.Arrays.array;

import java.awt.Point;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * Tests for {@link RobotEventGenerator#pressMouse(java.awt.Component, java.awt.Point, int)}.
 * 
 * @author Alex Ruiz
 */
public class RobotEventGenerator_pressMouse_withNullComponent_Test extends RobotEventGenerator_TestCase {
  @Override
  Method[] methodsToMockInRobot() throws Exception {
    return array(methodFromAWTRobot("mouseMove", int.class, int.class), methodFromAWTRobot("mousePress", int.class));
  }

  @Test
  public void should_press_mouse_at_given_coordinates_if_Component_is_null() {
    final Point where = new Point(6, 8);
    final int mouseButtons = BUTTON1_MASK;
    new EasyMockTemplate(robot) {
      @Override
      protected void expectations() {
        robot.mouseMove(where.x, where.y);
        expectLastCall().once();
        robot.mousePress(mouseButtons);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        eventGenerator.pressMouse(null, where, mouseButtons);
      }
    }.run();
  }
}
