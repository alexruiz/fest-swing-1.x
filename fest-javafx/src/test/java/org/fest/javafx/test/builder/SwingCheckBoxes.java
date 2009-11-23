/*
 * Created on Feb 20, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.test.builder;

import javax.swing.JCheckBox;

import com.sun.scenario.scenegraph.SGComponent;
import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

import static org.fest.swing.edt.GuiActionRunner.execute;

/**
 * Understands creation of <code>SwingCheckBox</code>es.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class SwingCheckBoxes {

  public static SwingCheckBoxFactory checkBox() {
    return new SwingCheckBoxFactory();
  }

  public static class SwingCheckBoxFactory {
    boolean enabled;
    String id;
    String text;
    boolean selected;

    public SwingCheckBoxFactory enabled(boolean isEnabled) {
      enabled = isEnabled;
      return this;
    }

    public SwingCheckBoxFactory withId(String newId) {
      id = newId;
      return this;
    }

    public SwingCheckBoxFactory withText(String newText) {
      text = newText;
      return this;
    }
    
    public SwingCheckBoxFactory selected(boolean isSelected) {
      selected = isSelected;
      return this;
    }

    @RunsInEDT
    public FXNode createNew() {
      SGComponent leaf = execute(new GuiQuery<SGComponent>() {
        protected SGComponent executeInEDT() {
          SGComponent c = new SGComponent();
          c.setID(id);
          JCheckBox checkBox = new JCheckBox(text);
          checkBox.setEnabled(enabled);
          checkBox.setSelected(selected);
          c.setComponent(checkBox);
          return c;
        }
      });
      return new FXNode(leaf);
    }
  }

  private SwingCheckBoxes() {}
}
