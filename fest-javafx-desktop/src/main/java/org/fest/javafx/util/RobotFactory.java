/*
 * Created on May 27, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.javafx.util;

import java.awt.AWTException;
import java.awt.Robot;

/**
 * Understands creation of AWT <code>{@link Robot}</code>s.
 *
 * @author Alex Ruiz
 */
public class RobotFactory {

  /**
   * Constructs a AWT <code>{@link Robot}</code> object in the coordinate system of the primary screen.
   * @return the created {@code Robot}.
   * @throws AWTException if the platform configuration does not allow low-level input control. This exception is always
   * thrown when {@code GraphicsEnvironment.isHeadless()} returns <code>true</code>.
   * @throws SecurityException if {@code createRobot} permission is not granted.
   */
  public Robot createAwtRobot() throws AWTException {
    return new Robot();
  }
}
