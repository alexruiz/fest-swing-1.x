/*
 * Created on Jun 9, 2008
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
package org.fest.swing.driver;

/**
 * Common validation methods.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public final class CommonValidations {

  /**
   * Validates that the given cell reader is not {@code null}. Since cell readers do not have a common interface, the
   * passed argument is {@code Object}.
   * @param cellReader the cell reader to validate.
   * @throws NullPointerException if the given cell reader is {@code null}.
   */
  public static void validateCellReader(Object cellReader) {
    if (cellReader == null) throw new NullPointerException("Cell reader should not be null");
  }

  /**
   * Validates that the given cell writer is not {@code null}. Since cell writers do not have a common interface, the
   * passed argument is {@code Object}.
   * @param cellWriter the cell writer to validate.
   * @throws NullPointerException if the given cell writer is {@code null}.
   */
  public static void validateCellWriter(Object cellWriter) {
    if (cellWriter == null) throw new NullPointerException("Cell writer should not be null");
  }

  private CommonValidations() {}
}
