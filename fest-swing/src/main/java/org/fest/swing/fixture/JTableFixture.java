/*
 * Created on Jul 12, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.ComponentDriver.propertyName;
import static org.fest.util.Strings.concat;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.JTable;
import javax.swing.table.JTableHeader;

import org.fest.assertions.Description;
import org.fest.swing.cell.JTableCellReader;
import org.fest.swing.cell.JTableCellWriter;
import org.fest.swing.core.KeyPressInfo;
import org.fest.swing.core.MouseButton;
import org.fest.swing.core.MouseClickInfo;
import org.fest.swing.core.Robot;
import org.fest.swing.data.TableCell;
import org.fest.swing.data.TableCellFinder;
import org.fest.swing.driver.BasicJTableCellReader;
import org.fest.swing.driver.BasicJTableCellWriter;
import org.fest.swing.driver.JTableDriver;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JTable}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 * <p>
 * The conversion between the values given in tests and the values being displayed by a <code>{@link JTable}</code>
 * renderer is performed by a <code>{@link JTableCellReader}</code>. This fixture uses a
 * <code>{@link BasicJTableCellReader}</code> by default.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author Fabien Barbero
 * @author Andriy Tsykholyas
 */
public class JTableFixture extends ComponentFixture<JTable> implements CommonComponentFixture,
    JComponentFixture, JPopupMenuInvokerFixture {

  private JTableDriver driver;

  /**
   * Creates a new <code>{@link JTableFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JTable</code>.
   * @param target the <code>JTable</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public JTableFixture(Robot robot, JTable target) {
    super(robot, target);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JTableFixture}</code>.
   * @param robot performs simulation of user events on a <code>JTable</code>.
   * @param tableName the name of the <code>JTable</code> to find using the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching <code>JTable</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JTable</code> is found.
   */
  public JTableFixture(Robot robot, String tableName) {
    super(robot, tableName, JTable.class);
    createDriver();
  }

  private void createDriver() {
    driver(new JTableDriver(robot));
  }

  /**
   * Sets the <code>{@link JTableDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JTableDriver</code>.
   * @throws NullPointerException if the given driver is <code>null</code>.
   */
  protected final void driver(JTableDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Returns the <code>{@link JTableDriver}</code> used by this fixture.
   * @return the <code>JTableDriver</code> used by this fixture.
   */
  protected final JTableDriver driver() {
    return driver;
  }

  /**
   * Returns a fixture that verifies the font of the given table cell.
   * @param cell the given table cell.
   * @return a fixture that verifies the font of the given table cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  public FontFixture fontAt(TableCell cell) {
    Font font = driver.font(target, cell);
    return new FontFixture(font, cellProperty(cell, FONT_PROPERTY));
  }

  /**
   * Returns a fixture that verifies the background color of the given table cell.
   * @param cell the given table cell.
   * @return a fixture that verifies the background color of the given table cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  public ColorFixture backgroundAt(TableCell cell) {
    Color background = driver.background(target, cell);
    return new ColorFixture(background, cellProperty(cell, BACKGROUND_PROPERTY));
  }

  /**
   * Returns a fixture that verifies the foreground color of the given table cell.
   * @param cell the given table cell.
   * @return a fixture that verifies the foreground color of the given table cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  public ColorFixture foregroundAt(TableCell cell) {
    Color foreground = driver.foreground(target, cell);
    return new ColorFixture(foreground, cellProperty(cell, FOREGROUND_PROPERTY));
  }

  private Description cellProperty(TableCell cell, String propertyName) {
    return propertyName(target, concat(propertyName, " ", cell));
  }

  /**
   * Returns a fixture that manages the table cell whose value matches the given one.
   * @param value the value of the cell to look for. It can be a regular expression.
   * @return a fixture that manages the table cell whose value matches the given one.
   * @throws ActionFailedException if a cell with a matching value cannot be found.
   */
  public TableCell cell(String value) {
    return driver.cell(target, value);
  }

  /**
   * Returns a fixture that manages the table cell whose value matches the given regular expression pattern.
   * @param valuePattern the regular expression pattern to match.
   * @return a fixture that manages the table cell whose value matches the given one.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws ActionFailedException if a cell with a matching value cannot be found.
   * @since 1.2
   */
  public TableCell cell(Pattern valuePattern) {
    return driver.cell(target, valuePattern);
  }

  /**
   * Returns a fixture that manages the table cell found by the given <code>{@link TableCellFinder}</code>.
   * @param cellFinder knows how to find a cell.
   * @return a fixture that manages the found table cell.
   * @throws NullPointerException if the <code>TableCellFinder</code> is <code>null</code>.
   * @throws ActionFailedException if a matching cell could not be found.
   * @throws IndexOutOfBoundsException if the row or column indices in the found cell are out of bounds.
   */
  public JTableCellFixture cell(TableCellFinder cellFinder) {
    return new JTableCellFixture(this, driver.cell(target, cellFinder));
  }

  /**
   * Returns a fixture that manages the table cell specified by the given row and column.
   * @param cell the cell of interest.
   * @return a fixture that manages the table cell specified by the given row and column.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  public JTableCellFixture cell(TableCell cell) {
    driver.validate(target, cell);
    return new JTableCellFixture(this, cell);
  }

  /**
   * Returns a <code>{@link JTableHeaderFixture}</code> wrapping the <code>{@link JTableHeader}</code> in this fixture's
   * <code>{@link JTable}</code>.
   * @return a <code>JTableHeaderFixture</code> wrapping the <code>JTableHeader</code> in this fixture's
   * <code>JTable</code>.
   * @throws AssertionError if the <code>JTableHeader</code> in this fixture's <code>JTable</code> is <code>null</code>.
   */
  public JTableHeaderFixture tableHeader() {
    JTableHeader tableHeader = driver.tableHeaderOf(target);
    assertThat(tableHeader).isNotNull();
    return new JTableHeaderFixture(robot, tableHeader);
  }

  /**
   * Returns the <code>String</code> representation of the selected cell in this fixture's <code>{@link JTable}</code>,
   * using this fixture's <code>{@link JTableCellReader}</code>. Returns <code>null</code> if one can not be obtained or
   * if the <code>{@link JTable}</code> does not have any selected cell.
   * @return the <code>String</code> representation of the selected cell.
   * @see #cellReader(JTableCellReader)
   */
  public String selectionValue() {
    return driver.selectionValue(target);
  }

  /**
   * Converts the given cell into a coordinate pair.
   * @param cell the given cell.
   * @return the coordinates of the given cell.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  public Point pointAt(TableCell cell) {
    return driver.pointAt(target, cell);
  }

  /**
   * Returns the <code>String</code> representation of the cells in the in this fixture's <code>{@link JTable}</code>,
   * using this fixture's <code>{@link JTableCellReader}</code>.
   * @return the <code>String</code> representation of the cells in thi fixture's <code>JTable</code>.
   * @see #cellReader(JTableCellReader)
   */
  public String[][] contents() {
    return driver.contents(target);
  }

  /**
   * Returns the number of rows that can be shown in this fixture's <code>{@link JTable}</code>, given unlimited space.
   * @return the number of rows shown in this fixture's <code>JTable</code>.
   * @see JTable#getRowCount()
   */
  public int rowCount() {
    return driver.rowCountOf(target);
  }

  /**
   * Returns the <code>String</code> representation of the value of a cell in this fixture's
   * <code>{@link JTable}</code>, using this fixture's <code>{@link JTableCellReader}</code>.
   * @param cell the given cell.
   * @return the <code>String</code> representation of the value of a cell in this fixture's <code>JTable</code>.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @see #cellReader(JTableCellReader)
   */
  public String valueAt(TableCell cell) {
    return driver.value(target, cell);
  }

  /**
   * Simulates a user selecting the given cell (row and column) of this fixture's <code>{@link JTable}</code>.
   * @param cell the cell to select.
   * @return this fixture.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  public JTableFixture selectCell(TableCell cell) {
    driver.selectCell(target, cell);
    return this;
  }

  /**
   * Simulates a user selecting the given cells of this fixture's <code>{@link JTable}</code>.
   * @param cells the cells to select.
   * @return this fixture.
   * @throws NullPointerException if <code>cells</code> is <code>null</code> or empty.
   * @throws IllegalArgumentException if <code>cells</code> is <code>null</code> or empty.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws NullPointerException if any element in <code>cells</code> is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices of any of the <code>cells</code> are out of bounds.
   */
  public JTableFixture selectCells(TableCell... cells) {
    driver.selectCells(target, cells);
    return this;
  }


  /**
   * Simulates a user selecting the given rows in this fixture's <code>{@link JTable}</code>.
   * @param rows the indices of the row to select.
   * @return this fixture.
   * @throws NullPointerException if the given array of indices is <code>null</code>.
   * @throws IllegalArgumentException if the given array of indices is empty.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the given indices is out of bounds.
   * @since 1.2
   */
  public JTableFixture selectRows(int... rows) {
    driver.selectRows(target, rows);
    return this;
  }

  /**
   * Simulates a user dragging an item from this fixture's <code>{@link JTable}</code>.
   * @param cell the cell to drag.
   * @return this fixture.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  public JTableFixture drag(TableCell cell) {
    driver.drag(target, cell);
    return this;
  }

  /**
   * Simulates a user dropping an item to this fixture's <code>{@link JTable}</code>.
   * @param cell the cell to drop the dragging item into.
   * @return this fixture.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  public JTableFixture drop(TableCell cell) {
    driver.drop(target, cell);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JTable}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   */
  public JTableFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JTable}</code>.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseButton</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   */
  public JTableFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JTable}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   */
  public JTableFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user clicking a cell in this fixture's <code>{@link JTable}</code> once, using the specified mouse
   * button.
   * @param cell the cell to click.
   * @param button the mouse button to use.
   * @return this fixture.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  public JTableFixture click(TableCell cell, MouseButton button) {
    click(cell, button, 1);
    return this;
  }

  /**
   * Simulates a user clicking a cell in this fixture's <code>{@link JTable}</code>, using the specified mouse button
   * the given number of times.
   * @param cell the cell to click.
   * @param mouseClickInfo specifies the mouse button to use and how many times to click.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   */
  public JTableFixture click(TableCell cell, MouseClickInfo mouseClickInfo) {
    if (mouseClickInfo == null) throw new NullPointerException("The given MouseClickInfo should not be null");
    click(cell, mouseClickInfo.button(), mouseClickInfo.times());
    return this;
  }

  void click(TableCell cell, MouseButton button, int times) {
    driver.click(target, cell, button, times);
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JTable}</code>.
   * <p>
   * <b>Note:</b> This method will not be successful if the double-clicking occurs on an editable table cell. For this
   * particular case, this method will start edition of the table cell located under the mouse pointer.
   * </p>
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   */
  public JTableFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JTable}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   */
  public JTableFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JTable}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @see KeyPressInfo
   */
  public JTableFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's <code>{@link JTable}</code>. This method
   * does not affect the current focus.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is <code>null</code>.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JTableFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JTable}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JTableFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JTable}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JTableFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JTable}</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @return this fixture.
   */
  public JTableFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Enters the given value in the given cell of this fixture's <code>{@link JTable}</code>, using this fixture's
   * <code>{@link JTableCellWriter}</code>. If you need more flexibility for editing cell, please see
   * <code>{@link JTableCellFixture#editor()}</code>.
   * @param cell the given cell.
   * @param value the given value.
   * @return this fixture.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not editable.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws ActionFailedException if this fixture's <code>JTableCellValueReader</code> is unable to enter the given
   * value.
   * @see #cellWriter(JTableCellWriter)
   * @see JTableCellFixture#editor()
   */
  public JTableFixture enterValue(TableCell cell, String value) {
    driver.enterValueInCell(target, cell, value);
    return this;
  }

  /**
   * Updates the implementation of <code>{@link JTableCellReader}</code> to use when comparing internal values of this
   * fixture's <code>{@link JTable}</code> and the values expected in a test. The default implementation to use is
   * <code>{@link BasicJTableCellReader}</code>.
   * @param cellReader the new <code>JTableCellValueReader</code> to use.
   * @throws NullPointerException if <code>cellReader</code> is <code>null</code>.
   * @return this fixture.
   */
  public JTableFixture cellReader(JTableCellReader cellReader) {
    driver.cellReader(cellReader);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTable}</code> has the given number of rows.
   * @param expected the expected number of rows.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTable</code> does not have the given number of rows.
   */
  public JTableFixture requireRowCount(int expected) {
    driver.requireRowCount(target, expected);
    return this;
  }

  /**
   * Asserts that the set of selected rows in this fixture's <code>{@link JTable}</code> contains to the given row
   * indices.
   * @param rows the indices of the rows expected to be selected.
   * @return this fixture.
   * @throws AssertionError if the set of selected rows in this fixture's <code>JTable</code> (if any) do not contain
   * the given indices.
   * @since 1.2
   */
  public JTableFixture requireSelectedRows(int... rows) {
    driver.requireSelectedRows(target, rows);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTable}</code> has the given number of columns.
   * @param expected the expected number of columns.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTable</code> does not have the given number of columns.
   */
  public JTableFixture requireColumnCount(int expected) {
    driver.requireColumnCount(target, expected);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTable}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTable</code> does not have input focus.
   */
  public JTableFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTable}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError is the managed <code>JTable</code> is disabled.
   */
  public JTableFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTable}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if the managed <code>JTable</code> is never enabled.
   */
  public JTableFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTable}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError is the managed <code>JTable</code> is enabled.
   */
  public JTableFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTable}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if the managed <code>JTable</code> is not visible.
   */
  public JTableFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTable}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if the managed <code>JTable</code> is visible.
   */
  public JTableFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JTable}</code> matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's <code>JTable</code> does not match the given value.
   * @since 1.2
   */
  public JTableFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JTable}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the toolTip in this fixture's <code>JTable</code> does not match the given regular
   * expression pattern.
   * @since 1.2
   */
  public JTableFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Asserts that the given cell in this fixture's <code>{@link JTable}</code> is editable.
   * @param cell the given cell.
   * @return this fixture.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws AssertionError if the given cell is not editable.
   */
  public JTableFixture requireEditable(TableCell cell) {
    driver.requireEditable(target, cell);
    return this;
  }

  /**
   * Asserts that the given cell in this fixture's <code>{@link JTable}</code> is not editable.
   * @param cell the given cell.
   * @return this fixture.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws AssertionError if the given cell is editable.
   */
  public JTableFixture requireNotEditable(TableCell cell) {
    driver.requireNotEditable(target, cell);
    return this;
  }

  /**
   * Verifies that this fixture's <code>{@link JTable}</code> does not have any selection.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTable</code> has a selection.
   */
  public JTableFixture requireNoSelection() {
    driver.requireNoSelection(target);
    return this;
  }

  /**
   * Asserts that the value of the given cell matches the given value.
   * @param cell the given table cell.
   * @param value the expected value. It can be a regular expression.
   * @return this fixture.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws AssertionError if the value of the given cell does not match the given value.
   */
  public JTableFixture requireCellValue(TableCell cell, String value) {
    driver.requireCellValue(target, cell, value);
    return this;
  }

  /**
   * Asserts that the value of the given cell matches the given regular expression pattern.
   * @param cell the given table cell.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws IndexOutOfBoundsException if any of the indices (row and column) is out of bounds.
   * @throws AssertionError if the value of the given cell does not match the given regular expression pattern.
   * @since 1.2
   */
  public JTableFixture requireCellValue(TableCell cell, Pattern pattern) {
    driver.requireCellValue(target, cell, pattern);
    return this;
  }

  /**
   * Asserts that the <code>String</code> representation of the cell values in this fixture's
   * <code>{@link JTable}</code> is equal to the given <code>String</code> array. This method uses this fixture's
   * <code>{@link JTableCellReader}</code> to read the values of the table cells as <code>String</code>s.
   * @param contents the expected <code>String</code> representation of the cell values in this fixture's
   * <code>JTable</code>.
   * @return this fixture.
   * @see #cellReader(JTableCellReader)
   */
  public JTableFixture requireContents(String[][] contents) {
    driver.requireContents(target, contents);
    return this;
  }

  /**
   * Updates the implementation of <code>{@link JTableCellWriter}</code> to use when comparing internal values of this
   * fixture's <code>{@link JTable}</code> and the values expected in a test. The default implementation to use is
   * <code>{@link BasicJTableCellWriter}</code>.
   * @param cellWriter the new <code>JTableCellValueWriter</code> to use.
   * @throws NullPointerException if <code>cellWriter</code> is <code>null</code>.
   * @return this fixture.
   */
  public JTableFixture cellWriter(JTableCellWriter cellWriter) {
    driver.cellWriter(cellWriter);
    return this;
  }

  /**
   * Returns the index of the column in this fixture's <code>{@link JTable}</code> whose name matches the given one.
   * @param columnName the name of the column to look for.
   * @return the index of the column whose name matches the given one.
   * @throws ActionFailedException if a column with a matching name could not be found.
   */
  public int columnIndexFor(Object columnName) {
    return driver.columnIndex(target, columnName);
  }

  /**
   * Returns the client property stored in this fixture's <code>{@link JTable}</code>, under the given key.
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
   * Shows a pop-up menu using this fixture's <code>{@link JTable}</code> as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's <code>{@link JTable}</code> as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }

  /**
   * Shows a pop-up menu at the given cell.
   * @param cell the table cell where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws NullPointerException if the cell is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTable</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(TableCell cell) {
    return new JPopupMenuFixture(robot, driver.showPopupMenuAt(target, cell));
  }
}
