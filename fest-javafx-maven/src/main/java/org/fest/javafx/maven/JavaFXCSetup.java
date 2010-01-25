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

import static org.fest.javafx.maven.Ant.*;
import static org.fest.reflect.core.Reflection.method;
import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.Path;
import org.fest.reflect.exception.ReflectionError;

/**
 * Understands configuration of an instance of the JavaFX compiler Ant task.
 *
 * @author Alex Ruiz
 */
class JavaFXCSetup {

  private final AntProjectFactory projectFactory;
  private final JavaFXClasspathFactory classpathFactory;

  JavaFXCSetup() {
    this(new AntProjectFactory(), new JavaFXClasspathFactory());
  }

  JavaFXCSetup(AntProjectFactory projectFactory, JavaFXClasspathFactory classpathFactory) {
    this.projectFactory = projectFactory;
    this.classpathFactory = classpathFactory;
  }

  void configure(Javac javaFXC, JavaFXCMojo mojo, File javaFXHomeDirectory) throws MojoExecutionException {
    setProject(javaFXC, mojo);
    setCompilerClasspath(javaFXC, javaFXHomeDirectory);
    setSource(javaFXC, mojo);
    setClasspath(javaFXC, mojo, javaFXHomeDirectory);
    javaFXC.setDebug(mojo.debug);
    javaFXC.setDeprecation(mojo.deprecation);
    javaFXC.setDestdir(mojo.outputDirectory);
    javaFXC.setEncoding(mojo.encoding);
    javaFXC.setExecutable(mojo.forkExecutable);
    javaFXC.setFailonerror(mojo.failOnError);
    javaFXC.setFork(mojo.fork);
    javaFXC.setOptimize(mojo.optimize);
    javaFXC.setSource(mojo.source);
    javaFXC.setTarget(mojo.target);
    javaFXC.setVerbose(mojo.verbose);
  }

  private void setProject(Javac javaFXC, JavaFXCMojo mojo) throws MojoExecutionException {
    Project antProject = projectFactory.createAntProject(mojo.project, mojo.getLog());
    javaFXC.setProject(antProject);
  }

  private void setCompilerClasspath(Javac javaFXC, File javaFXHomeDirectory) throws MojoExecutionException {
    Path path = classpathFactory.createCompilerClasspath(javaFXC.getProject(), javaFXHomeDirectory);
    try {
      method("setCompilerClassPath").withParameterTypes(Path.class).in(javaFXC).invoke(path);
    } catch (ReflectionError e) {
      throw new MojoExecutionException("Unable to set the compiler classpath", e);
    }
  }

  private void setSource(Javac javaFXC, JavaFXCMojo mojo) {
    updatePathWithFiles(javaFXC.createSrc(), mojo.sourceDirectory);
    updatePathWithFiles(javaFXC.createSourcepath(), mojo.sourceDirectory);
  }

  private void setClasspath(Javac javaFXC, JavaFXCMojo mojo, File javaFXHomeDirectory) {
    Path classpath = javaFXC.createClasspath();
    classpathFactory.setUpClasspath(classpath, mojo, javaFXHomeDirectory);
  }
}
