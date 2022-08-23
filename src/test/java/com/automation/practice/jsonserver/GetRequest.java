package com.automation.practice.jsonserver;

import com.automation.requestbuilder.RequestBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetRequest {

    @Test
    public void getEmployeeDetails() {

        RequestBuilder.buildRequestForGetCalls()
                .pathParam("id", 13)
                .when()
                .get("/employees/{id}")
                .then()
                .log().all();
    }
}
