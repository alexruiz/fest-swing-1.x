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

import java.awt.image.BufferedImage;

import org.junit.BeforeClass;

/**
 * Tests for <code>{@link ImageAssert#isNull()}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class ImageAssert_isNull_Test extends GenericAssert_isNull_TestCase<BufferedImage> {

  private static BufferedImage notNullValue;

  @BeforeClass
  public static void setUpOnce() {
    notNullValue = fivePixelBlueImage();
  }

  protected ImageAssert assertionsFor(BufferedImage actual) {
    return new ImageAssert(actual);
  }

  protected BufferedImage notNullValue() {
    return notNullValue;
  }
}
