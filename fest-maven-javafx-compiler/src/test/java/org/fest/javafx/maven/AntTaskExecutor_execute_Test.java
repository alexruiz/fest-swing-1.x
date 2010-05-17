/*
 * Created on Jan 24, 2010
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
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.maven.CommonAssertions.failWhenExpectingUnexpectedError;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.fest.mocks.EasyMockTemplate;
import org.fest.mocks.UnexpectedError;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link AntTaskExecutor#execute(org.apache.tools.ant.Task)}</code>.
 *
 * @author Alex Ruiz
 */
public class AntTaskExecutor_execute_Test {

  private Task task;
  private AntTaskExecutor taskExecutor;

  @Before
  public void setUp() {
    task = createMock(Task.class);
    taskExecutor = new AntTaskExecutor();
  }

  @Test
  public void should_execute_task() {
    new EasyMockTemplate(task) {
      @Override protected void expectations() {
        task.execute();
        expectLastCall().once();
      }

      @Override protected void codeToTest() throws MojoExecutionException {
        taskExecutor.execute(task);
      }
    }.run();
  }

  @Test
  public void should_rethrow_error_thrown_by_task() {
    final BuildException error = new BuildException("Thrown on purpose");
    try {
      new EasyMockTemplate(task) {
        @Override protected void expectations() {
          task.execute();
          expectLastCall().andThrow(error);
        }

        @Override protected void codeToTest() throws MojoExecutionException {
            taskExecutor.execute(task);
        }
      }.run();
      failWhenExpectingUnexpectedError();
    } catch (UnexpectedError e) {
      Throwable expected = e.getCause();
      assertThat(expected).isInstanceOf(MojoExecutionException.class);
      assertThat(expected.getCause()).isSameAs(error);
    }
  }
}
