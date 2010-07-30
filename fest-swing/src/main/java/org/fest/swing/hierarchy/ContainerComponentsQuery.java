package org.fest.swing.hierarchy;

import static org.fest.util.Collections.list;

import java.awt.*;
import java.util.List;

import org.fest.swing.annotation.RunsInCurrentThread;

/**
 * Understands an action that returns all the components in a given <code>{@link Container}</code> in a
 * <code>{@link List}</code>.
 * @see Container#getComponents()
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
final class ContainerComponentsQuery {

  @RunsInCurrentThread
  static List<Component> componentsOf(Container container) {
    return list(container.getComponents());
  }

  private ContainerComponentsQuery() {}
}