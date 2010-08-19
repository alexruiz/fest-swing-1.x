/*
 * Created on Dec 23, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.format;

import static org.fest.util.Strings.concat;

import java.awt.Component;

/**
 * Understands a template for implementations of <code>{@link ComponentFormatter}</code>.
 *
 * @author Yvonne Wang
 */
public abstract class ComponentFormatterTemplate implements ComponentFormatter {

  /**
   * Returns the {@code String} representation of the given <code>{@link Component}</code>.
   * @param c the given {@code Component}.
   * @return the {@code String} representation of the given {@code Component}.
   * @throws NullPointerException if the given {@code Component} is {@code null}.
   * @throws IllegalArgumentException if the type of the given {@code Component} is not supported by this
   * formatter.
   */
  public final String format(Component c) {
    validateTypeOf(c);
    return doFormat(c);
  }

  /**
   * Returns the {@code String} representation of the given <code>{@link Component}</code>.
   * @param c the given {@code Component}.
   * @return the {@code String} representation of the given {@code Component}.
   */
  protected abstract String doFormat(Component c);

  private void validateTypeOf(Component c) {
    if (c == null) throw new NullPointerException("The component should not be null");
    if (!targetType().isAssignableFrom(c.getClass()))
      throw new IllegalArgumentException(concat("This formatter only supports components of type ", targetType().getName()));
  }


}
