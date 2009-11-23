/*
 * Created on Oct 22, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.hierarchy;

import java.awt.Component;
import java.awt.Container;
import java.util.Collection;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands how to find children components in a <code>{@link Container}</code>.
 *
 * @author Yvonne Wang
 */
interface ChildrenFinderStrategy {

  /**
   * Returns the non-explicit children of a container. Non-explicit children are components considered to be children of
   * the given container but cannot be obtained by calling <code>{@link Container#getComponents()}</code>.
   * <p>
   * <b>Note:</b> Implementations of this method should <b>not</b> use the event dispatch thread (EDT.) Clients are
   * responsible for invoking this method in the EDT.
   * </p>
   * @param c the container whose children we are looking for.
   * @return a collection containing the non-explicit children found.
   */
  @RunsInCurrentThread
  Collection<Component> nonExplicitChildrenOf(Container c);
}
