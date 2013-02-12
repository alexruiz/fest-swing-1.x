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

import java.awt.Component;

import javax.swing.JTextField;

import org.fest.swing.core.Robot;
import org.junit.Test;

/**
 * Tests for {@link AbstractComponentFixture#ComponentFixture(Robot, Component)}.
 * 
 * @author Alex Ruiz
 */
public class ComponentFixture_constructor_withTargetComponent_Test extends ComponentFixture_constructor_TestCase {
  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_Robot_is_nullWhenPassingTarget() {
    new ConcreteComponentFixture(null, target);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_target_Component_is_null() {
    new ConcreteComponentFixture(robot, (JTextField) null);
  }
}
