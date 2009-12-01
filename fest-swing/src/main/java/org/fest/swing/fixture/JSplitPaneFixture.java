/*
 * Created on Sep 4, 2007
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

import javax.swing.JSplitPane;

import org.fest.swing.core.*;
import org.fest.swing.driver.JSplitPaneDriver;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JSplitPane}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JSplitPaneFixture extends ComponentFixture<JSplitPane> implements CommonComponentFixture,
    JComponentFixture, JPopupMenuInvokerFixture {

  private JSplitPaneDriver driver;

  /**
   * Creates a new <code>{@link JSplitPaneFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JSplitPane</code>.
   * @param target the <code>JSplitPane</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public JSplitPaneFixture(Robot robot, JSplitPane target) {
    super(robot, target);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JSplitPaneFixture}</code>.
   * @param robot performs simulation of user events on a <code>JSplitPane</code>.
   * @param spinnerName the name of the <code>JSplitPane</code> to find using the given <code>Robot</code>.
   * @throws ComponentLookupException if a matching <code>JSplitPane</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JSplitPane</code> is found.
   */
  public JSplitPaneFixture(Robot robot, String spinnerName) {
    super(robot, spinnerName, JSplitPane.class);
    createDriver();
  }

  private void createDriver() {
    driver(new JSplitPaneDriver(robot));
  }

  /**
   * Sets the <code>{@link JSplitPaneDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JSplitPaneDriver</code>.
   * @throws NullPointerException if the given driver is <code>null</code>.
   */
  protected final void driver(JSplitPaneDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Simulates a user moving the divider of this fixture's <code>{@link JSplitPane}</code>.
   * <p>
   * Since 1.2, this method respects the minimum and maximum values of the left and right components inside this
   * fixture's <code>JSplitPane</code>.
   * </p>
   * @param location the location to move the divider to.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   */
  public JSplitPaneFixture moveDividerTo(int location) {
    driver.moveDividerTo(target, location);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JSplitPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   */
  public JSplitPaneFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JSplitPane}</code>.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseButton</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   */
  public JSplitPaneFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JSplitPane}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   */
  public JSplitPaneFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JSplitPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   */
  public JSplitPaneFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JSplitPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   */
  public JSplitPaneFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JSplitPane}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   */
  public JSplitPaneFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JSplitPane}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   * @see KeyPressInfo
   */
  public JSplitPaneFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's <code>{@link JSplitPane}</code>. This
   * method does not affect the current focus.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is <code>null</code>.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JSplitPaneFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JSplitPane}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JSplitPaneFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JSplitPane}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JSplitPaneFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JSplitPane}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JSplitPane</code> does not have input focus.
   */
  public JSplitPaneFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JSplitPane}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JSplitPane</code> is disabled.
   */
  public JSplitPaneFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JSplitPane}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's <code>JSplitPane</code> is never enabled.
   */
  public JSplitPaneFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JSplitPane}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JSplitPane</code> is enabled.
   */
  public JSplitPaneFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JSplitPane}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JSplitPane</code> is not visible.
   */
  public JSplitPaneFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JSplitPane}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JSplitPane</code> is visible.
   */
  public JSplitPaneFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JSplitPane}</code> matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's <code>JSplitPane</code> does not match the given value.
   * @since 1.2
   */
  public JSplitPaneFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JSplitPane}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the toolTip in this fixture's <code>JSplitPane</code> does not match the given regular
   * expression.
   * @since 1.2
   */
  public JSplitPaneFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's <code>{@link JSplitPane}</code>, under the given key.
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
   * Shows a pop-up menu using this fixture's <code>{@link JSplitPane}</code> as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's <code>{@link JSplitPane}</code> as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JSplitPane</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }
}
