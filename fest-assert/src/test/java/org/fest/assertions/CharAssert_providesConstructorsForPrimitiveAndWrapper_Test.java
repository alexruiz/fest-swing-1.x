package org.fest.assertions;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class CharAssert_providesConstructorsForPrimitiveAndWrapper_Test implements PrimitiveAssert_providesConstructorsForPrimitiveAndWrapper_TestCase {

  private static final char primitiveActual = '?';

  private static final Character wrapperActual = new Character(primitiveActual);

  @Test
  public void shouldConstructInstanceFromPrimitiveValue() {
    CharAssert assertion = new CharAssert(primitiveActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromWrapperValue() {
    CharAssert assertion = new CharAssert(wrapperActual);
    assertThat(assertion).isNotNull();
  }

  @Test
  public void shouldConstructInstanceFromNull() {
    CharAssert assertion = new CharAssert(null);
    assertThat(assertion).isNotNull();
  }
}
