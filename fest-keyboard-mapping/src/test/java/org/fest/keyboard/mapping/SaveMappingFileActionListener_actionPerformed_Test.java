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

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.finder.JOptionPaneFinder.findOptionPane;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.annotation.*;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JOptionPaneFixture;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link SaveMappingFileActionListener#actionPerformed(java.awt.event.ActionEvent)}</code>.
 *
 * @author Alex Ruiz
 */
@GUITest
public class SaveMappingFileActionListener_actionPerformed_Test extends RobotBasedTestCase {

  private FileChooser fileChooser;
  private CharMappingFileFactory fileFactory;
  private CharMappingTableModel mappings;
  private FrameFixture frameFixture;

  @Override protected void onSetUp() {
    fileChooser = createMock(FileChooser.class);
    fileFactory = createMock(CharMappingFileFactory.class);
    mappings = createMock(CharMappingTableModel.class);
    MyWindow window = MyWindow.createNew();
    window.button.addActionListener(new SaveMappingFileActionListener(window, fileChooser, fileFactory, mappings));
    frameFixture = new FrameFixture(robot, window);
    frameFixture.show();
  }

  @Test
  public void should_save_mappings_as_file() {
    new EasyMockTemplate(fileChooser, fileFactory) {
      @Override protected void expectations() throws Exception {
        File file = new File("New File.txt");
        expect(fileChooser.fileToSave()).andReturn(file);
        fileFactory.createMappingFile(file, mappings);
        expectLastCall();
      }

      @Override protected void codeToTest() {
        frameFixture.button().click();
        JOptionPaneFixture optionPane = findOptionPane().using(robot);
        optionPane.requireInformationMessage()
                  .requireMessage("File 'New File.txt' successfully saved.");
      }
    }.run();
  }

  @Test
  public void should_not_save_mappings_if_file_is_null() {
    new EasyMockTemplate(fileChooser, fileFactory) {
      @Override protected void expectations() throws Exception {
        expect(fileChooser.fileToSave()).andReturn(null);
      }

      @Override protected void codeToTest() {
        frameFixture.button().click();
        robot.requireNoJOptionPaneIsShowing();
      }
    }.run();
  }

  @Test
  public void should_show_error_message_in_case_of_failure() {
    new EasyMockTemplate(fileChooser, fileFactory) {
      @Override protected void expectations() throws Exception {
        File file = new File("New File.txt");
        expect(fileChooser.fileToSave()).andReturn(file);
        fileFactory.createMappingFile(file, mappings);
        expectLastCall().andThrow(new IOException("Cannot save mock file"));
      }

      @Override protected void codeToTest() {
        frameFixture.button().click();
        JOptionPaneFixture optionPane = findOptionPane().using(robot);
        optionPane.requireErrorMessage()
                  .requireMessage("Unable to save file. Reason: [Cannot save mock file]");
      }
    }.run();
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    @RunsInEDT
    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        @Override protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    private final JButton button = new JButton("Click Me");

    @RunsInCurrentThread
    private MyWindow() {
      super(SaveMappingFileActionListener.class);
      addComponents(button);
    }
  }
}
