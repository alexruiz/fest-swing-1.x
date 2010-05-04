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
import static org.fest.util.Strings.concat;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.swing.JButton;

import org.fest.mocks.EasyMockTemplate;
import org.fest.reflect.exception.ReflectionError;
import org.fest.swing.annotation.GUITest;
import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
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

  private CharMappingTableModel mappings;
  private FileChooser fileChooser;
  private CharMappingFileFactory fileFactory;
  private FrameFixture frameFixture;

  @Override protected void onSetUp() {
    mappings = createMock(CharMappingTableModel.class);
    fileChooser = createMock(FileChooser.class);
    fileFactory = createMock(CharMappingFileFactoryStub.class, createMappingFileMethod());
    final MyWindow window = MyWindow.createNew();
    ActionListener actionListener = execute(new GuiQuery<ActionListener>() {
      @Override protected ActionListener executeInEDT() {
        return new SaveMappingFileActionListener(window, fileChooser, fileFactory, mappings);
      }
    });
    window.button.addActionListener(actionListener);
    frameFixture = new FrameFixture(robot, window);
    frameFixture.show();
  }

  private Method createMappingFileMethod() {
    try {
      Class<?> targetType = CharMappingFileFactoryStub.class;
      return targetType.getDeclaredMethod("createMappingFile", File.class, CharMappingTableModel.class);
    } catch (Exception e) {
      String className = CharMappingFileFactory.class.getName();
      String msg = concat("Unable to find method 'createMappingFile(File, CharMappingTableModel)' in class ", className);
      throw new ReflectionError(msg, e);
    }
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

  /*
   * org.fest.mocks.UnexpectedError: java.lang.AssertionError: [javax.swing.JOptionPane[message='Unable to save file. Reason: [java.io.IOException: Cannot save mock file]', messageType=ERROR_MESSAGE, optionType=DEFAULT_OPTION, enabled=true, visible=true, showing=true] - property:'message'] actual value:<'Unable to save file. Reason: [java.io.IOException: Cannot save mock file]'> is not equal to or does not match pattern:<'Unable to save file. Reason: [Cannot save mock file]'>
  at org.fest.mocks.EasyMockTemplate.run(EasyMockTemplate.java:121)
  at org.fest.keyboard.mapping.SaveMappingFileActionListener_actionPerformed_Test.should_show_error_message_in_case_of_failure(SaveMappingFileActionListener_actionPerformed_Test.java:132)
  at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
  at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:39)
  at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:25)
  at java.lang.reflect.Method.invoke(Method.java:589)
  at org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:44)
  at org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)
  at org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:41)
  at org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:20)
  at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:28)
  at org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:31)
  at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:73)
  at org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:46)
  at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:180)
  at org.junit.runners.ParentRunner.access$000(ParentRunner.java:41)
  at org.junit.runners.ParentRunner$1.evaluate(ParentRunner.java:173)
  at org.junit.internal.runners.statements.RunBefores.evaluate(RunBefores.java:28)
  at org.junit.internal.runners.statements.RunAfters.evaluate(RunAfters.java:31)
  at org.junit.runners.ParentRunner.run(ParentRunner.java:220)
  at org.eclipse.jdt.internal.junit4.runner.JUnit4TestReference.run(JUnit4TestReference.java:46)
  at org.eclipse.jdt.internal.junit.runner.TestExecution.run(TestExecution.java:38)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:467)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.runTests(RemoteTestRunner.java:683)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.run(RemoteTestRunner.java:390)
  at org.eclipse.jdt.internal.junit.runner.RemoteTestRunner.main(RemoteTestRunner.java:197)
Caused by: java.lang.AssertionError: [javax.swing.JOptionPane[message='Unable to save file. Reason: [java.io.IOException: Cannot save mock file]', messageType=ERROR_MESSAGE, optionType=DEFAULT_OPTION, enabled=true, visible=true, showing=true] - property:'message'] actual value:<'Unable to save file. Reason: [java.io.IOException: Cannot save mock file]'> is not equal to or does not match pattern:<'Unable to save file. Reason: [Cannot save mock file]'>
  at org.fest.assertions.Fail.failure(Fail.java:206)
  at org.fest.assertions.Assert.failure(Assert.java:141)
  at org.fest.swing.driver.TextAssert.isEqualOrMatches(TextAssert.java:59)
  at org.fest.swing.driver.JOptionPaneDriver.requireMessage(JOptionPaneDriver.java:124)
  at org.fest.swing.driver.JOptionPaneDriver.requireMessage(JOptionPaneDriver.java:116)
  at org.fest.swing.fixture.JOptionPaneFixture.requireMessage(JOptionPaneFixture.java:355)
  at org.fest.keyboard.mapping.SaveMappingFileActionListener_actionPerformed_Test$4.codeToTest(SaveMappingFileActionListener_actionPerformed_Test.java:130)
  at org.fest.mocks.EasyMockTemplate.run(EasyMockTemplate.java:116)
  ... 25 more


   */

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

  private static class CharMappingFileFactoryStub extends CharMappingFileFactory {
    @Override void createMappingFile(File file, CharMappingTableModel model) throws IOException {}

    // override these so we don't get NPE when creating a mock for CharMappingFileFactory.
    @Override void add(CharMappingFileCreationListener l) {}
    @Override void remove(CharMappingFileCreationListener l) {}
  }
}
