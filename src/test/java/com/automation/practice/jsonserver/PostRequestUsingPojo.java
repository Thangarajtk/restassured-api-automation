package com.automation.practice.jsonserver;

import com.automation.pojo.Employee;
import com.automation.pojo.Skill;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Arrays;

import static io.restassured.RestAssured.given;

public final class PostRequestUsingPojo {

    // POJO - Plain Old Java Object
    // {} - Create class file for every Json Object
    // [] - Create List<T>

    @Test
    public void pojoTestUsingConstructor() {
        // Construction helps to create immutable objects
        Skill skill = new Skill("core java", Arrays.asList("rest-assured", "jackson"));
        Employee employee = new Employee(13, "fname", "lname", "testmail@test.com", Arrays.asList("QA", "SDET"), skill);

        Response response = given()
                .baseUri("http://localhost:3000")
                .header("Content-Type", "application/json")
                .body(employee)
                .when()
                .post("/employees");
        response.prettyPrint();
    }
}
