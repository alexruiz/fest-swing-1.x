/*
 * Created on Jan 25, 2010
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

import org.apache.maven.project.MavenProject;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.resources.FileResource;
import org.fest.mocks.EasyMockTemplate;
import org.junit.*;

import java.io.File;
import java.util.Iterator;

import static java.util.UUID.randomUUID;
import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.maven.JavaFxHomeDirectory.createJavaFxHomeDirectory;
import static org.fest.javafx.maven.JavaFxJarsInclusion.*;
import static org.fest.util.Collections.list;
import static org.fest.util.Files.temporaryFolder;

/**
 * Tests for <code>{@link JavaFxcSetup#setUpJavaFxc(Javac, AbstractJavaFxcMojo, File, boolean)}</code>.
 *
 * @author Alex Ruiz
 * @author Johannes Schneider
 */
public class JavaFxcSetup_setUpJavaFxc_Test {

  private Javac javaFxc;
  private AbstractJavaFxcMojoStub javaFxcMojo;
  private MavenProject mavenProject;
  private File javaFxcHome;
  private File basedir;
  private JavaFxcSetup javaFxcSetup;
  private String extraClasspathElement;

  @Before
  public void setUp() {
    javaFxc = new Javac();
    javaFxc.setProject(new Project());
    mavenProject = createMock(MavenProject.class);
    javaFxcMojo = new AbstractJavaFxcMojoStub();
    javaFxcHome = createJavaFxHomeDirectory();
    basedir = temporaryFolder();
    javaFxcSetup = new JavaFxcSetup();
    extraClasspathElement = randomUUID().toString();
    configureJavaFxcMojo();
  }

  private void configureJavaFxcMojo() {
    javaFxcMojo.debug = false;
    javaFxcMojo.deprecation = true;
    javaFxcMojo.encoding = "UTF-16";
    javaFxcMojo.failOnError = false;
    javaFxcMojo.fork = true;
    javaFxcMojo.forkExecutable = "javafx.exe";
    javaFxcMojo.optimize = true;
    javaFxcMojo.outputDirectory = temporaryFolder();
    javaFxcMojo.project = mavenProject;
    javaFxcMojo.source = "1.5";
    javaFxcMojo.sourceDirectory = temporaryFolder();
    javaFxcMojo.target = "1.5";
    javaFxcMojo.verbose = true;
    javaFxcMojo.memoryMaximumSize = "256m";
  }

  @Test
  public void should_configure_JavaFxc() {
    new EasyMockTemplate(mavenProject) {
      @Override protected void expectations() {
        expect(mavenProject.getBasedir()).andReturn(basedir);
      }

      @Override protected void codeToTest() {
        javaFxcSetup.setUpJavaFxc(javaFxc, javaFxcMojo, javaFxcHome, AUTOMATIC);
      }
    }.run();
    assertThat(javaFxc.getDebug()).isEqualTo(javaFxcMojo.debug);
    assertThat(javaFxc.getDeprecation()).isEqualTo(javaFxcMojo.deprecation);
    assertThat(javaFxc.getDestdir()).isEqualTo(javaFxcMojo.outputDirectory);
    assertThat(javaFxc.getEncoding()).isEqualTo(javaFxcMojo.encoding);
    assertThat(javaFxc.getFailonerror()).isEqualTo(javaFxcMojo.failOnError);
    assertThat(javaFxc.isForkedJavac()).isEqualTo(javaFxcMojo.fork);
    assertThat(javaFxc.getExecutable()).isEqualTo(javaFxcMojo.forkExecutable);
    assertThat(javaFxc.getOptimize()).isEqualTo(javaFxcMojo.optimize);
    assertThat(javaFxc.getSource()).isEqualTo(javaFxcMojo.source);
    assertThat(javaFxc.getTarget()).isEqualTo(javaFxcMojo.target);
    assertThat(javaFxc.getVerbose()).isEqualTo(javaFxcMojo.verbose);
    assertThat(javaFxc.getMemoryMaximumSize()).isEqualTo(javaFxcMojo.memoryMaximumSize);
  }

  @Test
  public void should_include_classpath_elements_from_Mojo() {
    javaFxcMojo.compileClasspathElements = list(extraClasspathElement);
    new EasyMockTemplate(mavenProject) {
      @Override protected void expectations() {
        expect(mavenProject.getBasedir()).andReturn(basedir);
      }

      @Override protected void codeToTest() {
        javaFxcSetup.setUpJavaFxc(javaFxc, javaFxcMojo, javaFxcHome, AUTOMATIC);
      }
    }.run();
    assertThat(containsExtraClasspathElement(javaFxc.getClasspath())).isTrue();
  }

  private boolean containsExtraClasspathElement(Path classpath) {
    Iterator<FileResource> iterator = iteratorOf(classpath);
    while(iterator.hasNext()) {
      String name = iterator.next().getName();
      if (name.contains(extraClasspathElement)) return true;
    }
    return false;
  }

  @Test
  public void should_include_classpath_elements_from_Mojo_no_cp_magic() {
    javaFxcMojo.compileClasspathElements = list(extraClasspathElement);
    new EasyMockTemplate(mavenProject) {
      @Override protected void expectations() {
        expect(mavenProject.getBasedir()).andReturn(basedir);
      }

      @Override protected void codeToTest() {
        javaFxcSetup.setUpJavaFxc(javaFxc, javaFxcMojo, javaFxcHome, MANUAL);
      }
    }.run();
    assertThat(containsExtraClasspathElementOnly(javaFxc.getClasspath())).isTrue();
  }

  private boolean containsExtraClasspathElementOnly(Path classpath) {
    int counter = 0;
    boolean found = false;
    Iterator<FileResource> iterator = iteratorOf(classpath);
    while(iterator.hasNext()) {
      String name = iterator.next().getName();
      if (name.contains(extraClasspathElement)) found = true;
      counter++;
    }
    return found == true && counter == 1;
  }

  @SuppressWarnings("unchecked")
  private Iterator<FileResource> iteratorOf(Path classpath) {
    Iterator<FileResource> iterator = classpath.iterator();
    return iterator;
  }
}
