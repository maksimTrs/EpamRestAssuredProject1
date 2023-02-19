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

import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.nio.file.*;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static utils.RouteApiConstants.BASE_URL_PATH;
import static utils.RouteApiConstants.USERS;


public abstract class BaseTest {


    public static String responseJsonBody = "src/test/resources/responseApiBody.json";
    public static Logger logger = Logger.getLogger(BaseTest.class);
    public static PrintStream printStream;
    public static RequestSpecification testRequestSpecification;
    public static ResponseSpecification testResponseSpecification;

    @BeforeSuite
    private static void executePreConditions() {

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
    }

    @BeforeClass
    public void setUp() throws IOException {

        Path directories = Files.createDirectories(Paths.get("logs/" + System.currentTimeMillis()));
        printStream = new PrintStream(directories + "/RestAPILog.log_" + System.currentTimeMillis());

        testRequestSpecification = new RequestSpecBuilder()
                .setBaseUri(BASE_URL_PATH)
                .setBasePath(USERS)
                .addFilter(new AllureRestAssured())
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        testResponseSpecification = new ResponseSpecBuilder()
                .log(LogDetail.ALL)
                .expectBody(matchesJsonSchemaInClasspath("JsonSchema.json"))
                .expectContentType(ContentType.JSON)
                .build();
    }

    public QueryableRequestSpecification queryRequestInfo() {
        return SpecificationQuerier.query(testRequestSpecification);
    }


    @BeforeMethod
    public void beforeMethod(Method m) {

        logger.info("********************************************************************************");
        logger.info("WAS STARTED TEST: " + m.getName());
        logger.info("THREAD ID: " + Thread.currentThread().getId());
        logger.info("********************************************************************************");
    }
}
