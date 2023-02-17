package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.file.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static utils.RouteApiConstants.BASE_URL_PATH;
import static utils.RouteApiConstants.USERS;


public abstract class BaseTest {


    RequestSpecification requestSpecification;
    ResponseSpecification responseSpecification;

    public static File responseJsonBody;

    public static Logger logger = Logger.getLogger(BaseTest.class);

    public PrintStream printStream;

    @BeforeSuite
    private static void deleteAllureResults() {

        Path path = FileSystems.getDefault().getPath("//target/allure-results");
        try {
            Files.deleteIfExists(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", path);
        } catch (IOException ix) {
            ix.printStackTrace();
        }

        responseJsonBody = new File("src/test/resources/responseApiBody.json");
    }

    @BeforeClass
    public void setUp() throws FileNotFoundException {

        printStream = new PrintStream("src/test/resources/RestAPILog.log");

        requestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL_PATH)
                .setBasePath(USERS)
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        responseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectBody(matchesJsonSchemaInClasspath("JsonSchema.json"))
                .expectContentType(ContentType.JSON)
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
