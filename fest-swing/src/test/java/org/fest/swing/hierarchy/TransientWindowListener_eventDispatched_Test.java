/*
 * Created on Nov 12, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.hierarchy;

import static java.awt.event.WindowEvent.WINDOW_CLOSED;
import static org.fest.swing.timing.Pause.pause;

import java.awt.AWTEvent;
import java.awt.event.WindowEvent;

import org.junit.Test;

/**
 * Tests for {@link TransientWindowListener#eventDispatched(AWTEvent)}.
 * 
 * @author Alex Ruiz
 */
public class TransientWindowListener_eventDispatched_Test extends TransientWindowListener_eventDispatched_TestCase {
  private WindowEvent event;

  @Override
  void onSetUp() {
    event = new WindowEvent(eventSource, WINDOW_CLOSED);
  }

  @Test
  public void shouldFilterClosedWindowAfterEventIsProcessed() {
    new EasyMockTemplate(mockWindowFilter) {
      @Override
      protected void expectations() {
        expect(mockWindowFilter.isIgnored(eventSource)).andReturn(false);
        mockWindowFilter.implicitlyIgnore(eventSource);
        expectLastCall();
        expect(mockWindowFilter.isImplicitlyIgnored(eventSource)).andReturn(true);
        mockWindowFilter.ignore(eventSource);
        expectLastCall();
      }

      @Override
      protected void codeToTest() {
        listener.eventDispatched(event);
        waitTillClosedEventIsHandled();
      }
    }.run();
  }

  @Test
  public void shouldNotFilterClosedWindowAfterEventIsProcessedIfWindowNotImplicitFiltered() {
    new EasyMockTemplate(mockWindowFilter) {
      @Override
      protected void expectations() {
        expect(mockWindowFilter.isIgnored(eventSource)).andReturn(false);
        mockWindowFilter.implicitlyIgnore(eventSource);
        expectLastCall();
        expect(mockWindowFilter.isImplicitlyIgnored(eventSource)).andReturn(false);
      }

      @Override
      protected void codeToTest() {
        listener.eventDispatched(event);
        waitTillClosedEventIsHandled();
      }
    }.run();
  }

  private void waitTillClosedEventIsHandled() {
    pause(2000);
  }
}
