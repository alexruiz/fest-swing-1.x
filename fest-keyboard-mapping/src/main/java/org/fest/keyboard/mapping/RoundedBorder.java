/*
 * Created on Apr 11, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import java.awt.*;

import javax.swing.JLabel;
import javax.swing.border.Border;

import static java.awt.RenderingHints.*;

/**
 * Understands a border with rounded corners.
 *
 * @author Alex Ruiz
 */
class RoundedBorder implements Border {

  private static final int RADIUS = 20;
  private static final Insets PADDING = new Insets(2, 2, 2, 2);
  private final JLabel label = new JLabel("Title");

  public RoundedBorder() {}

  public Insets getBorderInsets(Component c) {
    return PADDING;
  }

  public boolean isBorderOpaque() {
    return true;
  }

  public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
    Graphics2D g2d = (Graphics2D) g.create();
    label.paintComponents(g2d);
    g2d.setColor(c.getBackground().darker());
    g2d.setRenderingHint(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
    g2d.drawRoundRect(x, y, width - 1, height - 1, RADIUS, RADIUS);
  }

}
