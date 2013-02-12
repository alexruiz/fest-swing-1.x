/*
 * Created on Nov 19, 2009
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

import static org.fest.swing.test.builder.JLists.list;

import javax.swing.JList;

import org.fest.swing.driver.JListDriver;
import org.junit.BeforeClass;

/**
 * Tests for methods in {@link JListFixture} that are inherited from {@link MouseInputSimulationFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JListFixture_mouseInput_Test extends MouseInputSimulationFixture_TestCase<JList> {
  private static JList target;

  private JListDriver driver;
  private JListFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = list().createNew();
  }

  @Override
  void onSetUp() {
    driver = createMock(JListDriver.class);
    fixture = new JListFixture(robot(), target);
    fixture.driver(driver);
  }

  @Override
  JListDriver driver() {
    return driver;
  }

  @Override
  JList target() {
    return target;
  }

  @Override
  JListFixture fixture() {
    return fixture;
  }
}
