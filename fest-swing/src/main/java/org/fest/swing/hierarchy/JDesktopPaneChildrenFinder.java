/*
 * Created on Oct 24, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.hierarchy;

import static java.util.Collections.emptyList;

import java.awt.*;
import java.util.*;

import javax.swing.*;
import javax.swing.JInternalFrame.JDesktopIcon;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands how to find children components in a <code>{@link JDesktopPane}</code>.
 *
 * @author Yvonne Wang
 */
final class JDesktopPaneChildrenFinder implements ChildrenFinderStrategy {

  @RunsInCurrentThread
  public Collection<Component> nonExplicitChildrenOf(Container c) {
    if (!(c instanceof JDesktopPane)) return emptyList();
    return internalFramesFromIcons(c);
  }

  // From Abbot: add iconified frames, which are otherwise unreachable. For consistency, they are still considered
  // children of the desktop pane.
  @RunsInCurrentThread
  private Collection<Component> internalFramesFromIcons(Container c) {
    Collection<Component> frames = new ArrayList<Component>();
    for (Component child : c.getComponents()) {
      if (child instanceof JDesktopIcon) {
        JInternalFrame frame = ((JDesktopIcon)child).getInternalFrame();
        if (frame != null) frames.add(frame);
        continue;
      }
      // OSX puts icons into a dock; handle icon manager situations here
      if (child instanceof Container) frames.addAll(internalFramesFromIcons((Container)child));
    }
    return frames;
  }
}
