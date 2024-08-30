FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/authentication-service*.jar /app/authentication-service.jar
CMD ["java", "-jar", "authentication-service.jar"]