/*
 * Created on Dec 21, 2009
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
package org.fest.swing.security;

import static org.fest.util.Strings.concat;

import java.security.Permission;

/**
 * Understands a <code>{@link SecurityManager}</code> that does not allow an application under test to terminate the
 * current JVM. Adapted from Abbot's <code>NoExitSecurityManager</code>.
 *
 * @author Alex Ruiz
 */
public final class NoExitSecurityManager extends SecurityManager {

  private static final ExitCallHook NULL_HOOK = new ExitCallHook() {
    public void exitCalled(int status) {}
  };
  private final ExitCallHook hook;

  /**
   * Creates a new </code>{@link NoExitSecurityManager}</code>.
   */
  public NoExitSecurityManager() {
    this(NULL_HOOK);
  }

  /**
   * Creates a new </code>{@link NoExitSecurityManager}</code>.
   * @param hook notified when an application tries to terminate the current JVM.
   */
  public NoExitSecurityManager(ExitCallHook hook) {
    this.hook = hook;
  }

  /**
   * Allows everything.
   * @param permission the specified permission.
   * @param context a system-dependent security context.
   */
  @Override public void checkPermission(Permission permission, Object context) {}

  /**
   * Allows everything.
   * @param permission the specified permission.
   */
  @Override public void checkPermission(Permission permission) {}

  /**
   * Indicates whether "exit" has been invoked through a call of <code>{@link Runtime#exit(int)}</code> or
   * <code>{@link Runtime#halt(int)}</code>.
   * @return <code>true</code> if an exit has been invoked through a call of <code>Runtime.exit</code> or
   * <code>Runtime.halt</code>; <code>false</code> otherwise.
   */
  private boolean exitInvoked() {
    StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
    for (StackTraceElement e : stackTrace)
      if (exitInvoked(e)) return true;
    return false;
  }

  private boolean exitInvoked(StackTraceElement e) {
    if (!"java.lang.Runtime".equals(e.getClassName())) return false;
    final String method = e.getMethodName();
    return "exit".equals(method) || "halt".equals(method);
  }

  /**
   * Throws an <code>{@link ExitException}</code> if an application tries to terminate the current JVM (through
   * <code>{@link Runtime#exit(int)}</code> or <code>{@link Runtime#halt(int)}</code>.)
   * @param status the exit status.
   * @throws ExitException if an application tries to terminate the current JVM.
   */
  @Override public void checkExit(int status) {
    if (exitInvoked()) {
      hook.exitCalled(status);
      throw new ExitException(concat("Application tried to terminate current JVM with status ", status));
    }
  }
}
