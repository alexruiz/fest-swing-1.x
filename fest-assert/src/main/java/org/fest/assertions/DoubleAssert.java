package org.fest.assertions;

import static java.lang.Double.compare;
import static java.lang.Math.abs;
import static org.fest.assertions.ErrorMessages.*;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Strings.concat;

/**
 * Understands Assertion methods for <code>Double</code>. To create a new instance of this class use the
 * method <code>{@link Assertions#assertThat(double)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 */
public class DoubleAssert extends PrimitiveAssert implements NumberAssert {

  private static final double ZERO = 0.0;

  private final double actual;

  /**
   * Creates a new </code>{@link DoubleAssert}</code>.
   * @param actual the target to verify.
   */
  protected DoubleAssert(double actual) {
    this.actual = actual;
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
  public DoubleAssert as(String description) {
    description(description);
    return this;
  }

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
  public DoubleAssert describedAs(String description) {
    return as(description);
  }

  /**
   * Sets the description of the actual value, to be used in as message of any <code>{@link AssertionError}</code>
   * thrown when an assertion fails. This method should be called before any assertion method, otherwise any assertion
   * failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(value).<strong>as</strong>(new BasicDescription(&quot;Some value&quot;)).isEqualTo(otherValue);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public DoubleAssert as(Description description) {
    description(description);
    return this;
  }

  /**
   * Alias for <code>{@link #as(Description)}</code>, since "as" is a keyword in
   * <a href="http://groovy.codehaus.org/" target="_blank">Groovy</a>. This method should be called before any assertion
   * method, otherwise any assertion failure will not show the provided description.
   * <p>
   * For example:
   * <pre>
   * assertThat(value).<strong>describedAs</strong>(new BasicDescription(&quot;Some value&quot;)).isEqualTo(otherValue);
   * </pre>
   * </p>
   * @param description the description of the actual value.
   * @return this assertion object.
   */
  public DoubleAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual <code>double</code> value is equal to the given one.
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is not equal to the given one.
   */
  public DoubleAssert isEqualTo(double expected) {
    if (compareTo(expected) == 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual <code>double</code> value is not equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is equal to the given one.
   */
  public DoubleAssert isNotEqualTo(double other) {
    if (compareTo(other) != 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, other));
  }

  /**
   * Verifies that the actual <code>double</code> value is greater than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is not greater than the given one.
   */
  public DoubleAssert isGreaterThan(double other) {
    if (compareTo(other) > 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual <code>double</code> value is less than the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is not less than the given one.
   */
  public DoubleAssert isLessThan(double other) {
    if (compareTo(other) < 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual <code>double</code> value is greater or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is not greater than or equal to the given one.
   */
  public DoubleAssert isGreaterThanOrEqualTo(double other) {
    if (compareTo(other) >= 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual <code>double</code> value is less or equal to the given one.
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is not less than or equal to the given one.
   */
  public DoubleAssert isLessThanOrEqualTo(double other) {
    if (compareTo(other) <= 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual <code>double</code> value is equal to zero.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is not equal to zero.
   */
  public DoubleAssert isZero() {
    return isEqualTo(ZERO);
  }

  /**
   * Verifies that the actual <code>double</code> value is positive.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is not positive.
   */
  public DoubleAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual <code>double</code> value is negative.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is not negative.
   */
  public DoubleAssert isNegative() {
    return isLessThan(ZERO);
  }

  /**
   * Verifies that the actual <code>double</code> value is equal to <code>{@link Double#NaN}</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is not equal to <code>NAN</code>.
   */
  public DoubleAssert isNaN() {
    return isEqualTo(Double.NaN);
  }

  /**
   * Verifies that the actual <code>double</code> value is equal to the given one, within a positive delta.
   * @param expected the value to compare the actual one to.
   * @param delta the given delta.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is not equal to the given one.
   * @deprecated use method <code>{@link #isEqualTo(double, org.fest.assertions.Delta)}</code> instead. This method will
   * be removed in version 2.0.
   */
  @Deprecated
  public DoubleAssert isEqualTo(double expected, Delta delta) {
    return isEqualTo(expected, delta.value);
  }

  /**
   * Verifies that the actual <code>double</code> value is equal to the given one, within a positive delta.
   * @param expected the value to compare the actual one to.
   * @param delta the given delta.
   * @return this assertion object.
   * @throws AssertionError if the actual <code>double</code> value is not equal to the given one.
   * @since 1.1
   */
  public DoubleAssert isEqualTo(double expected, org.fest.assertions.Delta delta) {
    return isEqualTo(expected, delta.doubleValue());
  }

  private DoubleAssert isEqualTo(double expected, double deltaValue) {
    if (compareTo(expected) == 0) return this;
    if (abs(expected - actual) <= deltaValue) return this;
    failIfCustomMessageIsSet();
    throw failure(concat(unexpectedNotEqual(actual, expected), " using delta:", inBrackets(deltaValue)));
  }

  private int compareTo(double other) {
    return compare(actual, other);
  }

  /**
   * Creates a new holder for a delta value to be used in
   * <code>{@link DoubleAssert#isEqualTo(double, org.fest.assertions.DoubleAssert.Delta)}</code>.
   * @param d the delta value.
   * @return a new delta value holder.
   * @deprecated use method <code>{@link org.fest.assertions.Delta#delta(double)}</code> instead. This method will be
   * removed in version 2.0.
   */
  @Deprecated
  public static Delta delta(double d) {
    return new Delta(d);
  }

  /**
   * Holds a delta value to be used in
   * <code>{@link DoubleAssert#isEqualTo(double, org.fest.assertions.DoubleAssert.Delta)}</code>.
   * @deprecated use top-level class <code>{@link org.fest.assertions.Delta}</code> instead. This class will be removed
   * in version 2.0.
   */
  @Deprecated
  public static class Delta {
    final double value;

    private Delta(double value) {
      this.value = value;
    }
  }

  /** {@inheritDoc} */
  public DoubleAssert overridingErrorMessage(String message) {
    replaceDefaultErrorMessagesWith(message);
    return this;
  }
}
