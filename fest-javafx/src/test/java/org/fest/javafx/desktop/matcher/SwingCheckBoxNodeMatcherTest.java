/*
 * Created on Jan 11, 2009
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 2 of
 * the License. You may obtain a copy of the License at
 *
 * http://www.gnu.org/licenses/gpl-2.0.txt
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See
 * the GNU General Public License for more details.
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.javafx.desktop.matcher;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.sun.scenario.scenegraph.fx.FXNode;

import org.fest.swing.edt.FailOnThreadViolationRepaintManager;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.test.builder.SwingCheckBoxes.checkBox;
import static org.fest.util.Strings.concat;

/**
 * Tests for <code>{@link SwingCheckBoxNodeMatcher}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@Test public class SwingCheckBoxNodeMatcherTest {

  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  public void shouldMatchNodeByIdOnly() {
    String id = "checkBoxId";
    FXNode node = checkBox().withId(id).createNew();
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.checkBoxWithId(id);
    assertThat(m.matches(node)).isTrue();
  }

  public void shouldNotMatchIfIdNotMatchingWhenUsingOnlyId() {
    FXNode node = checkBox().withId("checkBoxId").createNew();
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.checkBoxWithId("anotherId");
    assertThat(m.matches(node)).isFalse();
  }

  public void shouldMatchNodeByIdAndText() {
    String id = "checkBoxId";
    String text = "Hello";
    FXNode node = checkBox().withId(id).withText(text).createNew();
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.checkBoxWithId(id).andText(text);
    assertThat(m.matches(node)).isTrue();
  }

  public void shouldMatchNodeByIdTextAndSelected() {
    String id = "checkBoxId";
    String text = "Hello";
    boolean selected = true;
    FXNode node = checkBox().withId(id).withText(text).selected(selected).createNew();
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.checkBoxWithId(id).andText(text).andSelected(selected);
    assertThat(m.matches(node)).isTrue();
  }

  
  @Test(dataProvider = "notMatchingIdAndCheckBoxText")
  public void shouldNotMatchIfIdAndTextNotMaching(String id, String checkBoxText) {
    FXNode node = checkBox().withId("someId").withText("someText").createNew();
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.checkBoxWithId(id).andText(checkBoxText);
    assertThat(m.matches(node)).isFalse();
  }

  public void shouldNotMatchIfSelectedNotMatching() {
    FXNode node = checkBox().withId("id").withText("someText").selected(false).createNew();
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.checkBoxWithId("id").andSelected(true);
    assertThat(m.matches(node)).isFalse();
  }

  @DataProvider(name = "notMatchingIdAndCheckBoxText")
  public Object[][] notMatchingIdAndCheckBoxText() {
    return new Object[][] {
         { "anotherId", "text" },
         { "id", "anotherText" },
         { "anotherId", "anotherText" }
    };
  }

  public void shouldMatchAnyIfIdAndTextNotSpecified() {
    FXNode node = checkBox().withId("someId").withText("someText").createNew();
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.any();
    assertThat(m.matches(node)).isTrue();
  }

  public void shouldMatchByTextOnly() {
    String text = "Hello";
    FXNode node = checkBox().withText(text).createNew();
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.checkBoxWithText(text);
    assertThat(m.matches(node)).isTrue();
  }

  public void shouldNotMatchIfTextNotMatchingAndUsingOnlyText() {
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.checkBoxWithText("Hello");
    FXNode node = checkBox().withText("Bye").createNew();
    assertThat(m.matches(node)).isFalse();
  }

  public void shouldShowAnyInToStringForMissingAllCriteria() {
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.any();
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id=<Any>,text=<Any>,selected=<Any>]"));
  }

  public void shouldShowAnyInToStringForMissingIdAndSelected() {
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.checkBoxWithText("Hello");
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id=<Any>,text='Hello',selected=<Any>]"));
  }

  public void shouldShowAnyInToStringForMissingTextAndSelected() {
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.checkBoxWithId("hello");
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id='hello',text=<Any>,selected=<Any>]"));
  }

  public void shouldShowInToStringSpecifiedCriteria() {
    SwingCheckBoxNodeMatcher m = SwingCheckBoxNodeMatcher.checkBoxWithId("hello").andText("Hello").andSelected(true);
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id='hello',text='Hello',selected=true]"));
  }
}
