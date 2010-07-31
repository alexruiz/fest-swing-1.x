/*
 * Created on Oct 31, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.driver;

import static java.util.Collections.sort;
import static org.fest.swing.driver.JListCellBoundsQuery.cellBounds;
import static org.fest.swing.driver.JListCellCenterQuery.cellCenter;
import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Point;
import java.util.*;

import javax.swing.JList;

import org.fest.swing.annotation.*;
import org.fest.swing.cell.JListCellReader;
import org.fest.swing.edt.GuiQuery;
import org.fest.swing.util.*;

/**
 * Understands lookup of the first item in a <code>{@link JList}</code> whose value matches a given one.
 *
 * @author Alex Ruiz
 */
final class JListMatchingItemQuery {

  @RunsInEDT
  static Point centerOfMatchingItemCell(final JList list, final String value, final JListCellReader cellReader) {
    return execute(new GuiQuery<Point>() {
      @Override protected Point executeInEDT() {
        int itemIndex = matchingItemIndex(list, new StringTextMatcher(value), cellReader);
        return cellCenter(list, cellBounds(list, itemIndex));
      }
    });
  }

  @RunsInCurrentThread
  static int matchingItemIndex(JList list, TextMatcher matcher, JListCellReader cellReader) {
    int size = list.getModel().getSize();
    for (int i = 0; i < size; i++)
      if (matcher.isMatching(cellReader.valueAt(list, i))) return i;
    return -1;
  }

  @RunsInEDT
  static List<Integer> matchingItemIndices(final JList list, final TextMatcher matcher,
      final JListCellReader cellReader) {
    return execute(new GuiQuery<List<Integer>>() {
      @Override protected List<Integer> executeInEDT() {
        Set<Integer> indices = new HashSet<Integer>();
        int size = list.getModel().getSize();
        for (int i = 0; i < size; i++)
          if (matcher.isMatching(cellReader.valueAt(list, i))) indices.add(i);
        List<Integer> indexList = new ArrayList<Integer>(indices);
        sort(indexList);
        return indexList;
      }
    });
  }

  @RunsInEDT
  static List<String> matchingItemValues(final JList list, final TextMatcher matcher,
      final JListCellReader cellReader) {
    return execute(new GuiQuery<List<String>>() {
      @Override protected List<String> executeInEDT() {
        List<String> values = new ArrayList<String>();
        int size = list.getModel().getSize();
        for (int i = 0; i < size; i++) {
          String value = cellReader.valueAt(list, i);
          if (matcher.isMatching(value)) values.add(value);
        }
        return values;
      }
    });
  }

  private JListMatchingItemQuery() {}
}
