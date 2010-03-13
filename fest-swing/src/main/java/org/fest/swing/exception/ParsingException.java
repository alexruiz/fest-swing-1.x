/*
 * Created on Mar 13, 2010
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
package org.fest.swing.exception;

/**
 * Understands an exception thrown when an unexpected error occurs while parsing a file.
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public class ParsingException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  /**
   * Creates a new </code>{@link ParsingException}</code>.
   * @param message the detail message.
   */
  public ParsingException(String message) {
    super(message);
  }

  /**
   * Creates a new </code>{@link ParsingException}</code>.
   * @param message the detail message.
   * @param cause the cause of the error.
   */
  public ParsingException(String message, Throwable cause) {
    super(message, cause);
  }
}
