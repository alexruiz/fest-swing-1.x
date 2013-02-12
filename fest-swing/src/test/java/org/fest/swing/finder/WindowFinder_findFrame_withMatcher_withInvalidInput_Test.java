/*
 * Created on Jul 30, 2007
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
package org.fest.swing.finder;

import javax.swing.JFrame;

import org.fest.swing.core.GenericTypeMatcher;
import org.junit.Test;

/**
 * Tests for {@link WindowFinder#findFrame(GenericTypeMatcher)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class WindowFinder_findFrame_withMatcher_withInvalidInput_Test {
  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_matcher_is_null() {
    GenericTypeMatcher<JFrame> matcher = null;
    WindowFinder.findFrame(matcher);
  }

  @Test(expected = IllegalArgumentException.class)
  public void should_throw_error_if_timeout_is_negative() {
    WindowFinder.findFrame(new MyMatcher()).withTimeout(-20);
  }

  @Test(expected = NullPointerException.class)
  public void should_throw_error_if_time_unit_is_null() {
    WindowFinder.findFrame(new MyMatcher()).withTimeout(10, null);
  }

  private static class MyMatcher extends GenericTypeMatcher<JFrame> {
    private MyMatcher() {
      super(JFrame.class);
    }

    @Override
    protected boolean isMatching(JFrame frame) {
      return false;
    }
  }
}
