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

import static org.apache.tools.ant.Project.MSG_INFO;
import static org.easymock.classextension.EasyMock.createMock;
import org.apache.maven.plugin.logging.Log;
import org.apache.tools.ant.*;
import org.junit.Before;

/**
 * Test case for <code>{@link LoggingBuildListener}</code>.
 *
 * @author Alex Ruiz
 */
public abstract class LoggingBuildListener_TestCase {

  static final String BUILD_EVENT_MESSAGE = "Hello World!";

  Log log;
  LoggingBuildListener listener;
  BuildEvent buildEvent;

  @Before
  public final void setUp() {
    log = createMock(Log.class);
    listener = new LoggingBuildListener(log);
    buildEvent = new BuildEvent(createMock(Project.class));
    buildEvent.setMessage(BUILD_EVENT_MESSAGE, MSG_INFO);
  }
}
