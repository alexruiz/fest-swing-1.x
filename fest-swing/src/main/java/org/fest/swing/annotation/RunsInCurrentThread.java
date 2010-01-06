/*
 * Created on Oct 28, 2008
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
 * Copyright @2008-2010 the original author or authors.
 */
package org.fest.swing.annotation;

import static java.lang.annotation.ElementType.*;

import java.lang.annotation.Documented;
import java.lang.annotation.Target;

/**
 * Understands an annotation that documents that a method is accessing GUI components in a thread other than the event
 * dispatch thread.
 * <p>
 * If this annotation is used at a type level:
 * <ol>
 * <li>
 * <b>class:</b> all methods in a class are accessing GUI components in a thread other than the event dispatch thread.
 * </li>
 * <li>
 * <b>interface:</b> methods in an interface that access GUI components should do it in a thread other than the event
 * dispatch thread.
 * </li>
 * </ol>
 * </p>
 * <p>
 * <b>Note:</b> Clients are responsible for calling methods marked with this annotation in the event dispatch thread.
 *
 * @author Alex Ruiz
 */
@Target( { METHOD, CONSTRUCTOR, TYPE })
@Documented
public @interface RunsInCurrentThread {}
