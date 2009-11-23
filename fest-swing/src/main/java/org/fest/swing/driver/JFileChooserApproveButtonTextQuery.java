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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.util.Strings.isEmpty;

import javax.swing.JFileChooser;
import javax.swing.plaf.FileChooserUI;

import org.fest.swing.edt.GuiQuery;

/**
 * Understands an action, executed in the event dispatch thread, that returns the text used in the "approve button" of a
 * <code>{@link JFileChooser}</code>.
 * @see JFileChooser#getApproveButtonText()
 * @see FileChooserUI#getApproveButtonText(JFileChooser)
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class JFileChooserApproveButtonTextQuery {

  static String approveButtonTextFrom(final JFileChooser fileChooser) {
    return execute(new GuiQuery<String>() {
      protected String executeInEDT() {
        String text = fileChooser.getApproveButtonText();
        if (!isEmpty(text)) return text;
        return fileChooser.getUI().getApproveButtonText(fileChooser);
      }
    });
  }

  private JFileChooserApproveButtonTextQuery() {}
}