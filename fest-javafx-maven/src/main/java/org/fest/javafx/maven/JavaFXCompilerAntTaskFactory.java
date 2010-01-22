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

import static java.io.File.separator;
import static java.lang.Thread.currentThread;
import static org.fest.javafx.maven.JavaFXCompilerClasspath.JAVAFX_COMPILER_CLASSPATH_FILE_NAMES;
import static org.fest.util.Strings.concat;

import java.io.File;
import java.net.*;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.taskdefs.Javac;

/**
 * Understands creation of new instances of the JavaFX compiler Ant task.
 *
 * @author Alex Ruiz
 */
class JavaFXCompilerAntTaskFactory {

  private static final String COMPILER_CLASSPATH_JAR_FOLDER = concat("lib", separator, "shared", separator);
  private static final String JAVAFX_COMPILER_ANT_TASK_CLASS = "com.sun.tools.javafx.ant.JavaFxAntTask";

  Javac createJavaFXCompilerAntTask(File javaFXHomeDirectory) throws MojoExecutionException {
    try {
      Class<?> javafxc = Class.forName(JAVAFX_COMPILER_ANT_TASK_CLASS, true /*initialize*/, classLoader(javaFXHomeDirectory));
      return (Javac) javafxc.newInstance();
    } catch (Exception e) {
      throw loadingTaskFailed(e);
    }
  }

  private ClassLoader classLoader(File javaFXHomeDirectory) throws MalformedURLException {
    URL[] classpath = compilerClasspath(javaFXHomeDirectory, JAVAFX_COMPILER_CLASSPATH_FILE_NAMES);
    return new URLClassLoader(classpath, currentThread().getContextClassLoader());
  }

  private static URL[] compilerClasspath(File javaFXHomeDirectory, String[] jarNames) throws MalformedURLException {
    int size = jarNames.length;
    URL[] urls = new URL[size];
    for (int i = 0; i < size; i++)
      urls[i] = jarURL(javaFXHomeDirectory, jarNames[i]);
    return urls;
  }

  private static URL jarURL(File javaFXHomeDirectory, String jarName) throws MalformedURLException {
    return new File(javaFXHomeDirectory, buildCompilerJarPath(jarName)).toURI().toURL();
  }

  private static String buildCompilerJarPath(String jarName) {
    return concat(COMPILER_CLASSPATH_JAR_FOLDER, jarName);
  }

  private static MojoExecutionException loadingTaskFailed(Exception cause) {
    String msg = "Unable to load JavaFX compiler Ant task. Please make sure javafxc.jar is in the classpath";
    return new MojoExecutionException(msg, cause);
  }
}
