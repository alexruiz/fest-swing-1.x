/*
 * Created on Oct 18, 2007
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
package org.fest.swing.monitor;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.monitor.TestWindows.newWindowsMock;
import static org.fest.swing.query.ComponentSizeQuery.sizeOf;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.swing.timing.Timeout.timeout;

import java.awt.*;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.test.core.SequentialTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.fest.swing.timing.Condition;
import org.fest.swing.util.RobotFactory;
import org.junit.Test;

/**
 * Tests for <code>{@link WindowStatus#checkIfReady(Window)}</code>.
 *
 * @author Alex Ruiz
 */
public class WindowStatus_checkIfReady_Test extends SequentialTestCase {

  private WindowStatus status;
  private TestWindow window;
  private Windows windows;

  @Override protected final void onSetUp() {
    window = TestWindow.createNewWindow(getClass());
    windows = newWindowsMock();
    status = new WindowStatus(windows);
  }

  @Override protected final void onTearDown() {
    window.destroy();
  }

  @Test
  public void should_move_mouse_to_center_of_Frame_if_width_is_greater_than_height() {
    window.display();
    Point center = new WindowMetrics(window).center();
    center.x += WindowStatus.sign();
    new EasyMockTemplate(windows) {
      @Override protected void expectations() {
        expectFrameIsReady();
      }

      @Override protected void codeToTest() {
        status.checkIfReady(window);
      }
    }.run();
    assertThat(MouseInfo.getPointerInfo().getLocation()).isEqualTo(center);
  }

  @Test
  public void should_move_mouse_to_center_of_Frame_if_height_is_greater_than_width() {
    window.display(new Dimension(200, 400));
    Point center = new WindowMetrics(window).center();
    center.y += WindowStatus.sign();
    new EasyMockTemplate(windows) {
      @Override protected void expectations() {
        expectFrameIsReady();
      }

      @Override protected void codeToTest() {
        status.checkIfReady(window);
      }
    }.run();
    assertThat(MouseInfo.getPointerInfo().getLocation()).isEqualTo(center);
  }

  private void expectFrameIsReady() {
    expect(windows.isShowingButNotReady(window)).andReturn(false);
  }

  @Test
  public void should_resize_Window_to_receive_events() {
    window.display(new Dimension(0 ,0));
    final Dimension original = sizeOf(window);
    new EasyMockTemplate(windows) {
      @Override protected void expectations() {
        expect(windows.isShowingButNotReady(window)).andReturn(true);
      }

      @Override protected void codeToTest() {
        status.checkIfReady(window);
      }
    }.run();
    pause(new Condition("Frame to be resized") {
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
      @Override protected void expectations() throws Throwable {
        expect(factory.newRobotInPrimaryScreen()).andReturn(null);
      }

      @Override protected void codeToTest() {
        status = new WindowStatus(windows, factory);
        status.checkIfReady(window);
      }
    }.run();
    // mouse pointer should not have moved
    assertThat(MouseInfo.getPointerInfo().getLocation()).isEqualTo(before);
  }
}
