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

import static java.awt.Color.BLUE;
import static java.awt.Color.YELLOW;
import static org.fest.assertions.Images.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;
import static org.fest.util.Strings.concat;

import java.awt.image.BufferedImage;

import org.fest.test.CodeToTest;
import org.junit.Test;

/**
 * Tests for <code>{@link ImageAssert#isEqualTo(BufferedImage)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ImageAssert_isEqualTo_Test {

  @Test
  public void should_pass_if_actual_and_expected_are_equal() {
    new ImageAssert(fivePixelBlueImage()).isEqualTo(fivePixelBlueImage());
  }

  @Test
  public void should_pass_if_both_actual_and_expected_are_null() {
    new ImageAssert(null).isEqualTo(null);
  }

  @Test
  public void should_fail_if_expected_is_null() {
    final BufferedImage a = fivePixelBlueImage();
    expectAssertionError(concat("expected:<null> but was:<", a, ">")).on(new CodeToTest() {
      public void run() {
        new ImageAssert(a).isEqualTo(null);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_expected_is_null() {
    final BufferedImage a = fivePixelBlueImage();
    expectAssertionError(concat("[A Test] expected:<null> but was:<", a, ">")).on(new CodeToTest() {
      public void run() {
        new ImageAssert(a).as("A Test")
                          .isEqualTo(null);
      }
    });
  }

  @Test
  public void should_fail_if_width_is_not_equal() {
    expectAssertionError("image size, expected:<(3, 5)> but was:<(5, 5)>").on(new CodeToTest() {
      public void run() {
        BufferedImage a = fivePixelBlueImage();
        BufferedImage e = image(3, 5, BLUE);
        new ImageAssert(a).isEqualTo(e);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_width_is_not_equal() {
    expectAssertionError("[A Test] image size, expected:<(3, 5)> but was:<(5, 5)>").on(new CodeToTest() {
      public void run() {
        BufferedImage a = fivePixelBlueImage();
        BufferedImage e = image(3, 5, BLUE);
        new ImageAssert(a).as("A Test")
                          .isEqualTo(e);
      }
    });
  }

  @Test
  public void should_fail_if_height_is_not_equal() {
    expectAssertionError("image size, expected:<(5, 2)> but was:<(5, 5)>").on(new CodeToTest() {
      public void run() {
        BufferedImage a = fivePixelBlueImage();
        BufferedImage e = image(5, 2, BLUE);
        new ImageAssert(a).isEqualTo(e);
      }
    });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_height_is_not_equal() {
    expectAssertionError("[A Test] image size, expected:<(5, 2)> but was:<(5, 5)>").on(new CodeToTest() {
      public void run() {
        BufferedImage a = fivePixelBlueImage();
        BufferedImage e = image(5, 2, BLUE);
        new ImageAssert(a).as("A Test")
                          .isEqualTo(e);
      }
    });
  }

  @Test
  public void should_fail_if_color_is_not_equal() {
    expectAssertionError("expected:<color[r=0,g=0,b=255]> but was:<color[r=255,g=255,b=0]> at pixel [0,0]").on(
      new CodeToTest() {
        public void run() {
          BufferedImage a = fivePixelBlueImage();
          BufferedImage e = fivePixelYellowImage();
          new ImageAssert(a).isEqualTo(e);
        }
      });
  }

  @Test
  public void should_fail_and_display_description_of_assertion_if_color_is_not_equal() {
    expectAssertionError("[A Test] expected:<color[r=0,g=0,b=255]> but was:<color[r=255,g=255,b=0]> at pixel [0,0]").on(
      new CodeToTest() {
        public void run() {
          BufferedImage a = fivePixelBlueImage();
          BufferedImage e = image(5, 5, YELLOW);
          new ImageAssert(a).as("A Test")
                            .isEqualTo(e);
        }
      });
  }

  @Test
  public void should_fail_with_custom_message_if_width_is_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        BufferedImage a = fivePixelBlueImage();
        BufferedImage e = image(3, 5, BLUE);
        new ImageAssert(a).overridingErrorMessage("My custom message")
                          .isEqualTo(e);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_width_is_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        BufferedImage a = fivePixelBlueImage();
        BufferedImage e = image(3, 5, BLUE);
        new ImageAssert(a).as("A Test")
                          .overridingErrorMessage("My custom message")
                          .isEqualTo(e);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_height_is_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        BufferedImage a = fivePixelBlueImage();
        BufferedImage e = image(5, 2, BLUE);
        new ImageAssert(a).overridingErrorMessage("My custom message")
                          .isEqualTo(e);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_height_is_not_equal() {
    expectAssertionError("My custom message").on(new CodeToTest() {
      public void run() {
        BufferedImage a = fivePixelBlueImage();
        BufferedImage e = image(5, 2, BLUE);
        new ImageAssert(a).as("A Test")
                          .overridingErrorMessage("My custom message")
                          .isEqualTo(e);
      }
    });
  }

  @Test
  public void should_fail_with_custom_message_if_color_is_not_equal() {
    expectAssertionError("My custom message").on(
      new CodeToTest() {
        public void run() {
          BufferedImage a = fivePixelBlueImage();
          BufferedImage e = image(5, 5, YELLOW);
          new ImageAssert(a).overridingErrorMessage("My custom message")
                            .isEqualTo(e);
        }
      });
  }

  @Test
  public void should_fail_with_custom_message_ignoring_description_of_assertion_if_color_is_not_equal() {
    expectAssertionError("My custom message").on(
      new CodeToTest() {
        public void run() {
          BufferedImage a = fivePixelBlueImage();
          BufferedImage e = image(5, 5, YELLOW);
          new ImageAssert(a).as("A Test")
                            .overridingErrorMessage("My custom message")
                            .isEqualTo(e);
        }
      });
  }
}
