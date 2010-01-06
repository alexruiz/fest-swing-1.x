/*
 * Created on Aug 11, 2008
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
package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JScrollBar;

import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that sets the value of a <code>{@link JScrollBar}</code>.This task is executed in the event
 * dispatch thread.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class JScrollBarSetValueTask {

  static void setValue(final JScrollBar scrollBar, final int value) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        scrollBar.setValue(value);
      }
    });
  }

  private JScrollBarSetValueTask() {}
}