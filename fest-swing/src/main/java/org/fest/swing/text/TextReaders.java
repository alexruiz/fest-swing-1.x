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
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Strings.concat;

import java.awt.*;
import java.util.concurrent.*;
import java.util.logging.Logger;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.util.VisibleForTesting;

/**
 * Understands a registry of <code>{@link TextReader}</code>s.
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
public class TextReaders {

  private static Logger logger = getAnonymousLogger();

  @VisibleForTesting
  final ConcurrentMap<Class<?>, TextReader<?>> readers = new ConcurrentHashMap<Class<?>, TextReader<?>>();

  @VisibleForTesting TextReaders() {
    register(new AbstractButtonTextReader());
    register(new JLabelTextReader());
    register(new JListTextReader());
    register(new JTextComponentTextReader());
  }

  /**
   * Adds the given {@code TextReader} to this registry.
   * @param reader the {@code TextReader} to add.
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

  /**
   * Indicates whether the given <code>{@link Container}</code> or any of its subcomponents contains the given text.
   * @param container the given {@code Container}. This method is executed in the event dispatch thread (EDT.)
   * @param text the text to look for.
   * @return {@code true} if the given {@code Container} or any of its subcomponents contains the given text;
   * {@code false} otherwise.
   * @throws NullPointerException if the given {@code Container} is {@code null}.
   * @throws NullPointerException if the given text is {@code null}.
   */
  @RunsInEDT
  public boolean containsText(final Container container, final String text) {
    notNull(container, text);
    return execute(new GuiQuery<Boolean>() {
      @Override protected Boolean executeInEDT() {
        if (componentContainsText(container, text)) return true;
        return anyComponentContainsText(container.getComponents(), text);
      }
    });
  }

  private void notNull(Container c, String text) {
    if (c == null) throw new NullPointerException("The container to check should not be null");
    if (text == null) throw new NullPointerException("The text to look for should not be null");
  }

  private boolean anyComponentContainsText(Component[] components, String text) {
    for (Component c : components) {
      if (componentContainsText(c, text)) return true;
      if (c instanceof Container)
        return anyComponentContainsText(((Container)c).getComponents(), text);
    }
    return false;
  }

  private boolean componentContainsText(Component c, String text) {
    TextReader<?> reader = readerFor(c);
    if (reader == null) return false;
    return reader.containsText(c, text);
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

  /**
   * Returns the singleton instance of this class.
   * @return the singleton instance of this class.
   */
  public static TextReaders instance() {
    return SingletonHolder.INSTANCE;
  }

  private static class SingletonHolder {
    static final TextReaders INSTANCE = new TextReaders();
  }
}
