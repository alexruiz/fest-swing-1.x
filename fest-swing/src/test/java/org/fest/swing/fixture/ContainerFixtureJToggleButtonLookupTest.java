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
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.JToggleButton;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@link JToggleButton}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJToggleButtonLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJToggleButtonByType() {
    JToggleButtonFixture toggleButton = fixture.toggleButton();
    assertThatFixtureHasCorrectJToggleButton(toggleButton);
  }

  @Test
  public void shouldFailIfJToggleButtonCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.toggleButton);
      }
    });
    robot.waitForIdle();
    try {
      fixture.toggleButton();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JToggleButton, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJToggleButtonByName() {
    JToggleButtonFixture toggleButton = fixture.toggleButton("clickMeButton");
    assertThatFixtureHasCorrectJToggleButton(toggleButton);
  }

  @Test
  public void shouldFailIfJToggleButtonCannotBeFoundByName() {
    try {
      fixture.toggleButton("myButton");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='myButton', type=javax.swing.JToggleButton, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJToggleButtonWithCustomMatcher() {
    JToggleButtonFixture toggleButton = fixture
        .toggleButton(new GenericTypeMatcher<JToggleButton>(JToggleButton.class) {
          @Override
          protected boolean isMatching(JToggleButton b) {
            return "Click Me".equals(b.getText());
          }
        });
    assertThatFixtureHasCorrectJToggleButton(toggleButton);
  }

  private void assertThatFixtureHasCorrectJToggleButton(JToggleButtonFixture toggleButtonFixture) {
    assertThat(toggleButtonFixture.target()).isSameAs(window.toggleButton);
  }

  @Test
  public void shouldFailIfJToggleButtonCannotBeFoundWithCustomMatcher() {
    try {
      fixture.toggleButton(new GenericTypeMatcher<JToggleButton>(JToggleButton.class) {
        @Override
        protected boolean isMatching(JToggleButton b) {
          return false;
        }
      });
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher");
    }
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final JToggleButton toggleButton = new JToggleButton("Click Me");

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJToggleButtonLookupTest.class);
      toggleButton.setName("clickMeButton");
      addComponents(toggleButton);
    }
  }
}
