/*
 * Created on Jan 15, 2008
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
 * Copyright @2008 the original author or authors.
 */
package org.fest.util;

import java.io.Closeable;

/**
 * Understands utility methods related to <code>{@link Closeable}</code>.
 *
 * @author Yvonne Wang
 */
public final class Closeables {

  /**
   * Closes the given <code>{@link Closeable}</code>s, ignoring any thrown exceptions.
   * @param closeables the <code>Closeable</code>s to close.
   */
  public static void close(Closeable...closeables) {
    for (Closeable c : closeables) closeCloseable(c);
  }

  private static void closeCloseable(Closeable c) {
    if (c == null) return;
    try {
      c.close();
    } catch (Exception e) {}
  }

  private Closeables() {}
}
