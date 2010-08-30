/*
 * Created on Aug 23, 2010
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
package org.fest.swing.assertions;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.assertions.ComponentEnabledCondition.untilIsEnabled;
import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.query.ComponentEnabledQuery.isEnabled;
import static org.fest.swing.query.ComponentHasFocusQuery.hasFocus;
import static org.fest.swing.query.ComponentSizeQuery.sizeOf;
import static org.fest.swing.query.ComponentVisibleQuery.isVisible;
import static org.fest.swing.timing.Pause.pause;
import static org.fest.util.Strings.*;

import java.awt.Component;
import java.awt.Dimension;

import org.fest.assertions.Description;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiLazyLoadingDescription;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.format.ComponentFormatter;
import org.fest.swing.format.Formatting;
import org.fest.swing.timing.Timeout;

/**
 * Verifies the state of a <code>{@link Component}</code>.
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 * </p>
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
public class ComponentAssertions {

  private static final String ENABLED_PROPERTY = "enabled";
  private static final String SIZE_PROPERTY = "size";
  private static final String VISIBLE_PROPERTY = "visible";

  /**
   * Asserts that the size of the <code>{@link Component}</code> is equal to given one.
   * @param c the target component.
   * @param size the given size to match.
   * @throws AssertionError if the size of the {@code Window} is not equal to the given size.
   */
  @RunsInEDT
  public void requireSize(Component c, Dimension size) {
    assertThat(sizeOf(c)).as(propertyName(c, SIZE_PROPERTY)).isEqualTo(size);
  }

  /**
   * Asserts that the <code>{@link Component}</code> is visible.
   * @param c the target component.
   * @throws AssertionError if the {@code Component} is not visible.
   */
  @RunsInEDT
  public void requireVisible(Component c) {
    assertThat(isVisible(c)).as(visibleProperty(c)).isTrue();
  }

  /**
   * Asserts that the <code>{@link Component}</code> is not visible.
   * @param c the target component.
   * @throws AssertionError if the {@code Component} is visible.
   */
  @RunsInEDT
  public void requireNotVisible(Component c) {
    assertThat(isVisible(c)).as(visibleProperty(c)).isFalse();
  }

  @RunsInEDT
  private static Description visibleProperty(Component c) {
    return propertyName(c, VISIBLE_PROPERTY);
  }

  /**
   * Asserts that the <code>{@link Component}</code> has input focus.
   * @param c the target component.
   * @throws AssertionError if the {@code Component} does not have input focus.
   */
  @RunsInEDT
  public void requireFocused(Component c) {
    assertThat(hasFocus(c)).as(requiredFocusedErrorMessage(c)).isTrue();
  }

  private static Description requiredFocusedErrorMessage(final Component c) {
    return new GuiLazyLoadingDescription() {
      @Override protected String loadDescription() {
        return concat("Expected component ", format(c), " to have input focus");
      }
    };
  }

  /**
   * Asserts that the <code>{@link Component}</code> is enabled.
   * @param c the target component.
   * @throws AssertionError if the {@code Component} is disabled.
   */
  @RunsInEDT
  public void requireEnabled(Component c) {
    assertThat(isEnabled(c)).as(enabledProperty(c)).isTrue();
  }

  /**
   * Asserts that the <code>{@link Component}</code> is enabled.
   * @param c the target component.
   * @param timeout the time this fixture will wait for the component to be enabled.
   * @throws WaitTimedOutError if the {@code Component} is never enabled.
   */
  @RunsInEDT
  public void requireEnabled(Component c, Timeout timeout) {
    pause(untilIsEnabled(c), timeout);
  }

  /**
   * Asserts that the <code>{@link Component}</code> is disabled.
   * @param c the target component.
   * @throws AssertionError if the {@code Component} is enabled.
   */
  @RunsInEDT
  public void requireDisabled(Component c) {
    assertThat(isEnabled(c)).as(enabledProperty(c)).isFalse();
  }

  @RunsInEDT
  private static Description enabledProperty(Component c) {
    return propertyName(c, ENABLED_PROPERTY);
  }

  /**
   * Formats the name of a property of the given <code>{@link Component}</code> by concatenating the value obtained
   * from <code>{@link Formatting#format(Component)}</code> with the given property name.
   * @param c the given {@code Component}.
   * @param propertyName the name of the property.
   * @return the description of a property belonging to a {@code Component}.
   * @see ComponentFormatter
   * @see Formatting#format(Component)
   */
  @RunsInEDT
  public static Description propertyName(final Component c, final String propertyName) {
    return new GuiLazyLoadingDescription() {
      @Override protected String loadDescription() {
        return concat(format(c), " - property:", quote(propertyName));
      }
    };
  }
}
