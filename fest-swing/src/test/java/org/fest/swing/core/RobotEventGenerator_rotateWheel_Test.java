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

import java.lang.reflect.Method;

import org.fest.util.Arrays;
import org.junit.Test;

/**
 * Tests for {@link RobotEventGenerator#rotateMouseWheel(int)}.
 * 
 * @author Alex Ruiz
 */
public class RobotEventGenerator_rotateWheel_Test extends RobotEventGenerator_TestCase {
  @Override
  Method[] methodsToMockInRobot() throws Exception {
    return Arrays.array(methodFromAWTRobot("mouseWheel", int.class));
  }

  @Test
  public void should_rotate_mouse_wheel() {
    final int amount = 8;
    new EasyMockTemplate(robot) {
      @Override
      protected void expectations() {
        robot.mouseWheel(amount);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        eventGenerator.rotateMouseWheel(amount);
      }
    }.run();
  }
}
