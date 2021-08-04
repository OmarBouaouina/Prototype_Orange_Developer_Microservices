#FROM openjdk:8-jdk-alpine as Build
#WORKDIR /app
#COPY mvnw .
#COPY .mvn .mvn
#COPY pom.xml .
#RUN ./mvnw dependency:go-offline -B
#COPY src src
#RUN ./mvnw package -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)
#
#FROM openjdk:8-jre-alpine
#ARG DEPENDENCY=/app/target/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.orange.usermicroservice.UserMicroServiceApplication"]

FROM openjdk:8-jdk-alpine
MAINTAINER orange.com
COPY target/user-microservice-1.0.0.jar user-microservice-1.0.0.jar
ENTRYPOINT ["java", "-jar", "/user-microservice-1.0.0.jar"]