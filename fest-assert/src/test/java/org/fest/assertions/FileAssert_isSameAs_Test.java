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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.FileStub.aFile;
import static org.fest.util.Files.temporaryFolder;

import java.io.File;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link FileAssert#isSameAs(java.io.File)}</code>.
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FileAssert_isSameAs_Test extends GenericAssert_isSameAs_TestCase<File> {

  private static File notNullValue;
  private static File notSameValue;

  @BeforeClass
  public static void setUpOnce() {
    notNullValue = temporaryFolder();
    notSameValue = aFile();
  }

  @Override
  protected FileAssert assertionsFor(File actual) {
    return new FileAssert(actual);
  }

  @Override
  protected File notNullValue() {
    return notNullValue;
  }

  @Override
  protected File notSameValue() {
    return notSameValue;
  }
}
