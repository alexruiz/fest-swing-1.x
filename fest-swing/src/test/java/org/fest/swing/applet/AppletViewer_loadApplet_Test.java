/*
 * Created on Jul 10, 2008
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
package org.fest.swing.applet;

import static javax.swing.SwingUtilities.getAncestorOfClass;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Container;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.swing.TestApplet;
import org.junit.Test;

/**
 * Tests for <code>{@link AppletViewer#display()}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class AppletViewer_loadApplet_Test extends AppletViewer_TestCase {

  @Test 
  public void should_load_applet_when_created() {
    assertThatAppletIsShowingAndViewerIsLoaded();
    Container ancestor = getAncestorOfClass(AppletViewer.Viewer.class, applet);
    assertThat(ancestor).isSameAs(viewer.window());
    fixture.label("status").requireText("Applet loaded");
    assertThat(viewer.applet()).isSameAs(applet);
    assertThat(viewer.stub()).isInstanceOf(BasicAppletStub.class);
  }

  @RunsInEDT
  AppletViewer createViewer() {
    return viewerFor(applet);
  }

  @RunsInEDT
  private static AppletViewer viewerFor(final TestApplet applet) {
    return execute(new GuiQuery<AppletViewer>() {
      protected AppletViewer executeInEDT() {
        return new AppletViewer(applet);
      }
    });
  }
}
