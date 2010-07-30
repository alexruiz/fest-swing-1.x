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

import static java.util.logging.Logger.getAnonymousLogger;
import static org.fest.util.Strings.concat;

import java.awt.Component;
import java.awt.Container;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

/**
 * Understands a registry of <code>{@link TextReader}</code>s.
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
public class TextReaders {

  private final ConcurrentMap<Class<?>, TextReader<?>> readers = new ConcurrentHashMap<Class<?>, TextReader<?>>();
  private static Logger logger = getAnonymousLogger();

  /**
   * Adds the given {@code TextReader} to this registry.
   * @param reader the reader to add.
   * @throws NullPointerException if the given {@code TextReader} is {@code null}.
   * @throws NullPointerException if the supported component type in the given {@code TextReader} is {@code null}.
   */
  public void register(TextReader<?> reader) {
    Class<?> type = supportedComponentTypeFrom(reader);
    TextReader<?> old = readers.put(type, reader);
    if (old != null) logger.info(concat("Replaced reader for type ", type.getName()));
  }

  private Class<?> supportedComponentTypeFrom(TextReader<?> reader) {
    if (reader == null) throw new NullPointerException("The TextReader to register should not be null");
    Class<?> type = reader.supportedComponent();
    if (type != null) return type;
    throw new NullPointerException("The type of Component that the TextReader supports should not be null");
  }

  public boolean containsText(Container container, String text) {
    notNull(container, text);
    Container parent = container;
    for (Component c : parent.getComponents()) {
      TextReader<? extends Component> reader = readerFor(c);
      if (reader.containsText(c, text)) return true;
    }
    return false;
  }

  private void notNull(Container c, String text) {
    if (c == null) throw new NullPointerException("The container to check should not be null");
    if (text == null) throw new NullPointerException("The text to look for should not be null");
  }

  private TextReader<?> readerFor(Component c) {
    Class<?> type = c.getClass();
    while (type != null) {
      TextReader<?> reader = readers.get(type);
      if (reader != null) return reader;
      if (type.equals(Component.class)) break;
      type = type.getSuperclass();
    }
    return null;
  }
}
