/*
 * Created on May 27, 2010
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
package org.fest.javafx.util;

import static org.fest.util.Objects.HASH_CODE_PRIME;
import javafx.geometry.Point2D;

/**
 * Understands a point.
 *
 * @author Alex Ruiz
 */
public class Point {

  /** The X coordinate of this {@code Point}. */
  public final int x;

  /** The Y coordinate of this {@code Point}. */
  public final int y;

  /**
   * Creates a new </code>{@link Point}</code>.
   * @param x the X coordinate.
   * @param y the Y coordinate.
   */
  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  /**
   * Creates a new </code>{@link Point}</code>.
   * @param p the base point.
   */
  public Point(Point2D p) {
    this((int)p.$x, (int)p.$y);
  }

  /**
   * Translates this point, at location {@code (x,y)}, by {@code other.x} along the {@code x} axis and {@code other.y}
   * along the {@code y} axis so that it now represents the point {@code (x+other.x,y+other.y)}.
   * @param other the distance to move this point along the X and Y axes.
   * @return a new {@code Point} containing the new coordinates.
   */
  public Point translate(Point other) {
    return translate(other.x, other.y);
  }

  /**
   * Translates this point, at location {@code (x,y)}, by {@code dx} along the {@code x} axis and {@code dy} along the
   * {@code y} axis so that it now represents the point {@code (x+dx,y+dy)}.
   * @param dx the distance to move this point along the X axis.
   * @param dy the distance to move this point along the Y axis.
   * @return a new {@code Point} containing the new coordinates.
   */
  public Point translate(int dx, int dy) {
    return new Point(x + dx, y + dy);
  }

  @Override public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Point other = (Point) obj;
    return x == other.x && y == other.y;
  }

  @Override public int hashCode() {
    int prime = HASH_CODE_PRIME;
    int result = 1;
    result = prime * result + x;
    result = prime * result + y;
    return result;
  }

  @Override public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append(getClass().getSimpleName()).append("[x=").append(x).append(", y=").append(y).append("]");
    return builder.toString();
  }
}
