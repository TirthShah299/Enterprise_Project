FROM openjdk:21-slim
WORKDIR /app
COPY target/EdgeServer-0.0.1-SNAPSHOT.jar /app/edge-service.jar
ENV SPRING_PROFILES_ACTIVE=prod
CMD ["java", "-jar", "edge-service.jar"]