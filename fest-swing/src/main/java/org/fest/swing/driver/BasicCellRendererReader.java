/*
 * Created on Oct 21, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import java.awt.Component;

import javax.swing.JLabel;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands a basic implementation of <code>{@link CellRendererReader}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicCellRendererReader implements CellRendererReader {

  /**
   * Reads the value in the given cell renderer component, or returns {@code null} if the renderer belongs to an
   * unknown component type. Internally, this method will call <code>getText()</code> if the given renderer is an
   * instance of <code>{@link JLabel}</code></li>.
   * <p>
   * <b>Note:</b> This method is <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.) Clients are
   * responsible for calling this method from the EDT.
   * </p>
   * @param c the given cell renderer component.
   * @return the value of the given {@code Component}, or {@code null} if the renderer belongs to an unknown
   * component type.
   */
  @RunsInCurrentThread
  public String valueFrom(Component c) {
    if (c instanceof JLabel) return ((JLabel)c).getText();
    return null;
  }
}
