/*
 * Created on Apr 8, 2010
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
 * Copyright @2010 the original author or authors.
 */
package org.fest.keyboard.mapping;

import javax.swing.text.*;

/**
 * Understands a document that only accepts a maximum number of characters.
 * 
 * @author Alex Ruiz
 */
class MaxLengthDocument extends PlainDocument {

  private static final long serialVersionUID = 1L;
  private final int maxLength;

  MaxLengthDocument() {
    this(1);
  }
  
  MaxLengthDocument(int maxLength) {
    this.maxLength = maxLength;
  }
  
  @Override public void insertString(int offset, String s, AttributeSet a) throws BadLocationException {
    if (s == null) return;
    removeCurrentTextIfMaxLengthReached(s);
    super.insertString(0, s, a);
  }

  private void removeCurrentTextIfMaxLengthReached(String s) throws BadLocationException {
    int currentLength = getLength();
    if (currentLength + s.length() <= maxLength) return;
    remove(0, currentLength);
  }
}
