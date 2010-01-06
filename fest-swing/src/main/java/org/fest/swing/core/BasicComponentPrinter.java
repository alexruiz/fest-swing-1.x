/*
 * Created on Dec 22, 2007
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
 * Copyright @2007-2010 the original author or authors.
 */
package org.fest.swing.core;

import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.format.Formatting.format;
import static org.fest.swing.hierarchy.NewHierarchy.ignoreExistingComponents;

import java.awt.Component;
import java.awt.Container;
import java.io.PrintStream;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.edt.GuiTask;
import org.fest.swing.hierarchy.*;

/**
 * Understands printing the <code>String</code> representation of <code>{@link java.awt.Component}</code>s to
 * facilitate debugging.
 * 
 * @author Alex Ruiz
 */
public final class BasicComponentPrinter implements ComponentPrinter {

  private static final String INDENTATION = "  ";
  
  private static final ComponentMatcher ALWAYS_MATCHES = alwaysMatches();
  
  private static ComponentMatcher alwaysMatches() {
    return new ComponentMatcher() {
      public boolean matches(Component c) {
        return true;
      }
    };
  }
  
  private final ComponentHierarchy hierarchy;

  /**
   * Creates a new <code>{@link BasicComponentPrinter}</code> with a new AWT hierarchy. <code>{@link Component}</code>s
   * created before the created <code>{@link BasicComponentPrinter}</code> cannot be accessed by the created
   * <code>{@link BasicComponentPrinter}</code>.
   * @return the created finder.
   */
  public static ComponentPrinter printerWithNewAwtHierarchy() {
    return new BasicComponentPrinter(ignoreExistingComponents());
  }

  /**
   * Creates a new <code>{@link BasicComponentPrinter}</code> that has access to all the GUI components in the AWT
   * hierarchy.
   * @return the created printer.
   */
  public static ComponentPrinter printerWithCurrentAwtHierarchy() {
    return new BasicComponentPrinter(new ExistingHierarchy());
  }

  /**
   * Creates a new <code>{@link BasicComponentPrinter}</code>.
   * @param hierarchy the component hierarchy to use.
   */
  protected BasicComponentPrinter(ComponentHierarchy hierarchy) {
    this.hierarchy = hierarchy;
  }
  
  /**
   * Returns the component hierarchy used by this printer.
   * @return the component hierarchy used by this printer.
   */
  protected final ComponentHierarchy hierarchy() { return hierarchy; }
  
  /** {@inheritDoc} */
  @RunsInEDT
  public void printComponents(PrintStream out) {
    printComponents(out, ALWAYS_MATCHES);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void printComponents(PrintStream out, Container root) {
    printComponents(out, ALWAYS_MATCHES, root);
  }

  /** {@inheritDoc} */
  @RunsInEDT
  public void printComponents(PrintStream out, Class<? extends Component> type) {
    printComponents(out, type, null);
  }
  
  /** {@inheritDoc} */
  @RunsInEDT
  public void printComponents(PrintStream out, Class<? extends Component> type, Container root) {
    validateNotNull(out);
    if (type == null) throw new NullPointerException("The type to match should not be null");
    print(hierarchy(root), new TypeMatcher(type), out);
  }

  /** ${@inheritDoc} */
  public void printComponents(PrintStream out, ComponentMatcher matcher) {
    printComponents(out, matcher, null);
  }

  /** ${@inheritDoc} */
  public void printComponents(PrintStream out, ComponentMatcher matcher, Container root) {
    validateNotNull(out);
    if (matcher == null) throw new NullPointerException("The matcher to use as filter should not be null");
    print(hierarchy(root), matcher, out);
  }

  private void validateNotNull(PrintStream out) {
    if (out == null) throw new NullPointerException("The output stream should not be null");
  }
  
  private ComponentHierarchy hierarchy(Container root) {
    return root != null ? new SingleComponentHierarchy(root, hierarchy) : hierarchy;
  }
  
  @RunsInEDT
  private static void print(final ComponentHierarchy hierarchy, final ComponentMatcher matcher, final PrintStream out) {
    execute(new GuiTask() {
      protected void executeInEDT() {
        for (Component c : hierarchy.roots()) print(c, hierarchy, matcher, 0, out);
      }
    });
  }
  
  @RunsInCurrentThread
  private static void print(Component c, ComponentHierarchy h, ComponentMatcher matcher, int level,
      PrintStream out) {
    if (matcher.matches(c)) print(c, level, out);
    for (Component child : h.childrenOf(c))
      print(child, h, matcher, level + 1, out);
  }

  @RunsInCurrentThread
  private static void print(Component c, int level, PrintStream out) {
    for (int i = 0; i < level; i++) out.print(INDENTATION);
    out.println(format(c));
  }
}
