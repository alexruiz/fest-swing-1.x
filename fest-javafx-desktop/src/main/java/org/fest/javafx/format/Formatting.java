/*
 * Created on May 23, 2010
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

import static org.fest.javafx.threading.GuiActionRunner.execute;
import static org.fest.util.Strings.concat;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.logging.Logger;

import org.fest.javafx.annotations.RunsInCurrentThread;
import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.threading.GuiQuery;

import javafx.scene.Node;

/**
 * Understands {@code String} representations of <code>{@link Node}</code>s.
 *
 * @author Alex Ruiz
 */
public class Formatting {

  private static final String NULL_NODE = "Null Node";
  private static final ConcurrentMap<Class<?>, NodeFormatter> FORMATTERS = new ConcurrentHashMap<Class<?>, NodeFormatter>();
  private static final NodeFormatter DEFAULT_FORMATTER = new NodeFormatterTemplate();

  private static Logger logger = Logger.getAnonymousLogger();

  /**
   * Registers the given formatter, replacing any other one previously registered for the same supported component type.
   * @param formatter the formatter to register.
   */
  public static void register(NodeFormatter formatter) {
    Class<?> key = formatter.targetType();
    NodeFormatter previous = FORMATTERS.putIfAbsent(key, formatter);
    if (previous != null)
      logger.info(
          concat("Replaced formatter ", previous, " with ", formatter, " for the type ", key.getName()));
  }

  /**
   * Returns a {@code String} representation of the given <code>{@link Node}</code>. This method is executed in the UI
   * thread.
   * @param n the given {@code Node}.
   * @return a {@code String} representation of the given {@code Node}.
   */
  @RunsInUIThread
  public static String formatInUIThread(final Node n) {
    return execute(new GuiQuery<String>() {
      @Override protected String executeInUIThread() {
        return format(n);
      }
    });
  }

  /**
   * Returns a {@code String} representation of the given <code>{@link Node}</code>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the UI thread. Clients are responsible for
   * calling this method from the UI thread.
   * </p>
   * @param n the given {@code Node}.
   * @return a {@code String} representation of the given {@code Node}.
   */
  @RunsInCurrentThread
  public static String format(Node n) {
    if (n == null) return NULL_NODE;
    NodeFormatter formatter = formatterFor(n.getClass());
    if (formatter == null) formatter = DEFAULT_FORMATTER;
    return formatter.format(n);
  }

  private static NodeFormatter formatterFor(Class<?> type) {
    NodeFormatter formatter = FORMATTERS.get(type);
    if (formatter != null) return formatter;
    Class<?> superType = type.getSuperclass();
    if (superType != null) return formatterFor(superType);
    return null;
  }

  private Formatting() {}
}
