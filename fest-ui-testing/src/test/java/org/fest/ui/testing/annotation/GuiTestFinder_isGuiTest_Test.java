/*
 * Created on May 27, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.ui.testing.annotation;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.reflect.core.Reflection.method;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link GuiTestFinder#isGuiTest(Class, Method)}</code>.
 *
 * @author Alex Ruiz
 */
public class GuiTestFinder_isGuiTest_Test {

  @GuiTest
  public static class GuiTestClass {
    @GuiTest
    public void guiTestMethodWithAnnotation() {}
    public void guiTestMethodWithoutAnnotation() {}
  }

  public static class NonGuiTestClass {
    @GuiTest public void guiTestMethod() {}
  }

  public static class GuiTestSubclass extends GuiTestClass {
    @Override public void guiTestMethodWithoutAnnotation() {
      super.guiTestMethodWithoutAnnotation();
    }
  }

  public static class NonGuiTestSubclass extends NonGuiTestClass {
    @Override public void guiTestMethod() {
      super.guiTestMethod();
    }
  }

  private GuiTestClass guiTest;
  private NonGuiTestClass nonGuiTest;
  private GuiTestSubclass guiTestSubclass;
  private NonGuiTestSubclass nonGuiTestSubclass;

  @Before public void setUp() throws Exception {
    guiTest = new GuiTestClass();
    nonGuiTest = new NonGuiTestClass();
    guiTestSubclass = new GuiTestSubclass();
    nonGuiTestSubclass = new NonGuiTestSubclass();
  }

  @Test public void should_return_true_if_class_has_GuiTest_annotation() {
    Class<? extends GuiTestClass> guiTestType = guiTest.getClass();
    Method guiTestMethod = method("guiTestMethodWithoutAnnotation").in(guiTest).info();
    boolean isGuiTest = GuiTestFinder.isGuiTest(guiTestType, guiTestMethod);
    assertThat(isGuiTest).isTrue();
  }

  @Test public void should_return_true_if_only_one_method_has_GuiTest_annotation() {
    Class<? extends NonGuiTestClass> nonGuiTestType = nonGuiTest.getClass();
    Method guiTestMethod = method("guiTestMethod").in(nonGuiTest).info();
    boolean isGuiTest = GuiTestFinder.isGuiTest(nonGuiTestType, guiTestMethod);
    assertThat(isGuiTest).isTrue();
  }

  @Test public void should_return_true_if_superclass_is_Gui_test() {
    Class<? extends GuiTestSubclass> guiTestSubtype = guiTestSubclass.getClass();
    Method guiTestMethod = method("guiTestMethodWithoutAnnotation").in(guiTestSubclass).info();
    boolean isGuiTest = GuiTestFinder.isGuiTest(guiTestSubtype, guiTestMethod);
    assertThat(isGuiTest).isTrue();
  }

  @Test public void should_return_true_if_overriden_method_is_Gui_test() {
    Class<? extends NonGuiTestSubclass> nonGuiTestSubtype = nonGuiTestSubclass.getClass();
    Method guiTestMethod = method("guiTestMethod").in(nonGuiTestSubclass).info();
    boolean isGuiTest = GuiTestFinder.isGuiTest(nonGuiTestSubtype, guiTestMethod);
    assertThat(isGuiTest).isTrue();
  }

  @Test public void should_return_false_if_not_containing_GuiTest_annotation() {
    String s = "Yoda";
    Method concat = method("concat").withReturnType(String.class).withParameterTypes(String.class).in(s).info();
    assertThat(GuiTestFinder.isGuiTest(s.getClass(), concat)).isFalse();
  }
}
