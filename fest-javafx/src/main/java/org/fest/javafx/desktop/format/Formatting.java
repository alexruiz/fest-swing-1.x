/*
 * Created on Feb 19, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.format;

import java.awt.Component;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.sun.scenario.scenegraph.SGComponent;
import com.sun.scenario.scenegraph.SGNode;
import com.sun.scenario.scenegraph.fx.FXNode;

/**
 * Understands utility methods related to node formatting.
 *
 * @author Yvonne Wang
 */
public final class Formatting {

  private static final String NULL_NODE_MESSAGE = "Null Node";

  private static final Map<Class<?>, NodeFormatter> FORMATTERS = new ConcurrentHashMap<Class<?>, NodeFormatter>();

  static {
    register(new SwingButtonNodeFormatter());
  }

  /**
   * Registers the given formatter in this registry.
   * @param formatter the formatter to register.
   */
  public static void register(NodeFormatter formatter) {
    FORMATTERS.put(formatter.supportedType(), formatter);
  }

  public static String format(FXNode node) {
    if (node == null) return NULL_NODE_MESSAGE;
    NodeFormatter formatter = FORMATTERS.get(supportedTypeFrom(node));
    if (formatter != null) return formatter.format(node);
    return node.toString();
  }

  private static Class<?> supportedTypeFrom(FXNode node) {
    SGNode leaf = node.getLeaf();
    if (leaf instanceof SGComponent) {
      SGComponent componentNode = (SGComponent) leaf;
      Component c = componentNode.getComponent();
      return c != null ? c.getClass() : null;
    }
    return leaf != null ? leaf.getClass() : null;
  }

  private Formatting() {}
}
