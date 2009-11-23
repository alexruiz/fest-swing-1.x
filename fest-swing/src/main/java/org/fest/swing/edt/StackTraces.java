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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.edt;

import static java.lang.Thread.currentThread;
import static org.fest.util.Collections.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Understands utility methods related to stack traces.
 *
 * @author Alex Ruiz 
 */
final class StackTraces {

  static void appendCurrentThreadStackTraceToThrowable(Throwable t, String methodToFilterCurrentThreadStackTraceBy) {
    List<StackTraceElement> stackTrace = list(t.getStackTrace());
    stackTrace.addAll(currentThreadStackTrace(methodToFilterCurrentThreadStackTraceBy));
    t.setStackTrace(stackTrace.toArray(new StackTraceElement[stackTrace.size()]));
  }
  
  private static List<StackTraceElement> currentThreadStackTrace(String methodToFilterBy) {
    List<StackTraceElement> filtered = list(currentThread().getStackTrace());
    List<StackTraceElement> toRemove = new ArrayList<StackTraceElement>();
    for (StackTraceElement e : filtered) {
      if (methodToFilterBy.equals(e.getMethodName())) break;
      toRemove.add(e);
    }
    filtered.removeAll(toRemove);
    return filtered;
  }

  private StackTraces() {}
}
