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

import static org.apache.tools.ant.Project.*;
import static org.easymock.EasyMock.expectLastCall;

import org.apache.tools.ant.*;
import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Test case for <code>{@link LoggingBuildListener#messageLogged(BuildEvent)}</code>.
 *
 * @author Alex Ruiz
 */
public class LoggingBuildListener_messageLogged_Test extends LoggingBuildListener_TestCase {

  @Test
  public void should_log_with_error_priority_when_BuildEvent_has_error_priority() {
    buildEvent.setMessage(BUILD_EVENT_MESSAGE, MSG_ERR);
    new EasyMockTemplate(log) {
      protected void expectations() {
        log.error(BUILD_EVENT_MESSAGE);
        expectLastCall().once();
      }

      protected void codeToTest() {
        listener.messageLogged(buildEvent);
      }
    }.run();
  }

  @Test
  public void should_log_with_warn_priority_when_BuildEvent_has_warn_priority() {
    buildEvent.setMessage(BUILD_EVENT_MESSAGE, MSG_WARN);
    new EasyMockTemplate(log) {
      protected void expectations() {
        log.warn(BUILD_EVENT_MESSAGE);
        expectLastCall().once();
      }

      protected void codeToTest() {
        listener.messageLogged(buildEvent);
      }
    }.run();
  }

  @Test
  public void should_log_with_info_priority_when_BuildEvent_has_info_priority() {
    buildEvent.setMessage(BUILD_EVENT_MESSAGE, MSG_INFO);
    new EasyMockTemplate(log) {
      protected void expectations() {
        log.info(BUILD_EVENT_MESSAGE);
        expectLastCall().once();
      }

      protected void codeToTest() {
        listener.messageLogged(buildEvent);
      }
    }.run();
  }


  @Test
  public void should_log_with_debug_priority_when_BuildEvent_has_debug_priority() {
    buildEvent.setMessage(BUILD_EVENT_MESSAGE, MSG_DEBUG);
    new EasyMockTemplate(log) {
      protected void expectations() {
        log.debug(BUILD_EVENT_MESSAGE);
        expectLastCall().once();
      }

      protected void codeToTest() {
        listener.messageLogged(buildEvent);
      }
    }.run();
  }

  @Test
  public void should_not_log_if_BuildEvent_has_verbose_priority() {
    buildEvent.setMessage(BUILD_EVENT_MESSAGE, MSG_VERBOSE);
    new EasyMockTemplate(log) {
      protected void expectations() {
        // log should not be called.
      }

      protected void codeToTest() {
        listener.messageLogged(buildEvent);
      }
    }.run();
  }

  @Test
  public void should_log_empty_message_if_BuildEvent_has_null_message() {
    buildEvent.setMessage(null, MSG_INFO);
    new EasyMockTemplate(log) {
      protected void expectations() {
        log.info("");
        expectLastCall().once();
      }

      protected void codeToTest() {
        listener.messageLogged(buildEvent);
      }
    }.run();
  }

  @Test
  public void should_trim_whitespaces_from_BuildEvent_message() {
    buildEvent.setMessage(" Hello World! ", MSG_INFO);
    new EasyMockTemplate(log) {
      protected void expectations() {
        log.info("Hello World!");
        expectLastCall().once();
      }

      protected void codeToTest() {
        listener.messageLogged(buildEvent);
      }
    }.run();
  }
}
