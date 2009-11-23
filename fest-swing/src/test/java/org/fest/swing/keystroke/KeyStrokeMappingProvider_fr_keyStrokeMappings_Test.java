/*
 * Created on Nov 16, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.keystroke;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for <code>{@link KeyStrokeMappingProvider_fr#keyStrokeMappings()}</code>.
 *
 * @author Alex Ruiz
 */
public class KeyStrokeMappingProvider_fr_keyStrokeMappings_Test {

  @Test
  public void should_return_empty_mapping() {
    KeyStrokeMappingProvider_fr provider = new KeyStrokeMappingProvider_fr();
    assertThat(provider.keyStrokeMappings()).isEmpty();
  }
}
