FROM openjdk:21-slim
WORKDIR /app
COPY target/edge-server-1.0.0.jar /app/edge-service.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "edge-service.jar"]