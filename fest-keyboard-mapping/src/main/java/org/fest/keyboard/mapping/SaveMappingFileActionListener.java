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

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;
import static org.fest.keyboard.mapping.CharMappingFileCreationProgressWindow.createProgressWindow;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;

import org.fest.util.VisibleForTesting;

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
    createProgressWindow(parent, progressPanel);
  }

  public void actionPerformed(ActionEvent event) {
    File toSave = fileChooser.fileToSave();
    if (toSave == null) return;
    try {
      CharMappingFileFactoryWorker worker = new CharMappingFileFactoryWorker(toSave, mappings, fileFactory, progressPanel);
      worker.execute();
      worker.get();
      showSuccessMessage(toSave);
    } catch (Exception e) {
      showFailureMessage(realCauseOf(e).getMessage());
    }
  }

  private Throwable realCauseOf(Exception e) {
    if (!(e instanceof ExecutionException)) return e;
    return e.getCause();
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
