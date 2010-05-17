/*
 * Created on Jan 28, 2010
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
import static org.fest.javafx.maven.CommonAssertions.failWhenExpectingUnexpectedError;
import static org.fest.util.Files.temporaryFolder;

import java.io.File;
import java.net.MalformedURLException;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.Path;
import org.fest.mocks.EasyMockTemplate;
import org.fest.mocks.UnexpectedError;
import org.fest.reflect.exception.ReflectionError;
import org.junit.*;

/**
 * Tests for <code>{@link JavaFxcFactory#createJavaFxc(java.io.File)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JavaFxcFactory_createJavaFxc_withMocks_Test {

  private static File javaFxcHome;
  private static Path classpath;
  private JavaFxcClasspathFactory classpathFactory;
  private JavaFxcInstantiator instantiator;
  private JavaFxcFactory javaFxcFactory;

  @BeforeClass
  public static void setUpOnce() {
    javaFxcHome = temporaryFolder();
    classpath = new Path(new Project());
  }

  @Before
  public void setUp() {
    classpathFactory = createMock(JavaFxcClasspathFactory.class);
    instantiator = createMock(JavaFxcInstantiator.class);
    javaFxcFactory = new JavaFxcFactory(classpathFactory, instantiator);
  }

  @Test
  public void should_wrap_thrown_BuildException() {
    final BuildException cause = new BuildException();
    try {
      new EasyMockTemplate(classpathFactory, instantiator) {
        protected void expectations() {
          expect(classpathFactory.createCompilerClasspath(javaFxcHome)).andThrow(cause);
        }

        protected void codeToTest() throws MojoExecutionException {
          javaFxcFactory.createJavaFxc(javaFxcHome);
        }
      }.run();
      failWhenExpectingUnexpectedError();
    } catch (UnexpectedError error) {
      assertErrorHasCorrectCause(error, cause);
    }
  }

  @Test
  public void should_wrap_thrown_ReflectionError() {
    try {
      new EasyMockTemplate(classpathFactory, instantiator) {
        protected void expectations() throws Exception {
          expect(classpathFactory.createCompilerClasspath(javaFxcHome)).andReturn(classpath);
          // plain Javac does not have method 'setCompilerClassPath'
          expect(instantiator.instantiateJavaFxc(classpath)).andReturn(new Javac());
        }

        protected void codeToTest() throws MojoExecutionException {
          javaFxcFactory.createJavaFxc(javaFxcHome);
        }
      }.run();
      failWhenExpectingUnexpectedError();
    } catch (UnexpectedError error) {
      MojoExecutionException mojoExecutionException = (MojoExecutionException) error.getCause();
      assertThat(mojoExecutionException.getCause()).isInstanceOf(ReflectionError.class);
    }
  }

  @Test
  public void should_wrap_thrown_MalformedURLException() {
    should_wrap_thrown_Exception(new MalformedURLException());
  }

  @Test
  public void should_wrap_thrown_ClassNotFoundException() {
    should_wrap_thrown_Exception(new ClassNotFoundException());
  }

  @Test
  public void should_wrap_thrown_InstantiationException() {
    should_wrap_thrown_Exception(new InstantiationException());
  }

  @Test
  public void should_wrap_thrown_IllegalAccessException() {
    should_wrap_thrown_Exception(new IllegalAccessException());
  }

  private void should_wrap_thrown_Exception(final Exception cause) {
    try {
      new EasyMockTemplate(classpathFactory, instantiator) {
        protected void expectations() throws Exception {
          expect(classpathFactory.createCompilerClasspath(javaFxcHome)).andReturn(classpath);
          expect(instantiator.instantiateJavaFxc(classpath)).andThrow(cause);
        }

        protected void codeToTest() throws MojoExecutionException {
          javaFxcFactory.createJavaFxc(javaFxcHome);
        }
      }.run();
      failWhenExpectingUnexpectedError();
    } catch (UnexpectedError error) {
      assertErrorHasCorrectCause(error, cause);
    }
  }

  private void assertErrorHasCorrectCause(UnexpectedError error, Exception cause) {
    MojoExecutionException mojoExecutionException = (MojoExecutionException) error.getCause();
    assertThat(mojoExecutionException.getMessage()).contains("Unable to load JavaFX compiler Ant task");
    assertThat(mojoExecutionException.getCause()).isSameAs(cause);
  }
}
