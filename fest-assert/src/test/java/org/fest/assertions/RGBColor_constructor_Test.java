/*
 * Created on Mar 25, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.assertions;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

/**
 * Test case for <code>{@link RGBColor#RGBColor(int)}</code>.
 *
 * @author Alex Ruiz
 */
public class RGBColor_constructor_Test {

  @Test
  public void should_separate_red_green_and_blue() {
    Color awtColor = new Color(6, 8, 10);
    RGBColor color = new RGBColor(awtColor.getRGB());
    assertEquals(awtColor.getRed(), color.r());
    assertEquals(awtColor.getGreen(), color.g());
    assertEquals(awtColor.getBlue(), color.b());
  }
}
