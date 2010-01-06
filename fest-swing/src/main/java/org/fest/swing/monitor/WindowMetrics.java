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

import java.awt.*;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands some window metrics.
 *
 * @author Alex Ruiz
 */
final class WindowMetrics {

  private final Window window;

  final Insets insets;

  @RunsInCurrentThread
  WindowMetrics(Window w) {
    this.window = w;
    insets = w.getInsets();
  }

  @RunsInCurrentThread
  Point center() {
    int x = window.getX() + insets.left + horizontalCenter();
    int y = window.getY() + insets.top + verticalCenter();
    return new Point(x, y);
  }

  @RunsInCurrentThread
  private int horizontalCenter() {
    return (window.getWidth() - leftAndRightInsets()) / 2;
  }

  @RunsInCurrentThread
  int leftAndRightInsets() {
    return insets.left + insets.right;
  }

  @RunsInCurrentThread
  private int verticalCenter() {
    return (window.getHeight() - topAndBottomInsets()) / 2;
  }

  @RunsInCurrentThread
  int topAndBottomInsets() {
    return insets.top + insets.bottom;
  }
}
