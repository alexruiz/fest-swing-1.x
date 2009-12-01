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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static javax.swing.text.DefaultEditorKit.selectAllAction;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.fest.swing.driver.CommonValidations.validateCellReader;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.JComboBoxAccessibleEditorValidator.validateEditorIsAccessible;
import static org.fest.swing.driver.JComboBoxContentQuery.contents;
import static org.fest.swing.driver.JComboBoxEditableQuery.isEditable;
import static org.fest.swing.driver.JComboBoxItemCountQuery.itemCountIn;
import static org.fest.swing.driver.JComboBoxItemIndexValidator.validateIndex;
import static org.fest.swing.driver.JComboBoxMatchingItemQuery.matchingItemIndex;
import static org.fest.swing.driver.JComboBoxSelectedIndexQuery.selectedIndexOf;
import static org.fest.swing.driver.JComboBoxSelectionValueQuery.NO_SELECTION_VALUE;
import static org.fest.swing.driver.JComboBoxSelectionValueQuery.selection;
import static org.fest.swing.driver.JComboBoxSetPopupVisibleTask.setPopupVisible;
import static org.fest.swing.driver.JComboBoxSetSelectedIndexTask.setSelectedIndex;
import static org.fest.swing.driver.TextAssert.verifyThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.util.Arrays.format;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.awt.Component;
import java.util.regex.Pattern;

import javax.swing.*;

import org.fest.assertions.Description;
import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.cell.JComboBoxCellReader;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.exception.*;
import org.fest.swing.util.*;

