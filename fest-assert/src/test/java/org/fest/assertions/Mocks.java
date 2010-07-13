/*
 * Created on Oct 27, 2009
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.assertions;

import static org.easymock.EasyMock.*;

/**
 * Understands utility methods related to mocks.
 *
 * @author Alex Ruiz
 */
final class Mocks {

  static Object createComparisonFailureFrom(ConstructorInvoker invoker) {
    Class<?>[] paramTypes = { String.class, String.class, String.class };
    Object[] args = { "[message]", "'expected'", "'actual'" };
    try {
      return invoker.newInstance(eq("org.junit.ComparisonFailure"), aryEq(paramTypes), aryEq(args));
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  private Mocks() {}
}
