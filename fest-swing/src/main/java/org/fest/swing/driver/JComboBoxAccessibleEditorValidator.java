/*
 * Created on Aug 8, 2008
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

import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.format.Formatting.format;
import static org.fest.util.Strings.concat;

import java.awt.Component;

import javax.swing.JComboBox;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands an action that validates that the editor of a <code>{@link JComboBox}</code> is accessible or not. To be
 * accessible, a <code>JComboBox</code> needs to be enabled and editable.
 * <p>
 * <b>Note:</b> Methods in this class are <b>not</b> executed in the event dispatch thread (EDT.) Clients are
 * responsible for invoking them in the EDT.
 * </p>
 * @see JComboBox#isEditable()
 * @see Component#isEnabled()
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class JComboBoxAccessibleEditorValidator {

  @RunsInCurrentThread
  static void validateEditorIsAccessible(JComboBox comboBox) {
    validateIsEnabledAndShowing(comboBox);
    if (!comboBox.isEditable()) 
      throw new IllegalStateException(concat("Expecting component ", format(comboBox), " to be editable"));
  }

  private JComboBoxAccessibleEditorValidator() {}
}