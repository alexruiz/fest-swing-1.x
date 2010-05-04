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

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.timing.Pause.pause;

import java.io.File;

import javax.swing.JLabel;
import javax.swing.JProgressBar;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.EdtSafeCondition;
import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.fixture.FrameFixture;
import org.fest.swing.fixture.JLabelFixture;
import org.fest.swing.fixture.JProgressBarFixture;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Tests for <code>{@link CharMappingFileFactoryWorker#execute()}</code>.
 *
 * @author Alex Ruiz
 */
public class CharMappingFileFactory_execute_Test extends RobotBasedTestCase {

  private static final int MAPPING_COUNT = 10;

  private FrameFixture frame;
  private CharMappingFileFactoryStub fileFactory;
  private CharMappingFileFactoryWorker worker;

  @Override protected void onSetUp() {
    fileFactory = new CharMappingFileFactoryStub(MAPPING_COUNT);
    MyWindow window = MyWindow.createNew();
    frame = new FrameFixture(robot, window);
    worker = new CharMappingFileFactoryWorker(new File("temp.txt"), null, fileFactory, window.progressPanel);
    frame.show();
  }

  @Test
  public void should_create_file_in_background() throws Exception {
    execute(new GuiTask() {
      @Override protected void executeInEDT() throws Exception {
        worker.execute();
      }
    });
    worker.get();
    pause(new EdtSafeCondition("file creation is finished") {
      @Override protected boolean testInEDT() {
        return progressBar().component().getValue() == MAPPING_COUNT;
      }
    });
    label().requireText("Saving file 'temp.txt'");
    progressBar().requireValue(MAPPING_COUNT)
                 .requireText("100%");
    frame.requireNotVisible();
  }

  private JLabelFixture label() {
    GenericTypeMatcher<JLabel> any = new AnyJLabelMatcher();
    return frame.label(any);
  }

  private JProgressBarFixture progressBar() {
    GenericTypeMatcher<JProgressBar> any = new AnyJProgressBarMatcher();
    return frame.progressBar(any);
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

    final CharMappingFileCreationProgressPanel progressPanel = new CharMappingFileCreationProgressPanel();

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
      for (int i = 1; i <= mappingCount; i++) {
        pause(100);
        notifyMappingsProcessed(i);
      }
    }
  }

  private static class AnyJLabelMatcher extends GenericTypeMatcher<JLabel> {
    private AnyJLabelMatcher() {
      super(JLabel.class, false);
    }

    @Override protected boolean isMatching(JLabel c) {
      return true;
    }
  }

  private static class AnyJProgressBarMatcher extends GenericTypeMatcher<JProgressBar> {
    AnyJProgressBarMatcher() {
      super(JProgressBar.class, false);
    }

    @Override protected boolean isMatching(JProgressBar c) {
      return true;
    }
  }
}
