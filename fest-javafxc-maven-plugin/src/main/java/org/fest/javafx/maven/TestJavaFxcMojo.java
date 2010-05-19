/*
 * Created on May 17, 2010
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

import static java.util.Collections.unmodifiableList;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;

import org.fest.util.VisibleForTesting;

/**
 * Compiles JavaFX test code by delegating to JavaFX's own compiler Ant task.
 * @goal testCompile
 * @phase test-compile
 * @requiresDependencyResolution test
 *
 * @author Alex Ruiz
 */
public class TestJavaFxcMojo extends AbstractJavaFxcMojo {

  /**
   * Set this to 'true' to bypass unit tests entirely. Its use is NOT RECOMMENDED, but quite convenient on occasion.
   * @parameter expression="${maven.test.skip}"
   */
  @VisibleForTesting boolean skip;

  /**
   * The list of test classpath elements.
   * @parameter default-value="${project.testClasspathElements}"
   * @requiresDependencyResolution test
   * @required
   * @readonly
   */
  @VisibleForTesting List<String> testClasspathElements;

  /**
   * The directory for compiled test classes.
   * @parameter default-value="${project.build.testOutputDirectory}"
   * @required
   * @readonly
   */
  @VisibleForTesting File outputDirectory;

  /**
   * The test source directory.
   * @parameter expression="${javafx.compiler.testSourceDirectory}" default-value="${basedir}/src/test/javafx"
   * @required
   */
  @VisibleForTesting File sourceDirectory;

  @Override List<String> classpathElements() {
    return unmodifiableList(testClasspathElements);
  }

  @Override File outputDirectory() {
    return outputDirectory;
  }

  @Override File sourceDirectory() {
    return sourceDirectory;
  }

  /**
   * Creates and executes a new instance of the JavaFX compiler Ant task to compile JavaFX test sources. If {@code skip}
   * is <code>true</code>, this method will not compile test sources.
   * @throws MojoExecutionException if the specified source directory does not exist or it is not a directory.
   * @throws MojoExecutionException if the output directory does not exist and cannot be created.
   * @throws MojoExecutionException if the JavaFX compiler Ant task cannot be instantiated.
   * @throws MojoExecutionException if the JavaFX home directory has not being set.
   * @throws MojoExecutionException if the location specified by as the JavaFX home directory does not exist or it is
   * not a directory.
   */
  @Override public void execute() throws MojoExecutionException {
    if (skip) {
      getLog().info("Not compiling test sources");
      return;
    }
    compile();
  }
}
