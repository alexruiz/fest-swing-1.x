/*
 * Created on Jun 24, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.input;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;

import java.awt.AWTEvent;
import java.awt.event.AWTEventListener;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.awt.ToolkitStub;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link EventNormalizer#eventDispatched(AWTEvent)}</code>.
 *
 * @author Alex Ruiz
 */
public class EventNormalizer_eventDispatched_Test extends EventNormalizer_TestCase {

  private DisposedWindowMonitor disposedWindowMonitor;
  private AWTEventListener delegateEventListener;
  private AWTEvent event;
  private EventNormalizer eventNormalizer;

  @Before
  public void setUp() {
    disposedWindowMonitor = createMock(DisposedWindowMonitor.class);
    delegateEventListener = mockDelegateEventListener();
    event = createMock(AWTEvent.class);
    eventNormalizer = new EventNormalizer(disposedWindowMonitor);
    eventNormalizer.startListening(ToolkitStub.createNew(), delegateEventListener, 8);
  }

  @Test
  public void should_delegate_event_if_it_is_not_a_duplicate_dispose() {
    new EasyMockTemplate(disposedWindowMonitor, delegateEventListener) {
      protected void expectations() {
        expect(disposedWindowMonitor.isDuplicateDispose(event)).andReturn(false);
        delegateEventListener.eventDispatched(event);
        expectLastCall().once();
      }

      protected void codeToTest() {
        eventNormalizer.eventDispatched(event);
      }
    }.run();
  }

  @Test
  public void should_not_delegate_event_if_it_is_a_duplicate_dispose() {
    new EasyMockTemplate(disposedWindowMonitor, delegateEventListener) {
      protected void expectations() {
        expect(disposedWindowMonitor.isDuplicateDispose(event)).andReturn(true);
      }

      protected void codeToTest() {
        eventNormalizer.eventDispatched(event);
      }
    }.run();
  }
}
