/*
 * Created on Aug 3, 2009
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

import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.timing.Timeout.timeout;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.timing.Timeout;
import org.junit.Test;

/**
 * Tests for {@link AbstractContainerFixture#dialog(GenericTypeMatcher, Timeout)}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixture_dialog_withMatcherAndTimeout_Test extends ContainerFixture_dialog_TestCase {
  @Test
  public void should_find_visible_Dialog() {
    launchDialogAfterWaitingFor(200);
    DialogFixture dialog = fixture.dialog(new DialogByTitleMatcher(), timeout(300));
    assertThatDialogWasFound(dialog);
  }

  @Test
  public void should_fail_if_visible_Dialog_not_found() {
    try {
      fixture.dialog(new DialogByTitleMatcher(), timeout(100));
      failWhenExpectingException();
    } catch (WaitTimedOutError e) {
      assertThatErrorMessageIsCorrect(e);
    }
  }
}
