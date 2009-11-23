/*
 * Created on Apr 16, 2008
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
 * Copyright @2008-2009 the original author or authors.
 */
package org.fest.swing.fixture;

import static java.awt.Font.*;
import static org.fest.test.ExpectedFailure.expectAssertionError;

import java.awt.Font;

import org.fest.test.CodeToTest;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for <code>{@link FontFixture}</code>.
 * @author Yvonne Wang
 * @author Alex Ruiz
 */
public class FontFixtureTest {

  private Font font;
  private FontFixture fixture;

  @Before
  public void setUp() {
    font = new Font("SansSerif", PLAIN, 8);
    fixture = new FontFixture(font);
  }

  @Test(expected = NullPointerException.class)
  public void shouldThrowErrorIfFontIsNull() {
    new FontFixture(null);
  }

  @Test
  public void shouldPassIfFamilyIsEqualToGivenOne() {
    fixture.requireFamily("SansSerif");
  }

  @Test(expected = AssertionError.class)
  public void shouldFailIfFamilyIsNotEqualToGivenOne() {
    // TODO fix ComparisonFailure message to be: "[family] expected:<'Monospace'> but was:<'SansSerif'>"
    fixture.requireFamily("Monospace");
  }

  @Test(expected = AssertionError.class)
  public void shouldFailShowingDescriptionIfFamilyIsNotEqualToGivenOne() {
    fixture = new FontFixture(font, "test");
    // TODO fix ComparisonFailure message to be: "[test - family] expected:<'Monospace'> but was:<'SansSerif'>"
    fixture.requireFamily("Monospace");
  }

  @Test
  public void shouldPassIfNameIsEqualToGivenOne() {
    fixture.requireName("SansSerif");
  }

  @Test(expected = AssertionError.class)
  public void shouldFailIfNameIsNotEqualToGivenOne() {
    // TODO fix ComparisonFailure message to be:  "[name] expected:<'Monospace'> but was:<'SansSerif'>"
    fixture.requireName("Monospace");
  }

  @Test(expected = AssertionError.class)
  public void shouldFailShowingDescriptionIfNameIsNotEqualToGivenOne() {
    fixture = new FontFixture(font, "test");
    // TODO fix ComparisonFailure message to be: "[test - name] expected:<'Monospace'> but was:<'SansSerif'>"
    fixture.requireName("Monospace");
  }

  @Test
  public void shouldPassIfSizeIsEqualToGivenOne() {
    fixture.requireSize(8);
  }

  @Test
  public void shouldFailIfSizeIsNotEqualToGivenOne() {
    expectAssertionError("[size] expected:<6> but was:<8>").on(new CodeToTest() {
      public void run() {
        fixture.requireSize(6);
      }
    });
  }

  @Test
  public void shouldFailShowingDescriptionIfSizeIsNotEqualToGivenOne() {
    fixture = new FontFixture(font, "test");
    expectAssertionError("[test - size] expected:<6> but was:<8>").on(new CodeToTest() {
      public void run() {
        fixture.requireSize(6);
      }
    });
  }

  @Test
  public void shouldPassIfFontIsBoldAsAnticipated() {
    fixture = new FontFixture(boldFont());
    fixture.requireBold();
  }

  @Test
  public void shouldFailIfFontIsNotBoldAndExpectingToBeBold() {
    expectAssertionError("[bold] expected:<true> but was:<false>").on(new CodeToTest() {
      public void run() {
        fixture.requireBold();
      }
    });
  }

  @Test
  public void shouldFailShowingDescriptionIfFontIsNotBoldAndExpectingToBeBold() {
    fixture = new FontFixture(font, "test");
    expectAssertionError("[test - bold] expected:<true> but was:<false>").on(new CodeToTest() {
      public void run() {
        fixture.requireBold();
      }
    });
  }

  @Test
  public void shouldPassIfFontIsNotBoldAsAnticipated() {
    fixture.requireNotBold();
  }

