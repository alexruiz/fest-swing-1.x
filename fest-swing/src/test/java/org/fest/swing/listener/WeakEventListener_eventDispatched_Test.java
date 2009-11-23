/*
 * Created on Oct 8, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.swing.listener;

import static java.awt.AWTEvent.WINDOW_EVENT_MASK;
import static org.fest.assertions.Assertions.assertThat;

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;

import org.fest.swing.test.awt.ToolkitStub;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link WeakEventListener#eventDispatched(AWTEvent)}</code>.
 *
 * @author Alex Ruiz
 */
public class WeakEventListener_eventDispatched_Test {

  private static final long EVENT_MASK = WINDOW_EVENT_MASK;

  private ToolkitStub toolkit;
  private UnderlyingEventListener underlying;
  private WeakEventListener listener;

  @Before public void setUp() {
    toolkit = ToolkitStub.createNew();
    underlying = new UnderlyingEventListener();
    listener = WeakEventListener.attachAsWeakEventListener(toolkit, underlying, EVENT_MASK);
  }

  @Test
  public void should_wrap_given_EventListener_and_add_itself_to_Toolkit_with_given_mask() {
    assertThat(listener.underlyingListener()).isSameAs(underlying);
    assertThat(toolkit.contains(listener, EVENT_MASK)).isTrue();
  }

  @Test
  public void should_dispatch_events_to_wrapped_EventListener() {
    AWTEvent event = awtEvent();
    listener.eventDispatched(event);
    assertThat(underlying.dispatchedEvent).isSameAs(event);
  }

  @Test
  public void should_remove_itself_from_Toolkit_if_wrapped_EventListener_is_null() {
    listener.simulateUnderlyingListenerIsGarbageCollected();
    listener.eventDispatched(awtEvent());
    assertThat(toolkit.contains(listener, EVENT_MASK)).isFalse();
  }

  private static AWTEvent awtEvent() {
    return new AWTEvent(new Object(), 0) {
      private static final long serialVersionUID = 1L;
    };
  }

  private static class UnderlyingEventListener implements AWTEventListener {
    AWTEvent dispatchedEvent;

    UnderlyingEventListener() {}

    public void eventDispatched(AWTEvent event) {
      dispatchedEvent = event;
    }
  }
}
