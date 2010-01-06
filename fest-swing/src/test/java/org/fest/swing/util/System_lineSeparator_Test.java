/*
 * Created on May 1, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.util;

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link System#lineSeparator(SystemPropertyReader)}</code>.
 *
 * @author Alex Ruiz
 */
public class System_lineSeparator_Test {

  @Test
  public void should_return_line_separator_from_system() {
    assertThat(System.LINE_SEPARATOR).isEqualTo(java.lang.System.getProperty("line.separator"));
  }

  @Test
  public void should_return_new_line_as_line_separator_if_cannot_get_it_from_system() {
    final SystemPropertyReader reader = createMock(SystemPropertyReader.class);
    new EasyMockTemplate(reader) {
      protected void expectations() {
        expect(reader.systemProperty("line.separator")).andThrow(new RuntimeException("On purpose"));
      }

      protected void codeToTest() {
        assertThat(System.lineSeparator(reader)).isEqualTo("\n");
      }
    }.run();
  }
}
