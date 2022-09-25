FROM openjdk:18.0.2.1-jdk-slim-buster
ARG JAR_FILE=target/*.jar
ARG PROPS_FILE=target/classes/application.properties
COPY ${JAR_FILE} cake-manager.jar
COPY ${PROPS_FILE} application.properties
ENTRYPOINT ["java","-jar","/cake-manager.jar"]