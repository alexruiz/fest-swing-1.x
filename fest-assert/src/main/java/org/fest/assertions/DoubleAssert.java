package org.fest.assertions;

import static java.lang.Double.compare;
import static java.lang.Math.abs;
import static org.fest.assertions.ErrorMessages.*;
import static org.fest.assertions.Formatting.inBrackets;
import static org.fest.util.Objects.areEqual;
import static org.fest.util.Strings.concat;

/**
 * Understands Assertion methods for {@code Double}. To create a new instance of this class use the method
 * <code>{@link Assertions#assertThat(double)}</code>.
 *
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Alex Ruiz
 * @author Ansgar Konermann
 */
public class DoubleAssert extends GenericAssert<Double> implements NumberAssert {

  private static final double ZERO = 0.0;

  /**
   * Creates a new <code>{@link DoubleAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected DoubleAssert(double actual) {
    super(actual);
  }

  /**
   * Creates a new <code>{@link DoubleAssert}</code>.
   * @param actual the actual value to verify.
   */
  protected DoubleAssert(Double actual) {
    super(actual);
  }

  /** {@inheritDoc} */
  public DoubleAssert as(String description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public DoubleAssert describedAs(String description) {
    return as(description);
  }

  /** {@inheritDoc} */
  public DoubleAssert as(Description description) {
    description(description);
    return this;
  }

  /** {@inheritDoc} */
  public DoubleAssert describedAs(Description description) {
    return as(description);
  }

  /**
   * Verifies that the actual {@code Double} value is equal to the given one.
   * @param expected the value to compare the actual one to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not equal to the given one.
   */
  public DoubleAssert isEqualTo(double expected) {
    if (compareTo(expected) == 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedNotEqual(actual, expected));
  }

  /**
   * Verifies that the actual {@code Double} value is equal to the given one, within a positive delta.
   *
   * @param expected the value to compare the actual one to.
   * @param delta    the given delta.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not equal to the given one.
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
   * Verifies that the actual {@code Double} value is equal to the given one.
   * @param expected the given value to compare the actual {@code Double} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not equal to the given one.
   * @since 1.3
   */
  public DoubleAssert isEqualTo(Double expected) {
    if (areEqual(actual, expected)) return this;
    assertEqualTo(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code Double} value is not equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is equal to the given one.
   */
  public DoubleAssert isNotEqualTo(double other) {
    if (compareTo(other) != 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedEqual(actual, other));
  }

  /**
   * Verifies that the actual {@code Double} value is greater than the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not greater than the given one.
   */
  public DoubleAssert isGreaterThan(double other) {
    if (compareTo(other) > 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Double} value is less than the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not less than the given one.
   */
  public DoubleAssert isLessThan(double other) {
    if (compareTo(other) < 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThanOrEqualTo(actual, other));
  }

  /**
   * Verifies that the actual {@code Double} value is greater or equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not greater than or equal to the given one.
   */
  public DoubleAssert isGreaterThanOrEqualTo(double other) {
    if (compareTo(other) >= 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedLessThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Double} value is less or equal to the given one.
   *
   * @param other the given value.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not less than or equal to the given one.
   */
  public DoubleAssert isLessThanOrEqualTo(double other) {
    if (compareTo(other) <= 0) return this;
    failIfCustomMessageIsSet();
    throw failure(unexpectedGreaterThan(actual, other));
  }

  /**
   * Verifies that the actual {@code Double} value is equal to zero.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not equal to zero.
   */
  public DoubleAssert isZero() {
    return isEqualTo(ZERO);
  }

  /**
   * Verifies that the actual {@code Double} value is positive.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not positive.
   */
  public DoubleAssert isPositive() {
    return isGreaterThan(ZERO);
  }

  /**
   * Verifies that the actual {@code Double} value is negative.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not negative.
   */
  public DoubleAssert isNegative() {
    return isLessThan(ZERO);
  }

  /**
   * Verifies that the actual {@code Double} value is equal to <code>{@link Double#NaN}</code>.
   *
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not equal to <code>NAN</code>.
   */
  public DoubleAssert isNaN() {
    return isEqualTo(Double.NaN);
  }

  /**
   * Verifies that the actual {@code Double} value is equal to the given one, within a positive delta.
   *
   * @param expected the value to compare the actual one to.
   * @param delta    the given delta.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not equal to the given one.
   * @deprecated use method <code>{@link #isEqualTo(double, org.fest.assertions.Delta)}</code> instead. This method will
   *             be removed in version 2.0.
   */
  @Deprecated
  public DoubleAssert isEqualTo(double expected, Delta delta) {
    return isEqualTo(expected, delta.value);
  }

  /**
   * Creates a new holder for a delta value to be used in <code>{@link DoubleAssert#isEqualTo(double,
   * org.fest.assertions.DoubleAssert.Delta)}</code>.
   *
   * @param d the delta value.
   * @return a new delta value holder.
   * @deprecated use method <code>{@link org.fest.assertions.Delta#delta(double)}</code> instead. This method will be
   *             removed in version 2.0.
   */
  @Deprecated
  public static Delta delta(double d) {
    return new Delta(d);
  }

  /**
   * Holds a delta value to be used in <code>{@link DoubleAssert#isEqualTo(double,
   * org.fest.assertions.DoubleAssert.Delta)}</code>.
   *
   * @deprecated use top-level class <code>{@link org.fest.assertions.Delta}</code> instead. This class will be removed
   *             in version 2.0.
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

  /**
   * Verifies that the actual {@code Double} satisfies the given condition.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError       if the actual {@code Double} does not satisfy the given condition.
   * @see #is(Condition)
   * @since 1.3
   */
  @Override
  public DoubleAssert satisfies(Condition<Double> condition) {
    assertSatisfies(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code Double} does not satisfy the given condition.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError       if the actual value does satisfies the given condition.
   * @see #isNot(Condition)
   * @since 1.3
   */
  @Override
  public DoubleAssert doesNotSatisfy(Condition<Double> condition) {
    assertDoesNotSatisfy(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #satisfies(Condition)}</code>.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError       if the actual {@code Double} does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public DoubleAssert is(Condition<Double> condition) {
    assertIs(condition);
    return this;
  }

  /**
   * Alias for <code>{@link #doesNotSatisfy(Condition)}</code>.
   *
   * @param condition the given condition.
   * @return this assertion object.
   * @throws NullPointerException if the given condition is <code>null</code>.
   * @throws AssertionError       if the actual {@code Double} does not satisfy the given condition.
   * @since 1.3
   */
  @Override
  public DoubleAssert isNot(Condition<Double> condition) {
    assertIsNot(condition);
    return this;
  }

  /**
   * Verifies that the actual {@code Double} is not equal to the given one.
   *
   * @param other the given {@code Double} to compare the actual {@code Double} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is equal to the given one.
   * @since 1.3
   */
  @Override
  public DoubleAssert isNotEqualTo(Double other) {
    assertNotEqualTo(other);
    return this;
  }

  /**
   * Verifies that the actual {@code Double} is not <code>null</code>.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is <code>null</code>.
   * @since 1.3
   */
  @Override
  public DoubleAssert isNotNull() {
    assertNotNull();
    return this;
  }

  /**
   * Verifies that the actual {@code Double} is the same object as the given one.
   * @param expected the given {@code Double} to compare the actual {@code Double} to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is not the same as the given one.
   * @since 1.3
   */
  @Override
  public DoubleAssert isSameAs(Double expected) {
    assertSameAs(expected);
    return this;
  }

  /**
   * Verifies that the actual {@code Double} is not the same object as the given one.
   * @param other the given {@code Double} to compare the actual <code>BigDecimal</code> to.
   * @return this assertion object.
   * @throws AssertionError if the actual {@code Double} value is the same as the given one.
   * @since 1.3
   */
  @Override
  public DoubleAssert isNotSameAs(Double other) {
    assertNotSameAs(other);
    return this;
  }
}
