/*
 * Created on Apr 12, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Assertions.assertThat;

import org.junit.Test;

/**
 * Tests for <code>{@link ShortAssert#ShortAssert(short)}</code>.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public class ShortAssert_providesConstructorsForPrimitiveAndWrapper_Test implements
    PrimitiveAssert_providesConstructorsForPrimitiveAndWrapper_TestCase {

  // TODO split

  private static final short primitiveActual = 42;
  private static final Short wrappedActual = new Short(primitiveActual);

  @Test
  public void shouldConstructInstanceFromPrimitiveValue() {
    ShortAssert assertion = new ShortAssert(primitiveActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromWrapperValue() {
    ShortAssert assertion = new ShortAssert(wrappedActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromNull() {
    ShortAssert assertion = new ShortAssert(null);
    assertThat(assertion).isNotNull();
  }
}
