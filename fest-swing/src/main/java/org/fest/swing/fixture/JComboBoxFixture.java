/*
 * Created on Apr 9, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.JComboBox;
import javax.swing.JList;

import org.fest.swing.cell.JComboBoxCellReader;
import org.fest.swing.core.*;
import org.fest.swing.driver.BasicJComboBoxCellReader;
import org.fest.swing.driver.JComboBoxDriver;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JComboBox}</code>es:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * <p>
 * The conversion between the values given in tests and the values being displayed by a <code>{@link JComboBox}</code>
 * renderer is performed by a <code>{@link JComboBoxCellReader}</code>. This fixture uses a
 * <code>{@link JComboBoxCellReader}</code> by default.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JComboBoxFixture extends ComponentFixture<JComboBox> implements CommonComponentFixture,
    EditableComponentFixture, ItemGroupFixture, JComponentFixture, JPopupMenuInvokerFixture {

  private JComboBoxDriver driver;

  /**
   * Creates a new <code>{@link JComboBoxFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JComboBox</code>.
   * @param target the <code>JComboBox</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public JComboBoxFixture(Robot robot, JComboBox target) {
    super(robot, target);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JComboBoxFixture}</code>.
   * @param robot performs simulation of user events on a <code>JComboBox</code>.
   * @param comboBoxName the name of the <code>JComboBox</code> to find using the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching <code>JComboBox</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JComboBox</code> is found.
   */
  public JComboBoxFixture(Robot robot, String comboBoxName) {
    super(robot, comboBoxName, JComboBox.class);
    createDriver();
  }

  private void createDriver() {
    driver(new JComboBoxDriver(robot));
  }

  /**
   * Sets the <code>{@link JComboBoxDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JComboBoxDriver</code>.
   * @throws NullPointerException if the given driver is <code>null</code>.
   */
  protected final void driver(JComboBoxDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JComboBox}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   */
  public JComboBoxFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JComboBox}</code>.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseButton</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   */
  public JComboBoxFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JComboBox}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   */
  public JComboBoxFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JComboBox}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   */
  public JComboBoxFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JComboBox}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   */
  public JComboBoxFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Returns the <code>String</code> representation of the elements in this fixture's <code>{@link JComboBox}</code>,
   * using this fixture's <code>{@link JComboBoxCellReader}</code>.
   * @return the <code>String</code> representation of the elements in this fixture's <code>JComboBox</code>.
   * @see #cellReader(JComboBoxCellReader)
   */
  public String[] contents() {
    return driver.contentsOf(target);
  }

  /**
   * Simulates a user entering the specified text in the <code>{@link JComboBox}</code>, replacing any text. This action
   * is executed only if the <code>{@link JComboBox}</code> is editable.
   * @param text the text to enter.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not editable.
   */
  public JComboBoxFixture replaceText(String text) {
    driver.replaceText(target, text);
    return this;
  }

  /**
   * Simulates a user selecting the text in the <code>{@link JComboBox}</code>. This action is executed only if the
   * <code>{@link JComboBox}</code> is editable.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not editable.
   */
  public JComboBoxFixture selectAllText() {
    driver.selectAllText(target);
    return this;
  }

  /**
   * Simulates a user entering the specified text in this fixture's <code>{@link JComboBox}</code>. This action is
   * executed only if the <code>{@link JComboBox}</code> is editable.
   * @param text the text to enter.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not editable.
   * @throws ActionFailedException if this fixture's <code>JComboBox</code> does not have an editor.
   */
  public JComboBoxFixture enterText(String text) {
    driver.enterText(target, text);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JComboBox}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   */
  public JComboBoxFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Finds and returns the {@link JList} in the pop-up raised by this fixture's <code>{@link JComboBox}</code>.
   * @return the <code>JList</code> in the pop-up raised by this fixture's <code>JComboBox</code>.
   * @throws ComponentLookupException if the <code>JList</code> in the pop-up could not be found.
   */
  public JList list() {
    return driver.dropDownList();
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JComboBox}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @see KeyPressInfo
   */
  public JComboBoxFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's <code>{@link JComboBox}</code>.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is <code>null</code>.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JComboBoxFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JComboBox}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JComboBoxFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JComboBox}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JComboBoxFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Clears the selection in this fixture's <code>{@link JComboBox}</code>. Since this method does not simulate user
   * input, it does not verifies that this fixture's <code>JComboBox</code> is enabled and showing.
   * @return this fixture.
   * @since 1.2
   */
  public JComboBoxFixture clearSelection() {
    driver.clearSelection(target);
    return this;
  }

  /**
   * Simulates a user selecting an item in this fixture's <code>{@link JComboBox}</code>.
   * @param index the index of the item to select.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JComboBox</code>.
   */
  public JComboBoxFixture selectItem(int index) {
    driver.selectItem(target, index);
    return this;
  }

  /**
   * Simulates a user selecting an item in this fixture's <code>{@link JComboBox}</code>. The text of the item to
   * select must match the given <code>String</code>. Such text is retrieved by this fixture's
   * <code>{@link JComboBoxCellReader}</code>.
   * @param text the text of the item to select. It can be a regular expression.
   * @return this fixture.
   * @throws LocationUnavailableException if an element matching the given text cannot be found.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @see #cellReader(JComboBoxCellReader)
   */
  public JComboBoxFixture selectItem(String text) {
    driver.selectItem(target, text);
    return this;
  }

  /**
   * Simulates a user selecting an item in this fixture's <code>{@link JComboBox}</code>. The text of the item to
   * select must match the given regular expression pattern. Such text is retrieved by this fixture's
   * <code>{@link JComboBoxCellReader}</code>.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws LocationUnavailableException if an element matching the given pattern cannot be found.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @see #cellReader(JComboBoxCellReader)
   * @since 1.2
   */
  public JComboBoxFixture selectItem(Pattern pattern) {
    driver.selectItem(target, pattern);
    return this;
  }

  /**
   * Returns the <code>String</code> representation of the value of an item in this fixture's
   * <code>{@link JComboBox}</code>, using this fixture's <code>{@link JComboBoxCellReader}</code>.
   * @param index the index of the item to return.
   * @return the <code>String</code> representation of the value of an item in this fixture's <code>JComboBox</code>.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JComboBox</code>.
   * @see #cellReader(JComboBoxCellReader)
   */
  public String valueAt(int index) {
    return driver.value(target, index);
  }

  /**
   * Asserts that this fixture's <code>{@link JComboBox}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JComboBox</code> is disabled.
   */
  public JComboBoxFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }
  /**
   * Asserts that this fixture's <code>{@link JComboBox}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JComboBox</code> does not have input focus.
   */
  public JComboBoxFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }


  /**
   * Asserts that this fixture's <code>{@link JComboBox}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws org.fest.swing.exception.WaitTimedOutError if this fixture's <code>JComboBox</code> is never enabled.
   */
  public JComboBoxFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JComboBox}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JComboBox</code> is enabled.
   */
  public JComboBoxFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JComboBox}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JComboBox</code> is not visible.
   */
  public JComboBoxFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JComboBox}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JComboBox</code> is visible.
   */
  public JComboBoxFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JComboBox}</code> is editable.
   * @throws AssertionError if this fixture's <code>JComboBox</code> is not editable.
   * @return this fixture.
   */
  public JComboBoxFixture requireEditable() {
    driver.requireEditable(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JComboBox}</code> is not editable.
   * @throws AssertionError if this fixture's <code>JComboBox</code> is editable.
   * @return this fixture.
   */
  public JComboBoxFixture requireNotEditable() {
    driver.requireNotEditable(target);
    return this;
  }

  /**
   * Verifies that the <code>String</code> representation of the selected item in this fixture's
   * <code>{@link JComboBox}</code> matches the given text.
   * @param value the text to match. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the selected item does not match the given text.
   * @see #cellReader(JComboBoxCellReader)
   */
  public JComboBoxFixture requireSelection(String value) {
    driver.requireSelection(target, value);
    return this;
  }

  /**
   * Verifies that this fixture's <code>{@link JComboBox}</code> has the expected number of items
   * @param expected the expected number of items.
   * @return this fixture.
   * @throws AssertionError if the number of items in this fixture's <code>JComboBox</code> is not equal to the expected
   * one.
   * @since 1.2
   */
  public JComboBoxFixture requireItemCount(int expected) {
    driver.requireItemCount(target, expected);
    return this;
  }

  /**
   * Verifies that the <code>String</code> representation of the selected item in this fixture's
   * <code>{@link JComboBox}</code> matches the given regular expression pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the selected item does not match the given regular expression pattern.
   * @see #cellReader(JComboBoxCellReader)
   * @since 1.2
   */
  public JComboBoxFixture requireSelection(Pattern pattern) {
    driver.requireSelection(target, pattern);
    return this;
  }

  /**
   * Verifies that the index of the selected item in this fixture's <code>{@link JComboBox}</code> is equal to the given
   * value.
   * @param index the expected selection index.
   * @return this fixture.
   * @throws AssertionError if the selected index is not equal to the given one.
   * @since 1.2
   */
  public JComboBoxFixture requireSelection(int index) {
    driver.requireSelection(target, index);
    return this;
  }

  /**
   * Verifies that this fixture's <code>{@link JComboBox}</code> does not have any selection.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JComboBox</code> has a selection.
   */
  public JComboBoxFixture requireNoSelection() {
    driver.requireNoSelection(target);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JComboBox}</code> matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's <code>JComboBox</code> does not match the given value.
   * @since 1.2
   */
  public JComboBoxFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JComboBox}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the toolTip in this fixture's <code>JComboBox</code> does not match the given regular
   * expression pattern.
   * @since 1.2
   */
  public JComboBoxFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's <code>{@link JComboBox}</code>, under the given key.
   * @param key the key to use to retrieve the client property.
   * @return the value of the client property stored under the given key, or <code>null</code> if the property was
   * not found.
   * @throws NullPointerException if the given key is <code>null</code>.
   * @since 1.2
   */
  public Object clientProperty(Object key) {
    return driver.clientProperty(target, key);
  }

  /**
   * Shows a pop-up menu using this fixture's <code>{@link JComboBox}</code> as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's <code>{@link JComboBox}</code> as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JComboBox</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }

  /**
   * Updates the implementation of <code>{@link JComboBoxCellReader}</code> to use when comparing internal values
   * of this fixture's <code>{@link JComboBox}</code> and the values expected in a test. The default implementation to
   * use is <code>{@link BasicJComboBoxCellReader}</code>.
   * @param cellReader the new <code>JComboBoxCellValueReader</code> to use.
   * @return this fixture.
   * @throws NullPointerException if <code>cellReader</code> is <code>null</code>.
   */
  public JComboBoxFixture cellReader(JComboBoxCellReader cellReader) {
    driver.cellReader(cellReader);
    return this;
  }
}
