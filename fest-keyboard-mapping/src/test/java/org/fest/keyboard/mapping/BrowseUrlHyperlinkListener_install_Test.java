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

import static org.easymock.EasyMock.expect;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkListener;

import org.fest.mocks.EasyMockTemplate;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.EDTSafeTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link BrowseUrlHyperlinkListener#install(javax.swing.JEditorPane, Platform)}</code>.
 *
 * @author Alex Ruiz
 */
public class BrowseUrlHyperlinkListener_install_Test extends EDTSafeTestCase {

  private JEditorPane editor;
  private Platform platform;

  @Before
  public void setUp() {
    editor = execute(new GuiQuery<JEditorPane>() {
      @Override protected JEditorPane executeInEDT() {
        return new JEditorPane();
      }
    });
    platform = createMock(Platform.class);
  }

  @Test
  public void should_install_listener_if_browsing_is_supported() {
    new EasyMockTemplate(platform) {
      @Override protected void expectations() {
        expect(platform.isBrowsingSupported()).andReturn(true);
      }

      @Override protected void codeToTest() {
        BrowseUrlHyperlinkListener.install(editor, platform);
        HyperlinkListener[] hyperlinkListeners = editor.getHyperlinkListeners();
        assertThat(hyperlinkListeners).hasSize(1);
        assertThat(hyperlinkListeners[0]).isInstanceOf(BrowseUrlHyperlinkListener.class);
      }
    }.run();
  }

  @Test
  public void should_not_install_listener_if_browsing_is_not_supported() {
    new EasyMockTemplate(platform) {
      @Override protected void expectations() {
        expect(platform.isBrowsingSupported()).andReturn(false);
      }

      @Override protected void codeToTest() {
        BrowseUrlHyperlinkListener.install(editor, platform);
        assertThat(editor.getHyperlinkListeners()).isEmpty();
      }
    }.run();
  }
}
