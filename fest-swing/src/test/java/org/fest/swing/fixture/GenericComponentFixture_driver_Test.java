/*
 * Created on Nov 17, 2009
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
import static org.fest.swing.core.TestRobots.singletonRobotMock;
import static org.fest.swing.test.builder.JButtons.button;

import javax.swing.JButton;

import org.fest.swing.driver.ComponentDriver;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link GenericComponentFixture#driver(org.fest.swing.driver.ComponentDriver)} and
 * {@link GenericComponentFixture#driver()}.
 * 
 * @author Alex Ruiz
 */
public class GenericComponentFixture_driver_Test extends EDTSafeTestCase {
  private GenericComponentFixture<JButton> fixture;

  @Before
  public void setUp() {
    JButton target = button().createNew();
    fixture = new GenericComponentFixture<JButton>(singletonRobotMock(), target) {
    };
  }

  @Test
  public void should_create_ComponentDriver_by_default() {
    assertThat(fixture.driver()).isInstanceOf(ComponentDriver.class);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_driver_is_null() {
    fixture.driver(null);
  }
}
