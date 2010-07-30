/* Created on Oct 13, 2009
 * Mel Llaguno
 * http://www.aclaro.com
 * ------------------------------------
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
 * Copyright @2009 the original author or authors.
 */

package org.fest.swing.fixture;

import static org.fest.swing.core.KeyPressInfo.keyCode;
import static org.fest.swing.data.TableCell.row;

import java.awt.Point;
import java.io.File;

import javax.swing.JApplet;

import org.fest.swing.core.*;
import org.fest.swing.data.TableCell;
import org.fest.swing.driver.JAppletDriver;
import org.fest.swing.exception.*;
import org.fest.swing.timing.Timeout;

/**
 * Understands simulation of user input on a <code>{@link JApplet}</code>. Unlike <code>WindowFixture</code>, this
 * driver only focuses on behavior present only in <code>{@link JApplet}</code>s.
 *
 * @author Mel Llaguno
 */

public class JAppletFixture extends ContainerFixture<JApplet>
	implements CommonComponentFixture, LiveConnectSupport{

	private JAppletDriver driver;

	/**
	 * Creates a new <code>{@link JAppletFixture}</code
	 * @param robot performs simulation of user events on a <code>JApplet</code>
	 * @param appletName the name of the <code>JApplet</code> to find using the given <code>Robot</code>.
	 * @throws NullPointerException if <code>robot</code> is <code>null</code>.
	 * @throws ComponentLookupException if a matching <code>JApplet</code> could not be found.
	 * @throws ComponentLookupException if more than one matching <code>JApplet</code> is found.
	 */
	public JAppletFixture(Robot robot, String appletName) {
		super(robot, appletName, JApplet.class);
		createDriver();
	}

	/**
	 * Creates a new <code>{@link JAppletFixture}</code
	 * @param robot performs simulation of user events on the given <code>JApplet</code>.
	 * @param target the <code>JApplet</code> to be managed by this fixture;
	 * @throws NullPointerException if <code>robot</code> is <code>null</code>.
	 * @throws ComponentLookupException if a matching <code>JApplet</code> could not be found.
	 * @throws ComponentLookupException if more than one matching <code>JApplet</code> is found.
	 */
	public JAppletFixture(Robot robot, JApplet target) {
		super(robot, target);
		createDriver();
	}

	/**
	 * Creates a new <code>{@link JAppletFixture}</code>. This constructor creates a new <code>{@link Robot}</code>
	 * containing the current AWT hierarchy. To work against multiple applets in the same browser page, the
	 * <code>{@link BasicRobot.robotWithCurrentAwtHierarchyWithOutScreenLock()}</code> constructor MUST be used.
	 * @param target the <code>JApplet</code> to be managed by this fixture.
	 * @throws NullPointerException if the given target <code>JApplet</code> is <code>null</code>.
	 */
	public JAppletFixture(JApplet target) {
		this(BasicRobot.robotWithCurrentAwtHierarchyWithoutScreenLock(),target);
	}

	/**
	 * Creates a new <code>{@link JAppletFixture}</code>. This constructor create a new <code>{@link Robot}</code>
	 * containing the current AWT hierarchy. To work against multiple applets in the same browser page, the
	 * <code>{@link BasicRobot.robotWithCurrentAwtHierarchyWithOutScreenLock}</code> constructor MUST be used.
	 * @param appletName the name of the <code>JApplet</code> to be managed by this fixture.
	 * @throws NullPointerException if the given target <code>JApplet</code> is <code>null</code>.
	 */
	public JAppletFixture(String appletName) {
		this(BasicRobot.robotWithCurrentAwtHierarchyWithoutScreenLock(), appletName);
	}

	private void createDriver() {
		driver(new JAppletDriver(robot, target));
	}

	/**
	 * Sets the <code>{@link JAppletDriver}</code> to be used by this fixture;
	 * @param newDriver the new <code>JApplet</code>.
	 * @throws NullPointerException if the given driver is <code>null</code>.
	 */
	protected final void driver(JAppletDriver newDriver) {
		validateNotNull(newDriver);
		driver = newDriver;
	}

	/**
	 * Simulates input focus to this fixture's <code>{@link JApplet}</code>.
	 * @return this fixture.
	 */
	public JAppletFixture focus() {
		driver.focus(target);
		return this;
	}

	/**
	 * Asserts that this fixture's <code>{@link JApplet}</code> has input focus.
	 * @return this fixture.
	 * @throws AssertionError if this fixture's <code>JApplet</code> does not have input focus.
	 */
	public JAppletFixture requireFocused() {
		driver.requireFocused(target);
		return this;
	}

	/**
	 * Simulates a user pressing a given key with the given modifiers on this fixture's <code>{@link JApplet}</code>.
	 * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
	 * @param keyPressInfo specifies the key and modifiers to press.
	 * @return this fixture.
	 * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
	 * @throws IllegalArgumentException if the give code is not a valid key code.
	 * @see KeyPressInfo
	 */
	public JAppletFixture pressAndReleaseKey(
			KeyPressInfo keyPressInfo) {
		driver.pressAndReleaseKey(target, keyPressInfo);
		return this;
	}

	/**
	 * Simulates a user pressing and releasing the given keys on the <code>{@link JApplet}</code> managed by this
	 * fixture.
	 * @param keyCodes one or more codes of the keys to press.
	 * @return this fixture.
	 * @throws NullPointerException if the given array of codes is <code>null</code>.
	 * @throws IllegalArgumentException if any of the given code is not a valid key code.
	 * @see java.awt.event.KeyEvent
	 */
	public JAppletFixture pressAndReleaseKeys(int... keyCodes) {
		driver.pressAndReleaseKeys(target, keyCodes);
		return this;
	}

	/**
	 * Simulates a user pressing the given key on this fixture's <code>{@link JApplet}</code>.
	 * @param keyCode the code of the key to press.
	 * @return this fixture.
	 * @throws IllegalArgumentException if the given code is not a valid key code.
	 * @see java.awt.event.KeyEvent
	 */
	public JAppletFixture pressKey(int keyCode) {
		driver.pressKey(target, keyCode);
		return this;
	}

	/**
	 * Simulates a user releasing the given key on this fixture's <code>{@link JApplet}</code>.
	 * @param keyCode the code of the key to release.
	 * @return this fixture.
	 * @throws IllegalArgumentException if the given code is not a valid key code.
	 * @see java.awt.event.KeyEvent
	 */
	public JAppletFixture releaseKey(int keyCode) {
		driver.releaseKey(target, keyCode);
		return this;
	}

	/**
	 * Simulates a user clicking this fixture's <code>{@link JApplet}</code>.
	 * @return this fixture.
	 */
	public JAppletFixture click() {
		driver.click(target);
		return this;
	}

	/**
	 * Simulates a user clicking this fixture's <code>{@link JApplet}</code>.
	 * @param button the button to click.
	 * @return this fixture.
	 * @throws NullPointerExceptin if the given <code>Button</code> is <code>null</code>.
	 */
	public JAppletFixture click(MouseButton button) {
		driver.click(target, button);
		return this;
	}

	/**
	 * Simulates a user clicking this fixture's <code>{@link JApplet}</code>.
	 * @param mouseClickInfo specifies the button to click and the times the button should be clicked.
	 * @return this fixture.
	 * @throws NullPointerException if the given <code>MouseClickInfo</code> is <code>null</code>.
	 */
	public JAppletFixture click(MouseClickInfo mouseClickInfo) {
		driver.click(target, mouseClickInfo);
		return this;
	}

	/**
	 * Simulates a user double-clicking this fixture's <code>{@link JApplet}</code>.
	 * @return this fixture.
	 */
	public JAppletFixture doubleClick() {
		driver.doubleClick(target);
		return this;
	}

	/**
	 * Simulates a user right-clicking this fixture's <code>{@link JApplet}</code>.
	 * @return this fixture.
	 */
	public JAppletFixture rightClick() {
		driver.rightClick(target);
		return this;
	}

	/**
	 * Asserts that this fixture's <code>{@link JApplet}</code> is disabled.
	 * @return this fixture.
	 * @throws AssertionError if this fixture's <code>JApplet</code> is enabled.
	 */
	public JAppletFixture requireDisabled() {
		driver.requireDisabled(target);
		return this;
	}

	/**
	 * Asserts that this fixture's <code>{@link JApplet}</code> is enabled.
	 * @return this fixture.
	 * @throws AssertionError if this fixture's <code>JApplet</code> is disabled.
	 */
	public JAppletFixture requireEnabled() {
		driver.requireEnabled(target);
		return this;
	}

	/**
	 * Asserts that this fixture's <code>{@link JApplet}</code> is enabled.
	 * @param timeout the time this fixture will wait for the component to be enabled.
	 * @return this fixture.
	 * @throws WaitTimedOutError if this fixture's <code>JApplet</code> is never enabled.
	 */
	public JAppletFixture requireEnabled(Timeout timeout) {
		driver.requireEnabled(target, timeout);
		return this;
	}

	/**
	 * Asserts that this fixture's <code>{@link JApplet}</code> is not visible.
	 * @return this fixture.
	 * @throws AssertionError if this fixture's <code>JApplet</code> is visible.
	 */
	public JAppletFixture requireNotVisible() {
		driver.requireNotVisible(target);
		return this;
	}

	/**
	 * Asserts that this fixture's <code>{@link JApplet}</code> is visible.
	 * @return this fixture.
	 * @throws AssertionError if this fixture's <code>JApplet</code> is not visible.
	 */
	public StateVerificationFixture requireVisible() {
		driver.requireVisible(target);
		return this;
	}

	/**
	 * Shows a pop-up menu using this fixture's <code>{@link JApplet}</code> as the invoker of the pop-up menu.
	 * @return a fixture that manages the displayed pop-up menu.
	 * @throws IllegalStateException if this fixture's <code>JApplet</code> is disabled.
	 * @throws IllegalStateException if this fixture's <code>JApplet</code> is not showing on the screen.
	 * @throws ComponentLookupException if a pop-up menu cannot be found.
	 */
	public JPopupMenuFixture showPopupMenu() {
		return new JPopupMenuFixture(robot, driver.invokePopupMenu(target));
	}

	/**
	 * Shows a pop-up menu at the given point using this fixture's <code>{@link JApplet}</code> as the invoker
	 * of the pop-up menu.
	 * @param p the given point where to show the pop-up menu.
	 * @return a fixture that manages the displayed pop-up menu.
	 * @throws IllegalStateException if this fixture's <code>JApplet</code> is disabled.
	 * @throws IllegalStateException if this fixture's <code>JApplet</code> is not showing on the screen.
	 * @throws ComponentLookupException if a pop-up menu cannot be found.
	 */
	public JPopupMenuFixture showPopupMenuAt(Point p) {
		return new JPopupMenuFixture(robot, driver.invokePopupMenu(target, p));
	}

	/**
	 * Factory method for creating KeyPressInfo objects
	 * @param keyCode the ASCII code as an integer
	 * @param modifier the bit-mask modifier as an integer
	 * @return KeyPressInfo object which understands key chord presses (i.e. CTRL-A)
	 */
	public KeyPressInfo createKeyPressInfo(int keyCode, int modifier)
	{
		return keyCode(keyCode).modifiers(modifier);
	}

	/**
	 * Factory method for creating Point objects
	 * @param x the x coordinate
	 * @param y the y coordinate
	 * @return Point object which understands a Cartesian coordinate.
	 */
	public Point createPoint(int x, int y)
	{
		return new Point(x,y);
	}

	/**
	 * Factory method for creating MouseButton objects
	 * @param buttonMask the integer representation of the Button Mask
	 * @return a MouseButton object which understands mouse button semantics.
	 */
	public MouseButton createMouseButton(int buttonMask)
	{
		return MouseButton.lookup(buttonMask);
	}

	/**
	 * Factory method for creating File objects
	 * @param name the name of the file
	 * @return a File object
	 */
	public File createFile(String name) {
		return new File(name);
	}

	/**
	 * Factory method for creating TableCell objects
	 * @param row the row for the cell
	 * @param column the column for the cell
	 * @return a TableCell object
	 * @see org.fest.swing.fixture.LiveConnectSupport#createTableCell(int, int)
	 */
	public TableCell createTableCell(int row, int column) {
		return row(row).column(column);
	}

	public TableCell[] createTableCells(Object...objects) {

		TableCell [] array = new TableCell[objects.length];
		for(int i = 0; i < objects.length; i++)
		{
			String[] rowColumn = ((String)objects[i]).split("\\,");
			array[i] = row(Integer.parseInt(rowColumn[0])).column(Integer.parseInt(rowColumn[1]));
		}
		return array;
	}

	/**
	 * Factory method for creating String Array objects
	 * @param objects the array of objects to be converted into a String Array
	 * @return String []
	 */
	public String [] createStringArray(Object...objects)
	{
		String [] array = new String[objects.length];
		for(int i = 0; i < objects.length; i++)
			array[i] = (String)objects[i];

		return array;
	}

}
