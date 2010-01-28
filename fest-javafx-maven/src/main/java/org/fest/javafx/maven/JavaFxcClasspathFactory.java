/*
 * Created on Jan 24, 2010
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

import java.io.File;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

/**
 * Understands creation of classpaths for the JavaFX compiler Ant task.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
class JavaFxcClasspathFactory {

  private static final String[] JAVAFX_COMPILER_CLASSPATH_FILES = { "**/javafxrt.jar", "**/javafxc.jar", "**/toolchain.jar" };

  Path createCompilerClasspath(File javaFxHomeDirectory) {
    FileSet files = new FileSet();
    files.setDir(javaFxHomeDirectory);
    for (String include : JAVAFX_COMPILER_CLASSPATH_FILES)
      files.createInclude().setName(include);
    Path path = new Path(new Project());
    path.addFileset(files);
    return path;
  }
}
