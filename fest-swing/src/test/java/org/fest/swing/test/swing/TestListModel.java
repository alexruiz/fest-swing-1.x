/*
 * Created on Oct 2, 2008
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
 * Copyright @2008-2013 the original author or authors.
 */
package org.fest.swing.test.swing;

import javax.swing.DefaultListModel;

/**
 * Understands a simplified version of {@link DefaultListModel}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class TestListModel extends DefaultListModel {
  private static final long serialVersionUID = 1L;

  public TestListModel(Object... elements) {
    setElements(elements);
  }

  public void setElements(Object... elements) {
    clear();
    if (elements == null) {
      throw new NullPointerException("The array of elements should not be null");
    }
    for (Object e : elements) {
      addElement(e);
    }
  }
}
