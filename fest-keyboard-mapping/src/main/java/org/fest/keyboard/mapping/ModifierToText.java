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

import static java.awt.event.InputEvent.*;

import java.util.*;
import java.util.Map.Entry;

/**
 * Understands of a modifier to text.
 *
 * @author Alex Ruiz
 */
final class ModifierToText {

  private static final String NO_MASK = "NO_MASK";
  private static final Map<Integer, String> modifiers = new LinkedHashMap<Integer, String>();

  static {
    modifiers.put(SHIFT_MASK, "SHIFT_MASK");
    modifiers.put(ALT_GRAPH_MASK, "ALT_GRAPH_MASK");
  }

  static String modifierToText(int modifier) {
    for (Entry<Integer, String> entry : modifiers.entrySet())
      if ((modifier & entry.getKey()) != 0) return entry.getValue();
    return NO_MASK;
  }

  private ModifierToText() {}
}
