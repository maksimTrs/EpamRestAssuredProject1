package tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static utils.ResponseHelper.extractApiResponse;

@Feature("API <<< jsonplaceholder.typicode.com/users >>> test")
public class ResponseHeaderTest extends BaseTest {

    @Story("TEST: check the content-type header")
    @Test(description = "TEST: check the content-type header")
    public void validateResponseHeaderTest() throws IOException {

        Response response = extractApiResponse();

        assertThat(response.getHeaders().toString()).contains("Content-Type");
        assertThat(response.getHeader("Content-Type")).isEqualTo("application/json; charset=utf-8");

        assertThat(new String(Files.readAllBytes(Paths.get(responseJsonBody))))
                .isEqualToIgnoringWhitespace(response.body().asString());

        logger.debug("|||<<< Were Sent Header ContentType data:  " + queryRequestInfo().getContentType() + " >>>|||");
    }
}
