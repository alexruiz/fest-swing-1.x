/*
 * Created on Jan 28, 2010
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
import static org.fest.javafx.maven.JavaFxHomeDirectory.createJavaFxHomeDirectory;

import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.Path;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link JavaFxcInstantiator#instantiateJavaFxc(Path)}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JavaFxcInstantiator_instantiateJavaFxc_Test {

  private JavaFxcInstantiator instantiator;

  @Before
  public void setUp() {
    instantiator = new JavaFxcInstantiator();
  }

  @Test
  public void should_instantiate_JavaFxc() throws Exception {
    Path classpath = new JavaFxcClasspathFactory().createCompilerClasspath(createJavaFxHomeDirectory());
    Javac javaFxc = instantiator.instantiateJavaFxc(classpath);
    assertThat(javaFxc).isNotNull();
    assertThat(javaFxc.getClass().getName()).isEqualTo("com.sun.tools.javafx.ant.JavaFxAntTask");
  }
}
