/*
 * Created on Sep 30, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.assertions;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

/**
 * Test cases for implementations of:
 * <ul>
 * <li><code>{@link PrimitiveAssert#as(Description)}</code></li>
 * <li><code>{@link PrimitiveAssert#as(String)}</code></li>
 * <li><code>{@link PrimitiveAssert#describedAs(Description)}</code></li>
 * <li><code>{@link PrimitiveAssert#describedAs(String)}</code></li>
 * </ul>
 *
 * @author Alex Ruiz
 */
public abstract class PrimitiveAssert_description_TestCase {

  private PrimitiveAssert assertion;

  @Before
  public final void setUp() {
    assertion = assertionToTest();
    assertNull(assertion.description());
  }

  protected abstract PrimitiveAssert assertionToTest();

  @Test
  public void should_set_description_as_text() {
    assertion.as("A Test");
    assertEquals("A Test", assertion.description());
  }

  @Test
  public void should_set_description_as_text_safely_for_Groovy() {
    assertion.describedAs("A Test");
    assertEquals("A Test", assertion.description());
  }

  @Test
  public void should_set_description() {
    assertion.as(new BasicDescription("A Test"));
    assertEquals("A Test", assertion.description());
  }

  @Test
  public void should_set_description_safely_for_Groovy() {
    assertion.describedAs(new BasicDescription("A Test"));
    assertEquals("A Test", assertion.description());
  }
}
