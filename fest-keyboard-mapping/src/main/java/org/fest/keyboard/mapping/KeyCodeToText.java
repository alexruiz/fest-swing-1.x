/*
 * Created on Apr 7, 2010
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
import java.security.PrivilegedAction;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static java.security.AccessController.doPrivileged;

/**
 * Understands conversion of a key code to text.
 *
 * @author Alex Ruiz
 */
final class KeyCodeToText {

  private static final Map<Integer, String> keyCodes = new ConcurrentHashMap<Integer, String>();
   
  static String keyCodeText(int keyCode) {
    if (!keyCodes.containsKey(keyCode)) addToMap(keyCode);
    return keyCodes.get(keyCode);
  }

  private static void addToMap(int keyCode) {
    String text = findTextOf(keyCode);
    if (text == null) return;
    keyCodes.put(keyCode, text);
  }

  private static String findTextOf(int keyCode) {
    try {
      for (Field field : KeyEvent.class.getDeclaredFields()) {
        if (!isKeyCodeField(field) || keyCode != valueOf(field)) continue;
        return field.getName().substring(3);
      }
    } catch (Exception ignored) {}
    return null;
  }

  private static boolean isKeyCodeField(Field field) {
    return field.getName().startsWith("VK") && field.getType().equals(int.class);
  }

  private static int valueOf(Field field) throws IllegalAccessException {
    boolean accessible = makeAccessible(field, true);
    Integer value = (Integer)field.get(KeyEvent.class);
    makeAccessible(field, accessible);
    return value;
  }

  private static boolean makeAccessible(Field field, boolean accessible) {
    return doPrivileged(new MakeAccessibleAction(field, accessible));    
  }

  private static class MakeAccessibleAction implements PrivilegedAction<Boolean> {
    private final Field target;
    private final boolean accessible;

    MakeAccessibleAction(Field target, boolean accessible) {
      this.target = target;
      this.accessible = accessible;
    }
    
    public Boolean run() {
      boolean current = target.isAccessible();
      target.setAccessible(accessible);
      return current;
    }
  }
  
  private KeyCodeToText() {}
}
