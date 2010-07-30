/*
 * Created on Jul 9, 2007
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

import java.io.File;

import javax.swing.JFileChooser;

import org.fest.swing.core.*;
import org.fest.swing.driver.JFileChooserDriver;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands functional testing of <code>{@link JFileChooser}</code>s:
 * <ul>
 * <li>user input simulation</li>
 * <li>state verification</li>
 * <li>property value query</li>
 * </ul>
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JFileChooserFixture extends ComponentFixture<JFileChooser> implements CommonComponentFixture {

  private JFileChooserDriver driver;

  /**
   * Creates a new <code>{@link JFileChooserFixture}</code>.
   * @param robot performs simulation of user events on a <code>JFileChooser</code>.
   * @throws NullPointerException if <code>robot</code> is {@code null}.
   * @throws ComponentLookupException if a matching <code>JFileChooser</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JFileChooser</code> is found.
   */
  public JFileChooserFixture(Robot robot) {
    super(robot, JFileChooser.class);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JFileChooserFixture}</code>.
   * @param robot performs simulation of user events on the given <code>JFileChooser</code>.
   * @param target the <code>JFileChooser</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is {@code null}.
   * @throws NullPointerException if <code>target</code> is {@code null}.
   */
  public JFileChooserFixture(Robot robot, JFileChooser target) {
    super(robot, target);
    createDriver();
  }

  /**
   * Creates a new <code>{@link JFileChooserFixture}</code>.
   * @param robot performs simulation of user events on a <code>JFileChooser</code>.
   * @param fileChooserName the name of the <code>JFileChooser</code> to find using the given <code>RobotFixture</code>.
   * @throws NullPointerException if <code>robot</code> is {@code null}.
   * @throws ComponentLookupException if a matching <code>JFileChooser</code> could not be found.
   * @throws ComponentLookupException if more than one matching <code>JFileChooser</code> is found.
   */
  public JFileChooserFixture(Robot robot, String fileChooserName) {
    super(robot, fileChooserName, JFileChooser.class);
    createDriver();
  }

  private void createDriver() {
    driver(new JFileChooserDriver(robot));
  }

  /**
   * Sets the <code>{@link JFileChooserDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>JFileChooserDriver</code>.
   * @throws NullPointerException if the given driver is {@code null}.
   */
  protected final void driver(JFileChooserDriver newDriver) {
    validateNotNull(newDriver);
    driver = newDriver;
  }

  /**
   * Simulates a user pressing the "Approve" button in this fixture's <code>{@link JFileChooser}</code>.
   * @throws ComponentLookupException if the "Approve" button cannot be found.
   * @throws IllegalStateException if the "Approve" button is disabled.
   * @throws IllegalStateException if the "Approve" button is not showing on the screen.
   */
  public void approve() {
    driver.clickApproveButton(target);
  }

  /**
   * Finds the "Approve" button in this fixture's <code>{@link JFileChooser}</code>.
   * @return the found "Approve" button.
   * @throws ComponentLookupException if the "Approve" button cannot be found.
   */
  public JButtonFixture approveButton() {
    return new JButtonFixture(robot, driver.approveButton(target));
  }

  /**
   * Simulates a user pressing the "Cancel" button in this fixture's <code>{@link JFileChooser}</code>.
   * @throws ComponentLookupException if the "Cancel" button cannot be found.
   * @throws IllegalStateException if the "Cancel" button is disabled.
   * @throws IllegalStateException if the "Cancel" button is not showing on the screen.
   */
  public void cancel() {
    driver.clickCancelButton(target);
  }

  /**
   * Finds the "Cancel" button in this fixture's <code>{@link JFileChooser}</code>.
   * @return the found "Cancel" button.
   * @throws ComponentLookupException if the "Cancel" button cannot be found.
   */
  public JButtonFixture cancelButton() {
    return new JButtonFixture(robot, driver.cancelButton(target));
  }

  /**
   * Returns a fixture that manages the field where the user can enter the name of the file to select in this fixture's
   * <code>{@link JFileChooser}</code>.
   * @return the created fixture.
   * @throws ComponentLookupException if a matching textToMatch field could not be found.
   */
  public JTextComponentFixture fileNameTextBox() {
    return new JTextComponentFixture(robot, driver.fileNameTextBox(target));
  }


  /**
   * Selects the given file in this fixture's <code>{@link JFileChooser}</code>.
   * @param file the file to select.
   * @return this fixture.
   * @throws NullPointerException if the given file is {@code null}.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   * @throws IllegalArgumentException if this fixture's <code>JFileChooser</code> can select directories only and the
   * file to select is not a directory.
   * @throws IllegalArgumentException if this fixture's <code>JFileChooser</code> cannot select directories and the file
   * to select is a directory.
   */
  public JFileChooserFixture selectFile(final File file) {
    driver.selectFile(target, file);
    return this;
  }


  /**
   * Selects the given files in this fixture's <code>{@link JFileChooser}</code>.
   * @param files the files to select.
   * @return this fixture.
   * @throws NullPointerException if the given array of files is {@code null}.
   * @throws IllegalArgumentException if the given array of files is empty.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> does not support multiple selection and
   * there is more than one file to select.
   * @throws IllegalArgumentException if this fixture's <code>JFileChooser</code> can select directories only and any of
   * the files to select is not a directory.
   * @throws IllegalArgumentException if this fixture's <code>JFileChooser</code> cannot select directories and any of
   * the files to select is a directory.
   */
  public JFileChooserFixture selectFiles(File... files) {
    driver.selectFiles(target, files);
    return this;
  }

  /**
   * Sets the current directory of this fixture's <code>{@link JFileChooser}</code> to the given one.
   * @param dir the directory to set as current.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   * @return this fixture.
   */
  public JFileChooserFixture setCurrentDirectory(final File dir) {
    driver.setCurrentDirectory(target, dir);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JFileChooser}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   */
  public JFileChooserFixture click() {
    driver.click(target);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JFileChooser}</code>.
   * @param button the button to click.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseButton</code> is {@code null}.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   */
  public JFileChooserFixture click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /**
   * Simulates a user clicking this fixture's <code>{@link JFileChooser}</code>.
   * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
   * @return this fixture.
   * @throws NullPointerException if the given <code>MouseClickInfo</code> is {@code null}.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   */
  public JFileChooserFixture click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /**
   * Simulates a user double-clicking this fixture's <code>{@link JFileChooser}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   */
  public JFileChooserFixture doubleClick() {
    driver.doubleClick(target);
    return this;
  }

  /**
   * Simulates a user right-clicking this fixture's <code>{@link JFileChooser}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   */
  public JFileChooserFixture rightClick() {
    driver.rightClick(target);
    return this;
  }

  /**
   * Gives input focus to this fixture's <code>{@link JFileChooser}</code>.
   * @return this fixture.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   */
  public JFileChooserFixture focus() {
    driver.focus(target);
    return this;
  }

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's <code>{@link JFileChooser}</code>.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is {@code null}.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   * @see KeyPressInfo
   */
  public JFileChooserFixture pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /**
   * Simulates a user pressing and releasing the given keys on the <code>{@link JFileChooser}</code> managed by this
   * fixture.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is {@code null}.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JFileChooserFixture pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /**
   * Simulates a user pressing the given key on this fixture's <code>{@link JFileChooser}</code>.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JFileChooserFixture pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /**
   * Simulates a user releasing the given key on this fixture's <code>{@link JFileChooser}</code>.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is disabled.
   * @throws IllegalStateException if this fixture's <code>JFileChooser</code> is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  public JFileChooserFixture releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JFileChooser}</code> has input focus.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JFileChooser</code> does not have input focus.
   */
  public JFileChooserFixture requireFocused() {
    driver.requireFocused(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JFileChooser}</code> is enabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JFileChooser</code> is disabled.
   */
  public JFileChooserFixture requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JFileChooser}</code> is enabled.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @return this fixture.
   * @throws WaitTimedOutError if this fixture's <code>JFileChooser</code> is never enabled.
   */
  public JFileChooserFixture requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JFileChooser}</code> is disabled.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JFileChooser</code> is enabled.
   */
  public JFileChooserFixture requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JFileChooser}</code> is visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JFileChooser</code> is not visible.
   */
  public JFileChooserFixture requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /**
   * Asserts that this fixture's <code>{@link JFileChooser}</code> is not visible.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>JFileChooser</code> is visible.
   */
  public JFileChooserFixture requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }
}
