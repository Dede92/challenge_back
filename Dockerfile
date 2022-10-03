FROM maven:3.6.0-jdk-13-alpine AS MAVEN_BUILD

COPY pom.xml /build/
COPY src /build/src/

WORKDIR /build/
RUN mvn package

FROM openjdk:13-jdk-alpine

WORKDIR /app

COPY --from=MAVEN_BUILD /build/target/springboot-0.0.1-SNAPSHOT.jar /app/

ENTRYPOINT ["java", "-jar", "springboot-0.0.1-SNAPSHOT.jar"]
