/*
 * Created on Feb 10, 2008
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
 * Copyright @2008 the original author or authors.
 */
package org.fest.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Tests for <code>{@link Systems#LINE_SEPARATOR}</code>.
 *
 * @author Yvonne Wang
 */
public class Systems_lineSeparator_Test {

  @Test
  public void should_return_line_separator() {
    assertEquals(java.lang.System.getProperty("line.separator"), Systems.LINE_SEPARATOR);
  }
}
