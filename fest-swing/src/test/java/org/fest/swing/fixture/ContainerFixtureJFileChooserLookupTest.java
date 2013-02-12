/*
 * Created on May 20, 2009
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

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;
import static org.fest.swing.timing.Timeout.timeout;
import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import javax.swing.JFileChooser;

import org.fest.swing.core.GenericTypeMatcher;
import org.fest.swing.exception.WaitTimedOutError;
import org.fest.swing.finder.WindowFinder_TestCase;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.JFileChooserLauncherWindow;
import org.junit.Test;

/**
 * Tests lookup of {@code JFileChooser}s in {@link AbstractContainerFixture}.
 * 
 * @author Alex Ruiz
 */
public class ContainerFixtureJFileChooserLookupTest extends RobotBasedTestCase {
  private ConcreteContainerFixture fixture;
  private JFileChooserLauncherWindow window;

  @Override
  protected final void onSetUp() {
    window = JFileChooserLauncherWindow.createNew(WindowFinder_TestCase.class);
    fixture = new ConcreteContainerFixture(robot, window);
    robot.showWindow(window);
  }

  @Test
  public void shouldFindJFileChooserByType() {
    launchJFileChooser(0);
    JFileChooserFixture fileChooser = fixture.fileChooser();
    assertCorrectJFileChooserWasFound(fileChooser);
  }

  @Test
  public void shouldFindJFileChooserByTypeUsingTimeout() {
    launchJFileChooser(200);
    JFileChooserFixture fileChooser = fixture.fileChooser(timeout(300));
    assertCorrectJFileChooserWasFound(fileChooser);
  }

  @Test
  public void shouldFailIfJFileChooserNotFoundAfterTimeout() {
    try {
      fixture.fileChooser(timeout(100));
      failWhenExpectingException();
    } catch (WaitTimedOutError e) {
      assertErrorMessageIsCorrect(e);
    }
  }

  @Test
  public void shouldFindJFileChooserWithMatcher() {
    launchJFileChooser(0);
    GenericTypeMatcher<JFileChooser> matcher = new JFileChooserByTitleMatcher();
    JFileChooserFixture fileChooser = fixture.fileChooser(matcher);
    assertCorrectJFileChooserWasFound(fileChooser);
  }

  @Test
  public void shouldFindJFileChooserWithMatcherUsingTimeout() {
    launchJFileChooser(200);
    JFileChooserFixture fileChooser = fixture.fileChooser(new JFileChooserByTitleMatcher(), timeout(300));
    assertCorrectJFileChooserWasFound(fileChooser);
  }

  @Test
  public void shouldFailIfJFileChooserNotFoundWithMatcherAfterTimeout() {
    try {
      fixture.fileChooser(new JFileChooserByTitleMatcher(), timeout(300));
      failWhenExpectingException();
    } catch (WaitTimedOutError e) {
      assertErrorMessageIsCorrect(e);
    }
  }

  @Test
  public void shouldFindJFileChooserByName() {
    launchJFileChooser(0);
    JFileChooserFixture fileChooser = fixture.fileChooser("fileChooser");
    assertCorrectJFileChooserWasFound(fileChooser);
  }

  @Test
  public void shouldFindJFileChooserByNameUsingTimeout() {
    launchJFileChooser(200);
    JFileChooserFixture fileChooser = fixture.fileChooser("fileChooser", timeout(300));
    assertCorrectJFileChooserWasFound(fileChooser);
  }

  private void assertCorrectJFileChooserWasFound(JFileChooserFixture fileChooser) {
    assertThat(fileChooser.target()).isSameAs(window.fileChooser());
  }

  @Test
  public void shouldFailIfJFileChooserNotFoundByNameAfterTimeout() {
    try {
      fixture.fileChooser("fileChooser", timeout(300));
      failWhenExpectingException();
    } catch (WaitTimedOutError e) {
      assertErrorMessageIsCorrect(e);
    }
  }

  private void assertErrorMessageIsCorrect(WaitTimedOutError e) {
    assertThat(e.getMessage()).contains("Timed out waiting for file chooser to be found");
  }

  private void launchJFileChooser(int delay) {
    window.launchDelay(delay);
    fixture.button("browse").click();
  }

  private static class JFileChooserByTitleMatcher extends GenericTypeMatcher<JFileChooser> {
    private static final String TITLE = "Launched JFileChooser";

    private JFileChooserByTitleMatcher() {
      super(JFileChooser.class);
    }

    @Override
    protected boolean isMatching(JFileChooser fileChooser) {
      return TITLE.equals(fileChooser.getDialogTitle());
    }

    @Override
    public String toString() {
      return concat("file chooser with title ", quote(TITLE));
    }
  }
}
