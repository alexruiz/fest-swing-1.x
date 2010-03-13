/*
 * Created on Mar 12, 2010
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
package org.fest.swing.keystroke;

import static java.awt.event.InputEvent.*;
import static java.awt.event.KeyEvent.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.keystroke.KeyStrokeMappingProvider.NO_MASK;
import static org.fest.util.Collections.list;

import java.util.Collection;

import javax.swing.KeyStroke;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link KeyStrokeMappingsParser#mappingFrom(String)}</code>.
 *
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class KeyStrokeMappingsParser_mappingFrom_Test {

  private final String lineToParse;
  private final char character;
  private final int keyCode;
  private final int modifiers;

  @Parameters
  public static Collection<Object[]> linesToParse() {
    return list(
        new Object[][] {
            { "a, A, NO_MASK", 'a', VK_A, NO_MASK },
            { "A, A, SHIFT_MASK", 'A', VK_A, shift() },
            { "COMMA,COMMA,NO_MASK", ',', VK_COMMA, NO_MASK },
            { "COMMA, COMMA, NO_MASK", ',', VK_COMMA, NO_MASK },
            { "  COMMA,  COMMA,  NO_MASK", ',', VK_COMMA, NO_MASK },
        }
    );
  }

  private static int shift() {
    return SHIFT_MASK | SHIFT_DOWN_MASK;
  }

  public KeyStrokeMappingsParser_mappingFrom_Test(String lineToParse, char character, int keyCode, int modifiers) {
    this.lineToParse = lineToParse;
    this.character = character;
    this.keyCode = keyCode;
    this.modifiers = modifiers;
  }

  private KeyStrokeMappingsParser parser;

  @Before
  public void setUp() {
    parser = new KeyStrokeMappingsParser();
  }

  @Test
  public void should_create_mapping_from_line() {
    KeyStrokeMapping mapping = parser.mappingFrom(lineToParse);
    verifyCharacterIn(mapping);
    verifyKeyStrokeIn(mapping);
  }

  private void verifyCharacterIn(KeyStrokeMapping mapping) {
    assertThat(mapping.character()).isEqualTo(character);
  }

  private void verifyKeyStrokeIn(KeyStrokeMapping mapping) {
    KeyStroke keyStroke = mapping.keyStroke();
    assertThat(keyStroke.getKeyCode()).isEqualTo(keyCode);
    assertThat(keyStroke.getModifiers()).isEqualTo(modifiers);
  }
}
