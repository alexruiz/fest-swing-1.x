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

import static java.awt.Adjustable.VERTICAL;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.JScrollBar;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@code JScrollBar}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJScrollBarLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJScrollBarByType() {
    JScrollBarFixture scrollBar = fixture.scrollBar();
    assertThatFixtureHasCorrectJScrollBar(scrollBar);
  }

  @Test
  public void shouldFailIfJScrollBarCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.scrollBar);
      }
    });
    robot.waitForIdle();
    try {
      fixture.scrollBar();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JScrollBar, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJScrollBarByName() {
    JScrollBarFixture scrollBar = fixture.scrollBar("scrollMeScrollBar");
    assertThatFixtureHasCorrectJScrollBar(scrollBar);
  }

  @Test
  public void shouldFailIfJScrollBarCannotBeFoundByName() {
    try {
      fixture.scrollBar("myScrollBar");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='myScrollBar', type=javax.swing.JScrollBar, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJScrollBarWithCustomMatcher() {
    JScrollBarFixture scrollBar = fixture.scrollBar(new GenericTypeMatcher<JScrollBar>(JScrollBar.class) {
      @Override
      protected boolean isMatching(JScrollBar s) {
        return s.getOrientation() == VERTICAL && s.getValue() == 8;
      }
    });
    assertThatFixtureHasCorrectJScrollBar(scrollBar);
  }

  private void assertThatFixtureHasCorrectJScrollBar(JScrollBarFixture scrollBarFixture) {
    assertThat(scrollBarFixture.target()).isSameAs(window.scrollBar);
  }

  @Test
  public void shouldFailIfJScrollBarCannotBeFoundWithCustomMatcher() {
    try {
      fixture.scrollBar(new GenericTypeMatcher<JScrollBar>(JScrollBar.class) {
        @Override
        protected boolean isMatching(JScrollBar s) {
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

    final JScrollBar scrollBar = new JScrollBar(VERTICAL, 8, 1, 6, 10);

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJScrollBarLookupTest.class);
      scrollBar.setName("scrollMeScrollBar");
      addComponents(scrollBar);
    }
  }
}
