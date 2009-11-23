/*
 * Created on Nov 17, 2008
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
package org.fest.swing.driver;

import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.driver.ComponentStateValidator.validateIsShowing;
import static org.fest.swing.format.Formatting.format;
import static org.fest.util.Strings.concat;

import java.awt.*;

import javax.swing.JInternalFrame;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands validation of the state of a <code>{@link Container}</code>.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are 
 * responsible for invoking them in the EDT.
 * </p>
 *
 * @author Alex Ruiz
 */
final class ContainerStateValidator {

  @RunsInCurrentThread
  static void validateCanResize(Container c) {
    if (c instanceof JInternalFrame) {
      validateCanResize((JInternalFrame)c);
      return;
    }
    if (isResizable(c)) validateIsEnabledAndShowing(c);
    else throw containerNotResizableFailure(c);
  }
  
  @RunsInCurrentThread
  private static void validateCanResize(JInternalFrame internalFrame) {
    validateIsShowing(internalFrame);
    if (!internalFrame.isResizable()) throw containerNotResizableFailure(internalFrame);
  }
  
  @RunsInCurrentThread
  private static boolean isResizable(Container c) {
    if (c instanceof Dialog) return ((Dialog)c).isResizable();
    if (c instanceof Frame)  return ((Frame)c).isResizable();
    return false;
  }
  
  @RunsInCurrentThread
  private static IllegalStateException containerNotResizableFailure(Container c) {
    throw new IllegalStateException(concat("Expecting component ", format(c), " to be resizable by the user"));
  }
  
  private ContainerStateValidator() {}
}
