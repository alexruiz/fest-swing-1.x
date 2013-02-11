/*
 * Created on May 21, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.tree.TreePath;

import org.fest.swing.cell.JTreeCellReader;
import org.fest.swing.core.*;
import org.fest.swing.driver.*;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of {@code JTree}s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * <p>
 * {@code TreePath}s can be specified using {@code String}s. For example, for the following
 * tree:
 *
 * <pre>
 * root
 *   |
 *   -- node1
 *      |
 *      -- node1.1
 * </pre>
 *
 * we can identify the node "node1.1" as follows:
 *
 * <pre>
 *   root/node1/node1.1
 * </pre>
 *
 * </p>
 * <p>
 * The default path separator is "/". It can be changed by calling {@link #separator(String)}.
 * </p>
 * <p>
 * The conversion between the values given in tests and the values being displayed by a {@code JTree}
 * renderer is performed by a {@link JTreeCellReader}. This fixture uses a
 * {@link BasicJTreeCellReader} by default.
 * </p>
 *
 * @author Keith Coughtrey
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author Fabien Barbero
 */
public class JTreeFixture extends AbstractComponentFixture<JTree> implements CommonComponentFixture,
    AbstractJComponentFixture, JPopupMenuInvokerFixture {
  private JTreeDriver driver;

  /**
   * Creates a new {@link JTreeFixture}.
   * @param robot performs simulation of user events on the given {@code JTree}.
   * @param target the {@code JTree} to be managed by this fixture.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws NullPointerException if {@code target} is {@code null}.
   */
  public JTreeFixture(Robot robot, JTree target) {
    super(robot, target);
    createDriver();
  }

  /**
   * Creates a new {@link JTreeFixture}.
   * @param robot performs simulation of user events on a {@code JTree}.
   * @param treeName the name of the {@code JTree} to find using the given {@code Robot}.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws ComponentLookupException if a matching {@code JTree} could not be found.
   * @throws ComponentLookupException if more than one matching {@code JTree} is found.
   */
  public JTreeFixture(Robot robot, String treeName) {
    super(robot, treeName, JTree.class);
    createDriver();
  }

  private void createDriver() {
    driver(new JTreeDriver(robot));
  }

  /**
   * Sets the {@link JTreeDriver} to be used by this fixture.
   * @param newDriver the new {@code JTreeDriver}.
   * @throws NullPointerException if the given driver is {@code null}.
   */
  protected final void driver(JTreeDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Clicks the given row.
   * @param row the given row.
   * @return this fixture.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   * @since 1.2
   */
  public JTreeFixture clickRow(int row) {
    driver.clickRow(target, row);
    return this;
  }


  /**
   * Clicks the given row.
   * @param row the given row.
   * @param button the mouse button to use.
   * @return this fixture.
   * @throws NullPointerException if the button is {@code null}.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   * @since 1.2
   */
  public JTreeFixture clickRow(int row, MouseButton button) {
    driver.clickRow(target, row, button);
    return this;
  }


  /**
   * Clicks the given row.
   * @param row the given row.
   * @param mouseClickInfo specifies the mouse button to use and how many times to click.
   * @return this fixture.
   * @throws NullPointerException if the given {@code MouseClickInfo} is {@code null}.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   * @since 1.2
   */
  public JTreeFixture clickRow(int row, MouseClickInfo mouseClickInfo) {
    driver.clickRow(target, row, mouseClickInfo);
    return this;
  }

  /**
   * Clicks the given path, expanding parent nodes if necessary.
   * @param path the given path.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws LocationUnavailableException if the given path cannot be found.
   */
  public JTreeFixture clickPath(String path) {
    driver.clickPath(target, path);
    return this;
  }

  /**
   * Clicks the given path, expanding parent nodes if necessary.
   * @param path the given path.
   * @param button the mouse button to use.
   * @return this fixture.
   * @throws NullPointerException if the button is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws LocationUnavailableException if the given path cannot be found.
   * @since 1.2
   */
  public JTreeFixture clickPath(String path, MouseButton button) {
    driver.clickPath(target, path, button);
    return this;
  }

  /**
   * Clicks the given path, expanding parent nodes if necessary.
   * @param path the given path.
   * @param mouseClickInfo specifies the mouse button to use and how many times to click.
   * @return this fixture.
   * @throws NullPointerException if the given {@code MouseClickInfo} is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws LocationUnavailableException if the given path cannot be found.
   * @since 1.2
   */
  public JTreeFixture clickPath(String path, MouseClickInfo mouseClickInfo) {
    driver.clickPath(target, path, mouseClickInfo);
    return this;
  }

  /**
   * Double-clicks the given row.
   * @param row the given row.
   * @return this fixture.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   * @since 1.2
   */
  public JTreeFixture doubleClickRow(int row) {
    driver.doubleClickRow(target, row);
    return this;
  }

  /**
   * Double-clicks the given path.
   * @param path the given path.
   * @return this fixture.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   * @throws LocationUnavailableException if the given path cannot be found.
   * @since 1.2
   */
  public JTreeFixture doubleClickPath(String path) {
    driver.doubleClickPath(target, path);
    return this;
  }

  /**
   * Right-clicks the given row.
   * @param row the given row.
   * @return this fixture.
   * @throws IllegalStateException if the {@code JTree} is disabled.
   * @throws IllegalStateException if the {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   * @since 1.2
   */
  public JTreeFixture rightClickRow(int row) {
    driver.rightClickRow(target, row);
    return this;
  }

  /**
   * Right-clicks the given path, expanding parent nodes if necessary.
   * @param path the given path.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws LocationUnavailableException if the given path cannot be found.
   * @since 1.2
   */
  public JTreeFixture rightClickPath(String path) {
    driver.rightClickPath(target, path);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code JTree}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   */
  public JTreeFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code JTree}.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given {@code MouseButton} is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   */
  public JTreeFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@code JTree}.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given {@code MouseClickInfo} is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   */
  public JTreeFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's {@code JTree}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   */
  public JTreeFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's {@code JTree}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   */
  public JTreeFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Simulates a user dragging a row from this fixture's {@code JTree}.
   * @param row the index of the row to drag.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   */
  public JTreeFixture drag(int row) {
    driver.drag(target, row);
    return this;
  }

  /**
   * Simulates a user dragging an item from this fixture's {@code JTree}.
   * @param path the path corresponding to the item to drag.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws LocationUnavailableException if the given path cannot be found.
   */
  public JTreeFixture drag(String path) {
    driver.drag(target, path);
    return this;
  }

  /**
   * Simulates a user dropping an item into this fixture's {@code JTree}.
   * @param row the row to drop the item to.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  public JTreeFixture drop(int row) {
    driver.drop(target, row);
    return this;
  }

  /**
   * Simulates a user dropping an item into this fixture's {@code JTree}.
   * @param path the path corresponding to the item relative to the drop point.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws LocationUnavailableException if the given path cannot be found.
   * @throws ActionFailedException if there is no drag action in effect.
   */
  public JTreeFixture drop(String path) {
    driver.drop(target, path);
    return this;
  }

  /**
   * Selects the given path, expanding parent nodes if necessary. Unlike {@link #clickPath(String)},
   * this method will not click the path if it is already selected.
   * @param path the path to select.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws LocationUnavailableException if the given path cannot be found.
   */
  public JTreeFixture selectPath(String path) {
    driver.selectPath(target, path);
    return this;
  }

  /**
   * Select the given paths, expanding parent nodes if necessary.
   * @param paths the paths to select.
   * @return this fixture.
   * @throws NullPointerException if the array of rows is {@code null}.
   * @throws IllegalArgumentException if the array of rows is empty.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws LocationUnavailableException if the any of the given paths cannot be found.
   */
  public JTreeFixture selectPaths(String... paths) {
    driver.selectPaths(target, paths);
    return this;
  }

  /**
   * Simulates a user selecting the tree node at the given row.
   * @param row the index of the row to select.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   */
  public JTreeFixture selectRow(int row) {
    driver.selectRow(target, row);
    return this;
  }

  /**
   * Simulates a user selecting the tree nodes at the given rows.
   * @param rows the indices of the rows to select.
   * @return this fixture.
   * @throws NullPointerException if the array of rows is {@code null}.
   * @throws IllegalArgumentException if the array of rows is empty.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for any of the given rows cannot be found.
   */
  public JTreeFixture selectRows(int... rows) {
    driver.selectRows(target, rows);
    return this;
  }

  /**
   * Simulates a user toggling the open/closed state of the tree node at the given row.
   * @param row the index of the row to toggle.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   * @throws ActionFailedException if this method fails to toggle the row.
   */
  public JTreeFixture toggleRow(int row) {
    driver.toggleRow(target, row);
    return this;
  }

  /**
   * Simulates a user expanding the tree node at the given row.
   * @param row the index of the row to expand.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   * @throws ActionFailedException if this method fails to expand the row.
   * @since 1.2
   */
  public JTreeFixture expandRow(int row) {
    driver.expandRow(target, row);
    return this;
  }

  /**
   * Simulates a user collapsing the tree node at the given row.
   * @param row the index of the row to collapse.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   * @throws ActionFailedException if this method fails to collapse the row.
   * @since 1.2
   */
  public JTreeFixture collapseRow(int row) {
    driver.collapseRow(target, row);
    return this;
  }

  /**
   * Simulates a user expanding the tree node at the given path.
   * @param path the path of the row to expand.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws LocationUnavailableException if the given path cannot be found.
   * @throws ActionFailedException if this method fails to expand the path.
   * @since 1.2
   */
  public JTreeFixture expandPath(String path) {
    driver.expandPath(target, path);
    return this;
  }

  /**
   * Simulates a user collapsing the tree node at the given path.
   * @param path the path of the row to collapse.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws LocationUnavailableException if the given path cannot be found.
   * @throws ActionFailedException if this method fails to collapse the path.
   * @since 1.2
   */
  public JTreeFixture collapsePath(String path) {
    driver.collapsePath(target, path);
    return this;
  }

  /**
   * Shows a pop-up menu at the position of the node in the given row.
   * @param row the index of the row invoking the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(int row) {
    JPopupMenu popupMenu = driver.showPopupMenu(target, row);
    return new JPopupMenuFixture(robot, popupMenu);
  }

  /**
   * Shows a pop-up menu at the position of the last node in the given path. The last node in the given path will be
   * made visible (by expanding the parent node(s)) if it is not visible.
   * @param path the path of the node invoking the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   * @throws LocationUnavailableException if the given path cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(String path) {
    JPopupMenu popupMenu = driver.showPopupMenu(target, path);
    return new JPopupMenuFixture(robot, popupMenu);
  }

  /**
   * Gives input focus to this fixture's {@code JTree}.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   */
  public JTreeFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's {@code JTree}.
   * Modifiers is a mask from the available {@link java.awt.event.InputEvent} masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given {@code KeyPressInfo} is {@code null}.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @see KeyPressInfo
   */
  public JTreeFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys in this fixture's {@code JTree}.
   * @param keyCodes the codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JTreeFixture pressAndReleaseKeys(int...keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's {@code JTree}.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JTreeFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's {@code JTree}.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @see java.awt.event.KeyEvent
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   */
  public JTreeFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JTree} has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JTree} does not have input focus.
   */
  public JTreeFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JTree} is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JTree} is disabled.
   */
  public JTreeFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JTree} is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws org.fest.swing.exception.WaitTimedOutError if this fixture's {@code JTree} is never enabled.
   */
  public JTreeFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JTree} is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JTree} is enabled.
   */
  public JTreeFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JTree} is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JTree} is not visible.
   */
  public JTreeFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JTree} is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JTree} is visible.
   */
  public JTreeFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JTree} is editable.
   * @throws AssertionError if this fixture's {@code JTree} is not editable.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JTree} is not editable.
   */
  public JTreeFixture requireEditable() {
    driver.requireEditable(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JTree} is not editable.
   * @throws AssertionError if this fixture's {@code JTree} is editable.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JTree} is editable.
   */
  public JTreeFixture requireNotEditable() {
    driver.requireNotEditable(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JTree} selection is equal to the given paths.
   * @param paths the given paths, expected to be selected.
   * @return this fixture.
   * @throws NullPointerException if the array of paths is {@code null}.
   * @throws LocationUnavailableException if any of the given path cannot be found.
   * @throws AssertionError if this fixture's {@code JTree} selection is not equal to the given paths.
   */
  public JTreeFixture requireSelection(String... paths) {
    driver.requireSelection(target, paths);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JTree}'s selected rows are equal to the given one.
   * @param rows the indices of the rows, expected to be selected.
   * @throws NullPointerException if the array of row indices is {@code null}.
   * @throws AssertionError if this fixture's {@code JTree} selection is not equal to the given rows.
   * @return this fixture.
   */
  public JTreeFixture requireSelection(int... rows) {
    driver.requireSelection(target, rows);
    return this;
  }

  /**
   * Asserts that this fixture's {@code JTree}'s does not have any selection.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JTree} has a selection.
   */
  public JTreeFixture requireNoSelection() {
    driver.requireNoSelection(target);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's {@code JTree} matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's {@code JTree} does not match the given value.
   * @since 1.2
   */
  public JTreeFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's {@code JTree} matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws AssertionError if the toolTip in this fixture's {@code JTree} does not match the given regular
   * expression.
   * @since 1.2
   */
  public JTreeFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }


  /**
   * Returns the client property stored in this fixture's {@link JButton}, under the given key.
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
   * Shows a pop-up menu using this fixture's {@code JTree} as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's {@code JTree} as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JTree} is disabled.
   * @throws IllegalStateException if this fixture's {@code JTree} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }

  /**
   * Returns the separator to use when converting {@code TreePath}s to {@code String}s.
   * @return the separator to use when converting {@code TreePath}s to {@code String}s.
   */
  public String separator() {
    return driver.separator();
  }

  /**
   * Updates the separator to use when converting {@code TreePath}s to {@code String}s. The default
   * value is "/".
   * @param separator the new separator.
   * @return this fixture.
   * @throws NullPointerException if the given separator is {@code null}.
   */
  public JTreeFixture separator(String separator) {
    driver.separator(separator);
    return this;
  }

  /**
   * Updates the implementation of {@link JTreeCellReader} to use when comparing internal values of a
   * {@code JTree} and the values expected in a test. The default implementation to use
   * is {@link BasicJTreeCellReader}.
   * @param cellReader the new {@code JTreeCellValueReader} to use.
   * @throws NullPointerException if {@code cellReader} is {@code null}.
   * @return this fixture.
   */
  public JTreeFixture cellReader(JTreeCellReader cellReader) {
    driver.cellReader(cellReader);
    return this;
  }

  /**
   * Returns a fixture that manages the node specified by the given row.
   * @param row the given row.
   * @return a fixture that manages the node specified by the given row.
   * @throws IndexOutOfBoundsException if the given index is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @since 1.2
   */
  public JTreeNodeFixture node(int row) {
    driver.checkRowInBounds(target, row);
    return new JTreeRowFixture(this, row);
  }

  /**
   * Returns a fixture that manages the node specified by the given path.
   * @param path the given path.
   * @return a fixture that manages the node specified by the given path.
   * @throws LocationUnavailableException if the given path cannot be found.
   * @since 1.2
   */
  public JTreeNodeFixture node(String path) {
    driver.checkPathExists(target, path);
    return new JTreePathFixture(this, path);
  }

  /**
   * Returns the {@code String} representation of the node at the given path.
   * @param path the given path.
   * @return the {@code String} representation of the node at the given path.
   * @throws LocationUnavailableException if the given path cannot be found.
   * @since 1.2
   */
  public String valueAt(String path) {
    return driver.nodeValue(target, path);
  }

  /**
   * Returns the {@code String} representation of the given row.
   * @param row the given row.
   * @return the {@code String} representation of the node at the given path.
   * @throws IndexOutOfBoundsException if the given row is less than zero or equal than or greater than the number of
   * visible rows in the {@code JTree}.
   * @throws LocationUnavailableException if a tree path for the given row cannot be found.
   * @since 1.2
   */
  public String valueAt(int row) {
    return driver.nodeValue(target, row);
  }
}