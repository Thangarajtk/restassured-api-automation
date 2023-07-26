package com.automation.practice.jsonserver;

import com.automation.constants.FrameworkConstants;
import com.automation.utils.ExcelUtils;
import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;


public final class PostRequestDataDrivenExcelTest {

  @DataProvider(name = "input")
  public Iterator<Object[]> getPostData() throws IOException {
    List<Map<String, Object>> excelData = ExcelUtils.getExcelData(
      FrameworkConstants.RESOURCES_FOLDER_PATH + File.separator + "TestData.xlsx");

    List<Object[]> dataList = new ArrayList<>();
    for (Map<String, Object> data: excelData) {
      dataList.add(new Object[] {data});
    }
    return dataList.iterator();
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
