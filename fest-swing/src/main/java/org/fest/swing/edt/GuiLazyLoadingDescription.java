/*
 * Created on Oct 27, 2008
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
package org.fest.swing.edt;

import static org.fest.swing.edt.GuiActionRunner.execute;

import org.fest.assertions.Description;
import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a <code>{@link Description}</code> that loads the text to return in the event dispatch thread.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class GuiLazyLoadingDescription implements Description {

  /**
   * Executes <code>{@link #loadDescription()}</code> in the event dispatch thread.
   * @return the text loaded in the event dispatch thread.
   */
  public final String value() {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        return loadDescription();
      }
    });
  }

  @RunsInCurrentThread
  protected abstract String loadDescription();
}
