/*
 * Created on May 2, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.util.Arrays.array;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link TestTerminator#terminateTests()}.
 * 
 * @author Alex Ruiz
 */
public class TestTerminator_terminateTests_Test {
  private ThreadsSource threadsSource;
  private FrameDisposer frameDisposer;
  private MainThreadIdentifier mainThreadIdentifier;
  private TestTerminator terminator;

  @Before
  public void setUp() {
    threadsSource = createMock(ThreadsSource.class);
    frameDisposer = createMock(FrameDisposer.class);
    mainThreadIdentifier = createMock(MainThreadIdentifier.class);
    terminator = new TestTerminator(threadsSource, frameDisposer, mainThreadIdentifier);
  }

  @Test
  public void should_terminate_GUI_tests() {
    final Thread mainThread = createMock(Thread.class);
    new EasyMockTemplate(threadsSource, frameDisposer, mainThreadIdentifier, mainThread) {
      @Override
      protected void expectations() {
        Thread[] allThreads = array(mainThread);
        expect(threadsSource.allThreads()).andReturn(allThreads);
        expect(mainThreadIdentifier.mainThreadIn(allThreads)).andReturn(mainThread);
        expectInterruptionOf(mainThread);
        expectFramesDisposal();
      }

      private void expectInterruptionOf(Thread t) {
        t.interrupt();
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        terminateTestsAndExpectException();
      }
    }.run();
  }

  @Test
  public void should_not_throw_error_if_main_thread_not_found() {
    new EasyMockTemplate(threadsSource, frameDisposer, mainThreadIdentifier) {
      @Override
      protected void expectations() {
        Thread[] allThreads = new Thread[0];
        expect(threadsSource.allThreads()).andReturn(allThreads);
        expect(mainThreadIdentifier.mainThreadIn(allThreads)).andReturn(null);
        expectFramesDisposal();
      }

      @Override
      protected void codeToTest() {
        terminateTestsAndExpectException();
      }
    }.run();
  }

  private void expectFramesDisposal() {
    frameDisposer.disposeFrames();
    expectLastCall().once();
  }

  private void terminateTestsAndExpectException() {
    try {
      terminator.terminateTests();
      failWhenExpectingException();
    } catch (RuntimeException e) {
      assertThat(e.getMessage()).isEqualTo("User aborted FEST-Swing tests");
    }
  }
}
