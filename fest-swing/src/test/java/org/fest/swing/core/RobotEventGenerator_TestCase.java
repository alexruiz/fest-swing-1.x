/*
 * Created on Dec 29, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.core;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;

import java.awt.Robot;
import java.lang.reflect.Method;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.util.RobotFactory;
import org.junit.Before;

/**
 * Base test case for <code>{@link RobotEventGenerator}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class RobotEventGenerator_TestCase {

  Robot robot;
  RobotEventGenerator eventGenerator;

  @Before public final void setUp() throws Exception {
    final RobotFactory robotFactory = createMock(RobotFactory.class);
    robot = createMock(Robot.class, methodsToMockInRobot());
    new EasyMockTemplate(robotFactory) {
      protected void expectations() throws Throwable {
        expect(robotFactory.newRobotInPrimaryScreen()).andReturn(robot);
      }

      protected void codeToTest() {
        eventGenerator = new RobotEventGenerator(robotFactory, new Settings());
      }
    }.run();
  }
  
  abstract Method[] methodsToMockInRobot() throws Exception;

  final Method methodFromAWTRobot(String name, Class<?>...parameterTypes) throws Exception {
    return Robot.class.getDeclaredMethod(name, parameterTypes);
  }  
}
