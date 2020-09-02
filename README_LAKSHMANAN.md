I) Spring Profiles:

There are 3 different spring profiles in this application. 
1)InMemory Profile= application-InMemory.yml
2)H2 Database Profile= application-h2.yml
3)SQL DB Profile= application-db.yml

For selecting the correct Spring profile used for testing, please change line 6 in application.yml file to one of the three values
InMemory
h2
db

Example:

spring:
  profiles:
    active: InMemory
    
  
II) Setup of Data for DB Testing:

I had created a sql runner under "src/main/resources" which will be run during the start of the application. Please feel free to use this for any data setup for your testing.
 

III) JUnit and Integration Testing:
The JUnit and Integration tests for Part 1(In Memory) and Part 2(H2/DB persistence) are added  under "src/test/java". The test are written with JUnit4 libraries loaded with pom.xml. 

Sometimes the tests gets started with JUnit5 when we use double click on the Tests. Please use Run->Run Configuration and select JUnit4 to run the tests. 


IV) Support for Swagger:
I had integrated Swagger for the ease of testing. The Swagger UI can be accessed from
http://localhost:5000/swagger-ui.html







