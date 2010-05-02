/*
 * Created on May 2, 2010
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

import java.io.File;

import org.junit.Test;

import org.fest.swing.annotation.*;
import org.fest.swing.edt.*;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.timing.Pause.pause;

/**
 * Tests for <code>{@link CharMappingFileFactoryWorker#execute()}</code>.
 *
 * @author Alex Ruiz
 */
public class CharMappingFileFactory_execute_Test extends RobotBasedTestCase {

  private MyWindow window;
  private CharMappingFileFactoryStub fileFactory;
  private CharMappingFileFactoryWorker worker;
  
  @Override protected void onSetUp() {
    fileFactory = new CharMappingFileFactoryStub(10);
    window = MyWindow.createNew();
    worker = execute(new GuiQuery<CharMappingFileFactoryWorker>() {
      @Override protected CharMappingFileFactoryWorker executeInEDT() {
        return new CharMappingFileFactoryWorker(null, null, fileFactory, window.progressPanel);
      }
    });
    robot.showWindow(window);
  }
  
  @Test
  public void should_create_file_in_background() throws Exception {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        worker.execute();
      }
    });
    worker.get();
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
    
    final CharMappingCreationProgressPanel progressPanel = new CharMappingCreationProgressPanel();
    
    @RunsInCurrentThread
    private MyWindow() {
      super(CharMappingFileFactory_execute_Test.class);
      setContentPane(progressPanel);
    }
  }
  
  private static class CharMappingFileFactoryStub extends CharMappingFileFactory {
    private final int mappingCount;

    CharMappingFileFactoryStub(int mappingCount) {
      this.mappingCount = mappingCount;
    }
    
    @Override void createMappingFile(File file, CharMappingTableModel model) {
      notifyCreationStarted(mappingCount);
      int count = 0;
      for (int i = 0; i < mappingCount; i++) {
        pause(2000);
        notifyMappingsProcessed(++count);
      }
    }
  }
}
