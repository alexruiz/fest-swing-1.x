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

import static org.fest.assertions.Assertions.assertThat;

import javax.swing.JComponent;

import org.fest.swing.driver.JComponentDriver;
import org.junit.Test;

/**
 * Understands test methods for implementations of {@link ClientPropertyStorageFixture}.
 * 
 * @param <T> the type of component supported by the fixture to test.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public abstract class ClientPropertyStorageFixture_TestCase<T extends JComponent> extends
ComponentFixture_Implementations_TestCase<T> {
  @Override
  abstract JComponentDriver driver();

  @Override
  abstract ClientPropertyStorageFixture fixture();

  @Test
  public final void shouldReturnClientProperty() {
    new EasyMockTemplate(driver()) {
      @Override
      protected void expectations() {
        expect(driver().clientProperty(target(), "name")).andReturn("Luke");
      }

      @Override
      protected void codeToTest() {
        assertThat(fixture().clientProperty("name")).isEqualTo("Luke");
      }
    }.run();
  }
}
