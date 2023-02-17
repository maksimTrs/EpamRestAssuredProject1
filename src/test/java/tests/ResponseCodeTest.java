package tests;


import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.Test;

@Feature("API <jsonplaceholder.typicode.com/users> test")
public class ResponseCode200Test {

    @Story("TEST: status code of the obtained response is 200 OK")
    @Test(description = "TEST: status code of the obtained response is 200 OK")
    public void validate200Response(){


    }
}
