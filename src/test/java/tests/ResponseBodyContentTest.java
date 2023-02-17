package tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.common.mapper.TypeRef;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import org.testng.annotations.Test;
import pojo.UserItem;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;

@Feature("API <<< jsonplaceholder.typicode.com/users >>> test")
public class ResponseBodyContentTest extends BaseTest {

    @Story("TEST: the content of the response body is the array of 10 users")
    @Test(description = "TEST: the content of the response body is the array of 10 users")
    public void validateResponseEntitiesCountTest() throws IOException {

        List<UserItem> users = given()
                .spec(requestSpecification)
                .filter(new RequestLoggingFilter(LogDetail.URI, printStream))
                .filter(new ResponseLoggingFilter(LogDetail.ALL, printStream))
                .when()
                .get()
                .then()
                .spec(responseSpecification)
                //.extract().as(UserItem.class);
                .extract().as(new TypeRef<>() {});


/*
        assertThat(user.getUsersMain().stream().map(UserItem::getId).count())
                .isNotNull()
                .extracting(UserItem::getId)
                .isEqualTo(10);
*/

                //  assertThat(response.path("data"), hasSize(2));

        assertThat(users.stream().mapToInt(UserItem::getId).count())
                .isEqualTo(10);

    }
}
