#
# Build stage
#
FROM maven:3.6.0-jdk-11-slim AS build
COPY src /home/app/src
COPY pom.xml /home/app
COPY mvnw /home/app
RUN mvn -f /home/app/pom.xml clean package

#
# Package stage
#
FROM openjdk:11-jre-slim
# COPY --from=build /home/app/target/classes/com/crypto/CryptoBackend /usr/local/CryptoBackend
COPY --from=build /home/app/ /usr/local/
EXPOSE 8080
ENTRYPOINT ["/usr/local/mvnw", "spring-boot:run"]