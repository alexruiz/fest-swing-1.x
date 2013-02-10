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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Point;
import java.util.regex.Pattern;

import javax.swing.JPanel;

import org.fest.swing.core.*;
import org.fest.swing.driver.JComponentDriver;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of {@link JPanel}s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JPanelFixture extends ContainerFixture<JPanel> implements CommonComponentFixture, JComponentFixture,
    JPopupMenuInvokerFixture {
  private JComponentDriver driver;

  /**
   * Creates a new {@link JPanelFixture}.
   * @param robot performs simulation of user events on a {@code JPanel}.
   * @param panelName the name of the {@code JPanel} to find using the given {@code Robot}.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws ComponentLookupException if a matching {@code JPanel} could not be found.
   * @throws ComponentLookupException if more than one matching {@code JPanel} is found.
   */
  public JPanelFixture(Robot robot, String panelName) {
    super(robot, panelName, JPanel.class);
    createDriver();
  }

  /**
   * Creates a new {@link JPanelFixture}.
   * @param robot performs simulation of user events on the given {@code JPanel}.
   * @param target the {@code JPanel} to be managed by this fixture.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws NullPointerException if {@code target} is {@code null}.
   */
  public JPanelFixture(Robot robot, JPanel target) {
    super(robot, target);
    createDriver();
  }

  private void createDriver() {
    driver(new JComponentDriver(robot));
  }

  /**
   * Sets the {@link JComponentDriver} to be used by this fixture.
   * @param newDriver the new {@code JComponentDriver}.
   * @throws NullPointerException if the given driver is {@code null}.
   */
  protected final void driver(JComponentDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Simulates a user clicking this fixture's {@link JPanel}.
   * @return this fixture.
   */
  public JPanelFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@link JPanel}.
   * @param button the button to click.
   * @return this fixture.
   */
  public JPanelFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's {@link JPanel}.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given {@code MouseClickInfo} is {@code null}.
   */
  public JPanelFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's {@link JPanel}.
   * @return this fixture.
   */
  public JPanelFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's {@link JPanel}.
   * @return this fixture.
   */
  public JPanelFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's {@link JPanel}.
   * @return this fixture.
   */
  public JPanelFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's {@link JPanel}.
   * Modifiers is a mask from the available {@link java.awt.event.InputEvent} masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given {@code KeyPressInfo} is {@code null}.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @see KeyPressInfo
   */
  public JPanelFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on the {@link JPanel} managed by this
   * fixture.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @see java.awt.event.KeyEvent
   */
  public JPanelFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's {@link JPanel}.
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
   * Simulates a user releasing the given key on this fixture's {@link JPanel}.
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
   * Asserts that this fixture's {@link JPanel} has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JPanel} does not have input focus.
   */
  public JPanelFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@link JPanel} is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JPanel} is disabled.
   */
  public JPanelFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@link JPanel} is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's {@code JPanel} is never enabled.
   */
  public JPanelFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's {@link JPanel} is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JPanel} is enabled.
   */
  public JPanelFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@link JPanel} is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JPanel} is not visible.
   */
  public JPanelFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's {@link JPanel} is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JPanel} is visible.
   */
  public JPanelFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's {@link JPanel} matches the given value.
   * @param expected the given value. It can be a regular expression.
   * @return this fixture.
   * @throws AssertionError if the toolTip in this fixture's {@code JPanel} does not match the given value.
   * @since 1.2
   */
  public JPanelFixture requireToolTip(String expected) {
    driver.requireToolTip(target, expected);
    return this;
  }

  /**
   * Asserts that the toolTip in this fixture's {@link JPanel} matches the given regular expression
   * pattern.
   * @param pattern the regular expression pattern to match.
   * @return this fixture.
   * @throws NullPointerException if the given regular expression pattern is {@code null}.
   * @throws AssertionError if the toolTip in this fixture's {@code JPanel} does not match the given regular
   * expression.
   * @since 1.2
   */
  public JPanelFixture requireToolTip(Pattern pattern) {
    driver.requireToolTip(target, pattern);
    return this;
  }

  /**
   * Returns the client property stored in this fixture's {@link JPanel}, under the given key.
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
   * Shows a pop-up menu using this fixture's {@link JPanel} as the invoker of the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JPanel} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPanel} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenu() {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
  }

  /**
   * Shows a pop-up menu at the given point using this fixture's {@link JPanel} as the invoker of the
   * pop-up menu.
   * @param p the given point where to show the pop-up menu.
   * @return a fixture that manages the displayed pop-up menu.
   * @throws IllegalStateException if this fixture's {@code JPanel} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPanel} is not showing on the screen.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  public JPopupMenuFixture showPopupMenuAt(Point p) {
    return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
  }
}
