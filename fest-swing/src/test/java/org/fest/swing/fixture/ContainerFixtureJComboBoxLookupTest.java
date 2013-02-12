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
import static org.fest.util.Arrays.array;

import javax.swing.JComboBox;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@code JComboBox}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJComboBoxLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJComboBoxByType() {
    JComboBoxFixture comboBox = fixture.comboBox();
    assertThatFixtureHasCorrectJComboBox(comboBox);
  }

  @Test
  public void shouldFailIfJComboBoxCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.comboBox);
      }
    });
    robot.waitForIdle();
    try {
      fixture.comboBox();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JComboBox, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJComboBoxByName() {
    JComboBoxFixture comboBox = fixture.comboBox("selectMeComboBox");
    assertThatFixtureHasCorrectJComboBox(comboBox);
  }

  @Test
  public void shouldFailIfJComboBoxCannotBeFoundByName() {
    try {
      fixture.comboBox("myComboBox");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='myComboBox', type=javax.swing.JComboBox, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJComboBoxWithCustomMatcher() {
    JComboBoxFixture comboBox = fixture.comboBox(new GenericTypeMatcher<JComboBox>(JComboBox.class) {
      @Override
      protected boolean isMatching(JComboBox c) {
        return c.getItemCount() == 3;
      }
    });
    assertThatFixtureHasCorrectJComboBox(comboBox);
  }

  private void assertThatFixtureHasCorrectJComboBox(JComboBoxFixture comboBoxFixture) {
    assertThat(comboBoxFixture.target()).isSameAs(window.comboBox);
  }

  @Test
  public void shouldFailIfJComboBoxCannotBeFoundWithCustomMatcher() {
    try {
      fixture.comboBox(new GenericTypeMatcher<JComboBox>(JComboBox.class) {
        @Override
        protected boolean isMatching(JComboBox c) {
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

    final JComboBox comboBox = new JComboBox(array("One", "Two", "Three"));

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJComboBoxLookupTest.class);
      comboBox.setName("selectMeComboBox");
      addComponents(comboBox);
    }
  }
}
