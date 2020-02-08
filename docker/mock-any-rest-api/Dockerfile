FROM openjdk:11-jdk-slim
COPY mock-any-rest-api/target/mock-any-rest-api.jar /app/
WORKDIR /app/
ENTRYPOINT ["java", "-Dloader.path=./config/,./data/", "-jar", "mock-any-rest-api.jar"]
