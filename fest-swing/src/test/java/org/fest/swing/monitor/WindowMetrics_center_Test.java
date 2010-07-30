/*
 * Created on Jul 31, 2009
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
package org.fest.swing.monitor;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.*;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.junit.Test;

/**
 * Tests for <code>{@link WindowMetrics#center()}</code>.
 *
 * @author Alex Ruiz
 */
public class WindowMetrics_center_Test extends WindowMetrics_TestCase {

  @Test
  public void should_calculate_center() {
    Point center = centerOf(metrics);
    assertThat(center).isEqualTo(expectedCenterOf(window));
  }

  @RunsInEDT
  private static Point centerOf(final WindowMetrics metrics) {
    return execute(new GuiQuery<Point>() {
      @Override
      protected Point executeInEDT() {
        return metrics.center();
      }
    });
  }

  @RunsInEDT
  private static Point expectedCenterOf(final Window w) {
    return execute(new GuiQuery<Point>() {
      @Override
      protected Point executeInEDT() {
        Insets insets = w.getInsets();
        int x = w.getX() + insets.left + ((w.getWidth() - (insets.left + insets.right)) / 2);
        int y = w.getY() + insets.top + ((w.getHeight() - (insets.top + insets.bottom)) / 2);
        return new Point(x, y);
      }
    });
  }
}
