/*
 * Created on Dec 23, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.FileStub.newFile;
import static org.fest.util.Files.temporaryFolder;

import java.io.File;

/**
 * Tests for <code>{@link FileAssert#isEqualTo(java.io.File)}</code>.
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FileAssert_isEqualTo_Test extends GenericAssert_isEqualTo_TestBase<File> {

  protected FileAssert assertObject() {
    return new FileAssert(temporaryFolder());
  }

  protected FileAssert assertObjectWithNullTarget() {
    return new FileAssert(null);
  }

  protected File notEqualValue() {
    return newFile("c:\\f.txt");
  }
}
