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
 * Understands the location of the JavaFX home directory.
 *
 * @author Alex Ruiz
 */
class JavaFxHome {

  private final Environment environment;

  JavaFxHome() {
    this(new Environment());
  }

  JavaFxHome(Environment environment) {
    this.environment = environment;
  }

  String verify(String javaFXHome) throws MojoExecutionException {
    String verified = javaFXHome;
    if (isEmpty(verified)) verified = environment.javaFXHome();
    if (isEmpty(verified)) throw javaFxHomeNotSet();
    return verified;
  }

  private static MojoExecutionException javaFxHomeNotSet() {
    return new MojoExecutionException("The path of the JavaFX home directory has not been set");
  }

  File reference(String javaFxHome) throws MojoExecutionException {
    File home = new File(javaFxHome);
    if (home.isDirectory()) return home;
    throw new MojoExecutionException(concat(
        "The JavaFX home directory location ", quote(javaFxHome), " does not belong to an existing directory"));
  }
}
