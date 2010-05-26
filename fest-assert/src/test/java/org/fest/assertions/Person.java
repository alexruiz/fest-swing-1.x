package org.fest.assertions;

/**
 * 
 * A simple class to test onProperty collection/list/array assertions.
 * <p>
 * The point here is to have all kind of properties : primitive type, primitive wrapper and Object.
 * 
 * @author Joel Costigliola
 * 
 */
public class Person {

  private Long id;
  private final String homeTown;
  private final Name name;
  private final int age;
  private final long socialSecurityNumber;
  private Title title = Title.Mr;
  private final boolean male;
  private final char favoriteAlphabetLetter;
  private final byte favoriteByte;
  private final short yearOfBirth;
  private final float height;
  private final double weight;
  private Person father;

  public Person(Long id, String name, int age, long socialSecurityNumber, boolean male, char favoriteAlphabetLetter,
      byte favoriteByte, short yearOfBirth, float height, double weight, String homeTown) {
    super();
    this.id = id;
    this.name = new Name(name, "");
    this.age = age;
    this.socialSecurityNumber = socialSecurityNumber;
    this.male = male;
    this.favoriteAlphabetLetter = favoriteAlphabetLetter;
    this.favoriteByte = favoriteByte;
    this.yearOfBirth = yearOfBirth;
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
    return socialSecurityNumber;
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
