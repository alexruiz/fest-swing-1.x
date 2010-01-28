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

import static java.lang.Thread.currentThread;

import java.net.*;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.*;

import org.apache.tools.ant.taskdefs.Classloader;
import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.resources.FileResource;

/**
 * Understands creation of a <code>{@link Classloader}</code> that loads the JavaFX compiler-related classes.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
class JavaFxcClassLoaderFactory {

  ClassLoader createClassLoader(Path classpath) throws MalformedURLException {
    final URL[] urls = fileUrlsFrom(classpath);
    return AccessController.doPrivileged(new PrivilegedAction<ClassLoader>() {
      public ClassLoader run() {
        return new URLClassLoader(urls, currentThread().getContextClassLoader());
      }
    });
  }

  @SuppressWarnings("unchecked")
  private static URL[] fileUrlsFrom(Path classpath) throws MalformedURLException {
    List<URL> urls = new ArrayList<URL>();
    Iterator<FileResource> iterator = classpath.iterator();
    while(iterator.hasNext())
      urls.add(iterator.next().getFile().toURI().toURL());
    return urls.toArray(new URL[urls.size()]);
  }
}
