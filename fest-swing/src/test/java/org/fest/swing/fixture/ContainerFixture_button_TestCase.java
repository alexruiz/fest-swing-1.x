/*
 * Created on Jun 7, 2009
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

import javax.swing.JButton;

import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;

/**
 * Base test case for lookup of {@link JButton}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public abstract class ContainerFixture_button_TestCase extends RobotBasedTestCase {
  ConcreteContainerFixture fixture;
  MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew(getClass());
    fixture = new ConcreteContainerFixture(robot, window);
  }

  final void showWindow() {
    robot.showWindow(window);
  }

  final void assertThatJButtonWasFound(JButtonFixture f) {
    assertThat(f.target()).isSameAs(window.button);
  }

  static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final JButton button = new JButton("Click Me");

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
      button.setName("clickMeButton");
      addComponents(button);
    }
  }
}
