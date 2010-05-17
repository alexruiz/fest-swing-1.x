/*
 * Created on Jan 23, 2010
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

import static org.fest.assertions.Assertions.assertThat;

import java.io.File;

/**
 * Understands utility methods related to the JavaFX home directory.
 *
 * @author Alex Ruiz
 */
final class JavaFxHomeDirectory {

  static File createJavaFxHomeDirectory() {
    String javaFXHome = System.getenv("JAVAFX_HOME");
    assertThat(javaFXHome).isNotEmpty();
    File javaFXHomeDirectory = new File(javaFXHome);
    assertThat(javaFXHomeDirectory).isDirectory();
    return javaFXHomeDirectory;
  }
  
  private JavaFxHomeDirectory() {}
}
