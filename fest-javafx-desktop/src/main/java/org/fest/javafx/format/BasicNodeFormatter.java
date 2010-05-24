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

import static java.util.Collections.emptyList;
import static org.fest.util.Strings.concat;

import java.util.List;

import javafx.scene.Node;

import org.fest.javafx.annotations.RunsInCurrentThread;

/**
 * Understands a basic implementation of <code>{@link NodeFormatter}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicNodeFormatter implements NodeFormatter {

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
    if (n == null) throw new NullPointerException("The node to format should not be null");
    validateTypeOf(n);
    return doFormat(n);
  }

  private void validateTypeOf(Node n) {
    if (!targetType().isAssignableFrom(n.getClass()))
      throw new IllegalArgumentException(concat("This formatter only supports components of type ", targetType().getName()));
  }

  @RunsInCurrentThread
  private String doFormat(Node n) {
    PropertyBuilder builder = new PropertyBuilder(n);
    builder.add("id", n.get$id());
    builder.add(formattedPropertiesOf(n));
    builder.add("disabled", n.get$disabled());
    builder.add("visible", n.get$visible());
    return builder.value();
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
  protected List<String> formattedPropertiesOf(Node n) {
    return emptyList();
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
