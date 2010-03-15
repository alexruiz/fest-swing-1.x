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

import static java.lang.Thread.currentThread;
import static org.fest.reflect.core.Reflection.staticField;
import static org.fest.swing.keystroke.KeyStrokeMapping.mapping;
import static org.fest.swing.keystroke.KeyStrokeMappingProvider.NO_MASK;
import static org.fest.util.Closeables.close;
import static org.fest.util.Strings.*;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.*;

import org.fest.reflect.exception.ReflectionError;
import org.fest.swing.exception.ParsingException;

/**
 * Understands creation of <code>{@link KeyStrokeMapping}</code>s by parsing a text file.
 * <p>
 * Mappings for the following characters:
 * <ul>
 * <li>Backspace</li>
 * <li>Delete</li>
 * <li>Enter</li>
 * <li>Escape</li>
 * <li>Tab</li>
 * </ul>
 * will be automatically added and should <strong>not</strong> be included to the file to parse.
 * </p>
 * <p>
 * The following is an example of a mapping file:
 *
 * <pre>
 * a, A, NO_MASK
 * A, A, SHIFT_MASK
 * COMMA, COMMA, NO_MASK
 * </pre>
 *
 * Each line represents a character-keystroke mapping where each value is separated by a comma.
 * <p>
 * The first value represents the character to map. For example 'a' or 'A'. Since each field is separated by a comma, to
 * map the ',' character we need to specify the text "COMMA."
 * </p>
 * <p>
 * The second value represents the key code, which should be the name of a key code from <code>{@link KeyEvent}</code>
 * without the prefix "VK_". For example, if the key code is <code>{@link KeyEvent#VK_COMMA}</code> we just need to
 * specify "COMMA".
 * </p>
 * <p>
 * The third value represents any modifiers to use, which should be the name of a modifier from
 * <code>{@link InputEvent}</code>. For example, if the modifier to use is <code>{@link InputEvent#SHIFT_MASK}</code> we
 * need to specify "SHIFT_MASK". If no modifiers are necessary, we just specify "NO_MASK".
 * </p>
 *
 * @author Olivier DOREMIEUX
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public class KeyStrokeMappingsParser {

  private static final Map<String, Character> SPECIAL_MAPPINGS = new HashMap<String, Character>();

  static {
    SPECIAL_MAPPINGS.put("COMMA", ',');
  }

  /**
   * Creates a <code>{@link KeyStrokeMappingProvider}</code> containing all the character-keystroke mappings specified
   * in the file with the given name.
   * <p>
   * <strong>Note:</strong> This attempts to read the file using
   * <code>{@link ClassLoader#getResourceAsStream(String)}</code>.
   * </p>
   * @param file the name of the file to parse.
   * @return the created {@code KeyStrokeMappingProvider}.
   * @throws NullPointerException if the given name is <code>null</code>.
   * @throws IllegalArgumentException if the given name is empty.
   * @throws ParsingException if any error occurs during parsing.
   * @see #parse(File)
   */
  public KeyStrokeMappingProvider parse(String file) {
    validate(file);
    try {
      return parse(fileAsStream(file));
    } catch (IOException e) {
      throw new ParsingException(concat("An I/O error ocurred while parsing file ", file), e);
    }
  }

  private void validate(String file) {
    if (file == null)
      throw new NullPointerException("The name of the file to parse should not be null");
    if (isEmpty(file))
      throw new IllegalArgumentException("The name of the file to parse should not be an empty string");
  }

  private InputStream fileAsStream(String file) {
    InputStream stream = currentThread().getContextClassLoader().getResourceAsStream(file);
    if (stream == null) throw new ParsingException(concat("Unable to open file ", file));
    return stream;
  }

  /**
   * Creates a <code>{@link KeyStrokeMappingProvider}</code> containing all the character-keystroke mappings specified
   * in the given file.
   * @param file the file to parse.
   * @return the created {@code KeyStrokeMappingProvider}.
   * @throws NullPointerException if the given file is <code>null</code>.
   * @throws IllegalArgumentException if the given file does not represent an existing file.
   * @throws ParsingException if any error occurs during parsing.
   */
  public KeyStrokeMappingProvider parse(File file) {
    validate(file);
    try {
      return parse(fileAsStream(file));
    } catch (IOException e) {
      throw new ParsingException(concat("An I/O error ocurred while parsing file ", file), e);
    }
  }

  private void validate(File file) {
    if (file == null)
      throw new NullPointerException("The file to parse should not be null");
    if (!file.isFile())
      throw new IllegalArgumentException(concat("The file ", file.getPath(), " is not an existing file"));
  }

  private InputStream fileAsStream(File file) {
    try {
      return new FileInputStream(file);
    } catch (FileNotFoundException e) {
      throw new ParsingException(concat("The file ", file.getPath(), " was not found"), e);
    }
  }

  private KeyStrokeMappingProvider parse(InputStream input) throws IOException {
    List<KeyStrokeMapping> mappings = new ArrayList<KeyStrokeMapping>();
    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
    try {
      String line = reader.readLine();
      while(line != null) {
        mappings.add(mappingFrom(line));
        line = reader.readLine();
      }
      return new ParsedKeyStrokeMappingProvider(mappings);
    } finally {
      close(reader);
    }
  }

  // package-protected for testing
  KeyStrokeMapping mappingFrom(String line) {
    String[] parts = split(line);
    if (parts.length != 3) throw notConformingWithPatternError(line);
    char character = characterFrom(parts[0].trim());
    int keyCode = keyCodeFrom(parts[1].trim());
    int modifiers = modifiersFrom(parts[2].trim());
    return mapping(character, keyCode, modifiers);
  }

  private static String[] split(String line) {
    return line.trim().split(",");
  }

  private static ParsingException notConformingWithPatternError(String line) {
    return new ParsingException(concat(
        "Line ", quote(line), " does not conform with pattern '{char}, {keycode}, {modifiers}'"));
  }

  private static char characterFrom(String s) {
    if (SPECIAL_MAPPINGS.containsKey(s)) return SPECIAL_MAPPINGS.get(s);
    if (s.length() == 1) return s.charAt(0);
    throw new ParsingException(concat("The text ", quote(s) , " should have a single character"));
  }

  private static int keyCodeFrom(String s) {
    try {
      return staticField(keyCodeNameFrom(s)).ofType(int.class).in(KeyEvent.class).get();
    } catch (ReflectionError e) {
      throw new ParsingException(concat("Unable to retrieve key code from text ", quote(s)), e.getCause());
    }
  }

  private static String keyCodeNameFrom(String s) {
    return concat("VK_", s);
  }

  private static int modifiersFrom(String s) {
    if ("NO_MASK".equals(s)) return NO_MASK;
    try {
      return staticField(s).ofType(int.class).in(InputEvent.class).get();
    } catch (ReflectionError e) {
      throw new ParsingException(concat("Unable to retrieve modifiers from text ", quote(s)), e.getCause());
    }
  }
}
