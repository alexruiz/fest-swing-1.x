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

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@code JTabbedPane}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJTabbedPaneLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJTabbedPaneByType() {
    JTabbedPaneFixture tabbedPane = fixture.tabbedPane();
    assertThatFixtureHasCorrectJTabbedPane(tabbedPane);
  }

  @Test
  public void shouldFailIfJTabbedPaneCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.tabbedPane);
      }
    });
    robot.waitForIdle();
    try {
      fixture.tabbedPane();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JTabbedPane, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJTabbedPaneByName() {
    JTabbedPaneFixture tabbedPane = fixture.tabbedPane("selectMeTabbedPane");
    assertThatFixtureHasCorrectJTabbedPane(tabbedPane);
  }

  @Test
  public void shouldFailIfJTabbedPaneCannotBeFoundByName() {
    try {
      fixture.tabbedPane("myTabbedPane");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='myTabbedPane', type=javax.swing.JTabbedPane, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJTabbedPaneWithCustomMatcher() {
    JTabbedPaneFixture tabbedPane = fixture.tabbedPane(new GenericTypeMatcher<JTabbedPane>(JTabbedPane.class) {
      @Override
      protected boolean isMatching(JTabbedPane t) {
        return t.getTabCount() == 1;
      }
    });
    assertThatFixtureHasCorrectJTabbedPane(tabbedPane);
  }

  private void assertThatFixtureHasCorrectJTabbedPane(JTabbedPaneFixture tabbedPaneFixture) {
    assertThat(tabbedPaneFixture.target()).isSameAs(window.tabbedPane);
  }

  @Test
  public void shouldFailIfJTabbedPaneCannotBeFoundWithCustomMatcher() {
    try {
      fixture.tabbedPane(new GenericTypeMatcher<JTabbedPane>(JTabbedPane.class) {
        @Override
        protected boolean isMatching(JTabbedPane t) {
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

    final JTabbedPane tabbedPane = new JTabbedPane();

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJTabbedPaneLookupTest.class);
      tabbedPane.setName("selectMeTabbedPane");
      tabbedPane.addTab("Tab 0", panel());
      addComponents(tabbedPane);
    }

    private JPanel panel() {
      JPanel panel = new JPanel();
      panel.setPreferredSize(new Dimension(100, 50));
      return panel;
    }
  }
}
