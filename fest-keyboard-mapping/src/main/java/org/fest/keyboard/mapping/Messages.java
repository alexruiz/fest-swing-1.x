/*
 * Created on May 1, 2010
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
package org.fest.keyboard.mapping;

import java.awt.Font;

/**
 * Understands messages used in the application.
 *
 * @author Alex Ruiz
 */
class Messages {

  private static final int STARTING_YEAR = 2007;

  String fontStyleAsCSS(Font font) {
    StringBuilder b = new StringBuilder();
    b.append("font-family:'").append(font.getFamily()).append("';")
     .append("font-size:").append(font.getSize()).append(";");
    return b.toString();
  }

  String copyrightYears(int currentYear) {
    StringBuilder b = new StringBuilder();
    b.append(STARTING_YEAR).append("-").append(currentYear);
    return b.toString();
  }

  String hyperlink(Platform platform, String url) {
    if (!platform.isBrowsingSupported()) return url;
    StringBuilder b = new StringBuilder();
    b.append("<a href=\"").append(url).append("\">").append(url).append("</a>");
    return b.toString();
  }

}
