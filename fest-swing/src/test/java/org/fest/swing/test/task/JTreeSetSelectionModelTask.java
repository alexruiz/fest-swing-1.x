/*
 * Created on Sep 8, 2008
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
package org.fest.swing.test.task;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JTree;
import javax.swing.tree.TreeSelectionModel;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that sets a <code>{@link TreeSelectionModel}</code> in a <code>{@link JTree}</code>. This task is
 * executed in the event dispatch thread.
 *
 * @author Alex Ruiz
 */
public final class JTreeSetSelectionModelTask {

  @RunsInEDT
  public static void setSelectionModel(final JTree tree, final TreeSelectionModel selectionModel) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        tree.setSelectionModel(selectionModel);
      }
    });
  }

  private JTreeSetSelectionModelTask() {}
}
