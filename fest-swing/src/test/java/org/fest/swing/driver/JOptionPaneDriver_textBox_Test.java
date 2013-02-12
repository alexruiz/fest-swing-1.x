/*
 * Created on Mar 11, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.swing.JOptionPaneLauncher.launch;

import javax.swing.JOptionPane;
import javax.swing.text.JTextComponent;

import org.fest.swing.exception.ComponentLookupException;
import org.junit.Test;

/**
 * Tests for {@link JOptionPaneDriver#textBox(JOptionPane)}.
 * 
 * @author Alex Ruiz
 */
@Deprecated
public class JOptionPaneDriver_textBox_Test extends JOptionPaneDriver_TestCase {
  @Test
  @Deprecated
  public void should_find_text_box() {
    JOptionPane optionPane = inputMessage();
    launch(optionPane, title());
    JTextComponent textBox = driver.textBox(optionPane);
    assertThat(textBox).isNotNull();
  }

  @Test(expected = ComponentLookupException.class)
  @Deprecated
  public void should_throw_error_if_text_box_not_found() {
    JOptionPane optionPane = errorMessage();
    launch(optionPane, title());
    driver.textBox(optionPane);
  }
}
