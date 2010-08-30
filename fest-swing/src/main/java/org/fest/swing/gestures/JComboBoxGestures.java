/*
 * Created on Jan 21, 2008
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
package org.fest.swing.gestures;

import static javax.swing.text.DefaultEditorKit.selectAllAction;
import static org.fest.swing.driver.CommonValidations.validateCellReader;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.gestures.JComboBoxAccessibleEditorValidator.validateEditorIsAccessible;
import static org.fest.swing.gestures.JComboBoxContentQuery.contents;
import static org.fest.swing.gestures.JComboBoxItemIndexValidator.validateIndex;
import static org.fest.swing.gestures.JComboBoxMatchingItemQuery.matchingItemIndex;
import static org.fest.swing.gestures.JComboBoxSetPopupVisibleTask.setPopupVisible;
import static org.fest.swing.gestures.JComboBoxSetSelectedIndexTask.setSelectedIndex;
import static org.fest.util.Arrays.format;
import static org.fest.util.Strings.concat;

import java.awt.Component;
import java.util.regex.Pattern;

import javax.swing.*;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JComboBoxCellReader;
import org.fest.swing.core.Robot;
import org.fest.swing.driver.BasicJComboBoxCellReader;
import org.fest.swing.driver.JListDriver;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.*;
import org.fest.swing.util.*;
import org.fest.util.VisibleForTesting;

/**
 * Simulates user input on a <code>{@link JComboBox}</code>.
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JComboBoxGestures extends JComponentGestures {

  private final JListDriver listDriver;
  private final JComboBoxDropDownListFinder dropDownListFinder;

  private JComboBoxCellReader cellReader;

  /**
   * Creates a new </code>{@link JComboBoxGestures}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JComboBoxGestures(Robot robot) {
    super(robot);
    listDriver = new JListDriver(robot);
    dropDownListFinder = new JComboBoxDropDownListFinder(robot);
    cellReader(new BasicJComboBoxCellReader());
  }

  /**
   * Returns an array of {@code String}s that represents the contents of the given <code>{@link JComboBox}</code>
   * list. The {@code String} representation of each element is performed using this driver's
   * <code>{@link JComboBoxCellReader}</code>.
   * @param comboBox the target {@code JComboBox}.
   * @return an array of {@code String}s that represent the contents of the given {@code JComboBox} list.
   * @see #cellReader(JComboBoxCellReader)
   */
  @RunsInEDT
  public String[] contentsOf(JComboBox comboBox) {
    return contents(comboBox, cellReader);
  }

  /**
   * Selects the first item matching the given text in the <code>{@link JComboBox}</code>. The text of the
   * {@code JComboBox} items is obtained by this fixture's <code>{@link JComboBoxCellReader}</code>.
   * @param comboBox the target {@code JComboBox}.
   * @param value the value to match. It can be a regular expression.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   * @throws IllegalStateException if the {@code JComboBox} is disabled.
   * @throws IllegalStateException if the {@code JComboBox} is not showing on the screen.
   * @see #cellReader(JComboBoxCellReader)
   */
  @RunsInEDT
  public void selectItem(JComboBox comboBox, String value) {
    selectItem(comboBox, new StringTextMatcher(value));
  }

  /**
   * Selects the first item matching the given regular expression pattern in the <code>{@link JComboBox}</code>. The
   * text of the {@code JComboBox} items is obtained by this fixture's <code>{@link JComboBoxCellReader}</code>.
   * @param comboBox the target {@code JComboBox}.
   * @param pattern the regular expression pattern to match.
   * @throws LocationUnavailableException if an element matching the given pattern cannot be found.
   * @throws IllegalStateException if the {@code JComboBox} is disabled.
   * @throws IllegalStateException if the {@code JComboBox} is not showing on the screen.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @see #cellReader(JComboBoxCellReader)
   * @since 1.2
   */
  @RunsInEDT
  public void selectItem(JComboBox comboBox, Pattern pattern) {
    selectItem(comboBox, new PatternTextMatcher(pattern));
  }

  @RunsInEDT
  private void selectItem(JComboBox comboBox, TextMatcher matcher) {
    int index = matchingItemIndex(comboBox, matcher, cellReader);
    if (index < 0) throw failMatchingNotFound(comboBox, matcher);
    selectItem(comboBox, index);
  }

  private LocationUnavailableException failMatchingNotFound(JComboBox comboBox, TextMatcher matcher) {
    throw new LocationUnavailableException(concat(
        "Unable to find item matching the ", matcher.description(), " ", matcher.formattedValues(),
        " among the JComboBox contents (", format(contentsOf(comboBox)), ")"));
  }

  /**
   * Clears the selection in the given <code>{@link JComboBox}</code>. Since this method does not simulate user input,
   * it does not verifies that the {@code JComboBox} is enabled and showing.
   * @param comboBox the target {@code JComboBox}.
   * @since 1.2
   */
  public void clearSelection(JComboBox comboBox) {
    setSelectedIndex(comboBox, -1);
    robot.waitForIdle();
  }

  /**
   * Selects the item under the given index in the <code>{@link JComboBox}</code>.
   * @param comboBox the target {@code JComboBox}.
   * @param index the given index.
   * @throws IllegalStateException if the {@code JComboBox} is disabled.
   * @throws IllegalStateException if the {@code JComboBox} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * {@code JComboBox}.
   */
  @RunsInEDT
  public void selectItem(final JComboBox comboBox, int index) {
    validateCanSelectItem(comboBox, index);
    showDropDownList(comboBox);
    selectItemAtIndex(comboBox, index);
    hideDropDownListIfVisible(comboBox);
  }

  @RunsInEDT
  private static void validateCanSelectItem(final JComboBox comboBox, final int index) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        validateIsEnabledAndShowing(comboBox);
        validateIndex(comboBox, index);
      }
    });
  }

  @VisibleForTesting
  @RunsInEDT
  void showDropDownList(JComboBox comboBox) {
    // Location of pop-up button activator is LAF-dependent
    dropDownVisibleThroughUIDelegate(comboBox, true);
  }

  @RunsInEDT
  private void selectItemAtIndex(final JComboBox comboBox, final int index) {
    JList dropDownList = dropDownListFinder.findDropDownList();
    if (dropDownList != null) {
      listDriver.selectItem(dropDownList, index);
      return;
    }
    setSelectedIndex(comboBox, index);
    robot.waitForIdle();
  }

  @RunsInEDT
  private void hideDropDownListIfVisible(JComboBox comboBox) {
    dropDownVisibleThroughUIDelegate(comboBox, false);
  }

  @RunsInEDT
  private void dropDownVisibleThroughUIDelegate(JComboBox comboBox, final boolean visible) {
    setPopupVisible(comboBox, visible);
    robot.waitForIdle();
  }

  /**
   * Simulates a user entering the specified text in the <code>{@link JComboBox}</code>, replacing any text. This action
   * is executed only if the <code>{@link JComboBox}</code> is editable.
   * @param comboBox the target {@code JComboBox}.
   * @param text the text to enter.
   * @throws IllegalStateException if the {@code JComboBox} is disabled.
   * @throws IllegalStateException if the {@code JComboBox} is not showing on the screen.
   * @throws IllegalStateException if the {@code JComboBox} is not editable.
   */
  @RunsInEDT
  public void replaceText(JComboBox comboBox, String text) {
    selectAllText(comboBox);
    enterText(comboBox, text);
  }

  /**
   * Simulates a user selecting the text in the <code>{@link JComboBox}</code>. This action is executed only if the
   * <code>{@link JComboBox}</code> is editable.
   * @param comboBox the target {@code JComboBox}.
   * @throws IllegalStateException if the {@code JComboBox} is disabled.
   * @throws IllegalStateException if the {@code JComboBox} is not showing on the screen.
   * @throws IllegalStateException if the {@code JComboBox} is not editable.
   */
  @RunsInEDT
  public void selectAllText(JComboBox comboBox) {
    Component editor = accessibleEditorOf(comboBox);
    if (!(editor instanceof JComponent)) return;
    focus(editor);
    invokeAction((JComponent) editor, selectAllAction);
  }

  @RunsInEDT
  private static Component accessibleEditorOf(final JComboBox comboBox) {
    return execute(new GuiQuery<Component>() {
      @Override protected Component executeInEDT() {
        validateEditorIsAccessible(comboBox);
        return editorComponentOf(comboBox);
      }
    });
  }

  /**
   * Simulates a user entering the specified text in the <code>{@link JComboBox}</code>. This action is executed only
   * if the <code>{@link JComboBox}</code> is editable.
   * @param comboBox the target {@code JComboBox}.
   * @param text the text to enter.
   * @throws IllegalStateException if the {@code JComboBox} is disabled.
   * @throws IllegalStateException if the {@code JComboBox} is not showing on the screen.
   * @throws IllegalStateException if the {@code JComboBox} is not editable.
   * @throws ActionFailedException if the {@code JComboBox} does not have an editor.
   */
  @RunsInEDT
  public void enterText(JComboBox comboBox, String text) {
    inEdtValidateEditorIsAccessible(comboBox);
    Component editor = editorComponentOf(comboBox);
    // this will never happen...at least in Sun's JVM
    if (editor == null) throw actionFailure("JComboBox does not have an editor");
    focusAndWaitForFocusGain(editor);
    robot.enterText(text);
  }

  /**
   * Simulates a user pressing and releasing the given keys on the <code>{@link JComboBox}</code>.
   * @param comboBox the target {@code JComboBox}.
   * @param keyCodes one or more codes of the keys to press.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalStateException if the {@code JComboBox} is disabled.
   * @throws IllegalStateException if the {@code JComboBox} is not showing on the screen.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  @RunsInEDT
  public void pressAndReleaseKeys(JComboBox comboBox, int... keyCodes) {
    if (keyCodes == null) throw new NullPointerException("The array of key codes should not be null");
    assertIsEnabledAndShowing(comboBox);
    Component target = editorIfEditable(comboBox);
    if (target == null) target = comboBox;
    focusAndWaitForFocusGain(target);
    robot.pressAndReleaseKeys(keyCodes);
  }

  @RunsInEDT
  private static Component editorIfEditable(final JComboBox comboBox) {
    return execute(new GuiQuery<Component>() {
      @Override protected Component executeInEDT() {
        if (!comboBox.isEditable()) return null;
        return editorComponent(comboBox);
      }
    });
  }

  @RunsInEDT
  private static void inEdtValidateEditorIsAccessible(final JComboBox comboBox) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        validateEditorIsAccessible(comboBox);
      }
    });
  }

  @RunsInEDT
  private static Component editorComponentOf(final JComboBox comboBox) {
    return execute(new GuiQuery<Component>() {
      @Override protected Component executeInEDT() {
        return editorComponent(comboBox);
      }
    });
  }

  @RunsInCurrentThread
  private static Component editorComponent(JComboBox comboBox) {
    ComboBoxEditor editor = comboBox.getEditor();
    if (editor == null) return null;
    return editor.getEditorComponent();
  }

  /**
   * Find the <code>{@link JList}</code> in the pop-up raised by the <code>{@link JComboBox}</code>, if the LAF actually
   * uses one.
   * @return the found <code>JList</code>.
   * @throws ComponentLookupException if the <code>JList</code> in the pop-up could not be found.
   */
  @RunsInEDT
  public JList dropDownList() {
    JList list = dropDownListFinder.findDropDownList();
    if (list == null) throw listNotFound();
    return list;
  }

  private ComponentLookupException listNotFound() {
    throw new ComponentLookupException("Unable to find the pop-up list for the JComboBox");
  }

  /**
   * Updates the implementation of <code>{@link JComboBoxCellReader}</code> to use when comparing internal values
   * of a <code>{@link JComboBox}</code> and the values expected in a test.
   * @param newCellReader the new <code>JComboBoxCellValueReader</code> to use.
   * @throws NullPointerException if <code>newCellReader</code> is {@code null}.
   */
  public void cellReader(JComboBoxCellReader newCellReader) {
    validateCellReader(newCellReader);
    cellReader = newCellReader;
  }
}
