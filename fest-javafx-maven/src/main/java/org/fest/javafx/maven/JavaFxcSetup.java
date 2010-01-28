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

import static org.fest.util.Collections.isEmpty;

import java.io.File;
import java.util.List;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

/**
 * Understands configuration of an instance of the JavaFX compiler Ant task.
 *
 * @author Alex Ruiz
 */
class JavaFxcSetup {

  private static final String[] JAVAFX_DESKTOP_CLASSPATH_FILES = { "**/javafxrt.jar", "**/lib/desktop/*.jar" };

  void setUpJavaFxc(Javac javaFxc, JavaFxcMojo javaFxcMojo, File javaFxcHome) {
    configureProject(javaFxc, javaFxcMojo);
    setSource(javaFxc, javaFxcMojo.sourceDirectory);
    setClasspath(javaFxc, javaFxcMojo, javaFxcHome);
    javaFxc.setDebug(javaFxcMojo.debug);
    javaFxc.setDeprecation(javaFxcMojo.deprecation);
    javaFxc.setDestdir(javaFxcMojo.outputDirectory);
    javaFxc.setEncoding(javaFxcMojo.encoding);
    javaFxc.setExecutable(javaFxcMojo.forkExecutable);
    javaFxc.setFailonerror(javaFxcMojo.failOnError);
    javaFxc.setFork(javaFxcMojo.fork);
    javaFxc.setOptimize(javaFxcMojo.optimize);
    javaFxc.setSource(javaFxcMojo.source);
    javaFxc.setTarget(javaFxcMojo.target);
    javaFxc.setVerbose(javaFxcMojo.verbose);
  }

  private void configureProject(Javac javaFxc, JavaFxcMojo javaFxcMojo) {
    Project antProject = javaFxc.getProject();
    antProject.setBaseDir(javaFxcMojo.project.getBasedir());
    antProject.addBuildListener(new LoggingBuildListener(javaFxcMojo.getLog()));
    antProject.init();
  }

  private void setSource(Javac javaFxc, File sourceDirectory) {
    updatePathWithFile(javaFxc.createSrc(), sourceDirectory);
    updatePathWithFile(javaFxc.createSourcepath(), sourceDirectory);
  }

  private void updatePathWithFile(Path path, File file) {
    path.createPathElement().setLocation(file);
  }

  private void setClasspath(Javac javaFxc, JavaFxcMojo javaFxMojo, File javaFxcHome) {
    Path classpath = javaFxc.createClasspath();
    classpath.addFileset(javaFxFiles(javaFxcHome));
    updatePathWithElements(classpath, javaFxMojo.compileClasspathElements);
  }

  private FileSet javaFxFiles(File javaFxcHome) {
    FileSet javaFxFiles = new FileSet();
    javaFxFiles.setDir(javaFxcHome);
    for (String include : JAVAFX_DESKTOP_CLASSPATH_FILES)
      javaFxFiles.createInclude().setName(include);
    return javaFxFiles;
  }

  private static void updatePathWithElements(Path path, List<String> elements) {
    if (isEmpty(elements)) return;
    for (String element : elements) path.createPathElement().setPath(element);
  }
}
