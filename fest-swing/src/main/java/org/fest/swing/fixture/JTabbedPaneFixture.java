/*
 * Created on Apr 3, 2007
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

import java.awt.*;
import java.util.regex.Pattern;

import javax.swing.JTabbedPane;

import org.fest.swing.core.*;
import org.fest.swing.core.Robot;
import org.fest.swing.data.Index;
import org.fest.swing.driver.JTabbedPaneDriver;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JTabbedPane}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JTabbedPaneFixture extends ComponentFixture<JTabbedPane> implements CommonComponentFixture,
    JComponentFixture, JPopupMenuInvokerFixture {

  private JTabbedPaneDriver driver;

  /**
   * Creates a new <code>{@link JTabbedPaneFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JTabbedPane</code>.
   * @param target the <code>JTabbedPane</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is {@code null}.
   * @throws NullPointerException if <code>target</code> is {@code null}.
   */
  public JTabbedPaneFixture(Robot robot, JTabbedPane target) {
    super(robot, target);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JTabbedPaneFixture}</code>.
   * @param robot performs simulation of user events on a <code>JTabbedPane</code>.
   * @param tabbedPaneName the name of the <code>JTabbedPane</code> to find using the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is {@code null}.
   * @throws ComponentLookupException if a matching <code>JTabbedPane</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JTabbedPane</code> is found.
   */
  public JTabbedPaneFixture(Robot robot, String tabbedPaneName) {
    super(robot, tabbedPaneName, JTabbedPane.class);
    createDriver();
  }

  private void createDriver() {
    driver(new JTabbedPaneDriver(robot));
  }

  /**
   * Sets the <code>{@link JTabbedPaneDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JTabbedPaneDriver</code>.
   * @throws NullPointerException if the given driver is {@code null}.
   */
  protected final void driver(JTabbedPaneDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Returns the titles of all the tabs in this fixture's <code>{@link JTabbedPane}</code>.
   * @return the titles of all the tabs.
   */
  public String[] tabTitles() {
    return driver.tabTitles(target);
  }

  /**
   * Simulates a user selecting the tab located at the given index.
   * @param index the index of the tab to select.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   * @throws IndexOutOfBoundsException if the given index is not within the <code>JTabbedPane</code> bounds.
   */
  public JTabbedPaneFixture selectTab(int index) {
    driver.selectTab(target, index);
    return this;
  }

  /**
   * Simulates a user selecting the tab whose title matches the given value.
   * @param title the title to match. It can be a regular expression.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   * @throws LocationUnavailableException if a tab matching the given title could not be found.
   */
  public JTabbedPaneFixture selectTab(String title) {
    driver.selectTab(target, title);
    return this;
  }


  /**
   * Simulates a user selecting the tab whose title matches the given regular expression pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws LocationUnavailableException if a tab matching the given regular expression pattern could not be found.
   * @since 1.2
   */
  public JTabbedPaneFixture selectTab(Pattern pattern) {
    driver.selectTab(target, pattern);
    return this;
  }

  /**
   * Returns the currently selected component for this fixture's <code>{@link JTabbedPane}</code>.
   * @return the currently selected component for this fixture's <code>JTabbedPane</code>.
   */
  public Component selectedComponent() {
    return driver.selectedComponentOf(target);
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JTabbedPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   */
  public JTabbedPaneFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JTabbedPane}</code>.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseButton</code> is {@code null}.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   */
  public JTabbedPaneFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JTabbedPane}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is {@code null}.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   */
  public JTabbedPaneFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JTabbedPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   */
  public JTabbedPaneFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JTabbedPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   */
  public JTabbedPaneFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JTabbedPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   */
  public JTabbedPaneFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JTabbedPane}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is {@code null}.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   * @see KeyPressInfo
   */
  public JTabbedPaneFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's <code>{@link JTabbedPane}</code>. This
   * method does not affect the current focus.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JTabbedPaneFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JTabbedPane}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JTabbedPaneFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JTabbedPane}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JTabbedPaneFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTabbedPane}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTabbedPane</code> does not have input focus.
   */
  public JTabbedPaneFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTabbedPane}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTabbedPane</code> is disabled.
   */
  public JTabbedPaneFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTabbedPane}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws org.fest.swing.exception.WaitTimedOutError if this fixture's <code>JTabbedPane</code> is never enabled.
   */
  public JTabbedPaneFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTabbedPane}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTabbedPane</code> is enabled.
   */
  public JTabbedPaneFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTabbedPane}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTabbedPane</code> is not visible.
   */
  public JTabbedPaneFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JTabbedPane}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JTabbedPane</code> is visible.
   */
  public JTabbedPaneFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Asserts that the title of the tab at the given index matches the given value.
   * @param title the expected title. It can be a regular expression.
   * @param index the index of the tab.
   * @return this fixture.
   * @throws IndexOutOfBoundsException if the given index is not within the <code>JTabbedPane</code> bounds.
   * @throws AssertionError if the title of the tab at the given index does not match the given one.
   */
  public JTabbedPaneFixture requireTitle(String title, Index index) {
    driver.requireTabTitle(target, title, index);
    return this;
  }

  /**
   * Asserts that the title of the tab at the given index matches the given regular expression pattern.
   * @param pattern the regular expression pattern to match.
   * @param index the index of the tab.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws AssertionError if the title of the tab at the given index does not match the given regular expression
   * pattern.
   */
  public JTabbedPaneFixture requireTitle(Pattern pattern, Index index) {
    driver.requireTabTitle(target, pattern, index);
    return this;
  }

  /**
   * Asserts that the tabs of this fixture's <code>{@link JTabbedPane}</code> have the given titles. The tab titles are
   * evaluated by index order, for example, the first tab is expected to have the first title in the given array, and so
   * on.
   * @param titles the expected titles.
   * @return this fixture.
   * @throws AssertionError if the title of any of the tabs is not equal to the expected titles.
   */
  public JTabbedPaneFixture requireTabTitles(String... titles) {
    driver.requireTabTitles(target, titles);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JTabbedPane}</code> matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's <code>JTabbedPane</code> does not match the given value.
   * @since 1.2
   */
  public JTabbedPaneFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JTabbedPane}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws AssertionError if the toolTip in this fixture's <code>JTabbedPane</code> does not match the given regular
   * expression pattern.
   * @since 1.2
   */
  public JTabbedPaneFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's <code>{@link JTabbedPane}</code>, under the given key.
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
   * Shows a pop-up menu using this fixture's <code>{@link JTabbedPane}</code> as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's <code>{@link JTabbedPane}</code> as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JTabbedPane</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }
}