  @Test
  public void shouldFailIfFontIsBoldAndExpectingNotToBeBold() {
    fixture = new FontFixture(boldFont());
    expectAssertionError("[bold] expected:<false> but was:<true>").on(new CodeToTest() {
      public void run() {
        fixture.requireNotBold();
      }
    });
  }

  @Test
  public void shouldFailShowingDescriptionIfFontIsBoldAndExpectingNotToBeBold() {
    fixture = new FontFixture(boldFont(), "test");
    expectAssertionError("[test - bold] expected:<false> but was:<true>").on(new CodeToTest() {
      public void run() {
        fixture.requireNotBold();
      }
    });
  }

  @Test
  public void shouldPassIfFontIsItalicAsAnticipated() {
    fixture = new FontFixture(italicFont());
    fixture.requireItalic();
  }

  @Test
  public void shouldFailIfFontIsNotItalicAndExpectingToBeItalic() {
    expectAssertionError("[italic] expected:<true> but was:<false>").on(new CodeToTest() {
      public void run() {
        fixture.requireItalic();
      }
    });
  }

  @Test
  public void shouldFailShowingDescriptionIfFontIsNotItalicAndExpectingToBeItalic() {
    fixture = new FontFixture(font, "test");
    expectAssertionError("[test - italic] expected:<true> but was:<false>").on(new CodeToTest() {
      public void run() {
        fixture.requireItalic();
      }
    });
  }

  @Test
  public void shouldPassIfFontIsNotItalicAsAnticipated() {
    fixture.requireNotItalic();
  }

  @Test
  public void shouldFailIfFontIsItalicAndExpectingNotToBeItalic() {
    fixture = new FontFixture(italicFont());
    expectAssertionError("[italic] expected:<false> but was:<true>").on(new CodeToTest() {
      public void run() {
        fixture.requireNotItalic();
      }
    });
  }

  @Test
  public void shouldFailShowingDescriptionIfFontIsItalicAndExpectingNotToBeItalic() {
    fixture = new FontFixture(italicFont(), "test");
    expectAssertionError("[test - italic] expected:<false> but was:<true>").on(new CodeToTest() {
      public void run() {
        fixture.requireNotItalic();
      }
    });
  }

  private Font italicFont() {
    return font.deriveFont(ITALIC);
  }

  @Test
  public void shouldPassIfFontIsPlainAsAnticipated() {
    fixture = new FontFixture(boldFont());
    fixture.requirePlain();
  }

  @Test
  public void shouldFailIfFontIsNotPlainAndExpectingToBePlain() {
    expectAssertionError("[plain] expected:<true> but was:<false>").on(new CodeToTest() {
      public void run() {
        fixture.requirePlain();
      }
    });
  }

  @Test
  public void shouldFailShowingDescriptionIfFontIsNotPlainAndExpectingToBePlain() {
    fixture = new FontFixture(font, "test");
    expectAssertionError("[test - plain] expected:<true> but was:<false>").on(new CodeToTest() {
      public void run() {
        fixture.requirePlain();
      }
    });
  }

  @Test
  public void shouldPassIfFontIsNotPlainAsAnticipated() {
    fixture.requireNotPlain();
  }

  @Test
  public void shouldFailIfFontIsPlainAndExpectingNotToBePlain() {
    fixture = new FontFixture(boldFont());
    expectAssertionError("[plain] expected:<false> but was:<true>").on(new CodeToTest() {
      public void run() {
        fixture.requireNotPlain();
      }
    });
  }

  @Test
  public void shouldFailShowingDescriptionIfFontIsPlainAndExpectingNotToBePlain() {
    fixture = new FontFixture(boldFont(), "test");
    expectAssertionError("[test - plain] expected:<false> but was:<true>").on(new CodeToTest() {
      public void run() {
        fixture.requireNotPlain();
      }
    });
  }

  private Font boldFont() {
    return font.deriveFont(BOLD);
  }
}
