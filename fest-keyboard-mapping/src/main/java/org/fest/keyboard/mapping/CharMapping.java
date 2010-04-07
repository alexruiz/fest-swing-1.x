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
import java.lang.reflect.Field;

import static java.awt.event.KeyEvent.getKeyModifiersText;

import static org.fest.keyboard.mapping.CharToText.toText;
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
    String characterText = toText(character);
    if (isEmpty(characterText)) return null;
    return new CharMapping(characterText, keyCode, modifiers);
  }

  private CharMapping(String character, int keyCode, int modifiers) {
    this.character = character;
    this.keyCode = keyCodeText(keyCode);
    this.modifiers = modifiers == 0 ? "NO_MASK" : getKeyModifiersText(modifiers);
  }

  private static String keyCodeText(int keyCode) {
    for (Field field : KeyEvent.class.getDeclaredFields()) {
      String name = field.getName();
      if (!name.startsWith("VK_") || !field.getType().equals(int.class)) continue;
      try {
        Integer value = (Integer)field.get(KeyEvent.class);
        if (keyCode != value) continue;
        return name.substring(3);
      } catch (Exception ex) {
        // TODO rethrow;
      }
    }
    return null;
  }
}
