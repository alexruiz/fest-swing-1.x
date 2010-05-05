/*
 * Created on May 4, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.javafx.threading;

import java.util.concurrent.CountDownLatch;

/**
 * Understands the base class for actions that are executed in the UI thread.
 *
 * @author Alex Ruiz
 */
abstract class GuiAction implements Runnable {

  private boolean executedInEDT;
  private Throwable catchedException;
  private CountDownLatch executionNotification;

  final Throwable catchedException() { return catchedException; }

  final void catchedException(Throwable catched) {
    catchedException = catched;
  }

  final boolean wasExecutedInEDT() {
    return executedInEDT;
  }

  final void clearCatchedException() {
    catchedException = null;
  }

  final void executionNotification(CountDownLatch c) {
    executionNotification = c;
  }

  final void notifyExecutionCompleted() {
    executedInEDT();
    if (executionNotification == null) return;
    executionNotification.countDown();
    executionNotification = null;
  }

  private void executedInEDT() {
    executedInEDT = true;
  }
}