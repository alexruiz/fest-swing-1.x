/*
 * Created on Jun 11, 2009
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
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Dimension;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.hierarchy.ExistingHierarchy;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Before;

/**
 * Base test case for {@link AbstractWindowFixture}.
 * 
 * @author Alex Ruiz
 */
public abstract class WindowFixture_TestCase {
  MyWindow window;

  @Before
  public final void setUp() {
    window = MyWindow.createNew(getClass());
    extraSetUp();
  }

  void extraSetUp() {}

  static void assertThatContainsExistingHierarchy(AbstractWindowFixture<?> f) {
    assertThat(f.robot.hierarchy()).isInstanceOf(ExistingHierarchy.class);
  }

  static void assertThatFixtureUsesRobot(AbstractWindowFixture<?> f, Robot r) {
    assertThat(f.robot).isSameAs(r);
  }

  static void assertThatFixtureHandlesWindow(AbstractWindowFixture<?> f, MyWindow w) {
    assertThat(f.target()).isSameAs(w);
  }

  static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    @RunsInEDT
    static MyWindow createNew(final Class<?> testClass) {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow(testClass);
        }
      });
    }

    private MyWindow(Class<?> testClass) {
      super(testClass);
      setName("MyWindow");
      setPreferredSize(new Dimension(80, 60));
    }
  }
}
