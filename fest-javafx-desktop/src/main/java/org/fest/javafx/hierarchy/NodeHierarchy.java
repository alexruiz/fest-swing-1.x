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

import javafx.scene.Node;

import com.sun.javafx.runtime.sequence.Sequence;

import org.fest.javafx.annotations.RunsInCurrentThread;

/**
 * Understands access to all <code>{@link Node}</code>s in a hierarchy.
 * <p>
 * <b>Note:</b> methods in this interface are <b>not</b> guaranteed to be executed in the UI thread. Clients are 
 * responsible for invoking them in the UI thread.
 * </p>
 *
 * @author Alex Ruiz
 */
@RunsInCurrentThread
public interface NodeHierarchy {

  /**
   * Returns all the top-level nodes in this hierarchy.
   * @return all the top-level nodes in this hierarchy.
   */
  Sequence<? extends Node> contents();
  
  /**
   * Returns all the children nodes in the given node.
   * @param parent the given node.
   * @return all the children nodes in the given node.
   */
  Sequence<? extends Node> childrenOf(Node parent);
}
