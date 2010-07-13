/*
 * Created on Jun 7, 2007
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

import static java.lang.String.valueOf;
import static org.fest.assertions.ErrorMessages.*;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.assertions.Threshold.threshold;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Strings.*;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Understands assertion methods for images. To create a new instance of this class use the method
 * <code>{@link Assertions#assertThat(BufferedImage)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Ansgar Konermann
 */
public class ImageAssert extends GenericAssert<BufferedImage> {

  private static final Threshold ZERO_THRESHOLD = threshold(0);

  private static ImageReader imageReader = new ImageReader();

  /**
   * Reads the image in the specified path.
   * @param imageFilePath the path of the image to read.
   * @return the read image.
   * @throws NullPointerException if the given path is <code>null</code>.
   * @throws IllegalArgumentException if the given path does not belong to a file.
   * @throws IOException if any I/O error occurred while reading the image.
   */
  public static BufferedImage read(String imageFilePath) throws IOException {
    if (imageFilePath == null) throw new NullPointerException("The path of the image to read should not be null");
    File imageFile = new File(imageFilePath);
    if (!imageFile.isFile())
      throw new IllegalArgumentException(concat("The path ", quote(imageFilePath), " does not belong to a file"));
    return imageReader.read(imageFile);
  }

  /**
   * Creates a new </code>{@link ImageAssert}</code>.
   * @param actual the target to verify.
   */
  protected ImageAssert(BufferedImage actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public ImageAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public ImageAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public ImageAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public ImageAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual image satisfies the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual image does not satisfy the given condition.
   * @see #is(Condition)
   */
  public ImageAssert satisfies(Condition<BufferedImage> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual image does not satisfy the given condition.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual image satisfies the given condition.
   * @see #isNot(Condition)
   */
  public ImageAssert doesNotSatisfy(Condition<BufferedImage> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual image does not satisfy the given condition.
   * @since 1.2
   */
  public ImageAssert is(Condition<BufferedImage> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError if the actual image satisfies the given condition.
   * @since 1.2
   */
  public ImageAssert isNot(Condition<BufferedImage> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual image is equal to the given one. Two images are equal if they have the same size and the
   * pixels at the same coordinates have the same color.
   * @param expected the given image to compare the actual image to.
   * @return this assertion object.
   * @throws AssertionError if the actual image is not equal to the given one.
   */
  public ImageAssert isEqualTo(BufferedImage expected) {
    return isEqualTo(expected, ZERO_THRESHOLD);
  }

  /**
   * Verifies that the actual image is equal to the given one. Two images are equal if:
   * <ol>
   * <li>they have the same size</li>
   * <li>the difference between the RGB values of the color of each pixel is less than or equal to the given
   * threshold</li>
   * </ol>
   * @param expected the given image to compare the actual image to.
   * @param threshold the threshold to use to decide if the color of two pixels are similar: two pixels that are
   * identical to the human eye may still have slightly different color values. For example, by using a threshold of 1
   * we can indicate that a blue value of 60 is similar to a blue value of 61.
   * @return this assertion object.
   * @throws AssertionError if the actual image is not equal to the given one.
   * @since 1.1
   */
  public ImageAssert isEqualTo(BufferedImage expected, Threshold threshold) {
    if (areEqual(actual, expected)) return this;
    failIfNull(expected);
    failIfNotEqual(sizeOf(actual), sizeOf(expected));
    failIfNotEqualColor(expected, threshold);
    return this;
  }

  private void failIfNull(BufferedImage expected) {
    if (expected != null) return;
    failIfCustomMessageIsSet();
    fail(unexpectedNotEqual(actual, null));
  }

  private void failIfNotEqual(Dimension a, Dimension e) {
    if (areEqual(a, e)) return;
    failIfCustomMessageIsSet();
    fail(concat("image size, expected:", inBrackets(e), " but was:", inBrackets(a)));
  }

  private void failIfNotEqualColor(BufferedImage expected, Threshold threshold) {
    int w = actual.getWidth();
    int h = actual.getHeight();
    for (int x = 0; x < w; x++)
      for (int y = 0; y < h; y++)
        failIfNotEqual(new RGBColor(actual.getRGB(x, y)), new RGBColor(expected.getRGB(x, y)), threshold, x, y);
  }

  private void failIfNotEqual(RGBColor a, RGBColor e, Threshold threshold, int x, int y) {
    if (a.isEqualTo(e, threshold.value())) return;
    failIfCustomMessageIsSet();
    fail(concat("expected:", inBrackets(a), " but was:", inBrackets(e), " at pixel [", valueOf(x), ",", valueOf(y), "]"));
  }

  /**
   * Verifies that the actual image is not equal to the given one. Two images are equal if they have the same size and
   * the pixels at the same coordinates have the same color.
   * @param image the given image to compare the actual image to.
   * @return this assertion object.
   * @throws AssertionError if the actual image is equal to the given one.
   */
  public ImageAssert isNotEqualTo(BufferedImage image) {
    if (areEqual(actual, image)) {
      failIfCustomMessageIsSet();
      throw failure(unexpectedEqual(actual, image));
    }
    if (image == null) return this;
    if (areEqual(sizeOf(actual), sizeOf(image)) && hasEqualColor(image)) {
      failIfCustomMessageIsSet();
      throw failure("images are equal");
    }
    return this;
  }

  private static Dimension sizeOf(BufferedImage image) {
    return new Dimension(image.getWidth(), image.getHeight());
  }

  private boolean hasEqualColor(BufferedImage expected) {
    int w = actual.getWidth();
    int h = actual.getHeight();
    for (int x = 0; x < w; x++)
      for (int y = 0; y < h; y++)
        if (actual.getRGB(x, y) != expected.getRGB(x, y)) return false;
    return true;
  }

  /**
   * Verifies that the actual image is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual image is <code>null</code>.
   */
  public ImageAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual image is not the same as the given one.
   * @param expected the given image to compare the actual image to.
   * @return this assertion object.
   * @throws AssertionError if the actual image is the same as the given one.
   */
  public ImageAssert isNotSameAs(BufferedImage expected) {
    assertNotSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual image is the same as the given one.
   * @param expected the given image to compare the actual image to.
   * @return this assertion object.
   * @throws AssertionError if the actual image is not the same as the given one.
   */
  public ImageAssert isSameAs(BufferedImage expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the size of the actual image is equal to the given one.
   * @param expected the expected size of the actual image.
   * @return this assertion object.
   * @throws AssertionError if the actual image is <code>null</code>.
   * @throws NullPointerException if the given size is <code>null</code>.
   * @throws AssertionError if the size of the actual image is not equal to the given one.
   */
  public ImageAssert hasSize(Dimension expected) {
    isNotNull();
    if (expected == null)
      throw new NullPointerException(formattedErrorMessage("The size to compare to should not be null"));
    Dimension actualDimension = new Dimension(actual.getWidth(), actual.getHeight());
    Fail.failIfNotEqual(customErrorMessage(), rawDescription(), actualDimension, expected);
    return this;
  }

  /** {@inheritDoc} */
  public ImageAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }

  static void imageReader(ImageReader newImageReader) {
    imageReader = newImageReader;
  }
}
