/*
 * Created on Jul 13, 2010
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
package org.fest.javafx.maven;

/**
 * Understands an option that indicates whether JavaFX's jars should be automatically added or not.
 *
 * @author Alex Ruiz
 */
enum JavaFxJarsInclusion {

  AUTOMATIC(true), MANUAL(false);

  private final boolean automaticInclusion;

  JavaFxJarsInclusion(boolean automaticInclusion) {
    this.automaticInclusion = automaticInclusion;
  }

  boolean isAutomatic() {
    return automaticInclusion;
  }

  static JavaFxJarsInclusion javaFxJarsInclusion(boolean automatic) {
    if (automatic) return AUTOMATIC;
    return MANUAL;
  }
}
