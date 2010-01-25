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

import static org.fest.javafx.maven.Ant.updatePathWithPaths;
import static org.fest.javafx.maven.Classpaths.JAVAFX_COMPILER_CLASSPATH_FILE_NAMES;
import static org.fest.javafx.maven.Classpaths.JAVAFX_DESKTOP_CLASSPATH_FILE_PATTERNS;
import static org.fest.util.Strings.concat;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.FileSet;
import org.apache.tools.ant.types.Path;

/**
 * Understands creation of classpaths for the JavaFX compiler Ant task.
 *
 * @author Alex Ruiz
 */
class JavaFXClasspathFactory {

  Path createCompilerClasspath(Project project, File javaFXHomeDirectory) {
    FileSet files = new FileSet();
    files.setDir(javaFXHomeDirectory);
    for (String include : JAVAFX_COMPILER_CLASSPATH_FILE_NAMES)
      files.createInclude().setName(concat("**/", include));
    Path path = new Path(project);
    path.addFileset(files);
    return path;
  }

  void setUpClasspath(Path classpath, JavaFXCMojo mojo, File javaFXHomeDirectory) {
    FileSet javaFXFiles = new FileSet();
    javaFXFiles.setDir(javaFXHomeDirectory);
    for (String include : JAVAFX_DESKTOP_CLASSPATH_FILE_PATTERNS)
      javaFXFiles.createInclude().setName(include);
    classpath.addFileset(javaFXFiles);
    updatePathWithPaths(classpath, mojo.compileClasspathElements);
  }
}
