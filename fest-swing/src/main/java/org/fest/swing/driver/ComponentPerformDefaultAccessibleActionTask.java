/*
 * Created on Feb 23, 2008
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

import static org.fest.swing.exception.ActionFailedException.actionFailure;
import static org.fest.swing.format.Formatting.format;
import static org.fest.util.Strings.concat;

import java.awt.Component;

import javax.accessibility.AccessibleAction;
import javax.swing.Action;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands execution of the default (first) <code>{@link Action}</code> in a <code>{@link Component}</code>'s
 * <code>{@link AccessibleAction}</code>. This task is <b>not</b> executed in the event dispatch thread.
 *
 * @author Alex Ruiz
 */
class ComponentPerformDefaultAccessibleActionTask {

  private static final int DEFAULT_ACTION_INDEX = 0;

  @RunsInCurrentThread
  static void performDefaultAccessibleAction(final Component c) {
    AccessibleAction action = c.getAccessibleContext().getAccessibleAction();
    if (action == null || action.getAccessibleActionCount() == 0)
      throw actionFailure(concat("Unable to perform accessible action for ", format(c)));
    action.doAccessibleAction(DEFAULT_ACTION_INDEX);
  }
}
