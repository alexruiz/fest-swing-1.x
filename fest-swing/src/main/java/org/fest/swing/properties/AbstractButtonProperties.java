/*
 * Created on Aug 24, 2010
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
package org.fest.swing.properties;

import javax.swing.AbstractButton;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.query.AbstractButtonTextQuery;

/**
 * Queries property values of an <code>{@link AbstractButton}</code>.
 * <p>
 * <b>Note:</b> This class is intended for internal use only. Please use the classes in the package
 * <code>{@link org.fest.swing.fixture}</code> in your tests.
 * </p>
 *
 * @author Alex Ruiz
 *
 * @since 2.0
 */
public class AbstractButtonProperties extends JComponentProperties {

  /**
   * Returns the text of the given button.
   * @param button the given button.
   * @return the text of the given button.
   */
  @RunsInEDT
  public String textOf(AbstractButton button) {
    return AbstractButtonTextQuery.textOf(button);
  }
}
