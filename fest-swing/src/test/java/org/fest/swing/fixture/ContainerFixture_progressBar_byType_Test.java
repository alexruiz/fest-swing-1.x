/*
 * Created on Jan 15, 2010
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
package org.fest.swing.fixture;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import org.fest.swing.exception.ComponentLookupException;
import org.junit.Test;

/**
 * Tests for {@link AbstractContainerFixture#progressBar()}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixture_progressBar_byType_Test extends ContainerFixture_progressBar_TestCase {
  @Test
  public void should_find_visible_JProgressBar() {
    showWindow();
    JProgressBarFixture progressBar = fixture.progressBar();
    assertThatJProgressBarWasFound(progressBar);
  }

  @Test
  public void should_fail_if_visible_JProgressBar_not_found() {
    try {
      fixture.progressBar();
      failWhenExpectingException();
    } catch (ComponentLookupException e) {
      assertThat(e.getMessage()).contains("Unable to find component using matcher").contains(
          "type=javax.swing.JProgressBar, requireShowing=true");
    }
  }
}
