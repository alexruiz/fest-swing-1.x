/*
 * Created on Mar 26, 2008
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
package org.fest.swing.keystroke;

import static org.fest.util.Objects.HASH_CODE_PRIME;

import javax.swing.KeyStroke;

/**
 * Understands a mapping between a character and a <code>{@link KeyStroke}</code>.
 *
 * @author Yvonne Wang
 */
public class KeyStrokeMapping {

  private final char character;
  private final KeyStroke keyStroke;

  /**
   * Creates a new </code>{@link KeyStrokeMapping}</code>.
   * @param character the character corresponding to the intended <code>KeyStroke</code>.
   * @param keyCode the numeric key code for the intended <code>KeyStroke</code>.
   * @param modifiers the set of modifiers for the intended <code>KeyStroke</code>.
   * @return the created <code>KeyStrokeMapping</code>.
   */
  public static KeyStrokeMapping mapping(char character, int keyCode, int modifiers) {
    return new KeyStrokeMapping(character, keyCode, modifiers);
  }

  /**
   * Creates a new </code>{@link KeyStrokeMapping}</code>.
   * @param character the character corresponding to the intended <code>KeyStroke</code>.
   * @param keyCode the numeric key code for the intended <code>KeyStroke</code>.
   * @param modifiers the set of modifiers for the intended <code>KeyStroke</code>.
   */
  public KeyStrokeMapping(char character, int keyCode, int modifiers) {
    this(character, KeyStroke.getKeyStroke(keyCode, modifiers));
  }

  /**
   * Creates a new </code>{@link KeyStrokeMapping}</code>.
   * @param character the character corresponding to the given <code>KeyStroke</code>.
   * @param keyStroke the <code>KeyStroke</code> corresponding to the given character.
   */
  public KeyStrokeMapping(char character, KeyStroke keyStroke) {
    this.character = character;
    this.keyStroke = keyStroke;
  }

  /**
   * Returns the character corresponding to this mapping's <code>{@link #keyStroke()}</code>.
   * @return the character corresponding to this mapping's <code>KeyStroke</code>.
   */
  public char character() {
    return character;
  }

  /**
   * Returns the <code>{@link KeyStroke}</code> corresponding to this mapping's <code>{@link #character()}</code>.
   * @return the <code>KeyStroke</code> corresponding to this mapping's character.
   */
  public KeyStroke keyStroke() {
    return keyStroke;
  }

  /** @see Object#equals(Object) */
  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (!(o instanceof KeyStrokeMapping)) return false;
    KeyStrokeMapping other = (KeyStrokeMapping) o;
    if (character != other.character) return false;
    if (keyStroke == null) return other.keyStroke == null;
    if (other.keyStroke == null) return false;
    if (keyStroke.getKeyCode() != other.keyStroke.getKeyCode()) return false;
    return keyStroke.getModifiers() == other.keyStroke.getModifiers();
  }

  /** @see Object#hashCode() */
  @Override public int hashCode() {
    int prime = HASH_CODE_PRIME;
    int result = 1;
    result = prime * result + character;
    if (keyStroke == null) return result;
    result = prime * result + keyStroke.getKeyCode();
    result = prime * result + keyStroke.getModifiers();
    return result;
  }

  /** @see Object#toString() */
  @Override public String toString() {
    StringBuilder b = new StringBuilder();
    b.append(getClass().getSimpleName()).append("[");
    b.append("character='").append(character).append("',");
    b.append("keyStroke=").append(keyStroke).append("]");
    return b.toString();
  }
}
