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

import org.apache.maven.plugin.MojoExecutionException;

import java.io.File;

/**
 * Understands validation of the state of a <code>{@link JavaFxcMojo}</code>.
 *
 * @author Alex Ruiz
 */
class JavaFxcMojoValidator {

  void validate(AbstractJavaFxcMojo javaFxcMojo) throws MojoExecutionException {
    validateSourceDirectory(javaFxcMojo);
    validateOutputDirectory(javaFxcMojo);
  }

  private void validateSourceDirectory(AbstractJavaFxcMojo javaFxcMojo) throws MojoExecutionException {
    if (javaFxcMojo.sourceDirectory().isDirectory()) return;
    throw new MojoExecutionException( "Source directory <" + javaFxcMojo.sourceDirectory().getAbsolutePath() + "> is not an existing directory." );
  }

  private void validateOutputDirectory(AbstractJavaFxcMojo javaFxcMojo) throws MojoExecutionException {
    File output = javaFxcMojo.outputDirectory();
    if (output.isDirectory()) return;
    boolean success = output.mkdirs();
    if (!success) throw new MojoExecutionException( "Unable to create output directory <" + output.getAbsolutePath() + ">." );
  }
}
