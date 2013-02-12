/*
 * Created on Oct 14, 2007
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
package org.fest.swing.monitor;

import static java.awt.AWTEvent.COMPONENT_EVENT_MASK;
import static java.awt.AWTEvent.WINDOW_EVENT_MASK;
import static java.awt.event.WindowEvent.WINDOW_CLOSED;
import static java.awt.event.WindowEvent.WINDOW_CLOSING;
import static java.awt.event.WindowEvent.WINDOW_FIRST;
import static java.awt.event.WindowEvent.WINDOW_LAST;
import static java.awt.event.WindowEvent.WINDOW_OPENED;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.monitor.TestContexts.newMockContext;
import static org.fest.swing.monitor.TestWindows.newWindowsMock;
import static org.fest.swing.test.awt.Toolkits.newToolkitStub;
import static org.fest.swing.test.builder.JTextFields.textField;
import static org.fest.util.Lists.newArrayList;

import java.applet.Applet;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FileDialog;
import java.awt.Window;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.fest.swing.listener.WeakEventListener;
import org.fest.swing.test.awt.ToolkitStub;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for {@link ContextMonitor}. TODO Split
 * 
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class ContextMonitorTest extends EDTSafeTestCase {
  private static final long EVENT_MASK = WINDOW_EVENT_MASK | COMPONENT_EVENT_MASK;

  private ContextMonitor monitor;

  private Windows windows;
  private Context context;
  private TestWindow window;

  private final int eventId;

  @Parameters
  public static Collection<Object[]> eventsBetweenWindowFirstAndWindowLast() {
    List<Object[]> ids = newArrayList();
    for (int id = WINDOW_FIRST; id <= WINDOW_LAST; id++) {
      if (id == WINDOW_OPENED || id == WINDOW_CLOSED || id == WINDOW_CLOSING) {
        continue;
      }
      ids.add(new Object[] { id });
    }
    return ids;
  }

  public ContextMonitorTest(int eventId) {
    this.eventId = eventId;
  }

  @Before
  public void setUp() {
    window = TestWindow.createNewWindow(getClass());
    windows = newWindowsMock();
    context = newMockContext();
    monitor = new ContextMonitor(context, windows);
  }

  @After
  public void tearDown() {
    window.destroy();
  }

  @Test
  public void shouldAttachItSelfToToolkit() {
    ToolkitStub toolkit = newToolkitStub();
    monitor.attachTo(toolkit);
    List<WeakEventListener> eventListeners = toolkit.eventListenersUnderEventMask(EVENT_MASK, WeakEventListener.class);
    assertThat(eventListeners).hasSize(1);
    WeakEventListener weakEventListener = eventListeners.get(0);
    assertThat(weakEventListener.underlyingListener()).isSameAs(monitor);
  }

  @Test
  public void shouldNotProcessEventIfComponentIsNotWindowOrApplet() {
    new EasyMockTemplate(windows, context) {
      @Override
      protected void expectations() {
      }

      @Override
      protected void codeToTest() {
        monitor.eventDispatched(new ComponentEvent(textField().createNew(), 8));
      }
    }.run();
  }

  @Test
  public void shouldProcessEventWithIdEqualToWindowOpen() {
    new EasyMockTemplate(windows, context) {
      @Override
      protected void expectations() {
        context.addContextFor(window);
        windows.attachNewWindowVisibilityMonitor(window);
        windows.markAsShowing(window);
        expectEventQueueLookupFor(window);
      }

      @Override
      protected void codeToTest() {
        dispatchWindowOpenedEventToMonitor(window);
      }
    }.run();
  }

  @Test
  public void shouldProcessEventWithIdEqualToWindowOpenedAndMarkWindowAsReadyIfWindowIsFileDialog() {
    final Window w = new FileDialog(window);
    new EasyMockTemplate(windows, context) {
      @Override
      protected void expectations() {
        context.addContextFor(w);
        windows.attachNewWindowVisibilityMonitor(w);
        windows.markAsShowing(w);
        windows.markAsReady(w);
        expectEventQueueLookupFor(w);
      }

      @Override
      protected void codeToTest() {
        dispatchWindowOpenedEventToMonitor(w);
      }
    }.run();
  }

  @Test
  public void shouldProcessEventWithIdEqualToWindowClosedAndWithRootWindow() {
    new EasyMockTemplate(windows, context) {
      @Override
      protected void expectations() {
        context.removeContextFor(window);
        windows.markAsClosed(window);
        expectEventQueueLookupFor(window);
      }

      @Override
      protected void codeToTest() {
        dispatchWindowClosedEventToMonitor(window);
      }
    }.run();
  }

  @Test
  public void shouldProcessEventWithIdEqualToWindowClosedAndWithNotRootWindow() {
    final Applet applet = new Applet();
    window.add(applet);
    new EasyMockTemplate(windows, context) {
      @Override
      protected void expectations() {
        expectEventQueueLookupFor(applet);
      }

      @Override
      protected void codeToTest() {
        dispatchWindowClosedEventToMonitor(applet);
      }
    }.run();
  }

  @Test
  public void shouldNotProcessEventWithIdWindowClosing() {
    new EasyMockTemplate(windows, context) {
      @Override
      protected void expectations() {
        expectEventQueueLookupFor(window);
      }

      @Override
      protected void codeToTest() {
        dispatchWindowClosingEventToMonitor(window);
      }
    }.run();
  }

  @Test
  public void shouldAddToContextIfComponentEventQueueNotEqualToSystemEventQueue() {
    new EasyMockTemplate(windows, context) {
      @Override
      protected void expectations() {
        expect(context.storedQueueFor(window)).andReturn(new EventQueue());
        context.addContextFor(window);
      }

      @Override
      protected void codeToTest() {
        dispatchWindowClosingEventToMonitor(window);
      }
    }.run();
  }

  @Test
  public void shouldProcessEventWithIdBetweenWindowFirstAndWindowLastAndWindowNotInContext() {
    new EasyMockTemplate(windows, context) {
      @Override
      protected void expectations() {
        expect(context.rootWindows()).andReturn(new ArrayList<Window>());
        context.addContextFor(window);
        windows.attachNewWindowVisibilityMonitor(window);
        windows.markAsShowing(window);
        expectEventQueueLookupFor(window);
      }

      @Override
      protected void codeToTest() {
        dispatchEventToMonitor(window, eventId);
      }
    }.run();
  }

  @Test
  public void shouldProcessEventWithIdBetweenWindowFirstAndWindowLastAndWindowInContextAndClosed() {
    new EasyMockTemplate(windows, context) {
      @Override
      protected void expectations() {
        expect(context.rootWindows()).andReturn(frameInList());
        expect(windows.isClosed(window)).andReturn(true);
        context.addContextFor(window);
        windows.attachNewWindowVisibilityMonitor(window);
        windows.markAsShowing(window);
        expectEventQueueLookupFor(window);
      }

      @Override
      protected void codeToTest() {
        dispatchEventToMonitor(window, eventId);
      }
    }.run();
  }

  @Test
  public void shouldProcessEventWithIdBetweenWindowFirstAndWindowLastAndWindowInContextAndNotClosed() {
    new EasyMockTemplate(windows, context) {
      @Override
      protected void expectations() {
        expect(context.rootWindows()).andReturn(frameInList());
        expect(windows.isClosed(window)).andReturn(false);
        expectEventQueueLookupFor(window);
      }

      @Override
      protected void codeToTest() {
        dispatchEventToMonitor(window, eventId);
      }
    }.run();
  }

  private void expectEventQueueLookupFor(Component c) {
    expect(context.storedQueueFor(c)).andReturn(c.getToolkit().getSystemEventQueue());
  }

  private void dispatchWindowOpenedEventToMonitor(Component c) {
    dispatchEventToMonitor(c, WINDOW_OPENED);
  }

  private void dispatchWindowClosedEventToMonitor(Component c) {
    dispatchEventToMonitor(c, WINDOW_CLOSED);
  }

  private void dispatchWindowClosingEventToMonitor(Component c) {
    dispatchEventToMonitor(c, WINDOW_CLOSING);
  }

  private void dispatchEventToMonitor(Component c, int id) {
    monitor.eventDispatched(new ComponentEvent(c, id));
  }

  private List<Window> frameInList() {
    List<Window> rootWindows = new ArrayList<Window>();
    rootWindows.add(window);
    return rootWindows;
  }
}
