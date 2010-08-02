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

import java.io.File;

/**
 * Tests for:
 * <ul>
 * <li><code>{@link FileAssert#as(Description)}</code></li>
 * <li><code>{@link FileAssert#as(String)}</code></li>
 * <li><code>{@link FileAssert#describedAs(Description)}</code></li>
 * <li><code>{@link FileAssert#describedAs(String)}</code></li>
 * </ul>
 *
 * @author David DIDIER
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FileAssert_description_Test extends GenericAssert_description_TestCase<File> {

  @Override protected GenericAssert<File> assertionToTest() {
    return new FileAssert(new File("hello.txt"));
  }
}
