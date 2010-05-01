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

import org.fest.mocks.EasyMockTemplate;
import org.junit.*;

/**
 * Tests for <code>{@link Messages#hyperlink(Platform, String)}</code>.
 *
 * @author Alex Ruiz
 */
public class Messages_hyperlink_Test {

  private Platform platform;
  private static String url;
  private static Messages messages;

  @BeforeClass
  public static void setUpOnce() {
    url = "http://fest.easytesting.org";
    messages = new Messages();
  }

  @Before
  public void setUp() {
    platform = createMock(Platform.class);
  }

  @Test
  public void should_return_hyperlink_if_browsing_is_supported() {
    new EasyMockTemplate(platform) {
      @Override protected void expectations() {
        expect(platform.isBrowsingSupported()).andReturn(true);
      }

      @Override protected void codeToTest() {
        String msg = messages.hyperlink(platform, url);
        assertThat(msg).isEqualTo("<a href=\"http://fest.easytesting.org\">http://fest.easytesting.org</a>");
      }
    }.run();
  }

  @Test
  public void should_return_just_URL_if_browsing_is_not_supported() {
    new EasyMockTemplate(platform) {
      @Override protected void expectations() {
        expect(platform.isBrowsingSupported()).andReturn(false);
      }

      @Override protected void codeToTest() {
        String msg = messages.hyperlink(platform, url);
        assertThat(msg).isEqualTo("http://fest.easytesting.org");
      }
    }.run();
  }
}
