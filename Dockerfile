FROM openjdk:8-jdk-alpine

WORKDIR /opt/app

VOLUME /tmp

EXPOSE 8080

ARG JAR_FILE=dist/back.jar

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
