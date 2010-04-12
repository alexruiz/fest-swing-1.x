package org.fest.assertions;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class FloatAssert_providesConstructorsForPrimitiveAndWrapper_Test implements
  PrimitiveAssert_providesConstructorsForPrimitiveAndWrapper_TestCase,
  PrimitiveAssert_canBeConstructedFromPrimitiveInt_TestCase {

  private static final float primitiveActual = 2.7182818284590f;

  private static final Float wrappedActual = new Float(primitiveActual);

  @Test
  public void shouldConstructInstanceFromPrimitiveValue() {
    FloatAssert assertion = new FloatAssert(primitiveActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromPrimitiveInt() {
    FloatAssert assertion = new FloatAssert(8);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromWrapperValue() {
    FloatAssert assertion = new FloatAssert(wrappedActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromNull() {
    FloatAssert assertion = new FloatAssert(null);
    assertThat(assertion).isNotNull();
  }
}
