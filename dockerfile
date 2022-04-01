
FROM maven:3.8.1-jdk-8 AS build
WORKDIR /Backend

COPY src/ /Backend/src
COPY pom.xml /Backend/pom.xml
RUN ["mvn", "clean", "package"]

FROM openjdk:8-jdk-buster

COPY --from=build /Backend/src/main/resources /src/main/resources
COPY --from=build /Backend/target/WriteClientReportForm-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java","-jar","/app.war"]

