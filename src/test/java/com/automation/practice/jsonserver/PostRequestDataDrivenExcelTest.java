package com.automation.practice.jsonserver;

import com.automation.utils.ExcelUtils;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public final class PostRequestDataDrivenExcelTest {

  @DataProvider(name = "input")
  public Object[][] getPostData() {
    Object[][] data;
    ExcelUtils xlReader = new ExcelUtils();
    Map<String, ArrayList<Object>> map = xlReader.getExcelData();

    ArrayList<Object> value = map.get("FirstName");
    data = new Object[value.size()][2];

    for (int i = 0; i < value.size(); i++) {
      data[i][0] = map.get("FirstName").get(i);
      data[i][1] = map.get("SubjectId").get(i);
    }
    return data;
  }

  @Test(dataProvider = "input")
  public void testPostRequest(String firstName, String subjectId) {

    JSONObject request = new JSONObject();
    request.put("firstName", firstName);
    request.put("subjectId", Integer.parseInt(subjectId));

    baseURI = "http://localhost:3000/";

    given().
      header("Content-Type", "application.json").
      contentType(ContentType.JSON).
      accept(ContentType.JSON).
      body(request.toJSONString()).
      when().
      post("/users").
      then().
      assertThat().
      statusCode(201);
  }

  // Available Libraries Gson, Jackson, Json and Simple Json
  @Test
  public void testPostRequest() {

    org.json.JSONObject request = new org.json.JSONObject();
    //POST request body
    request.put("name", "Raj");
    request.put("Job", "Automation Engineer");

    given().
      baseUri("https://reqres.in").
      header("Content-Type", "application.json").
      contentType(ContentType.JSON).
      accept(ContentType.JSON).
      body(request.toString());
    when().
      post("/api/users").
      then().
      assertThat().
      statusCode(201);
  }
}
