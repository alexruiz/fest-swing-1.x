/*
 * Created on Jun 7, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import java.awt.Point;

import javax.annotation.Nonnull;
import javax.swing.JFrame;

import org.fest.swing.core.Robot;
import org.fest.swing.driver.ComponentDriver;

/**
 * Implementation of {@link AbstractContainerFixture} for testing.
 *
 * @author Alex Ruiz
 */
public class ConcreteContainerFixture extends AbstractContainerFixture<ConcreteContainerFixture, JFrame, ComponentDriver> {
  public ConcreteContainerFixture(Robot robot, JFrame target) {
    super(ConcreteContainerFixture.class, robot, target);
  }

  @Override
  protected @Nonnull ComponentDriver createDriver(Robot robot) {
    return new ComponentDriver(robot);
  }

  public JPopupMenuFixture showPopupMenu() {
    return null;
  }

  public JPopupMenuFixture showPopupMenuAt(Point p) {
    if (p == null) {
      return null; // just to satisfy compiler warning
    }
    return null;
  }
}
