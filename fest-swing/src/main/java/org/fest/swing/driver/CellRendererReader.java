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

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands reading the value of a <code>{@link Component}</code> that used as a cell renderer.
 * <p>
 * <b>Note:</b> methods in this interface are <b>not</b> guaranteed to be executed in the event dispatch thread (EDT.)
 * Clients are responsible for invoking them in the EDT.
 * </p>
 *
 * @author Alex Ruiz
 */
@RunsInCurrentThread
public interface CellRendererReader {

  /**
   * Reads the value in the given cell renderer component, or returns {@code null} if the component is not
   * recognized by this reader.
   * <p>
   * <b>Note:</b> Implementations of this method <b>may not</b> be guaranteed to be executed in the event dispatch
   * thread (EDT.) Clients are responsible for invoking this method in the EDT.
   * </p>
   * @param c the given cell renderer component.
   * @return the value of the given {@code Component}, or {@code null} if the renderer belongs to an unknown
   * component type.
   */
  String valueFrom(Component c);
}
