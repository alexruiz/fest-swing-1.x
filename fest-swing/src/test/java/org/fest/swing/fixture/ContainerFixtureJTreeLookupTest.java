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

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests lookup of {@code JTree}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJTreeLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew();
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJTreeByType() {
    JTreeFixture tree = fixture.tree();
    assertThatFixtureHasCorrectJTree(tree);
  }

  @Test
  public void shouldFailIfJTreeCannotBeFoundByType() {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        window.remove(window.tree);
      }
    });
    robot.waitForIdle();
    try {
      fixture.tree();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JTree, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJTreeByName() {
    JTreeFixture tree = fixture.tree("expandMeTree");
    assertThatFixtureHasCorrectJTree(tree);
  }

  @Test
  public void shouldFailIfJTreeCannotBeFoundByName() {
    try {
      fixture.tree("myTree");
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "name='myTree', type=javax.swing.JTree, requireShowing=true");
    }
  }

  @Test
  public void shouldFindJTreeWithCustomMatcher() {
    JTreeFixture tree = fixture.tree(new GenericTypeMatcher<JTree>(JTree.class) {
      @Override
      protected boolean isMatching(JTree t) {
        return "expandMeTree".equals(t.getName());
      }
    });
    assertThatFixtureHasCorrectJTree(tree);
  }

  private void assertThatFixtureHasCorrectJTree(JTreeFixture treeFixture) {
    assertThat(treeFixture.target()).isSameAs(window.tree);
  }

  @Test
  public void shouldFailIfJTreeCannotBeFoundWithCustomMatcher() {
    try {
      fixture.tree(new GenericTypeMatcher<JTree>(JTree.class) {
        @Override
        protected boolean isMatching(JTree t) {
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

    final JTree tree = new JTree(new DefaultMutableTreeNode("root"));

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerFixtureJTreeLookupTest.class);
      tree.setName("expandMeTree");
      addComponents(tree);
    }
  }
}
