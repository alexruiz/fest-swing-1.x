/*
 * Created on Oct 31, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static java.lang.String.valueOf;
import static org.fest.util.Strings.concat;

import javax.swing.JList;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands verification that a given number is a valid index of an item in a <code>{@link JList}</code>.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 *
 * @author Alex Ruiz
 */
final class JListItemIndexValidator {

  @RunsInCurrentThread
  static void validateIndex(JList list, int index) {
    validateIndex(index, list.getModel().getSize());
  }

  @RunsInCurrentThread
  static void validateIndices(final JList list, int...indices) {
    int itemCount = list.getModel().getSize();
    for (int index : indices) validateIndex(index, itemCount);
  }

  private static void validateIndex(int index, int itemCount) {
    if (index >= 0 && index < itemCount) return;
    throw new IndexOutOfBoundsException(concat(
        "Item index (", valueOf(index), ") should be between [", valueOf(0), "] and [", valueOf(itemCount - 1), "] (inclusive)"));
  }

  private JListItemIndexValidator() {}
}
