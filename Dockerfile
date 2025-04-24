FROM maven:latest AS build

WORKDIR /app

COPY . /app

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=build /app/target/ecommerce-0.0.1-SNAPSHOT.jar /app-1.0.0.jar

RUN mkdir -p /app/uploads

COPY src/main/resources/static/img/ /app/uploads/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app-1.0.0.jar"]
