/*
 * Created on Jan 21, 2010
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

import static org.fest.util.Arrays.isEmpty;

import java.io.File;
import java.util.List;

import org.apache.maven.plugin.logging.Log;
import org.apache.maven.project.MavenProject;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.types.Path;
import org.fest.util.Collections;

/**
 * Understands creation/configuration of Ant components from Maven ones.
 *
 * @author Alex Ruiz
 */
class AntAdapter {

  static Project createAntProject(MavenProject mavenProject, Log log) {
    Project antProject = new Project();
    antProject.setBaseDir(mavenProject.getBasedir());
    antProject.addBuildListener(new LoggingBuildListener(log));
    antProject.init();
    return antProject;
  }

  static void updatePathWithFiles(Path path, File... files) {
    if (isEmpty(files)) return;
    for (File f : files) path.createPathElement().setLocation(f);
  }

  static void updatePathWithPaths(Path destination, List<String> source) {
    if (Collections.isEmpty(source)) return;
    for (String s : source) destination.createPathElement().setPath(s);
  }
}
