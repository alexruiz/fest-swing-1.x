/*
 * Created on May 31, 2010
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
package org.fest.javafx.core;

import static com.sun.javafx.runtime.TypeInfo.getTypeInfo;
import static org.fest.javafx.util.Sequences.emptySequence;
import javafx.scene.*;

import com.sun.javafx.runtime.sequence.ObjectArraySequence;
import com.sun.javafx.runtime.sequence.Sequence;

/**
 * Understands a template for implementations of <code>{@link NodeHierarchy}</code>.
 *
 * @author Alex Ruiz
 */
abstract class NodeHierarchyTemplate implements NodeHierarchy {

  @Override public final Sequence<? extends Node> contents() {
    ObjectArraySequence<Node> nodes = new ObjectArraySequence<Node>(getTypeInfo(Node.class));
    for (Scene scene : roots())
      nodes.add(scene.get$content());
    return nodes;
  }

  @Override public final Sequence<? extends Node> childrenOf(Node parent) {
    if (!(parent instanceof Parent)) return emptySequence(Node.class);
    return ((Parent)parent).get$children();
  }
}
