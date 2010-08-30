/*
 * Created on Aug 24, 2010
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
package org.fest.swing.gestures;

import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.AbstractButton;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;

/**
 * Simulates user input on an <code>{@link AbstractButton}</code>.
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 * </p>
 *
 * @author Alex Ruiz
 *
 * @since 1.3
 */
public class AbstractButtonGestures extends JComponentGestures {

  /**
   * Creates a new </code>{@link AbstractButtonGestures}</code>.
   * @param robot the robot to use to simulate user input.
   */
  public AbstractButtonGestures(Robot robot) {
    super(robot);
  }

  /**
   * Selects the given button only it is not already selected.
   * @param button the target button.
   * @throws IllegalStateException if the button is disabled.
   * @throws IllegalStateException if the button is not showing on the screen.
   */
  @RunsInEDT
  public void select(AbstractButton button) {
    if (validateAndFindIsSelected(button)) return;
    robot.click(button);
  }

  /**
   * Unselects the given button only if it is selected.
   * @param button the target button.
   * @throws IllegalStateException if the button is disabled.
   * @throws IllegalStateException if the button is not showing on the screen.
   */
  @RunsInEDT
  public void unselect(AbstractButton button) {
    if (!validateAndFindIsSelected(button)) return;
    robot.click(button);
  }

  @RunsInEDT
  private static boolean validateAndFindIsSelected(final AbstractButton button) {
    return execute(new GuiQuery<Boolean>() {
      @Override protected Boolean executeInEDT() {
        validateIsEnabledAndShowing(button);
        return button.isSelected();
      }
    });
  }

}
