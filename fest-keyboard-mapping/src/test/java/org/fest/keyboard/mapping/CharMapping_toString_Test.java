/*
 * Created on Apr 14, 2010
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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.keyboard.mapping.CharMapping.newCharMapping;

/**
 * Tests for <code>{@link CharMapping#toString()}</code>.
 *
 * @author Alex Ruiz
 */
public class CharMapping_toString_Test {

  @Test
  public void should_join_fields_with_comma() {
    CharMapping mapping = newCharMapping("a", "A", "NO_MASK");
    assertThat(mapping.toString()).isEqualTo("a, A, NO_MASK");
  }
  
  @Test
  public void should_replace_comma_character_with_word_comma() {
    CharMapping mapping = newCharMapping(",", "COMMA", "NO_MASK");
    assertThat(mapping.toString()).isEqualTo("COMMA, COMMA, NO_MASK");
  }
}
