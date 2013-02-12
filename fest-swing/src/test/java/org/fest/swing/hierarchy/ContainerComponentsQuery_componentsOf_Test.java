/*
 * Created on Aug 26, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.swing.hierarchy;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Component;
import java.util.List;

import javax.swing.JButton;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.SequentialEDTSafeTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for {@link ContainerComponentsQuery#componentsOf(java.awt.Container)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ContainerComponentsQuery_componentsOf_Test extends SequentialEDTSafeTestCase {
  private MyWindow window;

  @Override
  protected final void onSetUp() {
    window = MyWindow.createNew();
  }

  @Override
  protected final void onTearDown() {
    window.destroy();
  }

  @Test
  public void should_return_Components_of_Container() {
    assertThat(componentsOf(window)).containsOnly(window.button);
  }

  @RunsInEDT
  private static List<Component> componentsOf(final MyWindow window) {
    return execute(new GuiQuery<List<Component>>() {
      @Override
      protected List<Component> executeInEDT() {
        return ContainerComponentsQuery.componentsOf(window.getContentPane());
      }
    });
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    final JButton button = new JButton("A button");

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ContainerComponentsQuery_componentsOf_Test.class);
      addComponents(button);
    }
  }
}
