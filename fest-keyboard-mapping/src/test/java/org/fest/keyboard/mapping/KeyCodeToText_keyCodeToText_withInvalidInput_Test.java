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

/**
 * Tests for <code>{@link KeyCodeToText#keyCodeToText(int)}</code>.
 *
 * @author Alex Ruiz
 */
public class KeyCodeToText_keyCodeToText_withInvalidInput_Test {

  @Test(expected = MappingNotFoundError.class)
  public void should_throw_error_if_value_is_not_key_code() throws MappingNotFoundError {
    KeyCodeToText.keyCodeToText(Integer.MIN_VALUE);
  }
}
