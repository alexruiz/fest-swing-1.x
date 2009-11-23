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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.*;

/**
 * Test case for <code>{@link RGBColor#isEqualTo(RGBColor, int)}</code>.
 *
 * @author Alex Ruiz
 */
public class RGBColor_isEqualTo_Test {

  private static int r;
  private static int g;
  private static int b;
  private RGBColor color;

  @BeforeClass
  public static void setUpOnce() {
    r = 6;
    g = 8;
    b = 10;
  }

  @Before
  public final void setUp() {
    color = new RGBColor(r, g, b);
  }

  @Test
  public void should_return_true_if_colors_are_exactly_equal() {
    assertTrue(color.isEqualTo(new RGBColor(r, g, b), 0));
  }

  @Test
  public void should_return_true_if_reds_are_similar_using_threshold() {
    assertTrue(color.isEqualTo(new RGBColor(r++, g, b), 1));
  }

  @Test
  public void should_return_true_if_greens_are_similar_using_threshold() {
    assertTrue(color.isEqualTo(new RGBColor(r, g++, b), 1));
  }

  @Test
  public void should_return_true_if_blues_are_similar_using_threshold() {
    assertTrue(color.isEqualTo(new RGBColor(r, g, b++), 1));
  }

  @Test
  public void should_return_false_if_reds_are_not_equal() {
    assertFalse(color.isEqualTo(new RGBColor(0, g, b), 0));
  }

  @Test
  public void should_return_false_if_greens_are_not_equal() {
    assertFalse(color.isEqualTo(new RGBColor(r, 0, b), 0));
  }

  @Test
  public void should_return_false_if_blues_are_not_equal() {
    assertFalse(color.isEqualTo(new RGBColor(r, g, 0), 0));
  }
}
