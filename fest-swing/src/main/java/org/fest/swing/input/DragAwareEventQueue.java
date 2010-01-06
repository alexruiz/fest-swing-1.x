/*
 * Created on Apr 3, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.input;

import static java.awt.AWTEvent.MOUSE_EVENT_MASK;
import static java.awt.AWTEvent.MOUSE_MOTION_EVENT_MASK;
import static java.awt.event.MouseEvent.*;
import static javax.swing.SwingUtilities.convertMouseEvent;
import static javax.swing.SwingUtilities.getDeepestComponentAt;

import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.MouseEvent;
import java.util.EmptyStackException;

/**
 * Catches native drop target events, which are normally hidden from AWTEventListeners.
 *
 * @author Alex Ruiz
 */
class DragAwareEventQueue extends EventQueue {

  private final Toolkit toolkit;
  private final long mask;
  private final AWTEventListener eventListener;
  private final NativeDnDIdentifier nativeDnd;

  DragAwareEventQueue(Toolkit toolkit, long mask, AWTEventListener eventListener) {
    this(toolkit, mask, eventListener, new NativeDnDIdentifier());
  }

  DragAwareEventQueue(Toolkit toolkit, long mask, AWTEventListener eventListener, NativeDnDIdentifier nativeDnd) {
    this.toolkit = toolkit;
    this.mask = mask;
    this.eventListener = eventListener;
    this.nativeDnd = nativeDnd;
  }

  /**
   * Stops dispatching events using this <code>EventQueue</code>, only if this <code>EventQueue</code> is the same
   * as the <code>{@link Toolkit}</code>'s system event queue. Any pending events are transferred to the previous
   * <code>EventQueue</code> for processing.
   * @throws EmptyStackException if no previous push was made on this <code>EventQueue</code>.
   */
  @Override public void pop() throws EmptyStackException {
    EventQueue systemEventQueue = toolkit.getSystemEventQueue();
    if (systemEventQueue == this) super.pop();
  }

  /**
   * Dispatch native drag/drop events the same way non-native drags are reported. Enter/Exit are reported with the
   * appropriate source, while drag and release events use the drag source as the source.
   * @param e an instance of <code>java.awt.AWTEvent</code>.
   */
  @Override protected void dispatchEvent(AWTEvent e) {
    // TODO: implement enter/exit events
    // TODO: change source to drag source, not mouse under
    if (nativeDnd.isNativeDragAndDrop(e)) {
      MouseEvent mouseEvent = (MouseEvent) e;
      Component target = getDeepestComponentAt(mouseEvent.getComponent(), mouseEvent.getX(), mouseEvent.getY());
      if (target != mouseEvent.getSource())
        mouseEvent = convertMouseEvent(mouseEvent.getComponent(), mouseEvent, target);
      relayDnDEvent(mouseEvent);
    }
    super.dispatchEvent(e);
  }

  private void relayDnDEvent(MouseEvent event) {
    int eventId = event.getID();
    if (eventId == MOUSE_MOVED || eventId == MOUSE_DRAGGED) {
      if ((mask & MOUSE_MOTION_EVENT_MASK) != 0) eventListener.eventDispatched(event);
      return;
    }
    if (eventId >= MOUSE_FIRST && eventId <= MOUSE_LAST) {
      if ((mask & MOUSE_EVENT_MASK) != 0) eventListener.eventDispatched(event);
    }
  }
}