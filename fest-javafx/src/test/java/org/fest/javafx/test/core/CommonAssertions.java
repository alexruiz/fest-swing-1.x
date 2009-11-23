/*
 * Created on Feb 19, 2008
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
package org.fest.javafx.test.core;

import static org.fest.assertions.Fail.fail;

/**
 * Understands common assertions using in the test suite.
 *
 * @author Alex Ruiz 
 */
public final class CommonAssertions {

  public static void failWhenExpectingException() {
    fail("Expecting exception");
  }
  
  private CommonAssertions() {}
}
