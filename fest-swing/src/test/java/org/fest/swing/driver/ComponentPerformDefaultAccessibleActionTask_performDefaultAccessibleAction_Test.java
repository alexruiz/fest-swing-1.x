/*
 * Created on Feb 23, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import java.awt.Component;
import java.util.Locale;

import javax.accessibility.*;
import javax.swing.JTextField;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link ComponentPerformDefaultAccessibleActionTask#performDefaultAccessibleAction(Component)}</code>.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ComponentPerformDefaultAccessibleActionTask_performDefaultAccessibleAction_Test extends RobotBasedTestCase {

  private AccessibleAction accessibleAction;
  private AccessibleContextStub accessibleContext;
  private Component component;

  @Override protected void onSetUp() {
    accessibleAction = createMock(AccessibleAction.class);
    accessibleContext = new AccessibleContextStub(accessibleAction);
    MyWindow window = MyWindow.createNew(accessibleContext);
    component = window.component;
  }

  @Test
  public void should_execute_first_Action_in_AccessibleAction() {
    new EasyMockTemplate(accessibleAction) {
      protected void expectations() {
        expect(accessibleAction.getAccessibleActionCount()).andReturn(1);
        expect(accessibleAction.doAccessibleAction(0)).andReturn(true);
      }

      protected void codeToTest() {
        ComponentPerformDefaultAccessibleActionTask.performDefaultAccessibleAction(component);
        robot.waitForIdle();
      }
    }.run();
  }

  @Test
  public void should_throw_error_if_AccessibleAction_is_null() {
    accessibleContext.accessibleAction(null);
    try {
      new EasyMockTemplate(accessibleAction) {
        protected void expectations() {
        }

        protected void codeToTest() {
          ComponentPerformDefaultAccessibleActionTask.performDefaultAccessibleAction(component);
          robot.waitForIdle();
        }
      }.run();
      failWhenExpectingException();
    } catch (ActionFailedException e) {
      assertActionFailedThrown(e);
    }
  }

  @Test
  public void should_throw_error_if_AccessibleAction_is_empty() {
    try {
      new EasyMockTemplate(accessibleAction) {
        protected void expectations() {
          expect(accessibleAction.getAccessibleActionCount()).andReturn(0);
        }

        protected void codeToTest() {
          ComponentPerformDefaultAccessibleActionTask.performDefaultAccessibleAction(component);
          robot.waitForIdle();
        }
      }.run();
      failWhenExpectingException();
    } catch (ActionFailedException e) {
      assertActionFailedThrown(e);
    }
  }

  private void assertActionFailedThrown(ActionFailedException e) {
    assertThat(e.getMessage()).contains("Unable to perform accessible action for");
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final MyComponent component;

    @RunsInEDT
    static MyWindow createNew(final AccessibleContext accessibleContext) {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow(accessibleContext);
        }
      });
    }

    private MyWindow(AccessibleContext accessibleContext) {
      super(ComponentPerformDefaultAccessibleActionTask_performDefaultAccessibleAction_Test.class);
      component = new MyComponent(accessibleContext);
      addComponents(component);
    }
  }

  private static class MyComponent extends JTextField {
    private static final long serialVersionUID = 1L;

    private final AccessibleContext accessibleContext;

    MyComponent(AccessibleContext accessibleContext) {
      super(20);
      this.accessibleContext = accessibleContext;
    }

    @Override
    public AccessibleContext getAccessibleContext() {
      return accessibleContext;
    }
  }

  private static class AccessibleContextStub extends AccessibleContext {
    private AccessibleAction accessibleAction;

    AccessibleContextStub(AccessibleAction newAccessibleAction) {
      accessibleAction(newAccessibleAction);
    }

    void accessibleAction(AccessibleAction newAccessibleAction) {
      this.accessibleAction = newAccessibleAction;
    }

    @Override
    public AccessibleAction getAccessibleAction() {
      return accessibleAction;
    }

    public Accessible getAccessibleChild(int i) {
      return null;
    }

    public int getAccessibleChildrenCount() {
      return 0;
    }

    public int getAccessibleIndexInParent() {
      return 0;
    }

    public AccessibleRole getAccessibleRole() {
      return null;
    }

    public AccessibleStateSet getAccessibleStateSet() {
      return null;
    }

    public Locale getLocale() {
      return null;
    }
  }
}
