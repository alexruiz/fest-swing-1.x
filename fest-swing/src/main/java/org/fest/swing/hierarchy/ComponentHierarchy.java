/*
 * Created on Oct 19, 2007
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.hierarchy;

import java.awt.*;
import java.util.Collection;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands access to all components in a hierarchy. <b>Note:</b> Methods in this class are <b>not</b> executed in
 * the event dispatch thread. Callers are responsible for calling them in the event dispatch thread.
 * <p>
 * <b>Note:</b> methods in this interface are <b>not</b> executed in the event dispatch thread (EDT.) Clients are 
 * responsible for invoking them in the EDT.
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunsInCurrentThread
public interface ComponentHierarchy {

  /**
   * Provides all root containers in the hierarchy.
   * @return all root containers in the hierarchy.
   */
  Collection<? extends Container> roots();

  /**
   * Returns all sub-components of the given component.
   * @param c the given component.
   * @return all sub-components of the given component.
   */
  Collection<Component> childrenOf(Component c);

  /**
   * Return the parent for the given component.
   * @param c the given component.
   * @return the parent for the given component.
   */
  Container parentOf(Component c);

  /**
   * Returns whether this hierarchy contains the given component.
   * @param c the given component.
   * @return <code>true</code> if this hierarchy contains the given component, <code>false</code> otherwise.
   */
  boolean contains(Component c);

  /**
   * Provides proper disposal of the given window, appropriate to this hierarchy. After disposal, the window and its
   * descendants will no longer be reachable from this hierarchy.
   * @param w the container to window.
   */
  void dispose(Window w);
}
