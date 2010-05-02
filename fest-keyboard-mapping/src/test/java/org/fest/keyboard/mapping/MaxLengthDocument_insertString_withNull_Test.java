/*
 * Created on May 2, 2010
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
package org.fest.keyboard.mapping;

import static org.easymock.classextension.EasyMock.createMock;

import javax.swing.text.AttributeSet;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link MaxLengthDocument#insertString(int, String, javax.swing.text.AttributeSet)}</code>.
 *
 * @author Alex Ruiz
 */
public class MaxLengthDocument_insertString_withNull_Test {

  private MaxLengthDocument document;

  @Before
  public void setUp() {
    document = new MaxLengthDocument();
  }

  @Test
  public void should_not_throw_error_if_text_is_null() throws Exception {
    document.insertString(0, null, createMock(AttributeSet.class));
  }
}
