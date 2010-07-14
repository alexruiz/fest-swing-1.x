/*
 * Created on May 26, 2010
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright @2010 the original author or authors.
 */
package org.fest.assertions;

import static org.fest.assertions.Title.Mr;

/**
 *
 * A simple class to test "onProperty" collection/list/array assertions.
 * <p>
 * The point here is to have all kind of properties: primitive type, primitive wrapper and Object.
 * </p>
 *
 * @author Joel Costigliola
 */
public class Person {

  private Long id;
  private final String homeTown;
  private final Name name;
  private final int age;
  private final long ssn;
  private Title title = Mr;
  private final boolean male;
  private final char favoriteAlphabetLetter;
  private final byte favoriteByte;
  private final short yearOfBirth;
  private final float height;
  private final double weight;
  private Person father;

  public Person(Long id, String name, int age, long ssn, boolean male, char favoriteAlphabetLetter, int favoriteByte,
      int yearOfBirth, float height, double weight, String homeTown) {
    this.id = id;
    this.name = new Name(name, "");
    this.age = age;
    this.ssn = ssn;
    this.male = male;
    this.favoriteAlphabetLetter = favoriteAlphabetLetter;
    this.favoriteByte = (byte)favoriteByte;
    this.yearOfBirth = (short)yearOfBirth;
    this.height = height;
    this.weight = weight;
    this.homeTown = homeTown;
  }

  public Person getFather() {
    return father;
  }

  public void setFather(Person father) {
    this.father = father;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Name getName() {
    return name;
  }

  public int getAge() {
    return age;
  }

  public long getSocialSecurityNumber() {
    return ssn;
  }

  public boolean isMale() {
    return male;
  }

  public char getFavoriteAlphabetLetter() {
    return favoriteAlphabetLetter;
  }

  public byte getFavoriteByte() {
    return favoriteByte;
  }

  public short getYearOfBirth() {
    return yearOfBirth;
  }

  public float getHeight() {
    return height;
  }

  public double getWeight() {
    return weight;
  }

  public String getHomeTown() {
    return homeTown;
  }

  public Title getTitle() {
    return title;
  }

  public void setTitle(Title title) {
    this.title = title;
  }
}
