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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.security;

/**
 * Understands an exception thrown when an application under tests tries to terminate the current JVM.
 *
 * @author Alex Ruiz
 */
public class ExitException extends RuntimeException {
  private static final long serialVersionUID = 1L;

  /**
   * Creates a new </code>{@link ExitException}</code>.
   * @param message the detail message.
   */
  public ExitException(String message) {
    super(message);
  }
}
