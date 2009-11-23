/*
 * Created on Apr 29, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.util;

import static org.fest.util.Strings.quote;

import java.util.*;
import java.util.Arrays;

/**
 * Understands utility methods related to collections.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class Collections {

  /**
   * Creates a list containing the given elements.
   * @param <T> the type of elements of the list to create.
   * @param elements the elements to store in the list.
   * @return a list containing the given elements.
   */
  public static <T> List<T> list(T... elements) {
    if (elements == null) return null;
    return new ArrayList<T>(Arrays.asList(elements));
  }

  /**
   * Returns any duplicate elements from the given collection.
   * @param <T> the generic type of the given collection.
   * @param c the given collection that might have duplicate elements.
   * @return a collection containing the duplicate elements of the given one. If no duplicates are found, an empty
   *          collection is returned.
   */
  public static <T> Collection<T> duplicatesFrom(Collection<T> c) {
    Set<T> duplicates = new HashSet<T>();
    if (isEmpty(c)) return duplicates;
    Set<T> onlyOne = new HashSet<T>();
    for(T e : c) {
      if (onlyOne.contains(e)) {
        duplicates.add(e);
        continue;
      }
      onlyOne.add(e);
    }
    return duplicates;
  }

  /**
   * Returns <code>true</code> if the given collection is <code>null</code> or empty.
   * @param c the collection to check.
   * @return <code>true</code> if the given collection is <code>null</code> or empty, otherwise <code>false</code>.
   */
  public static boolean isEmpty(Collection<?> c) {
    return c == null || c.isEmpty();
  }

  private Collections() {}

  public static <T> List<T> filter(Collection<?> target, CollectionFilter<T> filter) {
    return filter.filter(target);
  }

  /**
   * Returns the <code>String</code> representation of the given collection, or <code>null</code> if the given
   * collection is <code>null</code>.
   * @param c the collection to format.
   * @return the <code>String</code> representation of the given collection.
   */
  public static String format(Collection<?> c) {
    if (c == null) return null;
    Iterator<?> i = c.iterator();
    if (!i.hasNext()) return "[]";
    StringBuilder b = new StringBuilder();
    b.append('[');
    for (;;) {
      Object e = i.next();
      b.append(e == c ? "(this Collection)" : quote(e));
      if (!i.hasNext()) return b.append(']').toString();
      b.append(", ");
    }
  }

}
