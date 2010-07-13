/*
 * Created on Feb 15, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Resources.file;
import static org.fest.util.Collections.list;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.fest.assertions.FileContentComparator.LineDiff;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.*;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link FileContentComparator#compareContents(java.io.File, java.io.File)}</code>.
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class FileContentComparator_compareContents_Test {

  private FileContentComparator comparator;

  private final String fileName;

  @Parameters
  public static Collection<Object[]> files() {
    return list(new Object[][] {
        { "fileAssertTest2.txt" },
        { "fileAssertTest3.txt" },
        { "fileAssertTest4.txt" },
        { "fileAssertTest5.txt" },
    });
  }

  public FileContentComparator_compareContents_Test(String fileName) {
    this.fileName = fileName;
  }

  @Before
  public void setUp() {
    comparator = new FileContentComparator();
  }

  @Test
  public void should_not_return_any_diffs_for_equal_files() throws Exception {
    LineDiff[] diffs = comparator.compareContents(file(fileName), file(fileName));
    assertEquals(0, diffs.length);
  }
}
