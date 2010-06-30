package org.fest.assertions;

import static org.fest.assertions.Assertions.assertThat;
import static org.fest.assertions.CommonFailures.expectErrorIfActualListIsNull;

import java.util.ArrayList;
import java.util.List;

import org.fest.test.CodeToTest;
import org.fest.util.IntrospectionError;
import org.junit.Before;
import org.junit.Test;

public class ListAssert_onProperty_Test implements Assert_onProperty_Test {

  private static List<Person> friends;

  @Before
  public void initData() {
    // Person properties :
    // - Long id;
    // - Name name;
    // - Person father;
    // - int age;
    // - long socialSecurityNumber;
    // - boolean male;
    // - char favoriteAlphabetLetter;
    // - byte favoriteByte;
    // - short yearOfBirth;
    // - float height;
    // - double weight;
    // - String homeTown;
    friends = new ArrayList<Person>();
    Person pier = new Person(1L, "Pier", 25, 6L, true, 'P', (byte) 1, (short) 1974, 1.90f, 80.1, "Paris");
    pier.setFather(new Person(11L, "PierFather", 55, 6L, true, 'P', (byte) 1, (short) 1974, 1.90f, 80.1, "Paris"));
    friends.add(pier);
    Person paula = new Person(2L, "Paula", 32, 67L, false, 'O', (byte) 2, (short) 1975, 1.80f, 90.2, "Madrid");
    paula.setFather(new Person(22L, "PaulaFather", 62, 67L, false, 'O', (byte) 2, (short) 1975, 1.80f, 90.2, "Madrid"));
    paula.setTitle(Title.Miss);
    friends.add(paula);
    Person jack = new Person(3L, "Jack", 16, 678L, false, 'J', (byte) 4, (short) 1976, 1.70f, 100.3, "London");
    jack.setFather(new Person(33L, "JackFather", 46, 678L, false, 'J', (byte) 4, (short) 1976, 1.70f, 100.3, "London"));
    friends.add(jack);
    Person jack2 = new Person(4L, "Jack", 44, 6789L, true, 'K', (byte) 8, (short) 1977, 1.60f, 110.4, "Roma");
    jack2.setFather(new Person(44L, "JackFather2", 74, 678L, false, 'J', (byte) 4, (short) 1976, 1.70f, 100.3, "Roma"));
    friends.add(jack2);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_non_primitive_type_property() {
    assertThat(friends).onProperty("id").contains(1L, 2L, 4L);
    assertThat(friends).onProperty("homeTown").contains("Paris", "Roma", "London", "Madrid");
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_enum_type_property() {
    assertThat(friends).onProperty("title").contains(Title.Mr, Title.Miss);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_primitive_type_int_property() {
    assertThat(friends).onProperty("age").containsOnly(25, 16, 44, 32);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_primitive_type_long_property() {
    assertThat(friends).onProperty("socialSecurityNumber").containsOnly(6L, 67L, 678L, 6789L);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_primitive_type_short_property() {
    assertThat(friends).onProperty("yearOfBirth").containsOnly((short) 1974, (short) 1975, (short) 1976, (short) 1977);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_primitive_type_float_property() {
    assertThat(friends).onProperty("height").containsOnly(1.90f, 1.80f, 1.70f, 1.60f);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_primitive_type_double_property() {
    assertThat(friends).onProperty("weight").containsOnly(80.1, 90.2, 100.3, 110.4);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_primitive_type_boolean_property() {
    assertThat(friends).onProperty("male").containsOnly(true, false, false, true);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_primitive_type_byte_property() {
    assertThat(friends).onProperty("favoriteByte").containsOnly((byte) 1, (byte) 2, (byte) 4, (byte) 8);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_primitive_type_char_property() {
    assertThat(friends).onProperty("favoriteAlphabetLetter").contains('P', 'K');
    assertThat(friends).onProperty("favoriteAlphabetLetter").containsOnly('O', 'J', 'P', 'K');
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_non_primitive_type_nested_property() {
    assertThat(friends).onProperty("name.firstName").contains("Pier", "Paula", "Jack");
    assertThat(friends).onProperty("father.name.firstName").containsOnly("PierFather", "PaulaFather", "JackFather",
        "JackFather2");
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_enum_type_nested_property() {
    assertThat(friends).onProperty("father.title").containsOnly(Title.Mr, Title.Mr, Title.Mr, Title.Mr);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_on_primitive_type_nested_property() {
    assertThat(friends).onProperty("father.age").containsOnly(55, 46, 74, 62);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_even_if_actual_contains_null_elements() {
    friends.add(null);
    assertThat(friends).onProperty("father.name.firstName").containsOnly("PierFather", "PaulaFather", "JackFather",
        "JackFather2");
  }

  /** {@inheritDoc} */
  @Test
  public void should_fail_on_non_primitive_type_property() {
    try {
      assertThat(friends).onProperty("homeTown").contains("Paris", "Rome", "Londres", "Madrid");
    } catch (AssertionError e) {
      assertThat(e).hasMessage(
          "list:<['Paris', 'Madrid', 'London', 'Roma']> does not contain element(s):<['Rome', 'Londres']>");
    }
  }

  /** {@inheritDoc} */
  @Test
  public void should_fail_on_non_primitive_type_nested_property() {
    try {
      assertThat(friends).onProperty("name.firstName").contains("Jack", "Pier", "TOTO", "Paula");
    } catch (AssertionError e) {
      assertThat(e).hasMessage("list:<['Pier', 'Paula', 'Jack', 'Jack']> does not contain element(s):<['TOTO']>");
    }
  }

  @Test
  public void should_fail_on_enum_type_property() {
    try {
      assertThat(friends).onProperty("title").contains(Title.Mr, Title.Ms);
    } catch (AssertionError e) {
      assertThat(e).hasMessage("list:<[Mr, Miss, Mr, Mr]> does not contain element(s):<[Ms]>");
    }
  }

  /** {@inheritDoc} */
  @Test
  public void should_fail_on_enum_type_nested_property() {
    try {
      assertThat(friends).onProperty("father.title").contains(Title.Miss);
    } catch (AssertionError e) {
      assertThat(e).hasMessage("list:<[Mr, Mr, Mr, Mr]> does not contain element(s):<[Miss]>");
    }
  }

  /** {@inheritDoc} */
  @Test
  public void should_fail_on_primitive_type_property() {
    try {
      assertThat(friends).onProperty("age").contains(777);
    } catch (AssertionError e) {
      assertThat(e).hasMessage("list:<[25, 32, 16, 44]> does not contain element(s):<[777]>");
    }
  }

  /** {@inheritDoc} */
  @Test
  public void should_fail_on_primitive_type_nested_property() {
    try {
      assertThat(friends).onProperty("father.age").contains(888);
    } catch (AssertionError e) {
      assertThat(e).hasMessage("list:<[55, 62, 46, 74]> does not contain element(s):<[888]>");
    }
  }

  /** {@inheritDoc} */
  @Test
  public void should_fail_because_of_unknown_property() {
    try {
      // PersonName does not have a 'nickname' property => failure
      assertThat(friends).onProperty("name.nickname").containsOnly("PaulaFather", "JackFather", "JackFather2");
    } catch (IntrospectionError e) {
      assertThat(e).hasMessage("Unable to find property 'nickname' in org.fest.assertions.Name");
    }
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_with_null_nested_property_in_actual_elements() {
    friends.iterator().next().setFather(null);
    assertThat(friends).onProperty("father.name.firstName").containsOnly("PaulaFather", "JackFather", "JackFather2");
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_with_null_property_in_actual_elements() {
    for (Person person : friends) {
      if (person.getId() == 1L) {
        person.setId(null);
      }
    }
    assertThat(friends).onProperty("id").contains(2L, 3L, 4L);
    assertThat(friends).onProperty("id").containsOnly(2L, 3L, 4L, null);
    // nullify all id
    for (Person person : friends) {
      person.setId(null);
    }
    assertThat(friends).onProperty("id").containsOnly(null, null, null, null);
  }

  /** {@inheritDoc} */
  @Test
  public void should_pass_if_actual_is_empty() {
    List<Person> emptyList = new ArrayList<Person>();
    assertThat(emptyList).onProperty("id").isEmpty();
  }

  @Test
  public void should_fail_if_actual_is_null() {
    expectErrorIfActualListIsNull(new CodeToTest() {
      public void run() {
        new ListAssert(null).onProperty("homeTown").contains("Paris", "Roma", "London", "Madrid");
      }
    });
  }

}
