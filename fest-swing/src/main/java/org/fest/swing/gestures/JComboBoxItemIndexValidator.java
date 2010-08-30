/*
 * Created on Nov 10, 2008
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

import static java.lang.String.valueOf;
import static org.fest.util.Strings.concat;

import javax.swing.JComboBox;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Validates that a given number is a valid index of an item in a <code>{@link JComboBox}</code>.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.)
 * Clients are responsible for invoking them in the EDT.
 * </p>
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
final class JComboBoxItemIndexValidator {

  @RunsInCurrentThread
  static void validateIndex(JComboBox comboBox, int index) {
    if (index < 0) throw invalidIndex(concat(itemIndex(index), " should not be less than zero"));
    int itemCount = comboBox.getItemCount();
    if (itemCount == 0) throw invalidIndex("JComboBox is empty");
    if (index >= 0 && index < itemCount) return;
    throw invalidIndex(concat(
        itemIndex(index), " should be between [", valueOf(0), "] and [", valueOf(itemCount - 1), "] (inclusive)"));
  }

  private static String itemIndex(int index) {
    return concat("Item index (", valueOf(index), ")");
  }

  private static IndexOutOfBoundsException invalidIndex(String msg) {
    throw new IndexOutOfBoundsException(msg);
  }

  private JComboBoxItemIndexValidator() {}
}
