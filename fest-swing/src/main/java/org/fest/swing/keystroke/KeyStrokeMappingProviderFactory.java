/*
 * Created on Mar 28, 2010
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
package org.fest.swing.keystroke;

/**
 * Understands creation of <code>{@link KeyStrokeMappingProvider}</code>s from class names.
 *
 * @author Alex Ruiz
 *
 * @since 1.2
 */
class KeyStrokeMappingProviderFactory {

  KeyStrokeMappingProvider createProvider(String className) {
      try {
        Class<?> type = Class.forName(className);
        if (!isKeyStrokeMappingProviderType(type)) return null;
        return (KeyStrokeMappingProvider) type.newInstance();
      } catch (ClassNotFoundException e) {
        return null;
      } catch (InstantiationException e) {
        return null;
      } catch (IllegalAccessException e) {
        return null;
      } catch (RuntimeException e) {
        return null;
      }
  }

  private boolean isKeyStrokeMappingProviderType(Class<?> type) {
    return KeyStrokeMappingProvider.class.isAssignableFrom(type);
  }
}
