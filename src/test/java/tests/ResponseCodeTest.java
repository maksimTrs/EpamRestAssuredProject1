package tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.assertj.core.api.Assertions.assertThat;
import static utils.StatusCode.CODE_200;

@Feature("API <<< jsonplaceholder.typicode.com/users >>> test")
public class ResponseCodeTest extends BaseTest {

    @Story("TEST: status code of the obtained response is 200 OK")
    @Test(description = "TEST: status code of the obtained response is 200 OK")
    public void validateResponseStatusCodeTest() {

        Response response = given()
                .spec(requestSpecification)
                .filter(new RequestLoggingFilter(LogDetail.URI, printStream))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, printStream))
                .when()
                .get()
                .then()
                .spec(responseSpecification)
                .extract().response();

        assertThat(response.statusCode()).isEqualTo(CODE_200.CODE);
        assertThat(response.statusLine()).contains(CODE_200.MSG);
    }
}
