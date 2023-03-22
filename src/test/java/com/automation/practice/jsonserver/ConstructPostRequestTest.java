package com.automation.practice.jsonserver;

import com.automation.constants.FrameworkConstants;
import com.automation.models.builders.RequestBuilder;
import com.automation.reports.ExtentLogger;
import com.automation.utils.ApiUtils;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class ConstructPostRequestTest {

  // Different ways of constructing POST request
  // 1) As String
  @Test
  public void postRequestAsString() {

    String requestBody = "{\n" +
      "      \"id\": 5,\n" +
      "      \"first_name\": \"Auto\",\n" +
      "      \"last_name\": \"user\",\n" +
      "      \"email\": \"abc@testmail.com\"\n" +
      "    }";

    RequestSpecification requestSpecification = RequestBuilder.buildRequest()
      .body(requestBody);
    ExtentLogger.logRequest(requestSpecification);

    Response response = requestSpecification.post("/employees");

    ExtentLogger.logResponse(response.asPrettyString());
  }

  // 2) From External file
  @Test
  public void postRequestFromFile() {

    given()
      .baseUri("http://localhost:3000")
      .header("Content-Type", "application/json")
      .body(new File(System.getProperty("user.dir") + "/src/test/java/com/automation/practice/jsonserver/test.json"))
      .when()
      .post("/employees")
      .then()
      .log().all();
  }

  // 3) Read it as String from external file (.json file) and replace values
  @Test
  public void postRequestFromFileAsString() {

//        String requestBody = new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir") + "/src/test/java/com/automation/practice/jsonserver/test.json")));
//        String replacedRequestBody = requestBody.replace("6",
//                String.valueOf(new Faker().number().numberBetween(10,50)));

    String resource = ApiUtils.readJsonAndGetAsString(FrameworkConstants.RESOURCES_FOLDER_PATH + "/test.json")
      .replace("6", String.valueOf(new Faker().number().numberBetween(10, 50)));

    given()
      .baseUri("http://localhost:3000")
      .header("Content-Type", "application/json")
      .body(resource)
      .when()
      .post("/employees")
      .then()
      .log().all();
  }

  // 4) Using HashMap (for Json Object {} ) and ArrayList (for Json Array [])
  @Test
  public void postRequestUsingMapAndList() {

    Map<String, Object> object = new LinkedHashMap<>();
    object.put("id", new Faker().number().numberBetween(10, 50));
    object.put("first_name", "auto");
    object.put("last_name", "test_user");
    object.put("email", "test_user@email.com");
    object.put("jobs", Arrays.asList("Dev", "Tester"));

    Map<String, Object> skillSet = new LinkedHashMap<>();
    skillSet.put("programming", "java");
    skillSet.put("automation", Collections.singletonList("web, mobile, api"));

    object.put("skill", skillSet);

    given()
      .baseUri("http://localhost:3000")
      .header("Content-Type", "application/json")
      .body(object)
      .when()
      .post("/employees")
      .then()
      .log().all();
  }

  // 5) Using external Json library (JSONObject, JSONArray)
  @Test
  public void postRequestUsingExternalJsonLib() {

    JSONObject jsonObject = new JSONObject();
    jsonObject.put("id", new Faker().number().numberBetween(10, 50));
    jsonObject.put("first_name", "auto");
    jsonObject.put("last_name", "test_user");
    jsonObject.put("email", "test_user@email.com");
    jsonObject.accumulate("email", "test_user2@email.com"); // This will convert "email" key to store list of values

    JSONArray jobs = new JSONArray();
    jobs.put("Tester");
    jobs.put("Dev");
    jsonObject.put("jobs", jobs);

    JSONObject skillSet = new JSONObject();
    skillSet.put("programming", "java");
    skillSet.put("automation", Collections.singletonList("web, mobile, api"));

    jsonObject.put("skill", skillSet);

    given()
      .baseUri("http://localhost:3000")
      .header("Content-Type", "application/json")
      .body(jsonObject.toString())
      .when()
      .post("/employees")
      .then()
      .log().all();
  }

}
