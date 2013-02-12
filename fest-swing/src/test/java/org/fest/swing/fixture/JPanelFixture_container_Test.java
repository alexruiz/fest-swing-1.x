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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.builder.JPanels.panel;

import javax.swing.JPanel;

import org.fest.swing.driver.JComponentDriver;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests that {@link JPanelFixture} is a {@link AbstractContainerFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JPanelFixture_container_Test extends ComponentFixture_Implementations_TestCase<JPanel> {
  private static JPanel target;

  private JComponentDriver driver;
  private JPanelFixture fixture;

  @BeforeClass
  public static void setUpTarget() {
    target = panel().createNew();
  }

  @Override
  void onSetUp() {
    driver = createMock(JComponentDriver.class);
    fixture = new JPanelFixture(robot(), target);
    fixture.driver(driver);
  }

  @Test
  public void should_be_ContainerFixture() {
    assertThat(fixture).isInstanceOf(AbstractContainerFixture.class);
  }

  @Override
  JComponentDriver driver() {
    return driver;
  }

  @Override
  JPanel target() {
    return target;
  }

  @Override
  JPanelFixture fixture() {
    return fixture;
  }
}
