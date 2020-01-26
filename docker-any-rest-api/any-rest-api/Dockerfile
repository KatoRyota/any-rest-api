FROM openjdk:11-jdk-slim
COPY any-rest-api/target/any-rest-api.jar /app/
#COPY docker-any-rest-api/any-rest-api/config /app/config
WORKDIR /app/
ENTRYPOINT ["java","-jar","any-rest-api.jar"]
