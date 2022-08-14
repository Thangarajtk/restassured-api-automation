package com.automation.practice;

import org.testng.annotations.Test;

import java.io.*;

import static io.restassured.RestAssured.given;

public class DownloadFile {

    @Test
    public void testDownloadFile() throws IOException {
        InputStream is = given().
                baseUri("https://raw.githubusercontent.com").
                log().all().
                when().
                get("/appium/appium/master/sample-code/apps/ApiDemos-debug.apk").
                then().
                log().all().
                extract().asInputStream();

        OutputStream os = new FileOutputStream("ApiDemos-debug.apk");
        byte[] bytes = new byte[is.available()];
        is.read(bytes);
        os.write(bytes);
        os.close();
    }
}
