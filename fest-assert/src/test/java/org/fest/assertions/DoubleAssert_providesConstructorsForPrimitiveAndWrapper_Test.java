package org.fest.assertions;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class DoubleAssert_providesConstructorsForPrimitiveAndWrapper_Test implements
  PrimitiveAssert_providesConstructorsForPrimitiveAndWrapper_TestCase,
  PrimitiveAssert_canBeConstructedFromPrimitiveInt_TestCase {

  private static final double primitiveActual = 8.0d;
  private static final Double wrapperActual = new Double(primitiveActual);

  @Test
  public void shouldConstructInstanceFromPrimitiveValue() {
    DoubleAssert assertion = new DoubleAssert(primitiveActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromPrimitiveInt() {
    DoubleAssert assertion = new DoubleAssert(8);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromWrapperValue() {
    DoubleAssert assertion = new DoubleAssert(wrapperActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromNull() {
    DoubleAssert assertion = new DoubleAssert(null);
    assertThat(assertion).isNotNull();
  }
}
