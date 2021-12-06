FROM openjdk:17-slim-buster

ENV APP taskify-organization-management-service
WORKDIR /opt/$APP

COPY ./target/taskify-organization-management-service-0.0.1-SNAPSHOT.jar /opt/$APP
COPY ./src/main/resources/application.properties /opt/$APP

EXPOSE 8080

CMD ["java","-jar", "taskify-organization-management-service-0.0.1-SNAPSHOT.jar"]
