/*
 * Created on May 2, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.util.Colors.colorFromHexString;

import java.awt.Color;

import org.fest.assertions.*;

/**
 * Understands state verification of <code>{@link Color}</code>s.
 *
 * @author Alex Ruiz
 */
public class ColorFixture {

  private final Color target;
  private final Description description;

  /**
   * Creates a new </code>{@link ColorFixture}</code>.
   * @param target the color to manage.
   * @throws NullPointerException if <code>target</code> is {@code null}.
   */
  public ColorFixture(Color target) {
    this(target, (Description)null);
  }

  /**
   * Creates a new </code>{@link ColorFixture}</code>.
   * @param target the color to manage.
   * @param description this fixture's description.
   * @throws NullPointerException if <code>target</code> is {@code null}.
   */
  public ColorFixture(Color target, String description) {
    this(target, new BasicDescription(description));
  }

  /**
   * Creates a new </code>{@link ColorFixture}</code>.
   * @param target the color to manage.
   * @param description this fixture's description.
   * @throws NullPointerException if <code>target</code> is {@code null}.
   */
  public ColorFixture(Color target, Description description) {
    if (target == null) throw new NullPointerException("The given color should not be null");
    this.target = target;
    this.description = description;
  }

  /**
   * Verifies that this fixture's <code>Color</code> is equal to the given color represented by the given hexadecimal
   * value (e.g. "82A9FF".)
   * @param hexValue the value representing the color to compare to.
   * @return this fixture.
   * @throws NullPointerException if the hexadecimal code is {@code null}.
   * @throws IllegalArgumentException if the hexadecimal code is empty.
   * @throws NumberFormatException if the hexadecimal code is empty.
   * @throws AssertionError if this fixture's <code>Color</code> is not equal to the given one.
   */
  public ColorFixture requireEqualTo(String hexValue) {
    return requireEqualTo(colorFromHexString(hexValue));
  }

  /**
   * Verifies that this fixture's <code>Color</code> is equal to the given one.
   * @param color the given <code>Color</code> to compare to.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>Color</code> is not equal to the given one.
   */
  public ColorFixture requireEqualTo(Color color) {
    assertThat(target).as(description).isEqualTo(color);
    return this;
  }

  /**
   * Verifies that this fixture's <code>Color</code> is not equal to the given color represented by the given
   * hexadecimal value (e.g. "82A9FF".)
   * @param hexValue the value representing the color to compare to.
   * @return this fixture.
   * @throws NullPointerException if the hexadecimal code is {@code null}.
   * @throws IllegalArgumentException if the hexadecimal code is empty.
   * @throws NumberFormatException if the hexadecimal code is empty.
   * @throws AssertionError if this fixture's <code>Color</code> is equal to the given one.
   */
  public ColorFixture requireNotEqualTo(String hexValue) {
    return requireNotEqualTo(colorFromHexString(hexValue));
  }

  /**
   * Verifies that this fixture's <code>Color</code> is not equal to the given one.
   * @param color the given <code>Color</code> to compare to.
   * @return this fixture.
   * @throws AssertionError if this fixture's <code>Color</code> is equal to the given one.
   */
  public ColorFixture requireNotEqualTo(Color color) {
    assertThat(target).as(description).isNotEqualTo(color);
    return this;
  }

  /**
   * Returns this fixture's color.
   * @return this fixture's color.
   */
  public Color target() { return target; }

  /**
   * Returns this fixture's description.
   * @return this fixture's description.
   */
  public final String description() { return description != null ? description.value() : null; }
}
