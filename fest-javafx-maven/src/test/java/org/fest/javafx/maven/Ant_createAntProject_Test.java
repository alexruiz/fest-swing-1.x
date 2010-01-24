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
import static org.fest.util.Files.temporaryFolder;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.apache.tools.ant.Project;
import org.fest.mocks.EasyMockTemplate;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link Ant#createAntProject(MavenProject, Log)}</code>.
 *
 * @author Alex Ruiz
 */
public class Ant_createAntProject_Test {

  private MavenProject mavenProject;
  private Log logger;

  @Before
  public void setUp() {
    mavenProject = createMock(MavenProject.class);
    logger = createMock(Log.class);
  }

  @Test
  public void should_create_and_configure_Ant_project() {
    final File basedir = temporaryFolder();
    new EasyMockTemplate(mavenProject) {
      protected void expectations() {
        expect(mavenProject.getBasedir()).andReturn(basedir);
      }

      protected void codeToTest() throws MojoExecutionException {
        Project antProject = Ant.createAntProject(mavenProject, logger);
        assertThat(antProject.getBaseDir()).isEqualTo(basedir);
        LoggingBuildListener listener = (LoggingBuildListener)antProject.getBuildListeners().get(0);
        assertThat(listener.logger()).isSameAs(logger);
      }
    }.run();
  }
}
