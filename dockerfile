
FROM node:14.17.3-buster AS nodebuild

WORKDIR /Frontend

COPY angular/angular.json /Frontend
COPY angular/package.json /Frontend
COPY angular/package-lock.json /Frontend
COPY angular/src/ /Frontend/src/
COPY angular/tsconfig.json /Frontend
COPY angular/tsconfig.app.json /Frontend
COPY angular/tsconfig.spec.json /Frontend

RUN ["npm", "install"]
RUN ["npm", "run", "build"]

FROM maven:3.8.1-jdk-8 AS build
WORKDIR /Backend

COPY src/ /Backend/src
COPY pom.xml /Backend/pom.xml

COPY --from=nodebuild /Frontend/dist/WriteClientReport /Backend/src/main/webapp

RUN ["mvn", "clean", "package"]

FROM openjdk:8-jdk-buster

COPY --from=build /Backend/src/main/resources /src/main/resources
COPY --from=build /Backend/target/WriteClientReportForm-0.0.1-SNAPSHOT.war app.war
ENTRYPOINT ["java","-jar","/app.war"]

