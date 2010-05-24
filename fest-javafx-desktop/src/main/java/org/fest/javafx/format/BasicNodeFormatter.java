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

import org.fest.javafx.annotations.RunsInCurrentThread;

import javafx.scene.Node;

import static org.fest.util.Strings.quote;

/**
 * Understands a basic implementation of <code>{@link NodeFormatter}</code> to be used if a type-specific formatter is
 * not defined.
 *
 * @author Alex Ruiz
 */
public final class BasicNodeFormatter extends NodeFormatterTemplate {

  /** {@inheritDoc} */
  @RunsInCurrentThread
  @Override protected String doFormat(Node n) {
    StringBuilder b = new StringBuilder();
    b.append(n.getClass().getName()).append("[")
     .append("id=").append(quote(n.get$id())).append(", ")
     .append("disabled=").append(n.get$disabled()).append(", ")
     .append("visible=").append(n.get$visible()).append("]");
    return b.toString();
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
