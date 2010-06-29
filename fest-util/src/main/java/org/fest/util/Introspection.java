/*
 * Created on Jun 28, 2010
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
package org.fest.util;

import static org.fest.util.Strings.*;

import java.beans.*;

/**
 * Understands utility methods related to
 * <a href="http://java.sun.com/docs/books/tutorial/javabeans/introspection/index.html">JavaBeans Introspection</a>.
 *
 * @author Alex Ruiz
 */
public final class Introspection {

  /**
   * Returns a <code>{@link PropertyDescriptor}</code> for a property matching the given name in the given object.
   * @param propertyName the given property name.
   * @param target the given object.
   * @return a {@code PropertyDescriptor} for a property matching the given name in the given object.
   * @throws NullPointerException if the given property name is <code>null</code>.
   * @throws IllegalArgumentException if the given property name is empty.
   * @throws NullPointerException if the given object is <code>null</code>.
   * @throws IntrospectionError if a matching property cannot be found or accessed.
   */
  public static PropertyDescriptor descriptorForProperty(String propertyName, Object target) {
    validate(propertyName, target);
    BeanInfo beanInfo = null;
    Class<?> type = target.getClass();
    try {
      beanInfo = Introspector.getBeanInfo(type, Object.class);
    } catch (Exception e) {
      throw new IntrospectionError(concat("Unable to get BeanInfo for type ", type.getName()), e);
    }
    for (PropertyDescriptor d : beanInfo.getPropertyDescriptors())
      if (propertyName.equals(d.getName())) return d;
    throw new IntrospectionError(concat("Unable to find property ", quote(propertyName), " in ", type.getName()));
  }

  private static void validate(String propertyName, Object target) {
    if (propertyName == null) throw new NullPointerException("The property name should not be null");
    if (isEmpty(propertyName)) throw new IllegalArgumentException("The property name should not be empty");
    if (target == null) throw new NullPointerException("The target object should not be null");
  }

  private Introspection() {}
}
