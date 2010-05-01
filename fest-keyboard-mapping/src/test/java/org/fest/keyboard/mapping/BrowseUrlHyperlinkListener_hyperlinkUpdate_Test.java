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

import static javax.swing.event.HyperlinkEvent.EventType.ACTIVATED;
import static javax.swing.event.HyperlinkEvent.EventType.EXITED;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.classextension.EasyMock.createMock;
import static org.fest.keyboard.mapping.URLs.FestURL;
import static org.fest.swing.timing.Pause.pause;

import java.net.URL;

import javax.swing.event.HyperlinkEvent;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link BrowseUrlHyperlinkListener#hyperlinkUpdate(javax.swing.event.HyperlinkEvent)}</code>.
 *
 * @author Alex Ruiz
 */
public class BrowseUrlHyperlinkListener_hyperlinkUpdate_Test {

  private Platform platform;
  private URL url;
  private BrowseUrlHyperlinkListener listener;

  @Before
  public void setUp() {
    platform = createMock(Platform.class);
    url = FestURL();
    listener = new BrowseUrlHyperlinkListener(platform);
  }

  @Test
  public void should_browse_URL_if_hyperlink_was_activated() {
    final HyperlinkEvent e = new HyperlinkEvent(this, ACTIVATED, url);
    new EasyMockTemplate(platform) {
      @Override protected void expectations() throws Exception {
        platform.browse(url);
        expectLastCall();
      }

      @Override protected void codeToTest() {
        listener.hyperlinkUpdate(e);
        pause(1000); // wait a little bit, to give time for the URL to be browsed in a separate thread.
      }
    }.run();
  }

  @Test
  public void should_not_browse_URL_if_hyperlink_was_not_activated() {
    final HyperlinkEvent e = new HyperlinkEvent(this, EXITED, url);
    new EasyMockTemplate(platform) {
      @Override protected void expectations() throws Exception {
        // platform.browse should not be called
      }

      @Override protected void codeToTest() {
        listener.hyperlinkUpdate(e);
      }
    }.run();
  }
}
