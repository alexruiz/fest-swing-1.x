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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Images.fivePixelBlueImage;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Strings.concat;

import java.awt.image.BufferedImage;

import org.fest.test.CodeToTest;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link ImageAssert#isNull()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ImageAssert_isNull_Test implements GenericAssert_isNull_TestCase {

  private static BufferedImage image;

  @BeforeClass
  public static void setUpOnce() {
    image = fivePixelBlueImage();
  }

  @Test
  public void should_pass_if_actual_is_null() {
    new ImageAssert(null).isNull();
  }

  @Test
  public void should_fail_if_actual_is_not_null() {
    expectAssertionError(concat("<", image, "> should be null")).on(new CodeToTest() {
      public void run() {
        new ImageAssert(image).isNull();
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_actual_is_not_null() {
    expectAssertionError(concat("[A Test] <", image, "> should be null")).on(new CodeToTest() {
      public void run() {
        new ImageAssert(image).as("A Test")
                              .isNull();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_actual_is_not_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ImageAssert(image).overridingErrorMessage("My custom message")
                              .isNull();
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_actual_is_not_null() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        new ImageAssert(image).as("A Test")
                              .overridingErrorMessage("My custom message")
                              .isNull();
      }
    });
  }
}
