/*
 * Created on May 6, 2007
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
 * Copyright @2007-2013 the original author or authors.
 */
package org.fest.swing.image;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.image.TestImageFileWriters.newImageFileWriterMock;
import static org.fest.swing.test.core.CommonAssertions.failWhenExpectingException;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.fest.swing.util.RobotFactory;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for {@link ScreenshotTaker#saveImage(BufferedImage, String)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class ScreenshotTaker_saveImage_Test {
  private BufferedImage image;
  private String path;
  private ImageFileWriter writer;
  private IOException error;
  private ScreenshotTaker taker;

  @Before
  public void setUp() {
    image = createMock(BufferedImage.class);
    path = "image.png";
    writer = newImageFileWriterMock();
    error = new IOException("On Purpose");
    taker = new ScreenshotTaker(writer, new RobotFactory());
  }

  @Test
  public void should_throw_wrapped_Exception_thrown_when_writing_image_to_file() {
    new EasyMockTemplate(writer) {
      @Override
      protected void expectations() throws Throwable {
        expect(writer.writeAsPng(image, path)).andThrow(error);
      }

      @Override
      protected void codeToTest() {
        try {
          taker.saveImage(image, path);
          failWhenExpectingException();
        } catch (ImageException e) {
          assertThat(e.getCause()).isSameAs(error);
        }
      }
    }.run();
  }
}
