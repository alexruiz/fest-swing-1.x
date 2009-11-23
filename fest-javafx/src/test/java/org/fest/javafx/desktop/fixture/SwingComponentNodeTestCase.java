/*
 * Created on Feb 20, 2009
 * 
 * This program is free software; you can redistribute it and/or modify it under the terms of the 
 * GNU General Public License as published by the Free Software Foundation; either version 2 of 
 * the License. You may obtain a copy of the License at
 * 
 * http://www.gnu.org/licenses/gpl-2.0.txt
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; 
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See 
 * the GNU General Public License for more details.
 * 
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.fixture;

import org.fest.javafx.desktop.core.JavaFxRobot;
import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.core.Robot;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;

/**
 * Understands test methods for FEST-JavaFX fixtures that handle nodes that contain Swing components.
 *
 * @author Alex Ruiz
 */
public abstract class SwingComponentNodeTestCase extends NodeFixtureTestCase {

  private Robot swingRobot;

  final void onSetUp() {
    swingRobot = createMock(Robot.class);
    final JavaFxRobot robot = robot();
    new EasyMockTemplate(robot) {
      protected void expectations() {
        expect(robot.swingRobot()).andReturn(swingRobot).anyTimes();
      }

      protected void codeToTest() {
        setUpFixture();
      }

    }.run();    
  }

  abstract void setUpFixture();
  
  final Robot swingRobot() { return swingRobot; }
}
