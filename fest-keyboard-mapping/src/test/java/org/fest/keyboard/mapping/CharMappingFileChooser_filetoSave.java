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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.finder.JFileChooserFinder.findFileChooser;
import static org.fest.util.Files.newTemporaryFile;
import static org.fest.util.Files.temporaryFolder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

import javax.swing.JButton;
import org.fest.swing.annotation.GUITest;
import org.fest.swing.edt.*;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JFileChooserFixture;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link CharMappingFileChooser#fileToSave()}</code>.
 *
 * @author Alex Ruiz
 */
@GUITest
public class CharMappingFileChooser_filetoSave extends RobotBasedTestCase {

  private MyWindow window;
  private FrameFixture frameFixture;
  private File selectedFile;

  @Override protected void onSetUp() {
    window = MyWindow.createNew();
    frameFixture = new FrameFixture(robot, window);
    frameFixture.show();
    selectedFile = newTemporaryFile();
  }

  @Override protected void onTearDown() {
    if (selectedFile != null) selectedFile.delete();
  }

  @Test
  public void should_return_selected_file() {
    frameFixture.button().click();
    JFileChooserFixture fileChooser = findFileChooser().using(robot);
    fileChooser.setCurrentDirectory(temporaryFolder());
    fileChooser.fileNameTextBox().enterText(selectedFile.getName());
    fileChooser.approve();
    assertThat(window.selectedFile().getName()).isEqualTo(selectedFile.getName());
  }

  @Test
  public void should_return_null_if_a_file_was_not_selected() {
    frameFixture.button().click();
    JFileChooserFixture fileChooser = findFileChooser().using(robot);
    fileChooser.cancel();
    assertThat(window.selectedFile()).isNull();
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private final AtomicReference<File> fileRef = new AtomicReference<File>();

    private MyWindow() {
      super(CharMappingFileChooser_filetoSave.class);
      JButton button = new JButton("Click Me");
      button.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          File file = new CharMappingFileChooser(MyWindow.this).fileToSave();
          fileRef.set(file);
        }
      });
      addComponents(button);
    }

    File selectedFile() { return fileRef.get(); }
  }
}
