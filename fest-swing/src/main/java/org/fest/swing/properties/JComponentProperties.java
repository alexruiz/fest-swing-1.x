/*
 * Created on Aug 23, 2010
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
package org.fest.swing.properties;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Rectangle;

import javax.swing.JComponent;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;

/**
 * Queries property values of a <code>{@link JComponent}</code>.
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 * </p>
 *
 * @author Alex Ruiz
 *
 * @since 2.0
 */
public class JComponentProperties {

  /**
   * Indicates whether the given <code>{@link JComponent}</code>'s visible <code>{@link Rectangle}</code> contains the
   * given one.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param c the given {@code JComponent}.
   * @param r the {@code Rectangle} to verify.
   * @return {@code true} if the given {@code Rectangle} is contained in the given {@code JComponent}'s
   * visible {@code Rectangle}.
   */
  @RunsInCurrentThread
  protected static boolean isVisible(JComponent c, Rectangle r) {
    return c.getVisibleRect().contains(r);
  }

  /**
   * Returns the client property stored in the given <code>{@link JComponent}</code>, under the given key.
   * @param c the given {@code JComponent}.
   * @param key the key to use to retrieve the client property.
   * @return the value of the client property stored under the given key, or {@code null} if the property was
   * not found.
   * @throws NullPointerException if the given key is {@code null}.
   */
  @RunsInEDT
  public Object clientProperty(JComponent c, Object key) {
    if (key == null) throw new NullPointerException("The key of the client property to return should not be null");
    return clientPropertyIn(c, key);
  }

  private static Object clientPropertyIn(final JComponent c, final Object key) {
    return execute(new GuiQuery<Object>() {
      @Override protected Object executeInEDT() {
        return c.getClientProperty(key);
      }
    });
  }
}
