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

import static javax.swing.JSplitPane.VERTICAL_SPLIT;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import java.awt.Dimension;

import javax.swing.JList;
import javax.swing.JSplitPane;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@code JSplitPane}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJSplitPaneLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJSplitPaneByType() {
    JSplitPaneFixture splitPane = fixture.splitPane();
    assertThatFixtureHasCorrectJSplitPane(splitPane);
  }

  @Test
  public void shouldFailIfJSplitPaneCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.splitPane);
      }
    });
    robot.waitForIdle();
    try {
      fixture.splitPane();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JSplitPane, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJSplitPaneByName() {
    JSplitPaneFixture splitPane = fixture.splitPane("slideMeSplitPane");
    assertThatFixtureHasCorrectJSplitPane(splitPane);
  }

  @Test
  public void shouldFailIfJSplitPaneCannotBeFoundByName() {
    try {
      fixture.splitPane("mySplitPane");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='mySplitPane', type=javax.swing.JSplitPane, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJSplitPaneWithCustomMatcher() {
    JSplitPaneFixture splitPane = fixture.splitPane(new GenericTypeMatcher<JSplitPane>(JSplitPane.class) {
      @Override
      protected boolean isMatching(JSplitPane s) {
        return s.getLeftComponent() instanceof JList;
      }
    });
    assertThatFixtureHasCorrectJSplitPane(splitPane);
  }

  private void assertThatFixtureHasCorrectJSplitPane(JSplitPaneFixture splitPaneFixture) {
    assertThat(splitPaneFixture.target()).isSameAs(window.splitPane);
  }

  @Test
  public void shouldFailIfJSplitPaneCannotBeFoundWithCustomMatcher() {
    try {
      fixture.splitPane(new GenericTypeMatcher<JSplitPane>(JSplitPane.class) {
        @Override
        protected boolean isMatching(JSplitPane s) {
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

    final JSplitPane splitPane = new JSplitPane(VERTICAL_SPLIT);

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJSplitPaneLookupTest.class);
      splitPane.setName("slideMeSplitPane");
      splitPane.setLeftComponent(list("One", "Two"));
      splitPane.setRightComponent(list("Three"));
      addComponents(splitPane);
    }

    private static JList list(Object... elements) {
      JList list = new JList(elements);
      list.setPreferredSize(new Dimension(100, 50));
      return list;
    }
  }
}
