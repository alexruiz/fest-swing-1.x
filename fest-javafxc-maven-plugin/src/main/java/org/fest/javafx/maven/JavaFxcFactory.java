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

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.Path;
import org.fest.reflect.exception.ReflectionError;

import java.io.File;
import java.net.MalformedURLException;

import static org.fest.reflect.core.Reflection.method;

/**
 * Understands creation of new instances of the JavaFX compiler Ant task.
 *
 * @author Alex Ruiz
 */
class JavaFxcFactory {

  final JavaFxcClasspathFactory classpathFactory;
  private final JavaFxcInstantiator instantiator;

  JavaFxcFactory() {
    this(new JavaFxcClasspathFactory(), new JavaFxcInstantiator());
  }

  JavaFxcFactory(JavaFxcClasspathFactory classpathFactory, JavaFxcInstantiator instantiator) {
    this.classpathFactory = classpathFactory;
    this.instantiator = instantiator;
  }

  Javac createJavaFxc(File javaFxHome,boolean automaticallyAddFxJars) throws MojoExecutionException {
      try {
        Path compilerClasspath;
        if ( automaticallyAddFxJars ) {
          compilerClasspath = classpathFactory.createCompilerClasspath( javaFxHome );
        }else {
          //TODO This does not work! There are some class not found exceptions then!
          //It is necessary to add the dependencies to the JavaFX toolchain in some other way...
//          compilerClasspath = new Path( new Project() ); //Empty
          compilerClasspath = classpathFactory.createCompilerClasspath( javaFxHome );
        }
        Javac javaFxc = instantiator.instantiateJavaFxc( compilerClasspath );
        configureCompiler( javaFxc, compilerClasspath );
        return javaFxc;
      } catch (BuildException e) {
        throw loadingTaskFailed(e);
      } catch (MalformedURLException e) {
        throw loadingTaskFailed(e);
      } catch (ClassNotFoundException e) {
        throw loadingTaskFailed(e);
      } catch (InstantiationException e) {
        throw loadingTaskFailed(e);
      } catch (IllegalAccessException e) {
        throw loadingTaskFailed(e);
      }
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
