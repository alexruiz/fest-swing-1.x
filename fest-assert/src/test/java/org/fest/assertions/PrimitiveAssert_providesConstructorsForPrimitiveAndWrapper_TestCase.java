package org.fest.assertions;

/**
 * Tests for subclasses of PrimitiveAssert which are expected to provide
 * constructors capable of constructing instances from primitive values,
 * "primitive wrappers" and null.
 *
 * @author Ansgar Konermann
 */
public interface PrimitiveAssert_providesConstructorsForPrimitiveAndWrapper_TestCase {

  public void shouldConstructInstanceFromPrimitiveValue();

  public void shouldConstructInstanceFromWrapperValue();

  public void shouldConstructInstanceFromNull();
  
}
