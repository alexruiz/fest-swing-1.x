/*
 * Created on Apr 18, 2010
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
package org.fest.keyboard.mapping;

import java.awt.event.*;
import java.io.File;

import javax.swing.JFrame;

import org.fest.util.VisibleForTesting;

import static javax.swing.JOptionPane.*;

import static org.fest.keyboard.mapping.CharMappingFileCreationProgressDialog.createProgressDialog;
import static org.fest.util.Strings.*;

/**
 * Understands a UI action that prompts the user to save a group of <code>{@link CharMapping}</code>s as a text file.
 *
 * @author Alex Ruiz
 */
class SaveMappingFileActionListener implements ActionListener {

  private final JFrame parent;
  private final FileChooser fileChooser;
  private final CharMappingFileFactory fileFactory;
  private final CharMappingTableModel mappings;
  private final CharMappingFileCreationProgressPanel progressPanel;
  
  SaveMappingFileActionListener(JFrame parent, CharMappingTableModel mappings) {
    this(parent, new CharMappingFileChooser(parent), new CharMappingFileFactory(), mappings);
  }

  @VisibleForTesting
  SaveMappingFileActionListener(JFrame parent, FileChooser fileChooser, CharMappingFileFactory fileFactory,
      CharMappingTableModel mappings) {
    this.parent = parent;
    this.fileChooser = fileChooser;
    this.mappings = mappings;
    this.fileFactory = fileFactory;
    progressPanel = new CharMappingFileCreationProgressPanel();
    createProgressDialog(parent, progressPanel);
  }

  public void actionPerformed(ActionEvent event) {
    File toSave = fileChooser.fileToSave();
    if (toSave == null) return;
    try {
      CharMappingFileFactoryWorker worker = new CharMappingFileFactoryWorker(toSave, mappings, fileFactory, progressPanel);
      worker.execute();
      showSuccessMessage(toSave);
    } catch (Exception e) {
      showFailureMessage(e.getMessage());
    }
  }

  private void showSuccessMessage(File file) {
    String message = concat("File ", quote(file.getName()), " successfully saved.");
    showMessageDialog(parent, message, "Success", INFORMATION_MESSAGE);
  }

  private void showFailureMessage(String cause) {
    String message = concat("Unable to save file. Reason: [", cause, "]");
    showMessageDialog(parent, message, "Error", ERROR_MESSAGE);
  }
}
