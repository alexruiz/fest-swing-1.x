/*
 * Created on Feb 26, 2008
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
package org.fest.swing.driver;

import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import javax.swing.JFileChooser;

import org.fest.swing.core.Robot;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link JFileChooserDriver#selectFile(JFileChooser, java.io.File)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JFileChooserDriver_selectFile_withInvalidInput_Test {

  private JFileChooser fileChooser;
  private JFileChooserDriver driver;

  @Before public void setUp() {
    fileChooser = createMock(JFileChooser.class);
    driver = new JFileChooserDriver(createMock(Robot.class));
  }

  @Test
  public void should_throw_error_if_file_is_null() {
    try {
      driver.selectFile(fileChooser, null);
      failWhenExpectingException();
    } catch (NullPointerException e) {
      assertThat(e.getMessage()).isEqualTo("The file to select should not be null");
    }
  }
}
