/*
 * Created on Feb 15, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.assertions;

import static java.awt.Color.RED;
import static org.fest.assertions.Resources.file;
import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.*;

import org.junit.*;

/**
 * Tests for <code>{@link ImageReader#read(File)}</code>.
 *
 * @author Yvonne Wang
 */
public class ImageReader_reader_Test {

  private static ImageReader imageReader;

  @BeforeClass
  public static void setUpOnce() {
    imageReader = new ImageReader();
  }

  @Test
  public void should_read_image_file() throws IOException {
    final File imageFile = file("red.png");
    BufferedImage image = imageReader.read(imageFile);
    assertNotNull(image);
    int w = image.getWidth();
    assertEquals(6, w);
    int h = image.getHeight();
    assertEquals(6, h);
    for (int x = 0; x < w; x++)
      for (int y = 0; y < h; y++)
        assertEquals(RED.getRGB(), image.getRGB(x, y));
  }

}
