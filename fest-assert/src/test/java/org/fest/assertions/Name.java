package org.fest.assertions;

/**
 *
 * A simple class to test onProperty collection/list/array assertions.
 *
 * @author Joel Costigliola
 *
 */
public class Name {
  private String firstName;
  private String lastName;

  public Name(String firstName, String lastName) {
    super();
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

}
