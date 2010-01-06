/*
 * Created on Mar 11, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static javax.swing.JOptionPane.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.AbstractButtonTextQuery.textOf;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Objects.*;

import javax.swing.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.RobotBasedTestCase;

/**
 * Base test case for <code>{@link JOptionPaneDriver}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class JOptionPaneDriver_TestCase extends RobotBasedTestCase {

  private static final Icon ICON = null;
  private static final String MESSAGE = "Message";

  JOptionPaneDriver driver;

  @Override protected final void onSetUp() {
    driver = new JOptionPaneDriver(robot);
  }

  final String title() {
    return getClass().getSimpleName();
  }

  @RunsInEDT
  static JOptionPane confirmMessage() {
    return execute(new GuiQuery<JOptionPane>() {
      protected JOptionPane executeInEDT() {
        return new JOptionPane(MESSAGE, QUESTION_MESSAGE, YES_NO_CANCEL_OPTION);
      }
    });
  }

  @RunsInEDT
  final void assertThatButtonHasText(JButton button, String textKey) {
    String expected = UIManager.getString(textKey);
    assertThat(textOf(button)).isEqualTo(expected);
  }

  @RunsInEDT
  static JOptionPane inputMessage() {
    return execute(new GuiQuery<JOptionPane>() {
      protected JOptionPane executeInEDT() {
        JOptionPane optionPane = new JOptionPane(MESSAGE, QUESTION_MESSAGE, OK_CANCEL_OPTION);
        optionPane.setWantsInput(true);
        return optionPane;
      }
    });
  }

  @RunsInEDT
  static JOptionPane messageWithOptions(final Object...options) {
    return execute(new GuiQuery<JOptionPane>() {
      protected JOptionPane executeInEDT() {
        Object initialValue = options[0];
        JOptionPane optionPane = new JOptionPane(MESSAGE, QUESTION_MESSAGE, YES_NO_OPTION, ICON, options, initialValue);
        optionPane.setInitialValue(initialValue);
        return optionPane;
      }
    });
  }

  static class Person {
    private final String name;

    Person(String name) {
      this.name = name;
    }

    @Override public int hashCode() {
      final int prime = HASH_CODE_PRIME;
      int result = 1;
      result = prime * result + hashCodeFor(name);
      return result;
    }

    @Override public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null) return false;
      if (getClass() != obj.getClass()) return false;
      Person other = (Person) obj;
      return areEqual(name, other.name);
    }

    @Override public String toString() {
      return name;
    }
  }

  @RunsInEDT
  static JOptionPane messageWithValue(final Object message) {
    return execute(new GuiQuery<JOptionPane>() {
      protected JOptionPane executeInEDT() {
        return new JOptionPane(message);
      }
    });
  }

  @RunsInEDT
  static JOptionPane informationMessage() {
    return messageOfType(INFORMATION_MESSAGE);
  }

  @RunsInEDT
  static JOptionPane errorMessage() {
    return messageOfType(ERROR_MESSAGE);
  }

  @RunsInEDT
  static JOptionPane messageOfType(final int type) {
    return execute(new GuiQuery<JOptionPane>() {
      protected JOptionPane executeInEDT() {
        return new JOptionPane(MESSAGE, type);
      }
    });
  }
}
