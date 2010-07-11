/*
 * Created on Jan 20, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010 the original author or authors.
 */
package org.fest.javafx.maven;

import org.apache.maven.plugin.MojoExecutionException;
import org.fest.util.VisibleForTesting;

import java.io.File;
import java.util.List;

import static java.util.Collections.unmodifiableList;

/**
 * Compiles JavaFX source code by delegating to JavaFX's own compiler Ant task.
 * @goal compile
 * @phase compile
 * @requiresDependencyResolution compile
 *
 * @author Alex Ruiz
 * @author Johannes Schneider
 */
public class JavaFxcMojo extends AbstractJavaFxcMojo {

  /**
   * The list of compile classpath elements.
   * @parameter default-value="${project.compileClasspathElements}"
   * @requiresDependencyResolution compile
   * @required
   * @readonly
   */
  @VisibleForTesting List<String> compileClasspathElements;

  /**
   * The directory for compiled classes.
   * @parameter default-value="${project.build.outputDirectory}"
   * @required
   * @readonly
   */
  @VisibleForTesting File outputDirectory;

  /**
   * The source directory.
   * @parameter expression="${javafx.compiler.sourceDirectory}" default-value="${basedir}/src/main/javafx"
   * @required
   */
  @VisibleForTesting File sourceDirectory;

  /**
   * Creates and executes a new instance of the JavaFX compiler Ant task to compile JavaFX sources.
   * @throws MojoExecutionException if the specified source directory does not exist or it is not a directory.
   * @throws MojoExecutionException if the output directory does not exist and cannot be created.
   * @throws MojoExecutionException if the JavaFX compiler Ant task cannot be instantiated.
   * @throws MojoExecutionException if the JavaFX home directory has not being set.
   * @throws MojoExecutionException if the location specified by as the JavaFX home directory does not exist or it is
   * not a directory.
   */
  @Override public void execute() throws MojoExecutionException {
    if (!isJavaProject()) return;
    compile();
  }

  @Override List<String> classpathElements() {
    return unmodifiableList(compileClasspathElements);
  }

  @Override File outputDirectory() {
    return outputDirectory;
  }

  @Override File sourceDirectory() {
    return sourceDirectory;
  }
}
