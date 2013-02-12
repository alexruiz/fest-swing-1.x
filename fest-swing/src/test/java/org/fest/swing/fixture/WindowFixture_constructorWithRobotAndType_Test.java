/*
 * Created on Aug 2, 2009
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
package org.fest.swing.fixture;

import org.fest.swing.core.BasicRobot;
import org.fest.swing.core.Robot;
import org.junit.After;
import org.junit.Test;

/**
 * Tests for {@link AbstractWindowFixture#WindowFixture(org.fest.swing.core.Robot, Class)}.
 * 
 * @author Alex Ruiz
 */
public class WindowFixture_constructorWithRobotAndType_Test extends WindowFixture_TestCase {
  private Robot robot;

  @Override
  void extraSetUp() {
    robot = BasicRobot.robotWithCurrentAwtHierarchy();
    window.display();
  }

  @After
  public void tearDown() {
    if (robot != null) {
      robot.cleanUp();
    }
  }

  @Test
  public void should_create_WindowFixture_with_given_Robot_and_type() {
    ConcreteWindowFixture fixture = new ConcreteWindowFixture(robot, MyWindow.class);
    assertThatFixtureUsesRobot(fixture, robot);
    assertThatFixtureHandlesWindow(fixture, window);
  }
}
