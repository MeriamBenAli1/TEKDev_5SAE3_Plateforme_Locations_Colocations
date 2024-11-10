FROM openjdk:17-jdk-alpine

ADD target/Gateway-0.0.1-SNAPSHOT.jar Gateway.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "Gateway.jar"]
