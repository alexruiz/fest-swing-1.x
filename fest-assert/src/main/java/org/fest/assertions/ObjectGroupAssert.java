/*
 * Created on Jun 28, 2010
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
package org.fest.assertions;

import static org.fest.assertions.Collections.notFound;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Strings.concat;

import java.util.*;

/**
 * Understands a template for assertion methods related to arrays or collections.
 * @param <T> the type of object implementations of this template can verify.
 *
 * @author Yvonne Wang
 */
public abstract class ObjectGroupAssert<T> extends GroupAssert<T> {

  /**
   * Creates a new </code>{@link ObjectGroupAssert}</code>.
   * @param actual the target to verify.
   */
  protected ObjectGroupAssert(T actual) {
    super(actual);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>as</strong>(&quot;name&quot;).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract ObjectGroupAssert<T> as(String description);

  /**
   * Alias for <code>{@link #as(String)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(val).<strong>describedAs</strong>(&quot;name&quot;).isEqualTo(&quot;Frodo&quot;);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  protected abstract ObjectGroupAssert<T> describedAs(String description);

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
  protected abstract ObjectGroupAssert<T> as(Description description);

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
  protected abstract ObjectGroupAssert<T> describedAs(Description description);

  /** {@inheritDoc} */
  protected abstract ObjectGroupAssert<T> overridingErrorMessage(String message);

  /**
   * Verifies that the actual group of objects contains the given objects.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual group of objects is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual group of objects does not contain the given objects.
   */
  public abstract ObjectGroupAssert<T> contains(Object... objects);

  /**
   * Verifies that the actual actual group of objects contains the given objects, in any order.
   * @param objects the objects to look for.
   * @throws AssertionError if the actual actual group of objects is <code>null</code>.
   * @throws NullPointerException if the given array is <code>null</code>.
   * @throws AssertionError if the actual actual group of objects does not contain the given objects.
   */
  protected final void assertContains(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    Collection<Object> notFound = notFoundInActual(objects);
    if (notFound.isEmpty()) return;
    throw failureIfExpectedElementsNotFound(notFound);
  }

  private Collection<Object> notFoundInActual(Object... objects) {
    return notFound(actualAsSet(), objects);
  }

  /**
   * Verifies that the actual group of objects contains the given objects <strong>only</strong>, in any order.
   * @param objects the objects to look for.
   * @return this assertion object.
   * @throws AssertionError if the actual group of objects is <code>null</code>.
   * @throws NullPointerException if the given group of objects is <code>null</code>.
   * @throws AssertionError if the actual group of objects does not contain the given objects, or if the actual group of
   * objects contains elements other than the ones specified.
   */
  public abstract ObjectGroupAssert<T> containsOnly(Object... objects);

  /**
   * Verifies that the actual group of objects contains the given objects <strong>only</strong>, in any order.
   * @param objects the objects to look for.
   * @throws AssertionError if the actual group of objects is <code>null</code>.
   * @throws NullPointerException if the given group of objects is <code>null</code>.
   * @throws AssertionError if the actual group of objects does not contain the given objects, or if the actual
   * group of objects contains elements other than the ones specified.
   */
  protected final void assertContainsOnly(Object... objects) {
    isNotNull();
    validateIsNotNull(objects);
    Set<Object> copy = actualAsSet();
    List<Object> notFound = notFoundInCopy(copy, asSet(objects));
    if (!notFound.isEmpty()) throw failureIfExpectedElementsNotFound(notFound);
    if (copy.isEmpty()) return;
    throw failureIfUnexpectedElementsFound(copy);
  }

  private void validateIsNotNull(Object[] objects) {
    if (objects == null)
      throw new NullPointerException(formattedErrorMessage("the given array of objects should not be null"));
  }

  /**
   * Returns the actual value as a {@code Set}.
   * @return the actual value as a {@code Set}.
   */
  protected abstract Set<Object> actualAsSet();

  private Set<Object> asSet(Object[] objects) {
    Set<Object> s = new HashSet<Object>();
    for (Object o : objects) s.add(o);
    return s;
  }

  private List<Object> notFoundInCopy(Set<Object> copy, Set<Object> objects) {
    List<Object> notFound = new ArrayList<Object>();
    for (Object o : objects) {
      if (!copy.contains(o)) {
        notFound.add(o);
        continue;
      }
      copy.remove(o);
    }
    return notFound;
  }

  private AssertionError failureIfExpectedElementsNotFound(Collection<Object> notFound) {
    failIfCustomMessageIsSet();
    return failure(concat(inBrackets(actual), " does not contain element(s):", inBrackets(notFound)));
  }

  private AssertionError failureIfUnexpectedElementsFound(Collection<Object> unexpected) {
    failIfCustomMessageIsSet();
    return failure(concat("unexpected element(s):", inBrackets(unexpected), " in:", inBrackets(actual)));
  }
}
