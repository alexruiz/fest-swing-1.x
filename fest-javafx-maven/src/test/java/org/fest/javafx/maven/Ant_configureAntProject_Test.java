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

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Fail.fail;
import static org.fest.util.Files.temporaryFolder;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.fest.mocks.EasyMockTemplate;
import org.fest.mocks.UnexpectedError;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link Ant#configureAntProject(Project, MavenProject, Log)}</code>.
 *
 * @author Alex Ruiz
 */
public class Ant_configureAntProject_Test {

  private File baseDirectory;
  private MavenProject mavenProject;
  private Log logger;
  private CustomAntProject antProject;

  @Before
  public void setUp() {
    baseDirectory = temporaryFolder();
    mavenProject = createMock(MavenProject.class);
    logger = createMock(Log.class);
    antProject = new CustomAntProject();
  }

  @Test
  public void should_configure_Ant_project() {
    new EasyMockTemplate(mavenProject) {
      protected void expectations() {
        expect(mavenProject.getBasedir()).andReturn(baseDirectory);
      }

      protected void codeToTest() throws MojoExecutionException {
        Ant.configureAntProject(antProject, mavenProject, logger);
        assertThat(antProject.getBaseDir()).isEqualTo(baseDirectory);
        LoggingBuildListener listener = (LoggingBuildListener)antProject.getBuildListeners().get(0);
        assertThat(listener.logger()).isSameAs(logger);
        assertThat(antProject.wasInitialized()).isTrue();
      }
    }.run();
  }

  @Test
  public void should_throw_error_if_configuration_fails() {
    antProject.failOnInit();
    try {
      new EasyMockTemplate(mavenProject) {
        protected void expectations() {
          expect(mavenProject.getBasedir()).andReturn(baseDirectory);
        }

        protected void codeToTest() throws MojoExecutionException {
          Ant.configureAntProject(antProject, mavenProject, logger);
        }
      }.run();
      fail("Expecting exception");
    } catch (UnexpectedError e) {
      Throwable cause = e.getCause();
      assertThat(cause).isInstanceOf(MojoExecutionException.class);
    }
  }

  private static class CustomAntProject extends Project {
    private boolean initialized;
    private boolean failOnInit;

    void failOnInit() { failOnInit = true; }

    @Override public void init() throws BuildException {
      if (failOnInit) throw new BuildException("Thrown on purpose");
      super.init();
      initialized = true;
    }

    boolean wasInitialized() { return initialized; }
  }
}
