/*
 * Created on Jul 18, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Component;

import org.fest.swing.core.*;
import org.fest.swing.driver.ComponentDriver;
import org.fest.swing.timing.Timeout;

/**
 * A generic component fixture providing basic keyboard and mouse input operations. Useful as a base class for
 * specialized fixtures providing higher level input operations for custom components.
 * <p>
 * Example:
 *
 * <pre>
 * public class MyWidget extends JComponent {
 *  ...
 *  public void paintComponent(Graphics g) {
 *     ...
 *  }
 *  ...
 * }
 *
 * public class MyWidgetFixture extends GenericComponentFixture&lt;MyWidget&gt; {
 *   public MyWidgetFixture(Robot robot, MyWidget target) {
 *     super(robot, target);
 *   }
 *
 *   public void clickAndDrag(Point start, Point end) {
 *     robot.pressMouse(target, start);
 *     robot.moveMouse(target, end);
 *     robot.releaseAllMouseButtons();
 *   }
 * }
 *
 * </pre>
 *
 * </p>
 * @param <T> the type of <code>Component</code> that this fixture can manage.
 *
 * @author <a href="mailto:simeon.fitch@mseedsoft.com">Simeon H.K. Fitch</a>
 */
public abstract class GenericComponentFixture<T extends Component> extends ComponentFixture<T> implements
    CommonComponentFixture {

  /** Delegate for constructing and passing input operations to Robot. */
  private ComponentDriver driver;

  /**
   * Creates a new <code>{@link GenericComponentFixture}</code>.
   * @param robot performs simulation of user events on the given target component.
   * @param target the target <code>Component</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public GenericComponentFixture(Robot robot, T target) {
    this(robot, new ComponentDriver(robot), target);
  }

  /**
   * Creates a new <code>{@link GenericComponentFixture}</code> using a provided driver.
   * @param robot performs simulation of user events on the given target component.
   * @param driver provided driver to handle input requests.
   * @param target the target <code>Component</code> to be managed by this fixture.
   * @throws NullPointerException if <code>robot</code> is <code>null</code>.
   * @throws NullPointerException if <code>driver</code> is <code>null</code>.
   * @throws NullPointerException if <code>target</code> is <code>null</code>.
   */
  public GenericComponentFixture(Robot robot, ComponentDriver driver, T target) {
    super(robot, target);
    driver(driver);
  }

  /**
   * Sets the <code>{@link ComponentDriver}</code> to be used by this fixture.
   * @param newDriver the new <code>ComponentDriver</code>.
   * @throws NullPointerException if the given driver is <code>null</code>.
   */
  protected final void driver(ComponentDriver newDriver) {
    validateNotNull(newDriver);
    this.driver = newDriver;
  }

  /**
   * Returns the <code>{@link ComponentDriver}</code> used by this fixture.
   * @return the driver used by this fixture.
   */
  protected final ComponentDriver driver() {
    return driver;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> click() {
    driver.click(target);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> click(MouseButton button) {
    driver.click(target, button);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> click(MouseClickInfo mouseClickInfo) {
    driver.click(target, mouseClickInfo);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> doubleClick() {
    driver.doubleClick(target);
    return this;
  }


  /** {@inheritDoc} */
  public GenericComponentFixture<T> rightClick() {
    driver.rightClick(target);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> focus() {
    driver.focus(target);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> pressAndReleaseKey(KeyPressInfo keyPressInfo) {
    driver.pressAndReleaseKey(target, keyPressInfo);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> pressAndReleaseKeys(int... keyCodes) {
    driver.pressAndReleaseKeys(target, keyCodes);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> pressKey(int keyCode) {
    driver.pressKey(target, keyCode);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> releaseKey(int keyCode) {
    driver.releaseKey(target, keyCode);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> requireEnabled() {
    driver.requireEnabled(target);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> requireEnabled(Timeout timeout) {
    driver.requireEnabled(target, timeout);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> requireDisabled() {
    driver.requireDisabled(target);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> requireVisible() {
    driver.requireVisible(target);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> requireNotVisible() {
    driver.requireNotVisible(target);
    return this;
  }

  /** {@inheritDoc} */
  public GenericComponentFixture<T> requireFocused() {
    driver.requireFocused(target);
    return this;
  }
}
