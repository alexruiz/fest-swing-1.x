/*
 * Created on Sep 21, 2007
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
package org.fest.swing.core;

import static org.fest.swing.util.Arrays.copyOf;

import java.awt.Event;
import java.awt.event.KeyEvent;

import org.fest.swing.util.Platform;

/**
 * Understands information about pressing a keyboard key.
 * <p>
 * Examples:
 * </p>
 * <p>
 * Specify that 'CTRL' + 'C' should be pressed:
 * <pre>
 * // import static org.fest.swing.fixture.KeyPressInfo.*;
 * KeyPressInfo i = key(VK_C).modifiers(CTRL_MASK);
 * </pre>
 * </p>
 * <p>
 * Specify that 'SHIFT' + 'R' should be pressed:
 * <pre>
 * // import static org.fest.swing.fixture.KeyPressInfo.*;
 * KeyPressInfo i = key(VK_R).modifiers(SHIFT_MASK);
 * </pre>
 * </p>
 * <p>
 * For platform-safe mask pressing (e.g. 'Control' in Windows or 'Command' in MacOS) use
 * <code>{@link Platform#controlOrCommandMask()}</code>.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class KeyPressInfo {

  private final int keyCode;
  private int[] modifiers;

  /**
   * Specifies the code of the key to press, without any modifiers (e.g.
   * <code>{@link KeyEvent#VK_C KeyEvent.VK_C}</code>.)
   * @param keyCode the code of the key to press.
   * @return the created <code>KeyPressInfo</code>.
   */
  public static KeyPressInfo keyCode(int keyCode) {
    return new KeyPressInfo(keyCode, new int[0]);
  }

  private KeyPressInfo(int keyCode, int[] modifiers) {
    this.keyCode = keyCode;
    this.modifiers = modifiers;
  }

  /**
   * Returns the code of the key to press.
   * @return the code of the key to press.
   */
  public int keyCode() { return keyCode; }

  /**
   * Returns the modifiers to use when pressing <code>{@link #keyCode() the specified key}</code>.
   * @return the modifiers to use.
   */
  public int[] modifiers() {
    return copyOf(modifiers);
  }

  /**
   * Specifies the modifiers to use when pressing <code>{@link #keyCode() the specified key}</code> (e.g.
   * <code>{@link Event#CTRL_MASK Event.CTRL_MASK}</code>.)
   * <p>
   * For platform-safe mask pressing (e.g. 'Control' in Windows or 'Command' in MacOS) use
   * <code>{@link Platform#controlOrCommandMask()}</code>.
   * </p>
   * @param newModifiers the new modifiers to use.
   * @return this object.
   * @throws NullPointerException if <code>newModifiers</code> is {@code null}.
   */
  public KeyPressInfo modifiers(int... newModifiers) {
    if (newModifiers == null) throw new NullPointerException("The array of modifiers should not be null");
    modifiers = copyOf(newModifiers);
    return this;
  }
}
