/*
 * Created on Jul 29, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import javax.swing.JInternalFrame;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands an action that indicates if a <code>{@link JInternalFrame}</code> is iconified or not.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 * @see JInternalFrame#isIcon()
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
final class JInternalFrameIconQuery {

  @RunsInCurrentThread
  static boolean isIconified(JInternalFrame frame) {
    if (frame.isMaximum()) return false;
    return frame.isIcon();
  }

  private JInternalFrameIconQuery() {}
}