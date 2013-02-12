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

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@code JSpinner}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJSpinnerLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJSpinnerByType() {
    JSpinnerFixture spinner = fixture.spinner();
    assertThatFixtureHasCorrectJSpinner(spinner);
  }

  @Test
  public void shouldFailIfJSpinnerCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.spinner);
      }
    });
    robot.waitForIdle();
    try {
      fixture.spinner();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JSpinner, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJSpinnerByName() {
    JSpinnerFixture spinner = fixture.spinner("spinMeSpinner");
    assertThatFixtureHasCorrectJSpinner(spinner);
  }

  @Test
  public void shouldFailIfJSpinnerCannotBeFoundByName() {
    try {
      fixture.spinner("mySpinner");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='mySpinner', type=javax.swing.JSpinner, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJSpinnerWithCustomMatcher() {
    JSpinnerFixture spinner = fixture.spinner(new GenericTypeMatcher<JSpinner>(JSpinner.class) {
      @Override
      protected boolean isMatching(JSpinner s) {
        return s.getValue().equals(8);
      }
    });
    assertThatFixtureHasCorrectJSpinner(spinner);
  }

  private void assertThatFixtureHasCorrectJSpinner(JSpinnerFixture spinnerFixture) {
    assertThat(spinnerFixture.target()).isSameAs(window.spinner);
  }

  @Test
  public void shouldFailIfJSpinnerCannotBeFoundWithCustomMatcher() {
    try {
      fixture.spinner(new GenericTypeMatcher<JSpinner>(JSpinner.class) {
        @Override
        protected boolean isMatching(JSpinner s) {
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

    final JSpinner spinner = new JSpinner(new SpinnerNumberModel(8, 6, 10, 1));

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJSpinnerLookupTest.class);
      spinner.setName("spinMeSpinner");
      addComponents(spinner);
    }
  }
}
