/*
 * Created on Oct 27, 2008
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
package org.fest.swing.timing;

import static org.fest.assertions.Assertions.assertThat;

import org.fest.assertions.Description;
import org.junit.Test;

/**
 * Tests for <code>{@link Condition#toString()}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class Condition_toString_Test {

  private static final String DESCRIPTION = "Hello World!";


  @Test
  public void should_return_description_text() {
    Description description = new Description() {
      public String value() {
        return DESCRIPTION;
      }
    };
    MyCondition condition = new MyCondition(description);
    assertThat(condition.toString()).isEqualTo(DESCRIPTION);
  }

  @Test
  public void should_return_description_text_set_as_String() {
    MyCondition condition = new MyCondition(DESCRIPTION);
    assertThat(condition.toString()).isEqualTo(DESCRIPTION);
  }

  @Test
  public void should_return_empty_text_if_description_is_null() {
    Description noDescription = null;
    MyCondition condition = new MyCondition(noDescription);
    assertThat(condition.toString()).isEmpty();
  }

  private static class MyCondition extends Condition {
    MyCondition(String description) {
      super(description);
    }

    MyCondition(Description lazyLoadingDescription) {
      super(lazyLoadingDescription);
    }

    public boolean test() {
      return false;
    }
  }
}
