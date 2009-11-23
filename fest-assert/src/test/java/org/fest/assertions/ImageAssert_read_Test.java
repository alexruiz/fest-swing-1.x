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

import static org.fest.assertions.CommonFailures.*;
import static org.fest.assertions.Images.fivePixelBlueImage;
import static org.fest.assertions.Resources.file;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.fest.test.CodeToTest;
import org.junit.After;
import org.junit.Test;

/**
 * Tests for <code>{@link ImageAssert#read(String)}</code>.
 *
 * @author Yvonne Wang
 * @author Alex Ruiz
 * @author Ansgar Konermann
 */
public class ImageAssert_read_Test {

  @After
  public void tearDown() {
    ImageAssert.imageReader(new ImageReader());
  }

  @Test
  public void should_read_image_file_using_ImageReader() throws IOException {
    ImageReaderStub imageReader = new ImageReaderStub();
    BufferedImage toRead = fivePixelBlueImage();
    imageReader.imageToRead(toRead);
    ImageAssert.imageReader(imageReader);
    BufferedImage image = ImageAssert.read(pathOf("red.png"));
    assertSame(toRead, image);
  }

  @Test
  public void should_rethrow_error_catched_from_ImageReader() {
    ImageReaderStub imageReader = new ImageReaderStub();
    IOException toThrow = new IOException();
    imageReader.toThrow(toThrow);
    ImageAssert.imageReader(imageReader);
    try {
      ImageAssert.read(pathOf("red.png"));
      fail("Expecting IOException");
    } catch (IOException e) {
      assertSame(toThrow, e);
    }
  }

  private String pathOf(String fileName) {
    return file(fileName).getPath();
  }

  @Test
  public void should_throw_error_if_path_of_image_to_read_does_not_belong_to_a_file() {
    expectIllegalArgumentException("The path 'blah' does not belong to a file").on(new CodeToTest() {
      public void run() throws IOException {
        ImageAssert.read("blah");
      }
    });
  }

  @Test
  public void should_throw_error_if_path_is_null() {
    expectNullPointerException("The path of the image to read should not be null").on(new CodeToTest() {
      public void run() throws IOException {
        ImageAssert.read(null);
      }
    });
  }

  private static class ImageReaderStub extends ImageReader {
    private BufferedImage imageRead;
    private IOException toThrow;

    void imageToRead(BufferedImage image) {
      imageRead = image;
    }

    void toThrow(IOException e) {
      toThrow = e;
    }

    @Override BufferedImage read(File imageFile) throws IOException {
      if (toThrow != null) throw toThrow;
      return imageRead;
    }
  }
}
