package org.fest.assertions;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class IntAssert_providesConstructorsForPrimitiveAndWrapper_Test implements PrimitiveAssert_providesConstructorsForPrimitiveAndWrapper_TestCase {

  private static final int primitiveActual = 271828;

  private static final Integer wrappedActual = new Integer(primitiveActual);

  @Test
  public void shouldConstructInstanceFromPrimitiveValue() {
    IntAssert assertion = new IntAssert(primitiveActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromWrapperValue() {
    IntAssert assertion = new IntAssert(wrappedActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromNull() {
    IntAssert assertion = new IntAssert(null);
    assertThat(assertion).isNotNull();

  }
}
