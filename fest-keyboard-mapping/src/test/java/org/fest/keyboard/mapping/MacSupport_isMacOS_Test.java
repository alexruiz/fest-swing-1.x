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
import static org.fest.assertions.Assertions.assertThat;

import org.fest.mocks.EasyMockTemplate;
import org.junit.Test;

/**
 * Tests for <code>{@link MacSupport#isMacOS()}</code>.
 *
 * @author Alex Ruiz
 */
public class MacSupport_isMacOS_Test extends MacSupport_TestCase {

  @Test
  public void should_return_true_if_OS_name_starts_with_Mac() {
    new EasyMockTemplate(systemProperties) {
      @Override protected void expectations() {
        expect(systemProperties.get("os.name")).andReturn("Mac");
      }

      @Override protected void codeToTest() {
        assertThat(macSupport.isMacOS()).isTrue();
      }
    };
  }

  @Test
  public void should_return_true_if_MRJ_version_is_not_null() {
    new EasyMockTemplate(systemProperties) {
      @Override protected void expectations() {
        expect(systemProperties.get("os.name")).andReturn("MAC");
        expect(systemProperties.get("mrj.version")).andReturn("something");
      }

      @Override protected void codeToTest() {
        assertThat(macSupport.isMacOS()).isTrue();
      }
    };
  }
}
