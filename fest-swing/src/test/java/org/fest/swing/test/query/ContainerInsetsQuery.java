/*
 * Created on Aug 8, 2008
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
package org.fest.swing.test.query;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Container;
import java.awt.Insets;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns the insets of a
 * <code>{@link Container}</code>.
 * @see Container#getInsets()
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class ContainerInsetsQuery {

  /**
   * Returns the insets of the given <code>{@link Container}</code>. This action is executed in the event dispatch
   * thread.
   * @param container the given <code>Container</code>.
   * @return the insets of the given <code>Container</code>.
   * @see Container#getInsets()
   */
  @RunsInEDT
  public static Insets insetsOf(final Container container) {
    return execute(new GuiQuery<Insets>() {
      protected Insets executeInEDT() {
        return container.getInsets();
      }
    });
  }

  private ContainerInsetsQuery() {}
}