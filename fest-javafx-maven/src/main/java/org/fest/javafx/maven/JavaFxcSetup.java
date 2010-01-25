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
class JavaFxcSetup {

  private final AntProjectFactory projectFactory;
  private final JavaFxcClasspathFactory classpathFactory;

  JavaFxcSetup() {
    this(new AntProjectFactory(), new JavaFxcClasspathFactory());
  }

  JavaFxcSetup(AntProjectFactory projectFactory, JavaFxcClasspathFactory classpathFactory) {
    this.projectFactory = projectFactory;
    this.classpathFactory = classpathFactory;
  }

  void configure(Javac javaFxc, JavaFxcMojo mojo, File javaFXHomeDirectory) throws MojoExecutionException {
    setProject(javaFxc, mojo);
    setCompilerClasspath(javaFxc, javaFXHomeDirectory);
    setSource(javaFxc, mojo);
    setClasspath(javaFxc, mojo, javaFXHomeDirectory);
    javaFxc.setDebug(mojo.debug);
    javaFxc.setDeprecation(mojo.deprecation);
    javaFxc.setDestdir(mojo.outputDirectory);
    javaFxc.setEncoding(mojo.encoding);
    javaFxc.setExecutable(mojo.forkExecutable);
    javaFxc.setFailonerror(mojo.failOnError);
    javaFxc.setFork(mojo.fork);
    javaFxc.setOptimize(mojo.optimize);
    javaFxc.setSource(mojo.source);
    javaFxc.setTarget(mojo.target);
    javaFxc.setVerbose(mojo.verbose);
  }

  private void setProject(Javac javaFxc, JavaFxcMojo mojo) throws MojoExecutionException {
    Project antProject = projectFactory.createAntProject(mojo.project, mojo.getLog());
    javaFxc.setProject(antProject);
  }

  private void setCompilerClasspath(Javac javaFxc, File javaFXHomeDirectory) throws MojoExecutionException {
    Path path = classpathFactory.createCompilerClasspath(javaFxc.getProject(), javaFXHomeDirectory);
    try {
      method("setCompilerClassPath").withParameterTypes(Path.class).in(javaFxc).invoke(path);
    } catch (ReflectionError e) {
      throw new MojoExecutionException("Unable to set the compiler classpath", e);
    }
  }

  private void setSource(Javac javaFxc, JavaFxcMojo mojo) {
    updatePathWithFiles(javaFxc.createSrc(), mojo.sourceDirectory);
    updatePathWithFiles(javaFxc.createSourcepath(), mojo.sourceDirectory);
  }

  private void setClasspath(Javac javaFxc, JavaFxcMojo mojo, File javaFXHomeDirectory) {
    Path classpath = javaFxc.createClasspath();
    classpathFactory.setUpClasspath(classpath, mojo, javaFXHomeDirectory);
  }
}
