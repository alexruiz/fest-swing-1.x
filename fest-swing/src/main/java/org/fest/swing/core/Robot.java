/*
 * Created on Mar 1, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.core;

import java.awt.*;
import java.awt.event.InputEvent;

import javax.swing.JPopupMenu;

import org.fest.swing.exception.*;
import org.fest.swing.hierarchy.ComponentHierarchy;
import org.fest.swing.lock.ScreenLock;

/**
 * Simulates user input on a GUI <code>{@link Component}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public interface Robot {

  /**
   * Returns the <code>{@link ComponentHierarchy}</code> being used by this robot.
   * @return the {@code ComponentHierarchy} being used by this robot.
   */
  ComponentHierarchy hierarchy();

  /**
   * Returns the <code>{@link ComponentFinder}</code> being used by this robot.
   * @return the {@code ComponentFinder} being used by this robot.
   */
  ComponentFinder finder();

  /**
   * Returns the <code>{@link BasicComponentPrinter}</code> being used by this robot.
   * @return the {@code ComponentPrinter} being used by this robot.
   */
  ComponentPrinter printer();

  /**
   * Safely display a window with proper EDT synchronization. This method blocks until the <code>{@link Window}</code>
   * is showing and ready for input.
   * @param w the window to display.
   */
  void showWindow(Window w);

  /**
   * Safely display a window with proper EDT synchronization. This method blocks until the <code>{@link Window}</code>
   * is showing and ready for input.
   * @param w the window to display.
   * @param size the size of the window to display.
   */
  void showWindow(Window w, Dimension size);

  /**
   * <p>
   * Safely display a window with proper EDT synchronization. This method blocks until the window is showing. This
   * method will return even when the window is a modal dialog, since the show method is called on the event dispatch
   * thread. The window will be packed if the pack flag is set, and set to the given size if it is non-{@code null}.
   * </p>
   * Modal dialogs may be shown with this method without blocking.
   * @param w the window to display.
   * @param size the size of the window to display.
   * @param pack flag that indicates if the window should be packed or not. By packed we mean calling
   * <code>w.pack()</code>.
   */
  void showWindow(final Window w, final Dimension size, final boolean pack);

  /**
   * Simulates a user closing the given window.
   * @param w the window to close.
   */
  void close(Window w);

  /**
   * Gives input focus to the given <code>{@link Component}</code>. Note that the component may not yet have focus when
   * this method returns.
   * @param c the component to give focus to.
   */
  void focus(Component c);

  /**
   * Gives input focus to the given <code>{@link Component}</code> and waits until the <code>{@link Component}</code>
   * has focus.
   * @param c the component to give focus to.
   */
  void focusAndWaitForFocusGain(Component c);

  /**
   * Cleans up any used resources (keyboard, mouse, open windows and <code>{@link ScreenLock}</code>) used by this
   * robot.
   */
  void cleanUp();

  /**
   * Cleans up any used resources (keyboard, mouse and <code>{@link ScreenLock}</code>) used by this robot. This method
   * <strong>does not</strong> dispose any open windows.
   * <p>
   * <strong>Note:</strong> The preferred method to use to clean up resources is <code>{@link #cleanUp()}</code>. Using
   * <code>{@link #cleanUpWithoutDisposingWindows()}</code> may leave many windows open after each test. Use it on very
   * special cases. Please read <a href="http://code.google.com/p/fest/issues/detail?id=138" target="_blank">bug 138</a>
   * for more details.
   * </p>
   */
  void cleanUpWithoutDisposingWindows();

  /**
   * Simulates a user clicking once the given <code>{@link Component}</code> using the left mouse button.
   * @param c the {@code Component} to click on.
   * @throws ActionFailedException if the component to click is out of the boundaries of the screen.
   */
  void click(Component c);

  /**
   * Simulates a user right-clicking the given <code>{@link Component}</code>.
   * @param c the {@code Component} to click on.
   * @throws ActionFailedException if the component to click is out of the boundaries of the screen.
   */
  void rightClick(Component c);

  /**
   * Simulates a user clicking once the given <code>{@link Component}</code> using the given mouse button.
   * @param c the {@code Component} to click on.
   * @param button the mouse button to use.
   * @throws ActionFailedException if the component to click is out of the boundaries of the screen.
   */
  void click(Component c, MouseButton button);

  /**
   * Simulates a user double-clicking the given <code>{@link Component}</code>.
   * @param c the {@code Component} to click on.
   * @throws ActionFailedException if the component to click is out of the boundaries of the screen.
   */
  void doubleClick(Component c);

  /**
   * Simulates a user clicking the given mouse button, the given times on the given <code>{@link Component}</code>.
   * @param c the {@code Component} to click on.
   * @param button the mouse button to click.
   * @param times the number of times to click the given mouse button.
   * @throws ActionFailedException if the component to click is out of the boundaries of the screen.
   */
  void click(Component c, MouseButton button, int times);

  /**
   * Simulates a user clicking at the given position on the given <code>{@link Component}</code>.
   * @param c the {@code Component} to click on.
   * @param where the given coordinates, relative to the given {@code Component}.
   * @throws ActionFailedException if the component to click is out of the boundaries of the screen.
   */
  void click(Component c, Point where);

  /**
   * Simulates a user clicking the given mouse button, the given times at the given position on the given
   * <code>{@link Component}</code>.
   * @param c the {@code Component} to click on.
   * @param where the given coordinates, relative to the given {@code Component}.
   * @param button the mouse button to click.
   * @param times the number of times to click the given mouse button.
   * @throws ActionFailedException if the component to click is out of the boundaries of the screen.
   */
  void click(Component c, Point where, MouseButton button, int times);

  /**
   * Simulates a user clicking the given mouse button, the given times at the given absolute coordinates.
   * @param where the coordinates where to click.
   * @param button the mouse button to click.
   * @param times the number of times to click the given mouse button.
   */
  void click(Point where, MouseButton button, int times);

  /**
   * Simulates a user pressing a mouse button.
   * @param button the mouse button to press.
   */
  void pressMouse(MouseButton button);

  /**
   * Simulates a user pressing the left mouse button on the given <code>{@link Component}</code>.
   * @param c the {@code Component} to click on.
   * @param where the given coordinates, relative to the given {@code Component}.
   */
  void pressMouse(Component c, Point where);

  /**
   * Simulates a user pressing the given mouse button on the given <code>{@link Component}</code>.
   * @param c the {@code Component} to click on.
   * @param where the given coordinates, relative to the given {@code Component}.
   * @param button the mouse button to press.
   */
  void pressMouse(Component c, Point where, MouseButton button);

  /**
   * Simulates a user pressing the given mouse button on the given coordinates.
   * @param where the position where to press the given mouse button.
   * @param button the mouse button to press.
   * @since 1.1
   */
  void pressMouse(Point where, MouseButton button);

  /**
   * Simulates a user moving the mouse pointer to the center of the given <code>{@link Component}</code>.
   * @param c the given {@code Component}.
   */
  void moveMouse(Component c);

  /**
   * Simulates a user moving the mouse pointer to the given coordinates relative to the given
   * <code>{@link Component}</code>.
   * @param c the given {@code Component}.
   * @param p the given coordinates, relative to the given {@code Component}.
   * @throws ActionFailedException if the given component is not showing and ready for input.
   */
  void moveMouse(Component c, Point p);

  /**
   * Simulates a user moving the mouse pointer to the given coordinates relative to the given
   * <code>{@link Component}</code>.
   * @param c the given {@code Component}.
   * @param x X coordinate, relative to the given {@code Component}.
   * @param y Y coordinate, relative to the given {@code Component}.
   * @throws ActionFailedException if the given component is not showing and ready for input.
   */
  void moveMouse(Component c, int x, int y);

  /**
   * Simulates a user moving the mouse pointer to the given coordinates.
   * @param p the given coordinates.
   * @since 1.1
   */
  void moveMouse(Point p);

  /**
   * Simulates a user moving the mouse pointer to the given coordinates.
   * @param x X coordinate.
   * @param y Y coordinate.
   * @since 1.1
   */
  void moveMouse(int x, int y);

  /**
   * Releases the given mouse button.
   * @param button the mouse button to release.
   */
  void releaseMouse(MouseButton button);

  /**
   * Releases any mouse button(s) used by the robot.
   */
  void releaseMouseButtons();

  /**
   * Moves the mouse pointer over to the given <code>{@link Component}</code> and rotates the scroll wheel on
   * wheel-equipped mice.
   * @param c the given {@code Component}.
   * @param amount number of "notches" to move the mouse wheel. Negative values indicate movement up/away from the user,
   * while positive values indicate movement down/towards the user.
   */
  void rotateMouseWheel(Component c, int amount);

  /**
   * Rotates the scroll wheel on wheel-equipped mice.
   * @param amount number of "notches" to move the mouse wheel. Negative values indicate movement up/away from the user,
   * while positive values indicate movement down/towards the user.
   */
  void rotateMouseWheel(int amount);

  /**
   * Makes the mouse pointer show small quick jumpy movements on the given <code>{@link Component}</code>.
   * @param c the given {@code Component}.
   */
  void jitter(Component c);

  /**
   * Makes the mouse pointer show small quick jumpy movements on the given <code>{@link Component}</code> at the given
   * point.
   * @param c the given {@code Component}.
   * @param where the given point.
   */
  void jitter(Component c, Point where);

  /**
   * Simulates a user entering the given text. Note that this method the key strokes to the component that has input
   * focus.
   * @param text the text to enter.
   */
  void enterText(String text);

  /**
   * Types the given character. Note that this method sends the key strokes to the component that has input focus.
   * @param character the character to type.
   */
  void type(char character);

  /**
   * Type the given key code with the given modifiers. Modifiers is a mask from the available
   * <code>{@link java.awt.event.InputEvent}</code> masks.
   * @param keyCode the code of the key to press.
   * @param modifiers the given modifiers.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   */
  void pressAndReleaseKey(int keyCode, int... modifiers);

  /**
   * Simulates a user pressing and releasing the given keys. This method does not affect the current focus.
   * @param keyCodes one or more codes of the keys to press.
   * @see java.awt.event.KeyEvent
   * @throws IllegalArgumentException if any of the given codes is not a valid key code.
   */
  void pressAndReleaseKeys(int... keyCodes);

  /**
   * Simulates a user pressing given key. This method does not affect the current focus.
   * @param keyCode the code of the key to press.
   * @see java.awt.event.KeyEvent
   * @throws IllegalArgumentException if the given code is not a valid key code.
   */
  void pressKey(int keyCode);

  /**
   * Simulates a user releasing the given key. This method does not affect the current focus.
   * @param keyCode the code of the key to release.
   * @see java.awt.event.KeyEvent
   * @throws IllegalArgumentException if the given code is not a valid key code.
   */
  void releaseKey(int keyCode);

  /**
   * Presses the appropriate modifiers corresponding to the given mask. Use mask values from
   * <code>{@link InputEvent}</code>.
   * @param modifierMask the given mask.
   * @see InputEvent
   */
  void pressModifiers(int modifierMask);

  /**
   * Releases the appropriate modifiers corresponding to the given mask. Use mask values from
   * <code>{@link InputEvent}</code>.
   * @param modifierMask the given mask.
   * @see InputEvent
   */
  void releaseModifiers(int modifierMask);

  /**
   * Wait for an idle AWT event queue. Note that this is different from the implementation of
   * <code>java.awt.Robot.waitForIdle()</code>, which may have events on the queue when it returns. Do <strong>NOT</strong>
   * use this method if there are animations or other continual refreshes happening, since in that case it may never
   * return.
   * @throws IllegalThreadStateException if this method is called from the event dispatch thread.
   */
  void waitForIdle();

  /**
   * Indicates whether the robot is currently in a dragging operation.
   * @return {@code true} if the robot is currently in a dragging operation, {@code false} otherwise.
   */
  boolean isDragging();

  /**
   * Indicates whether the given <code>{@link Component}</code> is ready for input.
   * @param c the given {@code Component}.
   * @return {@code true} if the given {@code Component} is ready for input, {@code false} otherwise.
   * @throws ActionFailedException if the given {@code Component} does not have a {@code Window} ancestor.
   */
  boolean isReadyForInput(Component c);

  /**
   * Shows a pop-up menu.
   * @param invoker the component to invoke the pop-up menu from.
   * @return the displayed pop-up menu.
   * @throws org.fest.swing.exception.ComponentLookupException if a pop-up menu cannot be found.
   */
  JPopupMenu showPopupMenu(Component invoker);

  /**
   * Shows a pop-up menu at the given coordinates.
   * @param invoker the component to invoke the pop-up menu from.
   * @param location the given coordinates for the pop-up menu.
   * @return the displayed pop-up menu.
   * @throws ComponentLookupException if a pop-up menu cannot be found.
   */
  JPopupMenu showPopupMenu(Component invoker, Point location);

  /**
   * Returns the currently active pop-up menu, if any. If no pop-up is currently showing, returns {@code null}.
   * @return the currently active pop-up menu or {@code null}, if no pop-up is currently showing.
   */
  JPopupMenu findActivePopupMenu();

  /**
   * Ensures that there is no <code>{@link javax.swing.JOptionPane}</code> showing, and potentially blocking GUI tests.
   * @throws AssertionError if there is one or more <code>JOptionPane</code>s showing on the screen.
   */
  void requireNoJOptionPaneIsShowing();

  /**
   * Returns the configuration settings for this <code>{@link Robot}</code>.
   * @return the configuration settings for this <code>Robot</code>.
   */
  Settings settings();

  /**
   * Indicates whether this <code>Robot</code> is active. Being "active" means that <code>{@link #cleanUp()}</code> has
   * not been called yet.
   * @return {@code true} if this <code>Robot</code> is active, {@code false} otherwise.
   */
  boolean isActive();
}