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

import java.net.MalformedURLException;

import org.apache.tools.ant.taskdefs.Javac;
import org.apache.tools.ant.types.Path;

/**
 * Understands creation of instances of the JavaFX compiler Ant task.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
class JavaFxcInstantiator {

  private static final String JAVAFX_COMPILER_ANT_TASK_CLASS = "com.sun.tools.javafx.ant.JavaFxAntTask";

  private final JavaFxcClassLoaderFactory classLoaderFactory;

  JavaFxcInstantiator() {
    classLoaderFactory = new JavaFxcClassLoaderFactory();
  }

  Javac instantiateJavaFxc(Path path) throws MalformedURLException, ClassNotFoundException, InstantiationException,
      IllegalAccessException {
    ClassLoader classLoader = classLoaderFactory.createClassLoader(path);
    Class<?> javaFxcType = Class.forName(JAVAFX_COMPILER_ANT_TASK_CLASS, true, classLoader);
    return (Javac) javaFxcType.newInstance();
  }
}
