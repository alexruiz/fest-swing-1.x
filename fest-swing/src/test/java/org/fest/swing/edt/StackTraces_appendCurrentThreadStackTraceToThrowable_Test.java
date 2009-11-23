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
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.util.Strings.concat;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.*;

/**
 * Tests for <code>{@link StackTraces#appendCurrentThreadStackTraceToThrowable(Throwable, String)}</code>.
 *
 * @author Alex Ruiz
 */
public class StackTraces_appendCurrentThreadStackTraceToThrowable_Test {

  private static final String TEST_CLASS = StackTraces_appendCurrentThreadStackTraceToThrowable_Test.class.getName();
  private static final String TEST_METHOD = "should_add_stack_trace_of_current_thread";

  private AtomicReference<RuntimeException> exceptionReference;

  @Before public void setUp() {
    exceptionReference = new AtomicReference<RuntimeException>();
  }

  @After public void tearDown() {
    exceptionReference.set(null);
  }

  @Test
  public void should_add_stack_trace_of_current_thread() {
    final CountDownLatch latch = new CountDownLatch(1);
    new Thread() {
      @Override public void run() {
        RuntimeException e = new RuntimeException("Thrown on purpose");
        exceptionReference.set(e);
        latch.countDown();
      }
    }.start();
    try {
      latch.await();
    } catch (InterruptedException e) {
      currentThread().interrupt();
    }
    RuntimeException thrown = exceptionReference.get();
    StackTraceElement[] originalExceptionStackTrace = thrown.getStackTrace();
    assertThat(originalExceptionStackTrace).hasSize(1);
    assertHasThreadStackTrace(originalExceptionStackTrace[0]);
    StackTraces.appendCurrentThreadStackTraceToThrowable(thrown, TEST_METHOD);
    StackTraceElement[] exceptionStackTrace = thrown.getStackTrace();
    assertThat(exceptionStackTrace.length).isEqualTo(new RuntimeException().getStackTrace().length + 1);
    assertHasThreadStackTrace(exceptionStackTrace[0]);
    assertHasMethodSignature(exceptionStackTrace[1], TEST_CLASS, TEST_METHOD);
  }

  private void assertHasThreadStackTrace(StackTraceElement e) {
    assertHasMethodSignature(e, concat(TEST_CLASS, "$1"), "run");
  }

  private void assertHasMethodSignature(StackTraceElement e, String className, String methodName) {
    assertThat(e.getClassName()).isEqualTo(className);
    assertThat(e.getMethodName()).isEqualTo(methodName);
  }
}
