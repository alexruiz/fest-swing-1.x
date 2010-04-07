/*
 * Created on Apr 2, 2010
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 * 
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import static java.awt.event.KeyEvent.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Understands SOMETHING DUMMY
 * 
 * @author alruiz
 */
final class KeyCodes {

  private static final List<Integer> keyCodesToIgnore = new ArrayList<Integer>();

  static {
    keyCodesToIgnore.add(VK_BACK_SPACE);
    keyCodesToIgnore.add(VK_DELETE);
    keyCodesToIgnore.add(VK_DOWN);
    keyCodesToIgnore.add(VK_ENTER);
    keyCodesToIgnore.add(VK_ESCAPE);
    keyCodesToIgnore.add(VK_LEFT);
    keyCodesToIgnore.add(VK_RIGHT);
    keyCodesToIgnore.add(VK_SHIFT);
    keyCodesToIgnore.add(VK_TAB);
    keyCodesToIgnore.add(VK_UP);
  }

  static boolean shouldIgnore(int keyCode) {
    return keyCodesToIgnore.contains(keyCode);
  }
}
