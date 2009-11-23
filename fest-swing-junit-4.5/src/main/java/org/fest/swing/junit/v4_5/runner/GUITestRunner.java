/*
 * Created on Mar 13, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.junit.v4_5.runner;

import static org.fest.swing.annotation.GUITestFinder.isGUITest;
import static org.fest.swing.junit.runner.Formatter.testNameFrom;

import java.lang.reflect.Method;

import org.fest.swing.junit.runner.FailureScreenshotTaker;
import org.fest.swing.junit.runner.ImageFolderCreator;
import org.junit.Ignore;
import org.junit.internal.AssumptionViolatedException;
import org.junit.internal.runners.model.EachTestNotifier;
import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

/**
 * Understands a JUnit 4.5 test runner that takes a screenshot of a failed GUI test.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class GUITestRunner extends BlockJUnit4ClassRunner {

  private final FailureScreenshotTaker screenshotTaker;

  /**
   * Creates a new <code>{@link GUITestRunner}</code>.
   * @param testClass the class containing the tests to run.
   * @throws InitializationError if something goes wrong when creating this runner.
   */
  public GUITestRunner(Class<?> testClass) throws InitializationError {
    super(testClass);
    screenshotTaker = new FailureScreenshotTaker(new ImageFolderCreator().createImageFolder());
  }

  /**
   * Runs the given test, taking a screenshot of the desktop if the test fails and notifying that relevant events are
   * reported through the given notifier.
   * @param method the test to run.
   * @param notifier the given notifier.
   */
  @Override
  protected void runChild(FrameworkMethod method, RunNotifier notifier) {
    EachTestNotifier eachNotifier = new EachTestNotifier(notifier, describeChild(method));
    if (method.getAnnotation(Ignore.class) != null) {
      eachNotifier.fireTestIgnored();
      return;
    }
    eachNotifier.fireTestStarted();
    try {
      methodBlock(method).evaluate();
    } catch (AssumptionViolatedException e) {
      eachNotifier.addFailedAssumption(e);
    } catch (Throwable e) {
      takeScreenshot(method);
      eachNotifier.addFailure(e);
    } finally {
      eachNotifier.fireTestFinished();
    }
  }

  private void takeScreenshot(FrameworkMethod method) {
    Method realMethod = method.getMethod();
    final Class<?> testClass = realMethod.getDeclaringClass();
    if (!(isGUITest(testClass, realMethod))) return;
    screenshotTaker.saveScreenshot(testNameFrom(testClass, realMethod));
  }
}
