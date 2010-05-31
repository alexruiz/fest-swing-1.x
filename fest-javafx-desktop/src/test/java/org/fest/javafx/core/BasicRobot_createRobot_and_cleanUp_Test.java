/*
 * Created on May 30, 2010
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

import org.junit.*;

/**
 * Tests for <code>{@link BasicRobot#createRobot()}</code> and <code>{@link BasicRobot#cleanUp()}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicRobot_createRobot_and_cleanUp_Test {

  private BasicRobot robot;

  @Before
  public void setUp() {
    Robot r = BasicRobot.createRobot();
    assertThat(r).isInstanceOf(BasicRobot.class);
    robot = (BasicRobot) r;
  }

  @After
  public void tearDown() {
    robot.cleanUp();
    assertThat(robot.ownsScreenLock()).isFalse();
  }

  @Test
  public void should_have_acquired_ScreenLock() {
    assertThat(robot.ownsScreenLock()).isTrue();
  }
}
