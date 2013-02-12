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

import static org.fest.swing.test.builder.JButtons.button;

import java.awt.Component;

import org.fest.swing.driver.ComponentDriver;
import org.junit.BeforeClass;

/**
 * Tests for methods in {@link GenericComponentFixture} that inherited from {@link MouseInputSimulationFixture}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class GenericComponentFixture_mouseInput_Test extends MouseInputSimulationFixture_TestCase<Component> {
  private static Component target;

  private GenericComponentFixture<Component> fixture;
  private ComponentDriver driver;

  @BeforeClass
  public static void setUpTarget() {
    target = button().createNew();
  }

  @Override
  void onSetUp() {
    driver = createMock(ComponentDriver.class);
    fixture = new GenericComponentFixture<Component>(robot(), driver, target) {
    };
  }

  @Override
  ComponentDriver driver() {
    return driver;
  }

  @Override
  Component target() {
    return target;
  }

  @Override
  CommonComponentFixture fixture() {
    return fixture;
  }
}
