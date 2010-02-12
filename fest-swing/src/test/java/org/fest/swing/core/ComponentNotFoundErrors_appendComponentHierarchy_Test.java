/*
 * Created on Feb 11, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.core.BasicComponentFinder.finderWithNewAwtHierarchy;
import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JButton;
import javax.swing.JLabel;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.*;

/**
 * Tests for <code>{@link ComponentNotFoundErrors#appendComponentHierarchy(String, java.awt.Container, ComponentFinder)}</code>.
 *
 * @author Alex Ruiz
 */
public class ComponentNotFoundErrors_appendComponentHierarchy_Test extends EDTSafeTestCase {

  private static ComponentFinder finder;
  private static MyWindow window;

  @BeforeClass
  public static void setUpOnce() {
    finder = finderWithNewAwtHierarchy();
    window = MyWindow.createNew();
  }

  @Test
  public void should_append_component_hierarchy_to_message() {
    String message = ComponentNotFoundErrors.appendComponentHierarchy("A message", window, finder);
    assertThat(message).contains("A message")
                       .contains("Component hierarchy:")
                       .contains("MyWindow[name='myWindow'")
                       .contains("javax.swing.JLabel[name='label'")
                       .contains("javax.swing.JButton[name='button'");
  }

  @Test
  public void should_not_append_component_hierarchy_to_message() {
    finder.includeHierarchyIfComponentNotFound(false);
    String message = ComponentNotFoundErrors.appendComponentHierarchy("A message", window, finder);
    assertThat(message).isEqualTo("A message");
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private MyWindow() {
      super(ComponentNotFoundErrors_appendComponentHierarchy_Test.class);
      setName("myWindow");
      addComponents(label(), button());
    }

    private static JLabel label() {
      JLabel label = new JLabel("Hello");
      label.setName("label");
      return label;
    }

    private static JButton button() {
      JButton button = new JButton("Press Me");
      button.setName("button");
      return button;
    }
  }
}
