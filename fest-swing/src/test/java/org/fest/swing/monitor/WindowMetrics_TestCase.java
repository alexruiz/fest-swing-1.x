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

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Window;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.SequentialEDTSafeTestCase;
import org.fest.swing.test.swing.TestWindow;

/**
 * Base test case for <code>{@link WindowMetrics}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class WindowMetrics_TestCase extends SequentialEDTSafeTestCase {

  WindowMetrics metrics;
  TestWindow window;

  @Override protected final void onSetUp() {
    window = TestWindow.createAndShowNewWindow(getClass());
    metrics = newWindowMetricsFor(window);
  }

  @RunsInEDT
  private static WindowMetrics newWindowMetricsFor(final Window window) {
    return execute(new GuiQuery<WindowMetrics>() {
      @Override protected WindowMetrics executeInEDT() {
        return new WindowMetrics(window);
      }
    });
  }

  @Override protected final void onTearDown() {
    window.destroy();
  }
}
