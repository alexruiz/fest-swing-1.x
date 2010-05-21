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

/**
 * Understands a formatter that returns a {@code String} representation of a given <code>{@link Node}</code>.
 *
 * @author Alex Ruiz
 */
@RunsInCurrentThread
public interface NodeFormatter {

  /**
   * Returns a {@code String} representation of the given <code>{@link Node}</code>.
   * @param n the given {@code Node}.
   * @return a {@code String} representation of the given {@code Node}.
   * @throws NullPointerException if the given {@code Node} is <code>null</code>.
   * @throws IllegalArgumentException if the type of the given {@code Node} is not supported by this formatter. 
   */
  String format(Node n);

  /**
   * Returns the type of <code>{@link Node}</code> this formatter supports.
   * @return the type of {@code Node} this formatter supports.
   */
  Class<? extends Node> targetType();

}
