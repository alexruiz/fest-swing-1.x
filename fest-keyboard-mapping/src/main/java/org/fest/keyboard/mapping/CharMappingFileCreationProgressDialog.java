/*
 * Created on May 2, 2010
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
package org.fest.keyboard.mapping;

import javax.swing.*;

/**
 * Understands a dialog that displays the progress made when saving a mapping file.
 * 
 * @author Alex Ruiz
 */
class CharMappingFileCreationProgressDialog extends JDialog {

  private static final long serialVersionUID = 1L;

  static void createProgressDialog(JFrame owner, CharMappingFileCreationProgressPanel contents) {
    new CharMappingFileCreationProgressDialog(owner, contents);
  }

  private CharMappingFileCreationProgressDialog(JFrame owner, CharMappingFileCreationProgressPanel contents) {
    super(owner, "Saving...", true);
    setContentPane(contents);
    setLocationRelativeTo(owner);
    setResizable(false);
    pack();
  }
}
