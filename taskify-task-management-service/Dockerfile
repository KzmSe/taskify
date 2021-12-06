FROM openjdk:17-slim-buster

ENV APP taskify-task-management-service
WORKDIR /opt/$APP

COPY ./target/taskify-task-management-service-0.0.1-SNAPSHOT.jar /opt/$APP
COPY ./src/main/resources/application.properties /opt/$APP

EXPOSE 8082

CMD ["java","-jar", "taskify-task-management-service-0.0.1-SNAPSHOT.jar"]
