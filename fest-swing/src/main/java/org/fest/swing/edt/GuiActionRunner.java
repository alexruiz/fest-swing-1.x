/*
 * Created on Aug 14, 2008
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
package org.fest.swing.edt;

import static javax.swing.SwingUtilities.invokeLater;
import static javax.swing.SwingUtilities.isEventDispatchThread;
import static org.fest.swing.edt.Throwables.appendCurrentThreadStackTraceToThrowable;
import static org.fest.swing.exception.UnexpectedException.unexpected;

import java.util.concurrent.CountDownLatch;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import org.fest.swing.exception.UnexpectedException;

/**
 * Understands running instances of <code>{@link GuiQuery}</code> and <code>{@link GuiTask}</code>.
 *
 * @author Alex Ruiz
 */
@ThreadSafe
public class GuiActionRunner {

  @GuardedBy("this")
  private static boolean executeInEDT = true;
  
  /**
   * Indicates <code>{@link GuiActionRunner}</code> if instances of <code>{@link GuiQuery}</code> and
   * <code>{@link GuiTask}</code> should be executed in the event dispatch thread or not.
   * @param b if <code>true</code>, GUI actions are executed in the event dispatch thread. If <code>false</code>,
   * GUI actions are executed in the current thread.
   */
  public static synchronized void executeInEDT(boolean b) {
    executeInEDT = b;
  }
  
  /**
   * Returns whether instances of <code>{@link GuiQuery}</code> and <code>{@link GuiTask}</code> should be executed in 
   * the event dispatch thread or not.
   * @return <code>true</code> if GUI actions are executed in the event dispatch thread, <code>false</code> otherwise.
   */
  public static synchronized boolean executeInEDT() {
    return executeInEDT;
  }
  
  /**
   * Executes the given query in the event dispatch thread. This method waits until the query has finished its
   * execution.
   * @param <T> the generic type of the return value.
   * @param query the query to execute.
   * @return the result of the query executed in the main thread.
   * @throws UnexpectedException wrapping any <b>checked</b> exception thrown when executing the given query in the 
   * event dispatch thread. Unchecked exceptions are re-thrown without any wrapping.
   * @see #executeInEDT()
   */
  public static <T> T execute(GuiQuery<T> query) {
    if (!executeInEDT) return executeInCurrentThread(query);
    run(query);
    return resultOf(query);
  }

  private static <T> T executeInCurrentThread(GuiQuery<T> query) {
    try {
      return query.executeInEDT();
    } catch (Throwable e) {
      throw unexpected(e);
    }
  }

  /**
   * Executes the given task in the event dispatch thread. This method waits until the task has finished its execution.
   * @param task the task to execute.
   * @throws UnexpectedException wrapping any <b>checked</b> exception thrown when executing the given query in the 
   * event dispatch thread. Unchecked exceptions are re-thrown without any wrapping.
   * @see #executeInEDT()
   */
  public static void execute(GuiTask task) {
    if (!executeInEDT) {
      executeInCurrentThread(task);
      return;
    }
    run(task);
    rethrowCatchedExceptionIn(task);
  }

  private static void executeInCurrentThread(GuiTask task) {
    try {
      task.executeInEDT();
    } catch (Throwable e) {
      throw unexpected(e);
    }
  }

  private static void run(final GuiAction action) {
    if (isEventDispatchThread()) {
      action.run();
      return;
    } 
    final CountDownLatch latch = new CountDownLatch(1);
    action.executionNotification(latch);
    invokeLater(action);
    try {
      latch.await();
    } catch (final InterruptedException e) {
      Thread.currentThread().interrupt();
    }
  }

  private static <T> T resultOf(GuiQuery<T> query) {
    T result = query.result();
    query.clearResult();
    rethrowCatchedExceptionIn(query);
    return result;
  }

  /**
   * Wraps (with a <code>{@link UnexpectedException}</code>) and retrows any catched exception in the given action.
   * @param action the given action that may have a catched exception during its execution.
   * @throws UnexpectedException wrapping any <b>checked</b> exception thrown when executing the given query in the 
   * event dispatch thread. Unchecked exceptions are rethrown without any wrapping.
   */
  private static void rethrowCatchedExceptionIn(GuiAction action) {
    Throwable catchedException = action.catchedException();
    action.clearCatchedException();
    if (catchedException == null) return;
    if (catchedException instanceof RuntimeException) {
      appendCurrentThreadStackTraceToThrowable(catchedException, "execute");
      throw (RuntimeException)catchedException;
    }
    if (catchedException instanceof Error) {
      catchedException.fillInStackTrace();
      throw (Error)catchedException;
    }
    throw unexpected(catchedException);
  }
}
