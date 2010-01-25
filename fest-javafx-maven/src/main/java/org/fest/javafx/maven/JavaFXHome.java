/*
 * Created on Jan 21, 2010
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

import static org.fest.util.Strings.*;
import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;

/**
 * Understands utility methods related to the location of the JavaFX home directory.
 *
 * @author Alex Ruiz
 */
class JavaFXHome {

  private static final String JAVAFX_HOME_ENV_VAR = "JAVAFX_HOME";

  private final Environment environment;

  JavaFXHome() {
    this(new Environment());
  }

  JavaFXHome(Environment environment) {
    this.environment = environment;
  }

  String verify(String javaFXHome) throws MojoExecutionException {
    String verified = javaFXHome;
    if (isEmpty(verified)) verified = environment.javaFXHome();
    if (isEmpty(verified)) throw javaFXHomeNotSet();
    return verified;
  }

  private static MojoExecutionException javaFXHomeNotSet() {
    return new MojoExecutionException(concat(
        "JavaFX home has not been set. Set it either as the property 'javafx.home' or as the environment variable ",
        JAVAFX_HOME_ENV_VAR));
  }

  File reference(String javaFXHome) throws MojoExecutionException {
    File home = new File(javaFXHome);
    if (home.isDirectory()) return home;
    throw new MojoExecutionException(concat(
        "The JavaFX home directory location ", quote(javaFXHome), " does not belong to an existing directory"));
  }
}
