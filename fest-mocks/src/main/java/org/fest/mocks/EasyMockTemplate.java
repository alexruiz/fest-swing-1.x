/*
 * Created on Apr 22, 2007
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
 * Copyright @2007 the original author or authors.
 */
package org.fest.mocks;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import static org.easymock.classextension.EasyMock.*;

/**
 * Understands a template for usage of <a href="http://www.easymock.org/" target="_blank">EasyMock</a> mocks.
 * <p>
 * Here is an small example that uses <a href="http://www.easymock.org/" target="_blank">EasyMock</a> to verify that 
 * <code>EmployeeBO</code> uses a <code>EmployeeDAO</code> to store employee information in the database:
 * <pre>
 * <code>
 * &#64;Test public void shouldAddNewEmployee() {
 *   mockEmployeeDao.insert(employee);
 *   replay(mockEmployeeDao);
 *   employeeBo.addNewEmployee(employee);
 *   verify(mockEmployeeDao);    
 * }
 * </code>
 * </pre>
 * </p>
 * <p>
 * In this example, it is easy to distinguish the expectations made on the mock object (<code>mockEmployeeDao</code>).
 * But in a more complex scenario, that distinction could be more difficult to spot. We can use the 
 * <code>EasyMockTemplate</code> to separate the code to be tested from the mock expectations:
 * <pre>
 * <code>
 * &#64;Test public void shouldAddNewEmployee() {
 *   EasyMockTemplate t = new EasyMockTemplate(mockEmployeeDao) {
 *     &#64;Override protected void expectations() {
 *       mockEmployeeDao.insert(employee);
 *     }
 *
 *     &#64;Override protected void codeToTest() {
 *       employeeBo.addNewEmployee(employee);
 *     }
 *   };
 *   t.run();    
 * }
 * </code>
 * </pre>
 * </p>
 * <p>
 * The benefits of <code>EasyMockTemplate</code> are:
 * <ul>
 * <li>Clear separation of mock expectations and code to test</li> 
 * <li>Less code duplication (we don't have to call <code>replay</code> and <code>verify</code> anymore)</li> 
 * </ul>
 * </p>
 * 
 * @author Alex Ruiz
 */
public abstract class EasyMockTemplate {

  /** Mock objects managed by this template */
  private final List<Object> mocks = new ArrayList<Object>();
    
  /**
   * Constructor.
   * @param mocks the mocks for this template to manage.
   * @throws IllegalArgumentException if the list of mock objects is <code>null</code> or empty.
   * @throws IllegalArgumentException if any of the given mocks is <code>null</code>.
   * @throws IllegalArgumentException if any of the given mocks is not a mock.
   */ 
  public EasyMockTemplate(Object... mocks) {
    if (mocks == null) throw new IllegalArgumentException("The list of mock objects should not be null");
    if (mocks.length == 0) throw new IllegalArgumentException("The list of mock objects should not be empty");
    for (Object mock : mocks) {
      if (mock == null) throw new IllegalArgumentException("The list of mocks should not include null values");
      this.mocks.add(checkAndReturnMock(mock));
    }
  }
  
  private Object checkAndReturnMock(Object mock) {
    if (Enhancer.isEnhanced(mock.getClass())) return mock;
    if (Proxy.isProxyClass(mock.getClass())) return mock;
    throw new IllegalArgumentException(mock + " is not a mock");
  }
   
  /**
   * Encapsulates EasyMock's behavior pattern.
   * <ol>
   * <li>Set up expectations on the mock objects</li>
   * <li>Set the state of the mock controls to "replay"</li>
   * <li>Execute the code to test</li>
   * <li>Verify that the expectations were met</li>
   * </ol>
   * Steps 2 and 4 are considered invariant behavior while steps 1 and 3 should be implemented by subclasses of this 
   * template.
   * @throws UnexpectedError wrapping any checked exception thrown during the execution of this method.
   */
  public final void run() {
    try {
      setUp();
      expectations();
      for (Object mock : mocks) replay(mock);
      codeToTest();
      for (Object mock : mocks) verify(mock);
      cleanUp();
    } catch (Throwable t) {
      if (t instanceof RuntimeException) throw (RuntimeException)t;
      throw new UnexpectedError(t);
    }
  }

  /**
   * Returns all the mocks managed by this template.
   * @return all the mocks managed by this template.
   */
  protected final List<Object> mocks() {
    return new ArrayList<Object>(mocks);
  }
  
  /** 
   * Sets the expectations on the mock objects. 
   * @throws Throwable any error thrown by the implementation of this method.
   */
  protected abstract void expectations() throws Throwable;

  /** 
   * Executes the code that is under test. 
   * @throws Throwable any error thrown by the implementation of this method.
   */
  protected abstract void codeToTest() throws Throwable;

  /** 
   * Sets up the test fixture if necessary. 
   * @throws Throwable any error thrown by the implementation of this method.
   */
  protected void setUp() throws Throwable {}

  /** 
   * Cleans up any resources if necessary. 
   * @throws Throwable any error thrown by the implementation of this method.
   */
  protected void cleanUp() throws Throwable {}
}
