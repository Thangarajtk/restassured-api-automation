package com.automation.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter // This is required for Serialization and DeSerialization
@AllArgsConstructor
@ToString
@Builder
public class Student {

  private int id;
  private String firstname;
  private String lastname;
  private String email;

  // Static Inner class
  public static class StudentBuilderInnerClass {
    private int id;
    private String firstname;
    private String lastname;
    private String email;

    public static StudentBuilderInnerClass builder() {
      return new StudentBuilderInnerClass();
    }

    public StudentBuilderInnerClass setId(int id) {
      this.id = id;
      return this;
    }

    public StudentBuilderInnerClass setFirstname(String firstname) {
      this.firstname = firstname;
      return this;
    }

    public StudentBuilderInnerClass setLastname(String lastname) {
      this.lastname = lastname;
      return this;
    }

    public StudentBuilderInnerClass setEmail(String email) {
      this.email = email;
      return this;
    }

    public Student build() {
      return new Student(this.id, this.firstname, this.lastname, this.email);
    }
  }
}
