/*
 * Created on Jan 23, 2010
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
package org.fest.javafx.maven;

import static org.easymock.EasyMock.expectLastCall;

import org.apache.tools.ant.*;
import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Test case for <code>{@link LoggingBuildListener#taskStarted(BuildEvent)}</code>.
 *
 * @author Alex Ruiz
 */
public class LoggingBuildListener_taskStarted_Test extends LoggingBuildListener_TestCase {

  @Test
  public void should_log_when_task_started() {
    new EasyMockTemplate(log) {
      protected void expectations() {
        log.info(BUILD_EVENT_MESSAGE);
        expectLastCall().once();
      }

      protected void codeToTest() {
        listener.taskStarted(buildEvent);
      }
    }.run();
  }
}
