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

import static org.fest.util.Strings.concat;

/**
 * Understands a template for implementations of <code>{@link NodeFormatter}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class NodeFormatterTemplate implements NodeFormatter {

  /** {@inheritDoc} */
  @RunsInCurrentThread
  @Override public final String format(Node n) {
    validateTypeOf(n);
    return doFormat(n);
  }

  /**
   * Returns the {@code String} representation of the given <code>{@link Node}</code>.
   * @param n the given {@code Node}.
   * @return the {@code String} representation of the given {@code Node}.
   */
  @RunsInCurrentThread
  protected abstract String doFormat(Node n);

  private void validateTypeOf(Node n) {
    if (n == null) throw new NullPointerException("The node to format should not be null");
    if (!targetType().isAssignableFrom(n.getClass()))
      throw new IllegalArgumentException(concat("This formatter only supports components of type ", targetType().getName()));
  }
}
