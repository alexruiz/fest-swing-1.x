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

import java.awt.Component;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands reading the text of a <code>{@link Component}</code>.
 *
 * @author Alex Ruiz
 * @param <T>
 *
 * @since 1.3
 */
public interface TextReader<T extends Component> {

  /**
   * Returns the type of component this reader supports.
   * @return the type of component this reader supports.
   */
  Class<T> supportedComponent();

  /**
   * Indicates whether the given component contains or displays the given text.  Implementations must ensure that they
   * are executed in the current thread.
   * @param component the given component.
   * @param text the given text.
   * @return {@code true} if the given component contains or displays the given text; {@code false} otherwise.
   */
  @RunsInCurrentThread
  boolean containsText(T component, String text);
}
