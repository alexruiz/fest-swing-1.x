/*
 * Created on May 24, 2010
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
package org.fest.javafx.core;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.javafx.test.builder.Nodes.node;
import static org.fest.javafx.threading.GuiActionRunner.execute;
import static org.fest.javafx.util.Sequences.emptySequence;
import static org.fest.javafx.util.Sequences.sequence;
import static org.fest.util.Collections.isEmpty;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.Node;
import javafx.scene.Parent;

import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.hierarchy.NodeHierarchy;
import org.fest.javafx.test.io.PrintStreamStub;
import org.fest.javafx.threading.GuiQuery;
import org.junit.Before;
import org.junit.Test;

import com.sun.javafx.runtime.sequence.Sequence;

/**
 * Tests for <code>{@link BasicNodePrinter#print(NodeHierarchy, NodeMatcher, java.io.PrintStream)}</code>.
 *
 * @author Alex Ruiz
 */
public class BasicNodePrinter_print_inHierarchyWithMatcher_Test {

  private List<Node> nodes;
  private TestParent parent;
  private NodeHierarchyStub hierarchy;
  private PrintStreamStub out;
  private NodeMatcher matcher;
  private BasicNodePrinter printer;

  @Before
  public void setUp() {
    nodes = new ArrayList<Node>();
    nodes.add(node().withId("1").createNew());
    nodes.add(node().withId("2").createNew());
    parent = TestParent.createNewWithId("3");
    parent.addChild(node().withId("3.1").createNew());
    parent.addChild(node().withId("3.2").createNew());
    nodes.add(parent);
    hierarchy = new NodeHierarchyStub(nodes);
    out = new PrintStreamStub();
    matcher = new BasicNodePrinter.AlwaysMatches();
    printer = new BasicNodePrinter();
  }

  @Test
  public void should_print_matching_nodes() {
    printer.print(hierarchy, matcher, out);
    String[] printed = out.printed();
    assertThat(printed).hasSize(5);
    assertThat(printed[0]).contains("id='1'");
    assertThat(printed[1]).contains("id='2'");
    assertThat(printed[2]).contains("id='3'");
    assertThat(printed[3]).contains("id='3.1'");
    assertThat(printed[4]).contains("id='3.2'");
  }

  static class NodeHierarchyStub implements NodeHierarchy {
    private final List<Node> contents = new ArrayList<Node>();

    NodeHierarchyStub(List<Node> contents) {
      this.contents.addAll(contents);
    }

    @Override public Sequence<? extends Node> contents() {
      return sequence(Node.class, contents);
    }

    @Override public Sequence<? extends Node> childrenOf(Node parent) {
      if (!(parent instanceof Parent)) return emptySequence(Node.class);
      return ((Parent)parent).get$children();
    }
  }

  private static class TestParent extends Parent {
    private final List<Node> children = new ArrayList<Node>();

    @RunsInUIThread
    static TestParent createNewWithId(final String id) {
      return execute(new GuiQuery<TestParent>() {
        @Override protected TestParent executeInUIThread() {
          TestParent parent = new TestParent();
          parent.set$id(id);
          return parent;
        }
      });
    }

    void addChild(Node child) {
      children.add(child);
    }

    @Override public Sequence<? extends Node> get$children() {
      if (isEmpty(children)) return super.get$children();
      return sequence(Node.class, children);
    }
  }
}