/**
 * Understands functional testing of <code>{@link JComboBox}</code>es:
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
public class JComboBoxDriver extends JComponentDriver {

  private static final String EDITABLE_PROPERTY = "editable";
  private static final String SELECTED_INDEX_PROPERTY = "selectedIndex";

  private final JListDriver listDriver;
  private final JComboBoxDropDownListFinder dropDownListFinder;

  private JComboBoxCellReader cellReader;

  /**
   * Creates a new </code>{@link JComboBoxDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public JComboBoxDriver(Robot robot) {
    super(robot);
    listDriver = new JListDriver(robot);
    dropDownListFinder = new JComboBoxDropDownListFinder(robot);
    cellReader(new BasicJComboBoxCellReader());
  }

  /**
   * Returns an array of <code>String</code>s that represents the contents of the given <code>{@link JComboBox}</code>
   * list. The <code>String</code> representation of each element is performed using this driver's
   * <code>{@link JComboBoxCellReader}</code>.
   * @param comboBox the target <code>JComboBox</code>.
   * @return an array of <code>String</code>s that represent the contents of the given <code>JComboBox</code> list.
   * @see #value(JComboBox, int)
   * @see #cellReader(JComboBoxCellReader)
   */
  @RunsInEDT
  public String[] contentsOf(JComboBox comboBox) {
    return contents(comboBox, cellReader);
  }

  /**
   * Selects the first item matching the given text in the <code>{@link JComboBox}</code>. The text of the
   * <code>JComboBox</code> items is obtained by this fixture's <code>{@link JComboBoxCellReader}</code>.
   * @param comboBox the target <code>JComboBox</code>.
   * @param value the value to match. It can be a regular expression.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   * @throws IllegalStateException if the <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if the <code>JComboBox</code> is not showing on the screen.
   * @see #cellReader(JComboBoxCellReader)
   */
  @RunsInEDT
  public void selectItem(JComboBox comboBox, String value) {
    selectItem(comboBox, new StringTextMatcher(value));
  }

  /**
   * Selects the first item matching the given regular expression pattern in the <code>{@link JComboBox}</code>. The
   * text of the <code>JComboBox</code> items is obtained by this fixture's <code>{@link JComboBoxCellReader}</code>.
   * @param comboBox the target <code>JComboBox</code>.
   * @param pattern the regular expression pattern to match.
   * @throws LocationUnavailableException if an element matching the given pattern cannot be found.
   * @throws IllegalStateException if the <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if the <code>JComboBox</code> is not showing on the screen.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
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
   * Verifies that the <code>String</code> representation of the selected item in the <code>{@link JComboBox}</code>
   * matches the given text.
   * @param comboBox the target <code>JComboBox</code>.
   * @param value the text to match. It can be a regular expression.
   * @throws AssertionError if the selected item does not match the given value.
   * @see #cellReader(JComboBoxCellReader)
   */
  @RunsInEDT
  public void requireSelection(JComboBox comboBox, String value) {
    String selection = selectionRequiredOf(comboBox);
    verifyThat(selection).as(selectedIndexProperty(comboBox)).isEqualOrMatches(value);
  }

  /**
   * Verifies that the <code>String</code> representation of the selected item in the <code>{@link JComboBox}</code>
   * matches the given regular expression pattern.
   * @param comboBox the target <code>JComboBox</code>.
   * @param pattern the regular expression pattern to match.
   * @throws AssertionError if the selected item does not match the given regular expression pattern.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @see #cellReader(JComboBoxCellReader)
   * @since 1.2
   */
  @RunsInEDT
  public void requireSelection(JComboBox comboBox, Pattern pattern) {
    String selection = selectionRequiredOf(comboBox);
    verifyThat(selection).as(selectedIndexProperty(comboBox)).matches(pattern);
  }

  private String selectionRequiredOf(JComboBox comboBox) throws AssertionError {
    Object selection = selection(comboBox, cellReader);
    if (NO_SELECTION_VALUE == selection) throw failNoSelection(comboBox);
    return (String)selection;
  }

   /**
    * Verifies that the index of the selected item in the <code>{@link JComboBox}</code> is equal to the given value.
    * @param comboBox the target <code>JComboBox</code>.
    * @param index the expected selection index.
    * @throws AssertionError if the selection index is not equal to the given value.
    * @since 1.2
    */
   @RunsInEDT
  public void requireSelection(JComboBox comboBox, int index) {
     int selectedIndex = selectedIndexOf(comboBox);
     if (selectedIndex == -1) failNoSelection(comboBox);
     assertThat(selectedIndex).as(selectedIndexProperty(comboBox)).isEqualTo(index);
  }

  private AssertionError failNoSelection(JComboBox comboBox) {
    throw fail(concat("[", selectedIndexProperty(comboBox).value(), "] No selection"));
  }

  /**
   * Verifies that the <code>{@link JComboBox}</code> does not have any selection.
   * @param comboBox the target <code>JComboBox</code>.
   * @throws AssertionError if the <code>JComboBox</code> has a selection.
   */
  @RunsInEDT
  public void requireNoSelection(JComboBox comboBox) {
    Object selection = selection(comboBox, cellReader);
    if (NO_SELECTION_VALUE == selection) return;
    fail(concat(
        "[", selectedIndexProperty(comboBox).value(), "] Expecting no selection, but found:<", quote(selection), ">"));
  }

  /**
   * Returns the <code>String</code> representation of the element under the given index, using this driver's
   * <code>{@link JComboBoxCellReader}</code>.
   * @param comboBox the target <code>JComboBox</code>.
   * @param index the given index.
   * @return the value of the element under the given index.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JComboBox</code>.
   * @see #cellReader(JComboBoxCellReader)
   */
  public String value(JComboBox comboBox, int index) {
    return valueAsText(comboBox, index, cellReader);
  }

  @RunsInEDT
  private static String valueAsText(final JComboBox comboBox, final int index, final JComboBoxCellReader cellReader) {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        validateIndex(comboBox, index);
        return cellReader.valueAt(comboBox, index);
      }
    });
  }

  private Description selectedIndexProperty(JComboBox comboBox) {
    return propertyName(comboBox, SELECTED_INDEX_PROPERTY);
  }


  /**
   * Clears the selection in the given <code>{@link JComboBox}</code>. Since this method does not simulate user input,
   * it does not verifies that the <code>JComboBox</code> is enabled and showing.
   * @param comboBox the target <code>JComboBox</code>.
   * @since 1.2
   */
  public void clearSelection(JComboBox comboBox) {
    setSelectedIndex(comboBox, -1);
    robot.waitForIdle();
  }

  /**
   * Selects the item under the given index in the <code>{@link JComboBox}</code>.
   * @param comboBox the target <code>JComboBox</code>.
   * @param index the given index.
   * @throws IllegalStateException if the <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if the <code>JComboBox</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JComboBox</code>.
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
      protected void executeInEDT() {
        validateIsEnabledAndShowing(comboBox);
        validateIndex(comboBox, index);
      }
    });
  }

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
   * @param comboBox the target <code>JComboBox</code>.
   * @param text the text to enter.
   * @throws IllegalStateException if the <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if the <code>JComboBox</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JComboBox</code> is not editable.
   */
  @RunsInEDT
  public void replaceText(JComboBox comboBox, String text) {
    selectAllText(comboBox);
    enterText(comboBox, text);
  }

  /**
   * Simulates a user selecting the text in the <code>{@link JComboBox}</code>. This action is executed only if the
   * <code>{@link JComboBox}</code> is editable.
   * @param comboBox the target <code>JComboBox</code>.
   * @throws IllegalStateException if the <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if the <code>JComboBox</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JComboBox</code> is not editable.
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
      protected Component executeInEDT() {
        validateEditorIsAccessible(comboBox);
        return editorComponentOf(comboBox);
      }
    });
  }

  /**
   * Simulates a user entering the specified text in the <code>{@link JComboBox}</code>. This action is executed only
   * if the <code>{@link JComboBox}</code> is editable.
   * @param comboBox the target <code>JComboBox</code>.
   * @param text the text to enter.
   * @throws IllegalStateException if the <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if the <code>JComboBox</code> is not showing on the screen.
   * @throws IllegalStateException if the <code>JComboBox</code> is not editable.
   * @throws ActionFailedException if the <code>JComboBox</code> does not have an editor.
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
   * @param comboBox the target component.
   * @param keyCodes one or more codes of the keys to press.
   * @throws NullPointerException if the given array of codes is <code>null</code>.
   * @throws IllegalStateException if the <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if the <code>JComboBox</code> is not showing on the screen.
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
      protected Component executeInEDT() {
        if (!comboBox.isEditable()) return null;
        return editorComponent(comboBox);
      }
    });
  }

  @RunsInEDT
  private static void inEdtValidateEditorIsAccessible(final JComboBox comboBox) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        validateEditorIsAccessible(comboBox);
      }
    });
  }

  @RunsInEDT
  private static Component editorComponentOf(final JComboBox comboBox) {
    return execute(new GuiQuery<Component>() {
      protected Component executeInEDT() {
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
   * Asserts that the given <code>{@link JComboBox}</code> is editable.
   * @param comboBox the target <code>JComboBox</code>.
   * @throws AssertionError if the <code>JComboBox</code> is not editable.
   */
  @RunsInEDT
  public void requireEditable(final JComboBox comboBox) {
    assertEditable(comboBox, true);
  }

  /**
   * Asserts that the given <code>{@link JComboBox}</code> is not editable.
   * @param comboBox the given <code>JComboBox</code>.
   * @throws AssertionError if the <code>JComboBox</code> is editable.
   */
  @RunsInEDT
  public void requireNotEditable(JComboBox comboBox) {
    assertEditable(comboBox, false);
  }

  @RunsInEDT
  private void assertEditable(JComboBox comboBox, boolean expected) {
    assertThat(isEditable(comboBox)).as(editableProperty(comboBox)).isEqualTo(expected);
  }

  @RunsInEDT
  private static Description editableProperty(JComboBox comboBox) {
    return propertyName(comboBox, EDITABLE_PROPERTY);
  }

  /**
   * Updates the implementation of <code>{@link JComboBoxCellReader}</code> to use when comparing internal values
   * of a <code>{@link JComboBox}</code> and the values expected in a test.
   * @param newCellReader the new <code>JComboBoxCellValueReader</code> to use.
   * @throws NullPointerException if <code>newCellReader</code> is <code>null</code>.
   */
  public void cellReader(JComboBoxCellReader newCellReader) {
    validateCellReader(newCellReader);
    cellReader = newCellReader;
  }

  /**
   * Verifies that number of items in the given <code>{@link JComboBox}</code> is equal to the expected one.
   * @param comboBox the target <code>JComboBox</code>.
   * @param expected the expected number of items.
   * @throws AssertionError if the number of items in the given <code>{@link JComboBox}</code> is not equal to the
   * expected one.
   * @since 1.2
   */
  @RunsInEDT
  public void requireItemCount(JComboBox comboBox, int expected) {
    int actual = itemCountIn(comboBox);
    assertThat(actual).as(propertyName(comboBox, "itemCount")).isEqualTo(expected);
  }
}
