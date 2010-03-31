/*
 * Created on Jul 13, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.Mocks.mockRobot;

import java.awt.Component;

import org.fest.swing.core.Robot;
import org.fest.swing.driver.ComponentDriver;
import org.junit.Before;

/**
 * Understands test methods for implementations of <code>{@link ComponentFixture}</code>.
 * @param <T> the type of component tested by this test class.
 *
 * @author Alex Ruiz
 */
public abstract class ComponentFixture_TestCase<T extends Component> {

  private Robot robot;

  @Before public final void setUp() {
    robot = mockRobot();
    onSetUp();
  }

  abstract void onSetUp();

  final void assertThatReturnsSelf(Object result) {
    assertThat(result).isSameAs(fixture());
  }

  abstract Object fixture();

  abstract T target();

  abstract ComponentDriver driver();

  final Robot robot() { return robot; }
}