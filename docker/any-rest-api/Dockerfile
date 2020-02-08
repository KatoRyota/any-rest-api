FROM openjdk:11-jdk-slim
COPY any-rest-api/target/any-rest-api.jar /app/
WORKDIR /app/
ENTRYPOINT ["java", "-Dloader.path=./config/,./data/", "-jar", "any-rest-api.jar"]
