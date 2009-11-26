/*
 * Created on Nov 25, 2009
 *
 * Copyright @2009 the original author or authors.
 */
package org.fest.swing.driver;

import static javax.swing.JSplitPane.VERTICAL_SPLIT;
import static javax.swing.SwingUtilities.invokeLater;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.swing.edt.GuiActionRunner.execute;
import static org.fest.swing.query.ComponentSizeQuery.sizeOf;

import java.awt.Dimension;

import javax.swing.*;

import org.fest.swing.edt.GuiQuery;
import org.fest.swing.test.core.RobotBasedTestCase;
import org.fest.swing.test.swing.TestWindow;
import org.junit.Test;

/**
 * Test case for bug <a href="http://jira.codehaus.org/browse/FEST-210" target="_blank">FEST-210</a>
 *
 * @author Alex Ruiz
 */
public class FEST210_respectLimitationsOfBasicSplitPaneDividerDragController_Test extends RobotBasedTestCase {

  private JSplitPaneDriver driver;
  private MyWindow window;

  @Override protected void onSetUp() {
    driver = new JSplitPaneDriver(robot);
    window = MyWindow.createNew();
    robot.showWindow(window);
  }

  @Test
  public void should_respect_minimum_size_when_moving_divider() {
    driver.moveDividerTo(window.splitPane, 30);
    driver.moveDividerTo(window.splitPane, 10);
    assertThat(sizeOf(window.leftPanel).height).isGreaterThanOrEqualTo(20);
  }

  private static class MyWindow extends TestWindow {
    private static final long serialVersionUID = 1L;

    static MyWindow createNew() {
      return execute(new GuiQuery<MyWindow>() {
        protected MyWindow executeInEDT() {
          return new MyWindow();
        }
      });
    }

    final JPanel leftPanel = newPanel();
    final JSplitPane splitPane = new JSplitPane(VERTICAL_SPLIT, leftPanel, newPanel());

    MyWindow() {
      super(FEST210_respectLimitationsOfBasicSplitPaneDividerDragController_Test.class);
      splitPane.setPreferredSize(new Dimension(100, 100));
      addComponents(splitPane);
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      setPreferredSize(new Dimension(200, 200));
    }

    private static JPanel newPanel() {
      JPanel panel = new JPanel();
      panel.setMinimumSize(new Dimension(20, 20));
      return panel;
    }
  }

  public static void main(String[] args) {
    invokeLater(new Runnable() {
      public void run() {
        MyWindow w = new MyWindow();
        w.pack();
        w.setVisible(true);
      }
    });
  }
}
