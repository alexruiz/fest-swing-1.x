package org.fest.assertions;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class ByteAssert_providesConstructorsForPrimitiveAndWrapper_Test implements PrimitiveAssert_providesConstructorsForPrimitiveAndWrapper_TestCase {

  private static final byte primitiveActual = 123;

  private static final Byte wrappedActual = new Byte(primitiveActual);

  @Test
  public void shouldConstructInstanceFromPrimitiveValue() {
    ByteAssert assertion = new ByteAssert(primitiveActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromWrapperValue() {
    ByteAssert assertion = new ByteAssert(wrappedActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromNull() {
    ByteAssert assertion = new ByteAssert(null);
    assertThat(assertion).isNotNull();
  }
}
