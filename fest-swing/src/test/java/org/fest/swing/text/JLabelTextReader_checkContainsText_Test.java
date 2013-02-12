/*
 * Created on Jul 29, 2010
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
 * Copyright @2010-2013 the original author or authors.
 */
package org.fest.swing.text;

import static org.fest.assertions.Assertions.assertThat;

import javax.swing.JLabel;

import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link JLabelTextReader#checkContainsText(JLabel, String)}.
 * 
 * @author Alex Ruiz
 */
public class JLabelTextReader_checkContainsText_Test extends EDTSafeTestCase {
  private JLabel label;
  private JLabelTextReader reader;

  @Before
  public void setUp() {
    label = createMock(JLabel.class);
    reader = new JLabelTextReader();
  }

  @Test
  public void should_return_false_if_text_in_JLabel_is_null() {
    new EasyMockTemplate(label) {
      @Override
      protected void expectations() {
        expect(label.getText()).andReturn(null);
      }

      @Override
      protected void codeToTest() {
        assertThat(reader.checkContainsText(label, "Yoda")).isFalse();
      }
    }.run();
  }

  @Test
  public void should_return_false_if_text_in_JLabel_does_not_contain_given_String() {
    new EasyMockTemplate(label) {
      @Override
      protected void expectations() {
        expect(label.getText()).andReturn("Leia");
      }

      @Override
      protected void codeToTest() {
        assertThat(reader.checkContainsText(label, "Yoda")).isFalse();
      }
    }.run();
  }

  @Test
  public void should_return_true_if_text_in_JLabel_contains_given_String() {
    new EasyMockTemplate(label) {
      @Override
      protected void expectations() {
        expect(label.getText()).andReturn("Yoda");
      }

      @Override
      protected void codeToTest() {
        assertThat(reader.checkContainsText(label, "Yo")).isTrue();
      }
    }.run();
  }
}
