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
import static org.fest.javafx.maven.Classpaths.JAVAFX_COMPILER_CLASSPATH_FILE_NAMES;
import static org.fest.javafx.maven.Classpaths.JAVAFX_DESKTOP_CLASSPATH_FILE_PATTERNS;
import static org.fest.reflect.core.Reflection.method;
import static org.fest.util.Strings.concat;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;
import org.fest.reflect.exception.ReflectionError;

/**
 * Understands configuration of an instance of the JavaFX compiler Ant task.
 *
 * @author Alex Ruiz
 */
public class JavaFXCompilerSetup {

  void configure(Javac javafxc, JavaFXCompilerMojo mojo, File javaFXHomeDirectory) throws MojoExecutionException {
    setProject(javafxc, mojo);
    setCompilerClasspath(javafxc, javaFXHomeDirectory);
    setSource(javafxc, mojo);
    setClasspath(javafxc, mojo, javaFXHomeDirectory);
    setOutputDirectory(javafxc, mojo);
    javafxc.setDebug(mojo.debug);
    javafxc.setDeprecation(mojo.deprecation);
    javafxc.setEncoding(mojo.encoding);
    javafxc.setExecutable(mojo.forkExecutable);
    javafxc.setFailonerror(mojo.failOnError);
    javafxc.setFork(mojo.fork);
    javafxc.setOptimize(mojo.optimize);
    javafxc.setSource(mojo.source);
    javafxc.setTarget(mojo.target);
    javafxc.setVerbose(mojo.verbose);
  }

  private static void setProject(Javac javafxc, JavaFXCompilerMojo mojo) throws MojoExecutionException {
    Project antProject = createAntProject(mojo.project, mojo.getLog());
    javafxc.setProject(antProject);
  }

  private static void setCompilerClasspath(Javac javafxc, File javaFXHomeDirectory) throws MojoExecutionException {
    Path path = createCompilerClasspath(javafxc.getProject(), javaFXHomeDirectory);
    try {
      method("setCompilerClassPath").withParameterTypes(Path.class).in(javafxc).invoke(path);
    } catch (ReflectionError e) {
      throw new MojoExecutionException("Unable to set the compiler classpath", e);
    }
  }

  private static Path createCompilerClasspath(Project project, File javaFXHomeDirectory) {
    FileSet files = new FileSet();
    files.setDir(javaFXHomeDirectory);
    for (String include : JAVAFX_COMPILER_CLASSPATH_FILE_NAMES)
      files.createInclude().setName(concat("**/", include));
    Path path = new Path(project);
    path.addFileset(files);
    return path;
  }

  private static void setSource(Javac javafxc, JavaFXCompilerMojo mojo) {
    updatePathWithFiles(javafxc.createSrc(), mojo.sourceDirectory);
    updatePathWithFiles(javafxc.createSourcepath(), mojo.sourceDirectory);
  }

  private static void setClasspath(Javac javafxc, JavaFXCompilerMojo mojo, File javaFXHomeDirectory) {
    Path classpath = javafxc.createClasspath();
    FileSet javaFXFiles = new FileSet();
    javaFXFiles.setDir(javaFXHomeDirectory);
    for (String include : JAVAFX_DESKTOP_CLASSPATH_FILE_PATTERNS)
      javaFXFiles.createInclude().setName(include);
    classpath.addFileset(javaFXFiles);
    updatePathWithPaths(classpath, mojo.compileClasspathElements);
  }

  private static void setOutputDirectory(Javac javafxc, JavaFXCompilerMojo mojo) {
    javafxc.setDestdir(mojo.outputDirectory);
  }
}
