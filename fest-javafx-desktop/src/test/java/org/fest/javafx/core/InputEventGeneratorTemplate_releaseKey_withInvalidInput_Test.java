/*
 * Created on May 27, 2010
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

import static org.fest.test.ExpectedFailure.expect;

import org.fest.test.CodeToTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link InputEventGeneratorTemplate#releaseMouse(javafx.scene.input.MouseButton)}</code>.
 *
 * @author Alex Ruiz
 */
public class InputEventGeneratorTemplate_releaseKey_withInvalidInput_Test {

  private InputEventGeneratorTemplate inputEventGenerator;

  @Before
  public void setUp() {
    inputEventGenerator = new TestInputEventGeneratorTemplate();
  }

  @Test
  public void should_throw_error_if_KeyCode_is_null() {
    String msg = "The KeyCode to release should not be null";
    expect(NullPointerException.class).withMessage(msg).on(new CodeToTest() {
      @Override public void run() {
        inputEventGenerator.releaseKey(null);
      }
    });
  }
}
