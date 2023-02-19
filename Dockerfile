FROM openjdk:17-alpine

WORKDIR usr/app

ADD target/selenium-docker.jar        selenium-docker.jar
ADD target/selenium-docker-tests.jar  selenium-docker-tests.jar
ADD target/libs                       libs

COPY src/test/resources/main-api-runner.xml   main-api-runner.xml
COPY src/test/resources/JsonSchema.json       JsonSchema.json
COPY src/test/resources/responseApiBody.json  responseApiBody.json

ENTRYPOINT java -cp selenium-docker.jar:selenium-docker-tests.jar:libs/*  org.testng.TestNG main-api-runner.xml

# mvn clean package -DskipTests
# docker build -t roronoazorroippo/restassured-project1:latest .
# docker run --rm -v E:/MAX/EPAM/AT_Mentoring_Java_Basic/testResult:/usr/app/test-output --name restassured-project1 roronoazorroippo/restassured-project1