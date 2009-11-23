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

import static java.util.Collections.emptyList;
import static org.fest.util.Collections.isEmpty;
import static org.fest.util.Collections.list;

import java.awt.*;
import java.util.*;
import java.util.List;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands how to find children components in a <code>{@link Window}</code>.
 *
 * @author Yvonne Wang
 */
final class WindowChildrenFinder implements ChildrenFinderStrategy {

  @RunsInCurrentThread
  public Collection<Component> nonExplicitChildrenOf(Container c) {
    if (!(c instanceof Window)) return emptyList();
    return ownedWindows((Window)c);
  }

  @RunsInCurrentThread
  private Collection<Component> ownedWindows(Window w) {
    return windows(list(w.getOwnedWindows()));
  }

  private Collection<Component> windows(List<Window> windows) {
    if (isEmpty(windows)) return emptyList();
    return new ArrayList<Component>(windows);
  }
}
