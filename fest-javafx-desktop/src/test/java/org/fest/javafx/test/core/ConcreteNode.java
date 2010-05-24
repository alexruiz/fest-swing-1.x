/*
 * Created on May 12, 2010
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
package org.fest.javafx.test.core;

import static org.fest.javafx.threading.GuiActionRunner.execute;

import org.fest.javafx.annotations.RunsInCurrentThread;
import org.fest.javafx.annotations.RunsInUIThread;
import org.fest.javafx.threading.GuiQuery;

import com.sun.javafx.geom.Bounds2D;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.sg.PGNode;

import javafx.scene.Node;

/**
 * Understands a dummy <code>{@link Node}</code> for testing.
 *
 * @author Alex Ruiz
 */
public class ConcreteNode extends Node {

  @RunsInUIThread
  public static ConcreteNode createNode() {
    return execute(new GuiQuery<ConcreteNode>() {
      @Override protected ConcreteNode executeInUIThread() {
        return new ConcreteNode();
      }
    });
  }

  private ConcreteNode() {}

  @RunsInUIThread
  @Override public boolean set$visible(final boolean visible) {
    return execute(new GuiQuery<Boolean>() {
      @Override protected Boolean executeInUIThread() {
        return superSetVisible(visible);
      }
    });
  }

  @RunsInCurrentThread
  private boolean superSetVisible(boolean visible) {
    return super.set$visible(visible);
  }

  @RunsInUIThread
  @Override public String set$id(final String id) {
    return execute(new GuiQuery<String>() {
      @Override  protected String executeInUIThread() {
        return superSetId(id);
      }
    });
  }

  @RunsInCurrentThread
  private String superSetId(String id) {
    return super.set$id(id);
  }

  @Override public boolean impl_computeContains(float arg0, float arg1) {
    return false;
  }

  @Override public Bounds2D impl_computeGeomBounds(Bounds2D arg0, BaseTransform arg1) {
    return null;
  }

  @Override public PGNode impl_createPGNode() {
    return null;
  }
}
