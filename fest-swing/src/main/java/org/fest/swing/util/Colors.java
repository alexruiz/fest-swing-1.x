/*
 * Created on Apr 15, 2008
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
package org.fest.swing.util;

import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.awt.Color;

import org.fest.util.Strings;

/**
 * Understands utility methods related to colors.
 *
 * @author Alex Ruiz
 */
public final class Colors {

  /**
   * Returns a <code>{@link Color}</code> from the given <code>String</code> containing the hexadecimal coding of a 
   * color. 
   * @param hexString contains the hexadecimal coding of a color.
   * @return a <code>Color</code> from the given <code>String</code> containing the hexadecimal coding of a color. 
   * @throws NullPointerException if the hexadecimal code is {@code null}.
   * @throws IllegalArgumentException if the hexadecimal code is empty.
   * @throws NumberFormatException if the hexadecimal code is empty.
   */
  public static Color colorFromHexString(String hexString) {
    if (hexString == null) throw new NullPointerException("The hexadecimal code should not be null");
    if (Strings.isEmpty(hexString)) throw new IllegalArgumentException("The hexadecimal code should not be empty");
    try {
      return new Color(Integer.parseInt(hexString, 16));
    } catch (NumberFormatException e) {
      throw new NumberFormatException(concat("The hexadecimal code ", quote(hexString), " is not a valid color code"));
    }
  }
  
  private Colors() {}
}
