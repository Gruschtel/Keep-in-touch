FROM openjdk:8-jdk-alpine
VOLUME /tmp
ARG JAR_FILE=target/keep_in_touch.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8082
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]