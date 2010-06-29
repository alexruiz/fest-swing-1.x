/*
 * Created on Jun 26, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import static java.util.Collections.emptyList;
import static org.fest.util.Collections.*;
import static org.fest.util.Introspection.descriptorForProperty;
import static org.fest.util.Strings.*;

import java.beans.PropertyDescriptor;
import java.util.*;

import org.fest.util.IntrospectionError;

/**
 * Understands utility methods related to properties.
 *
 * @author Joel Costigliola
 * @author Alex Ruiz
 *
 * @since 1.3
 */
final class PropertySupport {

  private static final String SEPARATOR = ".";

  /**
   * Returns a list containing the values of the given property name, from the elements of the given collection. If the
   * given collection is empty or <code>null</code>, this method will return an empty collection.
   * <p>
   * For example, given the nested property "address.street.number", this method will:
   * <ol>
   * <li>extract a collection of "address" from the given collection (remaining property is 'street.number')</li>
   * <li>extract a collection of "street" from the "address" collection (remaining property is 'number')</li>
   * <li>extract a collection of "number" from the "street" collection</li>
   * </ol>
   * </p>
   * @param propertyName the name of the property. It may be a nested property.
   * @param target the given collection.
   * @return a list containing the values of the given property name, from the elements of the given collection.
   * @throws NullPointerException if given property name is <code>null</code>.
   * @throws IntrospectionError if an element in the given collection does not have a matching property.
   */
  static List<Object> propertyValues(String propertyName, Collection<?> target) {
    if (isEmpty(target) || hasOnlyNullElements(target)) return emptyList();
    // ignore null elements as we can't extract a property from a null object
    Collection<?> nonNullElements = nonNullElements(target);
    if (isNestedProperty(propertyName)) {
      String firstProperty = firstPropertyIfNested(propertyName);
      List<Object> firstPropertyValues = propertyValues(firstProperty, nonNullElements);
      // extract next sub property values until reaching a last sub property
      return propertyValues(removeFirstPropertyIfNested(propertyName), firstPropertyValues);
    }
    return simplePropertyValues(propertyName, nonNullElements);
  }

  /**
   * Returns <code>true</code> if property is nested, <code>false</code> otherwise.
   * <p>
   * Examples:
   * <pre>
   * isNestedProperty("address.street"); // true
   * isNestedProperty("address.street.name"); // true
   * isNestedProperty("person"); // false
   * isNestedProperty(".name"); // false
   * isNestedProperty("person."); // false
   * isNestedProperty("person.name."); // false
   * isNestedProperty(".person.name"); // false
   * isNestedProperty("."); // false
   * isNestedProperty(""); // false
   * </pre>
   * @param propertyName the given property name.
   * @return <code>true</code> if property is nested, <code>false</code> otherwise.
   * @throws NullPointerException if given property name is <code>null</code>.
   */
  static boolean isNestedProperty(String propertyName) {
    if (propertyName == null) throw new NullPointerException("The property name to verify should not be null");
    return propertyName.contains(SEPARATOR) && !propertyName.startsWith(SEPARATOR) && !propertyName.endsWith(SEPARATOR);
  }

  /**
   * Removes the first property from the given property name only if the given property name belongs to a nested
   * property. For example, given the nested property "address.street.name", this method will return "street.name". This
   * method returns an empty {@code String} if the given property name does not belong to a nested property.
   * @param propertyName the given property name.
   * @return the given property name without its first property, if the property name belongs to a nested property;
   * otherwise, it will return an empty {@code String}.
   * @throws NullPointerException if given property name is <code>null</code>.
   */
  static String removeFirstPropertyIfNested(String propertyName) {
    if (!isNestedProperty(propertyName)) return "";
    return propertyName.substring(propertyName.indexOf(SEPARATOR) + 1);
  }

  /**
   * Returns the first property from the given property name only if the given property name belongs to a nested
   * property. For example, given the nested property "address.street.name", this method will return "address". This
   * method returns the given property name unchanged if it does not belong to a nested property.
   * @param propertyName the given property name.
   * @return the first property from the given property name, if the property name belongs to a nested property;
   * otherwise, it will return the given property name unchanged.
   * @throws NullPointerException if given property name is <code>null</code>.
   */
  static String firstPropertyIfNested(String propertyName) {
    if (!isNestedProperty(propertyName)) return propertyName;
    return propertyName.substring(0, propertyName.indexOf(SEPARATOR));
  }

  private static List<Object> simplePropertyValues(String propertyName, Collection<?> target) {
    List<Object> propertyValues = new ArrayList<Object>();
    for (Object e : target)
      propertyValues.add(propertyValue(propertyName, e));
    return propertyValues;
  }

  private static Object propertyValue(String propertyName, Object target) {
    PropertyDescriptor descriptor = descriptorForProperty(propertyName, target);
    try {
      return descriptor.getReadMethod().invoke(target);
    } catch (Exception e) {
      throw new IntrospectionError(concat("Unable to obtain the value in property " + quote(propertyName)), e);
    }
  }

  private PropertySupport() {}
}
