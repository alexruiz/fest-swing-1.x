/*
 * Created on Oct 8, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.listener;

import java.awt.AWTEvent;
import java.awt.Toolkit;
import java.awt.event.AWTEventListener;
import java.lang.ref.WeakReference;

/**
 * Understands an event listener that wraps a given <code>{@link AWTEventListener}</code> and:
 * <ul>
 * <li>attaches itself to the default toolkit</li>
 * <li>dispatches any given event to the wrapped listener</li>
 * <li>removes itself from the default toolkit when the wrapped listener gets garbage-collected</li>
 * </ul>
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class WeakEventListener implements AWTEventListener {

  private final WeakReference<AWTEventListener> listenerReference;
  private final Toolkit toolkit;

  /**
   * Creates a new <code>{@link WeakEventListener}</code> and adds it to the given <code>{@link Toolkit}</code> using
   * the given event mask. The created <code>{@link WeakEventListener}</code> simply "decorates" the given
   * <code>{@link AWTEventListener}</code>. All events dispatched to the <code>{@link WeakEventListener}</code> are
   * re-routed to the underlying <code>{@link AWTEventListener}</code>. When the underlying 
   * <code>{@link AWTEventListener}</code> is garbage-collected, the <code>{@link WeakEventListener}</code> will remove
   * itself from the toolkit.
   * @param toolkit the given AWT <code>Toolkit</code>.
   * @param listener the underlying listener to wrap.
   * @param eventMask the event mask to use to attach the <code>WeakEventListener</code> to the toolkit.
   * @return the created <code>WeakEventListener</code>.
   */
  public static WeakEventListener attachAsWeakEventListener(Toolkit toolkit, AWTEventListener listener, long eventMask) {
    WeakEventListener l = new WeakEventListener(toolkit, listener);
    toolkit.addAWTEventListener(l, eventMask);
    return l;
  }
  
  WeakEventListener(Toolkit toolkit, AWTEventListener listener) {
    listenerReference = new WeakReference<AWTEventListener>(listener);
    this.toolkit = toolkit;
  }

  /**
   * Returns the underlying listener.
   * @return the underlying listener.
   */
  public AWTEventListener underlyingListener() {
    return listenerReference.get();
  }
  
  /**
   * Dispatches the given event to the wrapped event listener. If the wrapped listener is <code>null</code> 
   * (garbage-collected,) this listener will remove itself from the default toolkit.
   * @param e the event dispatched in the AWT.
   */
  public void eventDispatched(AWTEvent e) {
    AWTEventListener listener = listenerReference.get();
    if (listener == null) {
      dispose();
      return;
    }
    listener.eventDispatched(e);
  }
  
  /** Removes itself from the <code>{@link Toolkit}</code> this listener is attached to. */
  public void dispose() {
    toolkit.removeAWTEventListener(this);
  }

  /**
   * Removes the wrapped listener from the <code>{@link WeakReference}</code> (to simulate garbage collection). This 
   * method should be used only for <strong>testing only</strong>.
   */
  void simulateUnderlyingListenerIsGarbageCollected() {
    listenerReference.clear();
  }
}
