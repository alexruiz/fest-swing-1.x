/*
 * Created on May 4, 2010
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
package org.fest.javafx.exception;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javafx.scene.Node;

/**
 * Understands an error thrown when looking up a node.
 *
 * @author Alex Ruiz
 */
public class NodeLookupException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  private final Collection<Node> found = new ArrayList<Node>();

  /**
   * Creates a new </code>{@link NodeLookupException}</code>.
   * @param message the detail message.
   */
  public NodeLookupException(String message) {
    super(message);
  }

  /**
   * Creates a new </code>{@link NodeLookupException}</code>.
   * @param message the detail message.
   * @param found the nodes found by the lookup (it can be empty.)
   */
  public NodeLookupException(String message, Collection<? extends Node> found) {
    super(message);
    this.found.clear();
    this.found.addAll(found);
  }

  /**
   * Returns the nodes found by the lookup (it can be an empty collection.)
   * @return the nodes found by the lookup.
   */
  public final Collection<? extends Node> found() {
    return Collections.<Node>unmodifiableCollection(found);
  }
}
