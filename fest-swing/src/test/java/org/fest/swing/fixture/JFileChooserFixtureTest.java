/*
 * Created on Jul 9, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.builder.JButtons.button;
import static org.fest.swing.test.builder.JTextFields.textField;
import static org.fest.util.Arrays.array;

import java.io.File;

import javax.swing.JButton;
import javax.swing.JTextField;

import org.junit.Test;

/**
 * Tests for {@link JFileChooserFixture}.
 * 
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class JFileChooserFixtureTest extends JFileChooserFixture_TestCase {
  // TODO Reorganize into smaller units

  @Test
  public void shouldReturnFileNameTextBox() {
    final JTextField fileNameTextBox = textField().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().fileNameTextBox(target())).andReturn(fileNameTextBox);
      }

      @Override
      protected void codeToTest() {
        JTextComponentFixture result = fixture().fileNameTextBox();
        assertThat(result.target()).isSameAs(fileNameTextBox);
      }
    }.run();
  }

  @Test
  public void shouldClickApproveButton() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().clickApproveButton(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        fixture().approve();
      }
    }.run();
  }

  @Test
  public void shouldReturnApproveButton() {
    final JButton approveButton = button().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().approveButton(target())).andReturn(approveButton);
      }

      @Override
      protected void codeToTest() {
        JButtonFixture result = fixture().approveButton();
        assertThat(result.target()).isSameAs(approveButton);
      }
    }.run();
  }

  @Test
  public void shouldClickCancelButton() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().clickCancelButton(target());
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        fixture().cancel();
      }
    }.run();
  }

  @Test
  public void shouldReturnCancelButton() {
    final JButton cancelButton = button().createNew();
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().cancelButton(target())).andReturn(cancelButton);
      }

      @Override
      protected void codeToTest() {
        JButtonFixture result = fixture().cancelButton();
        assertThat(result.target()).isSameAs(cancelButton);
      }
    }.run();
  }

  @Test
  public void shouldSelectFile() {
    final File file = new File("fake");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectFile(target(), file);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectFile(file));
      }
    }.run();
  }

  @Test
  public void shouldSelectFiles() {
    final File[] files = array(new File("Fake1"), new File("Fake2"));
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().selectFiles(target(), files);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().selectFiles(files));
      }
    }.run();
  }

  @Test
  public void shouldSetCurrentDirectory() {
    final File file = new File("fake");
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        driver().setCurrentDirectory(target(), file);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture().setCurrentDirectory(file));
      }
    }.run();
  }
}
