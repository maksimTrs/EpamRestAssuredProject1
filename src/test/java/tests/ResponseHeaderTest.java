package tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.StatusCode.CODE_200;

@Feature("API <<< jsonplaceholder.typicode.com/users >>> test")
public class ResponseHeaderTest  extends BaseTest {

    @Story("TEST: check the content-type header")
    @Test(description = "TEST: check the content-type header")
    public void validateResponseHeaderTest() throws IOException {

        Response response = given()
                .spec(requestSpecification)
                .filter(new RequestLoggingFilter(LogDetail.URI, printStream))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, printStream))
                .when()
                .get()
                .then()
                .spec(responseSpecification)
                .extract().response();

        assertThat(response.getHeaders().toString()).contains("Content-Type");
        assertThat(response.getHeader("Content-Type")).isEqualTo("application/json; charset=utf-8");

        assertThat(new String(Files.readAllBytes(Paths.get(responseJsonBody))))
                .containsIgnoringWhitespaces(response.body().asString());
    }
}
