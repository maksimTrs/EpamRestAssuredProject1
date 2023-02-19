package utils;

import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import static tests.BaseTest.*;


public class ResponseHelper {

    public static Response extractApiResponse() {
        return given()
                .spec(testRequestSpecification)
                .filter(new RequestLoggingFilter(LogDetail.URI, printStream))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, printStream))
                .when()
                .get()
                .then()
                .spec(testResponseSpecification)
                .extract().response();
    }
}
