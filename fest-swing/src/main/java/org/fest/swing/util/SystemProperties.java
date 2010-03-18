/*
 * Created on Mar 18, 2010
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

import org.fest.util.VisibleForTesting;

/**
 * Understands system properties.
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
public final class SystemProperties {

  private static final SystemPropertyReader READER = new SystemPropertyReader();

  private static final String LINE_SEPARATOR = getLineSeparator();

  private static String getLineSeparator() {
    return lineSeparator(READER);
  }

  @VisibleForTesting
  static String lineSeparator(SystemPropertyReader reader) {
    try {
      return reader.systemProperty("line.separator");
    } catch (RuntimeException e) {
      return "\n";
    }
  }

  /**
   * Returns the line separator from the system property "line.separator."
   * @return the line separator from the system property "line.separator."
   */
  public static String lineSeparator() {
    return LINE_SEPARATOR;
  }

  private SystemProperties() {}
}
