/*
 * Created on Jul 31, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.monitor;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import javax.swing.JTextField;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.FailOnThreadViolationRepaintManager;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.lock.ScreenLock;
import org.fest.swing.test.swing.TestWindow;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for {@link WindowAvailabilityMonitor}.
 * 
 * @author Alex Ruiz
 */
public class WindowAvailabilityMonitor_eventDispatched_Test extends WindowAvailabilityMonitor_TestCase {
  private MyWindow window;

  @BeforeClass
  public static void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  @Override
  void onSetUp() {
    ScreenLock.instance().acquire(this);
    window = MyWindow.createNew(getClass());
  }

  @After
  public void tearDown() {
    try {
      window.destroy();
    } finally {
      ScreenLock.instance().release(this);
    }
  }

  @Test
  public void should_mark_source_Window_as_ready_if_event_is_MouseEvent() {
    new EasyMockTemplate(windows) {
      @Override
      protected void expectations() {
        windows.markAsReady(window);
      }

      @Override
      protected void codeToTest() {
        monitor.eventDispatched(mouseEvent(window));
      }
    }.run();
  }

  @Test
  public void should_mark_source_Window_ancestor_as_ready_if_event_is_MouseEvent() {
    final JTextField source = window.textField;
    new EasyMockTemplate(windows) {
      @Override
      protected void expectations() {
        windows.markAsReady(window);
      }

      @Override
      protected void codeToTest() {
        monitor.eventDispatched(mouseEvent(source));
      }
    }.run();
  }

  @Test
  public void should_not_mark_source_Window_as_ready_if_event_is_not_MouseEvent() {
    new EasyMockTemplate(windows) {
      @Override
      protected void expectations() { /* should not call markAsReady */
      }

      @Override
      protected void codeToTest() {
        monitor.eventDispatched(new KeyEvent(window, 8, 9238, 0, 0, 'a'));
      }
    }.run();
  }

  private MouseEvent mouseEvent(Component source) {
    return new MouseEvent(source, 8, 8912, 0, 0, 0, 0, false, 0);
  }

  static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    @RunsInEDT
    static MyWindow createNew(final Class<?> testClass) {
      return execute(new GuiQuery<MyWindow>() {
        @Override
        protected MyWindow executeInEDT() {
          return new MyWindow(testClass);
        }
      });
    }

    final JTextField textField = new JTextField("Hello");

    private MyWindow(Class<?> testClass) {
      super(testClass);
      addComponents(textField);
    }
  }
}
