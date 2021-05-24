FROM openjdk:12-alpine
VOLUME /tmp
ADD target/app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "-Dserver.port=$PORT", "/app.jar"]