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
import static org.fest.javafx.test.builder.SwingButtons.button;
import static org.fest.util.Strings.concat;

/**
 * Tests for <code>{@link SwingButtonNodeMatcher}</code>.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@Test public class SwingButtonNodeMatcherTest {

  @BeforeClass public void setUpOnce() {
    FailOnThreadViolationRepaintManager.install();
  }

  public void shouldMatchNodeByIdOnly() {
    String id = "buttonId";
    FXNode node = button().withId(id).createNew();
    SwingButtonNodeMatcher m = SwingButtonNodeMatcher.buttonWithId(id);
    assertThat(m.matches(node)).isTrue();
  }

  public void shouldNotMatchIfIdNotMatchingWhenUsingOnlyId() {
    FXNode node = button().withId("buttonId").createNew();
    SwingButtonNodeMatcher m = SwingButtonNodeMatcher.buttonWithId("anotherId");
    assertThat(m.matches(node)).isFalse();
  }

  public void shouldMatchNodeByIdAndButtonText() {
    String id = "buttonId";
    String text = "Hello";
    FXNode node = button().withId(id).withText(text).createNew();
    SwingButtonNodeMatcher m = SwingButtonNodeMatcher.buttonWithId(id).andText(text);
    assertThat(m.matches(node)).isTrue();
  }

  @Test(dataProvider = "notMatchingIdAndButtonText")
  public void shouldNotMatchIfIdAndTextNotMaching(String id, String buttonText) {
    FXNode node = button().withId("someId").withText("someText").createNew();
    SwingButtonNodeMatcher m = SwingButtonNodeMatcher.buttonWithId(id).andText(buttonText);
    assertThat(m.matches(node)).isFalse();
  }

  @DataProvider(name = "notMatchingIdAndButtonText")
  public Object[][] notMatchingIdAndButtonText() {
    return new Object[][] {
         { "anotherId", "text" },
         { "id", "anotherText" },
         { "anotherId", "anotherText" }
    };
  }

  public void shouldMatchAnyIfIdAndTextNotSpecified() {
    FXNode node = button().withId("someId").withText("someText").createNew();
    SwingButtonNodeMatcher m = SwingButtonNodeMatcher.any();
    assertThat(m.matches(node)).isTrue();
  }

  public void shouldMatchTextOnly() {
    String text = "Hello";
    FXNode node = button().withText(text).createNew();
    SwingButtonNodeMatcher m = SwingButtonNodeMatcher.buttonWithText(text);
    assertThat(m.matches(node)).isTrue();
  }

  public void shouldNotMatchIfTextNotMatchingWhenUsingOnlyText() {
    FXNode node = button().withText("Bye").createNew();
    SwingButtonNodeMatcher m = SwingButtonNodeMatcher.buttonWithText("Hello");
    assertThat(m.matches(node)).isFalse();
  }

  public void shouldShowAnyInToStringForMissingIdAndText() {
    SwingButtonNodeMatcher m = SwingButtonNodeMatcher.any();
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id=<Any>,text=<Any>]"));
  }

  public void shouldShowAnyInToStringForMissingId() {
    SwingButtonNodeMatcher m = SwingButtonNodeMatcher.buttonWithText("Hello");
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id=<Any>,text='Hello']"));
  }

  public void shouldShowAnyInToStringForMissingText() {
    SwingButtonNodeMatcher m = SwingButtonNodeMatcher.buttonWithId("hello");
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id='hello',text=<Any>]"));
  }

  public void shouldShowInToStringSpecifiedIdAndText() {
    SwingButtonNodeMatcher m = SwingButtonNodeMatcher.buttonWithId("hello").andText("Hello");
    assertThat(m.toString()).isEqualTo(concat(m.getClass().getName(), "[id='hello',text='Hello']"));
  }
}
