/*
 * Created on Apr 28, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.fixture;

import static org.fest.swing.edt.GuiActionRunner.execute;

import java.awt.Container;

import javax.swing.JFrame;

import org.fest.swing.annotation.RunsInEDT;
import org.fest.swing.core.Robot;
import org.fest.swing.edt.GuiQuery;

/**
 * Understands utility methods related to <code>{@link Container}</code>s.
 * @since 1.2
 *
 * @author Alex Ruiz
 */
public final class Containers {

  /** Name of the <code>JFrame</code>s created by this class. */
  public static final String CREATED_FRAME_NAME = "org.fest.swing.CreatedFrameForContainer";

  /**
   * Creates a new <code>{@link JFrame}</code> and uses the given <code>{@link Container}</code> as its content pane.
   * The created <code>JFrame</code> is wrapped and displayed by a <code>{@link FrameFixture}</code>.
   * <p>
   * <strong>Note:</strong>This method creates a new <code>{@link Robot}</code>. When using this method, please do not
   * create any additional instances of <code>Robot</code>. Only one instance of <code>Robot</code> can exist per
   * test class.
   * </p>
   * @param contentPane the {@code Container} to use as content pane for the <code>JFrame</code> to create.
   * @return the created <code>FrameFixture</code>.
   * @see #frameFor(Container)
   */
  @RunsInEDT
  public static FrameFixture showInFrame(Container contentPane) {
    FrameFixture frameFixture = frameFixtureFor(contentPane);
    frameFixture.show();
    return frameFixture;
  }

  /**
   * Creates a new <code>{@link JFrame}</code> and uses the given <code>{@link Container}</code> as its content pane.
   * The created <code>JFrame</code> is wrapped by a <code>{@link FrameFixture}</code>. Unlike
   * <code>{@link #showInFrame(Container)}</code>, this method does <strong>not</strong> display the created
   * <code>JFrame</code>.
   * <p>
   * <strong>Note:</strong>This method creates a new <code>{@link Robot}</code>. When using this method, please do not
   * create any additional instances of <code>Robot</code>. Only one instance of <code>Robot</code> can exist per
   * test class.
   * </p>
   * @param contentPane the {@code Container} to use as content pane for the <code>JFrame</code> to create.
   * @return the created <code>FrameFixture</code>.
   * @see #frameFor(Container)
   */
  @RunsInEDT
  public static FrameFixture frameFixtureFor(Container contentPane) {
    return new FrameFixture(frameFor(contentPane));
  }

  /**
   * Creates a new <code>{@link JFrame}</code> and uses the given <code>{@link Container}</code> as its content pane.
   * The created <code>JFrame</code> has the name specified by <code>{@link #CREATED_FRAME_NAME}</code>. This method
   * is executed in the Event Dispatch Thread (EDT.)
   * @param contentPane the {@code Container} to use as content pane for the <code>JFrame</code> to create.
   * @return the created <code>JFrame</code>.
   */
  @RunsInEDT
  public static JFrame frameFor(final Container contentPane) {
    return execute(new GuiQuery<JFrame>() {
      protected JFrame executeInEDT() throws Throwable {
        JFrame frame = new JFrame("Created by FEST");
        frame.setName(CREATED_FRAME_NAME);
        frame.setContentPane(contentPane);
        return frame;
      }
    });
  }

  private Containers() {}
}
