package com.automation.practice.jsonserver;

import com.automation.pojo.Employee;
import com.automation.reports.ExtentLogger;
import com.automation.requestbuilder.RequestBuilder;
import com.automation.utils.ApiUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testng.annotations.Test;

import static com.automation.requestbuilder.RequestBuilder.buildRequest;
import static com.automation.utils.RandomUtils.*;
import static org.assertj.core.api.Assertions.assertThat;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EndToEndTest {

    @Test
    public void endToEndTest() {
        // Make a get call to the localhost:3000
        Response response = buildRequest()
                .get("/employees");

        // Get the size of an JSON Array
        int jsonArraySize = response.jsonPath().getList("$").size();

        // Make POST call using builder and pojo
        Employee employee = Employee.builder().firstname(getFirstname()).lastname(getLastname()).id(getId()).build();
        RequestSpecification requestSpecification = buildRequest().body(employee);
        ExtentLogger.logRequest(requestSpecification);
        Response postResponse = requestSpecification.post("/employees");
        ExtentLogger.logResponse(postResponse.asString());

        // Assert post call is success
        assertThat(buildRequest().get("/employees").jsonPath().getList("$").size()).isEqualTo(jsonArraySize + 1);

        // Update
        employee.setFirstname("Default first name");
        int id = employee.getId();
        Response putResponse = buildRequest().pathParam("id", id).body(employee).put("/employees/{id}");
        ApiUtils.storeStringAsJsonFile("response.txt", putResponse);
        ExtentLogger.logResponse(putResponse.asString());

        // Delete
        buildRequest().pathParam("id", id).delete("/employees/{id}");

        assertThat(buildRequest().get("/employees").jsonPath().getList("$").size()).isEqualTo(jsonArraySize);
    }
}
