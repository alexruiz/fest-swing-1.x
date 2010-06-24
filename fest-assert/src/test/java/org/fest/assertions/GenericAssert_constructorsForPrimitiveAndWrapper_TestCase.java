/*
 * Created on Apr 15, 2010
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

import static org.fest.reflect.core.Reflection.constructor;
import static org.junit.Assert.*;

import java.lang.reflect.Constructor;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests that implementations of <code>{@link GenericAssert}</code> that handle primitive wrappers provide a constructor
 * that takes a primitive value.
 * @param <T> the generic type of the <code>GenericAssert</code> to test.
 *
 * @author Ansgar Konermann
 * @author Alex Ruiz
 */
public abstract class GenericAssert_constructorsForPrimitiveAndWrapper_TestCase<T> {

  private Class<? extends GenericAssert<T>> assertionType;
  private Class<?> primitiveType;
  private Class<T> primitiveWrapperType;

  @Before
  public final void setUp() {
    assertionType = assertionType();
    primitiveType = primitiveType();
    assertTrue("is primitive type", primitiveType.isPrimitive());
    primitiveWrapperType = primitiveWrapperType();
  }

  protected abstract Class<? extends GenericAssert<T>> assertionType();

  protected abstract Class<?> primitiveType();

  protected abstract Class<T> primitiveWrapperType();

  @Test
  public final void should_provide_constructor_that_takes_primitive() {
    verifyAssertionTypeHasConstructorWithParameterType(primitiveType);
  }

  @Test
  public final void should_provide_constructor_that_takes_primitive_wrapper() {
    verifyAssertionTypeHasConstructorWithParameterType(primitiveWrapperType);
  }

  private void verifyAssertionTypeHasConstructorWithParameterType(Class<?> type) {
    Constructor<?> c = constructor().withParameterTypes(type)
                                    .in(assertionType)
                                    .info();
    verifyArrayContainsOnly(c.getParameterTypes(), type);
  }

  private void verifyArrayContainsOnly(Class<?>[] types, Class<?> type) {
    assertEquals(1, types.length);
    assertEquals(type, types[0]);
  }
}
