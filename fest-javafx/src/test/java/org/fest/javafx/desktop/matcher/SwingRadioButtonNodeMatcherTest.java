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
import static org.fest.javafx.test.builder.SwingRadioButtons.radioButton;
import static org.fest.util.Strings.concat;

/**
 * Tests for <code>{@link SwingRadioButtonNodeMatcher}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@Test public class SwingRadioButtonNodeMatcherTest {

  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  public void shouldMatchNodeByIdOnly() {
    String id = "radioButtonId";
    FXNode node = radioButton().withId(id).createNew();
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.radioButtonWithId(id);
    assertThat(m.matches(node)).isTrue();
  }

  public void shouldNotMatchIfIdNotMatchingWhenUsingOnlyId() {
    FXNode node = radioButton().withId("radioButtonId").createNew();
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.radioButtonWithId("anotherId");
    assertThat(m.matches(node)).isFalse();
  }

  public void shouldMatchNodeByIdAndText() {
    String id = "radioButtonId";
    String text = "Hello";
    FXNode node = radioButton().withId(id).withText(text).createNew();
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.radioButtonWithId(id).andText(text);
    assertThat(m.matches(node)).isTrue();
  }

  public void shouldMatchNodeByIdTextAndSelected() {
    String id = "radioButtonId";
    String text = "Hello";
    boolean selected = true;
    FXNode node = radioButton().withId(id).withText(text).selected(selected).createNew();
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.radioButtonWithId(id).andText(text).andSelected(selected);
    assertThat(m.matches(node)).isTrue();
  }
  
  @Test(dataProvider = "notMatchingIdAndRadioButtonText")
  public void shouldNotMatchIfIdAndTextNotMaching(String id, String radioButtonText) {
    FXNode node = radioButton().withId("someId").withText("someText").createNew();
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.radioButtonWithId(id).andText(radioButtonText);
    assertThat(m.matches(node)).isFalse();
  }

  public void shouldNotMatchIfSelectedNotMatching() {
    FXNode node = radioButton().withId("id").withText("someText").selected(false).createNew();
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.radioButtonWithId("id").andSelected(true);
    assertThat(m.matches(node)).isFalse();
  }

  @DataProvider(name = "notMatchingIdAndRadioButtonText")
  public Object[][] notMatchingIdAndRadioButtonText() {
    return new Object[][] {
         { "anotherId", "text" },
         { "id", "anotherText" },
         { "anotherId", "anotherText" }
    };
  }

  public void shouldMatchAnyIfIdAndTextNotSpecified() {
    FXNode node = radioButton().withId("someId").withText("someText").createNew();
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.any();
    assertThat(m.matches(node)).isTrue();
  }

  public void shouldMatchByTextOnly() {
    String text = "Hello";
    FXNode node = radioButton().withText(text).createNew();
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.radioButtonWithText(text);
    assertThat(m.matches(node)).isTrue();
  }

  public void shouldNotMatchIfTextNotMatchingAndUsingOnlyText() {
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.radioButtonWithText("Hello");
    FXNode node = radioButton().withText("Bye").createNew();
    assertThat(m.matches(node)).isFalse();
  }

  public void shouldShowAnyInToStringForMissingAllCriteria() {
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.any();
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id=<Any>,text=<Any>,selected=<Any>]"));
  }

  public void shouldShowAnyInToStringForMissingIdAndSelected() {
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.radioButtonWithText("Hello");
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id=<Any>,text='Hello',selected=<Any>]"));
  }

  public void shouldShowAnyInToStringForMissingTextAndSelected() {
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.radioButtonWithId("hello");
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id='hello',text=<Any>,selected=<Any>]"));
  }

  public void shouldShowInToStringSpecifiedCriteria() {
    SwingRadioButtonNodeMatcher m = SwingRadioButtonNodeMatcher.radioButtonWithId("hello").andText("Hello").andSelected(true);
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id='hello',text='Hello',selected=true]"));
  }
}
