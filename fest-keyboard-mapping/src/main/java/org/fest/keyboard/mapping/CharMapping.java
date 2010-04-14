/*
 * Created on Apr 2, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import java.awt.event.KeyEvent;

import static org.fest.keyboard.mapping.CharToText.charToText;
import static org.fest.keyboard.mapping.KeyCodeToText.keyCodeToText;
import static org.fest.keyboard.mapping.ModifierToText.modifierToText;
import static org.fest.util.Objects.*;
import static org.fest.util.Strings.join;

/**
 * Understands mappings of characters to key codes.
 *
 * @author Alex Ruiz
 */
class CharMapping {

  final String character;
  final String keyCode;
  final String modifier;

  static CharMapping newCharMapping(KeyEvent event) throws MappingNotFoundError {
    return newCharMapping(event.getKeyChar(), event.getKeyCode(), event.getModifiers());
  }

  private static CharMapping newCharMapping(char character, int keyCode, int modifiers) throws MappingNotFoundError {
    return new CharMapping(charToText(character), keyCodeToText(keyCode), modifierToText(modifiers));
  }

  CharMapping(String character, String keyCode, String modifier) {
    this.character = character;
    this.keyCode = keyCode;
    this.modifier = modifier;
  }

  @Override public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    if (getClass() != o.getClass()) return false;
    CharMapping other = (CharMapping)o;
    if (!areEqual(character, other.character)) return false;
    if (!areEqual(keyCode, other.keyCode)) return false;
    return areEqual(modifier, other.modifier);
  }

  @Override public int hashCode() {
    int prime = HASH_CODE_PRIME;
    int result = 1;
    result = prime * result + hashCodeFor(character);
    result = prime * result + hashCodeFor(keyCode);
    result = prime * result + hashCodeFor(modifier);
    return result;
  }
  
  @Override public String toString() {
    return join(formattedCharacter(), keyCode, modifier).with(", ");
  };
  
  private String formattedCharacter() {
    if (",".equals(character)) return "COMMA";
    return character;
  }
}
