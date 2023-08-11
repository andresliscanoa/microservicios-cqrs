FROM openjdk:17.0.1-slim
LABEL authors="lliscano"
WORKDIR /opt/app
COPY target/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]