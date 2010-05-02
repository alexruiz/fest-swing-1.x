/*
 * Created on May 1, 2010
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

import static javax.swing.JFileChooser.SAVE_DIALOG;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.fest.swing.edt.GuiQuery;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link CharMappingFileChooser#CharMappingFileChooser(javax.swing.JFileChooser, javax.swing.JFrame)}</code>.
 *
 * @author Alex Ruiz
 */
public class CharMappingFileChooser_constructor_Test extends EDTSafeTestCase {

  private JFileChooser fileChooser;

  @Before
  public void setUp() {
    fileChooser = execute(new GuiQuery<JFileChooser>() {
      @Override protected JFileChooser executeInEDT() {
        return new JFileChooser();
      }
    });
  }

  @Test
  public void should_configure_JFileChooser() {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        new CharMappingFileChooser(fileChooser, new JFrame());
        assertThat(fileChooser.isAcceptAllFileFilterUsed()).isFalse();
        assertThat(fileChooser.getDialogType()).isEqualTo(SAVE_DIALOG);
        assertThat(fileChooser.getFileFilter()).isInstanceOf(TextFileFilter.class);
      }
    });
  }
}
