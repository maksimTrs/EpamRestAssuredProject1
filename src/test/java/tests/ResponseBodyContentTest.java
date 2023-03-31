package tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import pojo.UserItem;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("API <<< jsonplaceholder.typicode.com/users >>> test")
public class ResponseBodyContentTest extends BaseTest {

    @Story("TEST: the content of the response body is the array of 10 users")
    @Test(description = "TEST: the content of the response body is the array of 10 users")
    public void validateResponseEntitiesCountTest() {

        List<UserItem> users = given()
                .spec(testRequestSpecification)
                .filter(new RequestLoggingFilter(LogDetail.URI, printStream))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, printStream))
                .when()
                .get()
                .then()
                .spec(testResponseSpecification)
                //.extract().as(UserItem.class);
                .extract().as(new TypeRef<>() {
                });
        // UserItem[] users = response.as(UserItem[].class);

        assertThat(users.stream().mapToInt(UserItem::getId).count())
                .isEqualTo(10);
    }

    @Story("TEST: the content of the response body is the array of 10 users")
    @Test(description = "TEST: the content of the response body is the array of 10 users")
    public void validateResponseEntitiesCountTest3() {

        List<UserItem> users = given()
                .spec(testRequestSpecification)
                .filter(new RequestLoggingFilter(LogDetail.URI, printStream))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, printStream))
                .when()
                .get()
                .then()
                .spec(testResponseSpecification)
                .extract().jsonPath()
                .getList(".", UserItem.class);

        assertThat(users.size()).isEqualTo(10);
    }


    @Story("TEST: the content of the response body is the array of 10 users - version 2.0")
    @Test(description = "TEST: the content of the response body is the array of 10 users - version 2.0")
    public void validateResponseEntitiesCountTest2() {

        Response response = given()
                .spec(testRequestSpecification)
                .filter(new RequestLoggingFilter(LogDetail.URI, printStream))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, printStream))
                .when()
                .get()
                .then()
                .spec(testResponseSpecification)
                /* .assertThat()
                 .body("id", hasSize(10))*/
                .extract().response();

        assertThat(response.jsonPath().getList("id"))
                .hasSize(10);
    }
}
