/*
 * Created on Nov 1, 2007
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

import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.JPanel;

import org.fest.swing.core.*;
import org.fest.swing.driver.JComponentDriver;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JPanel}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JPanelFixture extends ContainerFixture<JPanel> implements CommonComponentFixture, JComponentFixture {

  private JComponentDriver driver;

  /**
   * Creates a new <code>{@link JPanelFixture}</code>.
   * @param robot performs simulation of user events on a <code>JPanel</code>.
   * @param panelName the name of the <code>JPanel</code> to find using the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching <code>JPanel</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JPanel</code> is found.
   */
  public JPanelFixture(Robot robot, String panelName) {
    super(robot, panelName, JPanel.class);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JPanelFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JPanel</code>.
   * @param target the <code>JPanel</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public JPanelFixture(Robot robot, JPanel target) {
    super(robot, target);
    createDriver();
  }

  private void createDriver() {
    driver(new JComponentDriver(robot));
  }

  /**
   * Sets the <code>{@link JComponentDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JComponentDriver</code>.
   * @throws NullPointerException if the given driver is <code>null</code>.
   */
  protected final void driver(JComponentDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JPanel}</code>.
   * @return this fixture.
   */
  public JPanelFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JPanel}</code>.
   * @param button the button to click.
   * @return this fixture.
   */
  public JPanelFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JPanel}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
   */
  public JPanelFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JPanel}</code>.
   * @return this fixture.
   */
  public JPanelFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JPanel}</code>.
   * @return this fixture.
   */
  public JPanelFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JPanel}</code>.
   * @return this fixture.
   */
  public JPanelFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JPanel}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @see KeyPressInfo
   */
  public JPanelFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on the <code>{@link JPanel}</code> managed by this
   * fixture.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is <code>null</code>.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JPanelFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JPanel}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JPanelFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JPanel}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JPanelFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPanel}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JPanel</code> does not have input focus.
   */
  public JPanelFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPanel}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JPanel</code> is disabled.
   */
  public JPanelFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPanel}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's <code>JPanel</code> is never enabled.
   */
  public JPanelFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPanel}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JPanel</code> is enabled.
   */
  public JPanelFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPanel}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JPanel</code> is not visible.
   */
  public JPanelFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPanel}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JPanel</code> is visible.
   */
  public JPanelFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JPanel}</code> matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's <code>JPanel</code> does not match the given value.
   * @since 1.2
   */
  public JPanelFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's <code>{@link JPanel}</code> matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is <code>null</code>.
   * @throws AssertionError if the toolTip in this fixture's <code>JPanel</code> does not match the given regular
   * expression.
   * @since 1.2
   */
  public JPanelFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's <code>{@link JPanel}</code>, under the given key.
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
   * Shows a pop-up menu using this fixture's <code>{@link JPanel}</code> as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JPanel</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JPanel</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's <code>{@link JPanel}</code> as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's <code>JPanel</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JPanel</code> is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }
}
