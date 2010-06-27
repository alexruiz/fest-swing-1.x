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
import static org.fest.reflect.beanproperty.Invoker.descriptorForProperty;
import static org.fest.reflect.core.Reflection.property;
import static org.fest.reflect.util.Properties.*;
import static org.fest.util.Collections.*;

import java.util.*;

/**
 * Understands utility methods related to properties.
 *
 * @author Joel Costigliola
 * @author Alex Ruiz
 */
final class PropertySupport {

  /*
   * if property is nested, like 'address.street.number', extract sub-properties until reaching a simple property,
   * on our example :
   * 1. extract a collection of 'address' from collection elements (remaining property is 'street.number')
   * 2. extract a collection of 'street' from address collection (remaining property is 'number')
   * 3. extract a collection of 'number' from street collection
   *
   * TODO : to be replaced by fest reflect nested property support when available
   */
  static List<Object> propertyValues(String propertyName, Collection<?> collection) {
    if (isEmpty(collection) || hasOnlyNullElements(collection)) return emptyList();
    // ignore null elements as we can't extract a property from a null object
    Collection<?> nonNullElements = nonNullElements(collection);
    if (isNestedProperty(propertyName)) {
      String firstProperty = firstPropertyIfNested(propertyName);
      List<Object> firstPropertyValues = propertyValues(firstProperty, nonNullElements);
      // extract next sub property values until reaching a last sub property
      return propertyValues(removeFirstPropertyIfNested(propertyName), firstPropertyValues);
    }
    return simplePropertyValues(propertyName, nonNullElements);
  }

  private static List<Object> simplePropertyValues(String propertyName, Collection<?> target) {
    Class<?> propertyType = null;
    List<Object> propertyValues = new ArrayList<Object>();
    for (Object e : target) {
      if (propertyType == null) propertyType = descriptorForProperty(propertyName, e).getPropertyType();
      Object propertyValue = property(propertyName).ofType(propertyType).in(e).get();
      propertyValues.add(propertyValue);
    }
    return propertyValues;
  }

  private PropertySupport() {}
}
