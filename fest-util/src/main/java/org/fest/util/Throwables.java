/*
 * Created on Dec 13, 2008
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
package org.fest.util;

import static org.fest.util.Collections.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Understands utility methods related to <code>{@link Throwable}</code>s.
 *
 * @author Alex Ruiz
 */
public final class Throwables {

  /**
   * Appends the stack trace of the current thread to the one in the given <code>{@link Throwable}</code>.
   * @param t the given {@code Throwable}.
   * @param methodToStartFrom the name of the method used as the starting point of the current thread's stack trace.
   */
  public static void appendCurrentThreadStackTraceToThrowable(Throwable t, String methodToStartFrom) {
    List<StackTraceElement> stackTrace = list(t.getStackTrace());
    stackTrace.addAll(currentThreadStackTrace(methodToStartFrom));
    t.setStackTrace(stackTrace.toArray(new StackTraceElement[stackTrace.size()]));
  }

  private static List<StackTraceElement> currentThreadStackTrace(String methodToStartFrom) {
    List<StackTraceElement> filtered = stackTraceInCurrentThread();
    List<StackTraceElement> toRemove = new ArrayList<StackTraceElement>();
    for (StackTraceElement e : filtered) {
      if (methodToStartFrom.equals(e.getMethodName())) break;
      toRemove.add(e);
    }
    filtered.removeAll(toRemove);
    return filtered;
  }

  private static List<StackTraceElement> stackTraceInCurrentThread() {
    return list(StackTraces.instance().stackTraceInCurrentThread());
  }

  private Throwables() {}
}
