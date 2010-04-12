package org.fest.assertions;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class LongAssert_providesConstructorsForPrimitiveAndWrapper_Test implements PrimitiveAssert_providesConstructorsForPrimitiveAndWrapper_TestCase {

  private static final long primitiveActual = 987654321l;

  private static final Long wrappedActual = new Long(primitiveActual);

  @Test
  public void shouldConstructInstanceFromPrimitiveValue() {
    LongAssert assertion = new LongAssert(primitiveActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromWrapperValue() {
    LongAssert assertion = new LongAssert(wrappedActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromNull() {
    LongAssert assertion = new LongAssert(null);
    assertThat(assertion).isNotNull();
  }
}
