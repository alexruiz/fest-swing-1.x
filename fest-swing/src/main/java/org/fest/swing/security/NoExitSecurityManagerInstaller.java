/*
 * Created on Dec 21, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.security;

/**
 * Understands installing/uninstalling a <code>{@link NoExitSecurityManager}</code>.
 *
 * @author Alex Ruiz
 */
public class NoExitSecurityManagerInstaller {

  /**
   * Installs a new <code>{@link NoExitSecurityManager}</code> in <code>System</code>.
   * @return this installer.
   */
  public static NoExitSecurityManagerInstaller installNoExitSecurityManager() {
    return new NoExitSecurityManagerInstaller();
  }

  /**
   * Installs a new <code>{@link NoExitSecurityManager}</code> in <code>System</code>.
   * @param hook gets notified when an application tries to terminate the current JVM.
   * @return this installer.
   * @throws NullPointerException if the given hook is <code>null</code>.
   */
  public static NoExitSecurityManagerInstaller installNoExitSecurityManager(ExitCallHook hook) {
    return new NoExitSecurityManagerInstaller(hook);
  }

  private final SecurityManager oldManager;

  private NoExitSecurityManagerInstaller() {
    this(new NoExitSecurityManager());
  }

  private NoExitSecurityManagerInstaller(ExitCallHook hook) {
    this(new NoExitSecurityManager(hook));
  }

  private NoExitSecurityManagerInstaller(NoExitSecurityManager newManager) {
    oldManager = System.getSecurityManager();
    System.setSecurityManager(newManager);
  }

  /**
   * Uninstalls the <code>{@link NoExitSecurityManager}</code> installed by
   * <code>{@link #installNoExitSecurityManager()}</code>, restoring the original <code>{@link SecurityManager}</code>.
   */
  public void uninstall() {
    System.setSecurityManager(oldManager);
  }
}
