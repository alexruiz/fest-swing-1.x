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

import static java.util.Collections.unmodifiableList;

import java.io.File;
import java.util.List;

import org.fest.util.VisibleForTesting;

/**
 * Compiles JavaFX source code by delegating to JavaFX's own compiler Ant task.
 * @goal compile
 * @phase compile
 * @requiresDependencyResolution compile
 *
 * @author Alex Ruiz
 */
public final class JavaFxcMojo extends AbstractJavaFxcMojo {

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

  @Override List<String> compileClasspathElements() {
    return unmodifiableList(compileClasspathElements);
  }

  @Override File outputDirectory() {
    return outputDirectory;
  }

  @Override File sourceDirectory() {
    return sourceDirectory;
  }
}
