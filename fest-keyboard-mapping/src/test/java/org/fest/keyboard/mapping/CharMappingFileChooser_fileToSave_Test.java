/*
 * Created on May 1, 2010
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

import static javax.swing.SwingUtilities.invokeLater;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.finder.JFileChooserFinder.findFileChooser;
import static org.fest.swing.test.swing.TestWindow.createNewWindow;
import static org.fest.util.Files.newTemporaryFile;
import static org.fest.util.Files.temporaryFolder;

import java.awt.Dimension;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

import org.fest.swing.annotation.GUITest;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.JFileChooserFixture;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link CharMappingFileChooser#CharMappingFileChooser(javax.swing.JFileChooser, javax.swing.JFrame)}</code>.
 *
 * @author Alex Ruiz
 */
@GUITest
public class CharMappingFileChooser_fileToSave_Test extends RobotBasedTestCase {

  private CharMappingFileChooser fileChooser;
  private File fileToSelect;

  @Override protected void onSetUp() {
    fileToSelect = newTemporaryFile();
    final TestWindow parent = createNewWindow(getClass());
    fileChooser = execute(new GuiQuery<CharMappingFileChooser>() {
      @Override protected CharMappingFileChooser executeInEDT() {
        return new CharMappingFileChooser(parent);
      }
    });
    robot.showWindow(parent, new Dimension(300, 100));
  }

  @Override protected void onTearDown() {
    if (fileToSelect != null) fileToSelect.delete();
  }

  @Test
  public void should_return_selected_file() {
    final AtomicReference<File> fileReference = new AtomicReference<File>();
    invokeLater(new Runnable() {
      @Override public void run() {
        fileReference.set(fileChooser.fileToSave());
      }
    });
    selectFile();
    File selectedFile = fileReference.get();
    assertThat(selectedFile.getName()).isEqualTo(fileToSelect.getName());
  }

  private void selectFile() {
    JFileChooserFixture fc = findFileChooser().using(robot);
    fc.setCurrentDirectory(temporaryFolder());
    fc.fileNameTextBox().enterText(fileToSelect.getName());
    fc.approve();
  }

  @Test
  public void should_return_null_if_user_did_not_select_a_file() {
    final AtomicReference<File> fileReference = new AtomicReference<File>();
    invokeLater(new Runnable() {
      @Override public void run() {
        fileReference.set(fileChooser.fileToSave());
      }
    });
    findFileChooser().using(robot).cancel();
    File selectedFile = fileReference.get();
    assertThat(selectedFile).isNull();
  }
}
