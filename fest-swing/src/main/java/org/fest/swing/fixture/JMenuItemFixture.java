/*
 * Created on Oct 20, 2006
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
 * Copyright @2006-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import javax.swing.JMenuItem;

import org.fest.swing.core.KeyPressInfo;
import org.fest.swing.core.Robot;
import org.fest.swing.driver.JMenuItemDriver;
import org.fest.swing.exception.ActionFailedException;
import org.fest.swing.exception.ComponentLookupException;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JMenuItem}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Alex Ruiz
 */
public class JMenuItemFixture extends ComponentFixture<JMenuItem> implements KeyboardInputSimulationFixture,
    StateVerificationFixture {

  private JMenuItemDriver driver;

  /**
   * Creates a new <code>{@link JMenuItemFixture}</code>.
   * @param robot performs simulation of user events on a <code>JMenuItem</code>.
   * @param menuItemName the name of the <code>JMenuItem</code> to find using the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching <code>JMenuItem</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JMenuItem</code> is found.
   */
  public JMenuItemFixture(Robot robot, String menuItemName) {
    this(robot, robot.finder().findByName(menuItemName, JMenuItem.class, false));
  }

  /**
   * Creates a new <code>{@link JMenuItemFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JMenuItem</code>.
   * @param target the <code>JMenuItem</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public JMenuItemFixture(Robot robot, JMenuItem target) {
    super(robot, target);
    driver(new JMenuItemDriver(robot));
  }

  /**
   * Sets the <code>{@link JMenuItemDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JMenuItemDriver</code>.
   * @throws NullPointerException if the given driver is <code>null</code>.
   */
  protected final void driver(JMenuItemDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Simulates a user selecting this fixture's <code>{@link JMenuItem}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is not showing on the screen.
   * @throws ActionFailedException if the menu has a pop-up and it fails to show up.
   */
  public JMenuItemFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JMenuItem}</code>.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is not showing on the screen.
   * @return this fixture.
   */
  public JMenuItemFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JMenuItem}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is not showing on the screen.
   * @see KeyPressInfo
   */
  public JMenuItemFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's <code>{@link JMenuItem}</code>.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JMenuItemFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JMenuItem}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JMenuItemFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JMenuItem}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JMenuItem</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JMenuItemFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JMenuItem}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JMenuItem</code> is disabled.
   */
  public JMenuItemFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JMenuItem}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws org.fest.swing.exception.WaitTimedOutError if this fixture's <code>JMenuItem</code> is never enabled.
   */
  public JMenuItemFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JMenuItem}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JMenuItem</code> is enabled.
   */
  public JMenuItemFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JMenuItem}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JMenuItem</code> is not visible.
   */
  public JMenuItemFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JMenuItem}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JMenuItem</code> is visible.
   */
  public JMenuItemFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }
}
