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

import static java.awt.event.KeyEvent.CHAR_UNDEFINED;

import static org.fest.keyboard.mapping.MappingNotFoundError.mappingNotFound;
import static org.fest.util.Strings.isEmpty;

/**
 * Understands conversion of characters to text.
 *
 * @author Alex Ruiz
 */
final class CharToText {

  static String charToText(char character) throws MappingNotFoundError {
    if (character == CHAR_UNDEFINED) throw mappingNotFound();
    String text = new String(new char[] { character }).trim();
    if (isEmpty(text)) throw mappingNotFound();
    return text;
  }

  private CharToText() {}
}
