package com.automation.practice.builderpattern;

import com.automation.models.pojo.Student;
import com.automation.models.pojo.StudentBuilder;
import org.testng.annotations.Test;

public final class BuilderDesignPatternTest {

  @Test
  public void builderTest() {
    // 1) Number of parameters increases, the readability decreases
    // 2) To ignore some fields, multiple constructors has to be created

    // Builder design pattern will be useful to overcome above issues.
    // 1) External Builder

    Student student = StudentBuilder.builder().setId(222)
      .setFirstname("AutoUser")
      .setLastname("Test")
      .build();

    System.out.println(student);

    // 2) Static Inner Class
    Student testUser = Student.StudentBuilderInnerClass.builder().setFirstname("TestUser")
      .setId(123)
      .setEmail("testmail@test.com")
      .build();

    System.out.println(testUser);

    // @Builder annotation uses Static Inner Class implementation
    Student student1 = Student.builder().firstname("test")
      .lastname("user")
      .id(345)
      .email("test@email.com")
      .build();

    System.out.println(student1);
  }
}
