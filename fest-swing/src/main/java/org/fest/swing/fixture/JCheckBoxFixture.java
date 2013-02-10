/*
 * Created on Jun 10, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import javax.annotation.Nonnull;
import javax.swing.JCheckBox;

import org.fest.swing.core.Robot;
import org.fest.swing.exception.ComponentLookupException;

/**
 * Supports functional testing of {@code JCheckBox}es.
 * 
 * @author Alex Ruiz
 */
public class JCheckBoxFixture extends AbstractButtonFixture<JCheckBoxFixture, JCheckBox> implements
TwoStateButtonFixture<JCheckBoxFixture> {
  /**
   * Creates a new {@link JCheckBoxFixture}.
   * 
   * @param robot performs simulation of user events on the given {@code JCheckBox}.
   * @param target the {@code JCheckBox} to be managed by this fixture.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws NullPointerException if {@code target} is {@code null}.
   */
  public JCheckBoxFixture(@Nonnull Robot robot, @Nonnull JCheckBox target) {
    super(JCheckBoxFixture.class, robot, target);
  }

  /**
   * Creates a new {@link JCheckBoxFixture}.
   * 
   * @param robot performs simulation of user events on a {@code JCheckBox}.
   * @param checkBoxName the name of the {@code JCheckBox} to find using the given {@code Robot}.
   * @throws NullPointerException if {@code robot} is {@code null}.
   * @throws ComponentLookupException if a matching {@code JCheckBox} could not be found.
   * @throws ComponentLookupException if more than one matching {@code JCheckBox} is found.
   */
  public JCheckBoxFixture(@Nonnull Robot robot, @Nonnull String checkBoxName) {
    super(JCheckBoxFixture.class, robot, checkBoxName, JCheckBox.class);
  }

  /**
   * Checks (or selects) this fixture's {@code JCheckBox} only it is not already checked.
   * 
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JCheckBox} is disabled.
   * @throws IllegalStateException if this fixture's {@code JCheckBox} is not showing on the screen.
   */
  public @Nonnull JCheckBoxFixture check() {
    driver().select(target());
    return this;
  }

  /**
   * Unchecks this fixture's {@code JCheckBox} only if it is checked.
   * 
   * @return this fixture.
   * @throws IllegalStateException if this fixture's {@code JCheckBox} is disabled.
   * @throws IllegalStateException if this fixture's {@code JCheckBox} is not showing on the screen.
   */
  public @Nonnull JCheckBoxFixture uncheck() {
    driver().unselect(target());
    return this;
  }

  /**
   * Verifies that this fixture's {@code JCheckBox} is selected.
   * 
   * @return this fixture.
   * @throws AssertionError if the {@code JCheckBox} managed by this fixture is not selected.
   */
  @Override
  public @Nonnull JCheckBoxFixture requireSelected() {
    driver().requireSelected(target());
    return this;
  }

  /**
   * Verifies that this fixture's {@code JCheckBox} is not selected.
   * 
   * @return this fixture.
   * @throws AssertionError if the {@code JCheckBox} managed by this fixture is selected.
   */
  @Override
  public @Nonnull JCheckBoxFixture requireNotSelected() {
    driver().requireNotSelected(target());
    return this;
  }
}
