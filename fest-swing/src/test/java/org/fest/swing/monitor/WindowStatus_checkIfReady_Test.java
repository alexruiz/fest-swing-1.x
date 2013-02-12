/*
 * Created on Oct 18, 2007
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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.monitor.TestWindows.newWindowsMock;
import static org.fest.swing.monitor.WindowMetrics.absoluteCenterOf;
import static org.fest.swing.query.ComponentSizeQuery.sizeOf;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.swing.timing.Timeout.timeout;

import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Window;

import org.fest.swing.test.core.SequentialEDTSafeTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.fest.swing.timing.Condition;
import org.fest.swing.util.RobotFactory;
import org.junit.Test;

/**
 * Tests for {@link WindowStatus#checkIfReady(Window)}.
 * 
 * @author Alex Ruiz
 */
public class WindowStatus_checkIfReady_Test extends SequentialEDTSafeTestCase {
  private TestWindow window;
  private WindowStatus status;
  private Windows windows;

  @Override
  protected final void onSetUp() {
    window = TestWindow.createNewWindow(getClass());
    windows = newWindowsMock();
    status = new WindowStatus(windows);
  }

  @Override
  protected final void onTearDown() {
    window.destroy();
  }

  @Test
  public void should_move_mouse_to_center_of_Frame_if_width_is_greater_than_height() {
    window.display(new Dimension(300, 100));
    pause(500);
    Point center = absoluteCenterOf(window);
    center.x += WindowStatus.sign();
    new EasyMockTemplate(windows) {
      @Override
      protected void expectations() {
        expect(windows.isShowingButNotReady(window)).andReturn(true);
      }

      @Override
      protected void codeToTest() {
        status.checkIfReady(window);
      }
    }.run();
    assertThat(MouseInfo.getPointerInfo().getLocation()).isEqualTo(center);
  }

  @Test
  public void should_move_mouse_to_center_of_Frame_if_height_is_greater_than_width() {
    window.display(new Dimension(200, 400));
    pause(500);
    Point center = absoluteCenterOf(window);
    center.y += WindowStatus.sign();
    new EasyMockTemplate(windows) {
      @Override
      protected void expectations() {
        expect(windows.isShowingButNotReady(window)).andReturn(true);
      }

      @Override
      protected void codeToTest() {
        status.checkIfReady(window);
      }
    }.run();
    assertThat(MouseInfo.getPointerInfo().getLocation()).isEqualTo(center);
  }

  @Test
  public void should_resize_Window_to_receive_events() {
    window.display(new Dimension(2, 2));
    final Dimension original = sizeOf(window);
    new EasyMockTemplate(windows) {
      @Override
      protected void expectations() {
        expect(windows.isShowingButNotReady(window)).andReturn(true);
      }

      @Override
      protected void codeToTest() {
        status.checkIfReady(window);
      }
    }.run();
    pause(new Condition("Frame to be resized") {
      @Override
      public boolean test() {
        return sizeOf(window).height > original.height;
      }
    }, timeout(5000));
  }

  @Test
  public void should_not_check_if_Frame_is_ready_if_Robot_is_Null() {
    final RobotFactory factory = createMock(RobotFactory.class);
    Point before = MouseInfo.getPointerInfo().getLocation();
    new EasyMockTemplate(windows, factory) {
      @Override
      protected void expectations() throws Throwable {
        expect(factory.newRobotInPrimaryScreen()).andReturn(null);
      }

      @Override
      protected void codeToTest() {
        status = new WindowStatus(windows, factory);
        status.checkIfReady(window);
      }
    }.run();
    // mouse pointer should not have moved
    assertThat(MouseInfo.getPointerInfo().getLocation()).isEqualTo(before);
  }
}
