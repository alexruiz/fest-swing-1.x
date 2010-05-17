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

import static java.util.Collections.sort;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.maven.JavaFxHomeDirectory.createJavaFxHomeDirectory;

import java.io.File;
import java.util.*;

import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.resources.FileResource;
import org.junit.*;

/**
 * Tests for <code>{@link JavaFxcClasspathFactory#createCompilerClasspath(File)}</code>.
 *
 * @author Alex Ruiz
 */
public class JavaFxcClasspathFactory_createCompilerClasspath_Test {

  private File javaFxHomeDirectory;
  private static JavaFxcClasspathFactory classpathFactory;

  @BeforeClass
  public static void setUpOnce() {
    classpathFactory = new JavaFxcClasspathFactory();
  }

  @Before
  public void setUp() {
    javaFxHomeDirectory = createJavaFxHomeDirectory();
  }

  @Test
  public void should_create_compiler_classpath() {
    Path classpath = classpathFactory.createCompilerClasspath(javaFxHomeDirectory);
    assertThat(classpath.getProject()).isNotNull();
    List<String> fileNames = fileNamesFrom(classpath);
    assertThat(fileNames.get(0)).endsWith("javafxc.jar");
    assertThat(fileNames.get(1)).endsWith("javafxrt.jar");
    assertThat(fileNames.get(2)).endsWith("toolchain.jar");
  }

  @SuppressWarnings("unchecked")
  private List<String> fileNamesFrom(Path classpath) {
    Iterator<FileResource> iterator = classpath.iterator();
    List<String> files = new ArrayList<String>();
    while(iterator.hasNext()) files.add(iterator.next().getName());
    sort(files);
    return files;
  }
}
