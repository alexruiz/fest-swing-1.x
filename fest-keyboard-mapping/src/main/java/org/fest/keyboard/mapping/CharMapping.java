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

import static java.awt.event.KeyEvent.getKeyModifiersText;

import static org.fest.keyboard.mapping.CharToText.charsToText;
import static org.fest.keyboard.mapping.KeyCodeToText.keyCodeText;
import static org.fest.util.Strings.isEmpty;

/**
 * Understands mappings of characters to key codes.
 *
 * @author Alex Ruiz
 */
class CharMapping {

  private static final int INVALID_CHAR = 65535;

  final String character;
  final String keyCode;
  final String modifiers;

  static CharMapping newCharMapping(char character, int keyCode, int modifiers) {
    if (character == INVALID_CHAR) return null;
    String charText = charsToText(character);
    if (isEmpty(charText)) return null;
    String keyCodeText = keyCodeText(keyCode);
    if (isEmpty(keyCodeText)) return null;
    return new CharMapping(charText, keyCodeText, modifiers);
  }

  private CharMapping(String character, String keyCode, int modifiers) {
    this.character = character;
    this.keyCode = keyCode;
    this.modifiers = modifiers == 0 ? "NO_MASK" : getKeyModifiersText(modifiers);
  }
}
