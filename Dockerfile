FROM openjdk:17-jdk-slim as builder
COPY gradlew .
COPY gradle gradle
COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootjar


FROM openjdk:17-jdk-slim
ENV SERVER_PORT=${SERVER_PORT}
ENV R2DBC_URL=r2dbc:mariadb://${DATABASE_URI}:3306/GATEWAY
ENV R2DBC_USERNAME=${DB_USER}
ENV R2DBC_PASSWORD=${DB_PASSWORD}
COPY --from=builder build/libs/*.jar app.jar
EXPOSE ${SERVER_PORT}
ENTRYPOINT ["java","-Dspring.profiles.active=product","-jar","/app.jar"]