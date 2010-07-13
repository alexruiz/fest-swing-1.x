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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.FileContentComparator.LineDiff.lineDiff;
import static org.fest.assertions.Resources.file;
import static org.junit.Assert.assertEquals;

import java.util.Collection;

import org.fest.assertions.FileContentComparator.LineDiff;
import org.fest.util.Collections;
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
public class FileContentComparator_compareContents_withDifferentFiles_Test {

  private FileContentComparator comparator;

  private final String actual;
  private final String expected;
  private final LineDiff[] diffs;

  @Parameters
  public static Collection<Object[]> differentFiles() {
    return Collections.list(new Object[][] {
        { "fileAssertTest2.txt", "fileAssertTest3.txt",
          diffs(lineDiff(1, "abcde fghij abcde fghij", "abcde fghij abcde fghij z")) },
        { "fileAssertTest1.txt", "fileAssertTest2.txt",
          diffs(lineDiff(0, "this file is 22 bytes.", "abcde fghij"), lineDiff(0, "EOF", "abcde fghij")) },
        { "fileAssertTest2.txt", "fileAssertTest1.txt",
          diffs(lineDiff(0, "abcde fghij", "this file is 22 bytes."), lineDiff(0, "abcde fghij", "EOF")) }
    });
  }

  private static LineDiff[] diffs(LineDiff...diffs) {
    return diffs;
  }

  public FileContentComparator_compareContents_withDifferentFiles_Test(String actual, String expected, LineDiff[] diffs) {
    this.actual = actual;
    this.expected = expected;
    this.diffs = diffs;
  }

  @Before
  public void setUp() {
    comparator = new FileContentComparator();
  }

  @Test
  public void shouldReturnDiffsForNotEqualFiles() throws Exception {
    LineDiff[] actualDiffs = comparator.compareContents(file(actual), file(expected));
    verifyIfEqual(actualDiffs, diffs);
  }

  private static void verifyIfEqual(LineDiff[] actual, LineDiff[] expected) {
    int expectedSize = expected.length;
    assertEquals("diff count", expectedSize, actual.length);
    for (int i = 0; i < expectedSize; i++)
      assertEquals(expected[i], actual[i]);
  }
}
