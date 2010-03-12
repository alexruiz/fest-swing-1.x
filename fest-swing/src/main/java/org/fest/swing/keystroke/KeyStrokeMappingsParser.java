/*
 * Created on Mar 12, 2010
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
package org.fest.swing.keystroke;

import static java.util.Collections.unmodifiableList;
import static org.fest.reflect.core.Reflection.staticField;
import static org.fest.swing.keystroke.KeyStrokeMapping.mapping;
import static org.fest.swing.keystroke.KeyStrokeMappingProvider.NO_MASK;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;

/**
 * Understands creation of <code>{@link KeyStrokeMapping}</code>s by parsing a text file.
 *
 * @author Olivier DOREMIEUX
 * @author Alex Ruiz
 */
public class KeyStrokeMappingsParser {

  public List<KeyStrokeMapping> parse(String file) throws IOException {
    List<KeyStrokeMapping> mappings = new ArrayList<KeyStrokeMapping>();
    BufferedReader reader = fileReader(file);
    String line = null;
    while((line = reader.readLine()) != null)
      mappings.add(mappingFrom(line));
    return unmodifiableList(mappings);
  }

  private BufferedReader fileReader(String file) {
    InputStream fileAsStream = KeyStrokeMapping.class.getClassLoader().getResourceAsStream(file);
    return new BufferedReader(new InputStreamReader(fileAsStream));
  }

  // package-protected for testing
  KeyStrokeMapping mappingFrom(String line) {
    String[] parts = line.split(",");
    if (parts.length != 3) return null;
    char character = characterFrom(parts[0].trim());
    int keyCode = keyCodeFrom(parts[1].trim());
    int modifiers = modifiersFrom(parts[2].trim());
    return mapping(character, keyCode, modifiers);
  }

  private char characterFrom(String character) {
    return character.charAt(0);
  }

  private int keyCodeFrom(String keyCode) {
    return staticField(keyCode).ofType(int.class).in(KeyEvent.class).get();
  }

  private int modifiersFrom(String modifiers) {
    if ("NO_MASK".equals(modifiers)) return NO_MASK;
    return staticField(modifiers).ofType(int.class).in(InputEvent.class).get();
  }
}
