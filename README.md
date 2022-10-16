# new-cake-manager
Pre Requisite: OAuth2 ClientId and ClientSecret should be configured before starting application

To build the application locally

- Run below mvn command
  - mvn clean package
  
- Run build docker image command from project root location
  - docker build -t nkanat/cake-manager .

To run the application
  - Execute docker run command
    - docker run --name cake-manager -d -p 8088:8088 nkanat/cake-manager

Application swagger url: http://localhost:8088/cake-manager/swagger-ui/index.html

All endpoints:

  - [GET] http://localhost:8088/cake-manager/
  - [GET] http://localhost:8088/cake-manager/cakes
  - [POST] http://localhost:8088/cake-manager/cakes

To check jacoco code coverage report: http://localhost:64981/new-cake-manager/target/site/jacoco/index.html

