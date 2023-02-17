package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;

import static utils.RouteApiConstants.BASE_URL_PATH;
import static utils.RouteApiConstants.USERS;


public abstract class BaseTest {


    RequestSpecification requestSpecification;

    public static Logger logger = Logger.getLogger(BaseTest.class);

    @BeforeSuite
    private static void deleteAllureResults() {

        Path path = FileSystems.getDefault().getPath("//target/allure-results");
        try {
            Files.deleteIfExists(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (IOException ix) {
            ix.printStackTrace();
        }
    }

    @BeforeClass
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL_PATH)
                .setBasePath(USERS)
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();
    }


    public QueryableRequestSpecification queryRequestInfo() {
        return SpecificationQuerier.query(requestSpecification);
    }


    @BeforeMethod
    public void beforeMethod(Method m) {
        logger.info("WAS STARTED TEST: " + m.getName());
        logger.info("THREAD ID: " + Thread.currentThread().getId());
    }

}
