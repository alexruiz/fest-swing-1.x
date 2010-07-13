/*
 * Created on Mar 25, 2009
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
package org.fest.assertions;

import static java.lang.Math.abs;
import static java.lang.String.valueOf;
import static org.fest.util.Strings.concat;

/**
 * Understands a color.
 *
 * @author Alex Ruiz
 */
final class RGBColor {

  private final int r;
  private final int g;
  private final int b;

  RGBColor(int rgb) {
    this(r(rgb), g(rgb), b(rgb));
  }

  RGBColor(int r, int g, int b) {
    this.r = r;
    this.g = g;
    this.b = b;
  }

  private static int r(int rgb) {
    return (rgb >> 16) & 0xFF;
  }

  private static int g(int rgb) {
    return (rgb >> 8) & 0xFF;
  }

  private static int b(int rgb) {
    return (rgb >> 0) & 0xFF;
  }

  int r() { return r; }
  int g() { return g; }
  int b() { return b; }

  boolean isEqualTo(RGBColor color, int threshold) {
    if (abs(r - color.r) > threshold) return false;
    if (abs(g - color.g) > threshold) return false;
    return abs(b - color.b) <= threshold;
  }

  @Override public String toString() {
    return concat(
        "color[",
        "r=", valueOf(r), ",",
        "g=", valueOf(g), ",",
        "b=", valueOf(b), "]"
        );
  }
}
