/*
 * Created on May 10, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010 the original author or authors.
 */
package org.fest.javafx.annotations;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

import javafx.scene.Node;

/**
 * Understands an annotation that documents that a method is accessing <code>{@link Node}</code>s in the current thread.
 * The current thread may or may not be the UI thread.
 *
 * @author Alex Ruiz
 */
@Target( { METHOD, CONSTRUCTOR, TYPE })
@Documented
public @interface RunsInCurrentThread {}
