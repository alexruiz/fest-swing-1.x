/*
 * Created on Feb 29, 2008
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
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.driver.DialogModalQuery.isModal;

import java.awt.Dialog;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;

/**
 * User input simulation, state verification and property value query on a <code>{@link Dialog}</code>.
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 * </p>
 *
 * @author Alex Ruiz
 */
public class DialogDriver extends WindowDriver {

  /**
   * Creates a new </code>{@link DialogDriver}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public DialogDriver(Robot robot) {
    super(robot);
  }

  /**
   * Asserts that the<code>{@link Dialog}</code> is modal.
   * @param dialog the target <code>Dialog</code>.
   * @throws AssertionError if this fixture's <code>Dialog</code> is not modal.
   */
  @RunsInEDT
  public void requireModal(Dialog dialog) {
    assertThat(isModal(dialog)).as(propertyName(dialog, "modal")).isTrue();
  }
}
