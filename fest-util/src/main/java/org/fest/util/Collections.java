/*
 * Created on Apr 29, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.util;

import static java.util.Collections.*;
import static org.fest.util.Strings.quote;

import java.util.*;
import java.util.Arrays;

/**
 * Understands utility methods related to collections.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Joel Costigliola
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
   * collection is returned.
   */
  public static <T> Collection<T> duplicatesFrom(Collection<T> c) {
    Set<T> duplicates = new HashSet<T>();
    if (isEmpty(c)) return duplicates;
    Set<T> onlyOne = new HashSet<T>();
    for (T e : c) {
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

  /**
   * Returns a new unmodifiable collection containing the non-null elements of the given collection. This method returns
   * an empty unmodifiable collection if the given collection has only <code>null</code> elements or if it is empty. This
   * method returns <code>null</code> if the given collection is <code>null</code>.
   * @param <T> the type of elements of the collection.
   * @param c the collection we want to extract non null elements from.
   * @return a new unmodifiable collection containing the non-null elements of the given collection, or
   * <code>null</code> if the given collection is <code>null</code>.
   * @since 1.1.3
   */
  public static <T> Collection<T> nonNullElements(Collection<T> c) {
    if (c == null) return null;
    Collection<T> nonNullElements = new ArrayList<T>();
    for (T o : c) if (o != null) nonNullElements.add(o);
    return unmodifiableCollection(nonNullElements);
  }

  /**
   * Returns a new unmodifiable list containing the non-null elements of the given list. This method returns an empty
   * unmodifiable list if the given list has only <code>null</code> elements or if it is empty. This method returns
   * <code>null</code> if the given list is <code>null</code>.
   * @param <T> the type of elements of the list.
   * @param l the list we want to extract non null elements from.
   * @return a new unmodifiable list containing the non-null elements of the given list, or <code>null</code> if the
   * given list is <code>null</code>.
   * @since 1.1.3
   */
  public static <T> List<T> nonNullElements(List<T> l) {
    Collection<T> nonNullElements = nonNullElements((Collection<T>)l);
    if (nonNullElements == null) return null;
    return unmodifiableList(new ArrayList<T>(nonNullElements));
  }

  /**
   * Returns <code>true</code> if the given collection has only <code>null</code> elements, <code>false</code>
   * otherwise. If given collection is empty, this method returns <code>true</code>.
   * @param c the given collection. <b>It must not be null</b>.
   * @return <code>true</code> if the given collection has only <code>null</code> elements or is empty,
   * <code>false</code> otherwise.
   * @throws NullPointerException if the given collection is <code>null</code>.
   * @since 1.1.3
   */
  public static boolean hasOnlyNullElements(Collection<?> c) {
    if (c == null) throw new NullPointerException("The collection to check should not be null");
    if (c.isEmpty()) return false;
    for (Object element : c) if (element != null) return false;
    return true;
  }
}
