##### To start tests run command:

`mvn clean test`

##### To start allure report run command:

`mvn allure:serve`

##### To start tests via Docker run command:

`docker run  -v E:/MAX/EPAM/AT_Mentoring_Java_Basic/testResult/allure-results:/usr/app/allure-results --name restassured-project1 roronoazorroippo/restassured-project1`

#### **_NOTE:_** Change Default Path to yours in this part:

 -v ~~E:/MAX/EPAM/AT_Mentoring_Java_Basic/testResult~~/allure-results:/usr/app/allure-results

run tests, after that open "-v" local folder and run the command via CMD/git bash:

**_`allure serve`_**