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
package org.fest.javafx.core;

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
