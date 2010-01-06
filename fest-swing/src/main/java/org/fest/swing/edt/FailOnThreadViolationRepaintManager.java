/*
 * Created on Dec 01, 2008
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.edt;

import static org.fest.reflect.core.Reflection.staticMethod;

import javax.swing.*;

import org.fest.swing.exception.EdtViolationException;

/**
 * <p>
 * Fails a test when a Event Dispatch Thread rule violation is detected.<br/>
 * See <a href="http://java.sun.com/docs/books/tutorial/uiswing/misc/threads.html">How to Use Threads</a> for more info
 * </p>
 *
 * @author Alex Ruiz
 */
public class FailOnThreadViolationRepaintManager extends CheckThreadViolationRepaintManager {

  /**
   * Creates a new <code>{@link FailOnThreadViolationRepaintManager}</code> and sets it as the current repaint manager.
   * <p>
   * On Sun JVMs, this method will install the new repaint manager the first time only. Once installed, subsequent calls
   * to this method will not install new repaint managers. This optimization may not work on non-Sun JVMs, since we use
   * reflection to check if a {@code CheckThreadViolationRepaintManager} is already installed.
   * </p>
   * @return the created (and installed) repaint manager.
   * @see RepaintManager#setCurrentManager(RepaintManager)
   */
  public static FailOnThreadViolationRepaintManager install() {
    Object m = currentRepaintManager();
    if (m instanceof FailOnThreadViolationRepaintManager) return (FailOnThreadViolationRepaintManager)m;
    return installNew();
  }

  private static Object currentRepaintManager() {
    try {
      return staticMethod("appContextGet").withReturnType(Object.class)
                                          .withParameterTypes(Object.class)
                                          .in(SwingUtilities.class)
                                          .invoke(RepaintManager.class);
    } catch (RuntimeException e) {
      return null;
    }
  }

  private static FailOnThreadViolationRepaintManager installNew() {
    FailOnThreadViolationRepaintManager m = new FailOnThreadViolationRepaintManager();
    setCurrentManager(m);
    return m;
  }

  public FailOnThreadViolationRepaintManager() {}

  public FailOnThreadViolationRepaintManager(boolean completeCheck) {
    super(completeCheck);
  }

  /**
   * Throws a <code>{@link EdtViolationException}</code> when a EDT access violation is found.
   * @param c the component involved in the EDT violation.
   * @param stackTraceElements stack trace elements to be set to the thrown exception.
   * @throws EdtViolationException when a EDT access violation is found.
   */
  @Override void violationFound(JComponent c, StackTraceElement[] stackTraceElements) {
    EdtViolationException e = new EdtViolationException("EDT violation detected");
    if (stackTraceElements != null) e.setStackTrace(stackTraceElements);
    throw e;
  }
}