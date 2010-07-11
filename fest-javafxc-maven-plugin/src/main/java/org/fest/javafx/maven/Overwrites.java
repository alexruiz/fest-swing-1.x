/*
 * Created on Jul 11, 2010
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

import static org.fest.util.Strings.concat;

import java.io.File;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.logging.Log;
import org.codehaus.plexus.util.DirectoryScanner;

/**
 * Utility methods related to "overwrites."
 *
 * @author Johannes Schneider
 * @author Alex Ruiz
 */
class Overwrites {

  private final Log log;

  Overwrites(Log log) {
    this.log = log;
  }

  void deleteOverwrites(File outputDirectory, String[] overwrites) throws MojoExecutionException {
    DirectoryScanner scanner = new DirectoryScanner();
    scanner.setBasedir(outputDirectory);
    scanner.setIncludes(overwrites);
    scanner.scan();
    for (String overwrite : scanner.getIncludedFiles())
      deleteOverwrite(outputDirectory, overwrite);
  }

  private void deleteOverwrite(File outputDirectory, String overwrite) throws MojoExecutionException {
    log.info(concat("Overwriting: ", overwrite));
    File targetFile = new File(outputDirectory, overwrite);
    if (!targetFile.exists())
      throw new MojoExecutionException(concat("Overwrite not found <", targetFile.getAbsolutePath(), ">"));
    targetFile.delete();
  }
}
