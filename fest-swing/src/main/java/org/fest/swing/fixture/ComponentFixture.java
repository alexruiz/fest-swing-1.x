/*
 * Created on Oct 20, 2006
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
 * Copyright @2006-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.ComponentDriver.propertyName;
import static org.fest.swing.fixture.ComponentFixtureValidator.notNullRobot;
import static org.fest.swing.fixture.ComponentFixtureValidator.notNullTarget;
import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.query.ComponentBackgroundQuery.backgroundOf;
import static org.fest.swing.query.ComponentFontQuery.fontOf;
import static org.fest.swing.query.ComponentForegroundQuery.foregroundOf;

import java.awt.Component;

import org.fest.swing.core.Robot;
import org.fest.swing.core.Settings;
import org.fest.swing.driver.ComponentDriver;
import org.fest.swing.edt.GuiActionRunner;
import org.fest.swing.exception.ComponentLookupException;

/**
 * Understands simulation of user events on a <code>{@link Component}</code> and verification of the state of such
 * <code>{@link Component}</code>.
 * @param <T> the type of <code>Component</code> that this fixture can manage.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class ComponentFixture<T extends Component> {

  /** Name of the property "font". */
  protected static final String FONT_PROPERTY = "font";

  /** Name of the property "background". */
  protected static final String BACKGROUND_PROPERTY = "background";

  /** Name of the property "foreground". */
  protected static final String FOREGROUND_PROPERTY = "foreground";

  /** Performs simulation of user events on <code>{@link #target}</code> */
  public final Robot robot;

  /**
   * This fixture's <code>{@link Component}</code>.
   * <p>
   * <strong>Note:</strong> Access to this GUI component <em>must</em> be executed in the event dispatch thread. To do
   * so, please execute a <code>{@link org.fest.swing.edt.GuiQuery GuiQuery}</code> or
   * <code>{@link org.fest.swing.edt.GuiTask GuiTask}</code> (depending on what you need to do,) inside a
   * <code>{@link GuiActionRunner}</code>. To learn more about Swing threading, please read the
   * <a href="http://java.sun.com/javase/6/docs/api/javax/swing/package-summary.html#threading" target="_blank">Swing Threading Policy</a>.
   * </p>
   */
  public final T target;

  /**
   * Creates a new <code>{@link ComponentFixture}</code>.
   * @param robot performs simulation of user events on a <code>Component</code>.
   * @param type the type of the <code>Component</code> to find using the given <code>RobotFixture</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>type</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  public ComponentFixture(Robot robot, Class<? extends T> type) {
    this(robot, findTarget(robot, type));
  }

  private static <C extends Component> C findTarget(Robot robot, Class<? extends C> type) {
    validate(robot, type);
    return robot.finder().findByType(type, requireShowing(robot));
  }

  static void validateNotNull(ComponentDriver driver) {
    if (driver == null) throw new NullPointerException("The driver should not be null");
  }

  /**
   * Creates a new <code>{@link ComponentFixture}</code>.
   * @param robot performs simulation of user events on a <code>Component</code>.
   * @param name the name of the <code>Component</code> to find using the given <code>RobotFixture</code>.
   * @param type the type of the <code>Component</code> to find using the given <code>RobotFixture</code>.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>type</code> is <code>null</code>.
   * @throws ComponentLookupException if a matching component could not be found.
   * @throws ComponentLookupException if more than one matching component is found.
   */
  public ComponentFixture(Robot robot, String name, Class<? extends T> type) {
    this(robot, findTarget(robot, name, type));
  }

  private static <C extends Component> C findTarget(Robot robot, String name, Class<? extends C> type) {
    validate(robot, type);
    return robot.finder().findByName(name, type, requireShowing(robot));
  }

  private static void validate(Robot robot, Class<?> type) {
    notNullRobot(robot);
    if (type == null) throw new NullPointerException("The type of component to look for should not be null");
  }

  /**
   * Returns whether showing components are the only ones participating in a component lookup. The returned value is
   * obtained from the <code>{@link Settings#componentLookupScope() component lookup scope}</code> stored in this
   * fixture's <code>{@link Robot}</code>.
   * @return <code>true</code> if only showing components can participate in a component lookup, <code>false</code>
   * otherwise.
   */
  protected boolean requireShowing() {
    return requireShowing(robot);
  }

  private static boolean requireShowing(Robot robot) {
    return robot.settings().componentLookupScope().requireShowing();
  }

  /**
   * Creates a new <code>{@link ComponentFixture}</code>.
   * @param robot performs simulation of user events on the given <code>Component</code>.
   * @param target the <code>Component</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public ComponentFixture(Robot robot, T target) {
    this.robot = notNullRobot(robot);
    this.target = notNullTarget(target);
  }

  /**
   * Returns a fixture that verifies the font of this fixture's <code>{@link Component}</code>.
   * @return a fixture that verifies the font of this fixture's <code>Component</code>.
   */
  public final FontFixture font() {
    return new FontFixture(fontOf(target), propertyName(target, FONT_PROPERTY));
  }

  /**
   * Returns a fixture that verifies the background color of this fixture's <code>{@link Component}</code>.
   * @return a fixture that verifies the background color of this fixture's <code>Component</code>.
   */
  public final ColorFixture background() {
    return new ColorFixture(backgroundOf(target), propertyName(target, BACKGROUND_PROPERTY));
  }

  /**
   * Returns a fixture that verifies the foreground color of this fixture's <code>{@link Component}</code>.
   * @return a fixture that verifies the foreground color of this fixture's <code>Component</code>.
   */
  public final ColorFixture foreground() {
    return new ColorFixture(foregroundOf(target), propertyName(target, FOREGROUND_PROPERTY));
  }

  /**
   * Returns this fixture's <code>{@link Component}</code> casted to the given sub-type.
   * @param <C> enforces that the given type is a sub-type of the managed <code>Component</code>.
   * @param type the type that the managed <code>Component</code> will be casted to.
   * @return this fixture's <code>Component</code> casted to the given sub-type.
   * @throws AssertionError if this fixture's <code>Component</code> is not an instance of the given type.
   */
  public final <C extends T> C targetCastedTo(Class<C> type) {
    assertThat(target).as(format(target)).isInstanceOf(type);
    return type.cast(target);
  }

  /**
   * Returns the GUI component in this fixture (same as <code>{@link #target}</code>.)
   * <p>
   * <strong>Note:</strong> Access to the GUI component returned by this method <em>must</em> be executed in the event
   * dispatch thread. To do so, please execute a <code>{@link org.fest.swing.edt.GuiQuery GuiQuery}</code> or
   * <code>{@link org.fest.swing.edt.GuiTask GuiTask}</code> (depending on what you need to do,) inside a
   * <code>{@link GuiActionRunner}</code>. To learn more about Swing threading, please read the
   * <a href="http://java.sun.com/javase/6/docs/api/javax/swing/package-summary.html#threading" target="_blank">Swing Threading Policy</a>.
   * </p>
   * @return the GUI component in this fixture.
   */
  public final T component() {
    return target;
  }
}
