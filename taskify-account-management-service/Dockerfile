FROM openjdk:17-slim-buster

ENV APP taskify-account-management-service
WORKDIR /opt/$APP

COPY ./target/taskify-account-management-service-0.0.1-SNAPSHOT.jar /opt/$APP
COPY ./src/main/resources/application.properties /opt/$APP

EXPOSE 8081

CMD ["java","-jar", "taskify-account-management-service-0.0.1-SNAPSHOT.jar"]
