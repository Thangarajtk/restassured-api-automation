package com.automation.pojo;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class StudentBuilder {

  private int id;
  private String firstname;
  private String lastname;
  private String email;

  public static StudentBuilder builder() {
    return new StudentBuilder();
  }

  public StudentBuilder setId(int id) {
    this.id = id;
    return this;
  }

  public StudentBuilder setFirstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public StudentBuilder setLastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public StudentBuilder setEmail(String email) {
    this.email = email;
    return this;
  }

  public Student build() {
    return new Student(this.id, this.firstname, this.lastname, this.email);
  }
}

