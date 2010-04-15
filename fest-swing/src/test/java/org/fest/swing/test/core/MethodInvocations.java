/*
 * Created on Oct 8, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.test.core;

import java.util.*;

import static java.util.Arrays.deepEquals;

import static org.fest.assertions.Fail.fail;
import static org.fest.swing.util.Arrays.copyOf;
import static org.fest.util.Arrays.format;
import static org.fest.util.Strings.*;

/**
 * Understands a mechanism to record and verify expected method invocations.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class MethodInvocations {

  private final Map<String, Object[]> invocations = new HashMap<String, Object[]>();

  /**
   * Records that a method with the given name was invoked.
   * @param methodName the name of the invoked method.
   * @return <code>this</code>.
   */
  public MethodInvocations invoked(String methodName) {
    invocations.put(methodName, new Object[0]);
    return this;
  }

  /**
   * Records that a method with the given name was invoked with the given arguments.
   * @param methodName the name of the invoked method.
   * @param args the arguments passed to the invoked method.
   * @return <code>this</code>.
   */
  public MethodInvocations invoked(String methodName, Args args) {
    validate(args);
    invocations.put(methodName, args.args);
    return this;
  }

  /**
   * Verifies that a method with the given name was invoked.
   * @param methodName the name of the method to verify.
   * @return <code>this</code>.
   * @throws AssertionError if the method was not invoked.
   */
  public MethodInvocations requireInvoked(String methodName) {
    if (!invocations.containsKey(methodName)) methodNotInvoked(methodName);
    return this;
  }

  /**
   * Verifies that a method with the given name was invoked with the given arguments.
   * @param methodName the name of the method to verify.
   * @param args the arguments that should have been passed to the method to verify.
   * @return <code>this</code>.
   * @throws AssertionError if the method was not invoked.
   * @throws AssertionError if different arguments were passed to the method to verify.
   */
  public MethodInvocations requireInvoked(String methodName, Args args) {
    validate(args);
    if (!invocations.containsKey(methodName)) methodNotInvoked(methodName);
    Object[] actual = args.args;
    Object[] expected = invocations.get(methodName);
    if (!deepEquals(actual, expected))
      fail(concat("Expecting arguments ", format(actual), " but found ", format(expected)));
    return this;
  }

  private void validate(Args args) {
    if (args == null) throw new NullPointerException("Args should not be null");
  }

  private void methodNotInvoked(String methodName) {
    fail(concat("expecting method ", quote(methodName), " to be invoked"));
  }

  /**
   * Understands a list of arguments passed to a method.
   *
   * @author Alex Ruiz
   * @author Yvonne Wang
   */
  public static class Args {

    final Object[] args;

    /**
     * Creates a new <code>{@link Args}</code>.
     * @param args the arguments to store.
     * @return the created <code>Args</code>.
     */
    public static Args args(Object...args) {
      return new Args(args);
    }

    private Args(Object... args) {
      this.args = args != null ? copyOf(args) : new Object[0];
    }
  }
}
