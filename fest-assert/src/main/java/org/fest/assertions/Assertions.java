/*
 * Created on Dec 27, 2006
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
 * Copyright @2006-2010 the original author or authors.
 */
package org.fest.assertions;

import java.awt.image.BufferedImage;
import java.io.File;
import java.math.BigDecimal;
import java.util.*;

/**
 * Understands an entry point for assertion methods for different data types. Each method in this class is a static
 * factory for the type-specific assertion objects. The purpose of this class is to make test code more readable.
 * <p>
 * For example:
 * <pre>
 * int removed = employees.removeFired();
 * {@link org.fest.assertions.Assertions#assertThat(int) assertThat}(removed).{@link org.fest.assertions.IntAssert#isZero isZero}();
 *
 * List&lt;Employee&gt; newEmployees = employees.hired(TODAY);
 * {@link org.fest.assertions.Assertions#assertThat(java.util.Collection) assertThat}(newEmployees).{@link org.fest.assertions.CollectionAssert#hasSize(int) hasSize}(6);
 * </pre>
 * </p>
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 * @author David DIDIER
 * @author Ted Young
 */
public class Assertions {

  private static <T> Collection<T> asCollection(Iterator<T> iterator) {
    List<T> list = new ArrayList<T>();
    while (iterator.hasNext()) list.add(iterator.next());
    return list;
  }

  /**
   * Creates a new instance of <code>{@link BigDecimalAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static BigDecimalAssert assertThat(BigDecimal actual) {
    return new BigDecimalAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link BooleanAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static BooleanAssert assertThat(boolean actual) {
    return new BooleanAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link BooleanAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static BooleanAssert assertThat(Boolean actual) {
    return new BooleanAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link BooleanArrayAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static BooleanArrayAssert assertThat(boolean[] actual) {
    return new BooleanArrayAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ImageAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static ImageAssert assertThat(BufferedImage actual) {
    return new ImageAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ByteAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static ByteAssert assertThat(byte actual) {
    return new ByteAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ByteAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static ByteAssert assertThat(Byte actual) {
    return new ByteAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ByteArrayAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static ByteArrayAssert assertThat(byte[] actual) {
    return new ByteArrayAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link CharAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static CharAssert assertThat(char actual) {
    return new CharAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link CharAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static CharAssert assertThat(Character actual) {
    return new CharAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link CharArrayAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static CharArrayAssert assertThat(char[] actual) {
    return new CharArrayAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link CollectionAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static CollectionAssert assertThat(Collection<?> actual) {
    return new CollectionAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ListAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   * @since 1.1
   */
  public static ListAssert assertThat(List<?> actual) {
    return new ListAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link DoubleAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static DoubleAssert assertThat(double actual) {
    return new DoubleAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link DoubleAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static DoubleAssert assertThat(Double actual) {
    return new DoubleAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link DoubleArrayAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static DoubleArrayAssert assertThat(double[] actual) {
    return new DoubleArrayAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link FileAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static FileAssert assertThat(File actual) {
    return new FileAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link FloatAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static FloatAssert assertThat(float actual) {
    return new FloatAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link FloatAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static FloatAssert assertThat(Float actual) {
    return new FloatAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link FloatArrayAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static FloatArrayAssert assertThat(float[] actual) {
    return new FloatArrayAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link IntAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static IntAssert assertThat(int actual) {
    return new IntAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link IntAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static IntAssert assertThat(Integer actual) {
    return new IntAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link IntArrayAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static IntArrayAssert assertThat(int[] actual) {
    return new IntArrayAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link CollectionAssert}</code>.
   * @param actual an <code>Iterable</code> whose contents will be added to a new <code>Collection</code>.
   * @return the created assertion object.
   */
  public static CollectionAssert assertThat(Iterable<?> actual) {
    return assertThat(asCollection(actual.iterator()));
  }

  /**
   * Creates a new instance of <code>{@link CollectionAssert}</code>.
   * @param actual an <code>Iterator</code> whose contents will be added to a new <code>Collection</code>.
   * @return the created assertion object.
   */
  public static CollectionAssert assertThat(Iterator<?> actual) {
    return assertThat(asCollection(actual));
  }

  /**
   * Creates a new instance of <code>{@link LongAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static LongAssert assertThat(long actual) {
    return new LongAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link LongAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static LongAssert assertThat(Long actual) {
    return new LongAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link LongArrayAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static LongArrayAssert assertThat(long[] actual) {
    return new LongArrayAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link MapAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static MapAssert assertThat(Map<?, ?> actual) {
    return new MapAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ObjectAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static ObjectAssert assertThat(Object actual) {
    return new ObjectAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ObjectArrayAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static ObjectArrayAssert assertThat(Object[] actual) {
    return new ObjectArrayAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ShortAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static ShortAssert assertThat(short actual) {
    return new ShortAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ShortAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static ShortAssert assertThat(Short actual) {
    return new ShortAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link ShortArrayAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static ShortArrayAssert assertThat(short[] actual) {
    return new ShortArrayAssert(actual);
  }

  /**
   * Creates a new instance of <code>{@link StringAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static StringAssert assertThat(String actual) {
    return new StringAssert(actual);
  }

  /**
   * Returns the given assertion. This method improves code readability by surrounding the given assertion with "<code>assertThat</code>".
   * <p>
   * For example, let's assume we have the following custom assertion class:
   *
   * <pre>
   * public class ServerSocketAssertion implements AssertExtension {
   *   private final ServerSocket socket;
   *
   *   public ServerSocketAssertion(ServerSocket socket) {
   *     this.socket = socket;
   *   }
   *
   *   public ServerSocketAssert isConnectedTo(int port) {
   *     assertThat(socket.isBound()).isTrue();
   *     assertThat(socket.getLocalPort()).isEqualTo(port);
   *     assertThat(socket.isClosed()).isFalse();
   *     return this;
   *   }
   * }
   * </pre>
   * </p>
   * <p>
   * We can wrap that assertion with "<code>assertThat</code>" to improve test code readability.
   * <pre>
   *   ServerSocketAssertion socket = new ServerSocketAssertion(server.getSocket());
   *   assertThat(socket).isConnectedTo(2000);
   * </pre>
   * </p>
   *
   * @param <T> the generic type of the user-defined assertion.
   * @param assertion the assertion to return.
   * @return the given assertion.
   */
  public static <T extends AssertExtension> T assertThat(T assertion) {
    return assertion;
  }

  /**
   * Creates a new instance of <code>{@link ThrowableAssert}</code>.
   * @param actual the value to be the target of the assertions methods.
   * @return the created assertion object.
   */
  public static ThrowableAssert assertThat(Throwable actual) {
    return new ThrowableAssert(actual);
  }

  /**
   * This constructor is protected to make it possible to subclass this class. Since all its methods are static, there
   * is no point on creating a new instance of it.
   */
  protected Assertions() {}
}
