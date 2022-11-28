package com.automation.practice.reqres;

import java.util.Arrays;
import java.util.List;
import org.testng.annotations.*;
import com.automation.base.BaseTest;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public final class GetAPITest extends BaseTest {

    @BeforeClass
    public void beforeTest() {
        baseURI = "https://reqres.in/api/unknown";
        requestSpecification = given();
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