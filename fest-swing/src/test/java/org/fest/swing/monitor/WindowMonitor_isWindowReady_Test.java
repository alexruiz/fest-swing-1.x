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

import org.junit.Test;

/**
 * Tests for {@link WindowMonitor#isWindowReady(java.awt.Window)}.
 * 
 * @author Alex Ruiz
 */
public class WindowMonitor_isWindowReady_Test extends WindowMonitor_TestCase {
  @Test
  public void should_return_true_if_Windows_indicates_that_Window_is_ready() {
    new EasyMockTemplate(windows) {
      @Override
      protected void expectations() {
        expect(windows.isReady(frame)).andReturn(true);
      }

      @Override
      protected void codeToTest() {
        assertThat(monitor.isWindowReady(frame)).isTrue();
      }
    }.run();
  }

  @Test
  public void should_check_with_WindowStatus_if_Window_is_ready_when_Windows_indicates_it_is_not_ready() {
    new EasyMockTemplate(windows, windowStatus) {
      @Override
      protected void expectations() {
        expect(windows.isReady(frame)).andReturn(false);
        windowStatus.checkIfReady(frame);
        expectLastCall();
      }

      @Override
      protected void codeToTest() {
        assertThat(monitor.isWindowReady(frame)).isFalse();
      }
    }.run();
  }
}
