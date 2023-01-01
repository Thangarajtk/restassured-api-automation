package com.automation.practice;

import org.testng.annotations.Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import static com.automation.constants.FrameworkConstants.RESOURCES_FOLDER_PATH;
import static io.restassured.RestAssured.given;

public final class DownloadFileTest {

  @Test
  public void testDownloadFile() throws IOException {
    InputStream is = given().
      baseUri("https://github.com").
      log().all().
      when().
      get("/appium/java-client/blob/master/src/test/resources/apps/ApiDemos-debug.apk").
      then().
      log().all().
      extract().asInputStream();

    OutputStream os = new FileOutputStream(RESOURCES_FOLDER_PATH + "ApiDemos-debug.apk");
    byte[] bytes = new byte[is.available()];
    is.read(bytes);
    os.write(bytes);
    os.close();
  }
}
