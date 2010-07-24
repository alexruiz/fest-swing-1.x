/*
 * Created on Jul 8, 2008
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

import java.awt.Component;

import org.fest.swing.core.Robot;

/**
 * Understands a validator of common objects used in component fixtures.
 *
 * @author Alex Ruiz 
 */
public final class ComponentFixtureValidator {

  /**
   * Verifies that the given <code>{@link Robot}</code> is not {@code null}.
   * @param robot the <code>Robot</code> to verify.
   * @return the given <code>Robot</code>.
   * @throws NullPointerException if <code>robot</code> is {@code null}.
   */
  public static Robot notNullRobot(Robot robot) {
    if (robot == null) throw new NullPointerException("Robot should not be null");
    return robot;
  }
  
  /**
   * Verifies that the given <code>{@link Component}</code> is not {@code null}.
   * @param <T> specifies the type of {@code Component} to return.
   * @param target the {@code Component} to verify.
   * @return the given target {@code Component}.
   * @throws NullPointerException if <code>target</code> is {@code null}.
   */
  public static <T extends Component> T notNullTarget(T target) {
    if (target == null) throw new NullPointerException("Target component should not be null");
    return target;
  }
  
  private ComponentFixtureValidator() {}
}
