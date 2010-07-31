/*
 * Created on Sep 23, 2006
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
 * Copyright @2006 the original author or authors.
 */
package org.fest.util;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for {@link Objects#areEqual(Object, Object)}.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class Objects_areEqual_Test {

  @Test
  public void should_return_true_if_both_Objects_are_null() {
    assertTrue(Objects.areEqual(null, null));
  }

  @Test
  public void should_return_true_if_Objects_are_equal() {
    assertTrue(Objects.areEqual("Yoda", "Yoda"));
  }

  @Test
  public void should_return_are_not_equal_if_first_Object_is_null_and_second_is_not() {
    assertFalse(Objects.areEqual(null, "Yoda"));
  }

  @Test
  public void should_return_are_not_equal_if_second_Object_is_null_and_first_is_not() {
    assertFalse(Objects.areEqual("Yoda", null));
  }

  @Test
  public void should_return_are_not_equal_if_Objects_are_not_equal() {
    assertFalse(Objects.areEqual("Yoda", 2));
  }
}
