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

import static org.fest.assertions.Assertions.assertThat;

import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link SystemProperties#get(String)}</code> and <code>{@link SystemProperties#set(String, String)}</code>.
 *
 * @author Alex Ruiz
 */
public class SystemProperties_get_set_Test {

  private static SystemProperties systemProperties;

  @BeforeClass
  public static void setUpOnce() {
    systemProperties = new SystemProperties();
  }

  @Test
  public void should_set_system_properties() {
    systemProperties.set("test", "Hello");
    assertThat(System.getProperty("test")).isEqualTo("Hello");
  }

  @Test
  public void should_get_system_properties() {
    System.setProperty("test", "Hello");
    assertThat(systemProperties.get("test")).isEqualTo("Hello");
  }
}
