/*
 * Created on Jun 9, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.CommonFailures.*;
import static org.fest.assertions.Images.fivePixelBlueImage;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import java.awt.Dimension;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link ImageAssert#hasSize(Dimension)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ImageAssert_hasSize_Test {

  @Test
  public void should_pass_if_actual_has_expected_size() {
    new ImageAssert(fivePixelBlueImage()).hasSize(new Dimension(5, 5));
  }


  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualIsNull(new CodeToTest() {
      public void run() {
        new ImageAssert(null).hasSize(new Dimension(5, 5));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_null() {
    expectErrorWithDescriptionIfActualIsNull(new CodeToTest() {
      public void run() {
        new ImageAssert(null).as("A Test")
                             .hasSize(new Dimension(5, 5));
      }
    });
  }

  @Test
  public void should_throw_error_if_given_size_is_null() {
    expectNullPointerException("The size to compare to should not be null").on(new CodeToTest() {
      public void run() {
        new ImageAssert(fivePixelBlueImage()).hasSize(null);
      }
    });
  }

  @Test
  public void should_throw_error_and_display_description_of_assertion_if_given_size_is_null() {
    expectNullPointerException("[A Test] The size to compare to should not be null").on(new CodeToTest() {
      public void run() {
        new ImageAssert(fivePixelBlueImage()).as("A Test")
                                             .hasSize(null);
      }
    });
  }

  @Test
  public void should_fail_if_width_is_not_equal() {
    expectAssertionError("expected:<([3], 5)> but was:<([5], 5)>").on(new CodeToTest() {
      public void run() {
        new ImageAssert(fivePixelBlueImage()).hasSize(new Dimension(3, 5));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_width_is_not_equal() {
    expectAssertionError("[A Test] expected:<([3], 5)> but was:<([5], 5)>").on(new CodeToTest() {
      public void run() {
        new ImageAssert(fivePixelBlueImage()).as("A Test")
                                             .hasSize(new Dimension(3, 5));
      }
    });
  }

  @Test
  public void should_fail_if_height_is_not_equal() {
    expectAssertionError("expected:<(5, [3])> but was:<(5, [5])>").on(new CodeToTest() {
      public void run() {
        new ImageAssert(fivePixelBlueImage()).hasSize(new Dimension(5, 3));
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_height_is_not_equal() {
    expectAssertionError("[A Test] expected:<(5, [3])> but was:<(5, [5])>").on(new CodeToTest() {
      public void run() {
        new ImageAssert(fivePixelBlueImage()).as("A Test")
                                             .hasSize(new Dimension(5, 3));
      }
    });
  }
}
