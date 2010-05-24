/*
 * Created on May 24, 2010
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
package org.fest.javafx.format;

import static java.lang.String.valueOf;

import static org.fest.util.Strings.quote;

import java.util.List;

import net.jcip.annotations.NotThreadSafe;

import javafx.scene.Node;

/**
 * Understands a builder of formatted properties.
 *
 * @author Alex Ruiz
 */
@NotThreadSafe
class PropertyBuilder {

  private final StringBuilder buffer = new StringBuilder();

  private boolean hasProperties;

  PropertyBuilder(Node n) {
    String typeName = n.getClass().getName();
    buffer.append(typeName).append("[");
  }
  
  void add(String propertyName, boolean propertyValue) {
    addProperty(propertyName, valueOf(propertyValue));
  }

  void add(String propertyName, Object propertyValue) {
    addProperty(propertyName, quote(propertyValue));
  }
  
  private void addProperty(String name, Object value) {
    aboutToAddProperty();
    buffer.append(name).append("=").append(value);
  }
  
  void add(List<String> properties) {
    aboutToAddProperty();
    int propertyCount = properties.size();
    for (int i = 0; i < propertyCount; i++) {
      if (i > 0) appendComma();
      buffer.append(properties.get(i));
    }
  }
  
  private void aboutToAddProperty() {
    if (!hasProperties) {
      hasProperties = true;
      return;
    }
    // we already have properties in this builder, append a comma to separate the property to add.
    appendComma();
  }

  private StringBuilder appendComma() {
    return buffer.append(", ");
  }

  String value() {
    buffer.append("]");
    return buffer.toString();
  }
}
