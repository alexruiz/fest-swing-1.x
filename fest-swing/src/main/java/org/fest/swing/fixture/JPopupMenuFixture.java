/*
 * Created on Sep 5, 2007
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

import javax.swing.*;

import org.fest.swing.core.*;
import org.fest.swing.driver.JPopupMenuDriver;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JPopupMenu}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Yvonne Wang
 */
public class JPopupMenuFixture extends ComponentFixture<JPopupMenu> implements CommonComponentFixture {

  private JPopupMenuDriver driver;
  private final JMenuItemFinder menuItemFinder;

  /**
   * Creates a new <code>{@link JPopupMenuFixture}</code>.
   * @param robot performs simulation of user events on the given {@code JPopupMenu}.
   * @param target the {@code JPopupMenu} to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is {@code null}.
   * @throws NullPointerException if <code>target</code> is {@code null}.
   */
  public JPopupMenuFixture(Robot robot, JPopupMenu target) {
    super(robot, target);
    menuItemFinder = new JMenuItemFinder(robot, target);
    driver(new JPopupMenuDriver(robot));
  }

  /**
   * Sets the <code>{@link JPopupMenuDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JPopupMenuDriver</code>.
   * @throws NullPointerException if the given driver is {@code null}.
   */
  protected final void driver(JPopupMenuDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Finds a <code>{@link JMenuItem}</code>, contained in this fixture's <code>{@link JPopupMenu}</code>,
   * which name matches the specified one.
   * @param name the name to match.
   * @return a fixture that manages the <code>JMenuItem</code> found.
   * @throws ComponentLookupException if a <code>JMenuItem</code> having a matching name could not be found.
   * @throws ComponentLookupException if more than one <code>JMenuItem</code> having a matching name is found.
   */
  public JMenuItemFixture menuItem(String name) {
    return new JMenuItemFixture(robot, driver.menuItem(target, name));
  }

  /**
   * Finds a <code>{@link JMenuItem}</code>, contained in this fixture's <code>{@link JPopupMenu}</code>,
   * that matches the specified search criteria.
   * @param matcher contains the search criteria for finding a <code>JMenuItem</code>.
   * @return a fixture that manages the <code>JMenuItem</code> found.
   * @throws ComponentLookupException if a <code>JMenuItem</code> that matches the given search criteria could not be
   * found.
   * @throws ComponentLookupException if more than one <code>JMenuItem</code> that matches the given search criteria is
   * found.
   */
  public JMenuItemFixture menuItem(GenericTypeMatcher<? extends JMenuItem> matcher) {
    return new JMenuItemFixture(robot, driver.menuItem(target, matcher));
  }

  /**
   * Finds a <code>{@link JMenuItem}</code> in this fixture's <code>{@link JPopupMenu}</code>, which path matches
   * the given one.
   * <p>
   * For example, if we are looking for the menu with text "New" contained under the menu with text "File", we can
   * simply call
   *
   * <pre>
   * JPopupMenuFixture popupMenu = tree.showPopupMenu();
   * JMenuItemFixture menuItem = popupMenu.<strong>menuItemWithPath(&quot;File&quot;, &quot;Menu&quot;)</strong>;
   * </pre>
   *
   * </p>
   * @param path the path of the menu to find.
   * @return a fixture that manages the <code>JMenuItem</code> found.
   * @throws ComponentLookupException if a <code>JMenuItem</code> under the given path could not be found.
   * @throws AssertionError if the {@code Component} found under the given path is not a <code>JMenuItem</code>.
   */
  public JMenuItemFixture menuItemWithPath(String... path) {
    return new JMenuItemFixture(robot, menuItemFinder.menuItemWithPath(path));
  }

  /**
   * Returns the contents of this fixture's <code>{@link JPopupMenu}</code>.
   * @return a <code>String</code> array representing the contents of this fixture's {@code JPopupMenu}.
   */
  public String[] menuLabels() {
    return driver.menuLabelsOf(target);
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JPopupMenu}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is not showing on the screen.
   */
  public JPopupMenuFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JPopupMenu}</code>.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseButton</code> is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is not showing on the screen.
   */
  public JPopupMenuFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JPopupMenu}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is {@code null}.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is not showing on the screen.
   */
  public JPopupMenuFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JPopupMenu}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is not showing on the screen.
   */
  public JPopupMenuFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JPopupMenu}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is not showing on the screen.
   */
  public JPopupMenuFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JPopupMenu}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is not showing on the screen.
   */
  public JPopupMenuFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JPopupMenu}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is {@code null}.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is not showing on the screen.
   * @see KeyPressInfo
   */
  public JPopupMenuFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's <code>{@link JPopupMenu}</code>. This
   * method does not affect the current focus.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JPopupMenuFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JPopupMenu}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JPopupMenuFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JPopupMenu}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is disabled.
   * @throws IllegalStateException if this fixture's {@code JPopupMenu} is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JPopupMenuFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPopupMenu}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JPopupMenu} does not have input focus.
   */
  public JPopupMenuFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPopupMenu}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JPopupMenu} is disabled.
   */
  public JPopupMenuFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPopupMenu}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's {@code JPopupMenu} is never enabled.
   */
  public JPopupMenuFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPopupMenu}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JPopupMenu} is enabled.
   */
  public JPopupMenuFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPopupMenu}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JPopupMenu} is not visible.
   */
  public JPopupMenuFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JPopupMenu}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's {@code JPopupMenu} is visible.
   */
  public JPopupMenuFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }
}
