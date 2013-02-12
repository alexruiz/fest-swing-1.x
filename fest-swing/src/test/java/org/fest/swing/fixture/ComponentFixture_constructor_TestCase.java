/*
 * Created on Jun 14, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.TestRobots.newRobotMock;
import static org.fest.swing.test.builder.JTextFields.textField;

import javax.swing.JTextField;

import org.fest.swing.core.Robot;
import org.fest.swing.core.Settings;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;

/**
 * Test cases for constructors in {@link AbstractComponentFixture}.
 * 
 * @author Alex Ruiz
 */
public abstract class ComponentFixture_constructor_TestCase extends EDTSafeTestCase {
  Robot robot;
  Settings settings;
  Class<JTextField> type;
  String name;
  JTextField target;

  @Before
  public final void setUp() {
    robot = newRobotMock();
    settings = new Settings();
    type = JTextField.class;
    name = "textBox";
    target = textField().createNew();
  }

  final boolean requireShowing() {
    return settings.componentLookupScope().requireShowing();
  }

  final void assertHasCorrectTarget(ConcreteComponentFixture fixture) {
    assertThat(fixture.target).isSameAs(target);
  }
}
