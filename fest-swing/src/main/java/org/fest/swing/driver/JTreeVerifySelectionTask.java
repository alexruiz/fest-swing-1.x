/*
 * Created on Mar 10, 2010
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
package org.fest.swing.driver;

import static java.util.Arrays.sort;
import static org.fest.assertions.Fail.fail;
import static org.fest.swing.driver.JTreeMatchingPathQuery.matchingPathWithRootIfInvisible;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.util.Arrays.isEmptyIntArray;
import static org.fest.util.Arrays.*;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Strings.concat;

import java.util.Arrays;

import javax.swing.JTree;
import javax.swing.tree.TreePath;

import org.fest.assertions.Description;
import org.fest.swing.annotation.*;
import org.fest.swing.edt.GuiTask;

/**
 * Understands verification that a <code>{@link JTree}</code> has the expected selection.
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
final class JTreeVerifySelectionTask {

  @RunsInEDT
  static void verifySelection(final JTree tree, final int[] selection, final Description errMsg) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        checkSelection(tree, selection, errMsg);
      }
    });
  }

  @RunsInCurrentThread
  private static void checkSelection(JTree tree, int[] selection, Description errMsg) {
    int[] selectionRows = tree.getSelectionRows();
    if (isEmptyIntArray(selectionRows)) failNoSelection(errMsg);
    sort(selection);
    if (Arrays.equals(selectionRows, selection)) return;
    throw failNotEqualSelection(errMsg, selection, selectionRows);
  }

  private static AssertionError failNotEqualSelection(Description msg, int[] expected, int[] actual) {
    throw fail(concat(
        "[", msg.value(), "] expecting selection:<", format(expected), "> but was:<", format(actual), ">"));
  }

  @RunsInEDT
  static void verifySelection(final JTree tree, final String[] selection, final JTreePathFinder pathFinder,
      final Description errMsg) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        checkSelection(tree, selection, pathFinder, errMsg);
      }
    });
  }

  @RunsInCurrentThread
  private static void checkSelection(JTree tree, String[] selection, JTreePathFinder pathFinder, Description errMsg) {
    TreePath[] selectionPaths = tree.getSelectionPaths();
    if (isEmpty(selectionPaths)) failNoSelection(errMsg);
    int selectionCount = selection.length;
    if (selectionCount != selectionPaths.length) throw failNotEqualSelection(errMsg, selection, selectionPaths);
    for (int i = 0; i < selectionCount; i++) {
      TreePath expected = matchingPathWithRootIfInvisible(tree, selection[i], pathFinder);
      TreePath actual = selectionPaths[i];
      if (!areEqual(expected, actual)) throw failNotEqualSelection(errMsg, selection, selectionPaths);
    }
  }

  private static AssertionError failNotEqualSelection(Description msg, String[] expected, TreePath[] actual) {
    throw fail(concat(
        "[", msg.value(), "] expecting selection:<", format(expected), "> but was:<", format(actual), ">"));
  }

  private static void failNoSelection(final Description errorMessage) {
    fail(concat("[", errorMessage.value(), "] No selection"));
  }


  @RunsInEDT
  static void verifyNoSelection(final JTree tree, final Description errMsg) {
    execute(new GuiTask() {
      @Override protected void executeInEDT() {
        if (tree.getSelectionCount() == 0) return;
        String message = concat(
            "[", errMsg.value(), "] expected no selection but was:<", format(tree.getSelectionPaths()), ">");
        fail(message);
      }
    });
  }

  private JTreeVerifySelectionTask() {}
}
