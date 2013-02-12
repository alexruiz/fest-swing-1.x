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

import static java.awt.BorderLayout.NORTH;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JToolBar;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@code JToolBar}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJToolBarLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJToolBarByType() {
    JToolBarFixture toolBar = fixture.toolBar();
    assertThatFixtureHasCorrectJToolBar(toolBar);
  }

  @Test
  public void shouldFailIfJToolBarCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.toolBar);
      }
    });
    robot.waitForIdle();
    try {
      fixture.toolBar();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JToolBar, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJToolBarByName() {
    JToolBarFixture toolBar = fixture.toolBar("myToolBar");
    assertThatFixtureHasCorrectJToolBar(toolBar);
  }

  @Test
  public void shouldFailIfJToolBarCannotBeFoundByName() {
    try {
      fixture.toolBar("myButton");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='myButton', type=javax.swing.JToolBar, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJToolBarWithCustomMatcher() {
    JToolBarFixture toolBar = fixture.toolBar(new GenericTypeMatcher<JToolBar>(JToolBar.class) {
      @Override
      protected boolean isMatching(JToolBar t) {
        return "myToolBar".equals(t.getName());
      }
    });
    assertThatFixtureHasCorrectJToolBar(toolBar);
  }

  private void assertThatFixtureHasCorrectJToolBar(JToolBarFixture toolBarFixture) {
    assertThat(toolBarFixture.target()).isSameAs(window.toolBar);
  }

  @Test
  public void shouldFailIfJToolBarCannotBeFoundWithCustomMatcher() {
    try {
      fixture.toolBar(new GenericTypeMatcher<JToolBar>(JToolBar.class) {
        @Override
        protected boolean isMatching(JToolBar t) {
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

    final JToolBar toolBar = new JToolBar();

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJToolBarLookupTest.class);
      toolBar.setName("myToolBar");
      setLayout(new BorderLayout());
      toolBar.add(new JLabel("Hello"));
      add(toolBar, NORTH);
    }
  }
}
