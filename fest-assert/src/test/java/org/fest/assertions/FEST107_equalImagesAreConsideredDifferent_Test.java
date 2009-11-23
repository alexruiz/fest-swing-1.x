/*
 * Created on Mar 24, 2009
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
 * Copyright @2009 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.Resources.file;
import static org.fest.assertions.Threshold.threshold;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for bug <a href="http://jira.codehaus.org/browse/FEST-107" target="_blank">FEST-107</a>.
 *
 * @author Alex Ruiz
 */
public class FEST107_equalImagesAreConsideredDifferent_Test {

  private ImageReader reader;

  @Before public void setUp() {
    reader = new ImageReader();
  }

  @Test
  public void images_should_be_equal_when_using_threshold() throws IOException {
    BufferedImage a = imageFromFile("Basics1SimpleGridActual.png");
    BufferedImage e = imageFromFile("Basics1SimpleGridExpected.png");
    assertThat(a).isEqualTo(e, threshold(1));
  }

  private BufferedImage imageFromFile(String fileName) throws IOException {
    return reader.read(file(fileName));
  }
}
