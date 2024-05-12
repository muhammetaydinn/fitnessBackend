# Use an official OpenJDK 17 JDK slim image as the base
FROM openjdk:17
COPY target/security-0.0.1-SNAPSHOT.jar security-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/security-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080