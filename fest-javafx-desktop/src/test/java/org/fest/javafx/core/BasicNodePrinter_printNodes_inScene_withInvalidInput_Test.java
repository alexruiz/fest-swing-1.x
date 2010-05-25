/*
 * Created on May 24, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.javafx.core;

import static org.fest.javafx.test.builder.Scenes.scene;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for <code>{@link BasicNodePrinter#printNodes(java.io.PrintStream, javafx.scene.Scene)}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicNodePrinter_printNodes_inScene_withInvalidInput_Test {

  private static BasicNodePrinter printer;

  @BeforeClass
  public static void setUpOnce() {
    printer = new BasicNodePrinter();
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_PrintStream_is_null() {
    printer.printNodes(null, scene().createNew());
  }
}
