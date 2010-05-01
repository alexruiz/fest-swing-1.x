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

import static java.awt.Desktop.Action.BROWSE;
import static org.easymock.EasyMock.expect;
import static org.fest.assertions.Assertions.assertThat;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link Platform#isBrowsingSupported()}</code>.
 *
 * @author Alex Ruiz
 */
public class Platform_isBrowsingSupported_Test extends Platform_TestCase {

  @Test
  public void should_return_true_if_desktop_is_supported_and_desktop_supports_browsing() {
    new EasyMockTemplate(provider, desktop) {
      @Override protected void expectations() {
        expect(provider.supportsDesktop()).andReturn(true);
        expect(provider.desktop()).andReturn(desktop);
        expect(desktop.isSupported(BROWSE)).andReturn(true);
      }

      @Override protected void codeToTest() {
        assertThat(platform.isBrowsingSupported()).isEqualTo(true);
      }
    };
  }

  @Test
  public void should_return_false_if_desktop_is_not_supported() {
    new EasyMockTemplate(provider, desktop) {
      @Override protected void expectations() {
        expect(provider.supportsDesktop()).andReturn(false);
      }

      @Override protected void codeToTest() {
        assertThat(platform.isBrowsingSupported()).isEqualTo(false);
      }
    };
  }

  @Test
  public void should_return_false_if_desktop_is_supported_and_desktop_does_not_support_browsing() {
    new EasyMockTemplate(provider, desktop) {
      @Override protected void expectations() {
        expect(provider.supportsDesktop()).andReturn(true);
        expect(provider.desktop()).andReturn(desktop);
        expect(desktop.isSupported(BROWSE)).andReturn(false);
      }

      @Override protected void codeToTest() {
        assertThat(platform.isBrowsingSupported()).isEqualTo(false);
      }
    };
  }
}
