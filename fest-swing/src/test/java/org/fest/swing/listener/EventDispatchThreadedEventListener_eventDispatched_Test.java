/*
 * Created on Apr 6, 2008
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
package org.fest.swing.listener;

import static javax.swing.SwingUtilities.isEventDispatchThread;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.test.core.Mocks.mockAWTEvent;
import static org.fest.swing.timing.Pause.pause;

import java.awt.AWTEvent;

import org.fest.swing.edt.GuiTask;
import org.fest.swing.timing.Condition;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link EventDispatchThreadedEventListener#eventDispatched(AWTEvent)}</code>.
 *
 * @author Alex Ruiz
 */
public class EventDispatchThreadedEventListener_eventDispatched_Test {

  private AWTEvent event;
  private Listener listener;

  @Before
  public void setUp() {
    event = mockAWTEvent();
    listener = new Listener();
  }

  @Test
  public void should_always_process_event_in_EDT() {
    listener.eventDispatched(event);
    pause(new Condition("event to be processed in EDT") {
      public boolean test() {
        return listener.event == event && listener.wasProcessedInEventDispatchThread;
      }
    });
  }

  @Test
  public void should_process_event_directly_if_called_in_EDT() throws Exception {
    execute(new GuiTask() {
      protected void executeInEDT() {
        listener.eventDispatched(event);
      }
    });
    assertThat(listener.event).isSameAs(event);
    assertThat(listener.wasProcessedInEventDispatchThread).isTrue();
  }

  private static class Listener extends EventDispatchThreadedEventListener {
    AWTEvent event;
    boolean wasProcessedInEventDispatchThread;

    Listener() {}

    protected void processEvent(AWTEvent newEvent) {
      this.event = newEvent;
      wasProcessedInEventDispatchThread = isEventDispatchThread();
    }
  }
}
