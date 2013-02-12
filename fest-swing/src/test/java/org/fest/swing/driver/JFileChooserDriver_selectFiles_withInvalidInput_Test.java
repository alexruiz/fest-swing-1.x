/*
 * Created on Feb 26, 2008
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
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.util.Arrays.array;

import java.io.File;

import javax.swing.JFileChooser;

import org.junit.Test;

/**
 * Tests for {@link JFileChooserDriver#selectFiles(JFileChooser, java.io.File[])}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JFileChooserDriver_selectFiles_withInvalidInput_Test extends JFileChooserDriver_withMocks_TestCase {
  @Test
  public void should_throw_error_if_array_of_files_is_null() {
    try {
      driver.selectFiles(fileChooser, null);
      failWhenExpectingException();
    } catch (NullPointerException e) {
      assertThat(e.getMessage()).isEqualTo("The files to select should not be null");
    }
  }

  @Test
  public void should_throw_error_if_array_of_files_is_empty() {
    try {
      driver.selectFiles(fileChooser, new File[0]);
      failWhenExpectingException();
    } catch (IllegalArgumentException e) {
      assertThat(e.getMessage()).isEqualTo("The array of files to select should not be empty");
    }
  }

  @Test
  public void should_throw_error_if__any_file_is_null() {
    try {
      driver.selectFiles(fileChooser, array(new File("fake"), null));
      failWhenExpectingException();
    } catch (NullPointerException e) {
      assertThat(e.getMessage()).isEqualTo("The array of files to select should not contain null elements");
    }
  }
}
