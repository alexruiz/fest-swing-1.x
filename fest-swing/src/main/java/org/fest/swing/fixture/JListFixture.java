/*
 * Created on Jun 12, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.core.MouseButton.LEFT_BUTTON;

import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.JList;

import org.fest.swing.cell.JListCellReader;
import org.fest.swing.core.*;
import org.fest.swing.driver.*;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;
import org.fest.swing.util.Range;

/**
 * Understands functional testing of <code>{@link JList}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * <p>
 * The conversion between the values given in tests and the values being displayed by a <code>{@link JList}</code>
 * renderer is performed by a <code>{@link JListCellReader}</code>. This fixture uses a
 * <code>{@link BasicJListCellReader}</code> by default.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author Fabien Barbero
 */
public class JListFixture extends ComponentFixture<JList> implements CommonComponentFixture,
  ItemGroupFixture, JComponentFixture, JPopupMenuInvokerFixture {

  private JListDriver driver;

  /**
   * Creates a new <code>{@link JListFixture}</code>.
   * @param robot performs simulation of user events on a <code>JList</code>.
   * @param listName the name of the <code>JList</code> to find using the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is {@code null}.
   * @throws ComponentLookupException if a matching <code>JList</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JList</code> is found.
   */
  public JListFixture(Robot robot, String listName) {
    super(robot, listName, JList.class);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JListFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JList</code>.
   * @param target the <code>JList</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is {@code null}.
   * @throws NullPointerException if <code>target</code> is {@code null}.
   */
  public JListFixture(Robot robot, JList target) {
    super(robot, target);
    createDriver();
  }

  private void createDriver() {
    driver(new JListDriver(robot));
  }

  /**
   * Sets the <code>{@link JListDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JListDriver</code>.
   * @throws NullPointerException if the given driver is {@code null}.
   */
  protected final void driver(JListDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Returns the <code>String</code> representation of the value of an item in this fixture's
   * <code>{@link JList}</code>, using this fixture's <code>{@link JListCellReader}</code>.
   * @param index the index of the item to return.
   * @return the <code>String</code> representation of the value of an item in this fixture's <code>JList</code>.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   * @see #cellReader(JListCellReader)
   */
  public String valueAt(int index) {
    return driver.value(target, index);
  }

  /**
   * Returns the <code>String</code> representation of the elements in this fixture's <code>{@link JList}</code>,
   * using this fixture's <code>{@link JListCellReader}</code>.
   * @return the <code>String</code> representation of the elements in this fixture's <code>JList</code>.
   * @see #cellReader(JListCellReader)
   */
  public String[] contents() {
    return driver.contentsOf(target);
  }

  /**
   * Returns the <code>String</code> representation of the selected elements in this fixture's
   * <code>{@link JList}</code>, using this fixture's <code>{@link JListCellReader}</code>.
   * @return the <code>String</code> representation of the selected elements in this fixture's <code>JList</code>.
   * @see #cellReader(JListCellReader)
   */
  public String[] selection() {
    return driver.selectionOf(target);
  }

  /**
   * Returns a fixture that manages the list item specified by the given index.
   * @param index of the item.
   * @return a fixture that manages the list item specified by the given index.
   * @throws IndexOutOfBoundsException if the index is out of bounds.
   */
  public JListItemFixture item(int index) {
    return new JListItemFixture(this, index);
  }

  /**
   * Returns a fixture that manages the list item specified by the given text.
   * @param text the text of the item. It can be a regular expression.
   * @return a fixture that manages the list item specified by the given text.
   * @throws LocationUnavailableException if an element matching the given text cannot be found.
   */
  public JListItemFixture item(String text) {
    return new JListItemFixture(this, driver.indexOf(target, text));
  }

  /**
   * Returns a fixture that manages the list item whose text matches the given regular expression pattern.
   * @param pattern the regular expression pattern to match.
   * @return a fixture that manages the list item whose text matches the given regular expression pattern.
   * @throws LocationUnavailableException if an element matching the given text cannot be found.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @since 1.2
   */
  public JListItemFixture item(Pattern pattern) {
    return new JListItemFixture(this, driver.indexOf(target, pattern));
  }

  /**
   * Clears the selection in this fixture's <code>{@link JList}</code>. Since this method does not simulate user input,
   * it does not verifies that this fixture's <code>JList</code> is enabled and showing.
   * @return this fixture.
   * @since 1.2
   */
  public JListFixture clearSelection() {
    driver.clearSelection(target);
    return this;
  }

  /**
   * Simulates a user selecting the items (in the specified range) in this fixture's <code>{@link JList}</code>.
   * @param from the starting point of the selection.
   * @param to the last item to select (inclusive.)
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the any index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   */
  public JListFixture selectItems(Range.From from, Range.To to) {
    driver.selectItems(target, from, to);
    return this;
  }

  /**
   * Simulates a user selecting the specified items in this fixture's <code>{@link JList}</code>.
   * @param indices the indices of the items to select.
   * @return this fixture.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws IndexOutOfBoundsException if any of the indices is negative or greater than the index of the last item in
   * the <code>JList</code>.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   */
  public JListFixture selectItems(int...indices) {
    driver.selectItems(target, indices);
    return this;
  }

  /**
   * Simulates a user selecting the specified items in this fixture's <code>{@link JList}</code>. The items to select
   * should match the given values.
   * @param items the text of the items to select. Each <code>String</code> can be a regular expression.
   * @return this fixture.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the any of the given values cannot be found.
   * @see #cellReader(JListCellReader)
   */
  public JListFixture selectItems(String...items) {
    driver.selectItems(target, items);
    return this;
  }

  /**
   * Simulates a user selecting the specified items in this fixture's <code>{@link JList}</code>. The items to select
   * should select the given regular expression patterns.
   * @param patterns the regular expression patterns to match.
   * @return this fixture.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws NullPointerException if any of the regular expression patterns is {@code null}.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the any of the given regular expression patterns cannot
   * be found.
   * @see #cellReader(JListCellReader)
   * @since 1.2
   */
  public JListFixture selectItems(Pattern... patterns) {
    driver.selectItems(target, patterns);
    return this;
  }

  /**
   * Simulates a user selecting an item in this fixture's <code>{@link JList}</code>.
   * @param index the index of the item to select.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   * @see #item(int)
   * @see JListItemFixture#select()
   */
  public JListFixture selectItem(int index) {
    driver.selectItem(target, index);
    return this;
  }

  /**
   * Simulates a user selecting an item in this fixture's <code>{@link JList}</code>.
   * @param text the text of the item to select. It can be a regular expression.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given text cannot be found.
   * @see #item(String)
   * @see JListItemFixture#select()
   * @see #cellReader(JListCellReader)
   */
  public JListFixture selectItem(String text) {
    driver.selectItem(target, text);
    return this;
  }

  /**
   * Simulates a user selecting an item in this fixture's <code>{@link JList}</code>. The value of the item to select
   * must match the given regular expression pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given text cannot be found.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @see #item(Pattern)
   * @see JListItemFixture#select()
   * @see #cellReader(JListCellReader)
   * @since 1.2
   */
  public JListFixture selectItem(Pattern pattern) {
    driver.selectItem(target, pattern);
    return this;
  }

  /**
   * Simulates a user clicking an item in this fixture's <code>{@link JList}</code>.
   * @param index the index of the item to clicking.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   * @see #item(int)
   * @see JListItemFixture#click()
   * @since 1.2
   */
  public JListFixture clickItem(int index) {
    driver.clickItem(target, index, LEFT_BUTTON, 1);
    return this;
  }

  /**
   * Simulates a user clicking an item in this fixture's <code>{@link JList}</code>.
   * @param text the text of the item to select. It can be a regular expression.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given text cannot be found.
   * @see #item(String)
   * @see JListItemFixture#select()
   * @see #cellReader(JListCellReader)
   * @since 1.2
   */
  public JListFixture clickItem(String text) {
    driver.clickItem(target, text, LEFT_BUTTON, 1);
    return this;
  }

  /**
   * Simulates a user clicking an item in this fixture's <code>{@link JList}</code>. The value of the item to select
   * must match the given regular expression pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given text cannot be found.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @see #item(Pattern)
   * @see JListItemFixture#select()
   * @see #cellReader(JListCellReader)
   * @since 1.2
   */
  public JListFixture clickItem(Pattern pattern) {
    driver.clickItem(target, pattern, LEFT_BUTTON, 1);
    return this;
  }

  /**
   * Simulates a user double-clicking an item in this fixture's <code>{@link JList}</code>.
   * @param index the index of the item to double-click.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in
   * the <code>JList</code>.
   * @deprecated to be removed in version 2.0. Use <code>{@link #item(int)}</code> and
   * <code>{@link JListItemFixture#doubleClick()}</code> instead.
   */
  @Deprecated
  public JListFixture doubleClickItem(int index) {
    clickItem(index, LEFT_BUTTON, 2);
    return this;
  }

  /**
   * Simulates a user double-clicking an item in this fixture's <code>{@link JList}</code>.
   * @param text the text of the item to double-click.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given <code>String</code> cannot be found.
   * @deprecated to be removed in version 2.0. Use <code>{@link #item(String)}</code> and
   * <code>{@link JListItemFixture#doubleClick()}</code> instead.
   */
  @Deprecated
  public JListFixture doubleClickItem(String text) {
    driver.clickItem(target, text, LEFT_BUTTON, 2);
    return this;
  }

  void clickItem(int index, MouseButton button, int times) {
    driver.clickItem(target, index, button, times);
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JList}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   */
  public JListFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JList}</code>.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseButton</code> is {@code null}.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   */
  public JListFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JList}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is {@code null}.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   */
  public JListFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JList}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   */
  public JListFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JList}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   */
  public JListFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JList}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   */
  public JListFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JList}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is {@code null}.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @see KeyPressInfo
   */
  public JListFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's <code>{@link JList}</code>.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JListFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JList}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JListFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JList}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JListFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a drag operation at the location of the first item in this fixture's <code>{@link JList}</code> matching
   * the given value.
   * @param text the text of the item to drag. It can be a regular expression.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given text cannot be found.
   * @return this fixture.
   * @see #cellReader(JListCellReader)
   */
  public JListFixture drag(String text) {
    driver.drag(target, text);
    return this;
  }

  /**
   * Ends a drag operation at the location of the first item matching the given value.
   * @param text the text of the item to drop. It can be a regular expression.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws LocationUnavailableException if an element matching the given text cannot be found.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  public JListFixture drop(String text) {
    driver.drop(target, text);
    return this;
  }

  /**
   * Simulates a drag operation at the location of the first item in this fixture's <code>{@link JList}</code> matching
   * the given regular expression pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws NullPointerException if the given regular expression pattern in {@code null}.
   * @throws LocationUnavailableException if an element matching the given regular expression pattern cannot be found.
   * @see #cellReader(JListCellReader)
   * @since 1.2
   */
  public JListFixture drag(Pattern pattern) {
    driver.drag(target, pattern);
    return this;
  }

  /**
   * Ends a drag operation at the location of the first item matching the given regular expression pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws NullPointerException if the given regular expression pattern in {@code null}.
   * @throws LocationUnavailableException if an element matching the given text cannot be found.
   * @throws ActionFailedException if there is no drag action in effect.
   * @see #cellReader(JListCellReader)
   * @since 1.2
   */
  public JListFixture drop(Pattern pattern) {
    driver.drop(target, pattern);
    return this;
  }

  /**
   * Simulates a user dropping an item at the center of this fixture's <code>{@link JList}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  public JListFixture drop() {
    driver.drop(target);
    return this;
  }

  /**
   * Simulates a user dragging an item from this fixture's <code>{@link JList}</code>.
   * @param index the index of the item to drag.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   */
  public JListFixture drag(int index) {
    driver.drag(target, index);
    return this;
  }

  /**
   * Simulates a user dropping an item to this fixture's <code>{@link JList}</code>.
   * @param index the index of the item to drop.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  public JListFixture drop(int index) {
    driver.drop(target, index);
    return this;
  }

  /**
   * Shows a pop-up menu at the location of the specified item in this fixture's <code>{@link JList}</code>.
   * @param index the index of the item.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   * @throws IndexOutOfBoundsException if the given index is negative or greater than the index of the last item in the
   * <code>JList</code>.
   */
  public JPopupMenuFixture showPopupMenuAt(int index) {
    return new JPopupMenuFixture(robot, driver.showPopupMenu(target, index));
  }

  /**
   * Shows a pop-up menu at the location of the first item matching the given value in this fixture's
   * <code>{@link JList}</code>.
   * @param text the text of the item. It can be a regular expression.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(String text) {
    return new JPopupMenuFixture(robot, driver.showPopupMenu(target, text));
  }

  /**
   * Shows a pop-up menu at the location of the first item matching the given regular expression pattern in this
   * fixture's <code>{@link JList}</code>.
   * @param pattern the regular expression pattern to match.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   * @throws LocationUnavailableException if an element matching the given value cannot be found.
   * @since 1.2
   */
  public JPopupMenuFixture showPopupMenuAt(Pattern pattern) {
    return new JPopupMenuFixture(robot, driver.showPopupMenu(target, pattern));
  }

  /**
   * Asserts that this fixture's <code>{@link JList}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JList</code> does not have input focus.
   */
  public JListFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JList}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JList</code> is disabled.
   */
  public JListFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JList}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's <code>JList</code> is never enabled.
   */
  public JListFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JList}</code> is not enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JList</code> is enabled.
   */
  public JListFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JList}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JList</code> is not visible.
   */
  public JListFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JList}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JList</code> is visible.
   */
  public JListFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Verifies that the <code>String</code> representation of the selected item in this fixture's
   * <code>{@link JList}</code> matches the given text.
   * @param text the text to match. It can be a regular expression pattern.
   * @return this fixture.
   * @throws AssertionError if the selected item does not match the given text.
   * @see #cellReader(JListCellReader)
   */
  public JListFixture requireSelection(String text) {
    driver.requireSelection(target, text);
    return this;
  }

  /**
   * Verifies that the <code>String</code> representation of the selected item in this fixture's
   * <code>{@link JList}</code> matches the given regular expression pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws AssertionError if the selected item does not match the given regular expression pattern.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @see #cellReader(JListCellReader)
   * @since 1.2
   */
  public JListFixture requireSelection(Pattern pattern) {
    driver.requireSelection(target, pattern);
    return this;
  }

  /**
   * Verifies that the index of the selected item in this fixture's <code>{@link JList}</code> is equal to the given
   * value.
   * @param index the expected selection index.
   * @return this fixture.
   * @throws AssertionError if the selected index is not equal to the given one.
   * @since 1.2
   */
  public JListFixture requireSelection(int index) {
    driver.requireSelection(target, index);
    return this;
  }

  /**
   * Verifies that the <code>String</code> representations of the selected items in this fixture's
   * <code>{@link JList}</code> match the given text items.
   * @param items text items to match. Each <code>String</code> can be a regular expression.
   * @return this fixture.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws AssertionError if the selected items do not match the given text items.
   * @see #cellReader(JListCellReader)
   */
  public JListFixture requireSelectedItems(String... items) {
    driver.requireSelectedItems(target, items);
    return this;
  }

  /**
   * Verifies that the <code>String</code> representations of the selected items in this fixture's
   * <code>{@link JList}</code> match the given regular expression patterns.
   * @param patterns the regular expression patterns to match.
   * @return this fixture.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws NullPointerException if any of the patterns in the given array is {@code null}.
   * @throws AssertionError if the selected items do not match the given regular expression patterns.
   * @see #cellReader(JListCellReader)
   * @since 1.2
   */
  public JListFixture requireSelectedItems(Pattern[] patterns) {
    driver.requireSelectedItems(target, patterns);
    return this;
  }

  /**
   * Verifies that the given item indices are selected in this fixture's <code>{@link JList}</code>.
   * @param indices the expected indices of the selected items.
   * @return this fixture.
   * @throws NullPointerException if the given array is {@code null}.
   * @throws IllegalArgumentException if the given array is empty.
   * @throws AssertionError if the selection in this fixture's <code>JList</code> does not match the given one.
   * @since 1.2
   */
  public JListFixture requireSelectedItems(int... indices) {
    driver.requireSelectedItems(target, indices);
    return this;
  }

  /**
   * Verifies that this fixture's <code>{@link JList}</code> does not have any selection.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JList</code> has a selection.
   */
  public JListFixture requireNoSelection() {
    driver.requireNoSelection(target);
    return this;
  }

  /**
   * Verifies that this fixture's <code>{@link JList}</code> has the expected number of items
   * @param expected the expected number of items.
   * @return this fixture.
   * @throws AssertionError if the number of items in this fixture's <code>JList</code> is not equal to the expected
   * one.
   * @since 1.2
   */
  public JListFixture requireItemCount(int expected) {
    driver.requireItemCount(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JList}</code> matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's <code>JList</code> does not match the given value.
   * @since 1.2
   */
  public JListFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JList}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws AssertionError if the toolTip in this fixture's <code>JList</code> does not match the given regular
   * expression pattern.
   * @since 1.2
   */
  public JListFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's <code>{@link JList}</code>, under the given key.
   * @param key the key to use to retrieve the client property.
   * @return the value of the client property stored under the given key, or {@code null} if the property was
   * not found.
   * @throws NullPointerException if the given key is {@code null}.
   * @since 1.2
   */
  public Object clientProperty(Object key) {
    return driver.clientProperty(target, key);
  }

  /**
   * Shows a pop-up menu using this fixture's <code>{@link JList}</code> as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's <code>{@link JList}</code> as the invoker of the pop-up
   * menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JList</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JList</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }

  /**
   * Updates the implementation of <code>{@link JListCellReader}</code> to use when comparing internal values of
   * this fixture's <code>{@link JList}</code> and the values expected in a test. The default implementation to use
   * is <code>{@link BasicJListCellReader}</code>.
   * @param cellReader the new <code>JListCellValueReader</code> to use.
   * @return this fixture.
   * @throws NullPointerException if <code>cellReader</code> is {@code null}.
   */
  public JListFixture cellReader(JListCellReader cellReader) {
    driver.cellReader(cellReader);
    return this;
  }
}
