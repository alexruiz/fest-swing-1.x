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

import javax.swing.JList;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@code JList}s in {@link AbstractContainerFixture}.
 *
 * @author Alex Ruiz
 */
public class ContainerFixtureJListLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJListByType() {
    JListFixture list = fixture.list();
    assertThatFixtureHasCorrectJList(list);
  }

  @Test
  public void shouldFailIfJListCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.list);
      }
    });
    robot.waitForIdle();
    try {
      fixture.list();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JList, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJListByName() {
    JListFixture list = fixture.list("selectMeList");
    assertThatFixtureHasCorrectJList(list);
  }

  @Test
  public void shouldFailIfJListCannotBeFoundByName() {
    try {
      fixture.list("myList");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='myList', type=javax.swing.JList, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJListWithCustomMatcher() {
    JListFixture list = fixture.list(new GenericTypeMatcher<JList>(JList.class) {
      @Override
      protected boolean isMatching(JList l) {
        return l.getModel().getSize() == 3;
      }
    });
    assertThatFixtureHasCorrectJList(list);
  }

  private void assertThatFixtureHasCorrectJList(JListFixture listFixture) {
    assertThat(listFixture.target()).isSameAs(window.list);
  }

  @Test
  public void shouldFailIfJListCannotBeFoundWithCustomMatcher() {
    try {
      fixture.list(new GenericTypeMatcher<JList>(JList.class) {
        @Override
        protected boolean isMatching(JList l) {
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

    final JList list = new JList(array("One", "Two", "Three"));

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJListLookupTest.class);
      list.setName("selectMeList");
      addComponents(list);
    }
  }
}
