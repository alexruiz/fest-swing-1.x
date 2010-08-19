/*
 * Created on Jan 27, 2008
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
package org.fest.swing.driver;

import static java.util.Collections.sort;
import static javax.swing.Action.NAME;
import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.util.Collections.isEmpty;
import static org.fest.util.Strings.*;

import java.util.*;

import javax.swing.*;

/**
 * Utility methods related to <code>{@link Action}</code>s.
 *
 * @author Alex Ruiz
 */
final class Actions {

  static Object findActionKey(String name, ActionMap actionMap) {
    Action action = actionMap.get(name);
    if (action != null) return name;
    for (Object key : actionMap.allKeys()) {
      action = actionMap.get(key);
      if (name.equals(action.getValue(NAME))) return key;
    }
    String message = concat("The action ", quote(name), " is not available");
    List<String> allKeys = formatAllActionKeys(actionMap);
    if (!isEmpty(allKeys)) message = concat(message, ", available actions:", allKeys);
    throw actionFailure(message);
  }

  private static List<String> formatAllActionKeys(ActionMap actionMap) {
    List<String> keys = new ArrayList<String>();
    Object[] allKeys = actionMap.allKeys();
    if (allKeys == null) return keys;
    for (Object key : allKeys) {
      String keyAsString = keyAsString(key);
      if (keyAsString != null) keys.add(keyAsString);
    }
    sort(keys);
    return keys;
  }

  private static String keyAsString(Object key) {
    if (key == null) return null;
    if (key instanceof String) return (String)quote(key);
    return concat(key.toString(), " (", key.getClass().getName(), ") ");
  }

  private Actions() {}
}
