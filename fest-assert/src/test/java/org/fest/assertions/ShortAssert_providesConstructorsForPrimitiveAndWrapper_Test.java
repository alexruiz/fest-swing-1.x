package org.fest.assertions;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ShortAssert_providesConstructorsForPrimitiveAndWrapper_Test implements PrimitiveAssert_providesConstructorsForPrimitiveAndWrapper_TestCase {

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
