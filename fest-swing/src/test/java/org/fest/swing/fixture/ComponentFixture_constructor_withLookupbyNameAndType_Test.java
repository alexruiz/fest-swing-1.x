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

import static org.fest.swing.core.TestComponentFinders.newComponentFinderMock;

import org.fest.swing.core.ComponentFinder;
import org.fest.swing.core.Robot;
import org.junit.Test;

/**
 * Tests for {@link AbstractComponentFixture#ComponentFixture(Robot, String, Class)}.
 * 
 * @author Alex Ruiz
 */
public class ComponentFixture_constructor_withLookupbyNameAndType_Test extends ComponentFixture_constructor_TestCase {
  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_Robot_is_null() {
    new ConcreteComponentFixture(null, name, type);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_class_is_null() {
    new ConcreteComponentFixture(robot, name, null);
  }

  @Test
  public void should_lookup_Component_by_name_and_type() {
    final ComponentFinder finder = newComponentFinderMock();
    new EasyMockTemplate(robot, finder) {
      @Override
      protected void expectations() {
        expect(robot.settings()).andReturn(settings);
        expect(robot.finder()).andReturn(finder);
        expect(finder.findByName(name, type, requireShowing())).andReturn(target);
      }

      @Override
      protected void codeToTest() {
        assertHasCorrectTarget(new ConcreteComponentFixture(robot, name, type));
      }
    }.run();
  }
}
