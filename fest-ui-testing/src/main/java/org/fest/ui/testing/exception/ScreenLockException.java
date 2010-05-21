/*
 * Created on Jun 11, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.ui.testing.exception;

/**
 * Understands an error thrown when acquiring or releasing a <code>{@link org.fest.ui.testing.lock.ScreenLock}</code>.
 *
 * @author Alex Ruiz
 */
public final class ScreenLockException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Creates a new <code>{@link ScreenLockException}</code>.
   * @param message the detail message.
   */
  public ScreenLockException(String message) {
    super(message);
  }
}
