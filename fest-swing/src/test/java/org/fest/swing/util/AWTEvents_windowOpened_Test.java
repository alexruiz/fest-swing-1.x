/*
 * Created on Jul 30, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.util;

import static java.awt.event.WindowEvent.WINDOW_CLOSING;
import static java.awt.event.WindowEvent.WINDOW_OPENED;
import static org.fest.assertions.Assertions.assertThat;

import java.awt.AWTEvent;
import java.awt.event.WindowEvent;

import org.fest.swing.test.core.SequentialTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link AWTEvents#windowOpened(java.awt.AWTEvent)}</code>.
 *
 * @author Alex Ruiz
 */
public class AWTEvents_windowOpened_Test extends SequentialTestCase {

  private TestWindow source;

  @Override protected void onSetUp() {
    source = TestWindow.createNewWindow(getClass());
  }

  @Override protected void onTearDown() {
    source.destroy();
  }

  @Test 
  public void should_return_true_if_Window_opened() {
    AWTEvent event = new WindowEvent(source, WINDOW_OPENED);
    assertThat(AWTEvents.windowOpened(event)).isTrue();    
  }

  @Test 
  public void should_return_false_if_Window_not_opened() {
    AWTEvent event = new WindowEvent(source, WINDOW_CLOSING);
    assertThat(AWTEvents.windowOpened(event)).isFalse();
  }

}
