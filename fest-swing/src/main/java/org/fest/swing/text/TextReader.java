/*
 * Created on Jul 29, 2010
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
package org.fest.swing.text;

import static org.fest.util.Strings.concat;

import java.awt.Component;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands reading the text of a <code>{@link Component}</code>.
 * @param <T> the type of component this reader supports.
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
public abstract class TextReader<T extends Component> {

  /**
   * Returns the type of component this reader supports.
   * @return the type of component this reader supports.
   */
  public abstract Class<T> supportedComponent();

  /**
   * Indicates whether the given component contains or displays the given text.
   * @param c the given component.
   * @param text the given text.
   * @return {@code true} if the given component contains or displays the given text; {@code false} otherwise.
   * @throws IllegalArgumentException if this reader does not support the type of the given component (e.g. this
   * reader supports {@code JButton}s and a {@code JLabel} is passed.)
   * @see #supportedComponent()
   */
  @RunsInCurrentThread
  public final boolean containsText(Component c, String text) {
    validateCorrectType(c);
    T casted = supportedComponent().cast(c);
    return checkContainsText(casted, text);
  }

  private void validateCorrectType(Component c) {
    Class<T> type = supportedComponent();
    if (type.isAssignableFrom(c.getClass())) return;
    throw new IllegalArgumentException(concat(
        "Expecting component of type ", type.getName(), " but got ", c.getClass().getName()));
  }

  /**
   * Indicates whether the given component contains or displays the given text. Implementations must ensure that they
   * are executed in the current thread.
   * @param component the given component.
   * @param text the given text.
   * @return {@code true} if the given component contains or displays the given text; {@code false} otherwise.
   */
  @RunsInCurrentThread
  protected abstract boolean checkContainsText(T component, String text);
}
