FROM openjdk:11-jdk-slim
COPY mock-any-rest-api/target/mock-any-rest-api.jar /app/
#COPY docker-any-rest-api/mock-any-rest-api/config /app/config
WORKDIR /app/
ENTRYPOINT ["java","-jar","mock-any-rest-api.jar"]
