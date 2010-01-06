/*
 * Created on Jan 5, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.swing.util;

import org.fest.reflect.core.Reflection;

/**
 * Understands installation of AWT exception handlers.
 * <p>
 * An exception handler is passed to the JVM using the system property "sun.awt.exception.handler" to override the
 * default exception handling behavior of the event dispatch thread.
 * </p>
 * <p>
 * This is a Sun-specific feature (or "bug".) See <a
 * href="http://bugs.sun.com/bugdatabase/view_bug.do?bug_id=4714232" target="_blank">bug 4714232</a>.
 * </p>
 *
 * @author Alex Ruiz
 */
public final class AWTExceptionHandlerInstaller {

  private static final SystemPropertyWriter WRITER = new SystemPropertyWriter();

  /**
   * Installs the given exception handler type.
   * @param exceptionHandlerType the type of exception handler to be installed in the current JVM.
   * @throws IllegalArgumentException if the given type does not have a default constructor.
   */
  public static void installAWTExceptionHandler(Class<?> exceptionHandlerType) {
    installAWTExceptionHandler(exceptionHandlerType, WRITER);
  }

  // package-protected for testing
  static void installAWTExceptionHandler(Class<?> exceptionHandlerType, SystemPropertyWriter writer) {
    try {
      Reflection.constructor().in(exceptionHandlerType).info();
    } catch (RuntimeException e) {
      throw new IllegalArgumentException("The exception handler type should have a default constructor");
    }
    writer.updateSystemProperty("sun.awt.exception.handler", exceptionHandlerType.getName());
  }

  private AWTExceptionHandlerInstaller() {}
}
