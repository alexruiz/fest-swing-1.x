/*
 * Created on Aug 23, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.driver;

import static org.fest.util.Strings.concat;
import static org.fest.util.Strings.quote;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTree;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import org.fest.swing.annotation.RunsInCurrentThread;
import org.fest.swing.cell.JTreeCellReader;
import org.fest.swing.exception.LocationUnavailableException;

/**
 * Understands lookup of <code>{@link TreePath}</code>s which text matches the given one.
 *
 * @author Alex Ruiz
 */
class JTreePathFinder {

  // TODO TEST
  private static final String SEPARATOR = "/";

  private JTreeCellReader cellReader;
  private String separator;

  JTreePathFinder() {
    cellReader(new BasicJTreeCellReader());
    separator(SEPARATOR);
  }

  @RunsInCurrentThread
  TreePath findMatchingPath(JTree tree, String path) {
    String[] pathStrings = splitPath(path);
    TreeModel model = tree.getModel();
    List<Object> newPathValues = new ArrayList<Object>(pathStrings.length + 1);
    Object node = model.getRoot();
    int pathElementCount = pathStrings.length;
    for (int stringIndex = 0; stringIndex < pathElementCount; stringIndex++) {
      String pathString = pathStrings[stringIndex];
      Object match = null;
      if (stringIndex == 0 && tree.isRootVisible()) {
        if (!pathString.equals(value(tree, node))) throw pathNotFound(path);
        newPathValues.add(node);
        continue;
      }
      int childCount = model.getChildCount(node);
      for (int childIndex = 0; childIndex < childCount; childIndex++) {
        Object child = model.getChild(node, childIndex);
        if (pathString.equals(value(tree, child))) {
          if (match != null) throw multipleMatchingNodes(pathString, value(tree, node));
          match = child;
        }
      }
      if (match == null) throw pathNotFound(path);
      newPathValues.add(match);
      node = match;
    }
    return new TreePath(newPathValues.toArray());
  }

  private LocationUnavailableException pathNotFound(String path) {
    throw new LocationUnavailableException(concat("Unable to find path ", quote(path)));
  }

  private String[] splitPath(String path) {
    List<String> result = new ArrayList<String>();
    int separatorSize = separator.length();
    int index = 0;
    int pathSize = path.length();
    while (index < pathSize) {
      int separatorPosition = path.indexOf(separator, index);
      if (separatorPosition == -1) separatorPosition = pathSize;
      result.add(path.substring(index, separatorPosition));
      index = separatorPosition + separatorSize;
    }
    return result.toArray(new String[result.size()]);
  }

  private LocationUnavailableException multipleMatchingNodes(String matchingText, Object parentText) {
    throw new LocationUnavailableException(
        concat("There is more than one node with value ", quote(matchingText), " under ", quote(parentText)));
  }

  private String value(JTree tree, Object modelValue) {
    return cellReader.valueAt(tree, modelValue);
  }

  String separator() {
    return separator;
  }

  void separator(String newSeparator) {
    separator = newSeparator;
  }

  void cellReader(JTreeCellReader newCellReader) {
    cellReader = newCellReader;
  }

  JTreeCellReader cellReader() { return cellReader; }
}
