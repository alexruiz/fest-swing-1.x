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

import static javax.swing.JFileChooser.*;
import static org.fest.swing.driver.ComponentStateValidator.validateIsEnabledAndShowing;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.format.Formatting.format;
import static org.fest.util.Strings.concat;

import java.io.File;

import javax.swing.JFileChooser;

import org.fest.swing.annotation.*;
import org.fest.swing.edt.GuiTask;

/**
 * Understands a task that selects a file in a <code>{@link JFileChooser}</code>. This task is executed in the event
 * dispatch thread.
 *
 * @author Alex Ruiz
 */
final class JFileChooserSelectFileTask {

  @RunsInEDT
  static void validateAndSelectFile(final JFileChooser fileChooser, final File file) {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        validateIsEnabledAndShowing(fileChooser);
        validateFileToChoose(fileChooser, file);
        fileChooser.setSelectedFile(file);
      }
    });
  }

  @RunsInEDT
  static void validateAndSelectFiles(final JFileChooser fileChooser, final File[] files) {
    execute(new GuiTask() {
      @Override
      protected void executeInEDT() {
        validateIsEnabledAndShowing(fileChooser);
        if (files.length > 1 && !fileChooser.isMultiSelectionEnabled())
          throw new IllegalStateException(
              concat("Expecting file chooser ", format(fileChooser), " to handle multiple selection"));
        for (File file : files) validateFileToChoose(fileChooser, file);
        fileChooser.setSelectedFiles(files);
      }
    });
  }

  @RunsInCurrentThread
  private static void validateFileToChoose(JFileChooser fileChooser, File file) {
    int mode = fileChooser.getFileSelectionMode();
    boolean isFolder = file.isDirectory();
    if (mode == FILES_ONLY && isFolder)
      throw cannotSelectFile(file, "the file chooser cannot open directories");
    if (mode == DIRECTORIES_ONLY && !isFolder)
      throw cannotSelectFile(file, "the file chooser can only open directories");
  }

  private static IllegalArgumentException cannotSelectFile(File file, String reason) {
    return new IllegalArgumentException(concat("Unable to select file ", file, ": ", reason));
  }

  private JFileChooserSelectFileTask() {}
}