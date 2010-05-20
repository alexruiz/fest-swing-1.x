/*
 * Created on May 20, 2010
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
package org.fest.javafx.hierarchy;

import javafx.scene.*;
import javafx.stage.Stage;

import com.sun.javafx.runtime.sequence.*;

import static com.sun.javafx.runtime.TypeInfo.getTypeInfo;

/**
 * Understands a <code>{@link NodeHierarchy}</code> for a single <code>{@link Scene}</code>.
 *
 * @author Alex Ruiz
 */
public class SingleSceneNodeHierarchy implements NodeHierarchy {

  private final Scene root;

  /**
   * Creates a new </code>{@link SingleSceneNodeHierarchy}</code>.
   * @param stage the {@code Stage} containing the {@code Scene} for this hierarchy.
   */
  public SingleSceneNodeHierarchy(Stage stage) {
    this(stage.get$scene());
  }

  /**
   * Creates a new </code>{@link SingleSceneNodeHierarchy}</code>.
   * @param root the root {@code Scene} for this hierarchy.
   */
  public SingleSceneNodeHierarchy(Scene root) {
    this.root = root;
  }
  
  /** {@inheritDoc} */
  @Override public Sequence<? extends Node> contents() {
    return root.get$content();
  }

  /** {@inheritDoc} */
  @Override public Sequence<? extends Node> childrenOf(Node parent) {
    if (!(parent instanceof Parent)) return emptyNodeSequence();
    return ((Parent)parent).get$children();
  }

  private static ObjectArraySequence<Node> emptyNodeSequence() {
    return new ObjectArraySequence<Node>(getTypeInfo(Node.class));
  }
}
