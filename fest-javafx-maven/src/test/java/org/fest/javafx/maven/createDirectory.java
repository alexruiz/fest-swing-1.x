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

import org.apache.maven.plugin.MojoExecutionException;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link JavaFXHome#createDirectory(String)}</code>.
 *
 * @author Alex Ruiz
 */
public class createDirectory {

  private JavaFXHome javaFXHome;

  @Before
  public void setUp() {
    javaFXHome = new JavaFXHome();
  }

  @Test
  public void should_return_File_if_path_belongs_to_directory() throws MojoExecutionException {
    String path = new Environment().javaFXHome();
    assertThat(javaFXHome.createDirectory(path)).isDirectory();
  }

  @Test(expected = MojoExecutionException.class)
  public void should_throw_error_if_path_does_not_belong_to_directory() throws MojoExecutionException {
    javaFXHome.createDirectory("");
  }
}
