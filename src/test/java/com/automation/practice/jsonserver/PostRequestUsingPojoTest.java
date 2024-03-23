package com.automation.practice.jsonserver;

import com.automation.models.pojo.Employee;
import com.automation.models.pojo.Skill;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public final class PostRequestUsingPojoTest {

  // POJO - Plain Old Java Object
  // {} - Create class file for every Json Object
  // [] - Create List<T>

  @Test
  public void pojoTestUsingConstructor() {
    // Construction helps to create immutable objects
    Skill skill = new Skill("core java", Arrays.asList("rest-assured", "jackson"));
    Employee employee = new Employee(22, "fname", "lname", "testmail@test.com", Arrays.asList("QA", "SDET"), skill);

    Response response = given()
      .baseUri("http://localhost:3000")
      .header("Content-Type", "application/json")
      .body(employee)
      .when()
      .post("/employees");
    response.prettyPrint();

    Assertions.assertThat(response.statusCode()).isEqualTo(201);
    Assertions.assertThat(response.jsonPath().getString("firstname")).isEqualTo("fname");
    Assertions.assertThat(response.jsonPath().getList("jobs")).asList();
  }
}
