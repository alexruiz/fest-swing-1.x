/*
 * Created on Feb 15, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.test.ExpectedFailure.expect;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import org.fest.test.CodeToTest;
import org.fest.test.ExpectedFailure.Message;
import org.junit.ComparisonFailure;

/**
 * Understands common, expected test failures.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class CommonFailures {

  public static void expectErrorIfActualIsNull(CodeToTest codeToTest) {
    expectAssertionError("expecting actual value not be null").on(codeToTest);
  }

  public static void expectErrorWithDescriptionIfActualIsNull(CodeToTest codeToTest) {
    expectAssertionError("[A Test] expecting actual value not be null").on(codeToTest);
  }

  public static void expectErrorIfTypeIsNull(CodeToTest codeToTest) {
    expectNullPointerException("expected type should not be null").on(codeToTest);
  }

  public static void expectErrorWithDescriptionIfTypeIsNull(CodeToTest codeToTest) {
    expectNullPointerException("[A Test] expected type should not be null").on(codeToTest);
  }

  public static void expectErrorIfActualArrayIsNull(CodeToTest codeToTest) {
    expectAssertionError("expecting actual array not to be null").on(codeToTest);
  }

  public static void expectErrorWithDescriptionIfActualArrayIsNull(CodeToTest codeToTest) {
    expectAssertionError("[A Test] expecting actual array not to be null").on(codeToTest);
  }

  public static void expectErrorIfActualCollectionIsNull(CodeToTest codeToTest) {
    expectAssertionError("expecting actual collection not to be null").on(codeToTest);
  }

  public static void expectErrorWithDescriptionIfActualCollectionIsNull(CodeToTest codeToTest) {
    expectAssertionError("[A Test] expecting actual collection not to be null").on(codeToTest);
  }

  public static void expectErrorIfActualListIsNull(CodeToTest codeToTest) {
    expectAssertionError("expecting actual list not to be null").on(codeToTest);
  }

  public static void expectErrorWithDescriptionIfActualListIsNull(CodeToTest codeToTest) {
    expectAssertionError("[A Test] expecting actual list not to be null").on(codeToTest);
  }

  public static void expectErrorIfActualMapIsNull(CodeToTest codeToTest) {
    expectAssertionError("expecting actual map not to be null").on(codeToTest);
  }

  public static void expectErrorWithDescriptionIfActualMapIsNull(CodeToTest codeToTest) {
    expectAssertionError("[A Test] expecting actual map not to be null").on(codeToTest);
  }

  public static Message expectComparisonFailure(String message) {
    return expect(ComparisonFailure.class).withMessage(message);
  }

  public static Message expectIllegalArgumentException(String message) {
    return expect(IllegalArgumentException.class).withMessage(message);
  }

  public static Message expectIndexOutOfBoundsException(String message) {
    return expect(IndexOutOfBoundsException.class).withMessage(message);
  }

  public static Message expectErrorIfConditionIsNull() {
    return expectNullPointerException("Condition to check should not be null");
  }

  public static Message expectNullPointerException(String message) {
    return expect(NullPointerException.class).withMessage(message);
  }

  private CommonFailures() {}

}
