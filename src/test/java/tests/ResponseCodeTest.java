package tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.ResponseHelper.extractApiResponse;
import static utils.StatusCode.CODE_200;

@Feature("API <<< jsonplaceholder.typicode.com/users >>> test")
public class ResponseCodeTest extends BaseTest {

    @Story("TEST: status code of the obtained response is 200 OK")
    @Test(description = "TEST: status code of the obtained response is 200 OK")
    public void validateResponseStatusCodeTest() {

        Response response = extractApiResponse();

        assertThat(response.statusCode()).isEqualTo(CODE_200.CODE);
        assertThat(response.statusLine()).contains(CODE_200.MSG);
        // assertThat(queryRequestInfo().getHeaders().toString(), containsString("header=value1"));
        // assertThat(response.body().asString(), matchesJsonSchemaInClasspath("JsonSchema.json"));
    }
}
