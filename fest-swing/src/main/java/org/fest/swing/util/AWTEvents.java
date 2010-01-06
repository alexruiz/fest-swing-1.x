/*
 * Created on Nov 11, 2007
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
package org.fest.swing.util;

import static java.awt.event.ComponentEvent.COMPONENT_SHOWN;
import static java.awt.event.WindowEvent.WINDOW_CLOSED;
import static java.awt.event.WindowEvent.WINDOW_OPENED;

import java.awt.AWTEvent;
import java.awt.Window;

/**
 * Understands utility methods related to AWT events.
 *
 * @author Alex Ruiz
 */
public final class AWTEvents {

  /**
   * Returns <code>true</code> if the id of the given event is equal to
   * <code>{@link java.awt.event.WindowEvent#WINDOW_OPENED WINDOW_OPENED}</code>
   * @param e the given event.
   * @return <code>true</code> if the id of the given event is equal to <code>WINDOW_OPENED</code>; <code>false</code>
   * otherwise.
   */
  public static boolean windowOpened(AWTEvent e) {
    return idEquals(e, WINDOW_OPENED);
  }

  /**
   * Returns <code>true</code> if the id of the given event is equal to
   * <code>{@link java.awt.event.WindowEvent#COMPONENT_SHOWN COMPONENT_SHOWN}</code> and the source of the event is a
   * <code>{@link Window}</code>.
   * @param e the given event.
   * @return <code>true</code> if the id of the given event is equal to <code>COMPONENT_SHOWN</code> and the source of
   * the event is a window; <code>false</code> otherwise.
   */
  public static boolean windowShown(AWTEvent e) {
    return idEquals(e, COMPONENT_SHOWN)  && e.getSource() instanceof Window;
  }

  /**
   * Returns <code>true</code> if the id of the given event is equal to
   * <code>{@link java.awt.event.WindowEvent#WINDOW_CLOSED WINDOW_CLOSED}</code>
   * @param e the given event.
   * @return <code>true</code> if the id of the given event is equal to <code>WINDOW_CLOSED</code>; <code>false</code>
   * otherwise.
   */
  public static boolean windowClosed(AWTEvent e) {
    return idEquals(e, WINDOW_CLOSED);
  }

  private static boolean idEquals(AWTEvent e, int expectedId) {
    return e.getID() == expectedId;
  }

  private AWTEvents() {}
}
