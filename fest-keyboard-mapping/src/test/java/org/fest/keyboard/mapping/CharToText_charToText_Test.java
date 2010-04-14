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

import org.junit.Test;

import static java.awt.event.KeyEvent.CHAR_UNDEFINED;

import static org.fest.assertions.Assertions.assertThat;

/**
 * Tests for <code>{@link CharToText#charToText(char)}</code>.
 *
 * @author Alex Ruiz
 */
public class CharToText_charToText_Test {

  @Test
  public void should_return_text_from_single_char() throws MappingNotFoundError {
    assertThat(CharToText.charToText('c')).isEqualTo("c");
  }

  @Test(expected = MappingNotFoundError.class)
  public void should_throw_error_if_generated_text_is_empty() throws MappingNotFoundError {
    CharToText.charToText(' ');
  }

  @Test(expected = MappingNotFoundError.class)
  public void should_throw_error_if_character_is_invalid() throws MappingNotFoundError {
    CharToText.charToText(CHAR_UNDEFINED);
  }

}
