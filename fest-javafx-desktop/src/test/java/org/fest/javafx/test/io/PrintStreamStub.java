/*
 * Created on Nov 22, 2007
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
package org.fest.javafx.test.io;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Understands a stub of <code>{@link PrintStream}</code>.
 *
 * @author Alex Ruiz
 */
public class PrintStreamStub extends PrintStream {

  private final List<String> printed = new ArrayList<String>();

  public PrintStreamStub() {
    super(new ByteArrayOutputStream());
  }

  @Override public void print(String s) {
    System.out.print(s);
  }

  @Override public void println(String s) {
    printed.add(s);
    System.out.println(s);
  }

  public String[] printed() { return printed.toArray(new String[0]); }

  public void clearPrinted() { printed.clear(); }
}
