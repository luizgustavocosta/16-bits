FROM openjdk:11.0.9.1-slim
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} spring-helloworld.jar
ENTRYPOINT ["java","-jar","/spring-helloworld.jar"]
EXPOSE 8090