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

import static java.awt.Color.RED;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import java.awt.Container;

import javax.swing.JPanel;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@link JPanel}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJPanelLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJPanelByType() {
    JPanelFixture panel = fixture.panel();
    assertThatFixtureHasCorrectJPanel(panel);
  }

  @Test
  public void shouldFailIfJPanelCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.setContentPane(new Container());
      }
    });
    robot.waitForIdle();
    try {
      fixture.panel();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JPanel, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJPanelByName() {
    JPanelFixture panel = fixture.panel("myPanel");
    assertThatFixtureHasCorrectJPanel(panel);
  }

  @Test
  public void shouldFailIfJPanelCannotBeFoundByName() {
    try {
      fixture.panel("somePanel");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='somePanel', type=javax.swing.JPanel, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJPanelWithCustomMatcher() {
    JPanelFixture panel = fixture.panel(new GenericTypeMatcher<JPanel>(JPanel.class) {
      @Override
      protected boolean isMatching(JPanel p) {
        return RED.equals(p.getBackground());
      }
    });
    assertThatFixtureHasCorrectJPanel(panel);
  }

  private void assertThatFixtureHasCorrectJPanel(JPanelFixture panelFixture) {
    assertThat(panelFixture.target()).isSameAs(window.panel);
  }

  @Test
  public void shouldFailIfJPanelCannotBeFoundWithCustomMatcher() {
    try {
      fixture.panel(new GenericTypeMatcher<JPanel>(JPanel.class) {
        @Override
        protected boolean isMatching(JPanel p) {
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

    final JPanel panel = new JPanel();

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJPanelLookupTest.class);
      panel.setName("myPanel");
      panel.setBackground(RED);
      setContentPane(panel);
    }
  }
}
