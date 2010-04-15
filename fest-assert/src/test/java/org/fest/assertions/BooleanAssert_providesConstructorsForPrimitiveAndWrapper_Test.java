package org.fest.assertions;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class BooleanAssert_providesConstructorsForPrimitiveAndWrapper_Test implements PrimitiveAssert_providesConstructorsForPrimitiveAndWrapper_TestCase {

  private static final boolean primitiveActual = true;
  private static final Boolean wrappedActual = new Boolean(primitiveActual);

  @Test
  public void shouldConstructInstanceFromPrimitiveValue() {
    BooleanAssert assertion = new BooleanAssert(primitiveActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromWrapperValue() {
    BooleanAssert assertion = new BooleanAssert(wrappedActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromNull() {
    BooleanAssert assertion = new BooleanAssert(null);
    assertThat(assertion).isNotNull();
  }
}
