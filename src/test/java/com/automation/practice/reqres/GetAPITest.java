package com.automation.practice.reqres;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public final class GetAPITest {

  private Response response;

  @BeforeClass
  public void beforeTest() {
    baseURI = "https://reqres.in/api/unknown";
    RequestSpecification requestSpecification = given();
    response = requestSpecification.request(Method.GET, "");
  }

  @Test
  public void GetWeatherDetailsForChennai() {
    String responseBody = response.getBody().asString();
    System.out.println("Response Body is =>  " + responseBody);

    int statusCode = response.getStatusCode();
    System.out.println("Status code:" + statusCode);

    // First get the JsonPath object instance from the Response interface
    JsonPath jsonPathEvaluator = response.jsonPath();

    List<String> li = jsonPathEvaluator.getList("data.name");
    System.out.println(li.size());

    String[] strArr = new String[li.size()];
    for (int i = 0; i < li.size(); i++) {
      strArr[i] = li.get(i);
    }

    String str = Arrays.toString(strArr);
    str = str.substring(1, str.length() - 1);
    System.out.println(str);

//		obj.writeData("TC01","Name", str);
  }
}
