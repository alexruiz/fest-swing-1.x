/*
 * Created on Mar 30, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Collections.list;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.util.VisibleForTesting;

/**
 * Finds the <code>{@link Component}</code> owning the input focus.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public final class FocusOwnerFinder {

  private static final List<FocusOwnerFinderStrategy> STRATEGIES = new ArrayList<FocusOwnerFinderStrategy>();

  static {
    initializeStrategies();
  }

  @VisibleForTesting
  static void initializeStrategies() {
    replaceStrategiesWith(new ReflectionBasedFocusOwnerFinder(), new HierarchyBasedFocusOwnerFinder());
  }

  @VisibleForTesting
  static void replaceStrategiesWith(FocusOwnerFinderStrategy...strategies) {
    STRATEGIES.clear();
    STRATEGIES.addAll(list(strategies));
  }

  @VisibleForTesting
  static List<FocusOwnerFinderStrategy> strategies() {
    return new ArrayList<FocusOwnerFinderStrategy>(STRATEGIES);
  }

  /**
   * Returns the focus owner. This method is executed in the event dispatch thread.
   * @return the focus owner.
   */
  @RunsInEDT
  public static Component inEdtFocusOwner() {
    return execute(new GuiQuery<Component>() {
      @Override protected Component executeInEDT() {
        return focusOwner();
      }
    });
  }

  /**
   * Returns the focus owner. <b>Note:</b> this method is <b>not</b> executed in the event dispatch thread. Callers are
   * responsible for calling this method in the event dispatch thread.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @return the focus owner.
   */
  @RunsInCurrentThread
  public static Component focusOwner() {
    for (FocusOwnerFinderStrategy strategy : STRATEGIES) {
      Component focusOwner = focusOwnerFrom(strategy);
      if (focusOwner != null) return focusOwner;
    }
    return null;
  }

  private static Component focusOwnerFrom(FocusOwnerFinderStrategy strategy) {
    try {
      return strategy.focusOwner();
    } catch (Exception e) {
      return null;
    }
  }

  private FocusOwnerFinder() {}
}
