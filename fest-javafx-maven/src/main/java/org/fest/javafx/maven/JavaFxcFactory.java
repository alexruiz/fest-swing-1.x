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

import static java.lang.Thread.currentThread;
import static org.fest.reflect.core.Reflection.method;

import java.io.File;
import java.net.*;
import java.util.*;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.resources.FileResource;
import org.fest.reflect.exception.ReflectionError;

/**
 * Understands creation of new instances of the JavaFX compiler Ant task.
 *
 * @author Alex Ruiz
 */
class JavaFxcFactory {

  private static final String JAVAFX_COMPILER_ANT_TASK_CLASS = "com.sun.tools.javafx.ant.JavaFxAntTask";

  private final JavaFxcClasspathFactory classpathFactory = new JavaFxcClasspathFactory();

  Javac createJavaFxc(File javaFxHome) throws MojoExecutionException {
    try {
      Path compilerClasspath = classpathFactory.createCompilerClasspath(new Project(), javaFxHome);
      Class<?> javaFxcType = Class.forName(JAVAFX_COMPILER_ANT_TASK_CLASS, true /*initialize*/, classLoader(compilerClasspath));
      Javac javaFxc = (Javac) javaFxcType.newInstance();
      configureCompiler(javaFxc, compilerClasspath);
      return javaFxc;
    } catch (Exception e) {
      if (e instanceof MojoExecutionException) throw (MojoExecutionException)e;
      throw loadingTaskFailed(e);
    }
  }

  private static ClassLoader classLoader(Path classpath) throws MalformedURLException {
    URL[] urls = fileUrlsFrom(classpath);
    return new URLClassLoader(urls, currentThread().getContextClassLoader());
  }

  @SuppressWarnings("unchecked")
  private static URL[] fileUrlsFrom(Path classpath) throws MalformedURLException {
    List<URL> urls = new ArrayList<URL>();
    Iterator<FileResource> iterator = classpath.iterator();
    while(iterator.hasNext())
      urls.add(iterator.next().getFile().toURI().toURL());
    return urls.toArray(new URL[urls.size()]);
  }

  private static MojoExecutionException loadingTaskFailed(Exception cause) {
    String msg = "Unable to load JavaFX compiler Ant task. Please make sure javafxc.jar is in the classpath";
    return new MojoExecutionException(msg, cause);
  }

  private void configureCompiler(Javac javaFxc, Path compilerClasspath) throws MojoExecutionException {
    javaFxc.setProject(compilerClasspath.getProject());
    setCompilerClasspath(javaFxc, compilerClasspath);
  }

  private void setCompilerClasspath(Javac javaFxc, Path compilerClasspath) throws MojoExecutionException {
    try {
      method("setCompilerClassPath").withParameterTypes(Path.class).in(javaFxc).invoke(compilerClasspath);
    } catch (ReflectionError e) {
      throw new MojoExecutionException("Unable to set the compiler classpath", e);
    }
  }
}
