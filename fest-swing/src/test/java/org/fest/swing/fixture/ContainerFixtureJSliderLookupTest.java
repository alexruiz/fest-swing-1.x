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

import static javax.swing.SwingConstants.HORIZONTAL;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.JSlider;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@code JSlider}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJSliderLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJSliderByType() {
    JSliderFixture slider = fixture.slider();
    assertThatFixtureHasCorrectJSlider(slider);
  }

  @Test
  public void shouldFailIfJSliderCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.slider);
      }
    });
    robot.waitForIdle();
    try {
      fixture.slider();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JSlider, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJSliderByName() {
    JSliderFixture slider = fixture.slider("slideMeSlider");
    assertThatFixtureHasCorrectJSlider(slider);
  }

  @Test
  public void shouldFailIfJSliderCannotBeFoundByName() {
    try {
      fixture.slider("mySlider");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='mySlider', type=javax.swing.JSlider, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJSliderWithCustomMatcher() {
    JSliderFixture slider = fixture.slider(new GenericTypeMatcher<JSlider>(JSlider.class) {
      @Override
      protected boolean isMatching(JSlider s) {
        return s.getOrientation() == HORIZONTAL && s.getValue() == 8;
      }
    });
    assertThatFixtureHasCorrectJSlider(slider);
  }

  private void assertThatFixtureHasCorrectJSlider(JSliderFixture sliderFixture) {
    assertThat(sliderFixture.target()).isSameAs(window.slider);
  }

  @Test
  public void shouldFailIfJSliderCannotBeFoundWithCustomMatcher() {
    try {
      fixture.slider(new GenericTypeMatcher<JSlider>(JSlider.class) {
        @Override
        protected boolean isMatching(JSlider s) {
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

    final JSlider slider = new JSlider(HORIZONTAL, 6, 10, 8);

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJSliderLookupTest.class);
      slider.setName("slideMeSlider");
      addComponents(slider);
    }
  }
}
