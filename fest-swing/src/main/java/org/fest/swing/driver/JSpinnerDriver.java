/*
 * Created on Jan 26, 2008
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

import static javax.swing.text.DefaultEditorKit.selectAllAction;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.JSpinnerSetValueTask.setValue;
import static org.fest.swing.driver.JSpinnerValueQuery.valueOf;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.format.Formatting.format;
import static org.fest.util.Strings.*;

import java.awt.Component;
import java.text.ParseException;
import java.util.*;

import javax.swing.JSpinner;
import javax.swing.text.JTextComponent;

import org.fest.swing.annotation.*;
import org.fest.swing.core.*;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.*;

/**
 * Understands functional testing of <code>{@link JSpinner}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JSpinnerDriver extends JComponentDriver {

  private static final TypeMatcher EDITOR_MATCHER = new TypeMatcher(JTextComponent.class, true);
  private static final String VALUE_PROPERTY = "value";

  /**
   * Creates a new </code>{@link JSpinnerDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JSpinnerDriver(Robot robot) {
    super(robot);
  }

  /**
   * Increments the value of the <code>{@link JSpinner}</code> the given number of times.
   * @param spinner the target <code>JSpinner</code>.
   * @param times how many times the value of this fixture's <code>JSpinner</code> should be incremented.
   * @throws IllegalArgumentException if <code>times</code> is less than or equal to zero.
   * @throws IllegalStateException if the <code>JSpinner</code> is disabled.
   * @throws IllegalStateException if the <code>JSpinner</code> is not showing on the screen.
   */
  @RunsInEDT
  public void increment(JSpinner spinner, int times) {
    validate(times, "increment the value");
    validateAndIncrementValue(spinner, times);
    robot.waitForIdle();
  }

  @RunsInEDT
  private static void validateAndIncrementValue(final JSpinner spinner, final int times) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        validateIsEnabledAndShowing(spinner);
        incrementValue(spinner, times);
      }
    });
  }

  @RunsInCurrentThread
  private static void incrementValue(JSpinner spinner, int times) {
    for (int i = 0; i < times; i++) {
      Object newValue = spinner.getNextValue();
      if (newValue == null) return;
      spinner.setValue(newValue);
    }
  }

  /**
   * Increments the value of the <code>{@link JSpinner}</code>.
   * @param spinner the target <code>JSpinner</code>.
   * @throws IllegalStateException if the <code>JSpinner</code> is disabled.
   * @throws IllegalStateException if the <code>JSpinner</code> is not showing on the screen.
   */
  @RunsInEDT
  public void increment(JSpinner spinner) {
    validateAndIncrementValue(spinner);
    robot.waitForIdle();
  }

  @RunsInEDT
  private static void validateAndIncrementValue(final JSpinner spinner) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        validateIsEnabledAndShowing(spinner);
        Object newValue = spinner.getNextValue();
        if (newValue != null) spinner.setValue(newValue);
      }
    });
  }

  /**
   * Decrements the value of the <code>{@link JSpinner}</code> the given number of times.
   * @param spinner the target <code>JSpinner</code>.
   * @param times how many times the value of this fixture's <code>JSpinner</code> should be decremented.
   * @throws IllegalArgumentException if <code>times</code> is less than or equal to zero.
   * @throws IllegalStateException if the <code>JSpinner</code> is disabled.
   * @throws IllegalStateException if the <code>JSpinner</code> is not showing on the screen.
   */
  @RunsInEDT
  public void decrement(JSpinner spinner, int times) {
    validate(times, "decrement the value");
    validateAndDecrementValue(spinner, times);
    robot.waitForIdle();
  }

  private void validate(int times, String action) {
    if (times > 0) return;
    throw new IllegalArgumentException(concat(
        "The number of times to ", action, " should be greater than zero, but was <", times, ">"));
  }

  @RunsInEDT
  private static void validateAndDecrementValue(final JSpinner spinner, final int times) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        validateIsEnabledAndShowing(spinner);
        decrementValue(spinner, times);
      }
    });
  }

  @RunsInCurrentThread
  private static void decrementValue(JSpinner spinner, int times) {
    for (int i = 0; i < times; i++) {
      Object newValue = spinner.getPreviousValue();
      if (newValue == null) return;
      spinner.setValue(newValue);
    }
  }

  /**
   * Decrements the value of the <code>{@link JSpinner}</code>.
   * @param spinner the target <code>JSpinner</code>.
   * @throws IllegalStateException if the <code>JSpinner</code> is disabled.
   * @throws IllegalStateException if the <code>JSpinner</code> is not showing on the screen.
   */
  @RunsInEDT
  public void decrement(JSpinner spinner) {
    validateAndDecrementValue(spinner);
    robot.waitForIdle();
  }

  @RunsInEDT
  private static void validateAndDecrementValue(final JSpinner spinner) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        validateIsEnabledAndShowing(spinner);
        Object newValue = spinner.getPreviousValue();
        if (newValue != null) spinner.setValue(newValue);
      }
    });
  }

  /**
   * Returns the text displayed in the given <code>{@link JSpinner}</code>. This method first tries to get the text
   * displayed in the <code>JSpinner</code>'s editor, assuming it is a <code>{@link JTextComponent}</code>. If the
   * text from the editor cannot be retrieved, it will return the {@code String} representation of the value
   * in the <code>JSpinner</code>'s model.
   * @param spinner the target <code>JSpinner</code>.
   * @return the text displayed in the given <code>JSpinner</code>.
   * @since 1.2
   */
  @RunsInEDT
  public String textOf(JSpinner spinner) {
    JTextComponent editor = findEditor(spinner);
    if (editor != null) return JTextComponentTextQuery.textOf(editor);
    Object value = valueOf(spinner);
    return value != null ? value.toString() : null;
  }

  /**
   * Enters and commits the given text in the <code>{@link JSpinner}</code>, assuming its editor has a
   * <code>{@link JTextComponent}</code> under it.
   * @param spinner the target <code>JSpinner</code>.
   * @param text the text to enter.
   * @throws IllegalStateException if the <code>JSpinner</code> is disabled.
   * @throws IllegalStateException if the <code>JSpinner</code> is not showing on the screen.
   * @throws ActionFailedException if the editor of the <code>JSpinner</code> is not a <code>JTextComponent</code> or
   * cannot be found.
   * @throws UnexpectedException if entering the text in the <code>JSpinner</code>'s editor fails.
   */
  @RunsInEDT
  public void enterTextAndCommit(JSpinner spinner, String text) {
    enterText(spinner, text);
    commit(spinner);
    robot.waitForIdle();
  }

  @RunsInEDT
  private static void commit(final JSpinner spinner) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() throws ParseException {
        spinner.commitEdit();
      }
    });
  }

  /**
   * Enters the given text in the <code>{@link JSpinner}</code>, assuming its editor has a
   * <code>{@link JTextComponent}</code> under it. This method does not commit the value to the <code>JSpinner</code>.
   * @param spinner the target <code>JSpinner</code>.
   * @param text the text to enter.
   * @throws IllegalStateException if the <code>JSpinner</code> is disabled.
   * @throws IllegalStateException if the <code>JSpinner</code> is not showing on the screen.
   * @throws ActionFailedException if the editor of the <code>JSpinner</code> is not a <code>JTextComponent</code> or
   * cannot be found.
   * @throws UnexpectedException if entering the text in the <code>JSpinner</code>'s editor fails.
   * @see #enterTextAndCommit(JSpinner, String)
   */
  @RunsInEDT
  public void enterText(JSpinner spinner, String text) {
    assertIsEnabledAndShowing(spinner);
    JTextComponent editor = findEditor(spinner);
    validate(spinner, editor);
    robot.waitForIdle();
    robot.focusAndWaitForFocusGain(editor);
    invokeAction(editor, selectAllAction);
    robot.enterText(text);
  }

  @RunsInEDT
  private JTextComponent findEditor(JSpinner spinner) {
    List<Component> found = new ArrayList<Component>(robot.finder().findAll(spinner, EDITOR_MATCHER));
    if (found.size() != 1) return null;
    Component c = found.get(0);
    if (c instanceof JTextComponent) return (JTextComponent)c;
    return null;
  }

  @RunsInEDT
  private static void validate(final JSpinner spinner, final JTextComponent editor) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        if (editor == null) throw actionFailure(concat("Unable to find editor for ", format(spinner)));
      }
    });
  }

  /**
   * Selects the given value in the given <code>{@link JSpinner}</code>.
   * @param spinner the target <code>JSpinner</code>.
   * @param value the value to select.
   * @throws IllegalStateException if the <code>JSpinner</code> is disabled.
   * @throws IllegalStateException if the <code>JSpinner</code> is not showing on the screen.
   * @throws IllegalArgumentException if the given <code>JSpinner</code> does not support the given value.
   */
  @RunsInEDT
  public void selectValue(JSpinner spinner, Object value) {
    try {
      setValue(spinner, value);
    } catch (IllegalArgumentException e) {
      // message from original exception is useless
      throw new IllegalArgumentException(concat("Value ", quote(value), " is not valid"));
    }
    robot.waitForIdle();
  }

  /**
   * Returns the <code>{@link JTextComponent}</code> used as editor in the given <code>{@link JSpinner}</code>.
   * @param spinner the target <code>JSpinner</code>.
   * @return the <code>JTextComponent</code> used as editor in the given <code>JSpinner</code>.
   * @throws ComponentLookupException if the given <code>JSpinner</code> does not have a <code>JTextComponent</code> as
   * editor.
   */
  @RunsInEDT
  public JTextComponent editor(JSpinner spinner) {
    return (JTextComponent)robot.finder().find(spinner, EDITOR_MATCHER);
  }

  /**
   * Verifies that the value of the <code>{@link JSpinner}</code> is equal to the given one.
   * @param spinner the target <code>JSpinner</code>.
   * @param value the expected value.
   * @throws AssertionError if the value of the <code>JSpinner</code> is not equal to the given one.
   */
  @RunsInEDT
  public void requireValue(JSpinner spinner, Object value) {
    assertThat(valueOf(spinner)).as(propertyName(spinner, VALUE_PROPERTY)).isEqualTo(value);
  }
}
