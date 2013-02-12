/*
 * Created on Nov 17, 2009
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
 * Copyright @2009-2013 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.test.builder.JDialogs.dialog;
import static org.fest.swing.test.task.WindowDestroyTask.hideAndDisposeInEDT;

import java.awt.Dialog;

import org.fest.swing.driver.DialogDriver;
import org.junit.After;
import org.junit.Test;

/**
 * Tests for {@link DialogFixture#requireModal()}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class DialogFixture_requireModal_Test extends ComponentFixture_Implementations_TestCase<Dialog> {
  private DialogDriver driver;
  private Dialog target;
  private DialogFixture fixture;

  @Override
  void onSetUp() {
    driver = createMock(DialogDriver.class);
    target = dialog().createNew();
    fixture = new DialogFixture(robot(), target);
    fixture.driver(driver);
  }

  @After
  public void tearDown() {
    hideAndDisposeInEDT(target);
  }

  @Test
  public void should_require_modal() {
    new EasyMockTemplate(driver) {
      @Override
      protected void expectations() {
        driver.requireModal(target);
        expectLastCall().once();
      }

      @Override
      protected void codeToTest() {
        assertThatReturnsSelf(fixture.requireModal());
      }
    }.run();
  }

  @Override
  DialogDriver driver() {
    return driver;
  }

  @Override
  Dialog target() {
    return target;
  }

  @Override
  DialogFixture fixture() {
    return fixture;
  }
}