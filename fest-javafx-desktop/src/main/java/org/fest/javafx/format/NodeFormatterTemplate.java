/*
 * Created on May 21, 2010
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

import java.util.*;

import org.fest.javafx.annotations.RunsInCurrentThread;
import javafx.scene.Node;

import static java.lang.String.valueOf;
import static java.util.Collections.emptyList;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

/**
 * Understands a basic implementation of <code>{@link NodeFormatter}</code>.
 *
 * @author Alex Ruiz
 */
public class NodeFormatterTemplate implements NodeFormatter {

  /**
   * Returns the {@code String} representation of the given <code>{@link Node}</code>. This implementation only
   * includes the node's id, 'disabled' and 'visible' properties. To add more properties simply override
   * <code>{@link #formattedPropertiesOf(Node)}</code> returning one property per {@code String} in the format:
   * <pre>
   * propertyName=propertyValue
   * </pre>
   * @param n the given {@code Node}.
   * @return the {@code String} representation of the given {@code Node}.
   * @throws NullPointerException if the given {@code Node} is <code>null</code>.
   * @throws IllegalArgumentException if the type of the given {@code Node} is not supported by this formatter.
   */
  @RunsInCurrentThread
  @Override public final String format(Node n) {
    validateTypeOf(n);
    return doFormat(n);
  }

  @RunsInCurrentThread
  private String doFormat(Node n) {
    StringBuilder b = new StringBuilder();
    b.append(n.getClass().getName()).append("[");
    String[] properties = propertiesOf(n);
    int propertyCount = properties.length;
    for (int i = 0; i < propertyCount; i++) {
      b.append(properties[i]);
      if (i != propertyCount - 1) b.append(", ");
    }
    b.append("]");
    return b.toString();
  }

  @RunsInCurrentThread
  private String[] propertiesOf(Node n) {
    List<String> properties = new ArrayList<String>();
    properties.add(property("id", n.get$id()));
    properties.addAll(formattedPropertiesOf(n));
    properties.add(property("disabled", n.get$disabled()));
    properties.add(property("visible", n.get$visible()));
    return properties.toArray(new String[properties.size()]);
  }

  private String property(String name, Object value) {
    return concat(name, "=", quote(value));
  }

  private String property(String name, boolean value) {
    return concat(name, "=", valueOf(value));
  }

  /**
   * Returns the formatted properties of the given <code>{@link Node}</code> to be included in
   * <code>{@link #format(Node)}</code>. Each {@code String} represents a property in the format:
   * <pre>
   * propertyName=propertyValue
   * </pre>
   * @param n the given {@code Node}.
   * @return the formatted properties to be included in <code>{code format(Node)}</code>.
   */
  @RunsInCurrentThread
  protected Collection<String> formattedPropertiesOf(Node n) {
    return emptyList();
  }

  private void validateTypeOf(Node n) {
    if (n == null) throw new NullPointerException("The node to format should not be null");
    if (!targetType().isAssignableFrom(n.getClass()))
      throw new IllegalArgumentException(concat("This formatter only supports components of type ", targetType().getName()));
  }

  /**
   * Returns <code>{@link Node}</code>.{@code class}.
   * @return {@code Node.class}.
   * @see NodeFormatter#targetType()
   */
  @Override public Class<? extends Node> targetType() {
    return Node.class;
  }
}
