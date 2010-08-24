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
package org.fest.swing.gestures;

import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.util.Strings.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.InputMap;
import javax.swing.KeyStroke;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Utility methods related to <code>{@link KeyStroke}</code>.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.)
 * Clients are responsible for invoking them in the EDT.
 * </p>
 *
 * @author Alex Ruiz
 */
final class KeyStrokes {

  @RunsInCurrentThread
  static KeyStroke[] findKeyStrokesForAction(String actionName, Object actionKey, InputMap inputMap) {
    List<KeyStroke> keyStrokes = new ArrayList<KeyStroke>();
    for (KeyStroke keyStroke : inputMap.allKeys()) {
      if (actionKey.equals(inputMap.get(keyStroke)))
        keyStrokes.add(keyStroke);
    }
    int size = keyStrokes.size();
    if (size > 0) return keyStrokes.toArray(new KeyStroke[size]);
    throw actionFailure(concat("Unable to find valid input event for action with key ", quote(actionName)));
  }

  private KeyStrokes() {}
}
