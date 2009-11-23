/*
 * Created on Jul 8, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.event.KeyEvent;

import org.fest.swing.core.KeyPressInfo;
import org.fest.swing.util.Platform;

/**
 * Understands simulation of mouse input on a GUI component.
 *
 * @author Alex Ruiz 
 */
public interface KeyboardInputSimulationFixture {

  /**
   * Simulates a user pressing and releasing the given keys on this fixture's GUI component.
   * @param keyCodes one or more codes of the keys to press.
   * @return this fixture.
   * @throws NullPointerException if the given array of codes is <code>null</code>.
   * @throws IllegalArgumentException if any of the given code is not a valid key code.
   * @throws IllegalStateException if the component is disabled.
   * @throws IllegalStateException if the component is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  KeyboardInputSimulationFixture pressAndReleaseKeys(int...keyCodes);

  /**
   * Simulates a user pressing given key on this fixture's GUI component.
   * @param keyCode the code of the key to press.
   * @return this fixture.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if the component is disabled.
   * @throws IllegalStateException if the component is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  KeyboardInputSimulationFixture pressKey(int keyCode);

  /**
   * Simulates a user pressing given key with the given modifiers on this fixture's GUI component.
   * Modifiers is a mask from the available <code>{@link java.awt.event.InputEvent}</code> masks.
   * <p>
   * The following code listing shows how to press 'CTRL' + 'C' in a platform-safe way:
   * <pre>
   * JTextComponentFixture textBox = dialog.textBox(&quot;username&quot;);
   * textBox.selectAll()
   *        .pressAndReleaseKey(key(<code>{@link KeyEvent#VK_C VK_C}</code>).modifiers({@link Platform#controlOrCommandMask() controlOrCommandMask}())); 
   * </pre>
   * </p>
   * @param keyPressInfo specifies the key and modifiers to press.
   * @return this fixture.
   * @throws NullPointerException if the given <code>KeyPressInfo</code> is <code>null</code>.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if the component is disabled.
   * @throws IllegalStateException if the component is not showing on the screen.
   */
  KeyboardInputSimulationFixture pressAndReleaseKey(KeyPressInfo keyPressInfo);
  
  /**
   * Simulates a user releasing the given key on this fixture's GUI component.
   * @param keyCode the code of the key to release.
   * @return this fixture.
   * @throws IllegalArgumentException if the given code is not a valid key code.
   * @throws IllegalStateException if the component is disabled.
   * @throws IllegalStateException if the component is not showing on the screen.
   * @see java.awt.event.KeyEvent
   */
  KeyboardInputSimulationFixture releaseKey(int keyCode);
}