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

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.util.Files.temporaryFolder;

import java.io.File;

import org.apache.tools.ant.types.Path;
import org.apache.tools.ant.types.Path.PathElement;
import org.fest.mocks.EasyMockTemplate;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link Ant#updatePathWithFiles(Path, File...)}</code>.
 *
 * @author Alex Ruiz
 */
public class Ant_updatePathWithFiles_Test {

  private Path path;
  private File file;

  @Before
  public void setUp() {
    path = createMock(Path.class);
    file = temporaryFolder();
  }

  @Test
  public void should_update_path_with_given_files() {
    final PathElement element = createMock(PathElement.class);
    new EasyMockTemplate(path, element) {
      protected void expectations() {
        expect(path.createPathElement()).andReturn(element);
        element.setLocation(file);
        expectLastCall().once();
      }

      protected void codeToTest() {
        Ant.updatePathWithFiles(path, file);
      }
    }.run();
  }

  @Test
  public void should_not_update_path_if_given_array_of_files_is_null() {
    new EasyMockTemplate(path) {
      protected void expectations() {
        // path should not be updated.
      }

      protected void codeToTest() {
        File[] files = null;
        Ant.updatePathWithFiles(path, files);
      }
    }.run();
  }

  @Test
  public void should_not_update_path_if_given_array_of_files_is_empty() {
    new EasyMockTemplate(path) {
      protected void expectations() {
        // path should not be updated.
      }

      protected void codeToTest() {
        Ant.updatePathWithFiles(path, new File[0]);
      }
    }.run();
  }
}
