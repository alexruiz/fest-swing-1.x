/*
 * Created on Sep 16, 2007
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
 * Copyright @2007-2009 the original author or authors.
 */
package org.fest.assertions;

/**
 * Understands a template for assertion methods for primitive values.
 *
 * @author Yvonne Wang
 */
public abstract class PrimitiveAssert<T> extends GenericAssert<T> {

  /**
   * Creates a new <code>{@link PrimitiveAssert}</code>.
   * @param actual the actual target to verify.
   */
  protected PrimitiveAssert(T actual) {
    super(actual);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(value).<strong>as</strong>(&quot;Some value&quot;).isEqualTo(otherValue);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract PrimitiveAssert<T> as(String description);

  /**
   * Alias for <code>{@link #as(String)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(value).<strong>describedAs</strong>(&quot;Some value&quot;).isEqualTo(otherValue);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract PrimitiveAssert<T> describedAs(String description);

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>as</strong>(new BasicDescription(&quot;name&quot;)).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract PrimitiveAssert<T> as(Description description);

  /**
   * Alias for <code>{@link #as(Description)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>describedAs</strong>(new BasicDescription(&quot;name&quot;)).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract PrimitiveAssert<T> describedAs(Description description);

  /**
   * Replaces the default message displayed in case of a failure with the given one.
   * <p>
   * For example, the following assertion:
   * <pre>
   * assertThat("Hello").isEqualTo("Bye");
   * </pre>
   * will fail with the default message "<em>expected:<'[Bye]'> but was:<'[Hello]'></em>."
   * </p>
   * <p>
   * We can replace this message with our own:
   * <pre>
   * assertThat("Hello").overridingErrorMessage("'Hello' should be equal to 'Bye'").isEqualTo("Bye");
   * </pre>
   * in this case, the assertion will fail showing the message "<em>'Hello' should be equal to 'Bye'</em>".
   * </p>
   * @param message the given error message, which will replace the default one.
   * @return this assertion.
   * @since 1.2
   */
  protected abstract PrimitiveAssert<T> overridingErrorMessage(String message);
}
