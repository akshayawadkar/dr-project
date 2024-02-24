FROM maven:3.8.4-openjdk-17 AS builder
COPY . /home/app/src
WORKDIR /home/app/src
RUN mvn package


FROM openjdk:8u181-jdk-alpine
EXPOSE 8080
RUN mkdir /app
COPY --from=builder /home/app/src/target/*.jar /app/spring-boot-application.jar
CMD ["java", "-jar", "/app/spring-boot-application.jar"]
