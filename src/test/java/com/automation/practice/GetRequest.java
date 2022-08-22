package com.automation.practice;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import org.json.JSONArray;
import org.json.JSONObject;
import com.automation.base.BaseTest;
import org.testng.annotations.*;
import static io.restassured.RestAssured.*;

public class GetRequest extends BaseTest {

    static String responseString = null;

    @BeforeClass
    public void beforeTest()
    {
        baseURI = "http://restapi.demoqa.com/utilities/weather/city";
        requestSpecification = given();
        response = requestSpecification.request(Method.GET, "/Bangalore");
    }

    @Test
    public void GetWeatherDetailsForBlr()
    {
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is =>  " + responseBody);

        int statusCode = response.getStatusCode();
        System.out.println("Status code:"+statusCode);

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();
        String city = jsonPathEvaluator.get("City");
        System.out.println(city);
        String humidity = jsonPathEvaluator.get("Humidity");
        String temperature = jsonPathEvaluator.get("Temperature");

		/* obj.writeData("TC02","ResponseBody", responseBody);
		obj.writeData("TC02","City", city);
		obj.writeData("TC02","Humidity", humidity);
		obj.writeData("TC02","Temperature", temperature);*/

    }

    public static String parseGetRequest() {
        try {
            String url = "/utilities/weather/city";
            //String authorization = "Basic XXXXXXXXXXXXX";
            String authorization = "No auth";

            responseString = HandlingJson.HttpGetResponseAsString(url, authorization);

            if (response == null) {
                System.exit(0);
            } else {
                final JSONArray json = new JSONArray(response);
                final int count = json.length();

                for (int i = 0; i < count; i++) {
                    final JSONObject fields = json.getJSONObject(i);
                    String city = fields.getString("City");

                    System.out.println(city);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseString;
    }

}

class HandlingJson {

    public static JSONObject HttpGetResponseAsJSON(String url, String auth) {
        JSONObject obj = null;

        try {
            HttpResponse<JsonNode> response = Unirest
                    .get(url)
                    .header("Authorization", auth)
                    .asJson();

            int responsecode = response.getStatus();

            if (responsecode == 200) {
                obj = response.getBody().getObject();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String HttpGetResponseAsString(String url, String auth) {
        String obj = null;

        try {
            HttpResponse<String> response = Unirest.get(url).header("Authorization", auth).asString();

            int responsecode = response.getStatus();

            if (responsecode == 200) {
                obj = response.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

    public static String HttpPostResponseAsString(String url, String auth) {
        String obj = null;
        try {
            HttpResponse<String> response = Unirest.get(url)
                    .header("Authorization", auth)
                    .header("Cache-Control", "no-cache").asString();

            int responsecode = response.getStatus();

            if (responsecode == 200) {
                obj = response.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }

}
