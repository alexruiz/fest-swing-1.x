/*
 * Created on Apr 30, 2009
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
package org.fest.swing.data;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.test.EqualsHashCodeContractAssert.*;

import org.fest.test.EqualsHashCodeContractTestCase;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link TableCellByColumnId#equals(Object)}</code> and
 * <code>{@link TableCellByColumnId#hashCode()}</code>.
 *
 * @author Alex Ruiz
 */
public class TableCellByColumnId_equals_hashCode_Test implements EqualsHashCodeContractTestCase {

  private TableCellByColumnId cell;

  @Before public void setUp() {
    cell = TableCellByColumnId.row(6).columnId("id");
  }

  @Test
  public void should_have_consistent_equals() {
    TableCellByColumnId other = TableCellByColumnId.row(6).columnId("id");
    assertThat(cell.equals(other)).isTrue();
  }

  @Test
  public void should_have_reflexive_equals() {
    assertEqualsIsReflexive(cell);
  }

  @Test
  public void should_have_symmetric_equals() {
    TableCellByColumnId other = TableCellByColumnId.row(6).columnId("id");
    assertEqualsIsSymmetric(cell, other);
  }

  @Test
  public void should_have_transitive_equals() {
    TableCellByColumnId other1 = TableCellByColumnId.row(6).columnId("id");
    TableCellByColumnId other2 = TableCellByColumnId.row(6).columnId("id");
    assertEqualsIsTransitive(cell, other1, other2);
  }

  @Test
  public void should_maintain_equals_and_hashCode_contract() {
    TableCellByColumnId other = TableCellByColumnId.row(6).columnId("id");
    assertMaintainsEqualsAndHashCodeContract(cell, other);
  }

  @Test
  public void should_not_be_equal_to_null() {
    assertThat(cell.equals(null)).isFalse();
  }

  @Test
  public void should_not_be_equal_to_Object_not_being_of_same_type() {
    assertThat(cell.equals("Hello")).isFalse();
  }

  @Test
  public void should_return_not_equal_if_row_values_are_not_equal() {
    TableCellByColumnId other = TableCellByColumnId.row(8).columnId("id");
    assertThat(cell.equals(other)).isFalse();
  }

  @Test
  public void should_return_not_equal_if_column_values_are_not_equal() {
    TableCellByColumnId other = TableCellByColumnId.row(6).columnId("anotherId");
    assertThat(cell.equals(other)).isFalse();
  }
}
