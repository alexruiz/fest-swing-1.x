/*
 * Created on May 16, 2009
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
 * Copyright @2009-2010 the original author or authors.
 */
package org.fest.swing.keystroke;

import static java.util.Locale.*;
import static org.fest.assertions.Assertions.assertThat;
import static org.fest.util.Collections.list;

import java.util.Collection;
import java.util.Locale;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for <code>{@link KeyStrokeMappingProviderPicker#providerFor(Locale)}</code>.
 *
 * @author Alex Ruiz
 */
@RunWith(Parameterized.class)
public class KeyStrokeMappingProviderPicker_providerFor_Test {

  private KeyStrokeMappingProviderPicker picker;

  private final Locale locale;

  @Parameters
  public static Collection<Object[]> locales() {
    return list(new Object[][] { { ENGLISH }, { CHINESE }, { ITALIAN }, { SIMPLIFIED_CHINESE } });
  }

  public KeyStrokeMappingProviderPicker_providerFor_Test(Locale locale) {
    this.locale = locale;
  }

  @Before public void setUp() {
    picker = new KeyStrokeMappingProviderPicker();
  }

  @Test
  public void should_pick_provider_for_German_if_locale_has_German_language() {
    KeyStrokeMappingProvider provider = picker.providerFor(GERMAN);
    assertThat(provider).isInstanceOf(KeyStrokeMappingProvider_de.class);
  }

  @Test
  public void should_pick_provider_for_German_if_locale_has_French_language() {
    KeyStrokeMappingProvider provider = picker.providerFor(FRENCH);
    assertThat(provider).isInstanceOf(KeyStrokeMappingProvider_fr.class);
  }

  @Test
  public void should_pick_English_provider_as_default() {
    KeyStrokeMappingProvider provider = picker.providerFor(locale);
    assertThat(provider).isInstanceOf(KeyStrokeMappingProvider_en.class);
  }
}
