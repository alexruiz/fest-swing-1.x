/*
 * Created on Apr 14, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import org.junit.*;

import org.fest.test.EqualsHashCodeContractTestCase;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.keyboard.mapping.CharMapping.newCharMapping;
import static org.fest.test.EqualsHashCodeContractAssert.*;

/**
 * Tests for <code>{@link CharMapping#equals(Object)}</code> and
 * <code>{@link CharMapping#hashCode()}</code>.
 *
 * @author Alex Ruiz
 */
public class CharMapping_equals_hashCode_Test implements EqualsHashCodeContractTestCase {

  private static CharMapping mapping;
  
  @BeforeClass
  public static void setUpOnce() {
    mapping = newCharMappingForA();
  }
  
  @Test
  public void should_have_consistent_equals() {
    assertThat(mapping).isEqualTo(newCharMappingForA());
  }

  @Test
  public void should_have_reflexive_equals() {
    assertEqualsIsReflexive(mapping);
  }

  @Test
  public void should_have_symmetric_equals() {
    assertEqualsIsSymmetric(mapping, newCharMappingForA());
  }

  @Test
  public void should_have_transitive_equals() {
    assertEqualsIsTransitive(mapping, newCharMappingForA(), newCharMappingForA());
  }

  @Test
  public void should_maintain_equals_and_hashCode_contract() {
    assertMaintainsEqualsAndHashCodeContract(mapping, newCharMappingForA());
  }
  
  private static CharMapping newCharMappingForA() {
    return newCharMapping("A", "A", "SHIFT_MASK");
  }

  @Test
  public void should_not_be_equal_to_Object_not_being_of_same_type() {
    assertThat(mapping).isNotEqualTo("Hello");
  }

  @Test
  public void should_not_be_equal_to_null() {
    assertIsNotEqualToNull(mapping);
  }
  
  @Test
  public void should_not_be_equal_if_characters_are_not_equal() {
    assertThat(mapping).isNotEqualTo(newCharMapping("S", "A", "SHIFT_MASK"));
  }

  @Test
  public void should_not_be_equal_if_key_codes_are_not_equal() {
    assertThat(mapping).isNotEqualTo(newCharMapping("A", "Q", "SHIFT_MASK"));
  }

  @Test
  public void should_not_be_equal_if_modifier_are_not_equal() {
    assertThat(mapping).isNotEqualTo(newCharMapping("A", "A", "NO_MASK"));
  }
}
