/*
 * Created on Jul 31, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.query;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Component;
import java.awt.Container;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns the parent of a
 * <code>{@link Component}</code>.
 * @see Component#getParent()
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class ComponentParentQuery {

  /**
   * Returns the parent of the given <code>{@link Component}</code>. This action is executed in the event dispatch
   * thread.
   * @param component the given <code>Component</code>.
   * @return the parent of the given <code>Component</code>.
   * @see Component#getParent()
   */
  @RunsInEDT
  public static Container parentOf(final Component component) {
    return execute(new GuiQuery<Container>() {
      protected Container executeInEDT() {
        return component.getParent();
      }
    });
  }

  private ComponentParentQuery() {}
}
