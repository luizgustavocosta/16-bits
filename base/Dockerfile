FROM openjdk:11.0.9.1-slim
COPY target/*jar-with-dependencies.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
CMD ["", ""]